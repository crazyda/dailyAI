package com.axp.model;

import java.sql.Timestamp;

/**
 * CashshopReset entity. @author MyEclipse Persistence Tools
 */
public class CashshopTimes extends AbstractCashshopTimes
		implements java.io.Serializable {

	// Constructors

	/** default constructor 
	 * @return */
	public  CashshopTimes() {
	}

	/** full constructor 
	 * @return */
	public  CashshopTimes(Integer id, AdminUser user,
			String startTime, String endTime, Timestamp createTime,
			Boolean isValid) {
		super(user, startTime, endTime,createTime, isValid);
	}

}
