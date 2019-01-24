package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractIncometype entity provides the base persistence definition of the
 * Incometype entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractIncometype implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double income;
	private String name;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractIncometype() {
	}

	/** full constructor */
	public AbstractIncometype(Double income, String name, Timestamp createTime, Boolean isValid) {
		this.income = income;
		this.name = name;
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

	public Double getIncome() {
		return this.income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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