package com.axp.model;

import java.sql.Timestamp;
import java.util.List;

import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
public class ScoreMark extends AbstractScoreMark implements java.io.Serializable {

	public ScoreMark() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScoreMark(Integer id, AdminUser adminUser, Timestamp createTime,
			Boolean isValid, String remark, String newHands,
			Timestamp validityTime, Timestamp refreshTime, Integer scoreId,
			AdminUser adminUserDL) {
		super(id, adminUser, createTime, isValid, remark, newHands, validityTime,
				refreshTime, scoreId, adminUserDL);
		// TODO Auto-generated constructor stub
	}


	
	
	
	
	
	
	
	
}
