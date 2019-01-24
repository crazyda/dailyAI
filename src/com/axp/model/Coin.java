package com.axp.model;

import java.sql.Timestamp;

/**
 * Coin entity. @author MyEclipse Persistence Tools
 */
public class Coin extends AbstractCoin implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Coin() {
	}

	/** full constructor */
	public Coin(String name, String unit, Double price, Boolean isvalid,
			Timestamp createtime) {
		super(name, unit, price, isvalid, createtime);
	}

}
