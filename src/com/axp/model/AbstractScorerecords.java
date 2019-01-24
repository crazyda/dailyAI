package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractScorerecords entity provides the base persistence definition of the
 * Scorerecords entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractScorerecords implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Integer beforeScore;
	private Integer score;
	private Integer afterScore;
	private String remark;
	private Integer foreignId;
	private Integer type;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer adminuserId;
	private String scoretype;
	
	private Timestamp validityTime;
	private Integer surplusScore;
	private String isUserTimes;

	// Constructors

	public Timestamp getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(Timestamp validityTime) {
		this.validityTime = validityTime;
	}

	public Integer getSurplusScore() {
		return surplusScore;
	}

	public void setSurplusScore(Integer surplusScore) {
		this.surplusScore = surplusScore;
	}

	public String getIsUserTimes() {
		return isUserTimes;
	}

	public void setIsUserTimes(String isUserTimes) {
		this.isUserTimes = isUserTimes;
	}

	/** default constructor */
	public AbstractScorerecords() {
	}

	/** minimal constructor */
	public AbstractScorerecords(Users users, Integer score, Boolean isvalid,
			Timestamp createtime, Integer adminuserId, String scoretype) {
		this.users = users;
		this.score = score;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.adminuserId = adminuserId;
		this.scoretype = scoretype;
	}

	/** full constructor */
	public AbstractScorerecords(Users users, Integer beforeScore,
			Integer score, Integer afterScore, String remark,
			Integer foreignId, Integer type, Boolean isvalid,
			Timestamp createtime, Integer adminuserId, String scoretype) {
		this.users = users;
		this.beforeScore = beforeScore;
		this.score = score;
		this.afterScore = afterScore;
		this.remark = remark;
		this.foreignId = foreignId;
		this.type = type;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.adminuserId = adminuserId;
		this.scoretype = scoretype;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getBeforeScore() {
		return this.beforeScore;
	}

	public void setBeforeScore(Integer beforeScore) {
		this.beforeScore = beforeScore;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getAfterScore() {
		return this.afterScore;
	}

	public void setAfterScore(Integer afterScore) {
		this.afterScore = afterScore;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getForeignId() {
		return this.foreignId;
	}

	public void setForeignId(Integer foreignId) {
		this.foreignId = foreignId;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Integer getAdminuserId() {
		return this.adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}

	public String getScoretype() {
		return this.scoretype;
	}

	public void setScoretype(String scoretype) {
		this.scoretype = scoretype;
	}

}