package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractPromoterTimeLimit entity provides the base persistence definition of
 * the PromoterTimeLimit entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPromoterTimeLimit implements java.io.Serializable {

	// Fields

	private Integer id;
	private Promoter promoter;
	private Timestamp endTime1;
	private Timestamp endTime2;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractPromoterTimeLimit() {
	}

	/** full constructor */
	public AbstractPromoterTimeLimit(Promoter promoter, Timestamp endTime1,
			Timestamp endTime2, Timestamp createTime, Boolean isValid) {
		this.promoter = promoter;
		this.endTime1 = endTime1;
		this.endTime2 = endTime2;
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

	public Promoter getPromoter() {
		return this.promoter;
	}

	public void setPromoter(Promoter promoter) {
		this.promoter = promoter;
	}

	public Timestamp getEndTime1() {
		return this.endTime1;
	}

	public void setEndTime1(Timestamp endTime1) {
		this.endTime1 = endTime1;
	}

	public Timestamp getEndTime2() {
		return this.endTime2;
	}

	public void setEndTime2(Timestamp endTime2) {
		this.endTime2 = endTime2;
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

}