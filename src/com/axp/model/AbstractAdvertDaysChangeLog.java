package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractAdvertDaysChangeLog entity provides the base persistence definition
 * of the AdvertDaysChangeLog entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdvertDaysChangeLog implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer userId;
	private AdminUser adminUser;
	private Integer parentId;
	private String userRealName;
	private Integer centerDayCounts;
	private Integer cityDayCounts;
	private Integer unionDaycounts;
	private Timestamp createTime;
	private String discript;

	// Constructors

	/** default constructor */
	public AbstractAdvertDaysChangeLog() {
	}

	/** minimal constructor */
	public AbstractAdvertDaysChangeLog(Integer id, Integer userId) {
		this.id = id;
		this.userId = userId;
	}

	/** full constructor */
	public AbstractAdvertDaysChangeLog(Integer id, Integer userId,
			Integer parentId, String userRealName, Integer centerDayCounts,
			Integer cityDayCounts, Integer unionDaycounts,
			Timestamp createTime, String discript) {
		this.id = id;
		this.userId = userId;
		this.parentId = parentId;
		this.userRealName = userRealName;
		this.centerDayCounts = centerDayCounts;
		this.cityDayCounts = cityDayCounts;
		this.unionDaycounts = unionDaycounts;
		this.createTime = createTime;
		this.discript = discript;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getUserRealName() {
		return this.userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public Integer getCenterDayCounts() {
		return this.centerDayCounts;
	}

	public void setCenterDayCounts(Integer centerDayCounts) {
		this.centerDayCounts = centerDayCounts;
	}

	public Integer getCityDayCounts() {
		return this.cityDayCounts;
	}

	public void setCityDayCounts(Integer cityDayCounts) {
		this.cityDayCounts = cityDayCounts;
	}

	public Integer getUnionDaycounts() {
		return this.unionDaycounts;
	}

	public void setUnionDaycounts(Integer unionDaycounts) {
		this.unionDaycounts = unionDaycounts;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getDiscript() {
		return this.discript;
	}

	public void setDiscript(String discript) {
		this.discript = discript;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

}