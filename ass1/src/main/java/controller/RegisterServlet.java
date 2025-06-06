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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet{
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(); // get session
        Validator validator = new Validator();// use validator to check whether login is valid
         String customerID = UUID.randomUUID().toString();// get id
         String userName = request.getParameter("username");// get username input
         String name = request.getParameter("name");// get name iunput
         String gmail = request.getParameter("gmail");// get gmail input
         String passWord = request.getParameter("password");
         String phone = request.getParameter("phone");
         String gender = request.getParameter("gender");
        
         boolean existUserName = false;// variable to check whether username isvalid
         boolean existGmail = false;
         boolean existPhone = false;
          StaffDBManager staffDBManager = (StaffDBManager) session.getAttribute("staffManager");
         CustomerDBManager customerManager = (CustomerDBManager) session.getAttribute("customerManager");
         if (customerManager == null) throw new IOException("Manager not found");// use valid find each and print different error
        Customer customer = null;
        


        try {// check username
          existUserName = customerManager.checkExistUsername(userName);
            if (!existUserName) {
                existUserName = staffDBManager.checkExistUsername(userName);
            }
        } catch (Exception ex) {
         Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
         
        try {
            existGmail = customerManager.checkExistGmail(gmail);
            if (!existGmail) {
                existGmail = staffDBManager.checkExistGmail(gmail);
            }
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);         
       
        }
        try {
            existPhone = customerManager.checkExistPhone(phone);
            if (!existPhone) {
                existPhone = staffDBManager.checkExistPhone(phone);
            }
        }catch (Exception ex){
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (existGmail == false && existUserName == false && existPhone == false) {
            try {
                customerManager.addCustomer(customerID,userName,name,gmail,passWord,phone,gender);
                customer = customerManager.findCustomer(userName, passWord);
            } catch (Exception ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
               
                
            }
        }

        if (existUserName) {
            session.setAttribute("registerErrorMsg", "*This user name exist");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (existGmail) {
            session.setAttribute("registerErrorMsg", "*This gmail exist");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.gmailValidate(gmail)){
            session.setAttribute("registerErrorMsg", "*Gmail invalid");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.userNameValidate(userName)) {
            session.setAttribute("registerErrorMsg", "*Username invalid");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (!validator.passwordValidate(passWord)) {
            session.setAttribute("registerErrorMsg", "*Password invalid");
            request.getRequestDispatcher("register.jsp").include(request, response);
        } else if (customer != null){
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (customer == null){
            session.setAttribute("registerErrorMsg", "*IT's error");
            request.getRequestDispatcher("register.jsp").include(request, response);
        }

     }
}
