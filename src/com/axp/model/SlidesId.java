package com.axp.model;

import java.sql.Timestamp;

/**
 * SlidesId entity. @author MyEclipse Persistence Tools
 */
public class SlidesId extends AbstractSlidesId implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SlidesId() {
	}

	/** full constructor */
	public SlidesId(Integer id, String imgurls, Boolean isvalid,
			Integer userId, Timestamp createtime) {
		super(id, imgurls, isvalid, userId, createtime);
	}

}
