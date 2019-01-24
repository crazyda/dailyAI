
package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractSJScoreMark implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9172207845289888771L;
	private Integer id;
	private AdminUser adminUser;//归属商家
	private Timestamp createTime;
	private Boolean isValid;
	private Integer scoreId;
	private DLScoreMark dlScoreMark;//对应的代理积分记录
	private Users users; //持有用户
	private Timestamp refreshTime;//更新时间
	
	
	
	public AbstractSJScoreMark() {
		super();
	}
	
	

	public AbstractSJScoreMark(Integer id, AdminUser adminUser,
			Timestamp createTime, Boolean isValid, Integer scoreId,
			DLScoreMark dlScoreMark, Users users, Timestamp refreshTime) {
		super();
		this.id = id;
		this.adminUser = adminUser;
		this.createTime = createTime;
		this.isValid = isValid;
		this.scoreId = scoreId;
		this.dlScoreMark = dlScoreMark;
		this.users = users;
		this.refreshTime = refreshTime;
	}



	public Timestamp getRefreshTime() {
		return refreshTime;
	}



	public void setRefreshTime(Timestamp refreshTime) {
		this.refreshTime = refreshTime;
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
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}

	public DLScoreMark getDlScoreMark() {
		return dlScoreMark;
	}

	public void setDlScoreMark(DLScoreMark dlScoreMark) {
		this.dlScoreMark = dlScoreMark;
	}
	
	
	
	
}
