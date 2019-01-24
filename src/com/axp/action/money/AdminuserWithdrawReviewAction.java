package com.axp.action.money;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.AdminuserCashpointRecordDAO;
import com.axp.model.AdminUser;
import com.axp.model.AdminUserScoreRecord;
import com.axp.model.AdminuserCashpointRecord;
import com.axp.model.AdminuserWithdrawals;
import com.axp.model.Seller;
import com.axp.query.PageResult;
import com.axp.util.CalcUtil;
import com.axp.util.QueryModel;
import com.axp.util.StringUtil;

@Controller
@RequestMapping("/AdminwithdrawReview")
public class AdminuserWithdrawReviewAction extends BaseAction{
	@NeedPermission(permissionName = "未支付商家提现", classifyName = "资金管理")
	@IsItem(firstItemName = "资金管理", secondItemName = "未支付商家提现")
	@RequestMapping("/list")
	public String list(HttpServletRequest request,Integer currentPage, Integer pageSize){
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
        PageResult<AdminuserWithdrawals> result=withdrawReviewService.getCheckList(currentPage, pageSize, 0);

        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
        return "money/sellerWithdraw/confirmSellerWithdrawList";
	}
	
	
	
	@NeedPermission(permissionName = "已支付商家提现", classifyName = "资金管理")
	@IsItem(firstItemName = "资金管理", secondItemName = "已支付商家提现")
	@RequestMapping("/paylist")
	public String paylist(HttpServletRequest request,Integer currentPage, Integer pageSize){
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
        PageResult<AdminuserWithdrawals> result=withdrawReviewService.getCheckList(currentPage, pageSize, 10);
        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
		return "money/adminuserWithdraw/payWithdrawlist";
	}
	
	
	/*
	 * 审核页面
	 * */
	 @RequestMapping("/WithdrawCheckPage")
	    public String WithdrawCheckPage(HttpServletRequest request, Integer Id) {
		 AdminuserWithdrawals adminuserWithdrawals = withdrawReviewService.findById(Id);
		 Seller seller = sellerService.getSellerByAdmin(adminuserWithdrawals.getId());
	        request.setAttribute("withdraw", adminuserWithdrawals);
	        request.setAttribute("seller", seller);
	        return "money/adminuserWithdraw/WithdrawCheckPage";
	    }
	 
	 
	 	@NeedPermission(permissionName = "审核提现信息", classifyName = "资金管理")
	 	@RequestMapping("/WithdrawCheck")
	    @ResponseBody
	    public Map<String, Object> WithdrawCheck(HttpServletRequest request, Integer Id, Integer pass, String remark) throws Exception{

	        //返回值；
	        Map<String, Object> returnMap = new HashMap<>();

	        //审核操作；
	        return withdrawReviewService.isPay(Id, pass, remark, returnMap);
	    }
	 	
	 	
	 //提现资金明细
	 @RequestMapping("/getMoneychange")
	 public String getMoneychange(HttpServletRequest request,Integer adminuserId,Integer currentPage, Integer pageSize){
		  //参数检查；
	        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }
	      //查询出所有的金额变动情况，并放到session中；
	        PageResult<AdminuserCashpointRecord> result = cashpointRecordService.getMoneyChangeRecord(adminuserId, currentPage, pageSize);
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
	 
	 
	 
	 	@NeedPermission(permissionName = "申请退押金列表", classifyName = "资金管理")
		@IsItem(firstItemName = "资金管理", secondItemName = "申请退押金列表")
		@RequestMapping("/reDepositList")
		public String reDepositList(HttpServletRequest request,Integer currentPage, Integer pageSize){
	 		//检查用户是否存在；
	        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
	        if (adminUserId == null) {
	            request.getSession().setAttribute("errorstr", "登录超时,请重新登录");
	            return "error/error";//登录出错情况；
	        }
			if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }
	        String sellerName = request.getParameter("sellerName")==null?"":request.getParameter("sellerName");
	        String state = request.getParameter("type")==null?"-1":request.getParameter("type");
	        PageResult<AdminuserCashpointRecord> result=adminuserCashpointRecordService.reDepositList(currentPage, pageSize,state,sellerName);
	        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
			return "money/adminuserWithdraw/reDepositList";
    }
	 	
 	 @RequestMapping("/deposit")
 	 @ResponseBody
	 public Map<String,Object> deposit(HttpServletRequest request,HttpServletResponse response){
 		 Map<String,Object> map = new HashMap<String,Object>();
 		 try {
 			 //保存到money
			 String id = request.getParameter("id"); 
			 AdminuserCashpointRecord acr = adminuserCashpointRecordDao.findById(Integer.valueOf(id));
			 AdminUser adminUser = acr.getAdminUser();
			 adminUser.setMoney(CalcUtil.add(adminUser.getMoney(), acr.getCashpoint()));
			 adminUser.setLasttime(new Timestamp(System.currentTimeMillis()));
			 adminUserDao.update(adminUser);
			 acr.setType(1);
			 acr.setIsDeposit(1);
			 acr.setRemark(acr.getRemark()+new Timestamp(System.currentTimeMillis())+"(已确认)");
			 adminuserCashpointRecordDao.update(acr);
			 //保存保存到 deposit
			 AdminuserCashpointRecord acrScore = adminuserCashpointRecordDao.findById(Integer.valueOf(id));
			 acrScore.setType(1);
			 acrScore.setIsDeposit(5);
			 acrScore.setRemark(acr.getRemark()+new Timestamp(System.currentTimeMillis())+"(已确认)");
			 acrScore.setAdminUser(adminUser);
			 acrScore.setIsValid(true);
			 acrScore.setAfterpoint(CalcUtil.div(adminUser.getDeposit(), acr.getCashpoint(), 2));
			 acrScore.setBeforepoint(adminUser.getDeposit());
			 acrScore.setCashpoint(acr.getCashpoint());
			 acrScore.setRemark("申请退回押金"+acr.getCashpoint()+"元");
			 adminuserCashpointRecordDao.update(acrScore);
			 
			
			 
			map.put("message", "用户:"+adminUser.getUsername()+",申请退押金"+acr.getCashpoint()+"元,已经确认!");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("message", "请求异常");
		}
 		
	    return map;
	 }
	 
	 
	 
	 
}
