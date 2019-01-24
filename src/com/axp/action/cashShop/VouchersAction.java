package com.axp.action.cashShop;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.service.cashShop.VoucherService;

@Controller
@RequestMapping("/cashShop/vouchers")
public class VouchersAction {
	@Autowired
	VoucherService voucherService;
	
	@NeedPermission(permissionName = "积分卡列表", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "积分卡列表")
	@RequestMapping("/list")
	public String getList(HttpServletRequest request,HttpServletResponse response){
		voucherService.getList(response, request);
		return "/cashShop/Voucher/list";
	}
	
	
	@NeedPermission(permissionName = "积分卡导出", classifyName = "商城管理")
	@IsItem(firstItemName = "商城管理", secondItemName = "积分卡导出")
	@RequestMapping("/add")
	public String add(HttpServletRequest request,HttpServletResponse response,String Num,String faceValue){
		return "/cashShop/Voucher/add";
	}
	
	
	@RequestMapping("/save")
	public void save(HttpServletRequest request,HttpServletResponse response,String Num,String faceValue,String remark){
		voucherService.save(request, response, Num, faceValue, remark);
	}
	
}
