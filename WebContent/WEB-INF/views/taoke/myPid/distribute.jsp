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

<title>PID分配</title>
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

<body onload="setChecked();">
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
	                	<p>你当前的位置：[淘客管理]-[我的PID]-[PID分配]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table>

     <td>
     <form action="<%=basePath %>taoke/Mypid/savedistribute?id=${cityAdminuserPidDistribute.id}" id="saveForm" method="post">
     <input type="hidden" id="adminuserId" name="adminuserId" value="${cityAdminuserPidDistribute.adminuserId }"/>
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>PID</label>
			<span class="input">
			<font color="red"> ${cityAdminuserPidDistribute.adminuserTaokePid.pid}</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>PID说明</label>
			<span class="input">
			<font color="red"> ${cityAdminuserPidDistribute.adminuserTaokePid.pidname}</font>
			</span>
			<div class="clear"></div>
		</div>
		 <div class="rows" >
			<label>分配粉丝</label>
			<span class="input">
			<input type="text" id="name" name="name" value="" />
			 <span style="color: red">请输入粉丝手机号！</span>
			</span>
			<div class="clear"></div>
		</div> 
 		<div class="rows" >
			<label>粉丝名称</label>
			<span class="input">
			<input type="text" id="username" name="username" value="" />
			 <span style="color: red">请输入粉丝名称！</span>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button"  onclick="savePtype();" value="分 配" />
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
<script type="text/javascript" src="<%=basePath %>js/maputil.js"></script>
<script>
	
function savePtype(){
	var $name = $("#name");
	   $.ajax({
		   url:"${basePath}checkUserNameByAjax",
		   type:"post",
		   data:{"name":$name.val()},
	   	   async:true,
	   	   success:function(msg){
	   		   var json = msg;
	   		   if(json=="1"){
	   				alert("该用户不存在！");
	   				$("#name").val("");
	   				$("#name").focus();
	   		   }else if(json=="2"){
	   				$("#name").val("");
			   		alert("该用户已被分配！");
		   	   }else if(json=="0"){
		   			result=true;
		   			document.getElementById("saveForm").submit();
		   	   }
	   		
	   	   }
	   });
   
}

</script>
</body>
</html>
