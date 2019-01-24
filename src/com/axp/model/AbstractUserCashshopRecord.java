package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserCashshopRecord entity provides the base persistence definition of
 * the UserCashshopRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserCashshopRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Cashshop cashshop;
	private Seller seller;
	private Users users;
	private Boolean isValid;
	private Boolean isConfirm;
	private Double payValue;
	private String exchangeCode;
	private Timestamp createTime;
	private Timestamp lastTime;
	private String parameter;
	private String deliverName;
	private Integer deliverPrice;
	private String deliverCode;
	private Timestamp deliverTime;
	private Boolean isSend;
	private Boolean isGain;
	private String orderId;
	private Integer deliveryMethod;
	private Integer amount;
	private Integer payStatus;
	private String userName;
	private String userAddress;
	private String userPhone;
	private Boolean isNew;
	private Integer version;
	private Integer backtype;
	private String reason;
	private String alipayCode;
	private String failReason; 
	private String parameterRemark;
	private Double payCash;
	private String paymentString;
	private String mallTypeString;
	
	public Integer getBacktype() {
		return backtype;
	}

	public void setBacktype(Integer backtype) {
		this.backtype = backtype;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	// Constructors

	/** default constructor */
	public AbstractUserCashshopRecord() {
	}

	/** minimal constructor */
	public AbstractUserCashshopRecord(Boolean isValid, Boolean isSend, Boolean isGain) {
		this.isValid = isValid;
		this.isSend = isSend;
		this.isGain = isGain;
	}

	/** full constructor */
	public AbstractUserCashshopRecord(Cashshop cashshop, Seller seller, Users users, Boolean isValid, Double payValue, String exchangeCode, Timestamp createTime, Timestamp lastTime, String parameter, String deliverName,
			Integer deliverPrice, String deliverCode,Timestamp deliverTime,  Boolean isSend, Boolean isGain, Boolean isConfirm, String orderId, Integer deliveryMethod, Integer amount, Integer payStatus, String userName, String userAddress,
			String userPhone) {
		this.cashshop = cashshop;
		this.seller = seller;
		this.users = users;
		this.isValid = isValid;
		this.payValue = payValue;
		this.exchangeCode = exchangeCode;
		this.createTime = createTime;
		this.parameter = parameter;
		this.deliverName = deliverName;
		this.deliverPrice = deliverPrice;
		this.deliverCode = deliverCode;
		this.deliverTime=deliverTime;
		this.isSend = isSend;
		this.isGain = isGain;
		this.isConfirm = isConfirm;
		this.orderId = orderId;
		this.deliveryMethod = deliveryMethod;
		this.amount = amount;
		this.payStatus = payStatus;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.lastTime = lastTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cashshop getCashshop() {
		return this.cashshop;
	}

	public void setCashshop(Cashshop cashshop) {
		this.cashshop = cashshop;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Double getPayValue() {
		return this.payValue;
	}

	public void setPayValue(Double payValue) {
		this.payValue = payValue;
	}

	public String getExchangeCode() {
		return this.exchangeCode;
	}

	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getDeliverName() {
		return this.deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	public Integer getDeliverPrice() {
		return this.deliverPrice;
	}

	public void setDeliverPrice(Integer deliverPrice) {
		this.deliverPrice = deliverPrice;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public String getDeliverCode() {
		return this.deliverCode;
	}

	public void setDeliverCode(String deliverCode) {
		this.deliverCode = deliverCode;
	}

	
	
	public Timestamp getDeliverTime() {
		return this.deliverTime;
	}

	public void setDeliverTime(Timestamp deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Boolean getIsSend() {
		return this.isSend;
	}

	public void setIsSend(Boolean isSend) {
		this.isSend = isSend;
	}

	public Boolean getIsGain() {
		return this.isGain;
	}

	public void setIsGain(Boolean isGain) {
		this.isGain = isGain;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getDeliveryMethod() {
		return this.deliveryMethod;
	}

	public void setDeliveryMethod(Integer deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPayStatus() {
		return this.payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return this.userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Boolean getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public String getParameterRemark() {
		return parameterRemark;
	}

	public void setParameterRemark(String parameterRemark) {
		this.parameterRemark = parameterRemark;
	}

	public Double getPayCash() {
		return payCash;
	}

	public void setPayCash(Double payCash) {
		this.payCash = payCash;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getAlipayCode() {
		return alipayCode;
	}

	public void setAlipayCode(String alipayCode) {
		this.alipayCode = alipayCode;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getPaymentString() {
		return paymentString;
	}

	public void setPaymentString(String paymentString) {
		this.paymentString = paymentString;
	}

	public String getMallTypeString() {
		return mallTypeString;
	}

	public void setMallTypeString(String mallTypeString) {
		this.mallTypeString = mallTypeString;
	}

}