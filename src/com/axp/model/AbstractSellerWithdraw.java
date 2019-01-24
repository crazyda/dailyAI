package com.axp.model;

import java.sql.Timestamp;
import java.util.Date;

import com.axp.util.JsonDescript;

/**
 * AbstractSellerWithdraw entity provides the base persistence definition of the
 * SellerWithdrawAction entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSellerWithdraw implements java.io.Serializable {

	// Fields

	private Integer id;
	private Seller seller;
	private double cashpoint;
	private double money;
	private Integer checkStatus;
	private Integer withdrawStatus;
	private Integer type;
	private String cardCode;
	private Timestamp toAccountDate;
	private String remark;
	private String alipayReason;
	private Timestamp createTime;
	private String alipayInwardId;
	private boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractSellerWithdraw() {
	}

	/** full constructor */
	public AbstractSellerWithdraw(Seller seller, double cashpoint,
			double money, Integer checkStatus, Integer withdrawStatus,Integer type,String cardCode,
			Timestamp toAccountDate, String remark, Timestamp createTime, boolean isValid) {
		this.seller = seller;
		this.cashpoint = cashpoint;
		this.money = money;
		this.checkStatus = checkStatus;
		this.withdrawStatus = withdrawStatus;
		this.type=type;
		this.cardCode = cardCode;
		this.toAccountDate = toAccountDate;
		this.remark = remark;
		this.createTime = createTime;
		this.isValid = isValid;
	}

	// Property accessors

	public String getRemark() {
		return remark;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@JsonDescript
	public Integer getId() {
		return this.id;
	}
	@JsonDescript
	public void setId(Integer id) {
		this.id = id;
	}
	@JsonDescript
	public Seller getSeller() {
		return this.seller;
	}
	@JsonDescript
	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public double getCashpoint() {
		return this.cashpoint;
	}

	public void setCashpoint(double cashpoint) {
		this.cashpoint = cashpoint;
	}

	public double getMoney() {
		return this.money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Integer getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getWithdrawStatus() {
		return this.withdrawStatus;
	}

	public void setWithdrawStatus(Integer withdrawStatus) {
		this.withdrawStatus = withdrawStatus;
	}
	@JsonDescript(isShow=true,dateFormat="yyyy-MM-dd HH:mm:ss")
	public Date getToAccountDate() {
		return this.toAccountDate;
	}

	public void setToAccountDate(Timestamp toAccountDate) {
		this.toAccountDate = toAccountDate;
	}
	@JsonDescript(isShow=true,dateFormat="yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@JsonDescript
	public boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getAlipayReason() {
		return alipayReason;
	}

	public void setAlipayReason(String alipayReason) {
		this.alipayReason = alipayReason;
	}

	public String getAlipayInwardId() {
		return alipayInwardId;
	}

	public void setAlipayInwardId(String alipayInwardId) {
		this.alipayInwardId = alipayInwardId;
	}


	
}