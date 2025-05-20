package controller;
import controller.utility.Validator;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.*;
import model.dao.OrderDBManager;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();

        Customer customer = (Customer) session.getAttribute("customer");
        Cart cart = (Cart) session.getAttribute("cart");
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");
        if (customer == null || cart == null || orderManager == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            Time orderDate = new Time(System.currentTimeMillis());

            Order order = new Order(
                0,
                cart.getCustomerID(),
                cart.getPaymentID(),
                cart.getDeliveryID(),
                cart.getTotalPrice(),
                orderDate
            );

            boolean success = orderManager.addOrder(order);

            if (success) {
                session.removeAttribute(("cart"));
                response.sendRedirect("orderConfirmation.jsp");
        }   else {
                session.setAttribute("errorMsg", "Failed to place order.");
                response.sendRedirect("checkout.jsp");
        }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }



       
    }
}
