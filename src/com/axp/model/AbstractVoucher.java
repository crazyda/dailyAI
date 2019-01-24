package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractVoucher entity provides the base persistence definition of the
 * Voucher entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractVoucher implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String password;
	private String encryption;
	private Double faceValue;
	private String description;
	private Boolean isRecharge;
	private Boolean isValid;
	private Timestamp createTime;
	private Timestamp lastTime;
	private Integer status;
	private String imgUrl;
	private AdminUser assignUser;//如果为空值，则所有人都可以充值，否则，只有指定代理地区用户才能充值；
	private Set voucherRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractVoucher() {
	}

	/** minimal constructor */
	public AbstractVoucher(Boolean isRecharge, Boolean isValid) {
		this.isRecharge = isRecharge;
		this.isValid = isValid;
	}

	/** full constructor */
	public AbstractVoucher(String code, String password, String encryption,AdminUser assignUser, Double faceValue, String description, Boolean isRecharge, Boolean isValid, Timestamp createTime, Timestamp lastTime,
			Integer status, Set voucherRecords,String imgUrl) {
		this.code = code;
		this.password = password;
		this.encryption = encryption;
		this.faceValue = faceValue;
		this.description = description;
		this.isRecharge = isRecharge;
		this.isValid = isValid;
		this.createTime = createTime;
		this.lastTime = lastTime;
		this.status = status;
		this.voucherRecords = voucherRecords;
		this.imgUrl = imgUrl;
		this.assignUser = assignUser;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEncryption() {
		return this.encryption;
	}

	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

	public Double getFaceValue() {
		return this.faceValue;
	}

	public void setFaceValue(Double faceValue) {
		this.faceValue = faceValue;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsRecharge() {
		return this.isRecharge;
	}

	public void setIsRecharge(Boolean isRecharge) {
		this.isRecharge = isRecharge;
	}

	public Boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastTime() {
		return this.lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getVoucherRecords() {
		return this.voucherRecords;
	}

	public void setVoucherRecords(Set voucherRecords) {
		this.voucherRecords = voucherRecords;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public AdminUser getAssignUser() {
		return assignUser;
	}

	public void setAssignUser(AdminUser assignUser) {
		this.assignUser = assignUser;
	}


}