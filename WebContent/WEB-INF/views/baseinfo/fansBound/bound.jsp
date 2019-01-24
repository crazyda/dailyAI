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

<title>粉丝绑定</title>
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
	                	<p>你当前的位置：[系统管理]-[粉丝绑定]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table>

     <td>
     <form action="<%=basePath %>system/fans/bound" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>当前后台用户</label>
			<span class="input">
			<font color="red"> ${adminUser.loginname}</font>
			</span>
			<div class="clear"></div>
		</div>
		 <div class="rows" >
			<label>绑定粉丝号</label>
			<span class="input">
			<input type="text" id="phone" name="phone" value="${users.phone}"/>
			 <span style="color: red">请输入粉丝手机号！</span>
			</span>
			<div class="clear"></div>
		</div> 
		<dl style="padding-left:10.2%; padding-top:1%; padding-bottom: 1%">
			<label>绑定状态</label>
        	<dd style="display:inline-block;margin-left:13%;">
            	<input type="radio" name="status" value="10"/>绑定
            	<input type="radio" name="status" value="-1"/>解除绑定
        	</dd>
		</dl>
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
   $.ajax({
       type: 'POST',
       url: 'savebound',
       data: $('#saveForm').serialize(),
       async: false,
       dataType:'json',
       success: function (statusMap) {
      		 alert(statusMap.message);
      		 window.location.href = "${BASEPATH_IN_SESSION}/system/fans/bound";
      		 return;
       },
       error: function (statusMap) {
           alert("提交失败");
       }
   });
}


</script>
</body>
</html>
