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
					<p>你当前的位置：[淘客管理]-[合伙人列表]</p>
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
						<th width="7%">PID</th>
						<th width="10%">亮盾备注</th>
						<th width="10%">每天积分备注</th>
						<th width="8%">代理</th>
						<th width="8%">事业合伙人</th>
						<th width="8%">事业人备注</th>
						<th width="8%">合伙人</th>
						<th width="8%">合伙人备注</th>
						<th width="6%">是否分配</th>
						<!-- <th width="8%">等级</th> -->
						<th width="8%">时间</th>
						<c:if test="${sessionScope.userLevel>=95}">	
						<th width="9%">基本操作</th>
						</c:if>
					</thead>
					<tbody>
					
						<c:forEach items="${list}" var="pid">
							<tr>
								<td>${pid.id }</td>
								<td>${pid.pId}</td>
								<td>${pid.ldLoginReamrk}</td>
								<td>${pid.remark}</td>
								<td>${pid.tkldPid.tkldPid.adminUser.username}</td>
								<td>${pid.tkldPid.users.name}</td>
								<td>${pid.tkldPid.usersRemark}</td>
								<td>${pid.users.name}</td>
								<td>${pid.usersRemark}</td>
								<td>
									<c:if test="${(pid.level==1&&pid.adminUser.id!=null)||(pid.level==2&&pid.users.id!=null)||(pid.level==3&&pid.users.id!=null)}">
										已分配
									</c:if>
									<c:if test="${(pid.level==1&&pid.adminUser.id==null)||(pid.level==2&&pid.users.id==null)||(pid.level==3&&pid.users.id==null)}">
										未分配
									</c:if>
								</td>
								<%-- <td><c:if test="${pid.level==1}">代理商</c:if>
									<c:if test="${pid.level==2}">事业合伙人</c:if>
									<c:if test="${pid.level==3}">合伙人</c:if>
								 </td> --%>
								<td>${pid.createTime}</td>
								
							<c:if test="${sessionScope.userLevel>=95}">	
								<td style="text-align: center;">
										
										<c:if test="${sessionScope.userLevel>75}">	 
											<div style="width: 120px">
											<span onclick="if(!confirm('您确认要删除此数据吗?'))return false;else delTkldPid('${pid.id}')"  style="cursor: pointer;">
												<img
												src="<%=basePath %>constant/tab/images/del.gif" width="18px"
												height="16" />
												删除
											</span>
											</div>
										</c:if>
										
										<c:if test="${sessionScope.userLevel>75}">	 
											<div style="width: 120px">
											<span onclick="if(!confirm('您确认要编辑此数据吗?'))return false;else toAddEditPid('${pid.id}')" style="cursor: pointer;" >
												<img
												src="<%=basePath %>constant/tab/images/edt.gif" width="18px"
												height="16" />
												编辑
											</span>
											</div>
										</c:if>
										
										<c:if test="${(pid.level==1&&pid.adminUser.id!=null)||(pid.level==2&&pid.users.id!=null)||(pid.level==3&&pid.users.id!=null)}">
											<div style="width: 120px">
											<span onclick="if(!confirm('您确认要取消分配此数据吗?'))return false;else cancelDistributePid('${pid.id}')" style="cursor: pointer;">
												<img
												src="<%=basePath %>constant/tab/images/edt.gif" width="18px"
												height="16" />
												取消分配
											</span>
											</div>
										</c:if>
										
										<c:if test="${level==2}">
											<div style="width: 120px" >
												<span onclick="partnerDetail('${pid.id}')" style="cursor: pointer;">
													<img
													src="<%=basePath %>constant/tab/images/search01.png" width="18px"
													height="16" />
													合伙人详情
												</span>
											</div>
										</c:if>
								</td>
								</c:if>
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

