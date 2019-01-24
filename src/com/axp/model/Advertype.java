package com.axp.model;

import java.sql.Timestamp;

/**
 * Advertype entity. @author MyEclipse Persistence Tools
 */
public class Advertype extends AbstractAdvertype implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Advertype() {
	}

	/** full constructor */
	public Advertype(Integer type, Integer coin, Integer validity,
			Boolean isvalid, Timestamp createtime, Integer pool) {
		super(type, coin, validity, isvalid, createtime, pool);
	}

}
