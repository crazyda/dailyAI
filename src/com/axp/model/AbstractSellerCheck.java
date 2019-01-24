package com.axp.model;

import java.util.Date;

import com.axp.util.JsonDescript;

/**
 * AbstractSellerCheck entity provides the base persistence definition of the
 * SellerCheck entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSellerCheck implements java.io.Serializable {

	// Fields

	private Integer id;
	private Seller seller;
	private String paperCode;
	private String paperImgUrl;
	private String paperImgUrl2;
	private String alipayCode;
	private String alipayName;
	private String bankCardCode;
	private String bankCardName;
	private String depositBank;
	private String depositAddress;
	private Integer status;
	private Date createTime;
	private Date checkTime;
	private boolean isValid;
	private String userPhone;
	private String userName;
	private String remark;

	// Constructors

	/** default constructor */
	public AbstractSellerCheck() {
	}

	/** full constructor */

	// Property accessors
	public AbstractSellerCheck(Integer id, Seller seller, String paperCode,
			String paperImgUrl, String paperImgUrl2, String alipayCode,
			String alipayName, String bankCardCode, String bankCardName,
			String depositBank, String depositAddress, Integer status,
			Date createTime, Date checkTime, boolean isValid, String userPhone,
			String userName, String remark) {
		super();
		this.id = id;
		this.seller = seller;
		this.paperCode = paperCode;
		this.paperImgUrl = paperImgUrl;
		this.paperImgUrl2 = paperImgUrl2;
		this.alipayCode = alipayCode;
		this.alipayName = alipayName;
		this.bankCardCode = bankCardCode;
		this.bankCardName = bankCardName;
		this.depositBank = depositBank;
		this.depositAddress = depositAddress;
		this.status = status;
		this.createTime = createTime;
		this.checkTime = checkTime;
		this.isValid = isValid;
		this.userPhone = userPhone;
		this.userName = userName;
		this.remark = remark;
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getPaperCode() {
		return this.paperCode;
	}

	public void setPaperCode(String paperCode) {
		this.paperCode = paperCode;
	}

	public String getPaperImgUrl() {
		return this.paperImgUrl;
	}

	public void setPaperImgUrl(String paperImgUrl) {
		this.paperImgUrl = paperImgUrl;
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

	public String getBankCardCode() {
		return this.bankCardCode;
	}

	public void setBankCardCode(String bankCardCode) {
		this.bankCardCode = bankCardCode;
	}

	public String getBankCardName() {
		return this.bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getDepositBank() {
		return this.depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getDepositAddress() {
		return this.depositAddress;
	}

	public void setDepositAddress(String depositAddress) {
		this.depositAddress = depositAddress;
	}

	@JsonDescript(isShow = true, dateFormat = "yyyy-MM-dd")
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

	public String getPaperImgUrl2() {
		return paperImgUrl2;
	}

	public void setPaperImgUrl2(String paperImgUrl2) {
		this.paperImgUrl2 = paperImgUrl2;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

}