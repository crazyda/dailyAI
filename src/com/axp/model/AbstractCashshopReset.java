package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractCashshopTemporaryUser entity provides the base persistence definition
 * of the CashshopTemporaryUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCashshopReset implements
		java.io.Serializable {

	// Fields

	private Integer id;	
	private Cashshop cashshop;
	private CashshopTimes times;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractCashshopReset() {
	}

	
	// Property accessors

	public AbstractCashshopReset(Cashshop cashshop,
			CashshopTimes times, Timestamp createTime,
			Boolean isValid) {
		this.cashshop = cashshop;
		this.times=times;
		this.createTime = createTime;
		this.isValid = isValid;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Cashshop getCashshop() {
		return cashshop;
	}


	public void setCashshop(Cashshop cashshop) {
		this.cashshop = cashshop;
	}

	

	public CashshopTimes getTimes() {
		return times;
	}


	public void setTimes(CashshopTimes times) {
		this.times = times;
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