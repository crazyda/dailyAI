package com.axp.model;

import java.sql.Timestamp;

/**
 * UserCashshopRecord entity. @author MyEclipse Persistence Tools
 */
public class UserCashshopRecord extends AbstractUserCashshopRecord implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserCashshopRecord() {
	}

	/** minimal constructor */
	public UserCashshopRecord(Boolean isValid, Boolean isSend, Boolean isGain) {
		super(isValid, isSend, isGain);
	}

	/** full constructor */
	public UserCashshopRecord(Cashshop cashshop, Seller seller, Users users, Boolean isValid, Double payValue, String exchangeCode, Timestamp createTime, Timestamp lastTime, String parameter, String deliverName,
			Integer deliverPrice, String deliverCode, Timestamp deliverTime, Boolean isSend, Boolean isGain, Boolean isConfirm, String orderId, Integer deliveryMethod, Integer amount, Integer payStatus, String userName, String userAddress,
			String userPhone) {
		super(cashshop, seller, users, isValid, payValue, exchangeCode, createTime, lastTime, parameter, deliverName, deliverPrice, deliverCode, deliverTime,isSend, isGain, isConfirm, orderId, deliveryMethod, amount, payStatus,
				userName, userAddress, userPhone);
	}

}
