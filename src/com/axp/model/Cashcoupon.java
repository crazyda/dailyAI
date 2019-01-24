package com.axp.model;

import java.sql.Timestamp;

/**
 * Cashcoupon entity. @author MyEclipse Persistence Tools
 */
public class Cashcoupon extends AbstractCashcoupon implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Cashcoupon() {
	}

	/** minimal constructor */
	public Cashcoupon(Double price) {
		super(price);
	}

	/** full constructor */
	public Cashcoupon(Double price, Boolean isValid,String imgUrl, Timestamp createtime) {
		super(price, imgUrl, isValid, createtime);
	}

}
