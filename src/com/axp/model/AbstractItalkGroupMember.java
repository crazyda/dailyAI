package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractItalkGroupMember entity provides the base persistence definition of
 * the ItalkGroupMember entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractItalkGroupMember implements java.io.Serializable {

	// Fields

	private Integer id;
	private ItalkGroup italkGroup;
	private Users users;
	private Integer type;
	private Boolean isValid;
	private Boolean isForbid;// 0 禁言 1 不禁言
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public AbstractItalkGroupMember() {
	}

	/** full constructor */
	public AbstractItalkGroupMember(ItalkGroup italkGroup, Users users, Integer type, Boolean isValid,Boolean isForbid, Timestamp createTime) {
		this.italkGroup = italkGroup;
		this.users = users;
		this.type = type;
		this.isValid = isValid;
		this.isForbid = isForbid;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public Boolean getIsForbid() {
		return isForbid;
	}

	public void setIsForbid(Boolean isForbid) {
		this.isForbid = isForbid;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ItalkGroup getItalkGroup() {
		return this.italkGroup;
	}

	public void setItalkGroup(ItalkGroup italkGroup) {
		this.italkGroup = italkGroup;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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

}