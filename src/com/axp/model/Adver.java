package com.axp.model;

import java.sql.Timestamp;

/**
 * Adver entity. @author MyEclipse Persistence Tools
 */
public class Adver extends AbstractAdver implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Adver() {
	}

	/** full constructor */
	public Adver(AdminUser adminUser, Websites websites, String name,
			String description, Integer status, Boolean isvalid,
			Timestamp createtime) {
		super(adminUser, websites, name, description, status, isvalid,
				createtime);
	}

}
