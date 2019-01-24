package com.axp.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Proxyinfos entity. @author MyEclipse Persistence Tools
 */
public class Proxyinfos extends AbstractProxyinfos implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Proxyinfos() {
	}

	/** minimal constructor */
	public Proxyinfos(Users users, Integer levelNum1, Integer levelNum2,
			Integer levelNum3, Boolean isvalid, Timestamp createtime) {
		super(users, levelNum1, levelNum2, levelNum3, isvalid, createtime);
	}

	/** full constructor */
	public Proxyinfos(Users users, Integer levelNum1, Integer levelNum2,
			Integer levelNum3, Boolean isvalid, Timestamp createtime,
			Set proxyuserses) {
		super(users, levelNum1, levelNum2, levelNum3, isvalid, createtime,
				proxyuserses);
	}

}
