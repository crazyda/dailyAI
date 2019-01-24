package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserCoupons entity provides the base persistence definition of the
 * UserCoupons entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserCoupons implements java.io.Serializable {

	// Fields

	private Integer id;
	private Boolean isValid;
	private Timestamp createTime;
	private Users users;
	private Seller seller;
	private ReGoodsofextendmall ticket;
	private Boolean isUse;
	private String goodsName;
	private Double ticketprice;
	private Timestamp validtime;
	private String goodsMall;
	private Integer transportationType;
	private String sellerAddress;
	private Integer sign; //1 领券中 2 券已下架
	
	public Integer getSign() {
		return sign;
	}

	public void setSign(Integer sign) {
		this.sign = sign;
	}

	/** default constructor */
	public AbstractUserCoupons() {
	}

	// Constructors

	public AbstractUserCoupons(Integer id, Boolean isValid,
			Timestamp createTime, Users users, Seller seller,
			ReGoodsofextendmall ticket, Boolean isUse, String goodsName,
			Double ticketprice, Timestamp validtime, String goodsMall,
			Integer transportationType, String sellerAddress) {
		super();
		this.id = id;
		this.isValid = isValid;
		this.createTime = createTime;
		this.users = users;
		this.seller = seller;
		this.ticket = ticket;
		this.isUse = isUse;
		this.goodsName = goodsName;
		this.ticketprice = ticketprice;
		this.validtime = validtime;
		this.goodsMall = goodsMall;
		this.transportationType = transportationType;
		this.sellerAddress = sellerAddress;
	}


	// Property accessors

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public String getGoodsMall() {
		return this.goodsMall;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public Integer getId() {
		return this.id;
	}

	public Boolean getIsUse() {
		return this.isUse;
	}


	public Boolean getIsValid() {
		return this.isValid;
	}


	public Seller getSeller() {
		return seller;
	}


	public String getSellerAddress() {
		return this.sellerAddress;
	}


	public ReGoodsofextendmall getTicket() {
		return ticket;
	}


	public Double getTicketprice() {
		return this.ticketprice;
	}


	public Integer getTransportationType() {
		return this.transportationType;
	}

	public Users getUsers() {
		return users;
	}

	

	

	public Timestamp getValidtime() {
		return this.validtime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setGoodsMall(String goodsMall) {
		this.goodsMall = goodsMall;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsUse(Boolean isUse) {
		this.isUse = isUse;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public void setTicket(ReGoodsofextendmall ticket) {
		this.ticket = ticket;
	}

	public void setTicketprice(Double ticketprice) {
		this.ticketprice = ticketprice;
	}

	public void setTransportationType(Integer transportationType) {
		this.transportationType = transportationType;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public void setValidtime(Timestamp validtime) {
		this.validtime = validtime;
	}

}