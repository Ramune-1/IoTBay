package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Customer;
import model.Order;
import model.dao.OrderDBManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/CustomerOrderListServlet")
public class CustomerOrderListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");

        String searchID = request.getParameter("orderNumber");
        String searchDate = request.getParameter("orderDate");

        try {
            List<Order> orders;

            if ((searchID != null && !searchID.isEmpty()) || (searchDate != null && !searchDate.isEmpty())) {
                orders = orderManager.searchOrders(customer.getCustomerID(), searchID, searchDate);
            } else {
                orders = orderManager.getOrdersByCustomerID(customer.getCustomerID());
            }

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("customersOrders.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
   
    }
}
}