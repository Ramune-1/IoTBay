package model;

import java.util.ArrayList;
import java.util.List;


public class Cart {
    private int cartID;
    private int customerID;
    private int paymentID;
    private int deliveryID;
    private double totalPrice;
    private List<CartItem> items = new ArrayList<>();
    
    public Cart() {
        this.items = new ArrayList<>();
    }



    public Cart(int cartID, int customerID, int paymentID, int deliveryID, double totalPrice) { 
        this.cartID = cartID;
        this.customerID = customerID;
        this.paymentID = paymentID;
        this.deliveryID = deliveryID;
        this.totalPrice = totalPrice;
        this.items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        for (CartItem ci : items) {
            if (ci.getProduct().getProductID() == item.getProduct().getProductID()) {
                ci.setQuantity(ci.getQuantity() + item.getQuantity());
                return;
            }
        }
        items.add(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void removeItem(int productID) {
        items.removeIf(item -> item.getProduct().getProductID() == productID);
    }
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }
    public void clear() {
        items.clear();
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


    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
  
  
 
}
