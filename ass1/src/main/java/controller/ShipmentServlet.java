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

        HttpSession session = request.getSession();  // ✅ Needed first
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String customerId = customer.getCustomerID();
        String action = request.getParameter("action");  // ✅ Also needed

        String orderId = request.getParameter("orderId");
        String address = request.getParameter("address");
        String shipmentDate = request.getParameter("shipmentDate");
        String method = request.getParameter("method");
        String status = request.getParameter("status");

        if ("update".equalsIgnoreCase(action)) {
            int shipmentId = Integer.parseInt(request.getParameter("shipmentId"));
            Shipment updatedShipment = new Shipment(shipmentId, orderId, customerId, address, shipmentDate, method, status);
            shipmentDAO.updateShipment(updatedShipment);
        } else {
            Shipment shipment = new Shipment(0, orderId, customerId, address, shipmentDate, method, status);
            shipmentDAO.insertShipment(shipment);
        }

        response.sendRedirect("shipment?action=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String customerId = customer.getCustomerID();


        if (action == null) action = "";

        switch (action.toLowerCase()) {
            case "list":
                List<Shipment> shipments = shipmentDAO.getAllShipment(customerId);
                request.setAttribute("shipments", shipments);
                request.getRequestDispatcher("list.jsp").forward(request, response);
                break;

            case "edit":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Shipment s = shipmentDAO.getShipmentById(id);
                    if (s != null && s.getCustomerId().equals(customerId)) {
                        if (s.getStatus().equalsIgnoreCase("Finalised")) {
                            response.sendRedirect("shipment?action=list"); // block edit
                            return;
                        }
                        request.setAttribute("shipment", s);
                        request.getRequestDispatcher("editShipment.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("shipment?action=list");
                    }
                } catch (NumberFormatException e) {
                    response.sendRedirect("shipment?action=list");
                }
                break;

            case "delete":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Shipment s = shipmentDAO.getShipmentById(id);
                    if (s != null && s.getCustomerId().equals(customerId)) {
                        if (!s.getStatus().equalsIgnoreCase("Finalised")) {
                            shipmentDAO.deleteShipment(id);
                        }
                    }

                } catch (NumberFormatException e) {
                    // ignore
                }
                response.sendRedirect("shipment?action=list");
                break;

            case "search":
                String idStr = request.getParameter("id");
                String date = request.getParameter("date");
                Integer id = null;
                try {
                    if (idStr != null && !idStr.isEmpty()) {
                        id = Integer.parseInt(idStr);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[WARN] Invalid shipment ID format.");
                }

                List<Shipment> searchResults = shipmentDAO.searchShipment(customerId, id, date);
                request.setAttribute("shipments", searchResults);
                request.getRequestDispatcher("list.jsp").forward(request, response);
                break;

            default:
                response.sendRedirect("index.jsp");
        }
    }
}
