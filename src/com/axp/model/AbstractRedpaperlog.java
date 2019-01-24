package com.axp.model;

import java.sql.Timestamp;

import com.axp.util.JsonDescript;

/**
 * AbstractRedpaperlog entity provides the base persistence definition of the
 * Redpaperlog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRedpaperlog implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Redpaper redpaper;
	private Double money;
	private Timestamp createTime;
	private String remark;
	private Boolean isGet;
	private Integer sellerRedpaperId;//如果是null，表示是总部红包，有值则是对应商家红包；
	// Constructors

	/** default constructor */
	public AbstractRedpaperlog() {
	}

	/** minimal constructor */
	public AbstractRedpaperlog(Users users, Redpaper redpaper) {
		this.users = users;
		this.redpaper = redpaper;
	}

	/** full constructor */
	public AbstractRedpaperlog(Users users, Redpaper redpaper, Double money,
			Timestamp createTime, String remark) {
		this.users = users;
		this.redpaper = redpaper;
		this.money = money;
		this.createTime = createTime;
		this.remark = remark;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JsonDescript
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	@JsonDescript
	public Redpaper getRedpaper() {
		return this.redpaper;
	}

	public void setRedpaper(Redpaper redpaper) {
		this.redpaper = redpaper;
	}

	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}
	@JsonDescript
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsGet() {
		return isGet;
	}

	public void setIsGet(Boolean isGet) {
		this.isGet = isGet;
	}

	public Integer getSellerRedpaperId() {
		return sellerRedpaperId;
	}

	public void setSellerRedpaperId(Integer sellerRedpaperId) {
		this.sellerRedpaperId = sellerRedpaperId;
	}

	
}