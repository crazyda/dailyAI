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
    <title>商家提现-商家提现资料审核</title>
    <link type="text/css" href="<%=basePath%>css/money/phone/changePhone.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="detail_top">
    <p>商家名称：${data.seller.loginname}</p>
    <p>商家联系人：${data.seller.name}</p>
</div>
<form class="data_form">
    <input type="hidden" name="id" value="${data.id}"/>
    <dl>
        <dt>提现负责人：</dt>
        <dd><label>${data.name}</label></dd>
    </dl>
    <dl>
        <dt>负责人证件：</dt>
        <dd>
            <span>
                <img src="${RESOURCE_LOCAL_URL }${data.zhengMianTu}" alt="" style="width: 80px;height: 80px;"/>
            </span>
            <span>
                <img src="${RESOURCE_LOCAL_URL }${data.fanMianTu}" alt="" style="width: 80px;height: 80px;"/>
            </span>
        </dd>
    </dl>
    <dl class="data">
        <dt>提现资料：</dt>
        <dd>
            支付宝<label>${data.zhiFuBao}</label><br>
            微信&nbsp;&nbsp;&nbsp;<label>${data.weiXin}</label><br>
            银行卡<label>${data.yinHang}</label>
            开户行<label>${data.kaiHuHang}</label>
        </dd>
    </dl>
    <dl>
        <dt>审核状态：</dt>
        <dd>
            <input type="radio" name="checkStatus" value="1"/>审核通过
            <input type="radio" name="checkStatus" value="2"/>审核不通过
        </dd>
    </dl>
    <dl>
        <dt>审核信息：</dt>
        <dd>
            <input type="text" name="checkInfo"/>
        </dd>
    </dl>
    <p><input type="button" value="提交" onclick="submitForm()"></p>
    <script>
        //提交表单；
        function submitForm() {
            $.post("submitWithdrawDataCheck", $(".data_form").serialize(), function (data) {
                alert(data.message);
                location.href = "<%=basePath%>withdrawDataCheck/checkList";
            });
        }
    </script>
</form>
</body>
</html>
