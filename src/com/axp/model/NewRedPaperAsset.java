package com.axp.model;

import java.sql.Timestamp;

/**
 * NewRedPaperAsset entity. @author MyEclipse Persistence Tools
 */
public class NewRedPaperAsset extends AbstractNewRedPaperAsset implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public NewRedPaperAsset() {
	}

	/** full constructor */
	public NewRedPaperAsset(AdminUser adminUser, Seller seller,
			Double positionSurplus, Double positionUsed,
			Double positionTotal, Integer status, Timestamp beginTime,
			Timestamp endTime, Boolean isValid) {
		super(adminUser, seller, positionSurplus, positionUsed,
				positionTotal, status, beginTime, endTime, isValid);
	}

}
