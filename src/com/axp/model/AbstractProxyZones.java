package com.axp.model;

/**
 * AbstractProxyZones entity provides the base persistence definition of the
 * ProxyZones entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProxyZones implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Boolean isvalid;
	private Integer parentId;

	// Constructors

	/** default constructor */
	public AbstractProxyZones() {
	}

	/** full constructor */
	public AbstractProxyZones(String name, Boolean isvalid, Integer parentId) {
		this.name = name;
		this.isvalid = isvalid;
		this.parentId = parentId;
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

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}