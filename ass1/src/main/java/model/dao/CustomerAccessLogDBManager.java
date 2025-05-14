package model.dao;


import java.sql.*;
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
}
