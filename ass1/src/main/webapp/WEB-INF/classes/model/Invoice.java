package model;

import java.time.LocalDate;


public class Invoice {
    private int invoiceID;
    private int cartID;
    private LocalDate orderDate;
    private double totalPrice;
    private int customerID;



    public Invoice(int invoiceID, int cartID, LocalDate orderDate, double totalPrice, int customerID) {
        this.invoiceID = invoiceID;
        this.cartID = cartID;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.customerID = customerID;
    }

    public int getInvoiceID() {
        return this.invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCartID() {
        return this.cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

 

}
