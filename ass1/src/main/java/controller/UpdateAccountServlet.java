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

import model.dao.CustomerDBManager;
import model.dao.StaffDBManager;

@WebServlet("/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet{
    
    @Override
    public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Validator validator = new Validator();
         String userName = request.getParameter("username");// get username input
         String name = request.getParameter("name");// get name iunput
         String gmail = request.getParameter("gmail");// get gmail input
         String passWord = request.getParameter("password");
         String phone = request.getParameter("phone");
         String gender = request.getParameter("gender");
        
         boolean existUserName = false;// variable to check whether username isvalid
         boolean existGmail = false;
         boolean existPhone = false;

           CustomerDBManager customerManager = (CustomerDBManager) session.getAttribute("customerManager");
         if (customerManager == null) throw new IOException("CustomerManager not found");// use valid find each and print different error
        Customer customer = (Customer) session.getAttribute("customer");
        StaffDBManager staffDBManager = (StaffDBManager) session.getAttribute("staffManager");
                 if (staffDBManager == null) throw new IOException("StaffManager not found");// use valid find each and print different error

        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {// check username
            if (userName.equals(customer.getUserName()) ) {
                existUserName = false;
            } else if (!userName.equals(customer.getUserName()) && (customerManager.checkExistUsername(userName) == true || staffDBManager.checkExistUsername(userName)== true)) {
                existUserName = true;
            } 
        } catch (Exception ex) {
         Logger.getLogger(UpdateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
         
        try {
            if (gmail.equals(customer.getGmail())) {
                existGmail = false;
            } else if (!gmail.equals(customer.getGmail()) && (customerManager.checkExistGmail(gmail) || staffDBManager.checkExistGmail(gmail))) {
                existGmail = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(UpdateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);         
       
        }
        try {
           if (phone.equals(customer.getPhone())) {
                existPhone = false;
            } else if (!phone.equals(customer.getPhone()) && (customerManager.checkExistPhone(phone) || staffDBManager.checkExistPhone(phone))) {
                existPhone = true;
            }
        }catch (Exception ex){
            Logger.getLogger(UpdateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     

        
        if (!validator.gmailValidate(gmail)){
            session.setAttribute("updateError", "*Gmail invalid");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (!validator.userNameValidate(userName)) {
            session.setAttribute("updateError", "*Username  invalid");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (!validator.passwordValidate(passWord)) {
            session.setAttribute("updateError", "*Password invalid");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (existUserName) {
            session.setAttribute("updateError", "*This username has been used");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (existGmail ) {
            session.setAttribute("updateError", "*This gmail has been used");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (existPhone){
             session.setAttribute("updateError", "*This phone has been used");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        }else  if (existGmail == false && existUserName == false && existPhone == false) {
            try {
                  customerManager.updateCustomer(customer.getCustomerID(), userName, name, gmail, passWord, phone, gender);
                customer = customerManager.findCustomer(userName, passWord);
            } catch (Exception ex) {
                Logger.getLogger(UpdateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
               
                
            }
           session.setAttribute("customer", customer);
            request.getRequestDispatcher("accountView.jsp").forward(request, response);
        } else{
            session.setAttribute("updateError", "*IT's error");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        }
    }
}
