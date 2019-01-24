<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订单详情-交易已完成</title>
<link type="text/css" href="<%=basePath %>css/orders/child-page.css" rel="stylesheet">
</head>

<body>
<div class="section01">
	<p class="red">交易已完成</p>
    <p>订单号：<span class="order">${orderMap['order'].orderCode }</span> 成交时间：<span class="order"><fmt:formatDate value="${orderMap['order'].createTime }" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></span></p>
</div>
<div class="section02">
	<ul>
    	<li class="name">姓名：<span>${orderMap['order'].realname}</span></li>
    	<li class="phone">联系电话：<span>${orderMap['order'].phone}</span></li>
    	<li class="address">收货地址：<span>${orderMap['order'].address}</span></li>
    </ul>
    <div class="table">
        <table>
            <thead>
                <tr>
                    <th>商品</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>交易状态</th>
                    <th>积分</th>
                    <th>红包</th>
                    <th>实收款</th>
                </tr>
            </thead>
            <tbody>
                <tr class="order_time" style="display: table-row;vertical-align: inherit;border-color: inherit;">
                 <td colspan="7" style="background-color: #efefef;font-family: "微软雅黑";">
                     <br>
                        <p>         
                          	订单总金额：
                            <span class="order">${orderMap['order'].payPrice+orderMap['order'].logisticsType}&nbsp;&nbsp;</span> 
                                                                                    订单总红包：
                            <span class="time">${orderMap['order'].payCashpoint }&nbsp;&nbsp;</span>
                                                                                     订单总积分：
                            <span class="time">${orderMap['order'].payScore }&nbsp;&nbsp;</span>
                                                                                       邮费：
                            <span class="time"><font style="color:red;">${orderMap['order'].logisticsType }</font>&nbsp;&nbsp;</span>
                        </p>
                        <br>
                  </td>
               </tr>
                <c:forEach  items="${orderMap['items']}" var="item" >
                <tr>
                    <td width="30%">
                        <dl>
                            <dt><img src="${RESOURCE_LOCAL_URL }${item.imgUrl}" width="100%"></dt>
                            <dd><p>${item.goodName}</p><span>${item.style}</span></dd>
                        </dl>
                    </td>
                    <td width="15%">${item.goodPrice}</td>
                    <td width="10%">${item.goodQuantity}</td>
                    <td width="15%" class="red">${item.orderStatus}</td>
                    <td width="10%">${item.payScore}</td>
                    <td width="10%">${item.payCashpoint}</td>
                    <td width="10%">${item.payPrice}</td>
                </tr>
               </c:forEach> 
            </tbody>
            </tbody>
        </table>
    </div>
</div>
<form class="section03">
	<p>物流信息</p>
	<p style="color:#333">
    	物流公司名称：<span style=" margin-right:5%">${orderMap['order'].logisticsCompay}</span>
    	运单号码：<span style=" margin-right:5%">${orderMap['order'].logisticsCode}</span>
    </p>
</form>
</body>
</html>
