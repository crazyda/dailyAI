package com.axp.action.money;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminuserRedpaperDao;
import com.axp.dao.UsersDAO;
import com.axp.model.AdminuserRedpaper;
import com.axp.model.CashmoneyRecord;
import com.axp.model.GetmoneyRecord;
import com.axp.model.Scorerecords;
import com.axp.model.Users;
import com.axp.query.PageResult;
import com.axp.service.money.AdminUserRedpaperService;
import com.axp.util.CalcUtil;
import com.axp.util.QueryModel;
import com.axp.util.ResponseResult;

@Controller
@RequestMapping("/fansWithdraws")
public class FansWithdrawsAction  extends BaseAction{

	@Autowired
	UsersDAO usersDao;
	@Autowired
	AdminUserRedpaperService adminuserRedpaperService;
	@Autowired
	AdminuserRedpaperDao adminuserRedpaperDao;
	/*
	 * 粉丝提现--待审核
	 * */
	@IsItem(firstItemName = "资金管理", secondItemName = "粉丝提现--待审核")
    @NeedPermission(permissionName = "粉丝提现--待审核", classifyName = "资金管理")
    @RequestMapping("/fansExamineList")
	public String fansExamineList (HttpServletRequest request,Integer currentPage, Integer pageSize){

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        
        PageResult<GetmoneyRecord> result = fansGetmoneyRecordService.getCheckedRecord(currentPage, pageSize);

        request.getSession().setAttribute("pageResult", result);
        return "money/fansWithdraws/fansExamineList";
	}
	
	
	/*
	 * 粉丝审核页面
	 * */
	@RequestMapping("/fansWithdrawsPage")
    public String fansWithdrawsPage (HttpServletRequest request,Integer Id){
    	GetmoneyRecord getmoneyRecord=fansGetmoneyRecordService.findById(Id);
    	request.setAttribute("getmoneyRecord", getmoneyRecord);
    	return "money/fansWithdraws/fansWithdrawsPage";
    }
	
	/*
	 * 粉丝资金明细
	 * */
	 @RequestMapping("/cashFlow")
	    public String cashFlow(HttpServletRequest request, Integer userId, Integer currentPage, Integer pageSize) {
		 	String type = request.getParameter("type");
	        //参数检查；
	        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }

	        //查询出所有的粉丝积分情况，并放到session中；
	        PageResult<CashmoneyRecord> result = cashmoneyRecordService.getCashMoneyChangeRecord(userId, currentPage, pageSize,request);
	        if (StringUtils.isNotBlank(type)) {
				request.setAttribute("type", type);
			}
	        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
	        
	        Users users = usersDao.findById(userId);
	        request.setAttribute("users", users);
	        
	        
	        //已确认
	        Object[] veriryResult = cashmoneyRecordService.getTotalIncomeAndExpend(userId,1);
	        //未确认
	        Object[] unveriryResult = cashmoneyRecordService.getTotalIncomeAndExpend(userId,-1);
	       //已确认总金额
	        double cashpoint = CalcUtil.add((Double)veriryResult[0], (Double)veriryResult[1], 2);
	        request.setAttribute("totalMoney", cashpoint);
	        //未确认总金额
	        double unCashpoint = CalcUtil.add((Double)unveriryResult[0], (Double)unveriryResult[1], 2);
	        request.setAttribute("untotalMoney", unCashpoint);
	        return "money/fansWithdraws/cashFlow";
	    }
	 
	 
	 @RequestMapping("/redDatils")
	 public String redDatils(HttpServletRequest request,HttpServletResponse response,Integer redId,Integer currentPage,Integer pageSize){
	      AdminuserRedpaper adminuserRedpaper = adminuserRedpaperDao.findById(Integer.valueOf(redId));
	      request.setAttribute("redpaper", adminuserRedpaper);
		 return "money/fansWithdraws/redpaperReview";
	 }
	 
	
	
	 
	 /**
	     * 给提现审核通过的粉丝进行打钱：
	     * <p>
	     * 1，这里只是确认操作，将审核状态更改为提现完成；
	     * 2，真正的转钱操作在线下手动完成；
	     */
		 @RequestMapping("/verifying")
		 @ResponseBody
		public Map<String, Object> verifying(HttpServletRequest request, Integer Id, Integer pass, String remark) {

	        //返回值；
	        Map<String, Object> returnMap = new HashMap<>();

	        //审核操作；
	        return fansGetmoneyRecordService.isPay(Id, pass, remark, returnMap);
	    }
	 
		 
			/**
			 * 粉丝提现待支付列表
			 * @param request
			 * @param currentPage
			 * @param pageSize
			 * @return
			 */
			@IsItem(firstItemName = "资金管理", secondItemName = "粉丝提现--易联代付")
		    @NeedPermission(permissionName = "粉丝提现--易联代付", classifyName = "资金管理")
			@RequestMapping("/fansWaitPayList")
			public String fansWaitPayList(HttpServletRequest request,Integer currentPage, Integer pageSize){
				if (currentPage==null || currentPage < 1) {
					currentPage=1;
				}
				if (pageSize == null || pageSize < 1) {
					pageSize = 10;
				}
				PageResult<GetmoneyRecord> result = fansGetmoneyRecordService.getFansPayList(currentPage, pageSize);
		        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
		        return "money/fansWithdraws/fansWaitPayList";
			}
		 
			
			
			/**
			 * 粉丝支付操作
			 * @param request
			 * @return
			 */
			@RequestMapping("/fansPay")
			@ResponseBody
			public Map<String, Object> fansPay(HttpServletRequest request) {
		      
				String ids=request.getParameter("ids");
		        //审核操作；
		        return fansGetmoneyRecordService.fansPay(ids);
		    }
			
			
			/**
			 * 粉丝人工支付操作
			 * @param request
			 * @return
			 */
			@RequestMapping("/artificialPay")
			@ResponseBody
			public Map<String, Object> artificialPay(HttpServletRequest request) {
		      
				String ids=request.getParameter("ids");
		        //审核操作；
		        return fansGetmoneyRecordService.artificialPay(ids);
		    }
			

			/**
			 * 粉丝已支付列表
			 * */
			@IsItem(firstItemName = "资金管理", secondItemName = "粉丝提现--支付结果")
		    @NeedPermission(permissionName = "粉丝提现--支付结果", classifyName = "资金管理")
			@RequestMapping("/toHavePaidList ")
			public  String toHavePaidList(HttpServletRequest request,Integer currentPage, Integer pageSize,Integer timed){
				if (currentPage == null || currentPage < 1) {
		            currentPage = 1;
		        }
		        if (pageSize == null || pageSize < 1) {
		            pageSize = 10;
		        }
		        timed=timed==null?2:timed;
		        PageResult<GetmoneyRecord> result = fansGetmoneyRecordService.payList(currentPage, pageSize,timed);
		        request.getSession().setAttribute("pageResult", result);
				return "money/fansWithdraws/toHavePaidList";
			}
			
			
			/**
			 * 粉丝支付失败后驳回
			 * @param id
			 * @param response
			 */
			//支付失败 驳回次笔交易   
			@RequestMapping("/rejectPay")
			public void rejectPay(int id,HttpServletResponse response){
				
				ResponseResult.printResult(response, false,fansGetmoneyRecordService.rejectPay(id));
			
			}
			
			
			/**
			 *  粉丝积分查询
			 */
			@RequestMapping("/scoreFlow")
			public String scoreFlow(HttpServletRequest request, Integer userId, Integer currentPage, Integer pageSize){
				String type = request.getParameter("type");
		        
				//参数检查；
				if(userId == null){
			    	   return "error/exception";
			       }
				if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
		            currentPage = 1;
		        }
		        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
		            pageSize = 10;
		        }
		       
		        
		        //查询出所有的粉丝积分变动，并放到session中；
		        PageResult<Scorerecords>  result = fansGetScoreRecordService.getScoreChangeRecord(userId, currentPage, pageSize, request);
		       
		        if (StringUtils.isNotBlank(type)) {
					request.setAttribute("type", type);
				}
		        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
		        
		        Users users = usersDao.findById(userId);
		        request.setAttribute("users", users);
		        
				
				return "money/fansWithdraws/scoreFlow";
				
			}
			
			
			
			
			
}
