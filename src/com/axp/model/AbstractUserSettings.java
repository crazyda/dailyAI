package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserSettings entity provides the base persistence definition of the
 * UserSettings entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserSettings implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Integer maxscorePerday;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer advernum;
	private String spread;
	private Integer proxyZoneId;

	// Constructors

	/** default constructor */
	public AbstractUserSettings() {
	}

	/** minimal constructor */
	public AbstractUserSettings(Users users, Integer maxscorePerday,
			Boolean isvalid, Timestamp createtime, Integer advernum,
			Integer proxyZoneId) {
		this.users = users;
		this.maxscorePerday = maxscorePerday;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.advernum = advernum;
		this.proxyZoneId = proxyZoneId;
	}

	/** full constructor */
	public AbstractUserSettings(Users users, Integer maxscorePerday,
			Boolean isvalid, Timestamp createtime, Integer advernum,
			String spread, Integer proxyZoneId) {
		this.users = users;
		this.maxscorePerday = maxscorePerday;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.advernum = advernum;
		this.spread = spread;
		this.proxyZoneId = proxyZoneId;
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

	public Integer getMaxscorePerday() {
		return this.maxscorePerday;
	}

	public void setMaxscorePerday(Integer maxscorePerday) {
		this.maxscorePerday = maxscorePerday;
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

	public Integer getAdvernum() {
		return this.advernum;
	}

	public void setAdvernum(Integer advernum) {
		this.advernum = advernum;
	}

	public String getSpread() {
		return this.spread;
	}

	public void setSpread(String spread) {
		this.spread = spread;
	}

	public Integer getProxyZoneId() {
		return this.proxyZoneId;
	}

	public void setProxyZoneId(Integer proxyZoneId) {
		this.proxyZoneId = proxyZoneId;
	}

}