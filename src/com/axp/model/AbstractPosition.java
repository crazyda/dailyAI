package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractPosition entity provides the base persistence definition of the
 * Position entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPosition implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer quantity;
	private Integer position;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractPosition() {
	}

	/** full constructor */
	public AbstractPosition(String name, Integer quantity, Integer position,
			Boolean isvalid, Timestamp createtime) {
		this.name = name;
		this.quantity = quantity;
		this.position = position;
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

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
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