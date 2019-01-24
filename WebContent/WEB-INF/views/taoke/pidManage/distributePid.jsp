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
	<link href="<%=basePath %>css/pid/distrbutePid.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<%-- <script type="text/javascript" src="<%=basePath %>js/mian.js"></script> --%>
	<style type="text/css">
		.s1,.s2,.s3{
			height: 30px;
			font-size: 18px;
			font-weight: bold;
		}
	</style>
	</head>
	
	<body>

		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[淘客管理]-[分配PID]</p>
				</div>
				<hr align="center" />
			</div>

			<div class="container">
				<form>
					<!-- 代理级联下拉 -->
					<div class="choice">
						
						<div class="line2">
							<span>运营中心:</span>
							<select class="s1" id="s1">
								<option value="0">请选择</option>
								<c:forEach items="${list }" var="list" >
									<option value="${list.id}" <c:if test="${list.id==adverone.parentId}">selected</c:if> >${list.loginname}</option>
								</c:forEach>
							</select>
						</div>
						
						<div class="line2">
						<span>代理:</span>
							<select class="s2" id="s2">
								<option value="0">请选择</option>
								<c:forEach items="${adveroneList}" var="list" >
									<option value="${list.id}" <c:if test="${list.id==adverone.id}">selected</c:if> > ${list.loginname}</option>
								</c:forEach>
							</select>
						</div>
						
						
						<div class="line2">
						<span>商圈:</span>
							<select class="s3" id="s3">
								<option value="0">请选择</option>
								<c:forEach items="${advertwoList}" var="list">
									<option value="${list.id}" <c:if test="${list.id==advertwoId}">selected</c:if> >${list.loginname}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="user">
							<input type="hidden" id="daiLiPid" value="${tkldPid.id}"/>
						<div class="">代理级别PID:<span>${tkldPid.remark}_${tkldPid.pId}</span></div>
					</div>
					
					<div class="desc">
						<div>分配说明:											
							<input type="text" name="desc" value="${tkldPid.adminUserReamrk}" id="desc" />
							
							
						</div>
					</div>
					
					<div class="sub">
							<input type="button" value="提交" id="sub" />
					</div>
				</form>
			</div>

		</div>
				<script type="text/javascript">
				
						
					$("#sub").click(function(){
						subCheck();
					});
				
					function subCheck(){
						
						var s2= $("#s2").val();
						var s3= $("#s3").val();
						var desc=$("#desc").val();
						if(s2==0){
							alert("代理商不能为空!");
							return false;
						}
						if(!desc){
							alert("分配说明不能为空!");
							return false;
						}
						var userId="";
						if(s3>0){
							userId=s3;
						}else{
							userId=s2;
						}		
						var data={userId:userId,daiLiPid:$("#daiLiPid").val(),desc:desc};
						$.post("taoke/pid/distributePid",data,function(result){
								if(result==0){
									alert("该用户已有PID!");
								}
								else{
									location.href="taoke/pid/toPidList";
 								}
						});
					}
						
						//运营商选择时
						$("#s1").change(function(){
							var s1= $("#s1");
							var s2=$("#s2");
							console.log(s1);
								if($(s1).val()>1){
									$.getJSON("taoke/pid/changeSelect",{id:$(s1).val()},function(data){
										$(s2).children().remove();
										s2.append("<option value=0>请选择</option>")
										for(var i=0;i<data.length;i++){
											var option="<option value="+data[i].val+">"+data[i].key+"</option>";
											s2.append(option);
										}
									});
								}
								else{
									$(s2).children().remove();
									s2.append("<option value=0>请选择</option>")
									var s3=$("#s3");
									$(s3).children().remove();
									s3.append("<option value=0>请选择</option>")
								}
						});
						
						$("#s2").change(function(){
							var s2= $("#s2");
							var s3=$("#s3");
								if($(s2).val()>1){
									$.getJSON("taoke/pid/changeSelect",{id:$(s2).val()},function(data){
										
										$(s3).children().remove();
										s3.append("<option value=0>请选择</option>")
										for(var i=0;i<data.length;i++){
											var option="<option value="+data[i].val+">"+data[i].key+"</option>";
											s3.append(option);
										}
									});
								}
								else{
									$(s3).children().remove();
									s3.append("<option value=0>请选择</option>")
								}
						});
						
				</script>
	
	</body>

</html>