package com.axp.model;

import java.sql.Timestamp;

/**
 * Messages entity. @author MyEclipse Persistence Tools
 */
public class Messages extends AbstractMessages implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Messages() {
	}

	/** full constructor */
	public Messages(Users users, String content, Boolean isvalid,
			Timestamp createtime) {
		// super(users, content, isvalid, createtime);
	}

}
