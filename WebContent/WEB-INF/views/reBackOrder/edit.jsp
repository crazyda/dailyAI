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
						<p>你当前的位置：[商城管理]-[售后]-[退单审核]</p>
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
                    <th>单价（元）</th>
                    <th>数量</th>
                    <th>交易状态</th>
                    <th>积分</th>
                    <th>红包</th>
                    <th>实收款（元）</th>
                    <th>物流信息</th>
                    <th>物流费用</th>
                </tr>
            </thead>
            <tbody>
             <tr class="order_time" style="display: table-row;vertical-align: inherit;border-color: inherit;">
                 <td colspan="9" style="background-color: #efefef;font-family: "微软雅黑";">
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
                             <c:if test="${backmoney>0 }">
                               	其他退单：
                            <span class="time"><font style="color:red;">${otherMoney }</font>&nbsp;&nbsp;</span>
                            </c:if>
                        </p>
                        <br>
                  </td>
               </tr>
                <tr>
                    <td width="20%">
                        <dl>
                            <dt><img src="${RESOURCE_LOCAL_URL }${backOrder.orderItem.firstImgUrl}" width="100%"></dt>
                            <dd><p>${backOrder.orderItem.goodName}</p><span>${backOrder.orderItem.style}</span></dd>
                        </dl>
                    </td>
                    <td width="10%">${backOrder.orderItem.goodPrice}</td>
                    <td width="10%">${backOrder.orderItem.goodQuantity}</td>
                    <td width="15%" class="gray">${backOrder.orderItem.orderStatus}</td>
                    <td width="10%">${backOrder.orderItem.payScore}</td>
                    <td width="10%">${backOrder.orderItem.payCashpoint}</td>
                    <td width="10%">${backOrder.orderItem.payPrice}</td>
                    <td width="10%">${backOrder.orderItem.logisticsType==1?'包邮':backOrder.orderItem.logisticsType==2?'付邮费':backOrder.orderItem.logisticsType==3?'到店消费':'上门自提'}</td>
                    <td width="10%">${backOrder.orderItem.logisticsPrice}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<form >
	<div class="section03 back_reason">
		<div class="left">退单理由：</div>
		<div class="right">
	    	<p class="text" id="reason">${backOrder.reason}</p>
	        <p class="img">
	        <c:forEach items="${backOrder.imgList}" var="img">
	                <img src="${RESOURCE_LOCAL_URL }${img}" width="12%">
	        </c:forEach>
	        </p>
	    </div>
	</div>
	<div class="reason_con">
        <div class="left">审核理由：</div>
        <div class="right"><textarea placeholder="请输入审核理由" id="checkMessage">${backOrder.checkMessage}</textarea></div>
    </div>
    
    <div class="reason_con">
	<br><br>
        <div class="left">退款金额：</div>
        <div class="right">
        <input type="text" id="jiner" name="jiner" readonly="readonly" value="${backOrder.orderItem.payPrice}" >
        <input type="hidden" id="quaner" value="${backOrder.orderItem.order.payPrice+backOrder.orderItem.order.logisticsType}"> 
        </div>
   	</div>
   	
    <div class="reason_btn">
    	<input type="button" value="不可再退单" onclick="if(!confirm('您确认要执行此操作吗?确认后该订单不可再退单!'))return false; else tuidan('-30');">
    	<input type="button" value="审核不通过" onclick="tuidan('-15')">
    	<input type="button" value="审核通过" onclick="tuidan('20')">
    </div>
</form>
     </td>
  </tr>
  </table>
  </div>
  </div>
<script>
function tuidan(status){
	var id = $("#id").val();
	var checkMessage = $("#checkMessage").val();
 	var reason = $("#reason").html();
	var jiner = $("#jiner").val();
	alert(id);
	$.ajax({
   		url:'<%=basePath%>reBackOrder/tuidan',
   		type:"post",
   		data:{"id":id,"status":status,"reason":reason,"checkMessage":checkMessage,"jiner":jiner},	
   		success:function(data){
   			if(data.status==true){
   				alert("修改成功");
   				parent.document.getElementById('iframe_content').src='${BASEPATH_IN_SESSION}reBackOrder/list?backState=10';
   			}else{
   				alert("修改失败");
   			}
   		}
   	});
}
</script>
</body>
</html>
