package com.axp.model;

import java.sql.Timestamp;

/**
 * FeeRecords entity. @author MyEclipse Persistence Tools
 */
public class FeeRecords extends AbstractFeeRecords implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public FeeRecords() {
	}

	/** full constructor */
	public FeeRecords(Users usersByUserId, Users usersByOpUserId, Double fee,
			Boolean isvalid, Timestamp createtime) {
		
	}

}
