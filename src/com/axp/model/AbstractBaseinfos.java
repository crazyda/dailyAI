package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractBaseinfos entity provides the base persistence definition of the
 * Baseinfos entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBaseinfos implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private Boolean isvalid;
	private Timestamp createtime;
	private String linkurl;
	private String agreement;

	// Constructors

	/** default constructor */
	public AbstractBaseinfos() {
	}

	/** full constructor */
	public AbstractBaseinfos(String content, Boolean isvalid,
			Timestamp createtime, String linkurl, String agreement) {
		this.content = content;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.linkurl = linkurl;
		this.agreement = agreement;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getAgreement() {
		return this.agreement;
	}

	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}

}