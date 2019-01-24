package com.axp.model;

import java.sql.Timestamp;

/**
 * PromoterModeRecord entity. @author MyEclipse Persistence Tools
 */
public class PromoterModeRecord extends AbstractPromoterModeRecord implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PromoterModeRecord() {
	}

	/** full constructor */
	public PromoterModeRecord(PromoterMode promoterMode, AdminUser adminUser, Timestamp endTime, Timestamp createTime, Boolean isValid) {
		super(promoterMode, adminUser, endTime, createTime, isValid);
	}

}
