package com.axp.model;

import java.sql.Timestamp;

/**
 * AbstractUserShoppingcarBack entity provides the base persistence definition
 * of the UserShoppingcarBack entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserShoppingcarBack implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private UserShoppingcar userShoppingcar;
	private AdminUser adminUser;
	private Users users;
	private String reason;
	private String reasonImgUrl;
	private Timestamp backTime;
	private Integer status;
	private String checkPerson;
	private String checkStr;
	private String remark;
	private Boolean isValid;
	private Timestamp createTime;
	private Double backMoney;
	private Integer refundWay;

	// Constructors

	/** default constructor */
	public AbstractUserShoppingcarBack() {
	}

	/** full constructor */
	public AbstractUserShoppingcarBack(UserShoppingcar userShoppingcar,
			AdminUser adminUser, Users users, String reason,
			String reasonImgUrl, Timestamp backTime, Integer status,
			String checkPerson, String checkStr,Boolean isValid,
			Timestamp createTime,String remark,Double backMoney) {
		this.userShoppingcar = userShoppingcar;
		this.adminUser = adminUser;
		this.users = users;
		this.reason = reason;
		this.reasonImgUrl = reasonImgUrl;
		this.backTime = backTime;
		this.status = status;
		this.checkPerson = checkPerson;
		this.checkStr = checkStr;
		this.isValid = isValid;
		this.createTime = createTime;
		this.remark=remark;
		this.backMoney=backMoney;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public UserShoppingcar getUserShoppingcar() {
		return this.userShoppingcar;
	}

	public void setUserShoppingcar(UserShoppingcar userShoppingcar) {
		this.userShoppingcar = userShoppingcar;
	}

	public AdminUser getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReasonImgUrl() {
		return this.reasonImgUrl;
	}

	public void setReasonImgUrl(String reasonImgUrl) {
		this.reasonImgUrl = reasonImgUrl;
	}

	public Timestamp getBackTime() {
		return this.backTime;
	}

	public void setBackTime(Timestamp backTime) {
		this.backTime = backTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCheckPerson() {
		return this.checkPerson;
	}

	public void setCheckPerson(String checkPerson) {
		this.checkPerson = checkPerson;
	}

	public String getCheckStr() {
		return this.checkStr;
	}

	public void setCheckStr(String checkStr) {
		this.checkStr = checkStr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(Double backMoney) {
		this.backMoney = backMoney;
	}

	public Integer getRefundWay() {
		return refundWay;
	}

	public void setRefundWay(Integer refundWay) {
		this.refundWay = refundWay;
	}
	
}