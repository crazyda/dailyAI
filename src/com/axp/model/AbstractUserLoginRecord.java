package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserLoginRecord entity provides the base persistence definition of
 * the UserLoginRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserLoginRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private Integer zoneId;
	private Users users;
	private ProvinceEnum provinceEnum;
	private Timestamp lasttime;
	private String appVersion;
	// Constructors

	/** default constructor */
	public AbstractUserLoginRecord() {
	}

	/** minimal constructor */
	public AbstractUserLoginRecord(Integer id, Users users, Timestamp lasttime) {
		this.id = id;
		this.users = users;
		this.lasttime = lasttime;
	}

	/** full constructor */
	public AbstractUserLoginRecord(Integer id, Users users,
			 Timestamp lasttime,Integer userId,Integer zoneId,ProvinceEnum provinceEnum,String appVersion) {
		this.id = id;
		this.users = users;
		this.lasttime = lasttime;
		this.zoneId=zoneId;
		this.userId=userId;
		this.provinceEnum=provinceEnum;
		this.appVersion = appVersion;
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


	public Timestamp getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Timestamp lasttime) {
		this.lasttime = lasttime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getZoneId() {
		return zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
}