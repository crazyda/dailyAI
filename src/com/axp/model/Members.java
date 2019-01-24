package com.axp.model;

import java.sql.Timestamp;

/**
 * Members entity. @author MyEclipse Persistence Tools
 */
public class Members extends AbstractMembers implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Members() {
	}

	/** full constructor */
	public Members(Users users, MembersTypeIncome membersTypeIncome,
			Double cash, String inviteCode, Integer pid, Boolean isActivate,Double realPayMoney,
			Timestamp createTime, Timestamp lastTime, Boolean isValid) {
		super(users, membersTypeIncome, cash, inviteCode, pid, isActivate,realPayMoney,
				createTime, lastTime, isValid);
	}

}
