
package com.axp.model;

import java.sql.Timestamp;

public abstract class AbstractUserScoreMark implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9172207845289888771L;
	private Integer id;
	private AdminUser adminUser;//归属商家
	private Timestamp createTime;
	private Boolean isValid;
	private Integer scoreId;
	private SJScoreMark sjScoreMark; //对应的商家积分记录
	private Users users; //持有用户
	private Timestamp validityTime;//有效期时间
	private Timestamp refreshTime;//更新时间
	
	public AbstractUserScoreMark() {
		super();
	}
	
	




	public AbstractUserScoreMark(Integer id, AdminUser adminUser,
			Timestamp createTime, Boolean isValid, Integer scoreId,
			SJScoreMark sjScoreMark, Users users, Timestamp validityTime,
			Timestamp refreshTime) {
		super();
		this.id = id;
		this.adminUser = adminUser;
		this.createTime = createTime;
		this.isValid = isValid;
		this.scoreId = scoreId;
		this.sjScoreMark = sjScoreMark;
		this.users = users;
		this.validityTime = validityTime;
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
	public Timestamp getValidityTime() {
		return validityTime;
	}
	public void setValidityTime(Timestamp validityTime) {
		this.validityTime = validityTime;
	}



	public SJScoreMark getSjScoreMark() {
		return sjScoreMark;
	}



	public void setSjScoreMark(SJScoreMark sjScoreMark) {
		this.sjScoreMark = sjScoreMark;
	}
	
	
	
	
}
