package model;

import java.time.LocalDate;

public class Delivery {
    enum Status{
        PENDING, SHIPPED, DElIVERY;
    }
    private int deliveryID;
    private int customerID;
    private String address;
    private int cartID;
    private Status status;
    private LocalDate date;


    public Delivery() {
    }

    public Delivery(int deliveryID, int customerID, String address, int cartID, Status status, LocalDate date) {
        this.deliveryID = deliveryID;
        this.customerID = customerID;
        this.address = address;
        this.cartID = cartID;
        this.status = status;
        this.date = date;
    }

    public int getDeliveryID() {
        return this.deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCartID() {
        return this.cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

  

}
