package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractMerchantchild entity provides the base persistence definition of the
 * Merchantchild entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMerchantchild implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private String account;
	private String pwd;
	private Integer level;
	private String levelname;
	private Timestamp createtime;
	private Timestamp lasttime;
	private String fansids;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public AbstractMerchantchild() {
	}

	/** minimal constructor */
	public AbstractMerchantchild(Users users, String account, String pwd,
			Integer level, String levelname, Boolean isvalid) {
		this.users = users;
		this.account = account;
		this.pwd = pwd;
		this.level = level;
		this.levelname = levelname;
		this.isvalid = isvalid;
	}

	/** full constructor */
	public AbstractMerchantchild(Users users, String account, String pwd,
			Integer level, String levelname, Timestamp createtime,
			Timestamp lasttime, String fansids, Boolean isvalid) {
		this.users = users;
		this.account = account;
		this.pwd = pwd;
		this.level = level;
		this.levelname = levelname;
		this.createtime = createtime;
		this.lasttime = lasttime;
		this.fansids = fansids;
		this.isvalid = isvalid;
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

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLevelname() {
		return this.levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Timestamp getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}

	public String getFansids() {
		return this.fansids;
	}

	public void setFansids(String fansids) {
		this.fansids = fansids;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

}