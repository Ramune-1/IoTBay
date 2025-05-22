package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import model.Product;
import model.dao.ProductDBManager;


@WebServlet ("/ProductServlet")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDBManager productManager = (ProductDBManager) session.getAttribute("productManager");
        try {
            List<Product> products = productManager.getAllProducts();
            session.setAttribute("products", products);
            request.getRequestDispatcher("productList.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDBManager productManager = (ProductDBManager) session.getAttribute("productManager");

        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        Product product = new Product(0, name, quantity, price, description);
        try {
            productManager.addProduct(product);
            response.sendRedirect("ProductServlet");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        
        }

     }
    }
