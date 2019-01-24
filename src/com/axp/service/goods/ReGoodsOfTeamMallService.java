package com.axp.service.goods;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import com.axp.model.ReGoodsOfTeamMall;
import com.axp.query.PageResult;

public interface ReGoodsOfTeamMallService {
	/**
     * 获取所有的待审核的拼团商城的商品；
     *
     * @param currentPage 当前页；
     * @param pageSize    每页条数；
     * @return
     */
	PageResult<ReGoodsOfTeamMall> getCheckPageresult(Integer currentPage, Integer pageSize,Integer adminUserId);
	
	
	
	ReGoodsOfTeamMall get(Integer checkGoodsId);
	
	
	 Map<String, Object> doCheck(Map<String, Object> returnMamp, Boolean isPass, String checkDesc, Integer goodsId) throws Exception;
		/**
		 * 拼团置顶列表
		 * @param request
		 * @param currentPage
		 * @param search
		 */
	 void findReGoodsTeamList(HttpServletRequest request,Integer currentPage,String search,Integer type);
	
	 /**
	  * 拼团置顶
	  * @param request
	  */
	 Map<String, Object> stick(HttpServletRequest request);
	 
	/**
	 * 拼团自动退单
	 */
	 void teamOrderTimedtask();
	 
	 public Map<String, Object> uploadPic(HttpServletRequest request);
 
	 
	 void doSavePut(HttpServletRequest request) throws Exception;
}
