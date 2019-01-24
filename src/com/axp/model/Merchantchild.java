package com.axp.model;

import java.sql.Timestamp;

/**
 * Merchantchild entity. @author MyEclipse Persistence Tools
 */
public class Merchantchild extends AbstractMerchantchild implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Merchantchild() {
	}

	/** minimal constructor */
	public Merchantchild(Users users, String account, String pwd,
			Integer level, String levelname, Boolean isvalid) {
		super(users, account, pwd, level, levelname, isvalid);
	}

	/** full constructor */
	public Merchantchild(Users users, String account, String pwd,
			Integer level, String levelname, Timestamp createtime,
			Timestamp lasttime, String fansids, Boolean isvalid) {
		super(users, account, pwd, level, levelname, createtime, lasttime,
				fansids, isvalid);
	}

}
