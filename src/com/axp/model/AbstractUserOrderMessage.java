package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserOrderMessage entity provides the base persistence definition of
 * the UserOrderMessage entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserOrderMessage implements java.io.Serializable {

	// Fields

	private Integer id;
	private OrderMessageList OrderMessageLists;
	private Integer isRead;
	private Timestamp readtime;	
	private Boolean isValid;
	private Timestamp createtime;
	private Users users;
	private MessageType messageType;

	// Constructors

	/** default constructor */
	public AbstractUserOrderMessage() {
	}

	/** full constructor */
	public AbstractUserOrderMessage(OrderMessageList orderMessageLists,
			Integer isRead, Timestamp readtime, Boolean isValid,
			Timestamp createtime, Users users, MessageType messageType) {
		super();
		OrderMessageLists = orderMessageLists;
		this.isRead = isRead;
		this.readtime = readtime;
		this.isValid = isValid;
		this.createtime = createtime;
		this.users = users;
		this.messageType = messageType;
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

	public OrderMessageList getOrderMessageLists() {
		return OrderMessageLists;
	}

	public void setOrderMessageLists(OrderMessageList orderMessageLists) {
		OrderMessageLists = orderMessageLists;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	
	

}