package com.axp.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 商家商城购物车对象：
 * 此类用来描述商家商城的购物车对象；
 */
public abstract class AbstractSellerMallShoppingCar implements java.io.Serializable {
	private static final long serialVersionUID = -439360987466018244L;

	// 字段；
	private Integer id;
	private Boolean isValid = true;
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());

	private Double price = 0D;// 购物车中的总金额，不包含运费；
	private Integer number;// 购物车中的商品送数量；

	private Users user;// 此购物车对应的用户；
	private List<SellerMallShoppingCarDetail> details = new ArrayList<SellerMallShoppingCarDetail>();// 次购物车中包含的购物明细；

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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<SellerMallShoppingCarDetail> getDetails() {
		return details;
	}

	public void setDetails(List<SellerMallShoppingCarDetail> details) {
		this.details = details;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}