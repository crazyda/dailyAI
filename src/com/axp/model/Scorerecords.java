package com.axp.model;

/**
 * Scorerecords entity. @author MyEclipse Persistence Tools
 */
public class Scorerecords extends AbstractScorerecords implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Scorerecords() {
	}

	/** full constructor */
	public Scorerecords(Users users, Integer beforeScore, Integer score,
			Integer afterScore, String remark, Integer foreignId, Integer type) {
		// super(users, beforeScore, score, afterScore, remark, foreignId,
		// type);
	}

}
