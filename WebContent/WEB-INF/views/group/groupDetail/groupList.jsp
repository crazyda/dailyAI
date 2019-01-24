<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/css2.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
<link href="${BASEPATH_IN_SESSION}css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${BASEPATH_IN_SESSION}layui/css/layui.css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/layer3/layer.js"></script>
<script src="${BASEPATH_IN_SESSION}layui/layui.js" charset="utf-8"></script>

<style type="text/css">
		.layui-input-submit{
			margin:0 auto;
			text-align:center;
			margin-top: 20px;
		}
		.layui-input-submit input{
			width:15%;	
			height:3%;
			background-color: #0099ff;
			outline:none;
			border-radius:8px;
			font-weight:bold;
			font-size: 12px;
			color:#ffffff;
		}
		#content{
			display:none;
		}
		.f{
			margin-left: 50px;
		}
		.layui-inline input{
			border-radius:6px;
		}
	</style>
</head>
<body>
	<form>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[系统管理]-[群功能管理]-[群列表]</p>
				</div>
				<hr align="center" />
			</div>

				<div class="header-right" style="margin-top: -60px;margin-left: 100px">
					<div class="header-right-cell" onclick="addmessage()" style="cursor: pointer;">
						<img src="${BASEPATH_IN_SESSION}img/new-2.png" />
						<p>建群</p>
					</div>&nbsp;&nbsp;
				
				</div>
			
		
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">群头像</th>
						<th width="5%"  style="height:50px;">群ID</th>
						<th width="10%">群名称</th>
						<th width="9%">创建人</th>
						<th width="9%">群类型</th>
						<th width="9%">创建时间</th>
						<th width="9%">查看群成员</th>
						<th width="9%">解散群</th>
					</thead>
					<tbody>
						<c:forEach items="${groupList}" var="group">
							<tr>
								<td><img src="${RESOURCE_LOCAL_URL }${group.imgUrl}" style="width:80px;height:80px;"/></td>
								<td>${group.groupId}</td>
								<td>${group.name}</td>
								<td>${group.user.username}</td>
								<c:if test="${group.groupType==null}"><td></td></c:if>
								<c:if test="${group.groupType==2 }"><td>事业合伙人群</td></c:if>
								<c:if test="${group.groupType==3 }"><td>合伙人群</td></c:if>
								<td>
								   <fmt:formatDate value="${group.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
								</td>
								<td><span onclick="look(${group.groupId})">查看群成员</span></td>
								<td><span onclick="dissMiss(${group.groupId})">解散群</span></td>
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
		<div id="content" >
			<div class="f" >
				<label>用户手机号码：</label>
				<div class="layui-inline">
					<input type="text" name="name" style="width: 200px" id="userphone" value="" />
					<input  type = "hidden" id="groupId" value=""/> 
				</div>
			</div>
			
			<div class="layui-input-submit">
				<input type="button" name="btnSub" id="btnSub" onclick="submitForm()" value="提交"  />
			</div>
		</div>
		<!--end of main-->
	</form>
<script>
var index;
function laren(ids){
	$("#groupId").val(ids);
	index=layer.open(
		{
		  type: 1,
		  area: ['420px', '240px'], //宽高
		  content: $('#content')
		});
	
	
	}
	
	function submitForm(){
		var groupId = $("#groupId").val();
		var userphone = $("#userphone").val();
		
		$.ajax({
			type:"POST",
			url:"${BASEPATH_IN_SESSION}/system/group/addGroup",
			data: {"groupId":groupId,"userphone":userphone},
			success:function(data){
				alert("添加成功");
				window.location.href="${BASEPATH_IN_SESSION}system/group/groupEmp?groupId="+groupId;
			}
			
			
		});
			
		
	}
	
	function dissMiss(ids){
		$.ajax({
			type:"POST",
			url:"${BASEPATH_IN_SESSION}/system/group/dismiss",
			data: {"groupId":ids},
			
			success:function(data){
				
				alert("解散成功");
				window.location.href="${BASEPATH_IN_SESSION}system/group/groupList?groupId="+ids;
			}
			
			
		});
	}
	
	
	
	function look(ids){
		window.location.href="${BASEPATH_IN_SESSION}system/group/groupEmp?groupId="+ids;
	}
	
	function addmessage(){
		window.location.href="${BASEPATH_IN_SESSION}/system/group/Group";
		
		}
	
	
</script>
</body>
</html>

