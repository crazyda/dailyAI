package com.axp.model;

import java.sql.Timestamp;



/**
 * Adver entity. @author MyEclipse Persistence Tools
 */
public class CityAdminuserPidDistribute extends AbstractCityAdminuserPidDistribute implements java.io.Serializable {
	
	// Constructors


	/** default constructor */
	public CityAdminuserPidDistribute() {
	}

	/** full constructor */
	public CityAdminuserPidDistribute(Integer id,Integer pidId,Integer adminuserId,String fengpei,String username,
			AdminUser adminUser, Integer status, Boolean isValid,Timestamp createtime,AdminuserTaokePid adminuserTaokePid) {
		super(id, pidId, adminuserId, fengpei, username, adminUser, adminuserTaokePid, status, isValid, createtime);
	}

}
