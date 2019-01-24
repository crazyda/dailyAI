package com.axp.model;

import java.sql.Timestamp;

/**
 * NewRedPaperLog entity. @author MyEclipse Persistence Tools
 */
public class NewRedPaperLog extends AbstractNewRedPaperLog implements java.io.Serializable {

	public NewRedPaperLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewRedPaperLog(Users users, Double money, Double avail,
			Timestamp createTime, Timestamp endTime,
			NewRedPaperAddendum addendum, NewRedPaperSetting setting,
			Boolean isvalid, Integer status) {
		super(users, money, avail, createTime, endTime, addendum, setting, isvalid,
				status); 
		// TODO Auto-generated constructor stub
	}




}
