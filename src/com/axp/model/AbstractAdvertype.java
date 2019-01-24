package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdvertype entity provides the base persistence definition of the
 * Advertype entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdvertype implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer type;
	private Integer coin;
	private Integer validity;
	private Boolean isvalid;
	private Timestamp createtime;
	private Integer pool;

	// Constructors

	/** default constructor */
	public AbstractAdvertype() {
	}

	/** full constructor */
	public AbstractAdvertype(Integer type, Integer coin, Integer validity,
			Boolean isvalid, Timestamp createtime, Integer pool) {
		this.type = type;
		this.coin = coin;
		this.validity = validity;
		this.isvalid = isvalid;
		this.createtime = createtime;
		this.pool = pool;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCoin() {
		return this.coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}

	public Integer getValidity() {
		return this.validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
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

	public Integer getPool() {
		return this.pool;
	}

	public void setPool(Integer pool) {
		this.pool = pool;
	}

}