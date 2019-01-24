package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractPromoterModeRecord entity provides the base persistence definition of
 * the PromoterModeRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPromoterModeRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private PromoterMode promoterMode;
	private AdminUser adminUser;
	private Timestamp endTime;
	private Timestamp startTime;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractPromoterModeRecord() {
	}

	/** full constructor */
	public AbstractPromoterModeRecord(PromoterMode promoterMode, AdminUser adminUser, Timestamp endTime, Timestamp createTime, Boolean isValid) {
		this.promoterMode = promoterMode;
		this.adminUser = adminUser;
		this.endTime = endTime;
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

	public PromoterMode getPromoterMode() {
		return this.promoterMode;
	}

	public void setPromoterMode(PromoterMode promoterMode) {
		this.promoterMode = promoterMode;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
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

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

}