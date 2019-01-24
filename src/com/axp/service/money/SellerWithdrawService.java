package com.axp.service.money;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.axp.model.AdminuserWithdrawals;
import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;
import com.push.ImpAppInformation;
import com.yilian.service.versionSMS.MsgBean;
import com.yilian.service.versionSMS.PayMen;

public interface SellerWithdrawService {
	PageResult<AdminuserWithdrawals> payList(Integer currentPage, Integer pageSize, Integer state,Integer timed);
	
	AdminuserWithdrawals findById(Integer Id);
	
	Map<String, Object> doCheck(Integer Id, Integer pass, String remark, Map<String, Object> returnMap);
	
	
	/*
	 * 对商家提现进行是否支付操作
	 * */
	 Map<String, Object> isPay(Integer Id, Integer pass, String remark, Map<String, Object> returnMap);
	
		
		/**
		 * 商家待支付列表
		 * @param currentPage
		 * @param pageSize
		 * @return
		 */
		 PageResult<AdminuserWithdrawals> getSellerPayList(Integer currentPage,
				Integer pageSize) ;
		 /**
		  * 人工支付
		  * @param ids
		  * @return
		  */
		 Map<String, Object> sellerArtificialPay(String ids);
		 
		 /**
		  * 商家支付
		  * @param ids
		  * @return
		  */
		 Map<String, Object> sellerPay(String ids);
		 
		 /**
		  * 易联代付
		  * @param recordList
		  * @return
		  */
		 Map<String, Object> pay(List<AdminuserWithdrawals> recordList);
		 
		 	/**
		 	 * 易联代付查询
		 	 * @param recordList
		 	 */
		  void payQuery(List<AdminuserWithdrawals> recordList); 
		  
		  /**
		   * 驳回商家支付
		   */
		  Map<String, Object> rejectSellerPay(int id);
		  
		  /**
		   * 查询商家带审核的列表
		   */
		  PageResult<AdminuserWithdrawals> getCheckListByStatus(
					Integer currentPage, Integer pageSize, Integer state);
		  
		  
		  
		 void getWithdrawalsList(HttpServletRequest request,HttpServletResponse response,String sTM,String eTM,String userId,String state,String type,String btn);
		  
}
