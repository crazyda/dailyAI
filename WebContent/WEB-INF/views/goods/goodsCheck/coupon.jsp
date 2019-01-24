<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<base href="<%=basePath%>">
    <title>My JSP 'coupon.jsp' starting page</title>

  </head>
  
  <body>
  	<iframe src="${uri }" height="100%" width="100%"></iframe>
   
  </body>
</html>
