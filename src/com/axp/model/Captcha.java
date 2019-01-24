package com.axp.model;

import java.sql.Timestamp;

/**
 * Buy entity. @author MyEclipse Persistence Tools
 */
public class Captcha extends AbstractCaptcha implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Captcha() {
	}

	/** full constructor */
	public Captcha(Integer buy, Integer sell, Integer status, Boolean isvalid,
			Timestamp createtime) {
		super();
	}

}
