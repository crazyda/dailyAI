package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractUserShoppingcar entity provides the base persistence definition of
 * the UserShoppingcar entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserShoppingcar implements java.io.Serializable {

	// Fields

	private Integer id;
	private Seller seller;
	private Users users;
	private String shopOrder;
	private Double money;
	private Boolean isValid;
	private Timestamp createTime;
	private String deliverName;
	private Double deliverPrice;
	private String deliverCode;
	private Timestamp deliverTime;
	private Boolean isSend;
	private Boolean isGain;
	private String orderId;
	private Integer amount;
	private Integer payStatus;
	private Integer payment;
	private String userName;
	private String userAddress;
	private String userPhone;
	private Integer deliveryMethod;
	private Boolean isConfirm;
	private Timestamp lastTime;
	private Boolean isNew;
	private Double payCash;
	private Integer version;
	private Double payValue;
	private String exchangeCode;
	private String parameter;
	private AdminUser adminUser;
	private Integer hasEvaluateNum;
	private Set shoppingcars = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractUserShoppingcar() {
	}

	/** minimal constructor */
	public AbstractUserShoppingcar(Boolean isSend, Boolean isGain,
			Boolean isConfirm) {
		this.isSend = isSend;
		this.isGain = isGain;
		this.isConfirm = isConfirm;
	}

	/** full constructor */
	public AbstractUserShoppingcar(Seller seller, Users users,
			String shopOrder, Double money, Boolean isValid,
			Timestamp createTime, String deliverName, Double deliverPrice,
			String deliverCode, Timestamp deliverTime, Boolean isSend,
			Boolean isGain, String orderId, Integer amount, Integer payStatus,
			String userName, String userAddress, String userPhone,
			Integer deliveryMethod,  Boolean isConfirm,
			Timestamp lastTime, Boolean isNew, Double payCash, Integer version,
			Double payValue, String exchangeCode, String parameter,
			Set shoppingcars) {
		this.seller = seller;
		this.users = users;
		this.shopOrder = shopOrder;
		this.money = money;
		this.isValid = isValid;
		this.createTime = createTime;
		this.deliverName = deliverName;
		this.deliverPrice = deliverPrice;
		this.deliverCode = deliverCode;
		this.deliverTime = deliverTime;
		this.isSend = isSend;
		this.isGain = isGain;
		this.orderId = orderId;
		this.amount = amount;
		this.payStatus = payStatus;
		this.userName = userName;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.deliveryMethod = deliveryMethod;
		this.isConfirm = isConfirm;
		this.lastTime = lastTime;
		this.isNew = isNew;
		this.payCash = payCash;
		this.version = version;
		this.payValue = payValue;
		this.exchangeCode = exchangeCode;
		this.parameter = parameter;
		this.shoppingcars = shoppingcars;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
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

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getShopOrder() {
		return this.shopOrder;
	}

	public void setShopOrder(String shopOrder) {
		this.shopOrder = shopOrder;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
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

	public String getDeliverName() {
		return this.deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	

	public Double getDeliverPrice() {
		return deliverPrice;
	}

	public void setDeliverPrice(Double deliverPrice) {
		this.deliverPrice = deliverPrice;
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

	public Integer getDeliveryMethod() {
		return this.deliveryMethod;
	}

	public void setDeliveryMethod(Integer deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public Boolean getIsConfirm() {
		return this.isConfirm;
	}

	public void setIsConfirm(Boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Boolean getIsNew() {
		return this.isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Double getPayCash() {
		return this.payCash;
	}

	public void setPayCash(Double payCash) {
		this.payCash = payCash;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Set getShoppingcars() {
		return this.shoppingcars;
	}

	public void setShoppingcars(Set shoppingcars) {
		this.shoppingcars = shoppingcars;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Integer getHasEvaluateNum() {
		return hasEvaluateNum;
	}

	public void setHasEvaluateNum(Integer hasEvaluateNum) {
		this.hasEvaluateNum = hasEvaluateNum;
	}
	
}