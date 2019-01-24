package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserrecore entity provides the base persistence definition of the
 * Userrecore entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserrecore implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Goods goods;
	private Integer openCount;
	private Integer showCount;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractUserrecore() {
	}

	/** minimal constructor */
	public AbstractUserrecore(Users users, Goods goods) {
		this.users = users;
		this.goods = goods;
	}

	/** full constructor */
	public AbstractUserrecore(Users users, Goods goods, Integer openCount,
			Integer showCount, Boolean isvalid, Timestamp createtime) {
		this.users = users;
		this.goods = goods;
		this.openCount = openCount;
		this.showCount = showCount;
		this.isvalid = isvalid;
		this.createtime = createtime;
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

	public Integer getOpenCount() {
		return this.openCount;
	}

	public void setOpenCount(Integer openCount) {
		this.openCount = openCount;
	}

	public Integer getShowCount() {
		return this.showCount;
	}

	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
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

}