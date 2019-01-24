package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractCashshopParameterRecord entity provides the base persistence
 * definition of the CashshopParameterRecord entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractCashshopParameterRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Cashshop cashshop;
	private Parameter parameter;
	private Integer count;
	private Boolean isValid;
	private Timestamp createTime;
	private Timestamp lastTime;

	// Constructors

	/** default constructor */
	public AbstractCashshopParameterRecord() {
	}

	/** minimal constructor */
	public AbstractCashshopParameterRecord(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractCashshopParameterRecord(Cashshop cashshop, Parameter parameter, Integer count, Boolean isValid, Timestamp createTime, Timestamp lastTime) {
		this.cashshop = cashshop;
		this.parameter = parameter;
		this.count = count;
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

	public Cashshop getCashshop() {
		return this.cashshop;
	}

	public void setCashshop(Cashshop cashshop) {
		this.cashshop = cashshop;
	}

	public Parameter getParameter() {
		return this.parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

}