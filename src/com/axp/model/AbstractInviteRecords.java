package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractInviteRecords entity provides the base persistence definition of the
 * InviteRecords entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractInviteRecords implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users usersByUserId;
	private Users usersByInviteUserId;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer score;

	// Constructors

	/** default constructor */
	public AbstractInviteRecords() {
	}

	/** full constructor */
	public AbstractInviteRecords(Users usersByUserId,
			Users usersByInviteUserId, Boolean isvalid, Timestamp createtime,
			Integer score) {
		this.usersByUserId = usersByUserId;
		this.usersByInviteUserId = usersByInviteUserId;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.score = score;
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

	public Users getUsersByInviteUserId() {
		return this.usersByInviteUserId;
	}

	public void setUsersByInviteUserId(Users usersByInviteUserId) {
		this.usersByInviteUserId = usersByInviteUserId;
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

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}