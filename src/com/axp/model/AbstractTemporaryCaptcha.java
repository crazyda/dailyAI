package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractTemporaryCaptcha entity provides the base persistence definition of
 * the TemporaryCaptcha entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTemporaryCaptcha implements java.io.Serializable {

	// Fields

	private Integer id;
	private String captcha;
	private String name;
	private Boolean isEffect;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractTemporaryCaptcha() {
	}

	/** full constructor */
	public AbstractTemporaryCaptcha(String captcha, String name, Boolean isEffect, Timestamp createTime, Boolean isValid) {
		this.captcha = captcha;
		this.name = name;
		this.isEffect = isEffect;
		this.createTime = createTime;
		this.isValid = isValid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCaptcha() {
		return this.captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsEffect() {
		return this.isEffect;
	}

	public void setIsEffect(Boolean isEffect) {
		this.isEffect = isEffect;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

}