package controller;

import model.Product;
import model.CartItem;
import model.dao.ProductDBManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        List<Product> products = (List<Product>) session.getAttribute("products");
        if (products == null) {
            response.sendRedirect("product.jsp"); // or an error page
            return;
        }

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        for (Product product : products) {
            String paramName = "quantity_" + product.getProductID();
            String quantityStr = request.getParameter(paramName);

            if (quantityStr != null && !quantityStr.isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityStr);
                    if (quantity > 0) {
                        boolean found = false;
                        for (CartItem item : cart) {
                            if (item.getProduct().getProductID() == product.getProductID()) {
                                item.setQuantity(item.getQuantity() + quantity);
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            cart.add(new CartItem(product, quantity));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity for product ID: " + product.getProductID());
                }
            }
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("viewCart.jsp"); // Redirect to the cart page
    }
}