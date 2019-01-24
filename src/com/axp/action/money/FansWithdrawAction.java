package com.axp.action.money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.CashmoneyRecord;
import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;

@Controller
@RequestMapping("/confirmUsersWithdraw")
public class FansWithdrawAction extends BaseAction{
	
	/*
	 * 粉丝提现支付列表
	 * */
	@IsItem(firstItemName = "资金管理", secondItemName = "粉丝提现支付列表")
    @NeedPermission(permissionName = "粉丝提现支付列表", classifyName = "资金管理")
    @RequestMapping("/confirmUsersWithdrawList")
	public String confirmUsersWithdrawList (HttpServletRequest request,Integer currentPage, Integer pageSize){

        if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }

        PageResult<GetmoneyRecord> result = getmoneyRecordService.getCheckedRecord(currentPage, pageSize);

        request.getSession().setAttribute("pageResult", result);
        return "money/adminuserWithdraw/confirmUsersWithdrawList";
	}
	
	/*
	 * 粉丝提现支付操作页面
	 * */
	@RequestMapping("/getUserPayPage")
    public String getUserPayPage (HttpServletRequest request,Integer Id){
    	GetmoneyRecord getmoneyRecord=getmoneyRecordService.findById(Id);
    	request.setAttribute("getmoneyRecord", getmoneyRecord);
    	return "money/adminuserWithdraw/usersWithdrawPayPage";
    }
	
	
	 /**
     * 给提现审核通过的粉丝进行打钱：
     * <p>
     * 1，这里只是确认操作，将审核状态更改为提现完成；
     * 2，真正的转钱操作在线下手动完成；
     */
	 @RequestMapping("/confirmUsersWithdraw")
	 @ResponseBody
	public Map<String, Object> confirmUsersWithdraw(HttpServletRequest request, Integer Id, Integer pass, String remark) {

        //返回值；
        Map<String, Object> returnMap = new HashMap<>();

        //审核操作；
        return getmoneyRecordService.isPay(Id, pass, remark, returnMap);
    }
	
	
	/*
	 * 粉丝资金明细 da
	 * */
	 @RequestMapping("/getCashMoneyChangeRecord")
	    public String getCashMoneyChangeRecord(HttpServletRequest request, Integer userId, Integer currentPage, Integer pageSize) {

	        //参数检查；
	        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }

	        //查询出所有的粉丝金额变动情况，并放到session中；
	        PageResult<CashmoneyRecord> result = cashmoneyRecordService.getCashMoneyChangeRecord(userId, currentPage, pageSize,request);

	        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
	        return "money/adminuserWithdraw/cashMoneyChangeRecord";
	    }
	 
	 /**
	  *  用户现金明细 da
	  * @param request
	  * @param userId
	  * @param currentPage
	  * @param pageSize
	  * @return
	  */
	 @RequestMapping("/getCashMoneyRecord")
	 public String getCashMoneyRecord(HttpServletRequest request, Integer userId, Integer currentPage, Integer pageSize){
		 //参数检查；
	        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }
	        
	       PageResult<CashmoneyRecord> result =  cashmoneyRecordService.getCashMoneyRecord(userId, currentPage, pageSize, request);
	       
	       request.getSession().setAttribute("pageResult", result);
	       
		 return "money/adminuserWithdraw/cashMoneyRecord";
		 
	 }
	 @RequestMapping("/getUserMoneyRecord")
	 public String getUserMoneyRecord(HttpServletRequest request, Integer userId, Integer currentPage, Integer pageSize){
		 //参数检查；
	        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }
	        PageResult<CashmoneyRecord> result =  cashmoneyRecordService.getOneCashMoneyRecord(userId, currentPage, pageSize, request);
		    ;
	        request.getSession().setAttribute("pageResult", result);
		 
		 
		 return "money/adminuserWithdraw/userMoneyRecordDatail";
		 
	 }
	 
	 
}
