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
<title>PID管理</title>
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
					<p>你当前的位置：[淘客管理]-[我的PID]</p>
				</div>
				<hr align="center" />
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						
						<th width="5%"  style="height:50px;">序号</th>
						<th width="7%">PID</th>
						<th width="10%">PID说明</th>
						<th width="10%">时间</th>
						<th width="10%">粉丝名称</th>
						<th width="10%">是否分配</th>
						<th width="8%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.list }" var="citypid" >
						<tr>
							<td>${citypid.id }</td>
							<td>${citypid.adminuserTaokePid.pid }</td>
							<td>${citypid.adminuserTaokePid.pidname }</td> 
							<td>${citypid.createtime }</td>
							 <td>${citypid.username }</td> 
							<td>
							
								<c:choose>
									<c:when test="${citypid.status==1 }">
										已分配:${citypid.fengpei}
									</c:when>
									
									<c:otherwise>未分配</c:otherwise>
								</c:choose>
								
							</td>
							<td>
								<div>
								<c:if test="${citypid.status==0}">
									<span onclick="if(!confirm('您确认要分配此数据吗?'))return false;else distribute('${citypid.id}')" style="cursor: pointer;">
										<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
										height="16" />分配
									</span><br/>
									</c:if>
								</div>
								<div>
								<c:if test="${citypid.status==1}">
										<span onclick="if(!confirm('您确认要取消分配此数据吗?取消分配后，城市合伙人的收益信息同时取消!'))return false; else isdistribute('${citypid.id}')" style="cursor: pointer;">
										<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
										height="16" />取消分配
									</span><br/>
								</c:if>
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
function distribute(ids){
	window.location.href = "${BASEPATH_IN_SESSION}taoke/Mypid/isdistribute?ids="+ids;
}

function isdistribute(id){
	window.location.href = "${BASEPATH_IN_SESSION}taoke/Mypid/ofdistribute?id="+id;
}
</script>
</body>
</html>

