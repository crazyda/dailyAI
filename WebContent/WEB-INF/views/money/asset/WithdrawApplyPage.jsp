<%@page import="com.axp.util.StringUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Levels"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=FhqpjmNavgBUzcjqOlHfeqrR"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.0.js"></script>

<title>提现申请</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>
</head>

<body>
<div class="div_new_content">
  <div style="margin-left:10px;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
	                <div class="header-left">
	                	<p>你当前的位置：[资金管理]-[金额查询]-[提现申请]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table>

     <td>
     <form action="<%=basePath %>/sellerWithdraw/saveWithdrawApply" id="saveForm" method="post">
     <input type="hidden" name="adminUserId" value="${adminUser.id}">
     <input type="hidden" name="flag" value="true">
     
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>当前用户：</label>
			<span class="input">
			<font color="red"> ${adminUser.loginname}</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" >
			<label>现有金额：</label>
			<span class="input">
			<font id="verifyIncome" color="red">${totalMoney}</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" >
			<label><font color="red">*</font>提现金额：</label>
			<span class="input">
			<input type="text" id="money" name="money" value="" />
			<font id="tips" color="red"></font>
			</span>
			<div class="clear"></div>
		</div> 
		 <div class="rows" >
			<label>提现手续费：</label>
			<span class="input">
			<input type="text" id="totalFee" name="totalFee" value="" disabled="true"/>
			</span>
			<div class="clear"></div>
		</div> 
		<div class="rows" >
			<label>提现账号名：</label> 
			<span class="input">
				<c:choose>
					<c:when test="${data.name!=null}">
						<input type="text" id="name" name="name" value="${data.name}" disabled="true"/>
					</c:when>
					<c:otherwise>
						<span onclick="applyInfo('${adminUser.id }');" style="cursor: pointer; color: red;text-decoration: underline;">请完善提现资料!</span>
					</c:otherwise>
				</c:choose>
			</span>
			<div class="clear"></div>
		</div> 
		<c:if test="${data.name!=null}">
		<div class="rows" >
			<label>提现资料-电话：</label>
			<span class="input">
			<input type="text" id="phone" name="phone" value="${data.phone}" disabled="true"/>
			</span>
			<div class="clear"></div>
		</div> 
		<div class="rows" >
			<label>提现资料-身份证号：</label>
			<span class="input">
			<input type="text" id="code" name="code" value="${data.code}" disabled="true"/>
			</span>
			<div class="clear"></div>
		</div>
		</c:if>
		
		
        <div class="rows" >
			<label>提现银行：</label>
			<span class="input">
				<c:choose>
					<c:when test="${bank.bankName!=null}">
						<input type="text" id="bankName" name="bankName" value="${bank.bankName}" disabled="true"/>
					</c:when>
					<c:otherwise>
						<span onclick="applyBackInfo('${adminUser.id }');" style="cursor: pointer; color: red; text-decoration: underline;">请添加银行卡信息!</span>
					</c:otherwise>
				</c:choose>
			</span>
			<div class="clear"></div>
		</div> 
		<c:if test="${bank.bankName!=null}">
		<div class="rows" >
			<label>提现银行卡号：</label>
			<span class="input">
			<input type="text" id="cardNo" name="cardNo" value="${bank.cardNo}" disabled="true"/>
			</span>
			<div class="clear"></div>
		</div> 
		<div class="rows" >
			<label>提现银行地址：</label>
			<span class="input">
			<input type="text" id="bankAddress" name="bankAddress" value="${bank.bankAddress}" disabled="true"/>
			</span>
			<div class="clear"></div>
		</div>
		</c:if> 
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button" id="btn" onclick="savePtype();" value="提交" />
			</span>
			<div class="clear"></div>
		</div>
      </div>
     </div>
     </form> 
     </td>

</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script type="text/javascript" src="<%=basePath %>js/coordtransform.js"></script>
<script>
function savePtype(){
	var money =document.getElementById("money").value;
	var verifyIncome= $("#verifyIncome").text();
	var name= $("#name").val();
	var bankName = $("#bankName").val();
	if(money==""){
		alert("请填写提现金额！");
		return;
	}
	if(name == undefined || bankName == undefined){
		alert("提交失败，请先完善所需资料！");
		return;
	}
	 var totalFee=money*0.005;
	 var fee =null;
	 if(totalFee>2){
		fee = totalFee;
	 }else{
		fee = 2;
	 }
	 $("#totalFee").val(fee);
	 var totalMoney =Number(money) + Number(fee); 
	 if(totalMoney >=verifyIncome){
		 $("flag").val("false");
	 }
	if($("flag") && name!=undefined){
		$.ajax({
	         url: "<%=basePath%>sellerWithdraw/saveWithdrawApply",
	         type: "post",
	         data: $("#saveForm").serialize(),
	         success: function (data) {
	             alert(data.message);
	             location.href = "<%=basePath%>sellerWithdraw/sellerAssetQuery";
	         }
	     });
   		// document.getElementById("saveForm").submit();
	}
 }
 
 
 $(function(){
	 $("#bankName").change(function(){
		 var bankName = document.getElementById('bankName');
	 })
	 
 })
 
 function applyInfo(){
	 location.href="<%=basePath%>sellerWithdraw/withdrawApplyUserInfo";
 }
 
 function applyBackInfo(){
	 location.href="<%=basePath%>sellerWithdraw/withdrawApplyBankInfo";
 }
 
 
 $(function(){
	 $("#money").change(function(){
		 var verifyIncome= $("#verifyIncome").text();
		 var  money= $("#money").val();
		 var totalFee=money*0.005;
		 var fee =null;
		 if(totalFee>2){
			fee = totalFee;
		 }else{
			fee = 2;
		 }
		 $("#totalFee").val(fee);
	     var totalMoney =Number(money) + Number(fee); 
	     
		 if(totalMoney >=verifyIncome || verifyIncome<202){
			 $("#tips").text("提现金额加手续费金额不能大于现有金额并且当次提现不能小于200元!手续费为提现金额0.5%最低2元");
			 $("flag").val("false");
			 $("#btn").attr({"disabled":"true"});
			 $("#btn").css('background','#ccc');
		 }
		 else{
			 $("#btn").css('background','#2d84b9');
			 $("#tips").text("");
			 $("#btn").removeAttr("disabled");
		 }
	 })
 })
</script>
</body>
</html>
