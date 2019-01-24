package com.axp.model;

import java.sql.Timestamp;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
public class News extends AbstractNews implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public News() {
	}

	/** full constructor */
	public News(Integer zoneId, String title, String url, Short status,
			Timestamp createTime,Integer adminuserId) {
		super(zoneId, title, url, status, createTime, adminuserId);
	}

}
