package com.axp.model;

import java.sql.Timestamp;

/**
 * Proxyusers entity. @author MyEclipse Persistence Tools
 */
public class Proxyusers extends AbstractProxyusers implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Proxyusers() {
	}

	/** full constructor */
	public Proxyusers(Users users, Proxyinfos proxyinfos, Levels levels,
			Boolean isvalid, Timestamp createtime) {
		super(users, proxyinfos, levels, isvalid, createtime);
	}

}
