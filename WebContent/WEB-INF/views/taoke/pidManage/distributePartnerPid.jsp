<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>PID录入</title>
	<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
	<link href="<%=basePath %>css/pid/distributePartnerPid.css" rel="stylesheet" type="text/css"/>
	
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	
	<style type="text/css">
		#yes{
			text-align: left;
				margin-left:100px;
				margin-bottom: 20px;
		}
	</style>
	</head>

	<body >

		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[淘客管理]-[分配PID]</p>
				</div>
				<hr align="center" />
			</div>
			<div class="container">
				<form>
					<input type="hidden" id="id" value="${tkldPid.id}">
					<!-- pid级别 -->
						
						<div class="layout_input">
							<div>
								<span class="title">事业合伙人PID</span>
								<span id="pid" style="color: red">${tkldPid.pId}</span>
							</div>
						</div>
						
						
						<div class="layout_input">
							<div>
							<span class="title" >每天积分粉丝号</span>
							<input type="text" id="userPhone" value="${tkldPid.users.phone}" />
							<span id="tips" >手机号</span>
							</div>
						</div>
						
						
						<div class="layout_input">
							<div>
								<span class="title">分配备注</span>
								<input type="text" id="usersRemark" name="usersRemark" value="${tkldPid.usersRemark}" />
							</div>
						</div>
						
						<div class="layout_input">
								<div>
									<lable  class="title" for="checkbox">是否兜底:</lable>
									<lable class="check" >
									是:
									<input  id="s1" name="check" type="radio"  value="1" <c:if test="${tkldPid.provinceEnum.id!=null}">checked="checked"</c:if>>
									</lable>
									
									<lable  class="check">
									否:
									<input id="s2" name="check" type="radio"  value="0" checked="checked" <c:if test="${tkldPid.provinceEnum.id!=null}">checked="checked"</c:if>> 
									</lable>
									
									
									<span style="color: red">
										本区域内无上级用户升级合伙人使用本PID进行兜底
									</span>	
								</div>
						</div>
						
						<div id="yes" style="color: red";>
							本区域内使用该PID进行兜底
						</div>
						
					<div class="confirm">
							<input type="button" name="" id="sub" value="提交" />
					</div>
				</form>
			</div>

		</div>

				<script type="text/javascript">
				
				$("#yes").hide();
				
				$("input[name=check]").click(function(){
					var val=$(this).val();
					if(val==1){
					$.ajax({
						async:false,
						url:"taoke/pid/currentCityIsValid",
						success:function(data){
							if(data.flag==1){
								alert("当前地区已经有了地级市事业合伙人")
								$("#s1").attr("checked",false);
								$("#s2").attr("checked",true);
								$("#yes").hide();
							}
													
							if(data.flag==2){
								$("#yes").show();
							}
						
							if(data.flag==3){
								alert("当前用户并无地区记录");
								$("#s1").attr("checked",false);
								$("#s2").attr("checked",true);
								$("#yes").hide();
							}
						}
					});
					}
				})	
				
				
						
					$("#sub").click(function(){
						
						var id= $("#id").val();
						var userPhone=$("#userPhone").val();
						var usersRemark=$("#usersRemark").val();
				
						var checkbox=$('input:radio:checked').val();//$("input[name='check'][checked]").val();
						
						var data={id:id,userPhone:userPhone,usersRemark:usersRemark,checkbox:checkbox}
						$.ajax({
							async:false,
							url:"taoke/pid/saveOrUpdatePartnerPid",
							data:data,
							 dataType: "json",
							success:function(data){
								if(data.flag!="1"){
									alert(data.flag);
									$("#tips").text(data.flag);
								}else{
									location.href="taoke/pid/toPidList";
								}
							}
						})
					});
					
					
					
					
				</script>
						
	
	</body>

</html>