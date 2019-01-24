package com.axp.model;

import java.sql.Timestamp;

/**
 * FreeVoucherRecord entity. @author MyEclipse Persistence Tools
 */
public class FreeVoucherRecord extends AbstractFreeVoucherRecord implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public FreeVoucherRecord() {
	}

	/** full constructor */
	public FreeVoucherRecord(FreeVoucher freeVoucher, Users users,
			Timestamp startTime, Timestamp endTime, Timestamp createTime,
			Boolean isValid) {
		super(freeVoucher, users, startTime, endTime, createTime, isValid);
	}

}
