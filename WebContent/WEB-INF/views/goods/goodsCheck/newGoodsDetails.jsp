<%@page import="com.axp.dao.impl.AdminUserDAOImpl" %>
<%@page import="com.axp.util.StringUtil" %>
<%@page import="com.axp.model.AdminUser" %>
<%@page import="com.axp.dao.AdminUserDAO" %>
<%@page import="com.axp.model.CashshopType" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css">
    <title></title>
 
 	<style type="text/css">
 	 img{
 	 	width: 600px;
 	 }
 	
 	</style>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
  
</head>

<body>
<form action="<%=basePath%>reBaseGoods/goodsDetails" method="post" id="form">
	<div>
		<div>
			<p id="text" value=""></p>
		</div>
		<div id="img" style="width: 600px"></div>
	</div>

</form>
	
</body>
<script type="text/javascript">
	$(function(){
		var list='<%=request.getAttribute("contect")%>';
		var json =eval('(' + list + ')');
		for(var i=0;i<json.length;i++){
			if(json[i].picture!=undefined){
			    var img=document.createElement("img");
			   img.src=""+json[i].picture+"";
			   $("#img").append(img);
			}
			if(json[i].text!=undefined){
			  var txt = $("<p>"+json[i].text+"</p>");
			   $("#text").append(txt);
			}
			
		}
		
		
	})
</script>
</html>
