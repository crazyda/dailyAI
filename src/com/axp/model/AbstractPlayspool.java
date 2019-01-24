package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractPlayspool entity provides the base persistence definition of the
 * Playspool entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPlayspool implements java.io.Serializable {

	// Fields

	private Integer id;
	private Plays plays;
	private Timestamp starttime;
	private Timestamp endtime;
	private Timestamp createtime;
	private Boolean isvalid;

	// Constructors

	/** default constructor */
	public AbstractPlayspool() {
	}

	/** full constructor */
	public AbstractPlayspool(Plays plays, Timestamp starttime,
			Timestamp endtime, Timestamp createtime, Boolean isvalid) {
		this.plays = plays;
		this.starttime = starttime;
		this.endtime = endtime;
		this.createtime = createtime;
		this.isvalid = isvalid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Plays getPlays() {
		return this.plays;
	}

	public void setPlays(Plays plays) {
		this.plays = plays;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public Boolean getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}

}