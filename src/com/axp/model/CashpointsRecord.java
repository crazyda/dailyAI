package com.axp.model;


/**
 * CashpointsRecord entity. @author MyEclipse Persistence Tools
 */
public class CashpointsRecord extends AbstractCashpointsRecord implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CashpointsRecord() {
	}

	/** minimal constructor */
	public CashpointsRecord(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	/*
	public CashpointsRecord(Users users, Integer beforeScore, Integer score, Integer afterScore, String remark, Boolean isValid, Timestamp createTime, Timestamp lastTime) {
		super(users, beforeScore, score, afterScore, remark, isValid, createTime, lastTime);
	}
	 */
}
