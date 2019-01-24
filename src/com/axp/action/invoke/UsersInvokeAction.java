package com.axp.action.invoke;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axp.action.BaseAction;

@Controller
public class UsersInvokeAction extends BaseAction {

	/**
	 * app粉丝登陆接口
	 * @param name	用户名（必须）
	 * @param pwd	密码（必须）
	 * @param inviteCode	邀请码
	 * @param latitude	维度
	 * @param longitude	经度
	 * @param request
	 * @return
	 */
	@RequestMapping("invoke/userLoginNew")
	public String login(String name, String pwd, String inviteCode, String latitude, String longitude, HttpServletRequest request) {

		try {
			String commonReasult = usersInvokeService.login(name, pwd, inviteCode, latitude, longitude, request);
			request.setAttribute("commonReasult", commonReasult);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "invoke/common_result";

	}

}