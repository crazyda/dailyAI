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
<title>PID查询</title>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<%-- <script type="text/javascript" src="<%=basePath %>js/mian.js"></script> --%>
	
		<style type="text/css">
			#dpid{
				display: inline-block;
				width: 180px;
				word-wrap: break-word;
				vertical-align: middle;
				
				 margin-left:-20px; 
				margin-right: -20px;
				
				height: 63px;
				padding-top:22px; 
				
			}
			#tip{
				display: inline-block;
				color: red;
			}
		</style>
	
</head>
<body>

	<div class="main" style="height:auto;">
		<div class="header">
			<div class="header-left">
				<p>你当前的位置：[淘客管理]-[查询PID]</p>
			</div>
			<hr align="center" />
		</div>

		<div class="div_search">
			<form action="<%=basePath%>taoke/pid/findPidByCondition"
				id="searchOrChangePage" method="post">
				&nbsp;&nbsp; PID: <input id="pId" type=text name="pId" class="input"
					value="${pId}" />&nbsp;&nbsp; 用户: <input id="userNames"
					name="userNames" type=text class="input" value="${userNames }" />&nbsp;&nbsp;
				运营商: <input id="adminNames" name="adminNames" type=text
					class="input" value="${adminNames }" />&nbsp;&nbsp;
				级别:&nbsp;&nbsp; <select id="level" name="level">
				
					<c:if test="${userPidLevel==-1}">
					<option value="0" <c:if test="${level==0}">selected</c:if>>请选择</option>
					<option value="1" <c:if test="${level==1}">selected</c:if>>运营商</option>
					<option value="2" <c:if test="${level==2}">selected</c:if>>事业合伙人</option>
					<option value="3" <c:if test="${level==3}">selected</c:if>>合伙人</option>
					</c:if>
					
					<c:if test="${userPidLevel==1}">
					<option value="0" <c:if test="${level==0}">selected</c:if>>请选择</option>
					<option value="2" <c:if test="${level==2}">selected</c:if>>事业合伙人</option>
					<option value="3" <c:if test="${level==3}">selected</c:if>>合伙人</option>
					</c:if>
				</select> <input type=submit class="button" id="search" value="搜索" /> <input
					type="button" value="重置" id="reset" class="button" />
					
					<p id="tip">双击PID查看该详情</p>
			</form>
		</div>

		<!--end of header-->
		<div class="pageColumn">
			<table class="list" style="width:98%;margin:0 auto;">
				<thead>
					<th width="5%" style="height:50px">序号</th>
					<th   width="5%"  >PID</th>
					<th width="8%">亮盾备注</th>
					<th width="8%">每天积分备注</th>
					<th width="6%">等级</th>
					<!-- <th width="8%">运营商</th>
					<th width="8%">运营商备注</th>
					<th width="8%">用户</th>-->
					<th width="15%">运营商---事业---合伙人</th>
					
					<th width="8%">用户备注</th> 
					
					<th width="6%">是否分配</th>
					
					<c:if test="${level==2}">
						<th width="5%">地区</th>
					</c:if>
					<th width="8%">时间</th>
					 <th width="5%">基本操作</th> 
				</thead>
				<tbody>

					<c:forEach items="${list}" var="pid">
						<tr>
							<td>${pid.id }</td>
							<td  id="dpid" class="myPid" _id="${pid.id}" >${pid.pId}</td>
							<td>${pid.ldLoginReamrk}</td>
							<td>${pid.remark}</td>
							<td><c:if test="${pid.level==1}">运营商</c:if> <c:if
									test="${pid.level==2}">事业合伙人</c:if> <c:if
									test="${pid.level==3}">合伙人</c:if>
							</td>
							<td>
							<c:if test="${pid.level==1&&pid.adminUser!=null}">
								${pid.adminUser.loginname }
							</c:if>
							<c:if test="${pid.level==2&&pid.users!=null}">
								${pid.tkldPid.adminUser.loginname}---${pid.users.name}
							</c:if>
							<c:if test="${pid.level==3&&pid.users!=null}">
								${pid.tkldPid.tkldPid.adminUser.loginname}---${pid.tkldPid.users.name}---${pid.users.name}
							</c:if>
							</td>
							<td>
								<c:if test="${pid.level==1}">${pid.adminUserReamrk}</c:if>
								<c:if test="${pid.level!=1}">${pid.usersRemark}</c:if>
							</td>
							
						<%-- 	<c:if test="${pid.level==1}">
								<td>${pid.adminUserReamrk}</td>
							</c:if>
							<c:if test="${pid.level!=1}">
								<td>${pid.usersRemark}</td>
							</c:if> --%>
							
							
							<td>
							
									<c:if test="${(pid.level==1&&pid.adminUser.id!=null)||(pid.level==2&&pid.users.id!=null)||(pid.level==3&&pid.users.id!=null)}">
										已分配
									</c:if> 
									<c:if test="${(pid.level==1&&pid.adminUser.id==null)||(pid.level==2&&pid.users.id==null)||(pid.level==3&&pid.users.id==null)}">
										未分配
									</c:if>
							</td>
							
							<c:if test="${level==2}">
								<td>
									${pid.provinceEnum.name}
								</td>
							</c:if>
							<td>${pid.createTime}</td>
							<td>
							<div style="width: 120px">
							<span onclick="partnerDetail('${pid.id}')" style="cursor: pointer;">
												<img
												src="<%=basePath %>constant/tab/images/search01.png" width="18px"
												height="16" />
												详情												
							</span>
							</div>
							<c:if test="${userPidLevel==-1}">
							<c:if test="${pid.level==3 && pid.users!=null}">
								<div style="width: 120px">
											<span onclick="if(!confirm('是否升级事业合伙人,升级后数据不可逆?'))return false;else upgradeCareer('${pid.id}','2')" style="cursor: pointer;">
												<img
												src="<%=basePath %>constant/tab/images/edt.gif" width="18px"
												height="16" />
												升级事业合伙
											</span>
								</div>
							</c:if>
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
	
		
	
	
			$(".myPid").dblclick(function(){
				 var id=$(this).attr("_id");
				 location=' http://hhh.aixiaoping.cn/home/partner/parfindPid?pid_id='+id;
				
			});
			
	
			$("#reset").click(function(){
				var level = document.getElementById('level');
				level[0].selected = true;//选中
				$("#pId").val("");
				$("#userNames").val("");
				$("#adminNames").val("");
			});
			
							
			function partnerDetail(pid_id){
				location='http://hhh.aixiaoping.cn/home/Partner/parSelectPid?&pid_id='+pid_id;
			}		

		function upgradeCareer(id, level) {
			var pId= $("#pId").val();
			var userNames= $("#userNames").val();
			var adminNames= $("#adminNames").val();
			var level=$("#level").val();
			
					$.ajax({
						async : false,
						url : '${BASEPATH_IN_SESSION}taoke/pid/upgradeCareer?id='
								+ id + "&level=" + level,
						dataType : "json",
						success : function(data) {
							if (data.flag = "1") {
								alert("升级成功");
								location.href = "${BASEPATH_IN_SESSION}taoke/pid/findPidByCondition?pId="+pId+"&userNames="+userNames+"&adminNames="+adminNames+"&level="+level;
										
							} else {
								alert("升级失败");
							}
						}
					});
				} 
		
		
		
		
		
	</script>
</body>
</html>