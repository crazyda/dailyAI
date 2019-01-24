package com.axp.model;

import java.sql.Timestamp;

/**
 * PromoterTimeLimit entity. @author MyEclipse Persistence Tools
 */
public class PromoterTimeLimit extends AbstractPromoterTimeLimit implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public PromoterTimeLimit() {
	}

	/** full constructor */
	public PromoterTimeLimit(Promoter promoter, Timestamp endTime1,
			Timestamp endTime2, Timestamp createTime, Boolean isValid) {
		super(promoter, endTime1, endTime2, createTime, isValid);
	}

}
