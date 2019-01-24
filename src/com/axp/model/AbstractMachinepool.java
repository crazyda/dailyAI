package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractMachinepool entity provides the base persistence definition of the
 * Machinepool entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMachinepool implements java.io.Serializable {

	// Fields

	private Integer id;
	private Machine machine;
	private AdminUser adminUser;
	private Advers advers;
	private Integer level;
	private Boolean isplay;
	private Boolean isvalid;
	private Timestamp createtime;
	private String qr;
	private Timestamp starttime;
	private Timestamp endtime;
	private Set plans = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractMachinepool() {
	}

	/** minimal constructor */
	public AbstractMachinepool(Boolean isvalid, Timestamp createtime) {
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	/** full constructor */
	public AbstractMachinepool(Machine machine, AdminUser adminUser,
			Advers advers, Integer level, Boolean isplay, Boolean isvalid,
			Timestamp createtime, String qr, Timestamp starttime,
			Timestamp endtime, Set plans) {
		this.machine = machine;
		this.adminUser = adminUser;
		this.advers = advers;
		this.level = level;
		this.isplay = isplay;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.qr = qr;
		this.starttime = starttime;
		this.endtime = endtime;
		this.plans = plans;
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

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Advers getAdvers() {
		return this.advers;
	}

	public void setAdvers(Advers advers) {
		this.advers = advers;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getIsplay() {
		return this.isplay;
	}

	public void setIsplay(Boolean isplay) {
		this.isplay = isplay;
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

	public String getQr() {
		return this.qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Set getPlans() {
		return this.plans;
	}

	public void setPlans(Set plans) {
		this.plans = plans;
	}

}