package com.axp.model;

import java.sql.Timestamp;

/**
 * AdminUserMessage entity. @author MyEclipse Persistence Tools
 */
public class AdminUserMessage extends AbstractAdminUserMessage implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AdminUserMessage() {
	}

	/** full constructor */
	public AdminUserMessage(MessageCenter messageCenter, AdminUser adminUser,
			 Timestamp createTime, Boolean isValid) {
		super(messageCenter, adminUser, createTime, isValid);
	}

}
