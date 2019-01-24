package com.axp.model;

import java.sql.Timestamp;

/**
 * SellerWithdrawAction entity. @author MyEclipse Persistence Tools
 */
public class SellerWithdraw extends AbstractSellerWithdraw implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SellerWithdraw() {
	}

	/** full constructor */
	public SellerWithdraw(Seller seller, double cashpoint, double money,
			Integer checkStatus, Integer withdrawStatus,Integer type,String cardCode, Timestamp toAccountDate,String remark,
			Timestamp createTime, boolean isValid) {
		super(seller, cashpoint, money, checkStatus, withdrawStatus,type,cardCode,
				toAccountDate, remark, createTime, isValid);
	}

}
