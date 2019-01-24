package com.axp.model;

import java.sql.Timestamp;

/**
 * PartnerInform entity. @author MyEclipse Persistence Tools
 */
public class PartnerInform extends AbstractPartnerInform implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public PartnerInform() {
	}

	public PartnerInform(Integer id, Users users, Users causeUsers, String remark, Boolean isValid,
			Timestamp createtime,Integer level,Integer mode) {
		super(id, users, causeUsers, remark, isValid, createtime,level,mode);
		// TODO Auto-generated constructor stub
	}


}
