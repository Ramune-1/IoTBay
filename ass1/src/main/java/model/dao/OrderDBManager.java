package model.dao;

import model.Order;
import java.sql.*;

public class OrderDBManager {
    private Connection conn;

    public OrderDBManager(Connection conn) throws SQLException{
        this.conn = conn;
    }

    public boolean addOrder(Order order) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Orders VALUES (?,?,?,?,?,?)");
        ps.setInt(1, order.getCustomerID());
        ps.setInt(2, order.getPaymentID());
        ps.setInt(3, order.getDeliveryID());
        ps.setDouble(4, order.getTotalPrice());
        ps.setTime(5, order.getOrderDate());
        
        
        return ps.executeUpdate() > 0;
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
    
}
