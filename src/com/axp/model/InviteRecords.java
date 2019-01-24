package com.axp.model;

import java.sql.Timestamp;

/**
 * InviteRecords entity. @author MyEclipse Persistence Tools
 */
public class InviteRecords extends AbstractInviteRecords implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public InviteRecords() {
	}

	/** full constructor */
	public InviteRecords(Users users, Boolean isvalid, Timestamp createtime,
			Integer score) {
		// super(users, isvalid, createtime, score);
	}

}
