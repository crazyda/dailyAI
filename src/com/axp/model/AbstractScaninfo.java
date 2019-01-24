package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractScaninfo entity provides the base persistence definition of the
 * Scaninfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractScaninfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Machine machine;
	private Seller seller;
	private Integer scanUsers;
	private Timestamp scanTime;
	private Boolean isvalid;
	private Timestamp createtime;
	private String appUsername;

	// Constructors

	/** default constructor */
	public AbstractScaninfo() {
	}

	/** full constructor */
	public AbstractScaninfo(Machine machine, Seller seller, Integer scanUsers,
			Timestamp scanTime, Boolean isvalid, Timestamp createtime,
			String appUsername) {
		this.machine = machine;
		this.seller = seller;
		this.scanUsers = scanUsers;
		this.scanTime = scanTime;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.appUsername = appUsername;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Machine getMachine() {
		return this.machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Integer getScanUsers() {
		return this.scanUsers;
	}

	public void setScanUsers(Integer scanUsers) {
		this.scanUsers = scanUsers;
	}

	public Timestamp getScanTime() {
		return this.scanTime;
	}

	public void setScanTime(Timestamp scanTime) {
		this.scanTime = scanTime;
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

	public String getAppUsername() {
		return this.appUsername;
	}

	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}

}