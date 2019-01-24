package com.axp.model;

import java.sql.Timestamp;

/**
 * ExtendResults entity. @author MyEclipse Persistence Tools
 */
public class ExtendResults extends AbstractExtendResults implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ExtendResults() {
	}

	/** full constructor */
	public ExtendResults(Users users, Integer oneNum, Integer twoNum,
			Integer threeNum, Integer fourNum, Integer fiveNum, Integer sixNum,
			Boolean isvalid, Timestamp createtime) {
		super(users, oneNum, twoNum, threeNum, fourNum, fiveNum, sixNum,
				isvalid, createtime);
	}

}
