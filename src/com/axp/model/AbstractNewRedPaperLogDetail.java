package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractNewRedPaperLogDetail entity provides the base persistence definition
 * of the NewRedPaperLogDetail entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNewRedPaperLogDetail implements
		java.io.Serializable {

	// Fields

	private Integer id;
//	private Integer userId;
//	private Integer settingId;
	private Integer amount;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Boolean isValid;
	private Users users;
	private NewRedPaperSetting setting;

	// Constructors

	/** default constructor */
	public AbstractNewRedPaperLogDetail() {
	}

	/** full constructor */
	public AbstractNewRedPaperLogDetail(Users users, NewRedPaperSetting setting,
			Integer amount, Timestamp createTime, Timestamp lastTime,
			Boolean isValid) {
		this.users = users;
		this.setting = setting;
		this.amount = amount;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.isValid = isValid;
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

	public NewRedPaperSetting getSetting() {
		return setting;
	}

	public void setSetting(NewRedPaperSetting setting) {
		this.setting = setting;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

}