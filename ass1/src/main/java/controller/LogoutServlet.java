package controller;

import controller.utility.Validator;
import java.io.IOException;

import java.sql.SQLException;

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
import model.dao.CustomerAccessLogDBManager;
import model.dao.StaffAccessLogDBManager;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        CustomerAccessLogDBManager customerAccessLogManager = (CustomerAccessLogDBManager) session.getAttribute("customerAccessLogManager");
        StaffAccessLogDBManager staffAccessLogDBManager = (StaffAccessLogDBManager) session.getAttribute("staffLogManager");
        Customer customer = (Customer) session.getAttribute("customer");
        CustomerLog customerLog = (CustomerLog) session.getAttribute("customerLog");
        StaffAccount staff = (StaffAccount) session.getAttribute("staff");
        StaffAccessLog staffLog = (StaffAccessLog) session.getAttribute("staffLog");

        if (customer != null && customerLog != null) {
            try {
            customerAccessLogManager.updateCustomerLogout(customer.getCustomerID(),customerLog.getLogID());
        } catch (Exception ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }   
        }
         if (staff != null && staffLog != null) {
            try {
            staffAccessLogDBManager.updateStaffAccessLogout(staff.getStaffID(), staffLog.getLogID());
        } catch (Exception ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }   
        }
        
        session.invalidate();
        response.sendRedirect("index.jsp");
    } 


}