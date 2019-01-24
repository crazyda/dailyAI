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
import com.axp.model.AdminuserWithdrawalsDataLog;
import com.axp.query.PageResult;


@Controller
@RequestMapping("/AdminwithdrawData")
public class AdminuserWithdrawDataAction extends BaseAction{
	  @NeedPermission(permissionName = "提现信息审核", classifyName = "资金管理")
	  @IsItem(firstItemName = "资金管理", secondItemName = "提现信息审核")
	  @RequestMapping("/reviewList")
	  public String reviewList(HttpServletRequest request,HttpServletResponse response,Integer currentPage, Integer pageSize){
		  //参数；
	        if (currentPage == null || currentPage < 1) {//给当前页赋默认值；
	            currentPage = 1;
	        }
	        if (pageSize == null || pageSize < 1) {//给每页显示条数赋默认值；
	            pageSize = 10;
	        }

	        //查询出所有的待审核商家提现信息，并放到session中；
	        PageResult<AdminuserWithdrawalsDataLog> result = withdrawDatalogService.getCheckDataList(currentPage, pageSize);
	        request.setAttribute("pageResult", result);//将查询结果放到session中；
	        return "money/adminuserWithdata/adminuserWithdrawData";
	  }
	  
	  @RequestMapping("/reviewPage")
	    public String reviewPage(HttpServletRequest request, Integer LogId) {

		  
		  //返回值；
	        Map<String, Object> returnMap = new HashMap<>();
		  withdrawDatalogService.reviewPage(returnMap, LogId, 0, request);
	        

	        return "money/adminuserWithdata/adminuserWithdrawReview";
	    }
	  
	  
	  @RequestMapping("/submitWithdrawDataReview")
	  @ResponseBody
	  public Map<String, Object> submitWithdrawDataReview(Integer id,Integer status,HttpServletRequest request) throws Exception {

	        //返回值；
	        Map<String, Object> returnMap = new HashMap<>();

	        //检查参数；
	        if (id == null || id <= 0) {
	            returnMap.put("message", "参数错误：没有id值；");
	            return returnMap;
	        }

	        //审核操作；
	        return withdrawDatalogService.doReview(returnMap, id, status, request);
	    }
}
