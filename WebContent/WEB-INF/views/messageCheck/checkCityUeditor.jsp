<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="<%=basePath %>ueditor/themes/default/ueditor.css">

<title>图片</title>
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
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=basePath %>ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>

</head>



<body>
<div class="div_new_content">
<div style="margin-left:10px;">

                    <div class="header-left">
						<p>你当前的位置：[业务管理]-[推送消息审核列表]-[审核消息]</p>
					</div>
     <form action="<%=basePath%>messageCheck/save" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
			 <input name="id" type="hidden" id="id" value="${message.id }">
			 <input type=hidden id="imageurls" name="cover" value="" />
			 <div class="rows">
					<label> 标题：</label>
					<span class="input">
					  ${message.title }
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 作者：</label>
					<span class="input">
					  ${message.author }
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 说明：</label>
					<span class="input">
						${message.remark }
					</span>
					<div class="clear"></div>
			</div>
	 
			 <div class="rows">
					<label>内容编辑：</label>
					<span class="input">
			     		<!-- 加载编辑器的容器 -->
    					${message.content }
			 		</span>
					<div class="clear"></div>
			</div>
			
			<div class="rows">
					<label> 发送方式：</label>
					<span class="input">
					${message.isTimer==0?"即时":"定时" }
					</span>
					<div class="clear"></div>
			</div>
			
				<div class="rows">
					<label>审核状态：</label>
					<span class="input">
						<select name="checkStatus">
							<option>--请选择--</option>
							<option value="1">审核通过</option>
							<option value="2">审核不通过</option>
						</select>
					</span>
				</div>
				<div class="rows">
					<label></label>
					<span class="input">
					  <input type="button" class="button" onclick="savePtype();" value="审 核" />
					</span>
					<div class="clear"></div>
				</div>
				</div>
			</div>
        
        </form>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script>
function savePtype(){
    document.getElementById("saveForm").submit();
 }

</script>
</body>
</html>
