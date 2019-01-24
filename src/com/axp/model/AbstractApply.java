package com.axp.model;

/**
 * AbstractApply entity provides the base persistence definition of the Apply
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractApply implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private String realname;
	private Integer age;
	private Integer sex;
	private String address;
	private String bduserid;
	private String bdchannelid;
	private Integer status;
	private String reason;

	// Constructors

	/** default constructor */
	public AbstractApply() {
	}

	/** full constructor */
	public AbstractApply(Users users, String realname, Integer age,
			Integer sex, String address, String bduserid, String bdchannelid,
			Integer status, String reason) {
		this.users = users;
		this.realname = realname;
		this.age = age;
		this.sex = sex;
		this.address = address;
		this.bduserid = bduserid;
		this.bdchannelid = bdchannelid;
		this.status = status;
		this.reason = reason;
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

	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBduserid() {
		return this.bduserid;
	}

	public void setBduserid(String bduserid) {
		this.bduserid = bduserid;
	}

	public String getBdchannelid() {
		return this.bdchannelid;
	}

	public void setBdchannelid(String bdchannelid) {
		this.bdchannelid = bdchannelid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}