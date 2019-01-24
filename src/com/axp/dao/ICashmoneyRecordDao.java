package com.axp.dao;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.Session;

import com.axp.model.CashmoneyRecord;
import com.axp.model.Users;
import com.axp.query.PageResult;

public interface ICashmoneyRecordDao extends IBaseDao<CashmoneyRecord>{
	public static final int BUY = 0x21;//购物
	public static final int BACK = 0x22;//退货
	public static final int CASH = 0x23;//提现
	
	CashmoneyRecord updateRecord(Users user, Double updateValue, Integer type,
			Integer relateId, String relateClass);
	
	
	/**
     * 获取粉丝金额变动情况；
     *
     * @param userId    粉丝id值；
     * @param currentPage 当前页；
     * @param pageSize    每页显示条数；
     * @return
     */
	PageResult<CashmoneyRecord> getCashMoneyChangeRecord(Integer userId, Integer currentPage, Integer pageSize,HttpServletRequest request);


	/**
	 * 获取收入以及支出金额
	 * @param userId 		用户id值
	 * @param type				状态
	 * @return
	 */
	Object[] getTotalIncomeAndExpend(Integer userId,Integer type);

	
	
	public void updateMoneyById(Integer itemId);
	
	
	/**
	 *  统计每个用户的消费金额, da
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @param request
	 * @return
	 */
	PageResult<CashmoneyRecord> getCashMoneyRecord(Integer userId,Integer currentPage,Integer pageSize,HttpServletRequest request);
	
	/**
	 * 查询单个用户 da
	 * @param userId
	 * @param currentPage
	 * @param pageSize
	 * @param request
	 * @return
	 */
	PageResult<CashmoneyRecord> getOneCashMoneyRecord(Integer userId,Integer currentPage,Integer pageSize,HttpServletRequest request);		
}
