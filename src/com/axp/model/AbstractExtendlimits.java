package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractExtendlimits entity provides the base persistence definition of the
 * Extendlimits entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractExtendlimits implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer num;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer perAdverscore;
	private Integer perMoneyscore;
	private Integer perShowscore;

	// Constructors

	/** default constructor */
	public AbstractExtendlimits() {
	}

	/** full constructor */
	public AbstractExtendlimits(Integer num, Boolean isvalid,
			Timestamp createtime, Integer perAdverscore, Integer perMoneyscore,
			Integer perShowscore) {
		this.num = num;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.perAdverscore = perAdverscore;
		this.perMoneyscore = perMoneyscore;
		this.perShowscore = perShowscore;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	public Integer getPerAdverscore() {
		return this.perAdverscore;
	}

	public void setPerAdverscore(Integer perAdverscore) {
		this.perAdverscore = perAdverscore;
	}

	public Integer getPerMoneyscore() {
		return this.perMoneyscore;
	}

	public void setPerMoneyscore(Integer perMoneyscore) {
		this.perMoneyscore = perMoneyscore;
	}

	public Integer getPerShowscore() {
		return this.perShowscore;
	}

	public void setPerShowscore(Integer perShowscore) {
		this.perShowscore = perShowscore;
	}

}