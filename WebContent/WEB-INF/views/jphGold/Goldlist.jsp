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
<title>用户聚品惠金币列表</title>
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
					<p>[资金管理]-[赠送积分/金币]</p>
				</div>
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			
		<div class="div_search">
			<form action="<%=basePath%>sellerWithdraws/sendGoldCoins" method="post" >
			<div class="phone">
			<span> 需要增加的用户&nbsp;&nbsp;:&nbsp;&nbsp;</span>
			<input name="phone" maxlength="11" value=""/>
			</div>
			<div class="phone">
			<span> 增加的金币数&nbsp;&nbsp;:&nbsp;&nbsp;</span>
			<input name="gold" maxlength="11" value=""/>
			</div>
			
			<div class ="serech" >  
			<input class="button"  type="submit"  value="增加金币"/>
			</div>
			</form>	
			</div>
			<div class="pageColumn">
				<table class="list" style="width:90%;margin:0 auto;">	
					<thead>
						
						<th width="10%">用户名</th>
						<th width="10%">手机号</th>
						<th width="10%">积分</th>
						<th width="10%">金币</th>
						<th width="10%" style="height:50px;">增加时间</th>
						
					</thead>
					<tbody>
					<c:forEach items="${requestScope.list}" var="list">
						<tr>
							
							<td>${list.name}</td>
							<td>${list.phone}</td>
							<td>${list.score}</td>
							<td>${list.jphScore}</td> 
							<td><fmt:formatDate value="${list.lasttime}" type="both"/></td>
							
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

