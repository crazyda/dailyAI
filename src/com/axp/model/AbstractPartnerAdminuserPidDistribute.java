package com.axp.model;

import java.sql.Timestamp;


/**
 * AbstractAdver entity provides the base persistence definition of the Adver
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPartnerAdminuserPidDistribute implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pidId;
	private Integer adminuserId;
	private Integer usersId;
	private Users users;
	private AdminUser adminUser;
	private CityAdminuserPidDistribute cityAdminuserPidDistribute;
	private Boolean isValid;
	private Timestamp createtime;
	private String username;

	// Constructors

	/** default constructor */
	public AbstractPartnerAdminuserPidDistribute() {
	}

	/** full constructor */
	public AbstractPartnerAdminuserPidDistribute( Integer id,Integer pidId,Integer adminuserId,Integer usersId,
			Users users,AdminUser adminUser, CityAdminuserPidDistribute cityAdminuserPidDistribute, 
			Boolean isValid,Timestamp createtime,String username) {
		this.id=id;
		this.pidId = pidId;
		this.adminuserId =adminuserId;
		this.usersId =usersId;
		this.users = users;
		this.adminUser = adminUser;
		this.isValid = isValid;
		this.createtime = createtime;
		this.cityAdminuserPidDistribute =cityAdminuserPidDistribute;
		this.username = username;
		
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public CityAdminuserPidDistribute getCityAdminuserPidDistribute() {
		return cityAdminuserPidDistribute;
	}

	public void setCityAdminuserPidDistribute(
			CityAdminuserPidDistribute cityAdminuserPidDistribute) {
		this.cityAdminuserPidDistribute = cityAdminuserPidDistribute;
	}


}