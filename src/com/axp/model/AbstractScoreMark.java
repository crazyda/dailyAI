
package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractScoreMark implements java.io.Serializable {
	
	private Integer id;
	private AdminUser adminUser;
	private Timestamp createTime;
	private Boolean isValid;
	private String remark;//积分流向 总部-代理-合伙人-商家-粉丝 一圈后就清空该字段
	private String newHands;//目前在谁手上
	private Timestamp validityTime;//有效期时间
	private Timestamp refreshTime;//更新时间
	private Integer scoreId;
	private AdminUser adminUserDL;
	
	
	
	
	public AbstractScoreMark() {
		super();
	}

	
	
	
	public AbstractScoreMark(Integer id, AdminUser adminUser,
			Timestamp createTime, Boolean isValid, String remark,
			String newHands, Timestamp validityTime, Timestamp refreshTime,
			Integer scoreId, AdminUser adminUserDL) {
		super();
		this.id = id;
		this.adminUser = adminUser;
		this.createTime = createTime;
		this.isValid = isValid;
		this.remark = remark;
		this.newHands = newHands;
		this.validityTime = validityTime;
		this.refreshTime = refreshTime;
		this.scoreId = scoreId;
		this.adminUserDL = adminUserDL;
	}




	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public Timestamp getRefreshTime() {
		return refreshTime;
	}
	public void setRefreshTime(Timestamp refreshTime) {
		this.refreshTime = refreshTime;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNewHands() {
		return newHands;
	}
	public void setNewHands(String newHands) {
		this.newHands = newHands;
	}
	public Timestamp getValidityTime() {
		return validityTime;
	}
	public void setValidityTime(Timestamp validityTime) {
		this.validityTime = validityTime;
	}




	public AdminUser getAdminUserDL() {
		return adminUserDL;
	}




	public void setAdminUserDL(AdminUser adminUserDL) {
		this.adminUserDL = adminUserDL;
	}
	
	
	
	
	
}
