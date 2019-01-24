package com.axp.model;

import java.sql.Timestamp;

/**
 * Userrecore entity. @author MyEclipse Persistence Tools
 */
public class Userrecore extends AbstractUserrecore implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Userrecore() {
	}

	/** minimal constructor */
	public Userrecore(Users users, Goods goods) {
		super(users, goods);
	}

	/** full constructor */
	public Userrecore(Users users, Goods goods, Integer openCount,
			Integer showCount, Boolean isvalid, Timestamp createtime) {
		super(users, goods, openCount, showCount, isvalid, createtime);
	}

}
