package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserProfits entity provides the base persistence definition of the
 * UserProfits entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserProfits implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Double money;
	private Boolean isvalid;
	private Timestamp createtime;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractUserProfits() {
	}

	/** full constructor */
	public AbstractUserProfits(Users users, Double money, Boolean isvalid,
			Timestamp createtime, String remark) {
		this.users = users;
		this.money = money;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}