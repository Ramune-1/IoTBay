package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import model.Shipment;
import model.Customer;
import model.dao.ShipmentDAO;

@WebServlet("/shipment")
public class ShipmentServlet extends HttpServlet {

    private ShipmentDAO shipmentDAO;

    @Override
    public void init() throws ServletException {
        shipmentDAO = new ShipmentDAO(); // Create Shipment table if not exists
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String customerId = customer.getCustomerID();
        String action = request.getParameter("action");

        String shipmentId = request.getParameter("shipmentId"); // Now String
        String orderId = request.getParameter("orderId");
        String address = request.getParameter("address");
        String shipmentDate = request.getParameter("shipmentDate");
        String method = request.getParameter("method");
        String status = request.getParameter("status");

        if ("update".equalsIgnoreCase(action)) {
            Shipment updatedShipment = new Shipment(shipmentId, orderId, customerId, address, shipmentDate, method, status);
            shipmentDAO.updateShipment(updatedShipment);
        } else {
            // generate unique ID (you can use UUID)
            String newShipmentId = java.util.UUID.randomUUID().toString();
            Shipment shipment = new Shipment(newShipmentId, orderId, customerId, address, shipmentDate, method, status);
            shipmentDAO.insertShipment(shipment);
        }

        response.sendRedirect("thankYou.jsp"); // redirect after successful submission
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "";

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");

        // Allow viewbycustomer to bypass login
        if (!"viewbycustomer".equalsIgnoreCase(action) && customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String customerId = (customer != null) ? customer.getCustomerID() : null;

        switch (action.toLowerCase()) {
            case "list":
                List<Shipment> shipments = shipmentDAO.getAllShipment(customerId);
                request.setAttribute("shipments", shipments);
                request.getRequestDispatcher("listShipment.jsp").forward(request, response);
                break;

            case "edit":
                String idToEdit = request.getParameter("id");
                Shipment s = shipmentDAO.getShipmentById(idToEdit);
                if (s != null && s.getCustomerId().equals(customerId)) {
                    if (s.getStatus().equalsIgnoreCase("Finalised")) {
                        response.sendRedirect("shipment?action=list");
                        return;
                    }
                    request.setAttribute("shipment", s);
                    request.getRequestDispatcher("editShipment.jsp").forward(request, response);
                } else {
                    response.sendRedirect("shipment?action=list");
                }
                break;

            case "delete":
                String idToDelete = request.getParameter("id");
                Shipment d = shipmentDAO.getShipmentById(idToDelete);
                if (d != null && d.getCustomerId().equals(customerId)) {
                    if (!d.getStatus().equalsIgnoreCase("Finalised")) {
                        shipmentDAO.deleteShipment(idToDelete);
                    }
                }
                response.sendRedirect("shipment?action=list");
                break;

            case "search":
                String idStr = request.getParameter("id");
                String date = request.getParameter("date");
                String orderId = request.getParameter("orderId");

                List<Shipment> searchResults = shipmentDAO.searchShipment(customerId, idStr, date, orderId);
                request.setAttribute("shipments", searchResults);
                request.getRequestDispatcher("listShipment.jsp").forward(request, response);
                break;

            case "viewbycustomer":
                String queryCustomerId = request.getParameter("customerId");
                if (queryCustomerId == null || queryCustomerId.trim().isEmpty()) {
                    request.setAttribute("error", "Customer ID cannot be empty.");
                    request.getRequestDispatcher("viewMyShipments.jsp").forward(request, response);
                    return;
                }

                List<Shipment> foundShipments = shipmentDAO.getAllShipment(queryCustomerId);
                request.setAttribute("shipments", foundShipments);
                request.getRequestDispatcher("viewMyShipments.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
        }
    }
}
