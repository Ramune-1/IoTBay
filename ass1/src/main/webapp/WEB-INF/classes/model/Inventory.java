package model;

import java.util.LinkedList;

public class Inventory {
    LinkedList<Product> productList;


    public Inventory(LinkedList<Product> productList) {
        this.productList = productList;
    }

    public LinkedList<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(LinkedList<Product> productList) {
        this.productList = productList;
    }

}   
