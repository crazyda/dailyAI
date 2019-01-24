package com.axp.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.axp.model.RePermission;
import com.axp.service.permission.RePermissionRoleService;
import com.axp.service.system.AdminUserService;

public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private RePermissionRoleService rePermissionRoleService;
    @Autowired
    private AdminUserService adminUserService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
        //将当前登录用户的权限和菜单读取出来，放到session中；
        rePermissionRoleService.putPermissionAndItemToSession(request);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String link = requestURI.substring(contextPath.length());
        if (link.startsWith("/images_new/")
                || link.startsWith("/css/")
                || link.startsWith("/js/")
                || link.startsWith("/img/")
                || link.equals("/login")//在还没登录之前，没有数据
                || link.startsWith("/images/")
                || link.startsWith("/messagecenter_content/")
        		) {
            return true;
        }

        /**
         * 关于权限的操作；
         */

        //验证权限；如果是超级管理员，那么就直接不检查，如果是其他用户，则需要进行检查；
        if (adminUserService.isSuperAdmin(request)) {
            return true;
        }

        //验证权限；
        List<RePermission> adminUserPermissionList = (List<RePermission>) request.getSession().getAttribute("RePermissions_in_Session");//当前后台用户拥有的权限；
        List<RePermission> allPermissionList = (List<RePermission>) request.getServletContext().getAttribute("AllPermission_in_Session");//全部的权限；

        if (adminUserPermissionList != null) {
            for (RePermission each : adminUserPermissionList) {//查看当前用户拥有的权限是否包含此权限；
                if (link.equals(each.getLinkAddress())) {
                    return true;
                }
            }
        }
        if (allPermissionList != null) {
            for (RePermission each : allPermissionList) {//如果该用户不包含此项权限，那么在查看所有权限中是否包含了这个权限，如果没有这个权限，则说明当前请求不需要权限；
                if (link.equals(each.getLinkAddress())) {
                    request.getSession().setAttribute("errorstr", "你没有当前操作权限；");
                    request.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(request, response);
                    return false;
                }
            }
        }

        return true;
    }

}
