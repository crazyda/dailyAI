package com.axp.model;

import java.sql.Timestamp;

import javax.annotation.Resource;


import com.axp.dao.UsersDAO;
import com.axp.dao.impl.UsersDAOImpl;

/**
 * UsersOperationRecord entity. @author MyEclipse Persistence Tools
 */
public class UsersOperationRecord extends AbstractUsersOperationRecord
		implements java.io.Serializable {
	
	private Users users;
	private AdminUser adminUser;
	private ProvinceEnum provinceEnum;

	// Constructors
	

	/** default constructor */
	public UsersOperationRecord() {
	}

	public Users getUsers(){
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	/** full constructor */
	public UsersOperationRecord(Integer userId, Timestamp createtime,
			String requsetUri, String data, Double lng, Double lat,
			String channelId, String os, String machineCode, String appVersion,
			Integer zoneId, Integer adminuserId) {
		super(userId, createtime, requsetUri, data, lng, lat, channelId, os, machineCode, appVersion, zoneId, adminuserId);
	}

}
