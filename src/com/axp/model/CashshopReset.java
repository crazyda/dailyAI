package com.axp.model;

import java.sql.Timestamp;

/**
 * CashshopReset entity. @author MyEclipse Persistence Tools
 */
public class CashshopReset extends AbstractCashshopReset
		implements java.io.Serializable {

	// Constructors

	/** default constructor 
	 * @return */
	public  CashshopReset() {
	}

	/** full constructor 
	 * @return */
	public  CashshopReset(Integer id, Cashshop cashshop,
			CashshopTimes times, Timestamp createTime,
			Boolean isValid) {
		super(cashshop,times ,createTime, isValid);
	}

}
