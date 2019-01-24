package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractImageType entity provides the base persistence definition of the
 * ImageType entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractImageType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer type;
	private Timestamp createTime;
	private Boolean isValid;
	private Set cashshopTypes = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractImageType() {
	}

	/** full constructor */
	public AbstractImageType(String name, Integer type, Timestamp createTime,
			Boolean isValid, Set cashshopTypes) {
		this.name = name;
		this.type = type;
		this.createTime = createTime;
		this.isValid = isValid;
		this.cashshopTypes = cashshopTypes;
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Set getCashshopTypes() {
		return this.cashshopTypes;
	}

	public void setCashshopTypes(Set cashshopTypes) {
		this.cashshopTypes = cashshopTypes;
	}

}