package model.dao;

import model.Customer;
import java.sql.*;

public class CustomerDBManager {
    private Connection conn;

    public CustomerDBManager(Connection conn) throws SQLException{
        this.conn = conn;
    }

    public void addCustomer(String customerID, String userName, String name, String gmail, String passWord, String phone, String gender ) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?,?)");
        ps.setString(1, customerID);
        ps.setString(2, userName);
        ps.setString(3, name);
        ps.setString(4, gmail);
        ps.setString(5, passWord);
        ps.setString(6, phone);
        ps.setString(7, gender);
        ps.executeUpdate();
    } 


    public void updateCustomer(String customerID, String userName, String name, String gmail, String passWord, String phone, String gender) throws SQLException {
            PreparedStatement ps = conn.prepareStatement("UPDATE Customer SET userName = ?, name = ?, gmail = ?, passWord = ?, phone = ?, gender = ? WHERE customerID = ?");
            ps.setString(1, userName);
            ps.setString(2, name);
            ps.setString(3, gmail);
            ps.setString(4, passWord);
            ps.setString(5, phone);
            ps.setString(6, gender);
            ps.setString(7, customerID);
            ps.executeUpdate();
        }

   public Customer findCustomer(String userName, String password) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Customer WHERE userName = ? AND password = ?");
        ps.setString(1, userName);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String customerID = rs.getString("customerID");
            String name = rs.getString("name");
            String gmail = rs.getString("gmail");
            String phone = rs.getString("phone");
            String gender = rs.getString("gender");
            return new Customer(customerID, userName, name, gmail, password, phone, gender);
        }
        return null;
    }

    public int maxID() throws SQLException{
        PreparedStatement ps = conn.prepareStatement("SELECT MAX(CUSTOMERID) as  FROM Customer");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int customerID = rs.getInt(1);// get the customer id column with max
            return customerID;
        }
        return -1;
    }


    
    public boolean checkExistUsername(String userName) throws SQLException{// this is to check whether the username alreadyc yse or not
        PreparedStatement ps = conn.prepareStatement("SELECT USERNAME FROM Customer WHERE USERNAME = ?");

        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
      }
      return false;
    }

    public boolean checkExistGmail(String gmail) throws SQLException{// this is to check whether the username alreadyc yse or not
        PreparedStatement ps = conn.prepareStatement("SELECT GMAIL FROM Customer WHERE GMAIL = ?");

        ps.setString(1, gmail);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
      }
      return false;
    }

    public boolean checkExistPhone(String phone) throws SQLException{// this is to check whether the username alreadyc yse or not
        PreparedStatement ps = conn.prepareStatement("SELECT PHONE FROM Customer WHERE PHONE = ?");

        ps.setString(1, phone);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
      }
      return false;
    }

    public void updatePassword(){
        
    }

}

   


