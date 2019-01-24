package com.axp.model;

import java.sql.Timestamp;

/**
 * Scorerecords entity. @author MyEclipse Persistence Tools
 */
public class JphScorerecords extends AbstractJphScorerecords implements
		java.io.Serializable {

	public JphScorerecords() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JphScorerecords(Users users, Integer score, Boolean isvalid,
			Timestamp createtime, Integer adminuserId, String scoretype) {
		super(users, score, isvalid, createtime, adminuserId, scoretype);
		// TODO Auto-generated constructor stub
	}

	public JphScorerecords(Users users, Integer beforeScore, Integer score,
			Integer afterScore, String remark, Integer foreignId, Integer type,
			Boolean isvalid, Timestamp createtime, Integer adminuserId,
			String scoretype) {
		super(users, beforeScore, score, afterScore, remark, foreignId, type, isvalid,
				createtime, adminuserId, scoretype);
		// TODO Auto-generated constructor stub
	}

	

}
