package com.axp.model;

import java.sql.Timestamp;

/**
 * Buy entity. @author MyEclipse Persistence Tools
 */
public class AgentProduct extends AbstractAgentProduct  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AgentProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AgentProduct(Integer id, Timestamp createTime, Product product,
			AdminUser adminUser, Boolean isValid, Branks brank,
			AgentProduct agentProduct, String redeemCode, Users redeemCodeUser) {
		super(id, createTime, product, adminUser, isValid, brank, agentProduct,
				redeemCode, redeemCodeUser);
		// TODO Auto-generated constructor stub
	}

	
	
	


	



}
