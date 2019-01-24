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
<title>版本更新管理</title>
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
					<p>你当前的位置：[系统管理]-[APP版本管理]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell" onclick="add();">
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
						<th width="9%"  style="height:50px;">id</th>
						<th width="10%">App版本</th>
						<th width="10%">Android版本</th>
						<th width="10%">IOS版本</th>	
						<th width="10%">更新时间</th>
						<th width="10%">操作</th>
						
					</thead>
					<tbody>
			<c:forEach items="${list}" var="account">
				<tr>
					<td>${account.id}</td>
					<td>
						${account.appType eq 1 ?"用户版":"商家版"}
                	</td>
					<td>${account.appVersion }</td>
					<td>${account.appVersion }</td>
					<td>${account.updateTime}</td>
					
					
					<td style="color: #444444;">
						
					 	<span onclick="edit('${account.id}');">编辑</span>
						 	<span  onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else del('${account.id }');">删除</span>
					 
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
<script type="text/javascript">

  function add(){
	  window.location.href="<%=basePath %>system/appVersion/add";
  }
  function edit(editId){
		window.location.href="<%=basePath %>system/appVersion/edit?id="+editId;
	}
  
  function del(editId){
	  	$.post('<%=basePath %>system/appVersion/del',{"id":editId},function(data){
			if(data.success) {
				location.href="<%=basePath%>system/appVersion/list";
			}else{
				alert(data.message);
			}
		});
		
	}
 
</script>
</body>
</html>

