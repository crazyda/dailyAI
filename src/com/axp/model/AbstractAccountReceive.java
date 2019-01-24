package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdminUser entity provides the base persistence definition of the
 * AdminUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAccountReceive implements java.io.Serializable {
	public Integer id;
	public Boolean isValid;
	public String accountName;
	public Timestamp createTime;
	public Integer accountType;
	
	public AbstractAccountReceive() {
		super();
	}
	
	
	
	public AbstractAccountReceive(Integer id, Boolean isValid, String accountName, Timestamp createTime,
			Integer accountType) {
		super();
		this.id = id;
		this.isValid = isValid;
		this.accountName = accountName;
		this.createTime = createTime;
		this.accountType = accountType;
	}



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



	public String getAccountName() {
		return accountName;
	}



	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}



	public Timestamp getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}



	public Integer getAccountType() {
		return accountType;
	}



	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}


	
}