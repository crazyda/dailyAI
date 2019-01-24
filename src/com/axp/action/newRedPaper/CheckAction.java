package com.axp.action.newRedPaper;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.model.AdminUser;
import com.axp.model.NewRedPaperSetting;
import com.axp.service.newRedPaper.CheckService;

@Controller
@RequestMapping("/newRedPaper/check")
public class CheckAction {

	@Resource
	private CheckService checkService;

	@IsItem(firstItemName = "红包管理", secondItemName = "审核红包")
	@NeedPermission(permissionName = "审核红包列表", classifyName = "红包管理")
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {

		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = (AdminUser) checkService.findById(AdminUser.class, adminUserId);
		List<NewRedPaperSetting> redPaperSettingList = checkService.getCheckListByAdmin(adminUser);
		request.setAttribute("redPaperSettingList", redPaperSettingList);
		return "newRedPaper/check/list";
	}

	/**
	 * 审核方法
	 */
	@RequestMapping("/check")
	@ResponseBody
	public Object check(HttpServletRequest request, Integer id, Integer status) {

		String errorstr;
		// 判断审核参数；
		if (id == null || status == null || id < 0 || status < 0) {
			errorstr = "审核参数错误";
			request.setAttribute("errorstr", errorstr);
			return "error/time_out_error";
		}
		// 检查用户是否登录超时；	
		request.getSession().setAttribute("current_user_id", 47);
		Integer adminUserId = (Integer) request.getSession().getAttribute("currentUserId");
		// 判断是否存在该用户；
		AdminUser adminUser = (AdminUser) checkService.findById(AdminUser.class, adminUserId);
		if (adminUser == null) {
			errorstr = "无此用户";
			request.setAttribute("errorstr", errorstr);
			return "error/time_out_error";
		}
		// 更改红包状态；
		NewRedPaperSetting redPaper = (NewRedPaperSetting) checkService.findById(NewRedPaperSetting.class, id);
		redPaper.setStatus(status);
		checkService.updateSetting(redPaper);

		return status;
	}

}
