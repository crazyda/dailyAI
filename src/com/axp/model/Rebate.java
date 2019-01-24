package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Rebate entity. @author MyEclipse Persistence Tools
 */
public class Rebate extends AbstractRebate implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Rebate() {
	}

	/** full constructor */
	public Rebate(AdminUser adminUser, String title, String remark,
			Double budget, Double orderPoint, Integer status,
			Timestamp startTime, Timestamp endTime, Timestamp createTime,
			Boolean isValid, Set rebateUsersRecords) {
		super(adminUser, title, remark, budget, orderPoint, status, startTime,
				endTime, createTime, isValid, rebateUsersRecords);
	}

}
