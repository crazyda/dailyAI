package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractProviderBonus entity provides the base persistence definition of the
 * ProviderBonus entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProviderBonus implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer providerBonusId;
	private AdminUser adminUser;
	private Double hqscale;
	private Double centerScale;
	private Double providerScale;
	private Double sellerScale;
	private Boolean isValid;
	private Boolean providerBonusIsValid;
	private Timestamp createTime;
	private Double otherSellerScale;

	// Constructors

	/** default constructor */
	public AbstractProviderBonus() {
	}

	/** minimal constructor */
	public AbstractProviderBonus(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractProviderBonus(AdminUser adminUser, Double hqscale,
			Double centerScale, Double providerScale,
			Boolean isValid, Timestamp createTime) {
		this.adminUser = adminUser;
		this.hqscale = hqscale;
		this.centerScale = centerScale;
		this.providerScale = providerScale;
		this.isValid = isValid;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Double getHqscale() {
		return this.hqscale;
	}

	public void setHqscale(Double hqscale) {
		this.hqscale = hqscale;
	}

	public Double getCenterScale() {
		return this.centerScale;
	}

	public void setCenterScale(Double centerScale) {
		this.centerScale = centerScale;
	}

	public Double getProviderScale() {
		return this.providerScale;
	}

	public void setProviderScale(Double providerScale) {
		this.providerScale = providerScale;
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

	public Double getSellerScale() {
		return sellerScale;
	}

	public void setSellerScale(Double sellerScale) {
		this.sellerScale = sellerScale;
	}

	public Double getOtherSellerScale() {
		return otherSellerScale;
	}

	public void setOtherSellerScale(Double otherSellerScale) {
		this.otherSellerScale = otherSellerScale;
	}

	public Integer getProviderBonusId() {
		this.providerBonusId = this.id;
		return this.providerBonusId;
	}

	public void setProviderBonusId(Integer id) {
		this.providerBonusId = id;
		this.id = id;
	}

	public Boolean getProviderBonusIsValid() {
		this.providerBonusIsValid = this.isValid;
		return providerBonusIsValid;
	}

	public void setProviderBonusIsValid(Boolean providerBonusIsValid) {
		this.providerBonusIsValid = providerBonusIsValid;
		this.isValid = providerBonusIsValid;
	}

}