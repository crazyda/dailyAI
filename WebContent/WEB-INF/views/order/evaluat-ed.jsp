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
<title>订单详情-已评价</title>
<link type="text/css" href="<%=basePath %>css/orders/child-page.css" rel="stylesheet">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>

<body>
<div class="section01">
	<p class="red">买家已评价</p>
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
                <tr>
                    <td width="30%">
                        <dl>
                            <dt><img src="${RESOURCE_LOCAL_URL }${itemMap['item'].imgUrl}" width="100%"></dt>
                            <dd><p>${itemMap['item'].goodName}</p><span>${itemMap['item'].style}</span></dd>
                        </dl>
                    </td>
                    <td width="15%">${itemMap['item'].goodPrice}</td>
                    <td width="10%">${itemMap['item'].goodQuantity}</td>
                    <td width="15%" class="red">${itemMap['item'].orderStatus}</td>
                    <td width="10%">${itemMap['item'].payScore}</td>
                    <td width="10%">${itemMap['item'].payCashpoint}</td>
                    <td width="10%">${itemMap['item'].payPrice}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<p class="star">
	评分 
   	<c:forEach begin="1" end="${itemMap['comment'].score/2}">
        <img src="<%=basePath %>images/order/star-hover.png" width="16">
   	</c:forEach>
	<c:if test="${itemMap['comment'].score%2==1}">
		<img src="<%=basePath %>images/order/xx.png" width="16">
	</c:if>    
	<c:forEach begin="1" end="${(10-itemMap['comment'].score)/2}">
		<img src="<%=basePath %>images/order/star.png" width="16">
	</c:forEach>
</p>
<div class="section03 back_reason">
	<div class="left">评论内容：</div>
	<div class="right">
    	<p class="text">${itemMap['comment'].userComment}</p>
        <p class="img">
        	<c:forEach items="${itemMap['comment'].imgList}" var="img">
                  <img src="${RESOURCE_LOCAL_URL }${img}" width="12%">
            </c:forEach>
        </p>
    </div>
</div>
<form class="check_reason">
	<div class="reason_con">
        <div class="left">回复：</div>
        <div class="right"><textarea  id="licenseother"  placeholder="请输入回复内容">${itemMap['comment'].sellerReply}</textarea></div>
    </div>
    <div class="reason_btn">
    <input type="hidden"  id="comm" value="${itemMap['item'].id}">
    	<input type="button" value="回复" onclick="comment()">
    </div>
</form>
<script type="text/javascript">
function comment(){
	var licenseother = $("#licenseother").val();
	var id = $("#comm").val();
	if(licenseother==""||licenseother==null||licenseother==undefined){
		alert("请输入回复内容");
		$("#licenseother").focus();
		return;
	}
	$.ajax({
   		url:"<%=basePath%>reOrder/comment",
   		type:"post",
   		data:{"licenseother":licenseother,"id":id},	
   		success:function(data){
   			if(data.status==true){
   				alert("回复成功");
   				parent.document.getElementById('iframe_content').src='${BASEPATH_IN_SESSION}reOrder/list?status='+5;
   			}
   			if(data.status==false){
   				alert("回复失败");
   			}
   		}
   	});
}

</script>
</body>
</html>
