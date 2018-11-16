package com.example.dasha_000.shopping.CartInformation;

import java.util.ArrayList;

/**
 * Created by dasha_000 on 30.05.2018.
 */

public class CartShopInfo {
    private Integer id;
    private String shop_name;
    private Double shop_summ;
    private CartShopItems nearestShop;
    private ArrayList<CartProductInfo> cartProductInfos;
    private ArrayList<CartShopItems> cartShopItemses;


    public  CartShopInfo(){
        cartProductInfos = new ArrayList<>();
        cartShopItemses = new ArrayList<>();
    }

    public CartShopInfo(String shop_name, Integer id){
        cartProductInfos = new ArrayList<>();
        cartShopItemses = new ArrayList<>();
     //   this.setId(id);
        this.setShop_name(shop_name);
        this.setId(id);
    }

    public String getShop_name() {
        return shop_name;
    }

    private void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public ArrayList<CartProductInfo> getCartProductInfos() {
        return cartProductInfos;
    }

    public void setCartProductInfos(ArrayList<CartProductInfo> cartProductInfos) {
        this.cartProductInfos = cartProductInfos;
    }


    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }



    public void upgradeCartProductInfo (CartProductInfo cartProductInfo) {
        this.cartProductInfos.add(cartProductInfo);
    }

    public ArrayList<CartShopItems> getCartShopItemses() {
        return cartShopItemses;
    }

    public void setCartShopItemses(ArrayList<CartShopItems> cartShopItemses) {
        this.cartShopItemses = cartShopItemses;
    }

    public void upgradeCartShopItems (CartShopItems cartShopItems) {
        this.cartShopItemses.add(cartShopItems);

    }

    public Double getShop_summ() {
        return shop_summ;
    }

    public void setShop_summ(Double shop_summ) {
        this.shop_summ = shop_summ;
    }

    public CartShopItems getNearestShop() {
        return nearestShop;
    }

    public void setNearestShop(CartShopItems nearestShop) {
        this.nearestShop = nearestShop;
    }
}
