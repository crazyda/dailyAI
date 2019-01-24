package com.axp.model;

/**
 * Apply entity. @author MyEclipse Persistence Tools
 */
public class Apply extends AbstractApply implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Apply() {
	}

	/** full constructor */
	public Apply(Users users, String realname, Integer age, Integer sex,
			String address, String bduserid, String bdchannelid,
			Integer status, String reason) {
		super(users, realname, age, sex, address, bduserid, bdchannelid,
				status, reason);
	}

}
