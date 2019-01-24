package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdvertDaysSellerLog entity provides the base persistence definition
 * of the AdvertDaysSellerLog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdvertDaysSellerLog implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer sellerId;
	private Integer quantity;
	private Integer createUser;
	private String remark;
	private Timestamp createTime;
	private Boolean isValid;
	private AdminUser adminUser;
	private Seller seller;

	// Constructors

	/** default constructor */
	public AbstractAdvertDaysSellerLog() {
	}

	/** full constructor */
	public AbstractAdvertDaysSellerLog(Integer sellerId, Integer quantity,
			Integer createUser, String remark, Timestamp createTime,
			Boolean isValid) {
		this.sellerId = sellerId;
		this.quantity = quantity;
		this.createUser = createUser;
		this.remark = remark;
		this.createTime = createTime;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

}