package com.axp.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * AbstractFreeVoucherRecord entity provides the base persistence definition of
 * the FreeVoucherRecord entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractFreeVoucherRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private FreeVoucher freeVoucher;
	private Users users;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp createTime;
	private Boolean isValid;
	private Integer status;
	private UserShoppingcar userShoppingcar;
	
	private String voucherName;

	// Constructors

	/** default constructor */
	public AbstractFreeVoucherRecord() {
	}

	/** full constructor */
	public AbstractFreeVoucherRecord(FreeVoucher freeVoucher, Users users,
			Timestamp startTime, Timestamp endTime, Timestamp createTime,
			Boolean isValid) {
		this.freeVoucher = freeVoucher;
		this.users = users;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
		this.isValid = isValid;
	}

	// Property accessors
	@JSONField(name="freeVoucherId")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@JSONField(serialize=false)
	public FreeVoucher getFreeVoucher() {
		return this.freeVoucher;
	}

	public void setFreeVoucher(FreeVoucher freeVoucher) {
		this.freeVoucher = freeVoucher;
	}
	@JSONField(serialize=false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}
	@JSONField(serialize=false)
	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss")  
	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	@JSONField (format="yyyy-MM-dd HH:mm:ss")  
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@JSONField(serialize=false)
	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public UserShoppingcar getUserShoppingcar() {
		return userShoppingcar;
	}

	public void setUserShoppingcar(UserShoppingcar userShoppingcar) {
		this.userShoppingcar = userShoppingcar;
	}

}