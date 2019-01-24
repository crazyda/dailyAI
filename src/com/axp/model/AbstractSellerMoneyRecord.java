package com.axp.model;


import java.sql.Timestamp;

public abstract class AbstractSellerMoneyRecord implements java.io.Serializable {

	private Integer id;
	private Users users;
	private Seller seller;
	private Double money;
	private Double balance;
	private String remark;
	private String title;
	private Integer type;
	private Integer relateId;
	private String relateObject;
	private Boolean isConfirmed;
	private Timestamp createTime;
	private Boolean isValid;

	public AbstractSellerMoneyRecord() {
	}


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


	public Seller getSeller() {
		return seller;
	}


	public void setSeller(Seller seller) {
		this.seller = seller;
	}


	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRelateId() {
		return this.relateId;
	}

	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}

	public String getRelateObject() {
		return this.relateObject;
	}

	public void setRelateObject(String relateObject) {
		this.relateObject = relateObject;
	}

	public Boolean getIsConfirmed() {
		return this.isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
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

}