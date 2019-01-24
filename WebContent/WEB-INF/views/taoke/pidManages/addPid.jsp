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
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<%-- <script type="text/javascript" src="<%=basePath %>js/mian.js"></script> --%>
		<style type="text/css">
			* {
				margin: 0px;
				padding: 0px;
			}
			
			.container {
				margin-left: 10%;
				margin-top: 20px;
				width: 1024px;
				height: 700px;
				/* border: 1px solid black; */
			}
			
			.choice {
				width: 800px;
				height: 65px;
				
			}
			
			.line {
				height: 75px;
				line-height: 75px;
				float: left;
				width: 300px;
				text-align: center;
				margin-top: 7px;
			}
			
			.line2 {
				float: left;
				width: 500px;
				height: 75px;
				line-height: 75px;
				text-align: left;
				
			}
			
			.line2>select {
				height: 45px;
				width: 150px;
				line-height: 45px;
				margin-top: 20px;
			}
			
			.desc {
				float: left;
				width: 300px;
				height: 50px;
				line-height: 50px;
				text-align: center;
				
			}
			
			.layout_input {
				width: 500px;
				height: 50px;
				line-height: 50px;
				text-align: left;
				float: left;
			}
			
			.layout_input>input {
				
				width: 200px;
				height: 30px;
				line-height: 30px;
				text-align: left;
				margin-top: 10px;
				
			}
			.layout_div{
				margin: 20px 0 0 0;
			}
			.tips{
				margin-left: 30px;
				display: none;
				color: red;
			}
			.parent{
				
				
			}
			.layoutPid{
				width: 800px;
				height: 80px;
				margin: 20px 0 0 0;
				float: left;
				border-top: 1px solid black;
			}
			
			
			#choicePid{
				font-size:18px;
				width: 350px;
				font-weight: bold;
				overflow-y:auto;
			}
			.tip{
				font-size: 12px;
				color: red;
			}
			.confirm{
				width: 100%;
				height: 100px;
				float: left;
				margin-top: 50px;
				
			}
			.confirm>input{
				width: 150px;
				height: 50px;
				font-size: 24px;
				margin-left: -250px;
			}
			
			#level{
				font-size:18px;
				font-weight: bold;
			}
		</style>
	</head>

	<body>

		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[淘客管理]-[录入PID]</p>
				</div>
				<hr align="center" />
			</div>

			<div class="container">
				<form>
					<input type="hidden" id="id" value="${tkldPid.id}">
					<!-- pid级别 -->
					<div class="choice">
						<div class="line">PID级别 :</div>
						<div class="line2">
							<select name="level" id="level">
								<option value="1" <c:if test="${tkldPid.level==1}">selected</c:if> >运营商</option>
								<option value="2" <c:if test="${tkldPid.level==2}">selected</c:if> >事业合伙人</option>
								<option value="3" <c:if test="${tkldPid.level==3}">selected</c:if> >合伙人</option>
						 	</select>
							<span class="tips">提示:</span>
						</div>
					</div>

					<div class="layout_div">
						<div class="desc">
							<div>PID :</div>
						</div>

						<div class="layout_input">
							<input type="text" name="pid" id="pid" value="${tkldPid.pId}" />
							<span class="tips">提示:</span>
						</div>
					</div>
					
					<div class="layout_div">
						<div class="desc">
							<div>代理系统登录名称 :</div>
						</div>

						<div class="layout_input">
							<input type="text" name="name"  value="${tkldPid.ldLoginName}"/>
							<span class="tips">提示:</span>
						</div>
					</div>
					
					<div class="layout_div">
						<div class="desc">
							<div>代理系统登录密码 :</div>
						</div>

						<div class="layout_input">
							<input type="text" name="pwd" value="${tkldPid.ldLoginPwd}"/>
							<span class="tips">提示:</span>
						</div>
					</div>
					
					
					<div class="layout_div">
						<div class="desc">
							<div>代理系统账号备注 :</div>
						</div>

						<div class="layout_input">
							<input type="text" name="ldLoginReamrk" value="${tkldPid.ldLoginReamrk}" />
							<span class="tips">提示:</span>
						</div>
					</div>
					
					<div class="layout_div">
						<div class="desc">
							<div>每天积分系统备注 :</div>
						</div>

						<div class="layout_input">
							<input type="text" name="remark"  value="${tkldPid.remark}"/>
							<span class="tips">提示:</span>
						</div>
					</div>
					
					<div class="layoutPid">
						<div class="line">上级PID :</div>
						<div class="line2">
							<select id="choicePid"  name="parentPid">
								<option value="0">请选择</option>

								<c:forEach items="${list}" var="list">
									<option value="${list.id}" <c:if test="${list.id==tkldPid.tkldPid.id}">selected</c:if>>${list.remark}</option>
								</c:forEach>
							</select>
							<span class="tip">新增代理时,可不填上级</span>
						</div>
						
					</div>
					
					<div class="layout_div">
						<div class="desc">
							<div>查找上级PID</div>
						</div>

						<div class="layout_input">
							<input type="text" id="seek"/>
						</div>
					</div>
					
					<div class="confirm">
							<input type="button" name="" id="sub" value="提交" />
					</div>
				</form>
			</div>

		</div>

				<script type="text/javascript">
							$(function(){
								
									var datas;
									//控制pid
									var flag=true;
										//提交表单
										$("#sub").click(function(){
											checkSub(flag);
										});
									});
									
									
								$("#seek").keyup(function(ee){
									var flag=false;
								    var keyword=$("#seek").val();
									var choicePid=document.getElementById("choicePid");
									var options=document.getElementById("choicePid").options;
									var array=[];
									for(o in options){
										if(!options[o].text){
											continue;
										}
										if(coverString(keyword,options[o].text)){
											array.push(options[o]);
										}									
									}
									if(ee.keyCode==8){
										if(keyword.trim().length==0){
											array.length=0;
										}
									}
									//alert(array.length);
									if(array.length>0){
										choicePid.options.length=0;
										for(item in array){
											choicePid.options.add(new Option(array[item].text,array[item].value));	
										}
									}
									
									else{
										choicePid.options.length=0;
										for(item in datas){
											choicePid.options.add(new Option(datas[item].key,datas[item].val));	
										}
									}
								})
								
									function  coverString(subStr,str){
									try{
											return str.trim().toLowerCase().indexOf(subStr.trim().toLowerCase())>-1;
										
									}catch(e){
										//	console.log(subStr+"  "+str);											
									}
												
										} 
								
							
									$("#level").change(function(){
											var level= $("#level").val();
											var choicePid=  $("#choicePid");
											if(level>1){
												$.getJSON("taoke/pids/findParentTkldPid",{level:level},function(data){
													$(choicePid).children().remove();
													choicePid.append("<option value=0>请选择</option>")
													for(var i=0;i<data.length;i++){
														var option="<option value="+data[i].val+">"+data[i].key+"</option>";
														choicePid.append(option);
													}
													datas=data;
												});
											}
											else{
												$(choicePid).children().remove();
												choicePid.append("<option value=0>请选择</option>")
											}
										 
									});
									
									
									$("#pid").blur(function(){
										var pid= $("#pid").val();
										var span= $("#pid").next("span");
										if(pid.length==30){
											
											$.post("taoke/pids/checkPid",{pid:pid},function(data){
												if(data=='true'){	//如果是true 那么代表此pid已经录入过了
													$(span).text("该pid已经录入过了");
													$(span).show();
													$(span).attr("color","red");
													//控制pid是否重复
													flag=false;
												}
												else{
													$(span).hide();
													flag=true;
												}
											});
										}
										else{
											$(span).hide();
											flag=true;
										}
									});
								
					function checkSub(flag){
							var level= $("select[name=level]").val();
							var pId= $("input[name=pid]").val();
							var ldLoginName= $("input[name=name]").val();
							var ldLoginPwd= $("input[name=pwd]").val();
							var pwds= $("input[name=pwds]").val();
							var ldLoginReamrk= $("input[name=ldLoginReamrk]").val();
							var remark= $("input[name=remark]").val();
							var parentPidId= $("select[name=parentPid]").val();
							var id=$("#id").val();
							if(!pId){
									alert("pid不能为空");
								return false ;
							}
							if(!ldLoginName){
								alert("代理名称不能为空");
								return false ;
							}
							if(!ldLoginReamrk){
								alert("代理备注不能为空");
								return  false;
							}
							if(!remark){
								alert("每天积分备注不能为空");
								return false ;
							}
							if(level>1){
								//验证如果录入的不是代理商等级 那么底部就必须关联上级;
								if(parentPidId==0){
									alert("请选择上级");
									return false;
								}
							}
							if(flag==false){
								alert("该pid已经录入过了");
								return false;
							}
							
							var data={id:id,level:level,pId:pId,ldLoginName:ldLoginName,ldLoginPwd:ldLoginPwd,
									ldLoginReamrk:ldLoginReamrk,remark:remark,parentPidId:parentPidId}
							document.getElementById("sub").disabled = 'disabled';
								 $.ajax({
									async:false,
									url:"taoke/pids/saveTkldPid",
									data:data,
									success:function(data){
										 if(flag==1){
											alert("新增成功");
											location.href='taoke/pid/toPidList';
											}
											else{ 
												alert("新增失败");
												document.getElementById("sub").disabled ='';
											}
									}
								}) 
							
							return true;
					}
						
				</script>
	
	</body>

</html>