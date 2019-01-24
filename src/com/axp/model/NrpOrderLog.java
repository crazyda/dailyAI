package com.axp.model;

import java.sql.Timestamp;

/**
 * NrpOrderLog entity. @author MyEclipse Persistence Tools
 */

public class NrpOrderLog extends AbstractNrpOrderLog implements java.io.Serializable {

	public NrpOrderLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NrpOrderLog(Integer id, NewRedPaperLog nrpl,
			UserCashshopRecord userCR, Integer status, Boolean isvalid,
			Double userMoney, Timestamp createTime) {
		super(id, nrpl, userCR, status, isvalid, userMoney, createTime);
		// TODO Auto-generated constructor stub
	}






}