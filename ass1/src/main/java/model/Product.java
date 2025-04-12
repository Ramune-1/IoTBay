package model;

public class Product {
    private int productID;
    private String name;
    private int quantity;
    private double price;
    private String description;

    public Product(int productID, String name, int quantity, double price, String description) {
        this.productID = productID;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public int getProductID() {
        return this.productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
