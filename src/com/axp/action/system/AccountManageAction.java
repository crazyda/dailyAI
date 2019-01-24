package com.axp.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.service.system.AccountService;
import com.axp.util.ResponseResult;

@Controller
@RequestMapping("/account")
public class AccountManageAction extends BaseAction{
	@Autowired
	public AccountService accountService;
	@NeedPermission(permissionName="账户管理",classifyName="系统管理")
	@IsItem(firstItemName="系统管理",secondItemName="账户管理")
	@RequestMapping("/list")
	public String AccountManage(HttpServletRequest request,HttpServletResponse response){
		accountService.accountManage(request, response);
		return "/baseinfo/account/AcountManage";
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request ,HttpServletResponse response,Integer id){
		accountService.add(request, response, id);
		return "/baseinfo/account/add";
	}
	
	
	@RequestMapping("/save")
	public void  save(HttpServletRequest request,HttpServletResponse response,String name){
		name = request.getParameter("name");
		accountService.save(request, response, name);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("result", "success");
		ResponseResult.printResult(response, false, res);	
		
	}
	
	@RequestMapping("/saveAccount")
	public String  saveAccount(HttpServletRequest request,HttpServletResponse response,Integer id,String firstselect,String secondselect,
			String appid,String secret,String mchid,String apikey,String aliappid,String priKey,String priKey2,String pubKey,String alisellerid,String aliapikey){
		accountService.saveAccount(request, response, id, firstselect, secondselect, 
				appid, secret, mchid, apikey, aliappid, priKey, priKey2, pubKey, alisellerid, aliapikey);
		return "redirect:list";
	}
}
