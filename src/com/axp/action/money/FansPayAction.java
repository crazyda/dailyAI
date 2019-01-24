package com.axp.action.money;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.CashmoneyRecord;
import com.axp.model.GetmoneyRecord;
import com.axp.query.PageResult;

@Controller
@RequestMapping("/payComplete")
public class FansPayAction extends BaseAction{

	/*
	 * 已支付列表
	 * */
	@IsItem(firstItemName = "资金管理", secondItemName = "已支付列表")
    @NeedPermission(permissionName = "已支付列表", classifyName = "资金管理")
	@RequestMapping("/payUsersCompleteList")
	public  String payUsersCompleteList(HttpServletRequest request,Integer currentPage, Integer pageSize){
		if (currentPage == null || currentPage < 1) {
            currentPage = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        PageResult<GetmoneyRecord> result = getmoneyRecordService.getPaycompleteList(currentPage, pageSize);
        request.getSession().setAttribute("pageResult", result);
		return "money/adminuserWithdraw/payUsersCompleteList";
	}
	
	
	/*
	 * 已支付资金明细
	 * */
	@RequestMapping("/payCompleteDetail")
	public String payCompleteDetail(HttpServletRequest request, Integer userId,Integer currentPage, Integer pageSize){
		if (currentPage==null || currentPage < 1) {
			currentPage=1;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = 10;
		}
		
		PageResult<CashmoneyRecord> result = cashmoneyRecordService.getCashMoneyChangeRecord(userId, currentPage, pageSize,request);

        request.getSession().setAttribute("pageResult", result);//将查询结果放到session中；
        return "money/adminuserWithdraw/cashMoneyChangeRecord";
	}
}
