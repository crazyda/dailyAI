package com.axp.action.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;

@Controller
public class LoginAction extends BaseAction{

	//管理员登陆
	@RequestMapping("/login")
	public String Login(String loginname, String password, HttpServletRequest request) {
		String result = loginService.userLogin(loginname, password, request);
		if("LOGIN_SUCCESS".equals(result)){
			return "main_admin";
		}else if("NO_USER".equals(result)){
			return "redirect:/login/no_user";
		}else if("ERROR_PASSWORD".equals(result)){
			return "redirect:/login/error_password";
		}else{
			return "redirect:/login/login_error";
		}
	}

	//首页
	@RequestMapping("/login/indexPage")
	public String indexPage(){
		return "/login/index_admin";
	}

	@RequestMapping("/login/no_user")
	public String noUser(HttpServletRequest request){
		request.setAttribute("errortype", -1);
		return "index";
	}

	@RequestMapping("/login/error_password")
	public String errorPassword(HttpServletRequest request){
		request.setAttribute("errortype", -2);
		return "index";
	}

	@RequestMapping("/login/login_error")
	public String loginError(HttpServletRequest request){
		request.setAttribute("errortype", -3);
		return "index";
	}

	//退出登录
	@RequestMapping("/login/logout")
	public String Logout(HttpServletRequest request){
		loginService.userLogout(request);
		return "index";
	}

}