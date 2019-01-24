package com.axp.model;

import java.sql.Timestamp;

/**
 * AdminuserCashpointRecord entity. @author MyEclipse Persistence Tools
 */
public class AdminuserCashpointRecord extends AbstractAdminuserCashpointRecord
		implements java.io.Serializable {

	// Constructors

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** default constructor */
	public AdminuserCashpointRecord() {
	}

	public AdminuserCashpointRecord(Integer id, AdminUser adminUser,
			Double beforepoint, Double afterpoint, Double cashpoint,
			Integer type, String cardCode, String remark, Timestamp createTime,
			Boolean isValid, String tradeNo, ReGoodsorderItem orderItem,
			Users users, Integer isDeposit) {
		super(id, adminUser, beforepoint, afterpoint, cashpoint, type, cardCode,
				remark, createTime, isValid, tradeNo, orderItem, users, isDeposit);
		// TODO Auto-generated constructor stub
	}

	

}
