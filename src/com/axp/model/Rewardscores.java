package com.axp.model;

import java.sql.Timestamp;

/**
 * Rewardscores entity. @author MyEclipse Persistence Tools
 */
public class Rewardscores extends AbstractRewardscores implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Rewardscores() {
	}

	/** full constructor */
	public Rewardscores(Integer level, Integer score, Boolean isvalid,
			Timestamp createtime) {
		super(level, score, isvalid, createtime);
	}

}
