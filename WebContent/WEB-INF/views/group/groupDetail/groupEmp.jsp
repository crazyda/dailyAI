<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>群成员列表</title>
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
					<p>你当前的位置：[系统管理]-[群功能管理]-[群成员列表]</p>
				</div>
				<hr align="center" />
			</div>
				<div class="div_search">
					<input type="" class="button"  onclick="submitForm1()" value="直接添加对应等级用户"  />
				    <input type="" class="button" value="一键禁言" onclick="allIs(${groupId})"/>
				    <input type="" class="button" value="一键解除禁言"onclick="allIsFalse(${groupId})"/>
			    
			</div>
				<div class="header-right" style="margin-top: -60px;margin-left: 100px">
					 	<div class="header-right-cell" onclick="laren(${groupId})" style="cursor: pointer;">
						<img src="${BASEPATH_IN_SESSION}img/new-2.png" />
						<span>添加</span>
					</div>
				</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">成员ID</th>
						<th width="10%">成员名称</th>
						<th width="9%">加入时间</th>
						<th width="9%">是否禁言</th>
						<th width="9%">是否移出</th>
						
					</thead>
					<tbody>
						<c:forEach items="${igMember}" var="group">
							<tr>
								<td>${group.users.id}</td>
								<td>${group.users.name}</td>
								<td>
								   <fmt:formatDate value="${group.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
								</td>
								<td>
									<c:choose>
									<c:when test="${group.isForbid==true  }">
									<span onclick="gag('${group.users.id}','0')" >已禁言</span>
									</c:when>
									<c:when test="${group.isForbid==false || group.isForbid == null }">
									<span onclick="gag('${group.users.id}','1')">禁言</span>
									</c:when>
									</c:choose>
								</td>
								
								
								<td><span onclick="quit('${group.users.id}')">踢出群组</span></td>
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
					<input  type = "hidden" id="groupId" value="${groupId}"/> 
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
		alert("一个个添加");
		$.ajax({
			type:"POST",
			 url:"${BASEPATH_IN_SESSION}/system/group/addGroup",//手动一个个人添加

 			data: {"groupId":groupId,"userphone":userphone},
			success:function(data){
				if(data=="true"){
				alert("添加成功");
				window.location.href="${BASEPATH_IN_SESSION}system/group/groupEmp?groupId="+groupId;
					
				}else if(data=="false"){
					alert("用户已经存在该群了,不需要重复添加");
				}else if(data=="noUser"){
					alert("没有找到该用户,请输入正确手机号码");
				}
			}
		});
	}
	
	function submitForm1(){
		var groupId = $("#groupId").val();
		var userphone = $("#userphone").val();
		
		$.ajax({
			type:"POST",
 			url:"${BASEPATH_IN_SESSION}/system/group/selfAddGroup", //自动把所有对应的level全部添加
 			data: {"groupId":groupId,"userphone":userphone},
			success:function(data){
				window.location.href="${BASEPATH_IN_SESSION}system/group/groupEmp?groupId="+groupId;
			}
		});
	}
	
	function addmessage(){
		window.location.href="${BASEPATH_IN_SESSION}/system/group/Group";
		
		}
	
	function gag(ids,id){
		var groupId = $("#groupId").val();
		//var isForbid = $("#isForbid").val();
		$.ajax({
			type:"POST",
			url:"${BASEPATH_IN_SESSION}/system/group/isForbid",
			data:{"groupId":groupId,"userId":ids,"forbid":id},
			success:function(result){
				
				if(id==1){
					if(result=="true"){
					alert("用户Id为"+ids+"已被禁言");
					window.location.href="${BASEPATH_IN_SESSION}system/group/groupEmp?groupId="+groupId;	
					}else{
						alert("用户Id为"+ids+"禁言失败");
					}
					
				}else{
					if(result=="true"){
						alert("用户Id为"+ids+"取消禁言");
						window.location.href="${BASEPATH_IN_SESSION}system/group/groupEmp?groupId="+groupId;	
						}else{
							alert("用户Id为"+ids+"禁言失败");
						}
				}
			},
			
		});
		
	}
	function quit(ids){
		var groupId = $("#groupId").val();
		
		
		$.ajax({
			type:"POST",
			url:"${BASEPATH_IN_SESSION}/system/group/quitGroup",//自动把所有对应的level全部添加
 			data: {"groupId":groupId,"userId":ids},
			success:function(data){
				window.location.href="${BASEPATH_IN_SESSION}system/group/groupEmp?groupId="+groupId;
			}
		});
	}
	
	function allIs(ids){
		window.location.href="${BASEPATH_IN_SESSION}system/group/allIsForbid?groupId="+ids;	
	}
	
	function allIsFalse(ids){
		window.location.href="${BASEPATH_IN_SESSION}system/group/allIsForbid2?groupId="+ids;
	}
	
</script>
</body>
</html>

