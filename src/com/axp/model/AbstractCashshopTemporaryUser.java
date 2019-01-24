package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractCashshopTemporaryUser entity provides the base persistence definition
 * of the CashshopTemporaryUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCashshopTemporaryUser implements
		java.io.Serializable {

	// Fields

	private Integer id;
	//private Integer cashshopId;
	//private Integer adminuserId;
	//private Integer temporaryUserId;
	private Cashshop cashshop;
	private AdminUser adminUser;
	private AdminUser temporaryUser;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractCashshopTemporaryUser() {
	}

	/** full constructor */
	public AbstractCashshopTemporaryUser(Integer cashshopId,
			Integer adminuserId, Integer temporaryUserId, Timestamp createTime,
			Boolean isValid) {
		/*
		this.cashshopId = cashshopId;
		this.adminuserId = adminuserId;
		this.temporaryUserId = temporaryUserId;
		this.createTime = createTime;
		this.isValid = isValid;*/
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
/*
	public Integer getCashshopId() {
		return this.cashshopId;
	}

	public void setCashshopId(Integer cashshopId) {
		this.cashshopId = cashshopId;
	}

	public Integer getAdminuserId() {
		return this.adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}

	public Integer getTemporaryUserId() {
		return this.temporaryUserId;
	}

	public void setTemporaryUserId(Integer temporaryUserId) {
		this.temporaryUserId = temporaryUserId;
	}
*/
	
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public Cashshop getCashshop() {
		return cashshop;
	}

	public void setCashshop(Cashshop cashshop) {
		this.cashshop = cashshop;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public AdminUser getTemporaryUser() {
		return temporaryUser;
	}

	public void setTemporaryUser(AdminUser temporaryUser) {
		this.temporaryUser = temporaryUser;
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