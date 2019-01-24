package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractWebsites entity provides the base persistence definition of the
 * Websites entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractWebsites implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private String url;
	private Boolean isvalid;
	private Timestamp createtime;
	private String name;
	private Set goodses = new HashSet(0);
	private Set advers = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractWebsites() {
	}

	/** minimal constructor */
	public AbstractWebsites(AdminUser adminUser, String url, Boolean isvalid,
			Timestamp createtime, String name) {
		this.adminUser = adminUser;
		this.url = url;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.name = name;
	}

	/** full constructor */
	public AbstractWebsites(AdminUser adminUser, String url, Boolean isvalid,
			Timestamp createtime, String name, Set goodses, Set advers) {
		this.adminUser = adminUser;
		this.url = url;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.name = name;
		this.goodses = goodses;
		this.advers = advers;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set goodses) {
		this.goodses = goodses;
	}

	public Set getAdvers() {
		return this.advers;
	}

	public void setAdvers(Set advers) {
		this.advers = advers;
	}

}