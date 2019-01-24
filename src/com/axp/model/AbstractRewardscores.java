package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractRewardscores entity provides the base persistence definition of the
 * Rewardscores entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRewardscores implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer level;
	private Integer score;
	private Boolean isvalid;
	private Timestamp createtime;

	// Constructors

	/** default constructor */
	public AbstractRewardscores() {
	}

	/** full constructor */
	public AbstractRewardscores(Integer level, Integer score, Boolean isvalid,
			Timestamp createtime) {
		this.level = level;
		this.score = score;
		this.isvalid = isvalid;
		this.createtime = createtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
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

}