package model;

import java.time.LocalDate;

public class Payment {
    enum PaymentOption{
        PAYPAL, CREDITCARD, APPLEPAY;}
    private int paymentID;
    private int customerID;
    private PaymentOption paymentOption;
    private LocalDate date;
    private double amount;



    public Payment(int paymentID, int customerID, PaymentOption paymentOption, LocalDate date, double amount) {
        this.paymentID = paymentID;
        this.customerID = customerID;
        this.paymentOption = paymentOption;
        this.date = date;
        this.amount = amount;
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

    public PaymentOption getPaymentOption() {
        return this.paymentOption;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
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
