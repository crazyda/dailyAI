package com.axp.model;

import java.sql.Timestamp;

/**
 * NewRedPaperLogDetail entity. @author MyEclipse Persistence Tools
 */
public class NewRedPaperLogDetail extends AbstractNewRedPaperLogDetail
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public NewRedPaperLogDetail() {
	}

	/** full constructor */
	public NewRedPaperLogDetail(Users users, NewRedPaperSetting setting,
			Integer amount, Timestamp createTime, Timestamp lastTime,
			Boolean isValid) {
		super(users, setting, amount, createTime, lastTime, isValid);
	}

}
