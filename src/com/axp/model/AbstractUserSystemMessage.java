package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserSystemMessage entity provides the base persistence definition of
 * the UserSystemMessage entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserSystemMessage implements java.io.Serializable {

	// Fields

	private Integer id;
	private SystemMessageList systemMessageList;
	private Integer isRead;
	private Timestamp readtime;
	private Boolean isValid;
	private Timestamp createtime;
	private Integer userId;
	private Users users;
	private MessageType messageType;
	private AdminUser adminUser;
	private ProvinceEnum provinceEnum;
	// Constructors

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	/** default constructor */
	public AbstractUserSystemMessage() {
	}

	/** full constructor */
	public AbstractUserSystemMessage(SystemMessageList systemMessageList, Integer isRead,
			Timestamp readtime,  Boolean isValid,
			Timestamp createtime, Integer userId) {
		this.systemMessageList=systemMessageList;
		this.isRead = isRead;
		this.readtime = readtime;
		this.isValid = isValid;
		this.createtime = createtime;
		this.userId = userId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsRead() {
		return this.isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Timestamp getReadtime() {
		return this.readtime;
	}

	public void setReadtime(Timestamp readtime) {
		this.readtime = readtime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public SystemMessageList getSystemMessageList() {
		return systemMessageList;
	}

	public void setSystemMessageList(SystemMessageList systemMessageList) {
		this.systemMessageList = systemMessageList;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public ProvinceEnum getProvinceEnum() {
		return provinceEnum;
	}

	public void setProvinceEnum(ProvinceEnum provinceEnum) {
		this.provinceEnum = provinceEnum;
	}

	
	
}