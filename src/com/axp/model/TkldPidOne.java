package com.axp.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TkldPidOne  extends AbstractTkldPidOne implements Serializable{

	public TkldPidOne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TkldPidOne(Integer id, String pId, Integer level,
			TkldPidOne tkldPid, String ldLoginName, String ldLoginPwd,
			String ldLoginReamrk, String remark, Timestamp bindingTime,
			AdminUser adminUser, Users users, ProvinceEnum provinceEnum,
			String usersRemark, String adminUserReamrk, boolean isValid,
			Timestamp createTime) {
		super(id, pId, level, tkldPid, ldLoginName, ldLoginPwd, ldLoginReamrk, remark,
				bindingTime, adminUser, users, provinceEnum, usersRemark,
				adminUserReamrk, isValid, createTime);
		// TODO Auto-generated constructor stub
	}

	
	

	

	


	
	
	
}
