package com.axp.model;

import java.util.Date;

/**
 * AbstractAlipayInfo entity provides the base persistence definition of the
 * AlipayInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAlipayInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String alipayCode;
	private String alipayName;
	private double money;
	private boolean mark;
	private String reason;
	private String alipayId;
	private Date successTime;
	private String remark;
	private Integer status;
	private String serialNum;
	private Date createTime;

	// Constructors

	/** default constructor */
	public AbstractAlipayInfo() {
	}

	/** full constructor */
	public AbstractAlipayInfo(String alipayCode, String alipayName,
			double money, boolean mark, String reason, String alipayId,
			Date successTime, String remark, Integer status, String serialNum) {
		this.alipayCode = alipayCode;
		this.alipayName = alipayName;
		this.money = money;
		this.mark = mark;
		this.reason = reason;
		this.alipayId = alipayId;
		this.successTime = successTime;
		this.remark = remark;
		this.status = status;
		this.serialNum = serialNum;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlipayCode() {
		return this.alipayCode;
	}

	public void setAlipayCode(String alipayCode) {
		this.alipayCode = alipayCode;
	}

	public String getAlipayName() {
		return this.alipayName;
	}

	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}

	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public boolean getMark() {
		return this.mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAlipayId() {
		return this.alipayId;
	}

	public void setAlipayId(String alipayId) {
		this.alipayId = alipayId;
	}

	public Date getSuccessTime() {
		return this.successTime;
	}

	public void setSuccessTime(Date successTime) {
		this.successTime = successTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSerialNum() {
		return this.serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}