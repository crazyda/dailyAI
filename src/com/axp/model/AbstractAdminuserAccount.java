package com.axp.model;

import java.util.Date;

/**
 * AbstractAdminuserAccount entity provides the base persistence definition of
 * the AdminuserAccount entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminuserAccount implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer adminuserId;
	private double minMoney;
	private double maxMoney;
	private String alipayCode;
	private String alipayName;
	private Date createTime;
	private boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractAdminuserAccount() {
	}

	/** full constructor */
	public AbstractAdminuserAccount(Integer adminuserId, double minMoney,
			double maxMoney, String alipayCode, String alipayName,
			Date createTime, boolean isValid) {
		this.adminuserId = adminuserId;
		this.minMoney = minMoney;
		this.maxMoney = maxMoney;
		this.alipayCode = alipayCode;
		this.alipayName = alipayName;
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

	public Integer getAdminuserId() {
		return this.adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}

	public double getMinMoney() {
		return this.minMoney;
	}

	public void setMinMoney(double minMoney) {
		this.minMoney = minMoney;
	}

	public double getMaxMoney() {
		return this.maxMoney;
	}

	public void setMaxMoney(double maxMoney) {
		this.maxMoney = maxMoney;
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

}