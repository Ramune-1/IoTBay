package model;

import java.sql.Time;


public class Order {
    private int orderID;
    private int customerID;
    private int paymentID;
    private int deliveryID;
    private double totalPrice;
    private Time orderDate;
    
    public Order(int orderID, int customerID, int paymentID, int deliveryID, double totalPrice, Time orderDate) {
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
    public int getCustomerID() {
        return this.customerID;
    }
    public void setCustomerID(int customerID) {
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
    public Time getOrderDate() {
        return this.orderDate;
    }
    public void setOrderDate(Time orderDate) {
        this.orderDate = orderDate;
    }
}
