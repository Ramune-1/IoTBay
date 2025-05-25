package model.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.CustomerLog;

public class CustomerAccessLogDBManager {
    private Connection conn;

    public CustomerAccessLogDBManager(Connection conn) throws SQLException{
        this.conn = conn;
    }

    public void addLog(String customerID, String username) throws SQLException
    {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO CustomerAccessLog VALUES (?,?,CURRENT_TIMESTAMP, null)");
        ps.setString(1, customerID);
        ps.setString(2, username);
        ps.executeUpdate();
}
    public CustomerLog findCustomerLog(String customerID) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT USERNAME, LOGINTIME, LOGOUTTIME FROM CustomerAccessLog WHERE CUSTOMERID=? ORDER BY LOGINTIME DESC LIMIT 1");
        ps.setString(1, customerID);
        ResultSet rs =ps.executeQuery();
        if (rs.next()) {
           String username = rs.getString("username");
           Timestamp loginTime = rs.getTimestamp("logintime");
           Timestamp logoutTime = rs.getTimestamp("logouttime");
           return new CustomerLog(customerID, username, loginTime, logoutTime);
        }
        return null;
    }
  
    public void updateCustomerLogout(String customerID,Timestamp loginTime) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE CustomerAccessLog SET LOGOUTTIME=CURRENT_TIMESTAMP WHERE CUSTOMERID=? AND LOGINTIME=? ");
        ps.setString(1, customerID);
        ps.setTimestamp(2, loginTime);
        ps.executeUpdate();
    }

    public ArrayList<CustomerLog> findAllLog(String customerID) throws SQLException{
        ArrayList<CustomerLog> customerLogs = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM CustomerAccessLog WHERE CUSTOMERID=? ORDER BY LOGINTIME DESC LIMIT 5");
        ps.setString(1, customerID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String username = rs.getString("username");
            Timestamp loginTime = rs.getTimestamp("logintime");
            Timestamp logoutTime =  rs.getTimestamp("logouttime");
            CustomerLog customerLog = new CustomerLog(customerID, username, loginTime, logoutTime);
            customerLogs.add(customerLog);
        }
        return customerLogs;
    }
}
