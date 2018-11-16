package com.example.dasha_000.shopping;

/**
 * Created by dasha_000 on 21.05.2018.
 */

public class ProductItemInfo {

    private Double ProductPrice;
    private String ProductLink;
    private String ShopName;

    public ProductItemInfo(Double ProductPrice, String ProductLink, String ShopName) {
        this.setProductPrice(ProductPrice);
        this.setProductLink(ProductLink);
        this.setShopName(ShopName);
    }

    public Double getProductPrice() {
        return ProductPrice;
    }

    private void setProductPrice(Double productPrice) {
        ProductPrice = productPrice;
    }

    public String getProductLink() {
        return ProductLink;
    }

    private void setProductLink(String productLink) {
        ProductLink = productLink;
    }

    public String getShopName() {
        return ShopName;
    }

    private void setShopName(String shopName) {
        ShopName = shopName;
    }

}
