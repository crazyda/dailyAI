package com.axp.model;

import java.sql.Timestamp;

/**
 * UserAddressConfig entity. @author MyEclipse Persistence Tools
 */
public class UserAddressConfig extends AbstractUserAddressConfig implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserAddressConfig() {
	}

	/** minimal constructor */
	public UserAddressConfig(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public UserAddressConfig(Users users, String recipients, String recipientsPhone, String address, Boolean isValid, Timestamp createTime, String createUser, String lastUser, Timestamp lastTime) {
		super(users, recipients, recipientsPhone, address, isValid, createTime, createUser, lastUser, lastTime);
	}

}
