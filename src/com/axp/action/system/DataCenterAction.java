package com.axp.action.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.service.newRedPaper.AdminuserRedpaperReceiveService;

@Controller
@RequestMapping("/system/DataCenter")
public class DataCenterAction extends BaseAction{
	
	@Resource
	private AdminuserRedpaperReceiveService adminuserRedpaperReceiveService;
	
	@NeedPermission(permissionName = "数据中心", classifyName = "系统管理")
    @IsItem(firstItemName = "系统管理", secondItemName = "数据中心")
    @RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		//未支付退单
		Integer orderBackCount = dataCenterService.getBackOrderNumber( request, response);
		//未支付粉丝提现
		Integer fansWithdrawCount = dataCenterService.getFansWithdrawReviewNumber( request, response);
		//未支付商家提现
		Integer sellerWithdrawCount=dataCenterService.getSellerWithdrawReviewNumber( request, response);
		request.setAttribute("orderBackCount", orderBackCount);
		request.setAttribute("fansWithdrawCount", fansWithdrawCount);
		request.setAttribute("sellerWithdrawCount", sellerWithdrawCount);
		
		
		
		//检查用户是否存在；
        Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
        if (adminUserId == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }
        AdminUser adminUser = adminUserService.findById(adminUserId);
        if (adminUser == null) {
            request.getSession().setAttribute("errorstr", "您尚未登录");
            return "error/error";//登录出错情况；
        }
		
		
		//统计红包id为117的个数
		Integer redPaperId = 117;	
		Integer redPaperCount  = adminuserRedpaperReceiveService.countById(request,response,redPaperId);
		
		//获取当前未确认订单列表
		Integer status = 10;
		Integer list1Count = reGoodsorderService.countByStatus(adminUser.getId(),request,status);
		request.setAttribute("list1Count", list1Count);
		//获取当前已确认未发货订单列表
		status = 20;
		Integer list2Count = reGoodsorderService.countByStatus(adminUser.getId(),request,status);
		request.setAttribute("list2Count", list2Count);
		return "baseinfo/dataCenter";
	}
}
