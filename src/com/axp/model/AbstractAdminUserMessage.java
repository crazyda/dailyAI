package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdminUserMessage entity provides the base persistence definition of
 * the AdminUserMessage entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminUserMessage implements java.io.Serializable {

	// Fields

	private Integer id;
	private MessageCenter messageCenter;
	private AdminUser adminUser;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractAdminUserMessage() {
	}

	/** full constructor */
	public AbstractAdminUserMessage(MessageCenter messageCenter,
			AdminUser adminUser, Timestamp createTime,
			Boolean isValid) {
		this.messageCenter = messageCenter;
		this.adminUser = adminUser;
		this.createTime = createTime;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MessageCenter getMessageCenter() {
		return this.messageCenter;
	}

	public void setMessageCenter(MessageCenter messageCenter) {
		this.messageCenter = messageCenter;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

}