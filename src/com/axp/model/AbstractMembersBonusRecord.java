package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractMembersBonusRecord entity provides the base persistence definition of
 * the MembersBonusRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMembersBonusRecord implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Double value;
	private String remark;
	private Boolean isEffect;
	private Integer type;
	private Boolean isValid;
	private Timestamp createTime;
	private UserShoppingcar order;

	// Constructors

	/** default constructor */
	public AbstractMembersBonusRecord() {
	}

	/** full constructor */
	public AbstractMembersBonusRecord(Users users, Double value,
			String remark, Boolean isEffect, Integer type, Boolean isValid,
			Timestamp createTime) {
		this.users = users;
		this.value = value;
		this.remark = remark;
		this.isEffect = isEffect;
		this.type = type;
		this.isValid = isValid;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
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

	public UserShoppingcar getOrder() {
		return order;
	}

	public void setOrder(UserShoppingcar order) {
		this.order = order;
	}

}