<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>红包详情页</title>
    <link type="text/css" href="<%=basePath%>css/money/phone/changePhone.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
</head>
<body>

<form class="data_form">
    <dl>
        <dt>红包名：</dt>
        <dd><label>${redpaper.name}</label></dd>
    </dl>
    <dl>
        <dt>总金额：</dt>
        <dd>
           <label>${redpaper.totalMoney}</label><br/>
        </dd>
    </dl>
    <dl>
    	<dt>总个数：</dt>
    	<dd>
    		<label>${redpaper.totalQuantity}</label><br/>
    	</dd>
    </dl>
     <dl>
    	<dt>时间：</dt>
    	<dd>
    		<label>${redpaper.creattime}</label><br/>
    	</dd>
    </dl>
     <dl>
    	<dt>红包类型：</dt>
    	<dd>
		   	<c:if test="${redpaper.type==10 }">
		   		<label>随机红包</label><br/>
		   	</c:if>
    		<label>定额红包</label><br/>
    	</dd>
    </dl>
    <dl>
    	<dt>剩余金额：</dt>
    	<dd>
    		<label>${redpaper.surplusMoney}</label><br/>
    	</dd>
    </dl>
    <dl>
    	<dt>剩余个数：</dt>
    	<dd>
    		<label>${redpaper.surplusQuantity}</label><br/>
    	</dd>
    </dl>
     <dl>
    	<dt>红包地区：</dt>
    	<dd>
    		<label>${redpaper.provinceEnum.name}</label><br/>
    	</dd>
    </dl>
</form>
</body>
</html>
