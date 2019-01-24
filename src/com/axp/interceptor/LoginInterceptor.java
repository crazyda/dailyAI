package com.axp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.axp.service.permission.RePermissionRoleService;
import com.axp.util.StringUtil;

public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RePermissionRoleService rePermissionRoleService;

    @Override
    public void afterCompletion(HttpServletRequest reuqest, HttpServletResponse response, Object arg2, Exception arg3) throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

        String requestURI = request.getRequestURI();

        /**
         * 设置不拦截项目
         */
        if ((request.getContextPath() + "/").equals(requestURI) || (request.getContextPath() + "/login").equals(requestURI)) {
            return true;
        }

        if (request.getSession().getAttribute("currentUserId") == null) {
            request.setAttribute("errortype", -4);
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
            return false;
        }

        if ((request.getContextPath() + "/login").equals(requestURI) || (request.getContextPath() + "/sellerMallNotify").equals(requestURI)) {
            return true;
        }
        if (StringUtil.hasLength(requestURI) && requestURI.startsWith(request.getContextPath() + "/html/")) {// 将那些请求路径html开头的请求放走，一般都是请求页面；
            return true;
        }
        if (StringUtil.hasLength(requestURI) && requestURI.startsWith(request.getContextPath() + "/alipaySuccess")) {// 支付成功后的跳转界面；
            return true;
        }

        // 跳转到错误界面
        //request.getRequestDispatcher("/WEB-INF/views/login/errorLogin.jsp").forward(request, response);
        return true;
    }

}
