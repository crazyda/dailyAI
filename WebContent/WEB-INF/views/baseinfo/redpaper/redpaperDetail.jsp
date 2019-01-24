<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>红包详情</title>
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
					<p>[系统管理]-[红包管理]-[红包详情]</p>
				</div>
				<div class="header-right">
					<p style="text-decoration:underline,cursor:hand;" onclick="history.go(-1)">返回</p>
				</div>
				<hr align="center" />
			</div>
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">	
					<thead>
						<th width="10%">序号</th>
						<th width="10%">领取人姓名</th>
						<th width="10%">领取人电话</th>
						<th width="10%"  style="height:50px;">领取时间</th>
						<th width="10%">领取金额</th>
					</thead>
					<tbody>
					<c:forEach items="${redpaperList }" var="r" varStatus="vs">
						<tr>
							<td>${vs.count}</td>
							<td>${r.users.name}</td>
							<td>${fn:substring(r.users.phone,0,7)}****</td>
							<td><fmt:formatDate value="${r.createtime}" type="both"/></td>
							<td>${r.money}</td>
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

