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
<title>订单详情-待确认</title>
<link type="text/css" href="<%=basePath %>css/orders/child-page.css" rel="stylesheet">
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/paging.css" rel="stylesheet"/>
</head>

<body>
<div class="section02" style=" border-bottom:0">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
	                <div class="header-left">
						<p>你当前的位置：[系统管理]-[数据中心]-[详情]</p>
					</div>
                </td>	
              </tr>
            </table>
            </td>
          </tr>
        </table>
    <div class="table">
			<table>
				<thead>
					<tr>
						<th>商品</th>
						<th>单价（元）</th>
						<th>数量</th>
						<th>收货人信息</th>
						<th>交易状态</th>
						<th>商家</th>
						<th>商家电话</th>
						<th>实收款（元）</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${orderList }" var="orderMap">
						<td></td>
						<tr class="order_time">
							<td colspan="9"><c:choose>
									<c:when test="${item.user.isvalid==0}">
										<p>
											订单号： <span class="order" style="color:red">${orderMap['order'].orderCode }</span>
											成交时间： <span class="time" style="color: red">${orderMap['order'].createTime }</span>
										</p>
									</c:when>
									<c:otherwise>
										<p>
											订单号： <span class="order">${orderMap['order'].orderCode }</span>
											成交时间： <span class="time">${orderMap['order'].createTime }</span>
										</p>
									</c:otherwise>
								</c:choose></td>
						</tr>
						<c:forEach items="${orderMap['items']}" var="item" varStatus="st">
							<tr class="default">
								<td width="20%">
									<dl>
										<dt>
											<img src="${RESOURCE_LOCAL_URL }${item.imgUrl}" width="100%">
										</dt>
										<dd>
											<p>${item.goodName}</p>
											<span>${item.style}</span>
										</dd>
									</dl>
								</td>
								<td width="8%">${item.goodPrice}</td>
								<td width="8%">${item.goodQuantity}</td>
								<td width="15%" style="padding:0 1%;">${orderMap['order'].realname }，${orderMap['order'].phone}<br>${orderMap['order'].address }</td>
								<td class="red" width="8%">${item.orderStatus}</td>
								<td width="10%">${orderMap['order'].seller.name}</td>
								<td width="10%">${orderMap['order'].seller.phone}</td>
								<td width="10%">${item.payPrice}</td>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
		</div>
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
</div>
</body>
</html>
