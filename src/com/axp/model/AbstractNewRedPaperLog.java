package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractNewRedPaperLog entity provides the base persistence definition of the
 * NewRedPaperLog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNewRedPaperLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Double money;
	private Double avail;
	private Timestamp createTime;
	private Timestamp endTime;
	private NewRedPaperAddendum addendum;
	private NewRedPaperSetting setting;
	private Boolean isvalid;
	private Integer status;
	// Constructors
	public static int STATUS_NOTUSR = 0;	//未使用
	public static int STATUS_ALLPAY = 1;	//完全支付
	public static int STATUS_SPLITPAY = 2;	//拆分支付 

	/** default constructor */
	public AbstractNewRedPaperLog() {
	}

	/** full constructor */
	public AbstractNewRedPaperLog(Users users, Double money, Double avail,
			Timestamp createTime, Timestamp endTime, NewRedPaperAddendum addendum,
			NewRedPaperSetting setting, Boolean isvalid,Integer status) {
		this.users = users;
		this.money = money;
		this.avail = avail;
		this.createTime = createTime;
		this.endTime = endTime;
		this.addendum = addendum;
		this.setting = setting;
		this.isvalid = isvalid;
		this.status = status;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public NewRedPaperAddendum getAddendum() {
		return addendum;
	}

	public void setAddendum(NewRedPaperAddendum addendum) {
		this.addendum = addendum;
	}

	public NewRedPaperSetting getSetting() {
		return setting;
	}

	public void setSetting(NewRedPaperSetting setting) {
		this.setting = setting;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getAvail() {
		return this.avail;
	}

	public void setAvail(Double avail) {
		this.avail = avail;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



}