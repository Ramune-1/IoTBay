package controller;

import controller.utility.Validator;
import java.io.IOException;


import java.util.logging.Level;

import java.util.logging.Logger;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import model.Customer;
import model.dao.CustomerDBManager;

@WebServlet("/RemoveServlet")
public class RemoveServlet  extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
    CustomerDBManager customerManager = (CustomerDBManager) session.getAttribute("customerManager");
         if (customerManager == null) throw new IOException("Manager not found");  
               Customer customer = (Customer) session.getAttribute("customer");
        try {
            customerManager.removeCustomer(customer.getCustomerID());
        } catch (Exception e) { 
            Logger.getLogger(RemoveServlet.class.getName()).log(Level.SEVERE, null, e);         

        }
          session.invalidate();
        response.sendRedirect("index.jsp");
    }
    
}
