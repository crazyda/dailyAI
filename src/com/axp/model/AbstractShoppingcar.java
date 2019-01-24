package com.axp.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * AbstractShoppingcar entity provides the base persistence definition of the
 * Shoppingcar entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractShoppingcar implements java.io.Serializable {

	// Fields

	private Integer id;
	private Cashshop cashshop;
	private Seller seller;
	private Users users;
	private Integer amount;
	private String parameter;
	private double payCash;
	private double payValue;
	private boolean isValid;
	private String cashshopName;
	private String cashshopImgUrl;
	private String parameterChar;
	private Date createTime;
	private String createTimeChar;
	private UserShoppingcar usershop;
	private String parameterRemark;
	private Integer isEvaluate = 0;
	private Integer isInShoppingCar = 0;
	private double price;

	// Constructors

	/** default constructor */
	public AbstractShoppingcar() {
	}

	/** minimal constructor */
	public AbstractShoppingcar(Cashshop cashshop, Seller seller, Users users,
			boolean isValid) {
		this.cashshop = cashshop;
		this.seller = seller;
		this.users = users;
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractShoppingcar(Cashshop cashshop, Seller seller, Users users,
			Integer amount, String parameter, double payCash, double payValue,
			boolean isValid, Date createTime,UserShoppingcar usershop,Integer isEvaluate) {
		this.cashshop = cashshop;
		this.seller = seller;
		this.users = users;
		this.amount = amount;
		this.parameter = parameter;
		this.payCash = payCash;
		this.payValue = payValue;
		this.isValid = isValid;
		this.createTime = createTime;
		this.usershop=usershop;
		this.isEvaluate=isEvaluate;
	}

	// Property accessors
	
	@JSONField(name="shoppingcarId")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JSONField(serialize=false)
	public Cashshop getCashshop() {
		return this.cashshop;
	}
	public void setCashshop(Cashshop cashshop) {
		this.cashshop = cashshop;
	}
	@JSONField(serialize=false)
	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	@JSONField(serialize=false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getParameter() {
		return this.parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public double getPayCash() {
		return this.payCash;
	}

	public void setPayCash(double payCash) {
		this.payCash = payCash;
	}

	@JSONField(serialize=false)
	public double getPayValue() {
		return this.payValue;
	}

	public void setPayValue(double payValue) {
		this.payValue = payValue;
	}

	@JSONField(serialize=false)
	public boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
	@JSONField(serialize=false)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCashshopName() {
		return cashshopName;
	}

	public void setCashshopName(String cashshopName) {
		this.cashshopName = cashshopName;
	}

	public String getCashshopImgUrl() {
		return cashshopImgUrl;
	}

	public void setCashshopImgUrl(String cashshopImgUrl) {
		this.cashshopImgUrl = cashshopImgUrl;
	}

	public String getParameterChar() {
		return parameterChar;
	}

	public void setParameterChar(String parameterChar) {
		this.parameterChar = parameterChar;
	}

	public String getCreateTimeChar() {
		return createTimeChar;
	}

	public void setCreateTimeChar(String createTimeChar) {
		this.createTimeChar = createTimeChar;
	}
	@JSONField(serialize=false)
	public UserShoppingcar getUsershop() {
		return usershop;
	}

	public void setUsershop(UserShoppingcar usershop) {
		this.usershop = usershop;
	}

	public String getParameterRemark() {
		return parameterRemark;
	}

	public void setParameterRemark(String parameterRemark) {
		this.parameterRemark = parameterRemark;
	}

	public Integer getIsInShoppingCar() {
		return isInShoppingCar;
	}

	public void setIsInShoppingCar(Integer isInShoppingCar) {
		this.isInShoppingCar = isInShoppingCar;
	}
	public Integer getIsEvaluate() {
		return isEvaluate;
	}

	public void setIsEvaluate(Integer isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}




}