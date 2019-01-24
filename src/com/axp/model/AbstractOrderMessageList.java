package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * AbstractOrderMessageList entity provides the base persistence definition of
 * the OrderMessageList entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOrderMessageList implements java.io.Serializable {

	// Fields
	
	private Integer id;
	private MessageType messageType;
	private String content;
	private Timestamp time;
	private Boolean isValid;
	private Timestamp createtime;
	private String title;
	private ReGoodsorder reGoodsorder;	
	private Users users;
	

	// Constructors

	/** default constructor */
	public AbstractOrderMessageList() {
	}

	/** full constructor */
	public AbstractOrderMessageList(MessageType messageType, String content,
			Timestamp time, Boolean isValid, Timestamp createtime,
			String title, ReGoodsorder reGoodsorder, Users users) {
		super();
		this.messageType = messageType;
		this.content = content;
		this.time = time;
		this.isValid = isValid;
		this.createtime = createtime;
		this.title = title;
		this.reGoodsorder = reGoodsorder;
		this.users = users;
	}

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public MessageType getMessageType() {
		return this.messageType;
	}

	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
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

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public ReGoodsorder getReGoodsorder() {
		return reGoodsorder;
	}

	public void setReGoodsorder(ReGoodsorder reGoodsorder) {
		this.reGoodsorder = reGoodsorder;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	
	
	
	

}