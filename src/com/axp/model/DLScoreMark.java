package com.axp.model;

import java.sql.Timestamp;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
public class DLScoreMark extends AbstractDLScoreMark implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2604225953275809244L;

	public DLScoreMark() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DLScoreMark(Integer id, AdminUser adminUser, Timestamp createTime,
			Boolean isValid, Integer scoreId, AdminUser adminUserSeller,
			ScoreMark scoreMark) {
		super(id, adminUser, createTime, isValid, scoreId, adminUserSeller, scoreMark);
		// TODO Auto-generated constructor stub
	}

	

	
	
	
	
	
	
}
