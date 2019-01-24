package com.axp.model;

import java.sql.Timestamp;

/**
 * Redpaperlog entity. @author MyEclipse Persistence Tools
 */
public class Redpaperlog extends AbstractRedpaperlog implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Redpaperlog() {
	}

	/** minimal constructor */
	public Redpaperlog(Users users, Redpaper redpaper) {
		super(users, redpaper);
	}

	/** full constructor */
	public Redpaperlog(Users users, Redpaper redpaper, Double money,
			Timestamp createTime, String remark) {
		super(users, redpaper, money, createTime, remark);
	}

}
