package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractProviderBonusRecord entity provides the base persistence definition
 * of the ProviderBonusRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProviderBonusRecord implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Seller seller;
	private Double value;
	private String remark;
	private Boolean isEffect;
	private Integer type;
	private Boolean isValid;
	private Timestamp createTime;
	private Timestamp lastTime;
	private UserShoppingcar order;
	// Constructors

	/** default constructor */
	public AbstractProviderBonusRecord() {
	}

	/** full constructor */
	public AbstractProviderBonusRecord(AdminUser adminUser, Double value,
			String remark, Boolean isEffect, Integer type, Boolean isValid,
			Timestamp createTime, Timestamp lastTime) {
		this.adminUser= adminUser;
		this.value = value;
		this.remark = remark;
		this.isEffect = isEffect;
		this.type = type;
		this.isValid = isValid;
		this.createTime = createTime;
		this.lastTime = lastTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsEffect() {
		return this.isEffect;
	}

	public void setIsEffect(Boolean isEffect) {
		this.isEffect = isEffect;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public UserShoppingcar getOrder() {
		return order;
	}

	public void setOrder(UserShoppingcar order) {
		this.order = order;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

}