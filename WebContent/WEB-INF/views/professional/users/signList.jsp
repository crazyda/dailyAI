<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户签到列表</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/layer3/layer.js"></script>
	
</head>
<script >
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
		source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	   return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
	//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
	   source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
	else
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}
</script>
<body>
		<div class="main" id="div_main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[业务管理]-[用户敲到列表]</p>
				</div>
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			</div>
			<!--end of header-->
			<div class="pageColumn" id="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						
						<th width="5%" style="height:50px;">序号</th>
						<th width="10%">用户</th>
						<th width="8%">签到信息</th>
						<th width="10%">获得奖励</th>
						<th width="10%">签到时间</th>
					</thead>
					<tbody>
					<c:forEach items="${asList}" var="u">
					<tr>
					
					<td>${u.id }</td>
           	 		<td>${u.remark}</td>
            		<td>${u.newHands }</td>
					<td>${u.adminUser.loginname }</td>
					<td><fmt:formatDate value="${u.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						
					<td><fmt:formatDate value="${u.refreshTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
<script>
   function editUser(id,isTeam){
		 $.ajax({
		        url: "<%=basePath%>/professional/user/updateTeamStatus",
		        type: "post",
		        dataType:"json",
		        data: {"id": id,"isTeam":isTeam,
		        	
		        	
		        },
		        success: function (data) {
		            if (data.flag) {
		            	layer.alert(data.msg,{icon: 1},function(){
		            		window.location.href = "teamList";
		            	});
		            } else {
		            	layer.alert(data.msg,{icon: 2});
		            }
		        }
		    });
	}
</script>
</body>
</html>

