package com.axp.model;

import java.sql.Timestamp;

/**
 * VoucherRecord entity. @author MyEclipse Persistence Tools
 */
public class VoucherRecord extends AbstractVoucherRecord implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public VoucherRecord() {
	}

	/** full constructor */
	public VoucherRecord(Voucher voucher, Users users, Timestamp createTime, Boolean isValid, Double beforeScore, Double score, Double afterScore) {
		super(voucher, users, createTime, isValid, beforeScore, score, afterScore);
	}

}
