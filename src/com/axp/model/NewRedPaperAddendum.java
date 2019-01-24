package com.axp.model;

import java.sql.Timestamp;

/**
 * NewRedPaperAddendum entity. @author MyEclipse Persistence Tools
 */
public class NewRedPaperAddendum extends AbstractNewRedPaperAddendum implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public NewRedPaperAddendum() {
	}

	/** full constructor */
	public NewRedPaperAddendum(Users users, NewRedPaperSetting setting,
			Timestamp endTime, Double avail, Timestamp createTime,
			Boolean isValid) {
		super(users, setting, endTime, avail, createTime, isValid);
	}

}
