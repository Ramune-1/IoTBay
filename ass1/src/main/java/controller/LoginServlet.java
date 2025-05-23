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
import model.dao.CustomerAccessLogDBManager;
import model.dao.CustomerDBManager;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();// first we have to get the first session
        Validator validator = new Validator();
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        CustomerDBManager customerManager = (CustomerDBManager) session.getAttribute("customerManager");// manager is from ConnServlet
        CustomerAccessLogDBManager customerAccessLogManager = (CustomerAccessLogDBManager) session.getAttribute("customerAccessLogManager");
        if (customerManager == null) throw new IOException("Manager not found");
        Customer customer = null;// create customer instance
        try {
            customer = customerManager.findCustomer(userName, passWord); // use function to check exist
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!validator.userNameValidate(userName)) {
            session.setAttribute("errorMsg", "Your user name imput not valid");//RegEX
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (!validator.passwordValidate(passWord)) {
            session.setAttribute("errorMsg", "Your password input not valid");//RegEX
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (customer != null){
            try {
               customerAccessLogManager.addLog(customer.getCustomerID(), userName);
            } catch (Exception ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);            }
            session.setAttribute("customer", customer);// if customer find set session for customer
            request.getRequestDispatcher("welcome.jsp").include(request, response);
        }else {
            session.setAttribute("errorMsg", "You 've entered the wrong credentials, try again");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
