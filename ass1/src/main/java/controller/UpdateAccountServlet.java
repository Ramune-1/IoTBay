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
         if (customerManager == null) throw new IOException("Manager not found");// use valid find each and print different error
        Customer customer = (Customer) session.getAttribute("customer");
        


        try {// check username
            if (userName.equals(customer.getUserName())) {
                existUserName = false;
            } else if (!userName.equals(customer.getUserName()) && customerManager.checkExistUsername(userName) == false) {
                existUserName = true;
            }
        } catch (Exception ex) {
         Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
         
        try {
            if (gmail.equals(customer.getGmail())) {
                existGmail = false;
            } else if (!gmail.equals(customer.getGmail()) && customerManager.checkExistGmail(gmail) == false) {
                existGmail = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);         
       
        }
        try {
           if (phone.equals(customer.getPhone())) {
                existPhone = false;
            } else if (!phone.equals(customer.getPhone()) && customerManager.checkExistPhone(phone) == false) {
                existPhone = true;
            }
        }catch (Exception ex){
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (existGmail == false && existUserName == false && existPhone == false) {
            try {
                  customerManager.updateCustomer(customer.getCustomerID(), userName, name, gmail, passWord, phone, gender);
                customer = customerManager.findCustomer(userName, passWord);
            } catch (Exception ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
               
                
            }
        }
      

        if (existUserName) {
            session.setAttribute("updateError", "This user name exist");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (existGmail) {
            session.setAttribute("updateError", "This gmail exist");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (!validator.gmailValidate(gmail)){
            session.setAttribute("updateError", "gmail in valid");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (!validator.userNameValidate(userName)) {
            session.setAttribute("updateError", "username  in valid");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else if (!validator.passwordValidate(passWord)) {
            session.setAttribute("updateError", "password in valid");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        } else  if (existGmail == false && existUserName == false && existPhone == false) {
           session.setAttribute("customer", customer);
            request.getRequestDispatcher("accountView.jsp").forward(request, response);
        } else{
            session.setAttribute("updateError", "IT's error");
            request.getRequestDispatcher("updateAccount.jsp").include(request, response);
        }
    }
}
