package model.dao;

import model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDBManager {
    private Connection conn;

    public OrderDBManager(Connection conn) throws SQLException{
        this.conn = conn;
    }

    public int addOrder(String customerID, int paymentID, int deliveryID, double totalPrice, Time orderDate) throws SQLException{
        String query = "INSERT INTO `Order` (customerID, paymentID, deliveryID, totalPrice, orderDate) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, customerID);
        stmt.setInt(2, paymentID);
        stmt.setInt(3, deliveryID);
        stmt.setDouble(4, totalPrice);
        stmt.setTime(5, orderDate);
        stmt.executeUpdate();
        ResultSet rs = stmt.getGeneratedKeys();

        if (rs.next()) {
        return rs.getInt(1); // Return generated orderID
        } else {
        throw new SQLException("Creating order failed, no ID obtained.");
        }
    } 

    public void updateOrder(int orderID, int customerID, int paymentID, int deliveryID, double totalPrice, Date orderDate) throws SQLException {
            PreparedStatement ps = conn.prepareStatement("UPDATE Orders SET customerID = ?, paymentID = ?, deliveryID = ?, totalPrice = ?, orderDate = ? WHERE orderID = ?");
            ps.setInt(1, customerID);
            ps.setInt(2, paymentID);
            ps.setInt(3, deliveryID);
            ps.setDouble(4, totalPrice);
            ps.setDate(5, orderDate);
            ps.setInt(6, orderID);
            ps.executeUpdate();
        }

    public List<Order> getOrdersByCustomerID(String customerID) throws SQLException {
        String query = "SELECT * FROM Orders WHERE customerID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, customerID);
        ResultSet rs = ps.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            orders.add(new Order(
                rs.getInt("orderID"),
                rs.getString("customerID"),
                rs.getInt("paymentID"),
                rs.getInt("deliveryID"),
                rs.getDouble("totalPrice"),
                rs.getTime("orderDate")
            ));
        }
        return orders;
    }

    public List<Order> searchOrders(String customerID, String orderNumber, String date) throws SQLException {
        String query = "SELECT * FROM Orders WHERE customerID = ? AND orderID LIKE ? AND orderDate LIKE ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, customerID);
        ps.setString(2, "%" + orderNumber + "%");
        ps.setString(3, "%" + date + "%");
        ResultSet rs = ps.executeQuery();

        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            orders.add(new Order(
                rs.getInt("orderID"),
                rs.getString("customerID"),
                rs.getInt("paymentID"),
                rs.getInt("deliveryID"),
                rs.getDouble("totalPrice"),
                rs.getTime("orderDate")
            ));
        }
        return orders;
    }
    
}
