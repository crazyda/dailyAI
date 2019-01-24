package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractPartnerInform entity provides the base persistence definition of the
 * PartnerInform entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPartnerInform implements java.io.Serializable {

	// Fields

	public AbstractPartnerInform() {
		
	}

	public AbstractPartnerInform(Integer id, Users users, Users causeUsers, String remark, Boolean isValid,
			Timestamp createtime,Integer level,Integer mode) {
		super();
		this.id = id;
		this.users = users;
		this.causeUsers = causeUsers;
		this.remark = remark;
		this.isValid = isValid;
		this.createtime = createtime;
		this.level = level;
		this.mode = mode;
	}

	private Integer id;
	private Users users;
	private Users causeUsers;
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Users getCauseUsers() {
		return causeUsers;
	}

	public void setCauseUsers(Users causeUsers) {
		this.causeUsers = causeUsers;
	}

	private String remark;
	private Boolean isValid;
	private Timestamp createtime;
	private Integer level;//3为合伙人，2为高级合伙人
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	private Integer mode;//1为新模式；0为旧模式

	// Constructors


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

}