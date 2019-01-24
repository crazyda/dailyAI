package com.axp.model;

import java.sql.Timestamp;

/**
 * ProxyDrawCash entity. @author MyEclipse Persistence Tools
 */
public class ProxyDrawCash extends AbstractProxyDrawCash implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ProxyDrawCash() {
	}

	/** minimal constructor */
	public ProxyDrawCash(Boolean isValid, Boolean isDraw) {
		super(isValid, isDraw);
	}

	/** full constructor */
	public ProxyDrawCash(AdminUser adminUser, Boolean isValid, Integer cashPoints, Boolean isDraw, Timestamp createTime, Integer beforeScore, Integer afterScore, Integer lastUser) {
		super(adminUser, isValid, cashPoints, isDraw, createTime, beforeScore, afterScore, lastUser);
	}

}
