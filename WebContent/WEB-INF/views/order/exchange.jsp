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
<title>待兑换</title>
<link type="text/css" href="<%=basePath %>css/orders/child-page.css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>
<script>
function checkCode(){
	var code = $("#code").val();
	var status = $("#status").val();
	var id = $("#id").val();
	if(code==""||code==null||code==undefined){
		$("#f").html("");
		$("#f").html("请输入兑换码");
		$("#code").focus();
		return;
	}
	$.ajax({
   		url:"<%=basePath%>reOrder/exchange",
   		type:"post",
   		data:{"code":code,"status":status,"id":id},	
   		success:function(data){
   			if(data.status==true){
   				alert("兑换码正确");
   				parent.document.getElementById('iframe_content').src='${BASEPATH_IN_SESSION}reOrder/list?status='+status;
   			}
   			if(data.status==false){
   				alert("兑换码错误");
   			}
   		}
   	});
}
</script>
<body>
<div class="section01">
	<p class="red">买家已付款，上门自取</p>
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
                            <span class="order">${orderMap['order'].payPrice }&nbsp;&nbsp;</span> 
                                                                                    订单总红包：
                            <span class="time">${orderMap['order'].payCashpoint }&nbsp;&nbsp;</span>
                                                                                     订单总积分：
                             <span class="time">${orderMap['order'].payScore }&nbsp;&nbsp;</span>
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
        </table>
    </div>
</div>
<form class="section03">
	<p>输入兑换码：<input type="text" id="code" name="code" placeholder=""><font id="f" style="color: red"></font></p>
	<input type="hidden" id="status" name="status" value="${status}">
	<input type="hidden" id="id" name="id" value="${orderId}">
    <div class="btn"><input type="button" value="确认兑换" onclick="checkCode()"></div>
</form>

</body>
</html>































