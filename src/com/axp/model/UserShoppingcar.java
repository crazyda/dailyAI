package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * UserShoppingcar entity. @author MyEclipse Persistence Tools
 */
public class UserShoppingcar extends AbstractUserShoppingcar implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserShoppingcar() {
	}

	/** minimal constructor */
	public UserShoppingcar(Boolean isSend, Boolean isGain, Boolean isConfirm) {
		super(isSend, isGain, isConfirm);
	}

	/** full constructor */
	public UserShoppingcar(Seller seller, Users users, String shopOrder,
			Double money, Boolean isValid, Timestamp createTime,
			String deliverName, Double deliverPrice, String deliverCode,
			Timestamp deliverTime, Boolean isSend, Boolean isGain,
			String orderId, Integer amount, Integer payStatus, String userName,
			String userAddress, String userPhone, Integer deliveryMethod,
		    Boolean isConfirm, Timestamp lastTime,
			Boolean isNew, Double payCash, Integer version, Double payValue,
			String exchangeCode, String parameter, Set shoppingcars) {
		super(seller, users, shopOrder, money, isValid, createTime,
				deliverName, deliverPrice, deliverCode, deliverTime, isSend,
				isGain, orderId, amount, payStatus, userName, userAddress,
				userPhone, deliveryMethod, isConfirm, lastTime,
				isNew, payCash, version, payValue, exchangeCode, parameter,
				shoppingcars);
	}

}
