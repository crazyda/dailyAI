package com.axp.action.money;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.AdminuserWithdrawals;
import com.axp.model.Seller;
import com.axp.query.PageResult;
import com.axp.util.CalcUtil;
import com.axp.util.QueryModel;
import com.axp.util.ResponseResult;
import com.yilian.service.versionSMS.MsgBean;
import com.yilian.service.versionSMS.PayMen;


@Controller
@RequestMapping("/sellerWithdraws")
public class SellerWithdrawsAction extends BaseAction {

	
	/**
	 * 每天积分易联账户余额查询
	 * @throws Exception 
	 */
	@NeedPermission(permissionName = "账户--余额", classifyName = "资金管理")
	@IsItem(firstItemName = "资金管理", secondItemName = "账户--余额")
	@RequestMapping("/getAccount")
	public String getUserMoeny(HttpServletRequest request) throws Exception{
		try {
			MsgBean userMoney = PayMen.getUserMoney();
			request.setAttribute("money", userMoney.getAMOUNT());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "money/account";
		
	}

	/**
	 * 商家Action
	 */
	@NeedPermission(permissionName = "商家提现--待审核", classifyName = "资金管理")
	@IsItem(firstItemName = "资金管理", secondItemName = "商家提现--待审核")
	@RequestMapping("/sellerExamineList")
	public String sellerExamineList(HttpServletRequest request,Integer currentPage, Integer pageSize){
		//检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录或暂未绑定商家店铺，无法查看；");
            return "error/error";//登录出错情况；
        }
		if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
            pageSize = 10;
        }

        //查询出所有的商家提现记录，并放到session中；
        PageResult<AdminuserWithdrawals> result=sellerWithdrawService.getCheckListByStatus(currentPage, pageSize, 0);
        
        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
        return "money/sellerWithdraws/sellerExamineList";
	}
	
	
	
	/*
	 * 审核页面
	 * */
	 @RequestMapping("/sellerWithdrawsPage")
	    public String sellerWithdrawsPage(HttpServletRequest request, Integer Id) {
		 AdminuserWithdrawals adminuserWithdrawals = withdrawReviewService.findById(Id);
		 Seller seller = sellerService.getSellerByAdmin(adminuserWithdrawals.getAdminUser().getId());
	        request.setAttribute("withdraw", adminuserWithdrawals);
	        request.setAttribute("seller", seller);
	        return "money/sellerWithdraws/sellerWithdrawsPage";
	    }
	
	
	
	/**
	 * 资金流动页面
	 * @param request
	 * @param adminuserId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	 @RequestMapping("/cashFlow")
	 public String cashFlow(HttpServletRequest request,Integer adminuserId,Integer currentPage, Integer pageSize){
		  //参数检查；
	        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }
	      //查询出所有的金额变动情况，并放到session中；
	        PageResult<AdminuserCashpointRecord> result = cashpointRecordService.getMoneyChangeRecord(adminuserId, currentPage, pageSize);
	        //request.setAttribute("adminuserId", adminuserId);
	        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
	        
	        
	        AdminUser adminUser = adminUserDao.findById(adminuserId);
	        request.setAttribute("adminUser", adminUser);
	        
	        //已确认
	        Object[] veriryResult = cashpointRecordService.getTotalIncomeAndExpend(adminuserId,1);
	        if(veriryResult!=null){
	        	//已确认收益
	        	request.setAttribute("verifyIncome", (Double)veriryResult[0]);
	        	//已确认支出
	        	request.setAttribute("verifyExpend", (Double)veriryResult[1]);
	        }
	      //总金额
	        double cashpoint = CalcUtil.add((Double)veriryResult[0], (Double)veriryResult[1], 2);
	        request.setAttribute("totalMoney", cashpoint);
	        return "money/adminuserWithdraw/WithdrawChangeRecord";
	 }
	
	
	
	
	
	 /**
	 	 *审核操作通过或驳回
	 	 */
	 
	 	@NeedPermission(permissionName = "审核提现信息", classifyName = "资金管理")
	 	@RequestMapping("/verifying")
	    @ResponseBody
	    public Map<String, Object> verifying(HttpServletRequest request, Integer Id, Integer pass, String remark) throws Exception{
	 		  Map<String, Object> returnMap = new HashMap<>();
	 			try {
	 				return sellerWithdrawService.isPay(Id, pass, remark, returnMap);
				} catch (Exception e) {
					e.printStackTrace();
					returnMap.put("status", -1);
					 returnMap.put("message", "审核失败");
				}
	 			return returnMap;
	    }
	
	
	/**
	 * 商家待支付列表
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	 	@IsItem(firstItemName = "资金管理", secondItemName = "商家提现--易联代付")
	    @NeedPermission(permissionName = "商家提现--易联代付", classifyName = "资金管理")
		@RequestMapping("/sellerWaitPayList")
		public String sellerWaitPayList(HttpServletRequest request,Integer currentPage, Integer pageSize){
			if (currentPage==null || currentPage < 1) {
				currentPage=1;
			}
			if (pageSize == null || pageSize < 1) {
				pageSize = 10;
			}
			PageResult<AdminuserWithdrawals> result = sellerWithdrawService.getSellerPayList(currentPage, pageSize);
	        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
	        return "money/sellerWithdraws/sellerWaitPayList";
		}
	 	
	 	
	 	
		/**
	 	 * 商家人工支付
	 	 * @param request
	 	 * @return
	 	 */
	 	@RequestMapping("/sellerArtificialPay")	
		@ResponseBody
		public Map<String, Object> sellerArtificialPay(HttpServletRequest request) {
	      
			String ids=request.getParameter("ids");
	        //审核操作；
	        return sellerWithdrawService.sellerArtificialPay(ids);
	    }
	 	
	 	
	 	
	 	
	 	/**
	 	 * 商家支付
	 	 * @param request
	 	 * @return
	 	 */
	 	@RequestMapping("/sellerPay")	
		@ResponseBody
		public Map<String, Object> sellerPay(HttpServletRequest request) {
	      
			String ids=request.getParameter("ids");
	        //审核操作；
	        return sellerWithdrawService.sellerPay(ids);
	    }
		
	 	
	 	/**
	 	 * 已支付商家提现列表
	 	 * @param request
	 	 * @param currentPage
	 	 * @param pageSize
	 	 * @return
	 	 */
	 	@NeedPermission(permissionName = "商家提现--支付结果", classifyName = "资金管理")
		@IsItem(firstItemName = "资金管理", secondItemName = "商家提现--支付结果")
		@RequestMapping("/toHavePaidList")
		public String toHavePaidList(HttpServletRequest request,Integer currentPage, Integer pageSize,Integer timed){
			//检查用户是否存在；
	        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
	        if (adminUserId == null) {
	            request.getSession().setAttribute("errorstr", "您尚未登录或暂未绑定商家店铺，无法查看；");
	            return "error/error";//登录出错情况；
	        }
			if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }
	        timed=timed==null?2:timed;
	        PageResult<AdminuserWithdrawals> result=sellerWithdrawService.payList(currentPage, pageSize, 10,timed);
	        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
			return "money/sellerWithdraws/toHavePaidList";
		}
	 	
	 	
		
		/**
		 *商家 支付失败 驳回次笔交易   
		 */
		@RequestMapping("/rejectSellerPay")
		public void rejectSellerPay(int id,HttpServletResponse response){
			ResponseResult.printResult(response, false,sellerWithdrawService.rejectSellerPay(id));
		}
	
		
		//da 后台给用户增加聚品惠金币的只能增加不能减少
		@NeedPermission(permissionName = "赠送金币", classifyName = "资金管理")
		@IsItem(firstItemName = "资金管理", secondItemName = "赠送金币")
		@RequestMapping("/sendGoldCoins")
		public String sendGoldCoins(HttpServletRequest request,String phone, Integer gold){
			
			usersInvokeService.saveUserGoldList(request,phone, gold);
			
			
			return "jphGold/Goldlist";
			
		}
		
		
}
