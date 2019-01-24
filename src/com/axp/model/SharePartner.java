package com.axp.model;

import java.sql.Timestamp;


public class SharePartner extends AbstractSharePartner implements
		java.io.Serializable {

	public SharePartner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SharePartner(Integer id, Users users, ProvinceEnum provinceEnum,
			boolean isValid, Timestamp createTime, String name, String phone,
			String message, Integer status, boolean isCheck, Integer level) {
		super(id, users, provinceEnum, isValid, createTime, name, phone, message,
				status, isCheck, level);
		// TODO Auto-generated constructor stub
	}

	


}
