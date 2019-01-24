package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractVisitor entity provides the base persistence definition of the
 * Visitor entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractVisitor implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String password;
	private String machineCode;
	private String inviteCode;
	private Integer parentId;
	private Integer score;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Boolean isValid;
	private AdminUser AdminUser;//多关注的联盟组
	private Seller parentSeller;

	// Constructors

	/** default constructor */
	public AbstractVisitor() {
	}

	/** full constructor */
	public AbstractVisitor(String name, String password, String machineCode, String inviteCode, Integer parentId, Integer score, Timestamp createTime, Timestamp lastTime, Boolean isValid) {
		this.name = name;
		this.password = password;
		this.machineCode = machineCode;
		this.inviteCode = inviteCode;
		this.parentId = parentId;
		this.score = score;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public AdminUser getAdminUser() {
		return AdminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		AdminUser = adminUser;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMachineCode() {
		return this.machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public String getInviteCode() {
		return this.inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

	public Seller getParentSeller() {
		return parentSeller;
	}

	public void setParentSeller(Seller parentSeller) {
		this.parentSeller = parentSeller;
	}

}