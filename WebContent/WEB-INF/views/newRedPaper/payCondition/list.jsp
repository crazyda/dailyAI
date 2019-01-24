<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/paging.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
</head>
<body>
	<form action="<%=basePath%>newRedPaper/payCondition/list" method="post">
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[红包管理]-[红包派发管理]</p>
				</div>
			</div>
			<div class="div_search">
					<span style="float:right; margin-right:30px;">总记录：${count}条</span>
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"  style="height:50px;">序号</th> 
						<th width="5%">派发人</th>
						<th width="5%">总金额</th>
						<th width="5%">总个数</th>
						<th width="5%">剩余金额</th>
						<th width="5%">剩余个数</th>
						<th width="8%">派发时间</th>
						<th width="8%">红包类型</th>
						<th width="8%">红包地区</th>
						<th width="10%">操作</th>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.rlist }" var="redpaper">
							<tr>
								<td>${redpaper.id}</td>
								<td>${redpaper.adminUser.username}</td>
								<td>${redpaper.totalMoney}</td>
								<td>${redpaper.totalQuantity}</td>
								<td>${redpaper.surplusMoney}</td>
								<td>${redpaper.surplusQuantity}</td>
            					<td><fmt:formatDate value="${redpaper.createtime}" type="date" pattern="yyyy-MM-dd"/></td>
            					<td>
            					<c:choose>
            						<c:when test="${redpaper.type==10}">随机红包</c:when>
            						<c:otherwise>定额红包</c:otherwise>
            					</c:choose>
            					</td>
            					<td>${redpaper.adminUser.provinceEnum.provinceEnum.name}</td>
            			<td>
            			<c:if test="${redpaper.status==10}">
	                        <div align="center">
	                            <span class="STYLE4">
	                                <u><span onclick="isPay('${redpaper.id }');" style="cursor: pointer;">
	                                    	停发
	                                </span></u>&nbsp;&nbsp;
	                                <u><span onclick="changeRecord('${redpaper.id}');" style="cursor: pointer;">
	                                   		查看详情
	                                </span></u>
	                                <script>
	                                    function isPay(redpaperId){
	                                  	  window.location.href = "${BASEPATH_IN_SESSION}/newRedPaper/payCondition/isPay?redpaperId="+redpaperId;
	                                  }
	                                    function changeRecord(redpId){
	                                        location.href="<%=basePath%>newRedPaper/payCondition/getRedpaperDetail?redpId="+redpId;
	                                    }
	                                </script>
	                            </span>
	                        </div>
                        </c:if>
                        <c:if test="${redpaper.status==0}">
                        	已停发
                        </c:if>
                    </td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--end of pageColunm-->
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
