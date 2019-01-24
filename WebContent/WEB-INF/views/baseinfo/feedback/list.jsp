<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>意见反馈</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
</head>

<body>
	<form>
		<div class="main" id="div_main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[系统管理]-[意见反馈]</p>
				</div>
				
			</div>
			<!--end of header-->
			<div class="pageColumn" id="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%" style="height:50px;">序号</th>
						<th width="10%">用户</th>
						<th width="10%">内容</th>
						<th width="20%">图片</th>
						<th width="10%">时间</th>
						<th width="15%">联系方式</th>
					</thead>
					<tbody>
					<c:forEach items="${list}" var="feedback">
					<tr>
            		<td>${feedback.id}</td>
            		<td>${feedback.user.name}</td>
            		<td>${feedback.content}</td>
            		<td class="td" style="position: relative;">
            	    	${feedback.images}
            		</td> 
					<td>${feedback.createTime}</td>
					<td>${feedback.connectPhone}</td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!--end of pageColunm-->
			<div class="footer" id="footer">
				<div class="page">
					<table>
						<tr>
							<div class="page-box common-page-box" style="text-align:center">
								${requestScope.pageFoot }
							</div>
						</tr>
					</table>

				</div>
			</div>
		</div>
		<!--end of main-->
	</form>
<script type="text/javascript">

$(function(){
	var tbody =$("table.list tbody tr");
	for(var i=0;i<tbody.length;i++){
		var td =$(tbody[i]).find(".td");
		var dataObj=eval("("+$(td).text()+")");
		var _html="";
		for(var j=0;j<dataObj.length;j++){
			_html+="<img class=\"imgPic\" src=\"${RESOURCE_LOCAL_URL}"+dataObj[j].image+"\" width=\"50px\" height=\"50px\"></img>&nbsp;&nbsp;&nbsp;";
		}
		_html+="";
		td.html(_html);
	}
	
	$(".imgPic").mouseover(function(e){
		  var img = document.createElement("img"); 
		  img.src=this.src;
		  img.style.width="300px";
		  img.style.heigth="300px";
		  img.style.position="absolute";
		  img.setAttribute("id", "pic"); 
		  img.style.zIndex="2147483647";
		  img.style.top="40%"
		  img.style.left="40%";
		 var b= document.getElementsByTagName("body");
		$(b).append(img);
	});
	
	$(".imgPic").mouseout(function(){
		$("#pic").remove();
	});
});
	
</script>
</body>
</html>

