package com.axp.interceptor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.axp.util.ConstantUtil;
import com.axp.util.SellerMallUtil;
import com.axp.util.StringUtil;

/**
 * 用于检测用户是否登陆的过滤器，如果未登录或超时，则重定向到指的登录页面
 * <p>
 */
public class SystemContextFilter extends HttpServlet implements Filter {

	private static final long serialVersionUID = 1L;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res= (HttpServletResponse) response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        /**
         * 首先将项目的基本路径放到session中；
         */
        if (request.getServletContext().getAttribute("BASEPATH_IN_SESSION") == null) {
            String path = req.getContextPath();
            String basePathWithoutFirstSprit = req.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +path;
           // String basePathWithoutFirstSprit ="http://192.168.200.182:"+ request.getServerPort() + path;
            String basePath = basePathWithoutFirstSprit + "/";
            req.getSession().getServletContext().setAttribute("BasepathWithoutFirstSprit_IN_SESSION", basePathWithoutFirstSprit);
            req.getSession().getServletContext().setAttribute("BASEPATH_IN_SESSION", basePath);
            req.getSession().getServletContext().setAttribute("BASE_PIC_PATH_IN_SESSION", SellerMallUtil.BASE_PIC_PATH);// 将图片的基础路径放到session中；
            ConstantUtil.BASE = basePath;// 给base赋初始值；
            ConstantUtil.BASE_PATH = ConstantUtil.BASE + "uploadPic" + File.separatorChar;// 给basePath赋值；

			// 装载用户等级
			Map<String, Integer> levelMap = new HashMap<>();
			levelMap.put("SUPERADMIN", StringUtil.SUPERADMIN);// 总部
			levelMap.put("ADMIN", StringUtil.ADMIN);// 总部
			levelMap.put("CENTER", StringUtil.ADVERCENTER);// 运营中心
			levelMap.put("PROXY", StringUtil.ADVERONE);// 城市代理
			levelMap.put("ALLIANCE", StringUtil.ADVERTWO);// 联盟商圈
			req.getSession().getServletContext().setAttribute("USER_LEVEL_MAP", levelMap);
			
		}
		
		//装载接口服务器url
		if(request.getServletContext().getAttribute("API_URL") == null){
			
			String path ="/dailyAPI";
		//	String basePath = "http://192.168.200.182:"+ path + "/";
			String basePath = req.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
			
			request.getServletContext().setAttribute("API_URL", basePath);
		}

		//装载资源服务器url
		if(request.getServletContext().getAttribute("RESOURCE_LOCAL_URL") == null){

			//本地资源服务器是tomcat 中是daily_ref 对应的
			
			String basePath = "http://192.168.110.163:8080/daily_ref/";
			
			//线上的资源服务器是对应的是dailyRes 
			
			//String basePath = "http://jifen.aixiaoping.cn:8080/dailyRes/";
			request.getServletContext().setAttribute("RESOURCE_LOCAL_URL",basePath);//basePath
		}

        /**
         * 设置不拦截项目
         */
        String requestURI = req.getRequestURI();
        if ((req.getContextPath() + "/").equals(requestURI)
                || (req.getContextPath() + "/login").equals(requestURI)
                || requestURI.startsWith(req.getContextPath() + "/css")
                || requestURI.startsWith(req.getContextPath() + "/img")
                || requestURI.startsWith(req.getContextPath() + "/images")
                || requestURI.startsWith(req.getContextPath() + "/messagecenter_content")
                || requestURI.startsWith(req.getContextPath() + "/js")
                || requestURI.startsWith(req.getContextPath() + "/images_new")   
        		) {
            filterChain.doFilter(request, response);
        } else if (req.getSession().getAttribute("currentUserId") == null) {
            // 跳转到登录页
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            if ((req.getContextPath() + "/login/no_user").equals(requestURI)){
            	out.print("<script>alert('登陆失败！登录名不存在！');window.top.location.href='" + req.getContextPath() + "';</script>");
            }else if ((req.getContextPath() + "/login/error_password").equals(requestURI)) {
            	out.print("<script>alert('登陆失败！密码错误！');window.top.location.href='" + req.getContextPath() + "';</script>");
			}else if ((req.getContextPath() + "/login/login_error").equals(requestURI)) {
            	out.print("<script>alert('登陆失败！ 未知错误！');window.top.location.href='" + req.getContextPath() + "';</script>");
			}else {
            	out.print("<script>alert('登陆超时,请重新登陆');window.top.location.href='" + req.getContextPath() + "';</script>");
			}            
            out.flush();
            return;
        } else {
            filterChain.doFilter(request, response);
        }

	}

	public void destroy() {

	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}
}