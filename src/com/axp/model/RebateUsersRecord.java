package com.axp.model;

import java.sql.Timestamp;

/**
 * RebateUsersRecord entity. @author MyEclipse Persistence Tools
 */
public class RebateUsersRecord extends AbstractRebateUsersRecord implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public RebateUsersRecord() {
	}

	/** full constructor */
	public RebateUsersRecord(Goods goods,Rebate rebate, Users users, Double cashpoint,
			String remark, Timestamp createTime, Boolean isValid) {
		super(goods,rebate, users, cashpoint, remark, createTime, isValid);
	}

}
