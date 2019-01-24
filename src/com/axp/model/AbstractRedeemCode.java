package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AbstractRedeemCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id ;
	private AgentProduct agentProduct;
	private Users user;
	private AdminUser adminUser ;//合伙人
	private Boolean isValid;
	private String redeemCode;//兑换码
	private Boolean isRead ;//收分用户是否查看
	private AdminUser adminUserShouFen;//收分用户
	private AdminUser adminUserScore;//送积分商家
	private Timestamp createTime;
	private Boolean isVerify; //审核状态
	
	public AbstractRedeemCode() {
		super();
	}
	
	

	public AbstractRedeemCode(Integer id, AgentProduct agentProduct,
			Users user, AdminUser adminUser, Boolean isValid,
			String redeemCode, Boolean isRead, AdminUser adminUserShouFen,
			AdminUser adminUserScore, Timestamp createTime, Boolean isVerify) {
		super();
		this.id = id;
		this.agentProduct = agentProduct;
		this.user = user;
		this.adminUser = adminUser;
		this.isValid = isValid;
		this.redeemCode = redeemCode;
		this.isRead = isRead;
		this.adminUserShouFen = adminUserShouFen;
		this.adminUserScore = adminUserScore;
		this.createTime = createTime;
		this.isVerify = isVerify;
	}



	public Boolean getIsVerify() {
		return isVerify;
	}



	public void setIsVerify(Boolean isVerify) {
		this.isVerify = isVerify;
	}



	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AgentProduct getAgentProduct() {
		return agentProduct;
	}
	public void setAgentProduct(AgentProduct agentProduct) {
		this.agentProduct = agentProduct;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public AdminUser getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public String getRedeemCode() {
		return redeemCode;
	}
	public void setRedeemCode(String redeemCode) {
		this.redeemCode = redeemCode;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	public AdminUser getAdminUserShouFen() {
		return adminUserShouFen;
	}
	public void setAdminUserShouFen(AdminUser adminUserShouFen) {
		this.adminUserShouFen = adminUserShouFen;
	}
	public AdminUser getAdminUserScore() {
		return adminUserScore;
	}
	public void setAdminUserScore(AdminUser adminUserScore) {
		this.adminUserScore = adminUserScore;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
}
