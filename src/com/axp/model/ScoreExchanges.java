package com.axp.model;

import java.sql.Timestamp;

/**
 * ScoreExchanges entity. @author MyEclipse Persistence Tools
 */
public class ScoreExchanges extends AbstractScoreExchanges implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ScoreExchanges() {
	}

	/** full constructor */
	public ScoreExchanges(Integer userId, Integer goodId, Boolean isvalid,
			Timestamp createtime) {
		// super(userId, goodId, isvalid, createtime);
	}

}
