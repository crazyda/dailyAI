package com.axp.model;

import java.sql.Timestamp;

/**
 * Shoptypes entity. @author MyEclipse Persistence Tools
 */
public class Shoptypes extends AbstractShoptypes implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Shoptypes() {
	}

	/** full constructor */
	public Shoptypes(String name, Boolean isvalid, Timestamp createtime) {
		super(name, isvalid, createtime);
	}

}
