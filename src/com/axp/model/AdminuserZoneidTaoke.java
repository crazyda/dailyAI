package com.axp.model;

import java.sql.Timestamp;


public class AdminuserZoneidTaoke extends AbstractAdminuserZoneidTaoke implements java.io.Serializable{
	/** default constructor */
	public AdminuserZoneidTaoke() {
	}
	
	/** full constructor */
	public AdminuserZoneidTaoke(Integer id, Integer zoneid, String uri,
			Timestamp createtime, Boolean isValid,ProvinceEnum provinceEnum,Integer adminuserId,AdminUser adminUser) {
		super(id, zoneid, uri, createtime, isValid, provinceEnum, adminuserId, adminUser);
	}

}
