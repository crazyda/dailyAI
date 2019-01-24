package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractDistribution entity provides the base persistence definition of the
 * Distribution entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDistribution implements java.io.Serializable {

	// Fields

	private Integer id;
	private Machine machine;
	private AdminUser adminUser;
	private Seller seller;
	private Timestamp createtime;
	private Boolean isvalid;
	private String qr;
	private Integer status;

	// Constructors

	/** default constructor */
	public AbstractDistribution() {
	}

	/** full constructor */
	public AbstractDistribution(Machine machine, AdminUser adminUser,
			Seller seller, Timestamp createtime, Boolean isvalid, String qr,
			Integer status) {
		this.machine = machine;
		this.adminUser = adminUser;
		this.seller = seller;
		this.createtime = createtime;
		this.isvalid = isvalid;
		this.qr = qr;
		this.status = status;
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

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public String getQr() {
		return this.qr;
	}

	public void setQr(String qr) {
		this.qr = qr;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}