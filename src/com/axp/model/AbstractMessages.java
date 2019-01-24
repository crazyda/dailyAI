package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractMessages entity provides the base persistence definition of the
 * Messages entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMessages implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users usersByUserId;
	private Users usersByFromUserId;
	private String content;
	private Boolean isvalid;
	private Timestamp createtime;
	private String title;

	// Constructors

	/** default constructor */
	public AbstractMessages() {
	}

	/** full constructor */
	public AbstractMessages(Users usersByUserId, Users usersByFromUserId,
			String content, Boolean isvalid, Timestamp createtime, String title) {
		this.usersByUserId = usersByUserId;
		this.usersByFromUserId = usersByFromUserId;
		this.content = content;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.title = title;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsersByUserId() {
		return this.usersByUserId;
	}

	public void setUsersByUserId(Users usersByUserId) {
		this.usersByUserId = usersByUserId;
	}

	public Users getUsersByFromUserId() {
		return this.usersByFromUserId;
	}

	public void setUsersByFromUserId(Users usersByFromUserId) {
		this.usersByFromUserId = usersByFromUserId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}