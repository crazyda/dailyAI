package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAdminUser entity provides the base persistence definition of the
 * AdminUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminuserWithdrawalsDataLog implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer adminuserId;
	private String name;
	private String phone;
	private String code;
	private Timestamp cretatetime;
	private boolean isValid;
	private String image;
	private String image2;
	private Integer status;
	private AdminUser adminUser;
	// Constructors

	/** default constructor */
	public AbstractAdminuserWithdrawalsDataLog() {
	}


	

	// Property accessors
	public Integer getId() {
		return id;
	}

	

	public AbstractAdminuserWithdrawalsDataLog(Integer id, Integer adminuserId,
			String name, String phone, String code, Timestamp cretatetime,
			boolean isValid, String image, String image2,Integer status,AdminUser adminUser) {
		this.id = id;
		this.adminuserId = adminuserId;
		this.name = name;
		this.phone = phone;
		this.code = code;
		this.cretatetime = cretatetime;
		this.isValid = isValid;
		this.image = image;
		this.image2 = image2;
		this.status=status;
		this.adminUser=adminUser;
	}


	public void setId(Integer id) {
		this.id = id;
	}



	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}


	public Integer getAdminuserId() {
		return adminuserId;
	}


	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Timestamp getCretatetime() {
		return cretatetime;
	}


	public void setCretatetime(Timestamp cretatetime) {
		this.cretatetime = cretatetime;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getImage2() {
		return image2;
	}


	public void setImage2(String image2) {
		this.image2 = image2;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}




	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}




	public AdminUser getAdminUser() {
		return adminUser;
	}




	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}



	
	
	
}