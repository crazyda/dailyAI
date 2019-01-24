package com.axp.model;

import java.sql.Timestamp;


public class AdminuserWithdrawalsData extends AbstractAdminuserWithdrawalsData implements java.io.Serializable{
	
	public AdminuserWithdrawalsData(){
		
	}
	
	public AdminuserWithdrawalsData(Integer id,
			Integer adminuserId,
			AdminUser adminUser,
			String name,
			String phone,
			String code,
			Timestamp cretatetime,
			Boolean isValid,
			String image,String image2){
		super(id, adminuserId, name, phone, code, cretatetime, isValid, image, image2, adminUser);
		
	}
}
