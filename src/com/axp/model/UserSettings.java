package com.axp.model;

import java.sql.Timestamp;

/**
 * UserSettings entity. @author MyEclipse Persistence Tools
 */
public class UserSettings extends AbstractUserSettings implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserSettings() {
	}

	/** full constructor */
	public UserSettings(Users users, Integer maxcountPerday, Boolean isvalid,
			Timestamp createtime) {
		// super(users, maxcountPerday, isvalid, createtime);
	}

}
