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
import model.dao.CustomerAccessLogDBManager;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet{
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        CustomerAccessLogDBManager customerAccessLogManager = (CustomerAccessLogDBManager) session.getAttribute("customerAccessLogManager");
        Customer customer = (Customer) session.getAttribute("customer");
        CustomerLog customerLog = (CustomerLog) session.getAttribute("customerLog");
        try {
            customerAccessLogManager.updateCustomerLogout(customer.getUserName(),customerLog.getLoginTime());
        } catch (Exception ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }   
        session.invalidate();
        response.sendRedirect("index.jsp");
    }


}