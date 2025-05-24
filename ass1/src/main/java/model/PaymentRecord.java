package model;

import java.time.LocalDate;

public class PaymentRecord {
    private int paymentID;
    private int customerID;
    private LocalDate date;
    private double amount;
    // holds an instance of payment method 
    private PaymentMethod paymentmethod;
    private Order order;

    
    



    public PaymentRecord(int paymentID, int customerID, LocalDate date, double amount,Order order) {
        this.paymentID = paymentID;
        this.customerID = customerID;
        this.date = date;
        this.amount = amount;
        this.order= order;

    }

    public int getPaymentID() {
        return this.paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getCustomer() {
        return this.customerID;
    }

    public void setCustomer(int customerID) {
        this.customerID = customerID;
    }

   

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
