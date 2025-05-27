package controller;

import controller.utility.Validator;
import java.io.IOException;

import java.sql.SQLException;
import java.util.UUID;
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
import model.dao.CustomerAccessLogDBManager;
import model.dao.CustomerDBManager;
import model.dao.StaffAccessLogDBManager;
import model.dao.StaffDBManager;
import model.StaffAccount;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();// first we have to get the first session
        Validator validator = new Validator();
        String logID = UUID.randomUUID().toString();
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        CustomerDBManager customerManager = (CustomerDBManager) session.getAttribute("customerManager");// manager is from ConnServlet
        CustomerAccessLogDBManager customerAccessLogManager = (CustomerAccessLogDBManager) session.getAttribute("customerAccessLogManager");
        StaffDBManager staffDBManager = (StaffDBManager) session.getAttribute("staffManager");
        StaffAccessLogDBManager staffAccessLogManager = (StaffAccessLogDBManager) session.getAttribute("staffLogManager");
        if (customerManager == null) throw new IOException("Customer Manager not found");
        if (staffDBManager == null) throw new IOException("Staff Manager not found");
        if (customerAccessLogManager == null) throw new IOException("Manager not found");
        Customer customer = null;// create customer instance
        CustomerLog customerLog = null;
        StaffAccount staffAccount = null;
        StaffAccessLog staffAccessLog = null;
        try {
            customer = customerManager.findCustomer(userName, passWord); // use function to check exist
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            staffAccount = staffDBManager.findStaff(userName, passWord);
        } catch (Exception e) { 
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, e);

        }
        if (!validator.userNameValidate(userName)) {
            session.setAttribute("errorMsg", "*Your user name imput not valid");//RegEX
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.passwordValidate(passWord)) {
            session.setAttribute("errorMsg", "*Your password input not valid");//RegEX
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (customer != null){
            try {
               customerAccessLogManager.addLog(logID, customer.getCustomerID(), userName);
               customerLog = customerAccessLogManager.findCustomerLog(customer.getCustomerID());
            } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex); 
                }
            session.setAttribute("customerLog", customerLog);
            session.setAttribute("customer", customer);// if customer find set session for customer
            //request.getRequestDispatcher("ProductServlet").include(request, response);
            response.sendRedirect("ProductServlet");
        }else if(staffAccount != null){
            try {
                staffAccessLogManager.addLog(logID, staffAccount.getStaffID(), userName);
                staffAccessLog = staffAccessLogManager.findStaffAccessLog(staffAccount.getStaffID());
            } catch (Exception e) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, e); 
            }
            session.setAttribute("staffLog", staffAccessLog);
            session.setAttribute("staff", staffAccount);
            request.getRequestDispatcher("staffWelcome.jsp").include(request, response);
        }else {
            session.setAttribute("errorMsg", "*Password or username incorrect");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
