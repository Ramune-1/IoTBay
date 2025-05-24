package controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CustomerLog;
import model.Customer;
import model.dao.CustomerAccessLogDBManager;;


@WebServlet("/AccountViewServlet")
public class AccountViewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        List<CustomerLog> customerLogs = new ArrayList<>();
        CustomerAccessLogDBManager customerAccessLogDBManager = (CustomerAccessLogDBManager) session.getAttribute("customerAccessLogManager");
        Customer customer = (Customer) session.getAttribute("customer");

        try {
            customerLogs = customerAccessLogDBManager.findAllLog(customer.getUserName());
        } catch (Exception e) {
         e.printStackTrace();
        }
       
           session.setAttribute("customerLogs", customerLogs);
            request.getRequestDispatcher("accountView.jsp").include(request, response); 
        
        
    }
}
