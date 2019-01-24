package com.axp.model;

import java.sql.Timestamp;

/**
 * Playspool entity. @author MyEclipse Persistence Tools
 */
public class Playspool extends AbstractPlayspool implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Playspool() {
	}

	/** full constructor */
	public Playspool(Plays plays, Timestamp starttime, Timestamp endtime,
			Timestamp createtime, Boolean isvalid) {
		super(plays, starttime, endtime, createtime, isvalid);
	}

}
