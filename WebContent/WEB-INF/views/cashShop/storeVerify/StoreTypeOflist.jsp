<%@page import="java.sql.Timestamp"%>
<%@page import="com.axp.model.ImageType"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.axp.model.Zones"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
</head>
<body style="background:none">
	<form>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[商城管理]-[店铺类别管理]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell" onclick="createType();" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="9%"  style="height:50px;">序号</th>
						<th width="10%">类型名称</th>
						<th width="9%">创建时间</th>
						<th width="9%">类型级别</th>
						<th width="9%">基本操作</th>
					</thead>
					<tbody>
					<c:forEach items="${shoplist }" var="category">
						<tr>
							<td>${category.id }</td>
							<td>${category.name }</td>
							<td>${category.createtime}</td>
							<td>
								<c:choose>
									<c:when test="${category.level==1 }">
									父类--第一级
									</c:when>
									<c:when test="${category.level==2 }">
									子类--第二级
									</c:when>
								</c:choose>
							</td>
							<td>
				            <span class="STYLE4">
				            <span onclick="editType('${category.id }');" style="cursor: pointer;">
				            <img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16"  />编辑</span>
				            &nbsp; &nbsp;
				            <span style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deleteType('${category.id }');">
				            <img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />删除</span>
				            </span></div>
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
		<!--end of main-->
	</form>
<script>

  function createType(){
	   window.location.href="add"; 
  }

  function editType(id){
	  window.location.href="add?id="+id;
  }

  function deleteType(id){
  
	  window.location.href="del?id="+id;
  }
</script>
</body>
</html>

