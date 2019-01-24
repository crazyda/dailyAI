package com.axp.model;

import java.sql.Timestamp;

/**
 * UserProfits entity. @author MyEclipse Persistence Tools
 */
public class UserProfits extends AbstractUserProfits implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserProfits() {
	}

	/** full constructor */
	public UserProfits(Users users, Double money, Boolean isvalid,
			Timestamp createtime, String remark) {
		super(users, money, isvalid, createtime, remark);
	}

}
