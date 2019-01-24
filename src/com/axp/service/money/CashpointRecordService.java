package com.axp.service.money;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.axp.model.AdminuserCashpointRecord;
import com.axp.query.PageResult;

public interface CashpointRecordService {
	/**
     * 获取金额变动情况；
     *
     * @param userId          用户id值；
     * @param currentPage 当前页；
     * @param pageSize    每页显示条数；
     * @return
     */
	PageResult<AdminuserCashpointRecord> getMoneyChangeRecord(Integer userId, Integer currentPage, Integer pageSize);

	/**
	 * 获取收入以及支出金额
	 * @param adminUserId 		用户id值
	 * @param type				状态
	 * @return
	 */
	Object[] getTotalIncomeAndExpend(Integer adminUserId,Integer type);
	
	
	/*
	 * 
	 * 用户提现申请
	 * */
	public Map<String, Object> saveWithdrawApply(HttpServletRequest request,String phone, String cardNo,double money);
	
	
	/*
	 * 用户提现
	 * */
	public void withdrawalApplyPage(HttpServletRequest request);
	
	
	/*
	 * 保存用户提现资料
	 * */
	public Map<String, Object> saveUserInfo(HttpServletRequest request,String name,String phone,String code,String coverPics,String descriptionPics);
	
	/*
	 * 保存银行卡信息*/
	public Map<String, Object> saveBankInfo(HttpServletRequest request,String bankName,String cardNo,String bankAddress);
}
