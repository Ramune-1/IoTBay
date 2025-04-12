package model;



public class Payment {
    enum PaymentOption{
        PAYPAL, CREDITCARD, APPLEPAY;}
    private int paymentID;
    private Customer customer;
    private PaymentOption paymentOption;
    private String date;
    private double amount;



    public Payment(int paymentID, Customer customer, PaymentOption paymentOption, String date, double amount) {
        this.paymentID = paymentID;
        this.customer = customer;
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

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public PaymentOption getPaymentOption() {
        return this.paymentOption;
    }

    public void setPaymentOption(PaymentOption paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

}
