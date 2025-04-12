package model;

public class Invoice {
    private int invoiceID;
    private Cart cart;
    private String orderDate;
    private double totalPrice;
    private Customer customer;

    public Invoice(int invoiceID, Cart cart, String orderDate, double totalPrice, Customer customer) {
        this.invoiceID = invoiceID;
        this.cart = cart;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.customer = customer;
    }

    public int getInvoiceID() {
        return this.invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
