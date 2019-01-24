package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractPromoterMode entity provides the base persistence definition of the
 * PromoterMode entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPromoterMode implements java.io.Serializable {

	// Fields

	private Integer id;
	private PromoterMode promoterMode;
	private String name;
	private String descript;
	private Integer days;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Boolean isValid;
	private Set promoterModes = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractPromoterMode() {
	}

	/** full constructor */
	public AbstractPromoterMode(PromoterMode promoterMode, String name, String descript, Integer days, Timestamp createTime, Timestamp lastTime, Boolean isValid, Set promoterModes) {
		this.promoterMode = promoterMode;
		this.name = name;
		this.descript = descript;
		this.days = days;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.isValid = isValid;
		this.promoterModes = promoterModes;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PromoterMode getPromoterMode() {
		return this.promoterMode;
	}

	public void setPromoterMode(PromoterMode promoterMode) {
		this.promoterMode = promoterMode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Integer getDays() {
		return this.days;
	}

	public void setDays(Integer days) {
		this.days = days;
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

	public Set getPromoterModes() {
		return this.promoterModes;
	}

	public void setPromoterModes(Set promoterModes) {
		this.promoterModes = promoterModes;
	}

}