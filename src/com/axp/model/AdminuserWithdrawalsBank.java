package com.axp.model;

import java.sql.Timestamp;



public class AdminuserWithdrawalsBank extends AbstractAdminuserWithdrawalsBank implements java.io.Serializable{
	
	public AdminuserWithdrawalsBank(){
		
	}
	
	public AdminuserWithdrawalsBank(Integer id,
			Integer adminuserId,
			AdminUser adminUser,
			String bankAddress,
			String cardNo,
			String bankName,
			Boolean isValid,
			Boolean isDefault,
			Double counterFee,
			Timestamp createtime){
		super(id, adminuserId, adminUser, bankAddress, cardNo, bankName, isValid, isDefault, counterFee, createtime);
	}
}
