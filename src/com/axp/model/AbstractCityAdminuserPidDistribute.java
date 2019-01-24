package com.axp.model;

import java.sql.Timestamp;
import java.util.List;


import com.axp.dao.PartnerPidDistributeDao;
import com.axp.dao.impl.PartnerPidDistributeDaoImpl;


/**
 * AbstractAdver entity provides the base persistence definition of the Adver
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCityAdminuserPidDistribute implements java.io.Serializable {

	// Fields
	public String username;
	public String fengpei;
	private Integer id;
	private Integer pidId;
	private Integer adminuserId;
	private AdminUser adminUser;
	private AdminuserTaokePid adminuserTaokePid;
	private Integer status;
	private Boolean isValid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractCityAdminuserPidDistribute() {
	}

	/** full constructor */
	public AbstractCityAdminuserPidDistribute( Integer id,Integer pidId,Integer adminuserId,String fengpei,String username,
			AdminUser adminUser, AdminuserTaokePid adminuserTaokePid,Integer status, Boolean isValid,Timestamp createtime) {
		this.id=id;
		this.pidId = pidId;
		this.adminuserId =adminuserId;
		this.adminUser = adminUser;
		this.status = status;
		this.isValid = isValid;
		this.createtime = createtime;
		this.adminuserTaokePid =adminuserTaokePid;
		this.fengpei=fengpei;
		this.username = username;
		
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFengpei() {
		return fengpei;
	}

	public void setFengpei(String fengpei) {
		this.fengpei = fengpei;
		
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}


	public Integer getPidId() {
		return pidId;
	}

	public void setPidId(Integer pidId) {
		this.pidId = pidId;
	}

	public Integer getAdminuserId() {
		return adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public AdminuserTaokePid getAdminuserTaokePid() {
		return adminuserTaokePid;
	}

	public void setAdminuserTaokePid(AdminuserTaokePid adminuserTaokePid) {
		this.adminuserTaokePid = adminuserTaokePid;
	}

	
}