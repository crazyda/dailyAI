package com.axp.model;

import java.sql.Timestamp;

/**
 * Plan entity. @author MyEclipse Persistence Tools
 */
public class Plan extends AbstractPlan implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Plan() {
	}

	/** full constructor */
	public Plan(Advers advers, AdminUser adminUser, Seller seller,
			Machinepool machinepool, Users users, Machine machine,
			String userName, Integer type, Timestamp starttime, Integer day,
			Integer status, Integer puttype) {
		super(advers, adminUser, seller, machinepool, users, machine, userName,
				type, starttime, day, status, puttype);
	}

}
