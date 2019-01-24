package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AbstractAgentProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Timestamp createTime; //创建时间
	private Product product; //产品信息
	private AdminUser adminUser; //代理或者合伙人
	private Boolean isValid;
	private Branks brank; //银行
	private AgentProduct agentProduct; //合伙人获取的代理产品信息
	private String redeemCode ; //兑换码
	private Users redeemCodeUser; //兑换用户
	
	
	public AbstractAgentProduct() {
		super();
	}
	

	public AbstractAgentProduct(Integer id, Timestamp createTime,
			Product product, AdminUser adminUser, Boolean isValid,
			Branks brank, AgentProduct agentProduct, String redeemCode,
			Users redeemCodeUser) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.product = product;
		this.adminUser = adminUser;
		this.isValid = isValid;
		this.brank = brank;
		this.agentProduct = agentProduct;
		this.redeemCode = redeemCode;
		this.redeemCodeUser = redeemCodeUser;
	}


	public Users getRedeemCodeUser() {
		return redeemCodeUser;
	}


	public void setRedeemCodeUser(Users redeemCodeUser) {
		this.redeemCodeUser = redeemCodeUser;
	}


	public String getRedeemCode() {
		return redeemCode;
	}


	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}


	public AgentProduct getAgentProduct() {
		return agentProduct;
	}







	public void setAgentProduct(AgentProduct agentProduct) {
		this.agentProduct = agentProduct;
	}







	public Branks getBrank() {
		return brank;
	}



	public void setBrank(Branks brank) {
		this.brank = brank;
	}



	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public AdminUser getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	
	
	
}
