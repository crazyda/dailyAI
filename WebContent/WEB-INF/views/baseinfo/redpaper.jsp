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
<title>红包管理</title>
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
					<p>[系统管理]-[红包管理]</p>
				</div>
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">	
					<thead>
						<th width="10%">序号</th>
						<th width="10%">红包ID</th>
						<th width="10%">红包发放人</th>
						<th width="10%"  style="height:50px;">红包发放时间</th>
						<th width="10%">已发出红包个数</th>
						<th width="10%">已发出金额</th>
						<th width="10%">剩余红包个数</th>
						<th width="10%">剩余红包金额</th>
						<th width="10%">类型</th>
						<th width="10%"/>红包发放地区</th>
						<th width="10%">操作</th>
					</thead>
					<tbody>
					<c:forEach items="${redpaperList }" var="r" varStatus="vs">
						<tr>
							<td>${vs.count}</td>
							<td>${r.id }</td>
							<td>${r.adminUser.username }</td>
							<td><fmt:formatDate value="${r.creattime}" type="both"/></td>
							<td>${r.totalQuantity}</td>
							<td>${r.totalMoney}</td>
							<td>${r.surplusQuantity}</td>
							<td>${r.surplusMoney}</td>
							<td>
								<c:choose>
									<c:when test="${r.type ==10 }">
										随机红包
									</c:when>
									<c:when test="${r.type == 50 }">
										定额红包
									</c:when>
								</c:choose>
							</td>
							<td>
								${r.provinceEnum.provinceEnum.provinceEnum.name }${r.provinceEnum.provinceEnum.name }${r.provinceEnum.name }
							</td>
							<td>
								<a href="<%=basePath%>system/redpaper/getRedpaperDetail?redpaperId=${r.id }">查看红包领取情况</a>
							</td>
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

