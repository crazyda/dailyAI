package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserDrawCashRecord entity provides the base persistence definition of
 * the UserDrawCashRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserDrawCashRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Boolean isValid;
	private Double cashPoints;
	private Timestamp createTime;
	private Integer adminUserId;
	private Integer sellerId;
	
	private AdminUser adminUser;
	private Seller seller;
	private Integer lastUserId;
	// Constructors

	/** default constructor */
	public AbstractUserDrawCashRecord() {
	}

	/** minimal constructor */
	public AbstractUserDrawCashRecord(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractUserDrawCashRecord( Boolean isValid, Double cashPoints, Timestamp createTime) {
		this.isValid = isValid;
		this.cashPoints = cashPoints;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Double getCashPoints() {
		return this.cashPoints;
	}

	public void setCashPoints(Double cashPoints) {
		this.cashPoints = cashPoints;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
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

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getLastUserId() {
		return lastUserId;
	}

	public void setLastUserId(Integer lastUserId) {
		this.lastUserId = lastUserId;
	}

	
	
}