package com.axp.model;

import java.sql.Timestamp;

/**
 * Adverpool entity. @author MyEclipse Persistence Tools
 */
public class Adverpool extends AbstractAdverpool implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Adverpool() {
	}

	/** full constructor */
	public Adverpool(Goods goods, AdminUser adminUser, Integer playtotal,
			Integer batch, Boolean higelevel, Boolean isvalid,
			Timestamp createtime) {
		super(goods, adminUser, playtotal, batch, higelevel, isvalid,
				createtime);
	}

}
