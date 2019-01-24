<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="wh" uri="wehua-taglib"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>城市代理金额查询</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/paging.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
</head>

<body>
	<form action="<%=basePath%>sellerWithdraw/sellerAssetQuery" method="post">
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[资金管理]-[城市代理金额查询]</p>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search" style="margin-left: 1.5%">			
				<span style="float:left; margin-right:30px;">余额：${totalMoney==null?0:totalMoney}&nbsp;元</span>
				<span style="float:left; margin-right:30px;">已确认收益：${verifyIncome==null?0:verifyIncome}&nbsp;元</span>
				<span style="float:left; margin-right:30px;">已确认支出：${verifyExpend==null?0:verifyExpend}&nbsp;元</span>
				<span style="float:left; margin-right:30px;">未确认金额：${noVerifyMoney==null?0:noVerifyMoney}&nbsp;元</span>&nbsp;&nbsp;
					状态:&nbsp;&nbsp; <select id="type" name="type">
						<option value="0" >请选择</option>
						<option value="1" >已确认</option>
						<option value="-1">未确认</option>
					</select>
					&nbsp;<input type=submit class="button"  value="搜索" />
				
				<div class="header-right-cell" style="cursor: pointer;margin-right: 100px;" onclick="if(!confirm('您确认要提现吗?提现金额为已确认账户余额!'))return false; else pay();">
					<img src="${BASEPATH_IN_SESSION}constant/tab/images/withdraw.png" style="margin:3px 0;"/>
					<p>提现</p>
				</div>
				
			</div>
			
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="10%" style="height:50px;">ID</th>
						<th width="10%">金额</th>
						<th width="10%">状态</th>
						<th width="20%">说明</th>
						<th width="20%">日期</th>
					</thead>
					<tbody>
					
						<c:forEach items="${cashpointsList}" var="data">
							<tr>
            					<td>${data.id}</td>
            					<td>${data.cashpoint}</td>
            					<td>
            						<c:if test="${data.type==1 }"><font color="green">已确认</font></c:if>
            						<c:if test="${data.type==-1 }"><font color="red">未确认</font></c:if>
            					</td>
            					<td>${data.remark }</td>
            					<td>${data.createTime }</td>
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
	
	<script>
		function pay(){
			location.href="<%=basePath%>sellerWithdraw/withdrawalApplyPage";
		}
		
	</script>
</body>
</html>
