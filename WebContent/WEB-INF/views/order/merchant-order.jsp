<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>订单商家</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<link type="text/css" href="<%=basePath %>css/orders/child-page.css" rel="stylesheet">
<link type="text/css" href="<%=basePath %>css/orders/merchant-order.css" rel="stylesheet">
<script src="<%=basePath %>js/order/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
 <script type="text/javascript">
function batchHandle(){
	var status = $("#status").val();
	var checkbox =  new Array();
	$(".tab ul li:eq("+status+") :checkbox[name!='quanxuan']").each(function (i){
			if($(this).attr("checked")=="checked"){
				checkbox[i]=$(this).val();
			}		
	}); 
		$.ajax({
	   		url:"<%=basePath%>reOrder/batchHandle",
	   		type:"post",
	   		data:{"checkbox":checkbox,"status":status},	
	   		success:function(data){
	   			if(data.status==true){
	   				alert("已成功发货");
	   				
	   			}
	   			if(data.status==false){
	   				alert("发货失败");
	   			}
	   		}
	   	});
	}
</script>
</head>

<body>
<div class="merchant">
<div class="header">
	<div class="header-left">
		<p>你当前的位置：[商城管理]-[订单列表]</p>
	</div>
	<hr/>
</div>
<br>
	<div class="tab">
    	<h4>
            <span>待确认</span>
            <span>待发货</span>
            <span>待兑换</span>
            <span>待收货</span>
            <span>已完成</span>
            <span>已评价</span>
        </h4>
          <div class="section01">
            <p class="red">*订单号为红色,购买用户已经被删除！</p>
            </div>
        <form  action="list"  class="orderform" method="post">
            <p class="name">商品名称：<input type="text"  name="goodsName"  value="${goodsName }"   placeholder=""></p>
            <p class="time">下单时间：<input type="text"  name="createTime" value="${createTime }"    placeholder=""  onClick="WdatePicker({dateFmt:'yyyy年MM月dd日'});"></p>
            <p>&nbsp;&nbsp;&nbsp;兑换码：<input type="text"  name="code"  value="${code }" placeholder=""></p>
            <p class="num">订单编号：<input type="text"  name="orderCode"  value="${orderCode }" placeholder=""></p>
            <p class="nickname">买家真实名称：<input type="text" name="realname" value="${realname }" placeholder=""></p>
            <p class="payType">支付类型：
            	<select name="payType" >
                	<option value="" selected="selected"></option>
                	<option value="10">支付宝</option>
                	<option value="20">微信</option>
                	<option value="30">每天积分钱包</option>
                </select>
            </p>
             
            <p class="btn">
            	<input type="submit" value="搜索订单"    class="">
                <input type="button" value="批量导出">
            </p>
         	
            <input type="hidden" id="status" name="status" value="${status }">
        </form>
        <ul>
            <li class="current">
            	<div class="table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th>商品</th>
                            	<th>单价（元）</th>
                            	<th>数量</th>
                            	<th>收货人信息</th>
                            	<th>交易状态</th>
                            	<th>积分</th>
                            	<th>红包</th>
                            	<th>实收款（元）</th>
                            	<th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orderList }" var="orderMap">
               <td> 
            </td>
                        	<tr class="order_time">
                            	<td colspan="9">
                                     <c:choose>
                                    	<c:when test="${item.user.isvalid==0}">
	                                    	<p>
		                                    	<input type="checkbox" value="${orderMap['order'].id }"> 订单号：
		                                        <span class="order" style="color:red">${orderMap['order'].orderCode }</span> 成交时间：
		                                        <span class="time" style="color: red">${orderMap['order'].createTime }</span>
		                                    </p>
                                    	</c:when>
                                    	<c:otherwise>
	                                    	<p>
		                                    	<input type="checkbox" value="${orderMap['order'].id }"> 订单号：
		                                        <span class="order">${orderMap['order'].orderCode }</span> 成交时间：
		                                        <span class="time">${orderMap['order'].createTime }</span>
		                                    </p>
                                    	</c:otherwise>
                                    </c:choose> 
                                </td>
                            </tr>
                            <c:forEach items="${orderMap['items']}" var="item" varStatus="st">
                            <tr class="default">
                            	<td width="20%">
                                	<dl>
                                    	<dt><img src="${RESOURCE_LOCAL_URL }${item.imgUrl}" width="100%"></dt>
                                        <dd><p>${item.goodName}</p><span>${item.style}</span></dd>
                                    </dl>
                                </td>
                                <td width="8%">${item.goodPrice}</td>
                                <td width="8%">${item.goodQuantity}</td>
                                <td width="15%" style="padding:0 1%;">${orderMap['order'].realname }，${orderMap['order'].phone}<br>${orderMap['order'].address }</td>
                               	<td class="red" width="8%">
                               		${item.orderStatus}
                                </td>
                                <td width="10%">${item.payScore}</td>
                                 <td width="10%">${item.payCashpoint}</td>
                                <td width="10%">${item.payPrice}</td>
                                <td width="12%" rowspan="${orderMap['size']}">
                                	<c:if test="${st.index==0 }">
                                	<a href="<%=basePath %>reOrder/details?orderId=${orderMap['order'].id}&status=${status}">详情</a> / <a href="<%=basePath %>reOrder/confirm?orderId=${orderMap['order'].id}&status=${status}">确认</a>
                                	</c:if>
                                </td> 
                            </tr>
                            </c:forEach>                
                        </c:forEach>                    
                        </tbody>
                    </table>
                   
                    <div class="page_num">
                    	<div class="left">
                        	<input type="checkbox" name="quanxuan"> 全选
                            <input type="button" value="批量发货" class="btn" >
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
					<!--end of main-->
                    </div>
                </div>
            </li>
            <li>
            	<div class="table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th>商品</th>
                            	<th>单价（元）</th>
                            	<th>数量</th>
                            	<th>收货人信息</th>
                            	<th>交易状态</th>
                            	<th>积分</th>
                            	<th>红包</th>
                            	<th>实收款（元）</th>
                            	<th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                         <c:forEach items="${orderList }" var="orderMap">
                         	<tr class="order_time">
                            	<td colspan="9">
                                	<p>
                                    	<input type="checkbox"> 订单号：
                                        <span class="order">${orderMap['order'].orderCode }</span> 成交时间：
                                        <span class="time">${orderMap['order'].createTime }</span>
                                    </p>
                                </td>
                            </tr>
                            <c:forEach items="${orderMap['items']}" var="item" varStatus="st">
                            <tr class="default">
                            	<td width="20%">
                                	<dl>
                                    	<dt><img src="${RESOURCE_LOCAL_URL }${item.imgUrl}" width="100%"></dt>
                                        <dd><p>${item.goodName}</p><span>${item.style}</span></dd>
                                    </dl>
                                </td>
                                <td width="8%">${item.goodPrice}</td>
                                <td width="8%">${item.goodQuantity}</td>
                                <td width="15%" style="padding:0 1%;">${orderMap['order'].realname }，${orderMap['order'].phone}<br>${orderMap['order'].address }</td>
                                <td class="red" width="8%">${item.orderStatus}</td>
                                <td width="10%">${item.payScore}</td>
                                 <td width="10%">${item.payCashpoint}</td>
                                <td width="10%">${item.payPrice}</td>
                                <td width="12%" rowspan="${orderMap['size']}">
                                	<c:if test="${st.index==0 }">
                                	<a href="<%=basePath %>reOrder/details?orderId=${orderMap['order'].id}&status=${status}">详情</a> / <a href="<%=basePath %>reOrder/confirm?orderId=${orderMap['order'].id}&status=${status}">发货</a>
                                	</c:if>
                                </td> 
                            </tr>
                            </c:forEach>
                         </c:forEach>                   
                        </tbody>
                    </table>
                    <div class="page_num">
                    	<div class="left">
                        	<input type="checkbox"> 全选
                            <input type="button" value="批量发货" class="btn">
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
					<!--end of main-->
                    </div>
                </div>
            </li>
            <li>
            	<div class="table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th>商品</th>
                            	<th>单价（元）</th>
                            	<th>数量</th>
                            	<th>收货人信息</th>
                            	<th>交易状态</th>
                            	<th>积分</th>
                            	<th>红包</th>
                            	<th>实收款（元）</th>
                            	<th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orderList }" var="orderMap">
                        	<tr class="order_time">
                            	<td colspan="9">
                                	<p>
                                    	<input type="checkbox"> 订单号：
                                        <span class="order">${orderMap['order'].orderCode }</span> 成交时间：
                                        <span class="time">${orderMap['order'].createTime }</span>
                                    </p>
                                </td>
                            </tr>
                            <c:forEach items="${orderMap['items']}" var="item" varStatus="st">
                            <tr class="default">
                            	<td width="20%">
                                	<dl>
                                    	<dt><img src="${RESOURCE_LOCAL_URL }${item.imgUrl}" width="100%"></dt>
                                        <dd><p>${item.goodName}</p><span>${item.style}</span></dd>
                                    </dl>
                                </td>
                                <td width="8%">${item.goodPrice}</td>
                                <td width="8%">${item.goodQuantity}</td>
                                <td width="15%" style="padding:0 1%;">${orderMap['order'].realname }，${orderMap['order'].phone}<br>${orderMap['order'].address }</td>
                                <td class="red" width="8%">${item.orderStatus}</td>
                                <td width="10%">${item.payScore}</td>
                                 <td width="10%">${item.payCashpoint}</td>
                                <td width="10%">${item.payPrice}</td>
                                <td width="12%" rowspan="${orderMap['size']}">
                                	<c:if test="${st.index==0 }">
                                	<a href="<%=basePath %>reOrder/confirm?orderId=${orderMap['order'].id}&status=${status}">兑换</a> / <a href="<%=basePath %>reOrder/details?orderId=${orderMap['order'].id}&status=${status}">详情</a>
                                	</c:if>
                                </td> 
                            </tr>
                            </c:forEach>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="page_num">
                    	<div class="left">
                        	<!-- <input type="checkbox"> 全选
                            <input type="button" value="批量发货" class="btn"> -->
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
						<!--end of main-->
                    </div>
                </div>
            </li>
            <li>
            	<div class="table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th>商品</th>
                            	<th>单价（元）</th>
                            	<th>数量</th>
                            	<th>收货人信息</th>
                            	<th>交易状态</th>
                            	<th>积分</th>
                            	<th>红包</th>
                            	<th>实收款（元）</th>
                            	<th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach items="${orderList }" var="orderMap">
                        	<tr class="order_time">
                            	<td colspan="9">
                                	<p>
                                    	<input type="checkbox"> 订单号：
                                        <span class="order">${orderMap['order'].orderCode }</span> 成交时间：
                                        <span class="time">${orderMap['order'].createTime }</span>
                                    </p>
                                </td>
                            </tr>
                            <c:forEach items="${orderMap['items']}" var="item" varStatus="st">
                            <tr class="default">
                            	<td width="20%">
                                	<dl>
                                    	<dt><img src="${RESOURCE_LOCAL_URL }${item.imgUrl}" width="100%"></dt>
                                        <dd><p>${item.goodName}</p><span>${item.style}</span></dd>
                                    </dl>
                                </td>
                                <td width="8%">${item.goodPrice}</td>
                                <td width="8%">${item.goodQuantity}</td>
                                <td width="15%" style="padding:0 1%;">${orderMap['order'].realname }，${orderMap['order'].phone}<br>${orderMap['order'].address }</td>
                                <td class="red" width="8%">${item.orderStatus}</td>
                                <td width="10%">${item.payScore}</td>
                                 <td width="10%">${item.payCashpoint}</td>
                                <td width="10%">${item.payPrice}</td>
                                <td width="12%" rowspan="${orderMap['size']}">
                                	<c:if test="${st.index==0 }">
                                	<a href="<%=basePath %>reOrder/details?orderId=${orderMap['order'].id}&status=${status}">详情</a> / <a href="https://www.kuaidi100.com">查看物流</a>
                                	</c:if>
                                </td> 
                            </tr>
                            </c:forEach>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="page_num">
                    	<div class="left">
                        	<!-- <input type="checkbox"> 全选
                            <input type="button" value="批量发货" class="btn"> -->
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
						<!--end of main-->
                    </div>
                </div>
            </li>
            <li>
            	<div class="table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th>商品</th>
                            	<th>单价（元）</th>
                            	<th>数量</th>
                            	<th>收货人信息</th>
                            	<th>交易状态</th>
                            	<th>积分</th>
                            	<th>红包</th>
                            	<th>实收款（元）</th>
                            	<th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach items="${orderList }" var="orderMap">
                        	<tr class="order_time">
                            	<td colspan="9">
                                	<p>
                                    	<input type="checkbox"> 订单号：
                                        <span class="order">${orderMap['order'].orderCode }</span> 成交时间：
                                        <span class="time">${orderMap['order'].createTime }</span>
                                    </p>
                                </td>
                            </tr>
                            <c:forEach items="${orderMap['items']}" var="item" varStatus="st">
                            <tr class="default">
                            	<td width="20%">
                                	<dl>
                                    	<dt><img src="${RESOURCE_LOCAL_URL }${item.imgUrl}" width="100%"></dt>
                                        <dd><p>${item.goodName}</p><span>${item.style}</span></dd>
                                    </dl>
                                </td>
                                <td width="8%">${item.goodPrice}</td>
                                <td width="8%">${item.goodQuantity}</td>
                                <td width="15%" style="padding:0 1%;">${orderMap['order'].realname }，${orderMap['order'].phone}<br>${orderMap['order'].address }</td>
                                <td class="gray" width="8%">${item.orderStatus}</td>
                                <td width="10%">${item.payScore}</td>
                                <td width="10%">${item.payCashpoint}</td>
                                <td width="10%">${item.payPrice}</td>
                                <td width="12%" rowspan="${orderMap['size']}">
                                	<c:if test="${st.index==0 }">
                                	<a href="<%=basePath %>reOrder/details?orderId=${orderMap['order'].id}&status=${status}">详情</a>
                                	</c:if>
                                </td> 
                            </tr>
                            </c:forEach>
                            </c:forEach>
                        </tbody>
                    </table>
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
					<!--end of main-->
                </div>
            </li>
            <li>
            	<div class="table reply_table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th>商品</th>
                            	<th>评分</th>
                            	<th>评价内容</th>
                            	<th>交易状态</th>
                            	<th>评价人</th>
                            	<th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                         <c:forEach items="${orderList }" var="orderMap">
                         	<tr class="order_time">
                            	<td colspan="9">
                                	<p>
                                    	<input type="checkbox"> 订单号：
                                        <span class="order">${orderMap['order'].orderCode }</span> 成交时间：
                                        <span class="time">${orderMap['order'].createTime }</span>
                                    </p>
                                </td>
                            </tr>
                        
                         <c:forEach items="${orderMap['itemList']}" var="map" varStatus="st">
                         <tr class="default" id="">
                            	<td width="30%">
                                	<dl>
                                		${map['item'].id}
                                    	<dt><img src="${RESOURCE_LOCAL_URL }${map['item'].imgUrl}" width="100%"></dt>
                                        <dd><p>${map['item'].goodName}</p><span>${map['item'].style}<br><i>${map['item'].goodPrice}</i>元</span></dd>
                                    </dl>
                                </td>
                                <td width="12%">
                                	<c:forEach begin="1" end="${map['comment'].score/2}">
                                		<img src="<%=basePath %>images/order/star-hover.png" width="16">
                                	</c:forEach>
                               		<c:if test="${map['comment'].score%2==1}">
                               			<img src="<%=basePath %>images/order/xx.png" width="16">
                               		</c:if>    
                               		<c:forEach begin="1" end="${(10-map['comment'].score)/2}">
                                		<img src="<%=basePath %>images/order/star.png" width="16">
                                	</c:forEach>    	
                                </td>
                                <td width="25%">
                                	<p class="assess">${map['comment'].userComment}</p>
                                    <p class="img">
                                    	<c:forEach items="${map['comment'].imgList}" var="img">
                                    			<img src="${RESOURCE_LOCAL_URL }${img}" width="12%">
                                    	</c:forEach>
                                    </p>
                                </td>
                                <td class="gray" width="10%">${map['item'].orderStatus}</td>
                                <td width="10%">${orderMap['order'].realname}</td>
                                <td width="13%"><a href="<%=basePath %>reOrder/details?orderId=${orderMap['order'].id}&itemId=${map['item'].id}&status=${status}">详情</a> / <a href="#"  class="replybtn"><input type="hidden"  value="${map['item'].id}"><input type="hidden" value="${map['comment'].sellerReply}">回复</a>
                                 
                                 </td>
                            </tr>
                         </c:forEach>
                         </c:forEach> 
                        </tbody>
                    </table>
                    <div class="reply" id="">
                        <dl>
                        	<dt>回复：</dt>
                            <dd>
                            	<textarea placeholder="请输入回复内容" cols=100 rows=4 id="licenseother" onkeypress="count()" onkeyup="count()" onblur="count();" onChange="count();"></textarea>
                                <p>还能输入<input readonly type="text" size="3" id="result" value="250">个字</p>
                                <input type="hidden"  id="comm">
                            </dd>
                        </dl>
                        <div class="btn"><input type="button" value="回复" onclick="comment()"></div>
                    </div>
                    <script type="text/javascript">
					<!----------设置文本域内输入字符的多少----------------->
						function getStringUTFLength(str) { 
						 var value = str.replace(/[\u4e00-\u9fa5]/g," ");
						 //将汉字替换为两个空格
						 return value.length; 
						} 
						function leftUTFString(str,len) { 
						 if(getStringUTFLength(str)<=len) { 
						  return str; 
						 }
						 var value = str.substring(0,len); 
						 while(getStringUTFLength(value)>len) { 
						  value = value.substring(0,value.length-1); 
						 } 
						 return value; 
						} 
						function count() { 
						 var len=250;
						 var value = document.getElementById("licenseother").value; 
						 if(getStringUTFLength(value)>=len) {  
						  document.getElementById("licenseother").value = leftUTFString(document.getElementById("licenseother").value,len); 
						 } 
						 document.getElementById("result").value = len-getStringUTFLength(document.getElementById("licenseother").value); 
						} 
                    /* <!---------------回复评价内容------------------> */
                    	$('.replybtn').click(function(e) {
                    		var id = $(this).children().eq(0).val();
                    		$("#comm").val(id);
                    		var reply = $(this).children().eq(1).val();
                    		var licenseother = $("#licenseother").val();
                    		if(licenseother!=""||licenseother!=null||licenseother!=undefined){
                    			$("#licenseother").val(reply);
							}
                            $(this).parents('table').siblings('.reply').show();
                        });
						$('.reply .btn input').click(function(e) {
                            $(this).parents('.reply').hide();
                        });
						
						/* <!---------------提交回复------------------> */
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
						   				
						   			}
						   			if(data.status==false){
						   				alert("回复失败");
						   			}
						   		}
						   	});
						}
                    </script>
                    <div class="page_num">
                    	<div class="left">
                        	<!-- <input type="checkbox"> 全选
                            <input type="button" value="批量发货" class="btn"> -->
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
						<!--end of main-->
                    </div>
                </div>
            </li>
         
            
        </ul>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
/*<!-------------tab栏转换效果---------------->*/
(function xieRan(){
	$('.tab h4 span:eq(${status})').addClass('current').siblings().removeClass('current');
	$('.tab ul li:eq(${status})').addClass('current').siblings().removeClass('current');
})();
$('.tab h4 span').click(function(e) {
	var status = $(this).index();
	
	parent.document.getElementById('iframe_content').src='${BASEPATH_IN_SESSION}reOrder/list?status='+status;
	/* $(this).addClass('current').siblings().removeClass('current');
	$('.tab ul li:eq('+$(this).index()+')').addClass('current').siblings().removeClass('current'); */
	$("#status").val($(this).index());
});	


$('.btn input').click(function(e) {
    $(this).addClass('click').siblings().removeClass('click');
});

$("input[name='quanxuan']").click(function(e){
	$(".tab ul li:eq("+$(this).index()+") :checkbox[name!='quanxuan']").attr("checked",this.checked);
});

</script>


















