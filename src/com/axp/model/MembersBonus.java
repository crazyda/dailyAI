package com.axp.model;

import java.sql.Timestamp;

/**
 * MembersBonus entity. @author MyEclipse Persistence Tools
 */
public class MembersBonus extends AbstractMembersBonus implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public MembersBonus() {
	}

	/** minimal constructor */
	public MembersBonus(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public MembersBonus(MembersTypeIncome membersTypeIncome,
			Double levelOneScale, Double levelTwoScale, Double levelThreeScale,
			Boolean isValid, Timestamp createTime) {
		super(membersTypeIncome, levelOneScale, levelTwoScale, levelThreeScale,
				isValid, createTime);
	}

}
