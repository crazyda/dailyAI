package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractCoin entity provides the base persistence definition of the Coin
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCoin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String unit;
	private Double price;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractCoin() {
	}

	/** full constructor */
	public AbstractCoin(String name, String unit, Double price,
			Boolean isvalid, Timestamp createtime) {
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

}