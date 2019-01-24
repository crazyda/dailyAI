package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.axp.util.JsonDescript;

/**
 * AbstractRebate entity provides the base persistence definition of the Rebate
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRebate implements java.io.Serializable {

	// Fields

	private Integer id;
	private AdminUser adminUser;
	private String title;
	private String remark;
	private Double budget;
	private Double orderPoint;
	private Integer status;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp createTime;
	private Boolean isValid;
	private Set rebateUsersRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractRebate() {
	}

	/** full constructor */
	public AbstractRebate(AdminUser adminUser, String title, String remark,
			Double budget, Double orderPoint, Integer status,
			Timestamp startTime, Timestamp endTime, Timestamp createTime,
			Boolean isValid, Set rebateUsersRecords) {
		this.adminUser = adminUser;
		this.title = title;
		this.remark = remark;
		this.budget = budget;
		this.orderPoint = orderPoint;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.isValid = isValid;
		this.rebateUsersRecords = rebateUsersRecords;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getBudget() {
		return this.budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Double getOrderPoint() {
		return this.orderPoint;
	}

	public void setOrderPoint(Double orderPoint) {
		this.orderPoint = orderPoint;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	@JsonDescript(isShow=true,dateFormat="yyyy-MM-dd HH:mm:ss")
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	@JsonDescript(isShow=true,dateFormat="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Set getRebateUsersRecords() {
		return this.rebateUsersRecords;
	}

	public void setRebateUsersRecords(Set rebateUsersRecords) {
		this.rebateUsersRecords = rebateUsersRecords;
	}

}