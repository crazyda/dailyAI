package com.axp.model;

import java.sql.Timestamp;

/**
 * TemporaryCaptcha entity. @author MyEclipse Persistence Tools
 */
public class TemporaryCaptcha extends AbstractTemporaryCaptcha implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TemporaryCaptcha() {
	}

	/** full constructor */
	public TemporaryCaptcha(String captcha, String name, Boolean isEffect, Timestamp createTime, Boolean isValid) {
		super(captcha, name, isEffect, createTime, isValid);
	}

}
