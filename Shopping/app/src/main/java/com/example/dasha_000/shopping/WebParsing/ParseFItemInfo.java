package com.example.dasha_000.shopping.WebParsing;

/**
 * Created by dasha_000 on 28.05.2018.
 */

class ParseFItemInfo {
    private String barCode;
    private String linkFitem;
    private Double priceF;

    public ParseFItemInfo(String barCode, String linkFitem, Double priceF) {
        this.setBarCode(barCode);
        this.setLinkFitem(linkFitem);
        this.setPriceF(priceF);
    }

    public Double getPriceF() {
        return priceF;
    }

    private void setPriceF(Double priceF) {
        this.priceF = priceF;
    }

    public String getLinkFitem() {

        return linkFitem;
    }

    private void setLinkFitem(String linkFitem) {
        this.linkFitem = linkFitem;
    }

    public String getBarCode() {

        return barCode;
    }

    private void setBarCode(String barCode) {
        this.barCode = barCode;
    }


}
