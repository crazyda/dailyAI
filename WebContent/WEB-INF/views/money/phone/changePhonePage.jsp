<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>商家提现-更换手机号</title>
    <link type="text/css" href="<%=basePath%>css/money/phone/changePhone.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>

<body>
<form class="phonenum_form" action="bindPhone">
    <p>原手机号：${bindPhone}</p>
    <p>新手机号：<input type="text" class="txt" name="phoneNumber"/></p>
    <p>验证码：<input type="text" class="txt" name="securityCode">
        <input type="button" value="获取验证码" onClick="sendMessage()" class="btn01" data-disabled="1"></p>
    <p style="font-size:0.9rem; color:#999">绑定手机号；</p>
    <p><input type="button" value="提交申请" class="sub"></p>
</form>
</body>
</html>
<script type="text/javascript">

    //==================提交表单开始=============================================
    //异步提交表单；
    $(".sub").click(function () {
        $.post("<%=basePath%>changePhone/changePhone", $(".phonenum_form").serialize(), function (data) {
            alert(data.message);
        });
    });
    //==================提交表达结束=============================================


    //==================倒计时按钮开始=============================================
    var count = 60;//倒计时时间；
    var isSent = false;//是否发送了短信；
    var timer;//计时器；

    //发送短信，并开始倒计时；
    function sendMessage() {
        var disabled = $(".btn01").data("disabled");
        if (disabled) {
            timer = setInterval("doCountDown()", 1000);
        } else {
            return;
        }
    }

    //发送短信操作；
    function doSendMessage() {
        var phoneNumber = $("input[name='phoneNumber']").val();
        $.post("<%=basePath%>captcha/sendMessage", {'phoneNumber': phoneNumber}, function (data) {
            alert(data.message);
        })
    }

    //倒计时操作；
    function doCountDown() {
        $(".btn01").data("disabled", 0);
        if (count > 1) {
            if (!isSent) {
                doSendMessage();//发送短信；
                isSent = true;//将是否发送短信设置为true；
            }
            count--;
            $(".btn01").val(count + "秒后重试");
        } else {
            clearInterval(timer);
            count = 60;
            isSent = false;
            $(".btn01").val("获取验证码");
            $(".btn01").data("disabled", 1);
        }
    }
    //==================倒计时按钮结束=============================================
</script>
