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

<title>银行信息</title>
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
	                	<p>你当前的位置：[金额查询]-[提现申请]-[银行信息填写]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table>

     <td>
     <form action="<%=basePath %>/sellerWithdraw/saveBankInfo" id="saveForm" method="post">
     <input type="hidden" name="adminUserId" value="${adminUser.id}">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>当前每天积分用户：</label>
			<span class="input">
			<font color="red"> ${adminUser.loginname}</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" >
			<label><span style="color: red">*</span>开户行：</label>
			<span class="input">
			<input type="text" id="bankName" name="bankName" value=""/>
			</span>
			<div class="clear"></div>
		</div> 
		<div class="rows" >
			<label><span style="color: red">*</span>卡号：</label>
			<span class="input">
			<input type="text" id="cardNo" name="cardNo" value=""/>
			</span>
			<div class="clear"></div>
		</div>
		 <div class="rows" >
			<label><span style="color: red">*</span>开户行地址：</label>
			<span class="input">
			<input type="text" id="bankAddress" name="bankAddress" value=""/>
			</span>
			<div class="clear"></div>
		</div> 
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button"  onclick="savePtype();" value="提交" />
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
	 var bankName = document.getElementById("bankName").value;
	 var cardNo = document.getElementById("cardNo").value;
	 var bankAddress = document.getElementById("bankAddress").value;
	 if(bankName == ""){
		alert("请输入开户行！");
		return false;
	 }
	 if(cardNo == ""){
		 alert("请输入银行卡号！");
		 return false;
	 }
	 if(bankAddress == ""){
		alert("请输入开户行地址！");
		return false;
	 }
	 $.ajax({
         url: "<%=basePath%>sellerWithdraw/saveBankInfo",
         type: "post",
         data: $("#saveForm").serialize(),
         success: function (data) {
             alert(data.message);
             location.href = "<%=basePath%>sellerWithdraw/sellerAssetQuery";
         }
     });
    //document.getElementById("saveForm").submit();
 }
 
 
 
</script>
</body>
</html>
