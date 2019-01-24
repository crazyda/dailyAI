package com.axp.model;

import java.sql.Timestamp;

/**
 * 商家商城购物车明细：
 * 此类用来记录商家商城中购物车中的每一条明细对象；
 */
public abstract class AbstractSellerMallShoppingCarDetail implements java.io.Serializable {
	private static final long serialVersionUID = -4743180704673685213L;

	// 字段；
	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;

	private String name;// 此条明细中商品的名称；
	private String picture;// 此条明细中，对应商品的图片；
	private String attribute;// 此条明细中，对应的商品的属性；
	private Double price;// 此条明细中，对应的商品的价格；
	private Integer number;// 此条明细中，对应的商品的数量；

	private SellerMallShoppingCar shoppingCar;// 此明细对应的购物车对象；
	private SellerMallGoods goods;// 此条明细中，对应的商品对象；

	private Integer status;// 此处的状态，对应包含该条明细的订单的状态；

	// getAndSet
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public SellerMallGoods getGoods() {
		return goods;
	}

	public void setGoods(SellerMallGoods goods) {
		this.goods = goods;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SellerMallShoppingCar getShoppingCar() {
		return shoppingCar;
	}

	public void setShoppingCar(SellerMallShoppingCar shoppingCar) {
		this.shoppingCar = shoppingCar;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}