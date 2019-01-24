
package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractDLScoreMark implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9172207845289888771L;
	private Integer id;
	private AdminUser adminUser; //归属代理
	private Timestamp createTime;
	private Boolean isValid;
	private Integer scoreId;
	private AdminUser adminUserSeller;//持有商家
	private ScoreMark scoreMark;
	
	
	
	public AbstractDLScoreMark() {
		super();
	}
	
	public AbstractDLScoreMark(Integer id, AdminUser adminUser,
			Timestamp createTime, Boolean isValid, Integer scoreId,
			AdminUser adminUserSeller, ScoreMark scoreMark) {
		super();
		this.id = id;
		this.adminUser = adminUser;
		this.createTime = createTime;
		this.isValid = isValid;
		this.scoreId = scoreId;
		this.adminUserSeller = adminUserSeller;
		this.scoreMark = scoreMark;
	}

	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AdminUser getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public AdminUser getAdminUserSeller() {
		return adminUserSeller;
	}
	public void setAdminUserSeller(AdminUser adminUserSeller) {
		this.adminUserSeller = adminUserSeller;
	}
	public ScoreMark getScoreMark() {
		return scoreMark;
	}
	public void setScoreMark(ScoreMark scoreMark) {
		this.scoreMark = scoreMark;
	}
	
	
	
	
	
}
