package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractScoreExchanges entity provides the base persistence definition of the
 * ScoreExchanges entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractScoreExchanges implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Goods goods;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer status;
	private Integer tcount;
	private String realname;
	private String phone;
	private String address;
	private String code;
	private Boolean isNew;
	private String deliverName;
	private String deliverCode;
	private Double deliverPrice;
	private Timestamp deliverTime;
	
	// Constructors

	/** default constructor */
	public AbstractScoreExchanges() {
	}

	/** minimal constructor */
	public AbstractScoreExchanges(Users users, Goods goods, Boolean isvalid,
			Timestamp createtime, Integer status, Integer tcount) {
		this.users = users;
		this.goods = goods;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.status = status;
		this.tcount = tcount;
	}

	/** full constructor */
	public AbstractScoreExchanges(Users users, Goods goods, Boolean isvalid,
			Timestamp createtime, Integer status, Integer tcount,
			String realname, String phone, String address, String code,String deliverName,String deliverCode,Double deliverPrice,Timestamp deliverTime) {
		this.users = users;
		this.goods = goods;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.status = status;
		this.tcount = tcount;
		this.realname = realname;
		this.phone = phone;
		this.address = address;
		this.code = code;
		this.deliverCode=deliverCode;
		this.deliverName=deliverName;
		this.deliverPrice=deliverPrice;
		this.deliverTime=deliverTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTcount() {
		return this.tcount;
	}

	public void setTcount(Integer tcount) {
		this.tcount = tcount;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public String getDeliverName() {
		return deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

	public String getDeliverCode() {
		return deliverCode;
	}

	public void setDeliverCode(String deliverCode) {
		this.deliverCode = deliverCode;
	}

	public Double getDeliverPrice() {
		return deliverPrice;
	}

	public void setDeliverPrice(Double deliverPrice) {
		this.deliverPrice = deliverPrice;
	}

	public Timestamp getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Timestamp deliverTime) {
		this.deliverTime = deliverTime;
	}
	
}