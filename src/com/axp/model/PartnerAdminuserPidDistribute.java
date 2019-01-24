package com.axp.model;

import java.sql.Timestamp;

/**
 * Adver entity. @author MyEclipse Persistence Tools
 */
public class PartnerAdminuserPidDistribute extends AbstractPartnerAdminuserPidDistribute implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public PartnerAdminuserPidDistribute() {
	}

	/** full constructor */
	public PartnerAdminuserPidDistribute(Integer id,Integer pidId,Integer adminuserId,Integer usersId,
			Users users,AdminUser adminUser,CityAdminuserPidDistribute cityAdminuserPidDistribute, 
			Boolean isValid,Timestamp createtime,String username) {
		super(id, pidId, adminuserId, usersId, users, adminUser, cityAdminuserPidDistribute, isValid, createtime, username);
	}

}
