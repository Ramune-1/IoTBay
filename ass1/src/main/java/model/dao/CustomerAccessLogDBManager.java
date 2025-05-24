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
    public boolean findCustomerLog(String username) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT USERNAME FROM CustomerAccessLog WHERE USERNAME=?");
        ps.setString(1, username);
        ResultSet rs =ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }
    public void updateCustomerLogin(String username) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE CustomerAccessLog SET LOGINTIME=CURRENT_TIMESTAMP");
        ps.executeUpdate();
    }
    public void updateCustomerLogout(String username) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("UPDATE CustomerAccessLog SET LOGOUTIME=CURRENT_TIMESTAMP");
        ps.executeUpdate();
    }

    public ArrayList<CustomerLog> findAllLog(String username) throws SQLException{
        ArrayList<CustomerLog> customerLogs = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM CustomerAccessLog WHERE USERNAME=? ORDER BY LOGINTIME DESC");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String customerID = rs.getString("customerID");
            Timestamp loginTime = rs.getTimestamp("logintime");
            Timestamp logoutTime =  rs.getTimestamp("logoutime");
            CustomerLog customerLog = new CustomerLog(customerID, username, loginTime, logoutTime);
            customerLogs.add(customerLog);
        }
        return customerLogs;
    }
}
