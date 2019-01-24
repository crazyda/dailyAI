package com.axp.model;

import java.sql.Timestamp;

/**
 * CashshopTemporaryUser entity. @author MyEclipse Persistence Tools
 */
public class CashshopTemporaryUser extends AbstractCashshopTemporaryUser
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CashshopTemporaryUser() {
	}

	/** full constructor */
	public CashshopTemporaryUser(Integer cashshopId, Integer adminuserId,
			Integer temporaryUserId, Timestamp createTime, Boolean isValid) {
		super(cashshopId, adminuserId, temporaryUserId, createTime, isValid);
	}

}
