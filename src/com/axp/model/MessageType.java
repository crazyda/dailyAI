package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * MessageType entity. @author MyEclipse Persistence Tools
 */
public class MessageType extends AbstractMessageType implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public MessageType() {
	}

	/** full constructor */
	public MessageType(String icon, String title, String content,
			Timestamp time, Boolean isValid,Integer level,MessageType messageType) {
		super(icon, title, content, time, isValid,level,messageType);
	}

}
