package com.axp.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.Scorerecords;
import com.axp.model.Users;
import com.axp.query.PageResult;

public interface ScorerecordsDAO extends IBaseDao<Scorerecords>{
	public static final int BUY = 0x21;//购物
	public static final int BACK = 0x22;//退货
	public static final int CASH = 0x23;//提现
	
	Object getLastRecordParameterInType(String parameter, Integer userId,
			Integer type);

	Scorerecords updateRecord(Users user, Integer updateMoney, Integer type);
	Scorerecords updateRecord(Users user, Integer updateMoney, Integer type,String remark);
	/**
	 * da 积分详细情况
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @param request
	 * @return
	 */
	PageResult<Scorerecords> getScoreRecordsList(Integer userId,
			Integer currentPage, Integer pageSize, HttpServletRequest request);
}
