package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractStatisticsBase entity provides the base persistence definition of the
 * StatisticsBase entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStatisticsBase implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer score;
	private Integer type;
	private Timestamp createTime;
	private Boolean isValid;

	// Constructors

	/** default constructor */
	public AbstractStatisticsBase() {
	}

	/** full constructor */
	public AbstractStatisticsBase(String name, Integer score, Integer type,
			Timestamp createTime, Boolean isValid) {
		this.name = name;
		this.score = score;
		this.type = type;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
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