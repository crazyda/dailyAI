package com.axp.model;

import java.sql.Timestamp;

/**
 * 商品属性：
 * 表示商家商城中某一种商品的具体属性，如：红色42码运动鞋，库存23双，原价123，现价99；
 */
public abstract class AbstractSellerMallGoodsAttribute implements
		java.io.Serializable {

	private static final long serialVersionUID = -5884004973533591534L;

	// 字段；
	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;

	private String name; // 属性名称，应该是是由多个属性拼接而成；如：红色 42码 男士 运动鞋；
	private Integer repertory; // 库存；
	private Double price; // 现价；
	private Double usedPrice; // 原价；

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

	public Integer getRepertory() {
		return repertory;
	}

	public void setRepertory(Integer repertory) {
		this.repertory = repertory;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getUsedPrice() {
		return usedPrice;
	}

	public void setUsedPrice(Double usedPrice) {
		this.usedPrice = usedPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}