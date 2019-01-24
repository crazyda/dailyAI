package com.axp.model;

import java.util.Date;

/**
 * SellerRedpaper entity. @author MyEclipse Persistence Tools
 */
public class SellerRedpaper extends AbstractSellerRedpaper implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SellerRedpaper() {
	}

	/** minimal constructor */
	public SellerRedpaper(Seller seller) {
		super(seller);
	}

	/** full constructor */
	public SellerRedpaper(Seller seller, double totalCash, Integer count,
			Date createTime, boolean isValid, String descript,Integer surplusCount) {
		super(seller, totalCash, count, createTime, isValid, descript,surplusCount);
	}

}
