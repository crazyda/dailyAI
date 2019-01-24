package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractPlan entity provides the base persistence definition of the Plan
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPlan implements java.io.Serializable {

	// Fields

	private Integer id;
	private Advers advers;
	private AdminUser adminUser;
	private Seller seller;
	private Machinepool machinepool;
	private Users users;
	private Machine machine;
	private String userName;
	private Integer type;
	private Timestamp starttime;
	private Integer day;
	private Integer status;
	private Integer puttype;

	// Constructors

	/** default constructor */
	public AbstractPlan() {
	}

	/** full constructor */
	public AbstractPlan(Advers advers, AdminUser adminUser, Seller seller,
			Machinepool machinepool, Users users, Machine machine,
			String userName, Integer type, Timestamp starttime, Integer day,
			Integer status, Integer puttype) {
		this.advers = advers;
		this.adminUser = adminUser;
		this.seller = seller;
		this.machinepool = machinepool;
		this.users = users;
		this.machine = machine;
		this.userName = userName;
		this.type = type;
		this.starttime = starttime;
		this.day = day;
		this.status = status;
		this.puttype = puttype;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Advers getAdvers() {
		return this.advers;
	}

	public void setAdvers(Advers advers) {
		this.advers = advers;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Machinepool getMachinepool() {
		return this.machinepool;
	}

	public void setMachinepool(Machinepool machinepool) {
		this.machinepool = machinepool;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Machine getMachine() {
		return this.machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Integer getDay() {
		return this.day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPuttype() {
		return this.puttype;
	}

	public void setPuttype(Integer puttype) {
		this.puttype = puttype;
	}

}