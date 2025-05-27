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
import model.CustomerLog;
import model.StaffAccessLog;
import model.StaffAccount;
import model.dao.CustomerDBManager;
import model.dao.StaffAccessLogDBManager;
import model.dao.StaffDBManager;

@WebServlet("/RemoveServlet")
public class RemoveServlet  extends HttpServlet{

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
    CustomerDBManager customerManager = (CustomerDBManager) session.getAttribute("customerManager");
        StaffAccount staff = (StaffAccount) session.getAttribute("staff");
        StaffDBManager staffDBManager = (StaffDBManager) session.getAttribute("staffManager");

        if (staffDBManager == null) throw new IOException("StaffManager not found");  
         if (customerManager == null) throw new IOException("CustomerManager not found");  
               Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            try {
            customerManager.removeCustomer(customer.getCustomerID());
        } catch (Exception e) { 
            Logger.getLogger(RemoveServlet.class.getName()).log(Level.SEVERE, null, e);         

        }
        }
        if (staff != null) {
            try {
            staffDBManager.removeStaff(staff.getStaffID());
        } catch (Exception e) { 
            Logger.getLogger(RemoveServlet.class.getName()).log(Level.SEVERE, null, e);         

        }
        }
          session.invalidate();
        response.sendRedirect("index.jsp");
    }
    
}
