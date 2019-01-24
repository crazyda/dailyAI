package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractProxyinfos entity provides the base persistence definition of the
 * Proxyinfos entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProxyinfos implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Integer levelNum1;
	private Integer levelNum2;
	private Integer levelNum3;
	private Boolean isvalid;
	private Timestamp createtime;
	private Set proxyuserses = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractProxyinfos() {
	}

	/** minimal constructor */
	public AbstractProxyinfos(Users users, Integer levelNum1,
			Integer levelNum2, Integer levelNum3, Boolean isvalid,
			Timestamp createtime) {
		this.users = users;
		this.levelNum1 = levelNum1;
		this.levelNum2 = levelNum2;
		this.levelNum3 = levelNum3;
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	/** full constructor */
	public AbstractProxyinfos(Users users, Integer levelNum1,
			Integer levelNum2, Integer levelNum3, Boolean isvalid,
			Timestamp createtime, Set proxyuserses) {
		this.users = users;
		this.levelNum1 = levelNum1;
		this.levelNum2 = levelNum2;
		this.levelNum3 = levelNum3;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.proxyuserses = proxyuserses;
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

	public Integer getLevelNum1() {
		return this.levelNum1;
	}

	public void setLevelNum1(Integer levelNum1) {
		this.levelNum1 = levelNum1;
	}

	public Integer getLevelNum2() {
		return this.levelNum2;
	}

	public void setLevelNum2(Integer levelNum2) {
		this.levelNum2 = levelNum2;
	}

	public Integer getLevelNum3() {
		return this.levelNum3;
	}

	public void setLevelNum3(Integer levelNum3) {
		this.levelNum3 = levelNum3;
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

	public Set getProxyuserses() {
		return this.proxyuserses;
	}

	public void setProxyuserses(Set proxyuserses) {
		this.proxyuserses = proxyuserses;
	}

}