package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractProxyDrawCash entity provides the base persistence definition of the
 * ProxyDrawCash entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProxyDrawCash implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private Boolean isValid;
	private Integer cashPoints;
	private Boolean isDraw;
	private Timestamp createTime;
	private Integer beforeScore;
	private Integer afterScore;
	private Integer lastUser;

	// Constructors

	/** default constructor */
	public AbstractProxyDrawCash() {
	}

	/** minimal constructor */
	public AbstractProxyDrawCash(Boolean isValid, Boolean isDraw) {
		this.isValid = isValid;
		this.isDraw = isDraw;
	}

	/** full constructor */
	public AbstractProxyDrawCash(AdminUser adminUser, Boolean isValid, Integer cashPoints, Boolean isDraw, Timestamp createTime, Integer beforeScore, Integer afterScore, Integer lastUser) {
		this.adminUser = adminUser;
		this.isValid = isValid;
		this.cashPoints = cashPoints;
		this.isDraw = isDraw;
		this.createTime = createTime;
		this.beforeScore = beforeScore;
		this.afterScore = afterScore;
		this.lastUser = lastUser;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getCashPoints() {
		return this.cashPoints;
	}

	public void setCashPoints(Integer cashPoints) {
		this.cashPoints = cashPoints;
	}

	public Boolean getIsDraw() {
		return this.isDraw;
	}

	public void setIsDraw(Boolean isDraw) {
		this.isDraw = isDraw;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getBeforeScore() {
		return this.beforeScore;
	}

	public void setBeforeScore(Integer beforeScore) {
		this.beforeScore = beforeScore;
	}

	public Integer getAfterScore() {
		return this.afterScore;
	}

	public void setAfterScore(Integer afterScore) {
		this.afterScore = afterScore;
	}

	public Integer getLastUser() {
		return this.lastUser;
	}

	public void setLastUser(Integer lastUser) {
		this.lastUser = lastUser;
	}

}