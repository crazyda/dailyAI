package com.axp.model;

import java.sql.Timestamp;

/**
 * Promoter entity. @author MyEclipse Persistence Tools
 */
public class Promoter extends AbstractPromoter implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Promoter() {
	}

	/** minimal constructor */
	public Promoter(Users users, Boolean isvalid, Timestamp createtime, String alipayCode,
			Double commission) {
		super(users, isvalid, createtime, alipayCode, commission);
	}

	/** full constructor */
	public Promoter(Users users, Boolean isvalid, Timestamp createtime, String alipayCode,
			Timestamp lasttime, Double commission) {
		super(users, isvalid, createtime, alipayCode, lasttime, commission);
	}

}
