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

<style type="text/css">
			#dpid{
				display: inline-block;
				width: 180px;
				word-wrap: break-word;
				/* height: 45px;
				padding-top:20px; */
				padding-left: -8px;
				padding-right: -15px; 
				height: 63px;
				padding-top:22px; 
				
			}
		</style>
</head>
<body>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[淘客管理]-[PID列表]</p>
				</div>
				
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="<%=basePath%>taoke/pids/toPidList" id="searchOrChangePage" method="post">
				    <input type ="hidden" name="type" value="2"/>
				            	&nbsp;&nbsp;&nbsp;每天积分备注: <input type=text class="input" value="${search_name}" id="search_name" name="search_name" />&nbsp;&nbsp;
				    <input type="button" class="button"  id="btn" value="搜索"/>
				    <div id="selectLevel" style="display: inline-block;">
				    	<input id="userLevels"  type="hidden" value="${sessionScope.userLevel}"/>
				    	<select id="level" name="level">
				    	
				    	<c:if test="${sessionScope.userLevel>=95}">	
				    			<option value="1" <c:if test="${level==1}">selected</c:if> >运营商</option>
				    			<option value="2" <c:if test="${level==2}">selected</c:if> >事业合伙人</option>
				    			<option value="3" <c:if test="${level==3}">selected</c:if> >合伙人</option>
				    	</c:if>
						<c:if test="${sessionScope.userLevel<95}">	
				    			<option value="2" <c:if test="${level==2}">selected</c:if> >事业合伙人</option>
				    			<option value="3" <c:if test="${level==3}">selected</c:if> >合伙人</option>
				    	</c:if>	
				    	</select>
				    </div>
				    <c:if test="${sessionScope.userLevel>=75}">
					    <div style="display: inline-block; margin-left: 7%; color: red; font-size: 18px" >
					    	<p>双击已分配PID行可查看粉丝明细</p>
					    </div>
				    </c:if>
			    </form>
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">序号</th>
						<th  width="8%">PID</th>
						<th width="10%">亮盾备注</th>
						<th width="10%">每天积分备注</th>
						<th width="10%">运营商</th>
						<th width="10%">运营商备注</th>
						<th class="hide" width="10%">用户</th>
						<th class="hide" width="8%">用户备注</th>
						<th width="8%">是否分配</th>
						<th width="8%">等级</th>
						<c:if test="${level==2}">
							<th width="8%">地区</th>
						</c:if>
						<th width="8%">时间</th>
						<th width="9%">基本操作</th>
					</thead>
					<tbody>
							<c:forEach items="${list}" var="pid">
							<tr class="tr"  >
								<td class="uid">
									 ${pid.users.id}
								</td>
								<td >${pid.id }</td>
								<td id="dpid">${pid.pId}</td>
								<td>${pid.ldLoginReamrk}</td>
								<td>${pid.remark}</td>
								<td>${pid.adminUser.loginname}</td>
								<td>${pid.adminUserReamrk}</td>
								
								<td class="hide">${pid.users.name}</td>
								<td class="hide">${pid.usersRemark}</td>
								
								
								<td>
									<c:if test="${(pid.level==1&&pid.adminUser.id!=null)||(pid.level==2&&pid.users.id!=null)||(pid.level==3&&pid.users.id!=null)}">
										已分配
									</c:if>
									<c:if test="${(pid.level==1&&pid.adminUser.id==null)||(pid.level==2&&pid.users.id==null)||(pid.level==3&&pid.users.id==null)}">
										未分配
									</c:if>
								</td>
								<td><c:if test="${pid.level==1}">运营商</c:if>
									<c:if test="${pid.level==2}">事业合伙人</c:if>
									<c:if test="${pid.level==3}">合伙人</c:if>
								 </td>
								<c:if test="${level==2}">
								 	<td>${pid.provinceEnum.name }</td>
								</c:if>
								<td>${pid.createTime}</td>
								<td style="text-align: center;">
								<c:if test="${sessionScope.userLevel>=65}">	 
									<c:if test="${(pid.level==1&&pid.adminUser.id==null)||(pid.level==2&&pid.users.id==null)}">
												<div style="width: 120px">
												<span onclick="if(!confirm('您确认要分配此数据吗?'))return false;else distributePid('${pid.id}') " style="cursor: pointer;">
													<img
													src="<%=basePath %>constant/tab/images/edt.gif" width="18px"
													height="16" />
													分配
												</span>
												</div>
										</c:if>
									</c:if>
										<c:if test="${sessionScope.userLevel>=95}">	 
											<div style="width: 120px">
											<span onclick="if(!confirm('您确认要删除此数据吗?'))return false;else delTkldPid('${pid.id}')"  style="cursor: pointer;">
												<img
												src="<%=basePath %>constant/tab/images/del.gif" width="18px"
												height="16" />
												删除
											</span>
											</div>
										</c:if>
										
										<c:if test="${sessionScope.userLevel>=95}">	 
											<div style="width: 120px">
											<span onclick="if(!confirm('您确认要编辑此数据吗?'))return false;else toAddEditPid('${pid.id}')" style="cursor: pointer;" >
												<img
												src="<%=basePath %>constant/tab/images/edt.gif" width="18px"
												height="16" />
												编辑
											</span>
											</div>
										</c:if>
										<c:if test="${sessionScope.userLevel>=65}">	 
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
	
				$(function(){
					if($("#level").val()==1){
						$(".hide").hide();
					}
					
					$("#level").change(function(){
						if($("#level").val()!=1){
							$(".hide").show();
						}
					})
					
					 $(".uid").hide(); 
					
					
					$(".tr").dblclick(function(){
						if( $("#userLevels").val()>=75){ //代理级别
							var uid= $(this).find("td").eq(0).text().trim();
							if(uid.length>0){
								location.href="${BASEPATH_IN_SESSION}taoke/pids/findNextLevelByUserId?userId="+uid;
							}
						}
					});
					
				});
	
				function partnerDetail(id){
					location.href="${BASEPATH_IN_SESSION}taoke/pids/partnerDetail?id="+id;
				}
			
		
			$("#searchBtn").click(function(){
				location.href="${BASEPATH_IN_SESSION}taoke/pids/toSearchPid";
			})	
	
	
			$("#btn").click(function(){
				
				var level= $("#level").val();
				var search_name=$("#search_name").val();
				location.href="${BASEPATH_IN_SESSION}taoke/pids/toPidList?level="+level+"&search_name="+search_name;
			})
		
		
	
		$("#selectLevel").change(function(){
			var level= $("#level").val();
			var search_name=$("#search_name").val();
			location.href="${BASEPATH_IN_SESSION}taoke/pids/toPidList?level="+level+"&search_name="+search_name;
		});
	
		function cancelDistributePid(id){
			$.post("${BASEPATH_IN_SESSION}taoke/pids/cancelDistributePid",{id:id},function(data){
					if(data==0){
						alert("取消分配失败!");
						return false;
					}
					alert("取消分配成功!");
					var level= $("#level").val();
					location.href='${BASEPATH_IN_SESSION}taoke/pids/toPidList?level='+level;
			});
		}
		
		function delTkldPid(id){
			$.post("${BASEPATH_IN_SESSION}taoke/pids/delTkldPid",{id:id},function(data){
					if(data==0){
						alert("删除成功!");
						return false;
					}
					var level= $("#level").val();
					location.href='${BASEPATH_IN_SESSION}taoke/pids/toPidList?level='+level;
			});
		}
		
		
		
		function distributePid(id){
			location.href='${BASEPATH_IN_SESSION}taoke/pids/todistributePid?id='+id;
		}
		
		function toEditPid(id){
			location.href='${BASEPATH_IN_SESSION}taoke/pids/toEditPid?id='+id;
		}
	
		function createPid(){
			location.href='${BASEPATH_IN_SESSION}taoke/pids/toAddJsp';
		}
		
		function toAddEditPid(id){
			location.href='${BASEPATH_IN_SESSION}taoke/pids/toAddEditPid?id='+id;
		}
	</script>
</body>
</html>

