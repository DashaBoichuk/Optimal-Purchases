package com.example.dasha_000.shopping.CartInformation;

/**
 * Created by dasha_000 on 01.06.2018.
 */

public class CartShopItems {
    private String address;
    private String number;
    private String hours_of_work;
    private float coordinateX;
    private float coordinateY;
    private float current_distance;

    public CartShopItems(){}

    public CartShopItems(String address, String number, String hours_of_work, float coordinateX, float coordinateY) {
        this.setAddress(address);
        this.setNumber(number);
        this.setHours_of_work(hours_of_work);
        this.setCoordinateX(coordinateX);
        this.setCoordinateY(coordinateY);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHours_of_work() {
        return hours_of_work;
    }

    public void setHours_of_work(String hours_of_work) {
        this.hours_of_work = hours_of_work;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    public float getCoordinateX() {

        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCurrent_distance() {
        return current_distance;
    }

    public void setCurrent_distance(float current_distance) {
        this.current_distance = current_distance;
    }
}
