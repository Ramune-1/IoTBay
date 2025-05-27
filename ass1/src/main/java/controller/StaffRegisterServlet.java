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

import model.StaffAccount;
import model.dao.CustomerDBManager;
import model.dao.StaffDBManager;

@WebServlet("/StaffRegisterServlet")
public class StaffRegisterServlet extends HttpServlet{
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(); // get session
        Validator validator = new Validator();// use validator to check whether login is valid
         String staffID = UUID.randomUUID().toString();// get id
         String userName = request.getParameter("username");// get username input
         String name = request.getParameter("name");// get name iunput
         String gmail = request.getParameter("gmail");// get gmail input
         String passWord = request.getParameter("password");
         String phone = request.getParameter("phone");
         String gender = request.getParameter("gender");
        
         boolean existUserName = false;// variable to check whether username isvalid
         boolean existGmail = false;
         boolean existPhone = false;
        CustomerDBManager customerDBManager = (CustomerDBManager) session.getAttribute("customerManager");
         StaffDBManager staffDBManager = (StaffDBManager) session.getAttribute("staffManager");
         if (staffDBManager == null) throw new IOException("StaffManager not found");// use valid find each and print different error
        StaffAccount staff = null;
        


        try {// check username
          existUserName = staffDBManager.checkExistUsername(userName);
          if (!existUserName) {
            existUserName = customerDBManager.checkExistUsername(userName);
          }
        } catch (Exception ex) {
         Logger.getLogger(StaffRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);

        }
         
        try {
            existGmail = staffDBManager.checkExistGmail(gmail);
            if (!existGmail) {
            existGmail = customerDBManager.checkExistGmail(userName);
          }
        } catch (Exception ex) {
            Logger.getLogger(StaffRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);         
       
        }
        try {
            existPhone = staffDBManager.checkExistPhone(phone);
            if (!existPhone) {
            existPhone = customerDBManager.checkExistPhone(phone);
          }
        }catch (Exception ex){
            Logger.getLogger(StaffRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (existGmail == false && existUserName == false && existPhone == false) {
            try {
                staffDBManager.addStaff(staffID, userName, name, gmail, passWord, phone, gender);
                staff = staffDBManager.findStaff(userName, passWord);
            } catch (Exception ex) {
                Logger.getLogger(StaffRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
               
                
            }
        }
         if (existUserName) {
            session.setAttribute("staffErrorMsg", "*This user name exist");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (existGmail) {
            session.setAttribute("staffErrorMsg", "*This gmail exist");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (!validator.gmailValidate(gmail)){
            session.setAttribute("staffErrorMsg", "*Gmail invalid");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (!validator.userNameValidate(userName)) {
            session.setAttribute("staffErrorMsg", "*Username invalid");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (!validator.passwordValidate(passWord)) {
            session.setAttribute("staffErrorMsg", "*Password invalid");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        } else if (staff != null){
            request.getRequestDispatcher("login.jsp").include(request, response);
        } else if (staff == null){
            session.setAttribute("staffErrorMsg", "*IT's error");
            request.getRequestDispatcher("staffRegister.jsp").include(request, response);
        }

    }
}