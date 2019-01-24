package com.axp.model;

import java.sql.Timestamp;

/**
 * ItalkGroupMember entity. @author MyEclipse Persistence Tools
 */
public class ItalkGroupMember extends AbstractItalkGroupMember implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ItalkGroupMember() {
	}

	/** full constructor */
	public ItalkGroupMember(ItalkGroup italkGroup, Users users, Integer type, Boolean isValid,Boolean isForbid, Timestamp createTime) {
		super(italkGroup, users, type, isValid, isForbid, createTime);
	}

}
