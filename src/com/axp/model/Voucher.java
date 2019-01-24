package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Voucher entity. @author MyEclipse Persistence Tools
 */
public class Voucher extends AbstractVoucher implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Voucher() {
	}

	/** minimal constructor */
	public Voucher(Boolean isRecharge, Boolean isValid) {
		super(isRecharge, isValid);
	}

	/** full constructor */
	public Voucher(String code, String password, String encryption,AdminUser assignUser, Double faceValue, String description, Boolean isRecharge, Boolean isValid, Timestamp createTime, Timestamp lastTime,
			Integer status, Set voucherRecords,String imgUrl) {
		super(code, password, encryption, assignUser, faceValue, description, isRecharge, isValid, createTime, lastTime, status, voucherRecords,imgUrl);
	}

}
