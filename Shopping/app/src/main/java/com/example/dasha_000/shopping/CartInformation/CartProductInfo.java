package com.example.dasha_000.shopping.CartInformation;

/**
 * Created by dasha_000 on 30.05.2018.
 */

public class CartProductInfo {
    private int id;
    private String name;
    private String image;
    private Double price;

    public CartProductInfo(int id, String name, String image, Double price) {
        this.setId(id);
        this.setName(name);
        this.setImage(image);
        this.setPrice(price);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    private void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
