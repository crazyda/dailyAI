package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractNewRedPaperAddendum entity provides the base persistence definition
 * of the NewRedPaperAddendum entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNewRedPaperAddendum implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private NewRedPaperSetting setting;
	private Timestamp endTime;
	private Double avail;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractNewRedPaperAddendum() {
	}

	/** full constructor */
	public AbstractNewRedPaperAddendum(Users users, NewRedPaperSetting setting,
			Timestamp endTime, Double avail, Timestamp createTime,
			Boolean isValid) {
		this.users = users;
		this.setting = setting;
		this.endTime = endTime;
		this.avail = avail;
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

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Double getAvail() {
		return this.avail;
	}

	public void setAvail(Double avail) {
		this.avail = avail;
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