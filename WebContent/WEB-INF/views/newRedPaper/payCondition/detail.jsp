<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>粉丝金额变动情况</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
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

<form action="<%=basePath%>newRedPaper/payCondition/getRedpaperDetail?redpId=${adminuserRedpaper.id}" method="post">
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[红包管理]-[红包派发]-[派发详情]</p>
				</div>
				 <div class="header-right">
		            <div class="header-right-cell-line"></div>
		            <div class="header-right-cell" onclick="doRequest()" style="cursor: pointer;">
		                <p>返回</p>
		            </div>
		            <script>
		                function doRequest(){
		                   history.go(-1);
		                }
		            </script>
	        	</div>
				<hr align="center" />
			</div>
			<div class="div_search">
	        <form id="searchOrChangePage" method="post">
	            <span style="float:right; margin-right:30px;font-size: 18px;color: red">领取总金额：${money}元</span>
	        </form>
    		</div>
    		
			<div id="products" class="r_con_wrap" >
		    	 <div class="r_con_form" >
		    	 	<div class="rows">
					<label> 派发人:</label>
					<span class="input">
						<td>${adminuserRedpaper.adminUser.username }</td>
					</span>
					<div class="clear"></div>
		        	</div>
		    	 </div>
		    	 <div class="r_con_form" >
		    	 	<div class="rows">
					<label> 派发金额:</label>
					<span class="input">
						<td>${adminuserRedpaper.totalMoney}</td>
					</span>
					<div class="clear"></div>
		        	</div>
		    	 </div>
		    	 <div class="r_con_form" >
		    	 	<div class="rows">
					<label> 派发个数:</label>
					<span class="input">
						<td>${adminuserRedpaper.totalQuantity}</td>
					</span>
					<div class="clear"></div>
		        	</div>
		    	 </div>
		    	  <div class="r_con_form" >
		    	 	<div class="rows">
					<label> 派发时间:</label>
					<span class="input">
						<td><fmt:formatDate value="${adminuserRedpaper.createtime}" type="date" pattern="yyyy-MM-dd"/></td>
					</span>
					<div class="clear"></div>
		        	</div>
		    	 </div>
		    	 <div class="r_con_form" >
		    	 	<div class="rows">
					<label> 红包类型:</label>
					<span class="input">
						<td>
            				<c:choose>
            					<c:when test="${adminuserRedpaper.type==10}">随机红包</c:when>
            					<c:otherwise>定额红包</c:otherwise>
            				</c:choose>
            			</td>
					</span>
					<div class="clear"></div>
		        	</div>
		    	 </div>
		    	 <div class="r_con_form" >
		    	 	<div class="rows">
					<label> 红包地区:</label>
					<span class="input">
						<td>${adminuserRedpaper.adminUser.provinceEnum.provinceEnum.name}</td>
					</span>
					<div class="clear"></div>
		        	</div>
		    	 </div>
		    </div>		
		    
		    <div class="pageColumn">
		    	<table class="list" style="width:98%;margin:0 auto;">
		            <thead>
		            <th width="5%" style="height:50px;">序号</th>
		            <th width="12%">领取用户</th>
		            <th width="12%">领取金额</th>
		            <th width="12%">领取时间</th>
		            <th width="10%">红包类型</th>
		            <th width="12%">红包地区</th>
		            </thead>
		            <tbody>
		            <c:forEach items="${requestScope.Userslist }" var="receive">
		            
		                <tr>
		                	<td>${receive.id}</td>
		                    <td>${receive.users.name}</td>
		                    <td>${receive.money}</td>
		                    <td>
		                        <fmt:formatDate value="${receive.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		                    </td>
		                    <td>
		                    	<c:choose>
            						<c:when test="${receive.adminuserRedpaper.type==10}">随机红包</c:when>
            						<c:otherwise>定额红包</c:otherwise>
            					</c:choose>
		                   </td>
		                    <td>${receive.adminuserRedpaper.adminUser.provinceEnum.provinceEnum.name }</td>
		                </tr>
		            </c:forEach>
		            </tbody>
		        	</table>
		    
		    </div>
		    <div class="footer">
				<div class="page">
					<table>
						<tr>
							<div class="page-box common-page-box" style="text-align:center">${requestScope.pageFoot }</div>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<!--end of main-->
	</form>

</body>
</html>
