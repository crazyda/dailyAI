package com.axp.model;

import java.sql.Timestamp;

/**
 * UserOrderMessage entity. @author MyEclipse Persistence Tools
 */
public class UserOrderMessage extends AbstractUserOrderMessage implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserOrderMessage() {
	}

	/** full constructor */
	public UserOrderMessage(OrderMessageList orderMessageLists,
			Integer isRead, Timestamp readtime, Boolean isValid,
			Timestamp createtime, Users users, MessageType messageType){
		super(orderMessageLists, isRead, readtime, isValid,
				createtime, users, messageType);
	}

}
