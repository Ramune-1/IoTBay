package controller;

 

   import java.io.IOException;

   import java.sql.Connection;

   import java.sql.SQLException;

   import java.util.logging.Level;

   import java.util.logging.Logger;
   
   import jakarta.servlet.annotation.WebServlet;

   import jakarta.servlet.ServletException;

   import jakarta.servlet.http.HttpServlet;

   import jakarta.servlet.http.HttpServletRequest;

   import jakarta.servlet.http.HttpServletResponse;

   import jakarta.servlet.http.HttpSession;

import model.dao.*;

 
    @WebServlet("/ConnServlet")
   public class ConnServlet extends HttpServlet {// connector pool 

 

       private DBConnector db;

       

       private Connection conn;

        

       @Override //Create and instance of DBConnector for the deployment session

       public void init() {

           try {

               db = new DBConnector();

           } catch (ClassNotFoundException | SQLException ex) {

               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

           }      

       }

      

       @Override //Add the DBConnector, DBManager, Connection instances to the session

       protected void doGet(HttpServletRequest request, HttpServletResponse response)

               throws ServletException, IOException {

           response.setContentType("text/html;charset=UTF-8");       

           HttpSession session = request.getSession();

           conn = db.openConnection();       

           try {

              conn = db.openConnection();
              CustomerDBManager customerManager = new CustomerDBManager(conn);
              CustomerAccessLogDBManager customerAccessLogDBManager = new CustomerAccessLogDBManager(conn);
              OrderDBManager orderManager = new OrderDBManager(conn);
                ProductDBManager productManager = new ProductDBManager(conn);
                StaffDBManager staffManager = new StaffDBManager(conn);
                StaffAccessLogDBManager staffAccessLogDBManager = new StaffAccessLogDBManager(conn);
              session.setAttribute("customerManager", customerManager);
              session.setAttribute("customerAccessLogManager", customerAccessLogDBManager);
              session.setAttribute("orderManager", orderManager);
              session.setAttribute("productManager", productManager);
              session.setAttribute("staffManager", staffManager);
              session.setAttribute("staffLogManager", staffAccessLogDBManager);
           } catch (SQLException ex) {

               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

           }

           //export the DB manager to the view-session (JSPs)

           
           System.out.println("Manager stored in session.");     

       }   

        

       @Override //Destroy the servlet and release the resources of the application (terminate also the db connection)

        public void destroy() {

           try {

               db.closeConnection();

           } catch (SQLException ex) {

               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

           }

       }

   }