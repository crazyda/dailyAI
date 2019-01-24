package com.axp.model;

import java.util.Date;

/**
 * AdminuserAccount entity. @author MyEclipse Persistence Tools
 */
public class AdminuserAccount extends AbstractAdminuserAccount implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public AdminuserAccount() {
	}

	/** full constructor */
	public AdminuserAccount(Integer adminuserId, double minMoney,
			double maxMoney, String alipayCode, String alipayName,
			Date createTime, boolean isValid) {
		super(adminuserId, minMoney, maxMoney, alipayCode, alipayName,
				createTime, isValid);
	}

}
