package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractBuy entity provides the base persistence definition of the Buy
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractCaptcha implements java.io.Serializable {

	// Fields

	private Integer id;
	
	private String phone;
	private String code;
	private Boolean isvalid;
	private Timestamp createtime;
	

	// Constructors

	/** default constructor */
	public AbstractCaptcha() {
	}



	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}

	

}