package com.axp.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * AbstractUserAddressConfig entity provides the base persistence definition of
 * the UserAddressConfig entity. @author MyEclipse Persistence Tools
 */
public abstract class AbstractUserAddressConfig implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private String recipients;
	private String recipientsPhone;
	private String address;
	private Boolean isValid;
	private Timestamp createTime;
	private String createUser;
	private String lastUser;
	private Timestamp lastTime;
	private Boolean isDefaul;
	private String provincialUrbanAreas;
	// Constructors

	/** default constructor */
	public AbstractUserAddressConfig() {
	}

	/** minimal constructor */
	public AbstractUserAddressConfig(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractUserAddressConfig(Users users, String recipients, String recipientsPhone, String address, Boolean isValid, Timestamp createTime, String createUser, String lastUser,
			Timestamp lastTime) {
		this.users = users;
		this.recipients = recipients;
		this.recipientsPhone = recipientsPhone;
		this.address = address;
		this.isValid = isValid;
		this.createTime = createTime;
		this.createUser = createUser;
		this.lastUser = lastUser;
		this.lastTime = lastTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JSONField(serialize=false)
	public Users getUsers() {
		return this.users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}

	public String getRecipients() {
		return this.recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getRecipientsPhone() {
		return this.recipientsPhone;
	}

	public void setRecipientsPhone(String recipientsPhone) {
		this.recipientsPhone = recipientsPhone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@JSONField(serialize=false)
	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
	@JSONField(serialize=false)
	public Timestamp getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@JSONField(serialize=false)
	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@JSONField(serialize=false)
	public String getLastUser() {
		return this.lastUser;
	}

	public void setLastUser(String lastUser) {
		this.lastUser = lastUser;
	}
	@JSONField(serialize=false)
	public Timestamp getLastTime() {
		return this.lastTime;
	}
	//@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Boolean getIsDefaul() {
		return isDefaul;
	}

	public void setIsDefaul(Boolean isDefaul) {
		this.isDefaul = isDefaul;
	}

	public String getProvincialUrbanAreas() {
		return provincialUrbanAreas;
	}

	public void setProvincialUrbanAreas(String provincialUrbanAreas) {
		this.provincialUrbanAreas = provincialUrbanAreas;
	}


}