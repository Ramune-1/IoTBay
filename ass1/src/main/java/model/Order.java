package model;

import java.sql.Time;
import java.sql.Timestamp;


public class Order {
    private int orderID;
    private String customerID;
    private int paymentID;
    private int deliveryID;
    private double totalPrice;
    private Timestamp orderDate;
    
    public Order(int orderID, String customerID, int paymentID, int deliveryID, double totalPrice, Timestamp orderDate) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.paymentID = paymentID;
        this.deliveryID = deliveryID;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return this.orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public String getCustomerID() {
        return this.customerID;
    }
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    public int getPaymentID() {
        return this.paymentID;
    }
    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }
    public int getDeliveryID() {
        return this.deliveryID;
    }
    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }
    public double getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Timestamp getOrderDate() {
        return this.orderDate;
    }
    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
