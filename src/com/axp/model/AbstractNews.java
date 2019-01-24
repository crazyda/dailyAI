package com.axp.model;

import java.sql.Timestamp;


/**
 * AbstractNews entity provides the base persistence definition of the News
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNews implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer zoneId;
	private String title;
	private String url;
	private Short status;
	private Timestamp createTime;
	private Integer adminuserId;

	// Constructors

	/** default constructor */
	public AbstractNews() {
	}

	/** full constructor */
	public AbstractNews(Integer zoneId, String title, String url, Short status,
			Timestamp createTime, Integer adminuserId) {
		this.zoneId = zoneId;
		this.title = title;
		this.url = url;
		this.status = status;
		this.adminuserId = adminuserId;
		this.createTime=createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getAdminuserId() {
		return adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	
}