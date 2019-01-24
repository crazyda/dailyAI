package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractCashpointsRecord entity provides the base persistence definition of
 * the CashpointsRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCashpointsRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private Users fromUsers;
	private Double beforeScore;
	private Double score;
	private Double afterScore;
	private String remark;
	private Boolean isValid;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Integer type;
	private Integer createUser;

	// Constructors

	/** default constructor */
	public AbstractCashpointsRecord() {
	}

	/** minimal constructor */
	public AbstractCashpointsRecord(Boolean isValid) {
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractCashpointsRecord(Users users, Double beforeScore, Double score, Double afterScore, String remark, Boolean isValid, Timestamp createTime, Timestamp lastTime, Integer type,
			Integer createUser) {
		this.users = users;
		this.beforeScore = beforeScore;
		this.score = score;
		this.afterScore = afterScore;
		this.remark = remark;
		this.isValid = isValid;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.type = type;
		this.createUser = createUser;
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

	public Double getBeforeScore() {
		return this.beforeScore;
	}

	public void setBeforeScore(Double beforeScore) {
		this.beforeScore = beforeScore;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getAfterScore() {
		return this.afterScore;
	}

	public void setAfterScore(Double afterScore) {
		this.afterScore = afterScore;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Users getFromUsers() {
		return fromUsers;
	}

	public void setFromUsers(Users fromUsers) {
		this.fromUsers = fromUsers;
	}

}