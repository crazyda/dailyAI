package com.axp.model;

import java.sql.Timestamp;
import java.util.List;

import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
public class SJScoreMark extends AbstractSJScoreMark implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2604225953275809244L;

	public SJScoreMark() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SJScoreMark(Integer id, AdminUser adminUser, Timestamp createTime,
			Boolean isValid, Integer scoreId, DLScoreMark dlScoreMark,
			Users users, Timestamp refreshTime) {
		super(id, adminUser, createTime, isValid, scoreId, dlScoreMark, users,
				refreshTime);
		// TODO Auto-generated constructor stub
	}

	
	

	
	
	
	
	
	
}
