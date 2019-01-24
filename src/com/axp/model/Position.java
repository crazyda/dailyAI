package com.axp.model;

import java.sql.Timestamp;

/**
 * Position entity. @author MyEclipse Persistence Tools
 */
public class Position extends AbstractPosition implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Position() {
	}

	/** full constructor */
	public Position(String name, Integer quantity, Integer position,
			Boolean isvalid, Timestamp createtime) {
		super(name, quantity, position, isvalid, createtime);
	}

}
