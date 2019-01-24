package com.axp.service.system.impl;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.axp.model.AdminUser;
import com.axp.model.Users;
import com.axp.service.system.LoginService;
import com.axp.util.StringUtil;
import com.axp.util.Utility;

@Service
public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
    @Override
    public String userLogin(String loginname, String password, HttpServletRequest request) {
        //先通过登录名找到对应的用户
        AdminUser adminUser = adminUserDAO.getLoginAdminUser(loginname);
        /*if (adminUser == null) {//商家名登陆
            Seller seller = sellerDAO.findByName(loginname);
            if(seller!=null){
            	adminUser = adminUserDAO.getLoginAdminUserBySellerId(seller.getId());
            	if(adminUser==null){
            		return "NO_USER";
            	}
            }else{
            	return "NO_USER";
            }
        }*/
        
        StringUtil.setType(null);  //清空角色菜单类别（商城广告）
        
        if(adminUser==null){
    		return "NO_USER";
    	}
        
        //密码错误
        if (!Utility.MD5(password).equals(adminUser.getPassword())) {
        	
        	Users user = usersDAO.findByLoginName(loginname);
        	if(user!=null){
        		if(!Utility.MD5(password).equals(user.getPwd())){
        			return "ERROR_PASSWORD";
        		}
        	}else{
        		return "ERROR_PASSWORD";
        	}
        }
        // 登录成功，装载用户id和level到session
        
        
        
        int current_user_id = adminUser.getId();
        int userLevel = adminUser.getLevel()==null?-1:adminUser.getLevel();
        HttpSession session = request.getSession();
        session.setAttribute("userName", adminUser.getUsername());
        session.setAttribute("loginName", adminUser.getLoginname());
        session.setAttribute("userLevel", userLevel);
        session.setAttribute("userLevelName",
                StringUtil.getLevelsHash().get(userLevel + ""));
        session.setAttribute("currentUserId", current_user_id);
        session.setAttribute("isOpenCashshopType",
                adminUser.getIsOpenCashshopType());
        session.setAttribute("isOpenRebate", adminUser.getIsOpenRebate());
        
        //清空权限缓存
        session.removeAttribute("ReItems_of_loginin_in_Session");
        

        if (userLevel >= 65 && userLevel <= 70) {
            session.setAttribute("shopLevel", userLevel);
        }
        if (StringUtils.isBlank(adminUser.getInvitecode())) {
            // 生成邀请码
            adminUserDAO.updateInviteCode(adminUser);
        }
        return "LOGIN_SUCCESS";
    }

    @Override
    public String userLogout(HttpServletRequest request) {

        //移除session中所有的属性；
        HttpSession session = request.getSession();
        Enumeration e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String sessionName = (String) e.nextElement();
            session.removeAttribute(sessionName);
        }

        return "LOGIN_SUCCESS";
    }

}