package com.axp.model;

import java.sql.Timestamp;

/**
 * ProviderBonus entity. @author MyEclipse Persistence Tools
 */
public class ProviderBonus extends AbstractProviderBonus implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ProviderBonus() {
	}

	/** minimal constructor */
	public ProviderBonus(Boolean isValid) {
		super(isValid);
	}

	/** full constructor */
	public ProviderBonus(AdminUser adminUser, Double hqscale,
			Double centerScale, Double providerScale,
			Boolean isValid, Timestamp createTime) {
		super(adminUser, hqscale, centerScale, providerScale,
				isValid, createTime);
	}

}
