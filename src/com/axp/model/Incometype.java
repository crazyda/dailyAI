package com.axp.model;

import java.sql.Timestamp;

/**
 * Incometype entity. @author MyEclipse Persistence Tools
 */
public class Incometype extends AbstractIncometype implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Incometype() {
	}

	/** full constructor */
	public Incometype(Double income, String name, Timestamp createTime, Boolean isValid) {
		super(income, name, createTime, isValid);
	}

}
