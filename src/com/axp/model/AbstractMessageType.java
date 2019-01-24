package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractMessageType entity provides the base persistence definition of the
 * MessageType entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMessageType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String icon;
	private String title;
	private String content;
	private Timestamp time;
	private Boolean isValid;
	private Integer level;
	private MessageType messageType;
	private Integer isorder;

	// Constructors

	/** default constructor */
	public AbstractMessageType() {
	}

	/** full constructor */
	public AbstractMessageType(String icon, String title, String content,
			Timestamp time,Boolean isValid,Integer level,MessageType messageType) {
		this.icon = icon;
		this.title = title;
		this.content = content;
		this.time = time;
		this.isValid = isValid;
		this.level=level;
		this.messageType = messageType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	public Integer getIsorder() {
		return isorder;
	}

	public void setIsorder(Integer isorder) {
		this.isorder = isorder;
	}
	
	
	
}