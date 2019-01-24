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
<title>pid管理</title>
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
					<p>你当前的位置：[淘客管理]-[合伙人补充列表]</p>
				</div>
				
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">序号</th>
						<th width="10%">购买用户</th>
						<th width="7%">事业合伙人</th>
						<th width="10%">备注</th>
						<th width="10%">时间</th>
						<th width="9%">基本操作</th>
					</thead>
					<tbody>
							<c:forEach items="${list}" var="list">
							<tr>
								<td>${list.id }</td>
								<td>${list.users.name}</td>
								<td>${list.causeUsers.name}</td>
								<td>${list.remark}</td>
								<td>${list.createtime}</td>
								
								<td style="text-align: center; height: 35px" >
									
									
												<span onclick="if(!confirm('您确认要补充该合伙人吗?'))return false;else addPartner('${list.id}') " style="cursor: pointer;">
													<img
													src="<%=basePath %>constant/tab/images/edt.gif" width="18px"
													height="16" />
													补充
												</span>
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

	<script type="text/javascript">
		function addPartner(id){
			$.ajax({
				async:false,
				url:'${BASEPATH_IN_SESSION}taoke/pids/addPartner',
				data:{id:id},
				success:function(data){
					if(data==1){
						alert("补充成功!");
						location.href="${BASEPATH_IN_SESSION}taoke/pids/partnerList";
					}
					else{
						alert("补充失败!");
					}
				}
			})
		}
	</script>
</body>
</html>
