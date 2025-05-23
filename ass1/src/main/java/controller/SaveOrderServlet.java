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
import model.CartItem;
import model.Customer;
import model.Order;
import model.dao.OrderDBManager;

@WebServlet("/SaveOrderServlet")
public class SaveOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        Customer customer = (Customer) session.getAttribute("user");
        OrderDBManager orderManager = (OrderDBManager) session.getAttribute("orderManager");

        if (cart == null || cart.isEmpty() || customer == null) {
            response.sendRedirect("viewCart.jsp");
            return;
        }

        double total = 0;
        for (CartItem item : cart) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        try {
            Time now = new Time(System.currentTimeMillis());

            // TEMP placeholders 
            int placeholderPaymentID = 0;
            int placeholderDeliveryID = 0;

            int orderID = orderManager.addOrder(
                customer.getCustomerID(),
                placeholderPaymentID,
                placeholderDeliveryID,
                total,
                now
            );

            session.removeAttribute("cart");
            session.setAttribute("lastOrderID", orderID);
            response.sendRedirect("orderConfirmation.jsp");

        } catch (SQLException e) {
            throw new ServletException("Order saving failed", e);
        }
    }
}
