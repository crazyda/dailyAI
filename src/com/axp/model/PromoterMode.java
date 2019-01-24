package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * PromoterMode entity. @author MyEclipse Persistence Tools
 */
public class PromoterMode extends AbstractPromoterMode implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PromoterMode() {
	}

	/** full constructor */
	public PromoterMode(PromoterMode promoterMode, String name, String descript, Integer days, Timestamp createTime, Timestamp lastTime, Boolean isValid, Set promoterModes) {
		super(promoterMode, name, descript, days, createTime, lastTime, isValid, promoterModes);
	}

}
