<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>分佣比例分配</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>css/add/css/main.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<style type="text/css">
	.center{
		margin-left:20%;
		margin-top:5%;
		height: auto;
		padding-bottom:50px;
	}
	.center div{
		margin-top: 50px;
	}
	span{
	width:150px;
	display:inline-block;
		font-size: 22px;
		font-weight: bold;
	}
	input{
	text-align:center;
		font-size: 22px;
		width: 180px;
	}
	
	#btn{
		margin-left: 90px;
		-webkit-appearance: none;
	    border-radius: 0;
	    border: 1px solid #ddd;
	        color: #fff;
    background-color: #2d84b9;
    cursor: pointer;
	}
	#btn:HOVER{
		  background-color: #0D4B71;
	}
	
	
</style>
</head>

<body>
	<div class="div_new_content">
		<div style="margin-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="30" background="images/tab_05.gif"><table
							width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>

								<td><table width="100%" border="0" cellspacing="0"
										cellpadding="0">
										<tr>
											<td width="46%" valign="middle"><table width="100%"
													border="0" cellspacing="0" cellpadding="0">
													<tr>

														<td width="95%" >

															<div class="header-left">
																<p>你当前的位置：[系统管理]-[分佣比例]</p>
															</div>
														</td>
													</tr>
												</table>
											</td>

										</tr>
									</table>
								</td>
							</tr>
						</table></td>
				</tr>
				<tr>
					<td>
							<div class="center">
								
								<form id="sub">
									<input type="hidden" value="${CommissionRate.id}" />
									<div>
										<span>平台服务费:</span>
										<input type="text" name="terrace" value="${CommissionRate.terrace}">
									</div>
									<div>
										<span>代理商:&nbsp&nbsp</span>
										<input type="text" name="agent" value="${CommissionRate.agent}" />
									</div>
									<div>
										<span>事业合伙人:</span>
										<input type="text" name="career" value="${CommissionRate.career}" />
										
										<span>&nbsp事业自购:</span>
										<input type="text" name="career2" value="${CommissionRate.career2}" />
									</div>
									<div>
										<span>合伙人:&nbsp&nbsp</span>
										<input type="text" name="partner"   value="${CommissionRate.partner}" />
									</div>
									
									<div style="margin-left: 20px">
									已分配:
										<span id="rate">0%</span>
									未分配:
										<span id="rate2">100%</span>
									</div>
								</form>
								<div>
									<input type="button" id="btn" value="确认" > 
								</div>
							</div>
					</td>
					
					<script type="text/javascript">
					
						
						$(function(){
							var num=100;
							var flag=true;
							var rate=0;
							var downV;
							$("#rate").text(getSum()+"%");
							$("#rate2").text(100-getSum()+"%");
							
							$("input").keydown(function(){
								downV=$(this).val();
							})
						
							$("input:[name!='career2']").keyup(function(e){
								var inNum=$(this).val();
								if(e.keyCode!=8){
									if(!validate(inNum)){
										$(this).val(downV);
									}								
								}
								$("#rate").text(getSum()+"%");
								$("#rate2").text(100-getSum()+"%");
							});
							
							function validate (rate){
								 return getSum()<=100;
							}
							
							
							$("#btn").click(function(){
								submit();
							});
							
							function submit(){
								var value=getSum();
								if(validateInput()){
									if(value>0){
										$.ajax({  
									         type : "post",
									          url : "<%=basePath %>baseinfo/updateCommissionRate",  
									          data : $("form").serialize(), 
									          async : false,  
									          success : function(data){
									        	  if(data.success){
									        		  alert(data.msg);
									        		  location.href="<%=basePath %>/login/indexPage";
									        	  }	else{
									        		  alert(data.msg);
									        	  }
									          }  
									     });
									}else{
										alert("分配比例出错");
									}
								}
							}
							
							function getSum (){
								var value=0;
								var inputs= $("#sub input[name!='career2']:visible");
								for(var i=0;i<inputs.length;i++){
									if(!inputs[i].value){
										continue;
									}
									value+=parseFloat(inputs[i].value);
									//alert(value);
								}
								return value;
							}
							
							function validateInput(){
								$("#sub input:visible").each(function(){
									if(!$(this).val()){
										alert("请填写完整!");
										return false;
									}
								});
								return true;
							}
							
						});
						
					
					</script>
				</tr>

			</table>
</div>
</div>
</body>
</html>