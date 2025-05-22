package controller;
import controller.utility.Validator;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;
import model.Order;
import model.dao.OrderDBManager;


@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet{
    private OrderDBManager orderDBManager;

    @Override
    public void init() {
        orderDBManager = (OrderDBManager) getServletContext().getAttribute("orderDBManager");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        String orderID = request.getParameter("orderID");
        String orderDate = request.getParameter("orderDate");

        try {
            List<Order> orders;

            if ((orderID != null && !orderID.isEmpty()) || (orderDate != null && !orderDate.isEmpty())) {
                orders = orderDBManager.searchOrders(customer.getCustomerID(), orderID, orderDate);
            } else {
                orders = orderDBManager.getOrdersByCustomerID(customer.getCustomerID());
            }

            request.setAttribute("orders", orders);
            request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}






