package com.axp.model;

import java.sql.Timestamp;

/**
 * UserDrawCashRecord entity. @author MyEclipse Persistence Tools
 */
public class UserDrawCashRecord extends AbstractUserDrawCashRecord implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserDrawCashRecord() {
	}

	/** minimal constructor */
	public UserDrawCashRecord(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public UserDrawCashRecord( Boolean isValid, Double cashPoints, Timestamp createTime) {
		super(isValid, cashPoints, createTime);
	}

}
