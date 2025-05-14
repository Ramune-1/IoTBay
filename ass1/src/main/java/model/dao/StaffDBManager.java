package model.dao;

import model.StaffAccount;
import java.sql.*;


public class StaffDBManager {
    private Connection conn;
    
    public StaffDBManager(Connection conn){
        this.conn = conn;
    }

    public void addStaff(String staffID, String username, String name, String gmail, String passWord, String phone, String gender ) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?,?)");
        ps.setString(1, staffID);
        ps.setString(2, username);
        ps.setString(3, name);
        ps.setString(4, gmail);
        ps.setString(5, passWord);
        ps.setString(6, phone);
        ps.setString(7, gender);
        ps.executeUpdate();
    } 

    public void removeStaff(String username) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Staff WHERE USERNAME = ? ");
        ps.setString(1, username);
        ps.executeUpdate();
    }

    public StaffAccount findStaff(String username, String password) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Staff WHERE USERNAME = ? AND PASSWORD = ?");
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String id = rs.getString("staffID");
            String name = rs.getString("name");
            String gmail = rs.getString("gmail");
            String phone = rs.getString("phone");
            String gender = rs.getString("gender");
            return new StaffAccount(id, username, name, gmail, password, phone, gender);
        }
        return null;
    }

}
