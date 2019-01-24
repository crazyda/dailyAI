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
<title>PID列表</title>
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
					<p>你当前的位置：[淘客管理]-[用户留言]</p>
				</div>
				
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="<%=basePath%>taoke/pid/toPidList" id="searchOrChangePage" method="post">
			    </form>
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">序号</th>
						<th width="10%">分享用户</th>
						<th width="10%">申请人</th>
						<th width="10%">申请人手机</th>
						<th width="10%">留言</th>
						<th width="10%">地区</th>
						<th width="8%">来源</th>
						<!-- <th width="8%">是否审核</th> -->
						<th width="8%">申请等级</th>
						<th width="8%">时间</th>
						<!-- <th width="9%">基本操作</th> -->
					</thead>
					<tbody>
							<c:forEach items="${list}" var="list">
							<tr>
								<td>${list.id}</td>
								<td>${list.users.name}</td>
								<td>${list.name}</td>
								<td>${list.phone}</td>
								<td>${list.message}</td> 
								<td>${list.provinceEnum.name}</td>
								<c:if test="${list.status==1}">
								<td>APP</td>
								</c:if>
								<c:if test="${list.status==2}">
								<td>WEB</td>
								</c:if>
								<%-- <c:if test="${list.isCheck==true}"><td>已审核</td></c:if>
								<c:if test="${list.isCheck==false}"><td>未审核</td></c:if> --%>
								<c:if test="${list.level==1}"><td>代理商</td></c:if>
								<c:if test="${list.level==2}"><td>事业合伙人</td></c:if>
								<td>${list.createTime}</td>
								<!-- <td style="text-align: center;">
								  待开发
								</td> -->
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
	
			
				function partnerDetail(id){
					location.href="${BASEPATH_IN_SESSION}taoke/pid/partnerDetail?id="+id;
				}
			
		
			$("#searchBtn").click(function(){
				location.href="${BASEPATH_IN_SESSION}taoke/pid/toSearchPid";
			})	
	
	
			$("#btn").click(function(){
				
				var level= $("#level").val();
				var search_name=$("#search_name").val();
				location.href="${BASEPATH_IN_SESSION}taoke/pid/toPidList?level="+level+"&search_name="+search_name;
			})
		
		
	
		$("#selectLevel").change(function(){
			var level= $("#level").val();
			var search_name=$("#search_name").val();
			location.href="${BASEPATH_IN_SESSION}taoke/pid/toPidList?level="+level+"&search_name="+search_name;
		});
	
		function cancelDistributePid(id){
			$.post("${BASEPATH_IN_SESSION}taoke/pid/cancelDistributePid",{id:id},function(data){
					if(data==0){
						alert("取消分配失败!");
						return false;
					}
					var level= $("#level").val();
					location.href='${BASEPATH_IN_SESSION}taoke/pid/toPidList?level='+level;
			});
		}
		
		function delTkldPid(id){
			$.post("${BASEPATH_IN_SESSION}taoke/pid/delTkldPid",{id:id},function(data){
					if(data==0){
						alert("删除成功!");
						return false;
					}
					var level= $("#level").val();
					location.href='${BASEPATH_IN_SESSION}taoke/pid/toPidList?level='+level;
			});
		}
		
		
		
		function distributePid(id){
			location.href='${BASEPATH_IN_SESSION}taoke/pid/todistributePid?id='+id;
		}
		
		function toEditPid(id){
			location.href='${BASEPATH_IN_SESSION}taoke/pid/toEditPid?id='+id;
		}
	
		function createPid(){
			location.href='${BASEPATH_IN_SESSION}taoke/pid/toAddJsp';
		}
		
		function toAddEditPid(id){
			location.href='${BASEPATH_IN_SESSION}taoke/pid/toAddEditPid?id='+id;
		}
	</script>
</body>
</html>

