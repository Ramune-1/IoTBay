package model.dao;

import model.StaffAccount;
import java.sql.*;


public class StaffDBManager {
    private Connection conn;
    
    public StaffDBManager(Connection conn){
        this.conn = conn;
    }

    public void addStaff(String staffID, String username, String name, String gmail, String passWord, String phone, String gender ) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Staff VALUES (?,?,?,?,?,?,?)");
        ps.setString(1, staffID);
        ps.setString(2, username);
        ps.setString(3, name);
        ps.setString(4, gmail);
        ps.setString(5, passWord);
        ps.setString(6, phone);
        ps.setString(7, gender);
        ps.executeUpdate();
    } 
    
    public void updateStaff(String staffID, String userName, String name, String gmail, String passWord, String phone, String gender) throws SQLException {
            PreparedStatement ps = conn.prepareStatement("UPDATE Staff SET userName = ?, name = ?, gmail = ?, passWord = ?, phone = ?, gender = ? WHERE STAFFID = ?");
            ps.setString(1, userName);
            ps.setString(2, name);
            ps.setString(3, gmail);
            ps.setString(4, passWord);
            ps.setString(5, phone);
            ps.setString(6, gender);
            ps.setString(7, staffID);
            ps.executeUpdate();
        }

    public void removeStaff(String staffID) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("DELETE FROM Staff WHERE STAFFID = ? ");
        ps.setString(1, staffID);
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
     public boolean checkExistUsername(String userName) throws SQLException{// this is to check whether the username alreadyc yse or not
        PreparedStatement ps = conn.prepareStatement("SELECT USERNAME FROM Staff WHERE USERNAME = ?");

        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
      }
      return false;
    }

    public boolean checkExistGmail(String gmail) throws SQLException{// this is to check whether the username alreadyc yse or not
        PreparedStatement ps = conn.prepareStatement("SELECT GMAIL FROM Staff WHERE GMAIL = ?");

        ps.setString(1, gmail);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
      }
      return false;
    }

    public boolean checkExistPhone(String phone) throws SQLException{// this is to check whether the username alreadyc yse or not
        PreparedStatement ps = conn.prepareStatement("SELECT PHONE FROM Staff WHERE PHONE = ?");

        ps.setString(1, phone);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
      }
      return false;
    }

}
