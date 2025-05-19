package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import model.*;
import model.dao.ProductDBManager;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet  {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int productID = Integer.parseInt(request.getParameter("productID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ProductDBManager productManager = (ProductDBManager) session.getAttribute("productManager");
        if (productManager == null) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            Product product = productManager.findProductByID(productID);

            CartItem cartItem = new CartItem(product, quantity);

            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }



            cart.addItem(cartItem);
            session.setAttribute("cart", cart);
            response.sendRedirect("cart.jsp");


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
