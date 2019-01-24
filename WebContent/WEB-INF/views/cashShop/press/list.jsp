<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻管理</title>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/mian.js"></script>

</head>
<body>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[商城管理]-[新闻管理]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="createNews();" style="cursor: pointer;">
						<img src="<%=basePath %>img/new-2.png" />
						<p>新增</p>
				</div>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="<%=basePath%>cashShop/press/list.action" id="searchOrChangePage" method="post">
				            新闻名称: <input type=text class="input" value="${search_name}" name="search_name" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索"/>
			    </form>
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">序号</th>
						<th width="10%">新闻标题</th>
						<th width="8%">新闻URL</th>
						<th width="8%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.newslist }" var="press" >
						<tr>
						
							<td>${press.id }</td>
							<td>${press.title}</td>
							<td>${press.url}</td>
							<td>
								<div>
								<span onclick="editNews('${press.id }');" style="cursor: pointer;">
											<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
											height="16" />编辑
										</span><br/>
								<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deleteNews('${press.id}');"><img
										src="<%=basePath %>constant/tab/images/del.gif" width="16"
										height="16" />删除</span>		
								</div>
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
<script>
function editNews(id){
	  window.location.href = "${BASEPATH_IN_SESSION}/cashShop/press/add?id="+id;
}

function deleteNews(id){
	  window.location.href = "${BASEPATH_IN_SESSION}/cashShop/press/del?id="+id;
}

function createNews(){
	window.location.href = "${BASEPATH_IN_SESSION}cashShop/press/add";
}

</script>
</body>
</html>

