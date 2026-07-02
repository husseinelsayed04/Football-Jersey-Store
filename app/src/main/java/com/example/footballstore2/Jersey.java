package com.example.footballstore2;

public class Jersey {

    private String name;
    private String price;
    private int image;
    private String description;

    public Jersey(String name, String price, int image, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }

    public String getName() {

        return name;
    }

    public String getPrice() {

        return price;
    }

    public int getImage()
    {
        return image;
    }

    public String getDescription() {
        return description;
    }
}
