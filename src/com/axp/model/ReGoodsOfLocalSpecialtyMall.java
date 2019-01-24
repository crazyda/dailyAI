package com.axp.model;

import com.axp.model.ProvinceEnum;

/**
 * 当地特产商城的商品表；
 * @author Administrator
 *
 */
public class ReGoodsOfLocalSpecialtyMall extends ReBaseGoods {

	private Double cashBack;//返现比例；(只在无商品规格的时候使用，如果有商品规格，那么就将返现比例临时记录到红包字段中去)
	private String placeOfProduction;//所在产地
	private String pack;//包装
	private CommodityType commodityType;//所在类别
	private Integer sales;
	private ProvinceEnum provinceEnum;
	private Integer fenyong;
	private Boolean istop;
	
	
	
	public Integer getFenyong() {
		return fenyong;
	}

	public void setFenyong(Integer fenyong) {
		this.fenyong = fenyong;
	}

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

	public CommodityType getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(CommodityType commodityType) {
		this.commodityType = commodityType;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public Boolean getIstop() {
		return istop;
	}

	public void setIstop(Boolean istop) {
		this.istop = istop;
	}


	
}
