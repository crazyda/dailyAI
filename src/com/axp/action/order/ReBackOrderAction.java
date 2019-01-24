package com.axp.action.order;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.ReBackOrder;
import com.axp.service.order.ReBackOrderService;

@Controller
@RequestMapping("/reBackOrder")
public class ReBackOrderAction extends BaseAction {

	@Autowired
	ReBackOrderService reBackOrderService;

    @NeedPermission(permissionName = "退单/售后列表", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "退单/售后")
    @RequestMapping("/list")
    public String list(Integer backState, HttpServletRequest request,HttpServletResponse response){
    	reBackOrderService.getBackList(backState, request, response);
    	if(backState==null||backState==ReBackOrder.BACKSTATE_daishenhe){
    		return "reBackOrder/list_0";
    	}else{
    		return "reBackOrder/list_1";
    	}
    }
    
    @NeedPermission(permissionName = "退单支付", classifyName = "商城管理")
    @IsItem(firstItemName = "商城管理", secondItemName = "退单支付")
    @RequestMapping("/list2")
    public String list2(Integer backState, HttpServletRequest request,HttpServletResponse response){
    	backState=backState==null?20:backState;
    	reBackOrderService.getBackListForPay(backState, request, response);
    	
    		return "reBackOrder/list_2";
    	
    }
    
    @RequestMapping("/edit")
    public String edit(Integer id, HttpServletRequest request,HttpServletResponse response){
    	reBackOrderService.getBackOrder(id, request, response);
    	return "reBackOrder/edit";
    }
    
    @RequestMapping("/save")
    public String save(ReBackOrder reBackOrder, HttpServletRequest request,HttpServletResponse response){
    	reBackOrderService.saveBackOrder(reBackOrder, request, response);
    	return "redirect:list";
    }
    
    @RequestMapping("/detail")
    public String detail(Integer id, HttpServletRequest request,HttpServletResponse response){
    	reBackOrderService.getBackOrder(id, request, response);
    	return "reBackOrder/detail";
    }
    
    //退款页面
    @RequestMapping("/tuikuan")
    public String tuikuan(HttpServletRequest request,HttpServletResponse response){
    	reBackOrderService.tuikuan(request,response);
    	return "reBackOrder/tuikuan";
    }
    
    //退款
    @RequestMapping("/tuikuanDetail")
    @ResponseBody
    public Map<String,Object> tuikuanDetail(HttpServletRequest request,HttpServletResponse response){
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		 map = reBackOrderService.tuikuanDetail(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return map;
    }
    
    //退款
    @RequestMapping("/untuikuanDetail")
    @ResponseBody
    public Map<String,Object> untuikuanDetail(HttpServletRequest request,HttpServletResponse response){
    	return reBackOrderService.untuikuanDetail(request);
    }
    
    @RequestMapping("/tuidan")
    @ResponseBody
    public Map<String,Object> tuidan(HttpServletRequest request){
    	return reBackOrderService.tuidan(request);
    }
    
    
    
    
} 
