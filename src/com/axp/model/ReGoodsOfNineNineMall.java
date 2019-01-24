package com.axp.model;

/**
 * 99商城的商品表；
 *
 * @author Administrator
 */
public class ReGoodsOfNineNineMall extends ReBaseGoods {

    private Double cashBack;//返现
    private String placeOfProduction;//所在产地
    private String pack;//包装

    public Double getCashBack() {
        return cashBack;
    }

    public void setCashBack(Double cashBack) {
        this.cashBack = cashBack;
    }

    public String getPlaceOfProduction() {
        return placeOfProduction;
    }

    public void setPlaceOfProduction(String placeOfProduction) {
        this.placeOfProduction = placeOfProduction;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }


}
