package com.axp.model;

import java.sql.Timestamp;

/**
 * ProviderBonusRecord entity. @author MyEclipse Persistence Tools
 */
public class ProviderBonusRecord extends AbstractProviderBonusRecord implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ProviderBonusRecord() {
	}

	/** full constructor */
	public ProviderBonusRecord(AdminUser adminUser, Double value,
			String remark, Boolean isEffect, Integer type, Boolean isValid,
			Timestamp createTime, Timestamp lastTime) {
		super(adminUser, value, remark, isEffect, type, isValid, createTime,
				lastTime);
	}

}
