package com.axp.model;

import java.util.Date;

/**
 * SellerCheck entity. @author MyEclipse Persistence Tools
 */
public class SellerCheck extends AbstractSellerCheck implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SellerCheck() {
	}


	/** full constructor */
	public SellerCheck(Integer id, Seller seller, String paperCode,
			String paperImgUrl, String paperImgUrl2, String alipayCode,
			String alipayName, String bankCardCode, String bankCardName,
			String depositBank, String depositAddress, Integer status,
			Date createTime, Date checkTime, boolean isValid, String userPhone,
			String userName, String remark) {
		super(id, seller, paperCode, paperImgUrl, paperImgUrl2, alipayCode,
				alipayName, bankCardCode, bankCardName, depositBank,
				depositAddress, status, createTime, checkTime, isValid,
				userPhone, userName, remark);
		// TODO Auto-generated constructor stub
	}
	
}
