package model;
import java.util.Objects;

public class Cart {
    private int cartID;
    private Customer customer;
    private Payment payment;
    private Delivery delivery;
    private double totalPrice;



    public Cart() {
    }

    public Cart(int cartID, Customer customer, Payment payment, Delivery delivery, double totalPrice) {
        this.cartID = cartID;
        this.customer = customer;
        this.payment = payment;
        this.delivery = delivery;
        this.totalPrice = totalPrice;
    }


    public int getCartID() {
        return this.cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Delivery getDelivery() {
        return this.delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

  
 
}
