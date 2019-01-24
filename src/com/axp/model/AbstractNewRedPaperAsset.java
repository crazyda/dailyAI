package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractNewRedPaperAsset entity provides the base persistence definition of
 * the NewRedPaperAsset entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNewRedPaperAsset implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Seller seller;
	private Double positionSurplus;
	private Double positionUsed;
	private Double positionTotal;
	private Integer status;
	private String remark;
	private Timestamp beginTime;
	private Timestamp endTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractNewRedPaperAsset() {
	}

	/** full constructor */
	public AbstractNewRedPaperAsset(AdminUser adminUser, Seller seller,
			Double positionSurplus, Double positionUsed,
			Double positionTotal, Integer status, Timestamp beginTime,
			Timestamp endTime, Boolean isValid) {
		this.adminUser = adminUser;
		this.seller = seller;
		this.positionSurplus = positionSurplus;
		this.positionUsed = positionUsed;
		this.positionTotal = positionTotal;
		this.status = status;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Double getPositionSurplus() {
		return this.positionSurplus;
	}

	public void setPositionSurplus(Double positionSurplus) {
		this.positionSurplus = positionSurplus;
	}

	public Double getPositionUsed() {
		return this.positionUsed;
	}

	public void setPositionUsed(Double positionUsed) {
		this.positionUsed = positionUsed;
	}

	public Double getPositionTotal() {
		return this.positionTotal;
	}

	public void setPositionTotal(Double positionTotal) {
		this.positionTotal = positionTotal;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}