package com.axp.model;

import java.sql.Timestamp;

/**
 * UserLoginRecord entity. @author MyEclipse Persistence Tools
 */
public class UserLoginRecord extends AbstractUserLoginRecord implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserLoginRecord() {
	}

	/** minimal constructor */
	public UserLoginRecord(Integer id, Users users, Timestamp lasttime) {
		super(id, users, lasttime);
	}

	/** full constructor */
	public UserLoginRecord(Integer id, Users users,
			Timestamp lasttime,Integer userId,Integer zoneId,ProvinceEnum provinceEnum,String appVersion) {
		super(id, users, lasttime, userId, zoneId, provinceEnum, appVersion);
	}

}
