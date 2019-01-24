package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * SystemMessageList entity. @author MyEclipse Persistence Tools
 */
public class SystemMessageList extends AbstractSystemMessageList implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SystemMessageList() {
	}

	/** full constructor */
	public SystemMessageList(MessageType messageType, String content,Integer moneyState,Double money,
			Timestamp time, Boolean isValid, Timestamp createtime,String title,Users users) {
		super(messageType, content, money, moneyState, time, isValid, createtime, title, users);
	}

}
