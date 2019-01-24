package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * MembersTypeIncome entity. @author MyEclipse Persistence Tools
 */
public class MembersTypeIncome extends AbstractMembersTypeIncome implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public MembersTypeIncome() {
	}

	/** full constructor */
	public MembersTypeIncome(String typeName, Double payMoney,
			Double parentIncome, Double parentSellerIncome, Double cashIncome,
			Integer award, Timestamp createTime, Boolean isValid, Set memberses) {
		super(typeName, payMoney, parentIncome, parentSellerIncome, cashIncome,
				award, createTime, isValid, memberses);
	}

}
