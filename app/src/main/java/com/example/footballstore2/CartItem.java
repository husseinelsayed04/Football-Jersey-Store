package com.example.footballstore2;

public class CartItem {

    String name;
    double price;
    int image;
    String size;
    int quantity;

    public CartItem(String name, double price, int image, String size) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.size = size;
        this.quantity = 1;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getImage() {
        return image;
    }
    public String getSize() {
        return size;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
