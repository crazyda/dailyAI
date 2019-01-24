package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * MessageCenter entity. @author MyEclipse Persistence Tools
 */
public class MessageCenter extends AbstractMessageCenter implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public MessageCenter() {
	}

	/** full constructor */
	public MessageCenter(String title, String cover, String content, Timestamp createTime, Boolean isValid, String author,Integer type, String remark,Set adminUserMessages,Integer checkStatus,Integer centerStatus,Integer isTimer,Timestamp fixTime,AdminUser adminUser) {
		super(title, cover, content, createTime, isValid, author, type, remark,adminUserMessages,checkStatus,centerStatus,isTimer,fixTime,adminUser);
	}

}
