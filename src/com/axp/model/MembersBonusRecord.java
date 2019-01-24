package com.axp.model;

import java.sql.Timestamp;

/**
 * MembersBonusRecord entity. @author MyEclipse Persistence Tools
 */
public class MembersBonusRecord extends AbstractMembersBonusRecord implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public MembersBonusRecord() {
	}

	/** full constructor */
	public MembersBonusRecord(Users users, Double value, String remark,
			Boolean isEffect, Integer type, Boolean isValid,
			Timestamp createTime) {
		super(users, value, remark, isEffect, type, isValid, createTime);
	}

}
