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
    <title>商家金额变动情况</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>

    <!-- artDialog -->
    <link href="<%=basePath%>js/plugins/artDialog-master/artDialog-master/css/ui-dialog.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/artDialog-master/artDialog-master/dist/dialog-min.js"></script>
</head>
<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[资金管理]-[提现审核]-[金额变动情况]</p>
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
        <hr align="center"/>
    </div>

    <div class="div_search">
        <form id="searchOrChangePage" action="<%=basePath%>AdminwithdrawReview/getMoneychange" method="post">
        	<input type="hidden" name="adminuserId" value="${adminUser.id}${adminuserId}"/>
            <input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
            <input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
            <input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->
            <div style="float: left;height: 100%;margin-left: 6%;margin-top: -0.5%">
            	<div class="name" style="float: right;color:red;font-size: 18px">	
					<span>用户名&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="name">${adminUser.loginname}</span>
				</div>
				<br />
				<span style="float:right; margin-right:10px; color:red; font-size: 18px">双击购买者可查看资金详情</span>
            </div>
            <div style="float: right; height: 100%; margin-right: 10%; color: red;font-size: 18px;" >
					<div class="money" style="float: left;">	
					<span> 总金额&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="money">${totalMoney}&nbsp;元</span>
					</div>
					<div class="revenue" style="float: left;">	
					<span> 收入&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="revenue">${verifyIncome}&nbsp;元</span>
					</div>
					<div class="pay" style="float: left;">	
					<span> 支出&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="pay">${verifyExpend}&nbsp;元</span>
					</div>
		 </div>
        </form>
    </div>

    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
        <input type="hidden" name="code_Value" value="${cashpoint.adminUser.id }"/>
            <thead>
          <th width="3%"><input id="s" name="" type="checkbox" value=""
                                  onclick="$(this).attr('checked')?checkAll():uncheckAll()"/></th> 
            <th width="12%" style="height:50px;">商品名</th>
            <th width="5%">金额</th>
            <th width="5%">购买数量</th>
            <th width="8%">购买者</th>
            <th width="12%">订单号</th>
            <th width="12%">第三方交易码</th>
            <th width="5%">支付类别</th>
            <th width="8%">时间</th>
            <th width="20%">说明</th>
            
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="cashpoint">
                <tr  class="tr">
                <td class="uid">${cashpoint.orderItem.order.user.id}</td>
                 <td><input type="checkbox" name="code_Value" value="${cashpoint.id }"/></td>
                 <c:choose>
                 	<c:when test="${cashpoint.orderItem==null}">
                 		<td>提现</td>
                 		<td>${cashpoint.cashpoint}</td>
                 		<td>无</td>
                 		<td>无</td>
                 		<td>无</td>
                 		<td>无</td>
                 		<td>无</td>
                 	</c:when>
                 	<c:otherwise>
                 		 <td>${cashpoint.orderItem.goodName}</td>
	                     <td>${cashpoint.cashpoint}</td>
	                     <td>${cashpoint.orderItem.goodQuantity}</td>
	                    <td>${cashpoint.orderItem.user.name}-${cashpoint.orderItem.order.realname}</td>
	                    <td>${cashpoint.orderItem.order.outTradeNo}</td>
	                    <td>
	                    <c:choose>
	                    	<c:when test="${cashpoint.orderItem.order.payType==10}">
	                    		${cashpoint.orderItem.order.alipayCode}
	                    	</c:when>
	                    	<c:when test="${cashpoint.orderItem.order.payType==20 }">
	                    		${cashpoint.orderItem.order.weixinCode}
	                    	</c:when>
	                    	<c:when test="${cashpoint.orderItem.order.payType==40 }">
	                    		${cashpoint.orderItem.order.weixinCode}
	                    	</c:when>
	                    </c:choose>
	                    </td>
	                    <td>
	                    <c:choose>
	                    	<c:when test="${cashpoint.orderItem.order.payType==10}">支付宝</c:when>
	                    	<c:when test="${cashpoint.orderItem.order.payType==20}">微信</c:when>
	                    	<c:when test="${cashpoint.orderItem.order.payType==30}">每天积分钱包</c:when>
	                    	<c:when test="${cashpoint.orderItem.order.payType==40}">易联支付</c:when>
	                    </c:choose>
	                   </td>
                 	
                 	</c:otherwise>
                 </c:choose>
                
                   <td>
                        <fmt:formatDate value="${cashpoint.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${cashpoint.remark }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- 表格主体内容结束 -->

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
            
            
            $(".uid").hide(); 
            $(function(){
            	$(".tr").dblclick(function(){
						var uid= $(this).find("td").eq(0).text().trim();
						if(uid.length>0){
							location.href="${BASEPATH_IN_SESSION}taoke/pid/findNextLevelByUserId?userId="+uid;
						}
				});
            });
        </script>
    </div>
    <!-- 分页条开结束-->
</div>
<!-- 分页条结束 -->

</div>
<!-- 展示主体结束 -->

</body>
</html>
