package com.axp.action.money;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminuserWithdrawals;
import com.axp.model.Users;
import com.axp.query.PageResult;
import com.axp.util.ExcelUtil;
import com.axp.util.QueryModel;


@Controller
@RequestMapping("/money/ExcelPort")
public class ExcelPortAction extends BaseAction{
	@IsItem(firstItemName = "资金管理", secondItemName = "提现数据导出")
    @NeedPermission(permissionName = "提现数据导出", classifyName = "资金管理")
	@RequestMapping("excelPortList")
	public String excelPortList(HttpServletRequest request,HttpServletResponse response,String sTM,String eTM,String userId,String state,String type,String btn){
        sellerWithdrawService.getWithdrawalsList(request, response, sTM, eTM, userId, state, type,btn);
		return "/money/excelPort";
	}

}
