package com.axp.model;

import java.sql.Timestamp;

/**
 * NewRedPaperAssetapply entity. @author MyEclipse Persistence Tools
 */

public class NewRedPaperAssetapply implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private AdminUser remitter;
	private Double positons;
	private Timestamp createTime;
	private Timestamp endTime;
	private NewRedPaperAsset asser;
	private Integer status;
	private Boolean isValid;
	private String remark;
	// Constructors

	/** default constructor */
	public NewRedPaperAssetapply() {
	}

	public Integer getId() {
		return id;
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

	public AdminUser getRemitter() {
		return remitter;
	}

	public void setRemitter(AdminUser remitter) {
		this.remitter = remitter;
	}

	public Double getPositons() {
		return positons;
	}

	public void setPositons(Double positons) {
		this.positons = positons;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public NewRedPaperAsset getAsser() {
		return asser;
	}

	public void setAsser(NewRedPaperAsset asser) {
		this.asser = asser;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsValid() {
		return isValid;
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