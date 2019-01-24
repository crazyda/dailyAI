package com.axp.model;

import java.sql.Timestamp;

/**
 * AdvertDaysChangeLog entity. @author MyEclipse Persistence Tools
 */
public class AdvertDaysChangeLog extends AbstractAdvertDaysChangeLog implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AdvertDaysChangeLog() {
	}

	/** minimal constructor */
	public AdvertDaysChangeLog(Integer id, Integer userId) {
		super(id, userId);
	}

	/** full constructor */
	public AdvertDaysChangeLog(Integer id, Integer userId, Integer parentId,
			String userRealName, Integer centerDayCounts,
			Integer cityDayCounts, Integer unionDaycounts,
			Timestamp createTime, String discript) {
		super(id, userId, parentId, userRealName, centerDayCounts,
				cityDayCounts, unionDaycounts, createTime, discript);
	}

}
