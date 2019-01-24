package com.axp.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractAdminUser entity provides the base persistence definition of the
 * AdminUser entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAdminuserWithdrawalsBank implements java.io.Serializable {

	// Fields
	private Integer id;
	private AdminUser adminUser;
	private String bankAddress;
	private String cardNo;
	private Integer adminuserId;
	private Boolean isValid;
	private Boolean isDefault;
	private double counterFee;
	private String bankName;
	private Timestamp createtime;
	
	// Constructors

	/** default constructor */
	public AbstractAdminuserWithdrawalsBank() {
	}


	/** full constructor */
	public AbstractAdminuserWithdrawalsBank(Integer id,
			Integer adminuserId,
			AdminUser adminUser,
			String bankAddress,
			String cardNo,
			String bankName,
			Boolean isValid,
			Boolean isDefault,
			Double counterFee,
			Timestamp createtime) {
		this.adminUser=adminUser;
		this.adminuserId=adminuserId;
		this.bankAddress=bankAddress;
		this.bankName=bankName;
		this.cardNo=cardNo;
		this.counterFee=counterFee;
		this.id=id;
		this.isDefault=isDefault;
		this.isValid=isValid;
		this.createtime=createtime;
	}
	

	// Property accessors
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



	public Boolean getIsValid() {
		return isValid;
	}

	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}




	public Boolean getIsDefault() {
		return isDefault;
	}


	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}


	public Double getCounterFee() {
		return counterFee;
	}

	public void setCounterFee(Double counterFee) {
		this.counterFee = counterFee;
	}


	public String getBankAddress() {
		return bankAddress;
	}


	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public Integer getAdminuserId() {
		return adminuserId;
	}


	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}


	

	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}


	public void setCounterFee(double counterFee) {
		this.counterFee = counterFee;
	}


	public Timestamp getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}


	
	
	
}