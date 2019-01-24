package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractRebateUsersRecord entity provides the base persistence definition of
 * the RebateUsersRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRebateUsersRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Goods goods;
	private Rebate rebate;
	private Users users;
	private Double cashpoint;
	private String remark;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractRebateUsersRecord() {
	}

	/** full constructor */
	public AbstractRebateUsersRecord(Goods goods,Rebate rebate, Users users,
			Double cashpoint, String remark, Timestamp createTime,
			Boolean isValid) {
		this.goods = goods;
		this.rebate = rebate;
		this.users = users;
		this.cashpoint = cashpoint;
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

	public Rebate getRebate() {
		return this.rebate;
	}

	public void setRebate(Rebate rebate) {
		this.rebate = rebate;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Double getCashpoint() {
		return this.cashpoint;
	}

	public void setCashpoint(Double cashpoint) {
		this.cashpoint = cashpoint;
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

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	

}