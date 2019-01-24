package com.axp.model;

import java.sql.Timestamp;


public class SignCalc extends AbstractSignCalc implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignCalc() {
		super();
		
	}
	
	public SignCalc(Integer id, Users users, Boolean isValid,
			Timestamp createTime, Timestamp signCalcTime, String signDatil,
			Integer reward) {
		super(id, users, isValid, createTime, signCalcTime, signDatil, reward);
		
	}

	
	
}
