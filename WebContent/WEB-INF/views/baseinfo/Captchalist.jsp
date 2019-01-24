<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>验证码列表</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/Capthchalist.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
</head>

<body>
	
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>[系统管理]-[验证码]</p>
				</div>
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			
		<div class="div_search">
			<form action="<%=basePath%>Captcha/CaptchaList" method="post" >
			<div class="phone">
			<span> 手机号&nbsp;&nbsp;:&nbsp;&nbsp;</span>
			<input name="phone" maxlength="11" value="${requestScope.phone}"/>
			</div>
			<div class="start" >
			<span> 开始时间&nbsp;&nbsp;:&nbsp;&nbsp;</span>
            <input class="startTM" type=text id=starttime name="sTM" value='${requestScope.sTM }' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" />
			</div>
			<div class="end">
			<span> 结束时间&nbsp;&nbsp;:&nbsp;&nbsp;</span>
            <input class="startTM" type=text id=starttime name="eTM" value='${requestScope.eTM }' onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" />
			</div>
			<div class ="serech" >  
			<input class="button"  type="submit"  value="搜索"/>
			</div>
			</form>	
			</div>
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">	
					<thead>
						<th width="10%">序号</th>
						<th width="10%"  style="height:50px;">时间</th>
						<th width="10%">手机号</th>
						<th width="10%">验证码</th>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.list}" var="list">
						<tr>
							<td>${list.id}</td>
							<td><fmt:formatDate value="${list.createtime}" type="both"/></td>
							<td>${list.phone} </td>
							<td>${list.code}  </td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--end of pageColunm-->
			<div class="footer">
				<div class="page">
					<table>
						<tr>
							<div class="page-box common-page-box" style="text-align:center">
								${requestScope.pageFoot }
							</div>
						</tr>
					</table>

				</div>
			</div>
		</div>	
</body>
</html>

