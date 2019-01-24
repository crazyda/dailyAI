package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractMembers entity provides the base persistence definition of the
 * Members entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMembers implements java.io.Serializable {

	// Fields

	private Integer id;
	private Users users;
	private MembersTypeIncome membersTypeIncome;
	private Double cash;
	private String inviteCode;//邀请码
	//private Integer pid;
	private Members parentMembers;
	private Boolean isActivate;//是否激活
	private Double realPayMoney;//实际缴纳的会员费
	private Timestamp createTime;
	private Timestamp lastTime;
	private Boolean isValid;
	private String alipayCode;

	// Constructors

	/** default constructor */
	public AbstractMembers() {
	}

	/** full constructor */
	public AbstractMembers(Users users, MembersTypeIncome membersTypeIncome,
			Double cash, String inviteCode, Integer pid, Boolean isActivate,Double realPayMoney,
			Timestamp createTime, Timestamp lastTime, Boolean isValid) {
		this.users = users;
		this.membersTypeIncome = membersTypeIncome;
		this.cash = cash;
		this.inviteCode = inviteCode;
		//this.pid = pid;
		this.isActivate = isActivate;
		this.realPayMoney = realPayMoney;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.isValid = isValid;
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

	public MembersTypeIncome getMembersTypeIncome() {
		return this.membersTypeIncome;
	}

	public void setMembersTypeIncome(MembersTypeIncome membersTypeIncome) {
		this.membersTypeIncome = membersTypeIncome;
	}

	public Double getCash() {
		return this.cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public String getInviteCode() {
		return this.inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
/*
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
*/
	public Boolean getIsActivate() {
		return this.isActivate;
	}

	public void setIsActivate(Boolean isActivate) {
		this.isActivate = isActivate;
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

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Double getRealPayMoney() {
		return realPayMoney;
	}

	public void setRealPayMoney(Double realPayMoney) {
		this.realPayMoney = realPayMoney;
	}

	public Members getParentMembers() {
		return parentMembers;
	}

	public void setParentMembers(Members parentMembers) {
		//this.pid = parentMembers.getId();
		this.parentMembers = parentMembers;
	}

	public String getAlipayCode() {
		return alipayCode;
	}

	public void setAlipayCode(String alipayCode) {
		this.alipayCode = alipayCode;
	}

	
}