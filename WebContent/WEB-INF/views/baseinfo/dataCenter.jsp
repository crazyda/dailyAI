<%@page import="com.axp.util.StringUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Levels"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=FhqpjmNavgBUzcjqOlHfeqrR"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/messages_cn.js"></script>

<title>数据中心</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>
</head>

<body onload="load()" >
<div class="div_new_content">
  <div style="margin-left:10px;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="images/tab_05.gif">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
	                <div class="header-left">
						<p>你当前的位置：[系统管理]-[数据中心]</p>
					</div>
                </td>
              </tr>
            </table>
            </td>
          </tr>
        </table>
       </td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
     <td>
     	<form action="<%=basePath %>system/DataCentersave/list" id="saveForm" method="post">
	     <div id="products" class="r_con_wrap">
	     <div class="r_con_form" >
		      <div class="rows">
					<label>总部红包剩余金额/数量:</label>
					<span class="input">
						${redPaperMoney}/${redPaperCount}
					</span>
					<div class="clear"></div>
			  </div>
			  <div class="rows">
					<label>未确认订单数量:</label>
					<span class="input">
						${list1Count }
					</span>
					<a href="<%=basePath %>/reOrder/ordersDetail?status=10">查看详情</a>
					<div class="clear"></div>
			  </div>
			  <div class="rows">
					<label>已确认未发货数量:</label>
					<span class="input">
						${list2Count }
					</span>
					<a href="<%=basePath %>/reOrder/ordersDetail?status=20">查看详情</a>
					<div class="clear"></div>
			  </div>
			  <div class="rows">
					<label>提现未支付(粉丝)数量:</label>
					<span class="input">
					   ${fansWithdrawCount } 
					</span>
					<a><span onclick="checkFans();" style="cursor: pointer;" >
	                   		 查看详情         		
	                   </span></a>
					<div class="clear"></div>
			  </div>
			  <div class="rows">
					<label>提现未支付(商家)数量:</label>
					<span class="input">
					   ${sellerWithdrawCount } 
					</span>
					<a><span onclick="checkSeller();" style="cursor: pointer;" >
	                   		 查看详情         		
	                   </span></a>
					<div class="clear"></div>
			  </div>
			  <div class="rows">
					<label>退单未支付的数量:</label>
					<span class="input">
					   ${orderBackCount } 
					</span>
					<div class="clear"></div>
			  </div>
	     </div>
	     </div>
	     </form>
     </td>
  </tr>

</table>
</div>
</div>

<script type="text/javascript">
	function checkFans(){
		location.href="<%=basePath%>confirmUsersWithdraw/confirmUsersWithdrawList";
	}
	function checkSeller(){
		location.href="<%=basePath%>AdminwithdrawReview/list";
	}
	
</script>
</body>
</html>
