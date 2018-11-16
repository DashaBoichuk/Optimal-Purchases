package com.example.dasha_000.shopping;

import java.util.ArrayList;

/**
 * Created by dasha_000 on 31.03.2018.
 */

public class ProductInfo {
    private int id;
    private String name;
    private String energy;
    private String protein;
    private String fat;
    private String carbohydrates;
    private String image;
    private int categoryId;
    private ArrayList<ProductItemInfo> productItemInfo;
    public  ProductInfo(){
        productItemInfo = new ArrayList<>();
    }

    public ProductInfo(int id, String name, String energy, String protein, String fat, String carbohydrates, String image, int categoryId, ArrayList<ProductItemInfo> productItemInfo) {
        this.setId(id);
        this.setImage(image);
        this.setEnergy(energy);
        this.setProtein(protein);
        this.setFat(fat);
        this.setCarbohydrates(carbohydrates);
        this.setName(name);
        this.setCategoryId(categoryId);
        this.setProductItemInfo(productItemInfo);
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getFat() {

        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {

        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public ArrayList<ProductItemInfo> getProductItemInfo() {
        return productItemInfo;
    }

    private void setProductItemInfo(ArrayList<ProductItemInfo> productItemInfo) {
        this.productItemInfo = productItemInfo;
    }

    public void upgradeProductItemInfo (ProductItemInfo productItemInfo) {
        this.productItemInfo.add(productItemInfo);
    }
}
