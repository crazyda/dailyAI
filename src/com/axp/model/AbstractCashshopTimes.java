package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractCashshopTemporaryUser entity provides the base persistence definition
 * of the CashshopTemporaryUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCashshopTimes implements
		java.io.Serializable {

	// Fields

	private Integer id;	
	private AdminUser user;
	private String startTime;
	private String endTime;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractCashshopTimes() {
	}

	
	// Property accessors

	public AbstractCashshopTimes(AdminUser user,
			String startTime, String endTime, Timestamp createTime,
			Boolean isValid) {
		this.user = user;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.isValid = isValid;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public AdminUser getUser() {
		return user;
	}


	public void setUser(AdminUser user) {
		this.user = user;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Timestamp getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}


	public Boolean getIsValid() {
		return isValid;
	}


	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}


	

}