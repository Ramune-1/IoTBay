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
import model.StaffAccount;
import model.dao.StaffDBManager;
import model.dao.CustomerDBManager;

@WebServlet("/UpdateStaffServlet")
public class UpdateStaffServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Validator validator = new Validator();

        String userName = request.getParameter("username");
        String name = request.getParameter("name");
        String gmail = request.getParameter("gmail");
        String passWord = request.getParameter("password");
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");

        boolean existUserName = false;
        boolean existGmail = false;
        boolean existPhone = false;

        StaffDBManager staffManager = (StaffDBManager) session.getAttribute("staffManager");
        if (staffManager == null) throw new IOException("StaffManager not found");
        StaffAccount staff = (StaffAccount) session.getAttribute("staff");
        CustomerDBManager customerManager = (CustomerDBManager) session.getAttribute("customerManager");
        if (customerManager == null) throw new IOException("CustomerManager not found");

        try {
            if (userName.equals(staff.getUserName())) {
                existUserName = false;
            } else if (userName != null && !userName.equals(staff.getUserName()) && (staffManager.checkExistUsername(userName) || customerManager.checkExistUsername(userName))) {
                existUserName = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(UpdateStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (gmail.equals(staff.getGmail())) {
                existGmail = false;
            } else if (gmail != null &&!gmail.equals(staff.getGmail()) && (staffManager.checkExistGmail(gmail) || customerManager.checkExistGmail(gmail))) {
                existGmail = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(UpdateStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (phone.equals(staff.getPhone())) {
                existPhone = false;
            } else if (phone != null && !phone.equals(staff.getPhone()) && (staffManager.checkExistPhone(phone) || customerManager.checkExistPhone(phone))) {
                existPhone = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(UpdateStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!validator.gmailValidate(gmail)) {
            session.setAttribute("updateError", "*Gmail invalid");
            request.getRequestDispatcher("updateStaff.jsp").include(request, response);
        } else if (!validator.userNameValidate(userName)) {
            session.setAttribute("updateError", "*Username invalid");
            request.getRequestDispatcher("updateStaff.jsp").include(request, response);
        } else if (!validator.passwordValidate(passWord)) {
            session.setAttribute("updateError", "*Password invalid");
            request.getRequestDispatcher("updateStaff.jsp").include(request, response);
        } else if (existUserName) {
            session.setAttribute("updateError", "*This username has been used");
            request.getRequestDispatcher("updateStaff.jsp").include(request, response);
        } else if (existGmail) {
            session.setAttribute("updateError", "*This gmail has been used");
            request.getRequestDispatcher("updateStaff.jsp").include(request, response);
        } else if (existPhone) {
            session.setAttribute("updateError", "*This phone has been used");
            request.getRequestDispatcher("updateStaff.jsp").include(request, response);
        } else if (!existGmail && !existUserName && !existPhone) {
            try {
                staffManager.updateStaff(staff.getStaffID(), userName, name, gmail, passWord, phone, gender);
                staff = staffManager.findStaff(userName, passWord);
            } catch (Exception ex) {
                Logger.getLogger(UpdateStaffServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("staff", staff);
            request.getRequestDispatcher("staffAccountView.jsp").forward(request, response);
        } else {
            session.setAttribute("updateError", "*It's error");
            request.getRequestDispatcher("updateStaff.jsp").include(request, response);
        }
    }
}
