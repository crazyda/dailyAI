<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>订单列表</title>
    <link href="${BASEPATH_IN_SESSION}css/order/chargeback.css" rel="stylesheet" type="text/css" />
    <link href="${BASEPATH_IN_SESSION}css/order/css.css" rel="stylesheet" type="text/css"/>
    <link href="${BASEPATH_IN_SESSION}css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="${BASEPATH_IN_SESSION}css/paging.css" rel="stylesheet" type="text/css"/>
    
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="${BASEPATH_IN_SESSION}js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="${BASEPATH_IN_SESSION}js/plugins/pagination/mricode.pagination.js"></script>
</head>

<body>
<form id="searchOrChangePage"><!-- 用于提交查询条件，或者翻页的一些数据信息； -->


    <!-- 展示主体 -->
    <div class="main" style="height:auto;">
        <div class="header">
            <!-- 导航栏 -->
            <div class="header-left">
                <p>你当前的位置：[商城管理]-[退单支付]</p>
            </div>
            <!-- 右侧点击区域 -->
            <div class="header-right"> 
            </div>
            <hr align="center"/>
        </div>
       	
       	
       	<div class="chargeback">
	<div class="tab">
    	<h4 class="tabh4">
            <span class="current"  onclick="javascript:window.location.href='list2?backState=20';" >退单支付</span>
            <span  onclick="javascript:window.location.href='list2?backState=30';" >已支付</span>
        </h4>
        <ul>
            <li class="current">
            	<div class="table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th width="20%">商品</th>
                            	<th width="10%">店铺</th>
                            	<th width="10%">支付方式</th>
                            	<th width="15%">支付订单号</th>
                            	<th width="15%">支付时间</th>
                            	<th width="10%">支付金额(分)</th>
                            	<th width="10%">退款金额（元）</th>
                            	<th width="10%">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach items="${backList }" var="item"> 
                        	<tr class="order_time">
                            	<td colspan="8">
                                	<p>
                                    	<input type="checkbox"/> 订单号：${item.id}
                                        <span class="order">${item.orderItem.order.orderCode }</span> 成交时间：
                                        <span class="time"><fmt:formatDate value="${item.orderItem.order.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                                        </span>
                                    </p>
                                </td>
                            </tr>
                        	<tr class="default">
                            	<td >
                                	<dl>
                                    	<dt><img src="${RESOURCE_LOCAL_URL }${item.orderItem.firstImgUrl }" width="100%"/></dt>
                                        <dd><p>${item.orderItem.goodName }</p><span>${item.specDetail }</span></dd>
                                    </dl>
                                </td>
                                <td width="10%">${item.orderItem.order.seller.name }</td>
                               
                                <td width="10%" >${item.orderItem.order.payType==10?'支付宝':item.orderItem.order.payType==20?'微信':item.orderItem.order.payType==40?'易联':"每天积分钱包"}</td> 
                                <td width="10%" >${item.orderItem.order.transactionId }</td>
                                <td  width="10%">
                             	 ${item.orderItem.order.timeEnd }	
                                </td>
                                <td class="red" width="10%">${item.orderItem.order.totalFee }</td>
                                <td width="10%">${item.backmoney }</td>
                                <td width="10%">
                                <a href="javascript:window.location.href='detail?id=${item.id }';">详情</a>
                                <c:if test="${userLevel>90&&item.backstate==20 }">
                                <a href="javascript:window.location.href='tuikuan?id=${item.id }';">/退款</a>
                                </c:if>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="page_num">
                    <!--end of pageColunm-->
						<div class="page">
							<table>
								<tr>
									<div class="page-box common-page-box" style="text-align:center;" >${requestScope.pageFoot }</div>
								</tr>
							</table>
						</div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
       
       
       
</div>

</form>
<script type="text/javascript">
/*<!-------------tab栏转换效果---------------->*/
$('.tab h4 span').click(function(e) {
	$(this).addClass('current').siblings().removeClass('current');
});	


$('.btn input').click(function(e) {
    $(this).addClass('click').siblings().removeClass('click');
});
</script>
</body>
</html>

