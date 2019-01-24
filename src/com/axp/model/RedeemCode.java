package com.axp.model;

import java.sql.Timestamp;

/**
 * Buy entity. @author MyEclipse Persistence Tools
 */
public class RedeemCode extends AbstractRedeemCode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedeemCode() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RedeemCode(Integer id, AgentProduct agentProduct, Users user,
			AdminUser adminUser, Boolean isValid, String redeemCode,
			Boolean isRead, AdminUser adminUserShouFen,
			AdminUser adminUserScore, Timestamp createTime, Boolean isVerify) {
		super(id, agentProduct, user, adminUser, isValid, redeemCode, isRead,
				adminUserShouFen, adminUserScore, createTime, isVerify);
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	
}
