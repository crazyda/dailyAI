<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/css2.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
<link href="${BASEPATH_IN_SESSION}css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${BASEPATH_IN_SESSION}layui/css/layui.css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/layer3/layer.js"></script>
<script src="${BASEPATH_IN_SESSION}layui/layui.js" charset="utf-8"></script>

</head>
<body>
	<form>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[商城管理]-[积分卡列表]</p>
				</div>
				<hr align="center" />
			</div>
			
		
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">ID</th>
						<th width="10%">卡号</th>
						<th width="10%">面值</th>
						<th width="9%">描述</th>
						<th width="15%">时间</th>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="voucher">
							<tr>
								<td>${voucher.id}</td>
								<td>${voucher.code }</td>
								<td>${voucher.faceValue }</td>
								<td>${voucher.description }</td>
								<td>
								   <fmt:formatDate value="${voucher.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
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
							<div class="page-box common-page-box" style="text-align:center">
									${requestScope.pageFoot }
							</div>
						</tr>
					</table>

				</div>
			</div>
		</div>

	</form>
	
</body>
</html>

