package com.axp.model;

import java.sql.Timestamp;
import java.util.List;

import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
public class UserScoreMark extends AbstractUserScoreMark implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2604225953275809244L;

	public UserScoreMark() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserScoreMark(Integer id, AdminUser adminUser, Timestamp createTime,
			Boolean isValid, Integer scoreId, SJScoreMark sjScoreMark,
			Users users, Timestamp validityTime, Timestamp refreshTime) {
		super(id, adminUser, createTime, isValid, scoreId, sjScoreMark, users,
				validityTime, refreshTime);
		// TODO Auto-generated constructor stub
	}

	

	
	
	
	
	
	
}
