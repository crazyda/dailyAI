package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractNewRedPaperAssetlog entity provides the base persistence definition
 * of the NewRedPaperAssetlog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNewRedPaperAssetlog implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Seller seller;
	private Double positions;
	private Timestamp createTime;
	private AdminUser remitter;
	private Boolean isValid;
	private String remark;
	private Integer status;
	private NewRedPaperAsset asset;

	public static Integer STATUS_weishenhe = 0;
	public static Integer STATUS_tongguo = 1;
	public static Integer STATUS_butongguo = 2;
	// Constructors

	/** default constructor */
	public AbstractNewRedPaperAssetlog() {
	}

	/** full constructor */
	public AbstractNewRedPaperAssetlog(AdminUser adminUser, Seller seller,
			Double positions, Timestamp createTime, AdminUser remitter,
			Boolean isValid) {
		this.adminUser = adminUser;
		this.seller = seller;
		this.positions = positions;
		this.createTime = createTime;
		this.remitter = remitter;
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

	public AdminUser getRemitter() {
		return remitter;
	}

	public void setRemitter(AdminUser remitter) {
		this.remitter = remitter;
	}

	public Double getPositions() {
		return this.positions;
	}

	public void setPositions(Double positions) {
		this.positions = positions;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public NewRedPaperAsset getAsset() {
		return asset;
	}

	public void setAsset(NewRedPaperAsset asset) {
		this.asset = asset;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}