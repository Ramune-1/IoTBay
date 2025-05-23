package model.dao;

import model.Order;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class OrderDBManager {
    private Connection conn;

    public OrderDBManager(Connection conn) throws SQLException{
        this.conn = conn;
    }

    public int addOrder(String customerID, int paymentID, int deliveryID, double totalPrice, Timestamp orderDate) throws SQLException{
        String query = "INSERT INTO `Orders` (customerID, totalPrice, orderDate) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, customerID);
        // stmt.setInt(2, paymentID);
        // stmt.setInt(3, deliveryID);
        stmt.setDouble(2, totalPrice);
        stmt.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderDate));
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
                rs.getTimestamp("orderDate")
            ));
        }
        return orders;
    }

    public List<Order> searchOrders(String customerID, String orderNumber, String date) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM Orders WHERE customerID = ?");
        List<Object> parameters = new ArrayList<>();
        parameters.add(customerID);

        if (orderNumber != null && !orderNumber.trim().isEmpty()) {
        query.append(" AND orderID = ?");
        parameters.add(Integer.parseInt(orderNumber.trim()));
        }

        if (date != null && !date.trim().isEmpty()) {
        query.append(" AND DATE(orderDate) = ?");
        parameters.add(date.trim());  // Expected format: "yyyy-MM-dd"
        }

        PreparedStatement ps = conn.prepareStatement(query.toString());

        for (int i = 0; i < parameters.size(); i++) {
            ps.setObject(i + 1, parameters.get(i));
        }

        ResultSet rs = ps.executeQuery();
        List<Order> orders = new ArrayList<>();
        while (rs.next()) {
            orders.add(new Order(
                rs.getInt("orderID"),
                rs.getString("customerID"),
                rs.getInt("paymentID"),
                rs.getInt("deliveryID"),
                rs.getDouble("totalPrice"),
                rs.getTimestamp("orderDate")
            ));
        }
        return orders;

        
    
}

}
