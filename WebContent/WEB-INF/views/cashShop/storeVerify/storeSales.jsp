<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>店铺销量</title>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<!-- 分页插件 -->
<link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
<script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
<!-- artDialog -->
<link href="<%=basePath%>js/plugins/artDialog-master/artDialog-master/css/ui-dialog.css" rel="stylesheet"/>
<script src="<%=basePath%>js/plugins/artDialog-master/artDialog-master/dist/dialog-min.js"></script>

	
		<style type="text/css">
			#dpid{
				display: inline-block;
				width: 180px;
				word-wrap: break-word;
				vertical-align: middle;
				
				 margin-left:-20px; 
				margin-right: -20px;
				
				height: 63px;
				padding-top:22px; 
				
			}
			
			.times{
				float: left;
			}
			
			.times p{
				display: inline-block;
			}
		#sales{
			color: red;
		}
		
		tr{
			height: 40px !important;
		}
		</style>
	
</head>
<body>

	<div class="main" style="height:auto;">
		<div class="header">
			<div class="header-left">
				<p>你当前的位置：[商城管理]-[商城销量]</p>
			</div>
			<hr align="center" />
		</div>

		<div class="div_search">
			<form action="<%=basePath%>cashShop/store/storeSales" id="searchOrChangePage" method="post">&nbsp;&nbsp; 
			<input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
            <input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
            <input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->
            <div class="times">
			&nbsp;&nbsp; &nbsp;&nbsp; 商城类别:&nbsp;&nbsp; <select id="mallType" name="mallType">
					<option value="0">请选择</option>
					<option value="sem" <c:if test="${mallType=='1'}">selected</c:if>>实体店铺 </option>
					<option value="scm,skm,lsm"<c:if test="${mallType=='2'}">selected</c:if>>总部商城</option>
				</select>
			
 				 <p class="startTime">开始时间：<input class="startTM" type=text id=starttime name="sTM" value='${requestScope.sTM }' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen' })"/></p>
                 <p class="endTime">结束时间： <input class="endTM" type=text id=endtime name="eTM" value='${requestScope.eTM }'  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen'})" /></p>
				<input type=submit class="button" id="search" value="搜索" /> 
				
				<!-- <div style="float: right; height: 100%; margin-right: 70%; color: red;font-size: 17px;" > -->
					<span style="color:red;  margin-left:30px " > 订单总成交量&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px; " id="sales">${count==null?0:count}</span>
		 	</div>	 	
			</form>
		</div>

		<!--end of header-->
		<div class="pageColumn">
			<table class="list" style="width:98%;margin:0 auto;">
				<thead>
					<th width="5%">订单号</th>
					<th  width="15%" style="height:50px;">商品名</th>
					<th width="5%">购买量</th>
					<th width="8%">成交金额</th>
					<th width="8%">商城类别</th>
					<th width="8%">时间</th>
				</thead>
				<tbody>

					<c:forEach items="${pageResult.result }" var="sales">
						<tr>
							<td>${sales.id}</td>
							<td>${sales.goodName}</td>
							<td>${sales.goodQuantity}</td>
							<td>${sales.payPrice}</td>
							<td>
							<c:choose>
		                    	<c:when test="${sales.mallClass=='sem'}">周边店铺</c:when>
		                    	<c:when test="${sales.mallClass=='scm'}">积分商城</c:when>
		                    	<c:when test="${sales.mallClass=='skm'}">限时秒杀</c:when>
		                    	<c:when test="${sales.mallClass=='lsm'}">总部商城</c:when>
                    		</c:choose>
							</td>
							<td>${sales.createTime}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		    <!-- 分页条开始 -->
    <div style="margin-top: 50px;margin-bottom: 50px;text-align: center;margin-left: 20px;">
    <div id="page" class="m-pagination"></div>
    <div id="eventLog"></div>
    <script>
        (function () {
            $("#page").pagination({
                pageIndex: $("#currentPage").val() - 1,
                pageSize: $("#pageSize").val(),
                total: $("#totalCount").val(),
                debug: true,
                showJump: true,
                showPageSizes: true,
                pageElementSort: ['$page', '$size', '$jump', '$info']
            });
        })();
        $("#page").on("pageClicked", function (event, data) {
            $("#currentPage").val(data.pageIndex + 1);
            $("#searchOrChangePage").submit();
        }).on('jumpClicked', function (event, data) {
            $("#currentPage").val(data.pageIndex + 1);
            $("#searchOrChangePage").submit();
        }).on('pageSizeChanged', function (event, data) {
            $("#pageSize").val(data.pageSize);
            $("#searchOrChangePage").submit();
        });
        
        
        
        $(function(){
        	
        	$("#search").click(function(){
        		$("#currentPage").val("1");
        		
        	})
        })
        
    </script>
    </div>
    <!-- 分页条开结束-->
	</div>


	<script type="text/javascript">
		
	</script>
</body>
</html>