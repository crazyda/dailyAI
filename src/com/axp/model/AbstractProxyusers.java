package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractProxyusers entity provides the base persistence definition of the
 * Proxyusers entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProxyusers implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Proxyinfos proxyinfos;
	private Levels levels;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractProxyusers() {
	}

	/** full constructor */
	public AbstractProxyusers(Users users, Proxyinfos proxyinfos,
			Levels levels, Boolean isvalid, Timestamp createtime) {
		this.users = users;
		this.proxyinfos = proxyinfos;
		this.levels = levels;
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

	public Proxyinfos getProxyinfos() {
		return this.proxyinfos;
	}

	public void setProxyinfos(Proxyinfos proxyinfos) {
		this.proxyinfos = proxyinfos;
	}

	public Levels getLevels() {
		return this.levels;
	}

	public void setLevels(Levels levels) {
		this.levels = levels;
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