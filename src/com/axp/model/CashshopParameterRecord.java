package com.axp.model;

import java.sql.Timestamp;

/**
 * CashshopParameterRecord entity. @author MyEclipse Persistence Tools
 */
public class CashshopParameterRecord extends AbstractCashshopParameterRecord implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public CashshopParameterRecord() {
	}

	/** minimal constructor */
	public CashshopParameterRecord(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public CashshopParameterRecord(Cashshop cashshop, Parameter parameter, Integer count, Boolean isValid, Timestamp createTime, Timestamp lastTime) {
		super(cashshop, parameter, count, isValid, createTime, lastTime);
	}

}
