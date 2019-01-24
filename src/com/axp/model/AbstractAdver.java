package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdver entity provides the base persistence definition of the Adver
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdver implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Websites websites;
	private String name;
	private String description;
	private Integer status;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractAdver() {
	}

	/** full constructor */
	public AbstractAdver(AdminUser adminUser, Websites websites, String name,
			String description, Integer status, Boolean isvalid,
			Timestamp createtime) {
		this.adminUser = adminUser;
		this.websites = websites;
		this.name = name;
		this.description = description;
		this.status = status;
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

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Websites getWebsites() {
		return this.websites;
	}

	public void setWebsites(Websites websites) {
		this.websites = websites;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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