package com.axp.model;

import java.sql.Timestamp;

/**
 * UserShoppingcarBack entity. @author MyEclipse Persistence Tools
 */
public class UserShoppingcarBack extends AbstractUserShoppingcarBack implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserShoppingcarBack() {
	}

	/** full constructor */
	public UserShoppingcarBack(UserShoppingcar userShoppingcar,
			AdminUser adminUser, Users users, String reason,
			String reasonImgUrl, Timestamp backTime, Integer status,
			String checkPerson, String checkStr,Boolean isValid,
			Timestamp createTime,String remark,Double backMoney) {
		super(userShoppingcar, adminUser, users, reason, reasonImgUrl,
				backTime, status, checkPerson, checkStr,isValid,createTime,remark,backMoney);
	}

}
