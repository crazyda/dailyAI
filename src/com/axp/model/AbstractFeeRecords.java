package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractFeeRecords entity provides the base persistence definition of the
 * FeeRecords entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractFeeRecords implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users usersByUserId;
	private Users usersByOpUserId;
	private Double fee;
	private Boolean isvalid;
	private Timestamp createtime;
	private String remark;
	private Double beforeFee;
	private Double afterFee;

	// Constructors

	/** default constructor */
	public AbstractFeeRecords() {
	}

	/** full constructor */
	public AbstractFeeRecords(Users usersByUserId, Users usersByOpUserId,
			Double fee, Boolean isvalid, Timestamp createtime, String remark,
			Double beforeFee, Double afterFee) {
		this.usersByUserId = usersByUserId;
		this.usersByOpUserId = usersByOpUserId;
		this.fee = fee;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.remark = remark;
		this.beforeFee = beforeFee;
		this.afterFee = afterFee;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsersByUserId() {
		return this.usersByUserId;
	}

	public void setUsersByUserId(Users usersByUserId) {
		this.usersByUserId = usersByUserId;
	}

	public Users getUsersByOpUserId() {
		return this.usersByOpUserId;
	}

	public void setUsersByOpUserId(Users usersByOpUserId) {
		this.usersByOpUserId = usersByOpUserId;
	}

	public Double getFee() {
		return this.fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getBeforeFee() {
		return this.beforeFee;
	}

	public void setBeforeFee(Double beforeFee) {
		this.beforeFee = beforeFee;
	}

	public Double getAfterFee() {
		return this.afterFee;
	}

	public void setAfterFee(Double afterFee) {
		this.afterFee = afterFee;
	}

}