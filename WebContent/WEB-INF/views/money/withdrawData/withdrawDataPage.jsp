<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>商家提现-资料填写</title>
    <link type="text/css" href="<%=basePath%>css/money/phone/changePhone.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="detail_top">
    <p>商家名称：${seller.name}</p>
    <p>商圈：${shangQuan.username}</p>
    <p>商家联系人：${seller.contacts}</p>
    <p>商圈联系人：${shangQuan.contacts}</p>
</div>
<form class="data_form">
    <dl>
        <dt>提现负责人：</dt>
        <dd><input class="withdrawName" type="text" name="name"></dd>
    </dl>
    <dl>
        <dt>负责人证件：</dt>
        <dd>
            <%--<span><input type="text" name="zhengmiantu"/></span>
            <span><input type="text" name="fanmiantu"/></span>--%>
            <span>
                <wh:UploadUtil fileDirName="basegoods_cover" thumbH="100" thumbW="100" amount="1"
                               submitName="zhengmiantu" targetExt="jpg">身份证正面图</wh:UploadUtil>
            </span>
            <span>
                <wh:UploadUtil fileDirName="basegoods_cover" thumbH="100" thumbW="100" amount="1"
                               submitName="fanmiantu" targetExt="jpg">身份证反面图</wh:UploadUtil>
            </span>
        </dd>
    </dl>
    <dl class="data">
        <dt>提现资料：</dt>
        <dd>
            支付宝<input class="needOne" type="text" name="zhifubao"><br>
            微信&nbsp;&nbsp;&nbsp;<input class="needOne" type="text" name="weixin"><br>
            银行卡<input class="needOne" type="text" name="yinhangka">
            开户行<input type="text" name="kaihuhang">
        </dd>
    </dl>
    <p><input type="button" value="提交" onclick="submitForm()" class="sub"
              style="color:#fff; font-size:1.0rem; line-height:2.5rem; width:6.0rem; background:#0e93d8; border-radius:3px; cursor:pointer;" /></p>
    <script>
        //提交表单；
        function submitForm() {

            //验证提现人；
            var withdrawName=$(".withdrawName").val();
            if(!withdrawName) {
                alert("请输入提现负责人");
                return;
            }

            //提现资料必须要有一个才能提交；
            var messageSize = $(".needOne").filter(function () {
                if ($(this).val()) {
                    return true;
                } else {
                    return false;
                }
            }).size();
            if(messageSize<=0) {
                alert("提现资料必须为必填选项，请填写");
                return;
            }

            //提交表单；
            $.post('<%=basePath%>withdrawData/submitWithdrawData', $(".data_form").serialize(), function (data) {
                alert(data.message);
            });
        }
    </script>
</form>
</body>
</html>
