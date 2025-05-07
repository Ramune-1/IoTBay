package model;


public class Cart {
    private int cartID;
    private int customerID;
    private int paymentID;
    private int deliveryID;
    private double totalPrice;



    public Cart(int cartID, int customerID, int paymentID, int deliveryID, double totalPrice) {
        this.cartID = cartID;
        this.customerID = customerID;
        this.paymentID = paymentID;
        this.deliveryID = deliveryID;
        this.totalPrice = totalPrice;
    }


    public int getCartID() {
        return this.cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
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
  
  
 
}
