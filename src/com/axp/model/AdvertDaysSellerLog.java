package com.axp.model;

import java.sql.Timestamp;

/**
 * AdvertDaysSellerLog entity. @author MyEclipse Persistence Tools
 */
public class AdvertDaysSellerLog extends AbstractAdvertDaysSellerLog implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AdvertDaysSellerLog() {
	}

	/** full constructor */
	public AdvertDaysSellerLog(Integer sellerId, Integer quantity,
			Integer createUser, String remark, Timestamp createTime,
			Boolean isValid) {
		super(sellerId, quantity, createUser, remark, createTime, isValid);
	}

}
