package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CustomerLog;
import model.StaffAccessLog;
import model.StaffAccount;
import model.Customer;
import model.dao.CustomerAccessLogDBManager;
import model.dao.StaffAccessLogDBManager;;


@WebServlet("/AccountViewServlet")
public class AccountViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        List<CustomerLog> customerLogs = new ArrayList<>();
        List<StaffAccessLog> staffLogs = new ArrayList<>();
        CustomerAccessLogDBManager customerAccessLogDBManager = (CustomerAccessLogDBManager) session.getAttribute("customerAccessLogManager");
        StaffAccessLogDBManager staffAccessLogDBManager = (StaffAccessLogDBManager) session.getAttribute("staffLogManager");
        Customer customer = (Customer) session.getAttribute("customer");
        StaffAccount staff = (StaffAccount) session.getAttribute("staff");
        if (customer == null && staff == null ) {
        response.sendRedirect("login.jsp");
        return;
    }

    if (customer != null) {
        try {
            customerLogs = customerAccessLogDBManager.findAllLog(customer.getCustomerID());
        } catch (Exception e) {
         e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        List<String[]> logHistory = new ArrayList<>();
        for (CustomerLog log : customerLogs){
            String loginTime = (log.getLoginTime() != null) ? sdf.format(log.getLoginTime()) : "-";
            String logoutTime = (log.getLogoutTime() != null) ? sdf.format(log.getLogoutTime()) : "-";
            logHistory.add(new String[]{loginTime,logoutTime});
        }
           session.setAttribute("historyLog", logHistory);
            request.getRequestDispatcher("accountView.jsp").include(request, response); 
    }

    if (staff != null) {
        try {
            staffLogs = staffAccessLogDBManager.findAllLog(staff.getStaffID());
        } catch (Exception e) {
         e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Australia/Sydney"));
        List<String[]> logHistory = new ArrayList<>();
        for (StaffAccessLog log : staffLogs){
            String loginTime = (log.getLoginTime() != null) ? sdf.format(log.getLoginTime()) : "-";
            String logoutTime = (log.getLogoutTime() != null) ? sdf.format(log.getLogoutTime()) : "-";
            logHistory.add(new String[]{loginTime,logoutTime});
        }
           session.setAttribute("historyLog", logHistory);
            request.getRequestDispatcher("staffAccountView.jsp").include(request, response); 
    }

    }

        
}
