package controller;


import org.junit.jupiter.api.Test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Customer;
import model.dao.CustomerDBManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;


public class RegisterServletTest {
    private RegisterServlet servlet;
    private HttpServletResponse response;
    private HttpServletRequest request;
    private HttpSession session;
    private CustomerDBManager customerManager;
    private RequestDispatcher dispatcher;
    
    
    
    @BeforeEach
    public void setUp(){
        servlet = new RegisterServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        customerManager = mock(CustomerDBManager.class);
        dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);

    }

    @Test
    public void successRegistrationTest() throws ServletException, IOException, SQLException{
        when(request.getParameter("username")).thenReturn("gaobac");
        when(request.getParameter("name")).thenReturn("Sieu Nhan Gao");
        when(request.getParameter("gmail")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("Test1234");
        when(request.getParameter("phone")).thenReturn("1234567890");
        when(request.getParameter("gender")).thenReturn("Male");

        when(session.getAttribute("customerManager")).thenReturn(customerManager);
         when(customerManager.checkExistUsername("gaobac")).thenReturn(false);
        when(customerManager.checkExistGmail("test@example.com")).thenReturn(false);
        when(customerManager.checkExistPhone("1234567890")).thenReturn(false);

        Customer customer = mock(Customer.class); // or a mock
        when(customerManager.findCustomer("gaobac", "Test1234")).thenReturn(customer);

        when(request.getRequestDispatcher("welcome.jsp")).thenReturn(dispatcher);

        servlet.doPost(request, response);

        verify(session).setAttribute("customer", customer);
        verify(dispatcher).include(request, response);
    }

    @Test
public void testDoPost_UsernameExists() throws Exception {
    when(request.getParameter("username")).thenReturn("existingUser");
    when(request.getParameter("name")).thenReturn("Test User");
    when(request.getParameter("gmail")).thenReturn("test@example.com");
    when(request.getParameter("password")).thenReturn("Test1234");
    when(request.getParameter("phone")).thenReturn("1234567890");
    when(request.getParameter("gender")).thenReturn("Male");

    when(session.getAttribute("customerManager")).thenReturn(customerManager);
    when(customerManager.checkExistUsername("existingUser")).thenReturn(true); // Username exists

    when(request.getRequestDispatcher("register.jsp")).thenReturn(dispatcher);

    servlet.doPost(request, response);

    verify(session).setAttribute("errorMsg", "This user name exist");
    verify(dispatcher).include(request, response);
}
}
   