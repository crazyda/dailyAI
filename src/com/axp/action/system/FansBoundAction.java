package com.axp.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.axp.action.BaseAction;
import com.axp.action.permission.permissionAnnotation.IsItem;
import com.axp.action.permission.permissionAnnotation.NeedPermission;
import com.axp.dao.UsersDAO;
import com.axp.model.AdminUser;
import com.axp.model.Users;

@Controller
@RequestMapping("/system/fans")
public class FansBoundAction extends BaseAction{
	@Resource
	private UsersDAO usersDAO;
	@NeedPermission(permissionName="绑定粉丝",classifyName="系统管理")
	@IsItem(firstItemName="系统管理",secondItemName="绑定粉丝")
	@RequestMapping("/bound")
	public String list(HttpServletRequest request,HttpServletResponse response){
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = adminUserDao.findById(current_user_id);
		request.setAttribute("adminUser", adminUser);
		if (adminUser.getUsers()!=null) {
			Users users = adminUser.getUsers();
			request.setAttribute("users", users);
		}
		return "/baseinfo/fansBound/bound";
	} 
	
	@RequestMapping("/savebound")
	@ResponseBody
	public Map<String, Object> savebound(Integer status,String phone,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> statusMap = new HashMap<String, Object>();
		Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
		AdminUser adminUser = adminUserDao.findById(current_user_id);
		Users users = usersDAO.findByPhone(phone);
		if (status!=null&&status==10) {
			if (users!=null) {
				if (adminUser.getUsers()!=null&&phone.trim().equals(adminUser.getUsers().getPhone().trim())) {
					statusMap.put("status", -2);
					statusMap.put("message", "该粉丝已被绑定");
					return statusMap;
				}else{
					adminUser.setUsers(users);
					adminUserDao.update(adminUser);
					statusMap.put("status", 1);
					statusMap.put("message", "绑定成功");
				}
			}else{
				statusMap.put("status", -1);
				statusMap.put("message", "该粉丝不存在！");
				return statusMap;
			}
		}else if(status!=null&&status==-1){
			adminUser.setUsers(null);
			adminUserDao.update(adminUser);
			statusMap.put("status", 1);
			statusMap.put("message", "解绑成功");
		}
		return statusMap;
	}
}
