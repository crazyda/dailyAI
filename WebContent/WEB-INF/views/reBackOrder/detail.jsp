<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link href="${BASEPATH_IN_SESSION}css/order/css.css" rel="stylesheet" type="text/css" >
<link href="${BASEPATH_IN_SESSION}css/order/child-page.css" rel="stylesheet" type="text/css" >
<link href="${BASEPATH_IN_SESSION}css/add/css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
<title>每天积分管理系统</title>
</head>

<body>
<div class="div_new_content">
  <div style="margin-left:10px;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
	                <div class="header-left">
						<p>你当前的位置：[商城管理]-[退单/售后]-[退单详情]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table>
  </tr>
  <tr><td>
<div class="section01">
	<p class="red">买家申请退单</p>
    <p>订单号：<span class="order">${backOrder.orderItem.order.orderCode }</span> 成交时间：<span class="order"><fmt:formatDate value="${backOrder.orderItem.order.createTime }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span></p>
	<input type="hidden" name="id" id="id" value="${backOrder.id}">
</div>
<div class="section02">
	<ul>
    	<li class="name">姓名：<span>${backOrder.orderItem.order.realname}</span></li>
    	<li class="phone">联系电话：<span>${backOrder.orderItem.order.phone}</span></li>
    	<li class="address">收货地址：<span>${backOrder.orderItem.order.address}</span></li>
    </ul>
    <div class="table">
        <table>
            <thead>
                <tr>
                    <th>商品</th>
                    <th>店铺</th>
                    <th>单价（元）</th>
                    <th>数量</th>
                    <th>交易状态</th>
                    <th>积分</th>
                    <th>红包</th>
                    <th>实收款（元）</th>
                </tr>
            </thead>
            <tbody>
            <tr class="order_time" style="display: table-row;vertical-align: inherit;border-color: inherit;">
                 <td colspan="7" style="background-color: #efefef;font-family: "微软雅黑";">
                     <br>
                        <p>         
                          	订单总金额：
                            <span class="order">${order.payPrice+order.logisticsType}&nbsp;&nbsp;</span> 
                                                                                    订单总红包：
                            <span class="time">${order.payCashpoint }&nbsp;&nbsp;</span>
                                                                                     订单总积分：
                            <span class="time">${order.payScore }&nbsp;&nbsp;</span>
                                                                                      邮费：
                            <span class="time"><font style="color:red;">${order.logisticsType }</font>&nbsp;&nbsp;</span>
                               	其他退单：
                            <span class="time"><font style="color:red;">${otherMoney }</font>&nbsp;&nbsp;</span>
                        </p>
                        <br>
                  </td>
               </tr>
                <tr>
                    <td width="25%">
                        <dl>
                            <dt><img src="${RESOURCE_LOCAL_URL }${backOrder.orderItem.firstImgUrl}" width="100%"></dt>
                            <dd><p>${backOrder.orderItem.goodName}</p><span>${backOrder.orderItem.style}</span></dd>
                        </dl>
                    </td>
                    <td width="10%">${backOrder.seller.name}</td>
                    <td width="8%">${backOrder.orderItem.goodPrice}</td>
                    <td width="8%">${backOrder.orderItem.goodQuantity}</td>
                    <td width="12%" class="gray">${backOrder.orderItem.orderStatus}</td>
                    <td width="12%">${backOrder.orderItem.payScore}</td>
                    <td width="15%">${backOrder.orderItem.payCashpoint}</td>
                    <td width="15%">${backOrder.orderItem.payPrice}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="section03 back_reason">
	<div class="left">退单理由：</div>
	<div class="right">
    	<p class="text" id="reason">${backOrder.reason}</p>
        <p class="img">
        	<c:forEach items="${backOrder.imgList}" var="img">
                <img src="<%=basePath %>${img}" width="12%">
        	</c:forEach>
        </p>
    </div>
</div>
<div class="reason_con">
        <div class="left">审核理由：</div>
        <div class="right"><textarea placeholder="请输入审核理由" id="checkMessage">${backOrder.checkMessage}</textarea></div>
    </div>
     </td>
  </tr>
  </table>
  </div>
  </div>
</body>
</html>
