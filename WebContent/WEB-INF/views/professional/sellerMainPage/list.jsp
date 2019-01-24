<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<body>
	<form action="<%=basePath%>professional/branches/list" method="post">
		<div class="main" id="div_main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[业务管理]-[店铺主页管理]</p>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
				   <%--  <s:select id = "level_id"  name="level_id" list="#{'-1':'所有','loginname':'登录名','username':'真名'}"></s:select>
				    &nbsp;&nbsp; --%>
				      店铺名称: <input type=text class="input" value="${search_name} " name="search_name" />&nbsp;&nbsp;
				    <input type="submit" class="button" value="搜索" >
			</div>
			<!--end of header-->
			<div class="pageColumn" id="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="5%" style="height:50px;">序号</th>
						<th width="10%">店铺名称</th>
						<th width="10%">联系人</th>
						<th width="8%">联系电话</th>
						<th width="10%">所属商圈</th>
						<th width="10%">店铺类型</th>
						<th width="15%">基本操作</th>
					</thead>
					<tbody>
					<c:forEach items="${sellerList}" var="seller">
					<tr>
					<td><input type="checkbox" name="code_Value" value="" /></td>
            		<td>${seller.id }</td>
           	 		<td>${seller.name }</td>
            		<td>${seller.contacts }</td>
            		<td>${seller.phone }</td>
					<td>${seller.advertwo.loginname }</td>
					<td>
					<c:if test="${seller.level == 0 }">主店</c:if>
					<c:if test="${seller.level == 1 }">分店</c:if>
					</td>
					<td height="20" >
					<div align="center">
							<span onclick="edit('${seller.id }');" style="cursor: pointer;"> <img
										src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />编辑主页
								</span>&nbsp;
						</div></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!--end of pageColunm-->
			<div class="footer" id="footer">
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

  function createUser(){
	  window.location.href="add";
  }
  
  function edit(id){
	  window.location.href="add?id="+id;
  }
  
  function deleteSeller(id){
	  window.location.href="delete?id="+id;
  }
  
  function createChildUser(id){
	  parent.addAdminChilduser(id);
  }
  
 
  

</script>
</body>
</html>

