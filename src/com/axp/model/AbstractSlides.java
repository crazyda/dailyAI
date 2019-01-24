package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractSlides entity provides the base persistence definition of the Slides
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSlides implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private String imgurls;
	private Boolean isvalid;
	private Timestamp createtime;
	private String linkurl;
	private Integer type;

	// Constructors

	/** default constructor */
	public AbstractSlides() {
	}

	/** minimal constructor */
	public AbstractSlides(AdminUser adminUser, String imgurls, Boolean isvalid,
			Timestamp createtime, String linkurl) {
		this.adminUser = adminUser;
		this.imgurls = imgurls;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.linkurl = linkurl;
	}

	/** full constructor */
	public AbstractSlides(AdminUser adminUser, String imgurls, Boolean isvalid,
			Timestamp createtime, String linkurl, Integer type) {
		this.adminUser = adminUser;
		this.imgurls = imgurls;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.linkurl = linkurl;
		this.type = type;
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

	public String getImgurls() {
		return this.imgurls;
	}

	public void setImgurls(String imgurls) {
		this.imgurls = imgurls;
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

	public String getLinkurl() {
		return this.linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}