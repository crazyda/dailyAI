package com.axp.model;

import java.sql.Timestamp;

/**
 * PromoterIncomeRecord entity. @author MyEclipse Persistence Tools
 */
public class PromoterIncomeRecord extends AbstractPromoterIncomeRecord
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PromoterIncomeRecord() {
	}

	/** minimal constructor */
	public PromoterIncomeRecord(Promoter promoter) {
		super(promoter);
	}

	/** full constructor */
	public PromoterIncomeRecord(Promoter promoter, Double income,
			Integer incomeFromProId, Timestamp createTime, Timestamp lastTime,
			Boolean isValid) {
		super(promoter, income, incomeFromProId, createTime, lastTime, isValid);
	}

}
