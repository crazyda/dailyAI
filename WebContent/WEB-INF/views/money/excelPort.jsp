<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" deferredSyntaxAllowedAsLiteral="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>数据导出</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<link type="text/css" href="<%=basePath %>css/orders/child-page.css" rel="stylesheet">
<link type="text/css" href="<%=basePath %>css/orders/merchant-order.css" rel="stylesheet">
<script src="<%=basePath %>js/order/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>

</style>
</head>
<body>
<div class="merchant">
<div class="header">
	<div class="header-left">
		<p>你当前的位置：[提现结果]-[数据导出]</p>
	</div>
	<hr/>
</div>
<br>
	<div class="tab">
		<form  id="saveForm" action="excelPortList"  class="orderform" method="post" style="margin-left: 15px;">
			<div>
	         	 用户类型:
					<select name="type" id="type">
						<option value="1" <c:if test="${type==1}">selected</c:if>>商家</option>
				    	<option value="2" <c:if test="${type==2}">selected</c:if>>粉丝</option>
					</select>
            </div>
            <p class="name">用户名：<input type="text"  name="userId"  value="${requestScope.userId }"   placeholder=""></p>
            <p class="startTime">开始时间：<input class="startTM" type=text id=starttime name="sTM" value='${requestScope.sTM }' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen' })"/></p>
            <p class="endTime">结束时间： <input class="endTM" type=text id=endtime name="eTM" value='${requestScope.eTM }' onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen' })" /></p>
         	
	         	<p class="btn">
	            	 <input type="submit" value="搜索" class="btn"  name="btn" onclick="savePtype();">
	            	<input type="submit" value="导出Excel" class="btn" name="btn" >
	            	<input type="hidden" value="${requestScope.btn}"  name="sub" >
	            	
	            </p>
            
			
        </form>
            	 <div class="table"> 
                	<table>
                    	  <thead>
					              <th width="3%">序号</th>
						            <th width="8%" style="height:50px;">账号</th>
						            <th width="10%">店铺（用户）名称</th>
						            <th width="5%">提现金额</th>
						            <th width="8%">手续费</th>
						            <th width="10%">提现时间</th>
						            <th width="10%">银行户名</th>
						            <th width="8%">手机号</th>
						            <th width="10%">审核时间</th>
						            <th width="10%">支付时间</th>
						            <th width="10%">审核说明</th>
						            <th width="10%">审核状态</th>

				            </thead>
					    <tbody>
	            <c:forEach items="${list}" var="withdraw">
	            
	            	<c:choose>
	            	
	            		<c:when test="${type==1}">
	            			 <tr>
			                   <td>${withdraw.id }</td>
			                    <td>${withdraw.adminUser.loginname }</td>
			                    <td>${withdraw.adminUser.providerSeller.name}</td>
			                    <td>${withdraw.money }</td>
			                    <td>${withdraw.counterFee }</td>
			                    <td>
			                    	${withdraw.createtime }
			                    </td>
			                    <td>${withdraw.payName }</td>
			                    <td>${withdraw.adminUser.phone }</td>
			                    <td>
			                    	${withdraw.checktime }
			                    </td>
			                    <td>
			                    	${withdraw.yiLianPayTime }
			                    </td>
			                    <td>${withdraw.remark }</td>
			                    <td>
			                    	<c:if test="${withdraw.state==10}">已支付</c:if>
			                    </td>
			                     
			                </tr>
	            		</c:when>
	            		<c:when test="${type==2 }">
	            			 <tr>
			                   <td>${withdraw.id }</td>
			                    <td>${withdraw.users.name }</td>
			                    <td>${withdraw.users.name }</td>
			                    <td>${withdraw.money }</td>
			                    <td>${withdraw.counterFee }</td>
			                    <td>
			                    	${withdraw.createTime }
			                    </td>
			                    <td>${withdraw.name }</td>
			                    <td>${withdraw.users.phone }</td>
			                    <td>
			                      ${withdraw.checktime }
			                    </td>
			                     <td>
			                      ${withdraw.yiLianPayTime }
			                    </td>
			                    <td>${withdraw.remark }</td>
			                    <td>
			                    	<c:if test="${withdraw.status==10}">已支付</c:if>      				                   
			                    </td>
			                    
			                </tr>
	            		</c:when>
	            	</c:choose>
	            </c:forEach>
            </tbody>
					</table>
					 </div> 
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
    	</div>
</div>
<script type="text/javascript">
	
function savePtype(){
		document.getElementById("saveForm").submit();
}
</script>
</body>
</html>


















