<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>提现审核</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link href="<%=basePath%>css/money/withdraw/sellerWithdrawCheckPage.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>

<body style="background: white">
<div class="div_new_content">
    <div style="margin-left:10px;">

        <div class="header-left">
            <p>你当前的位置：[资金管理管理]-[商家提现]-[商家提现审核]</p>
        </div>

        <form class="detail_form" action="sellerWithdrawCheck">
            <input type="hidden" name="recordId" value="${record.id}">
            <dl>
                <dt>提现商家：</dt>
                <dd>${record.seller.name}</dd>
            </dl>
            <dl>
                <dt>提现金额：</dt>
                <dd><b>${record.money}</b>元 <%--<a href="#">资金详情</a>--%></dd>
            </dl>
            <dl>
                <dt>提现负责人：</dt>
                <dd>${record.realName}</dd>
            </dl>
            <dl>
                <dt>提现类型：</dt>
                <dd>${record.styleString}</dd>
            </dl>
            <dl>
                <dt>提现账号：</dt>
                <dd>${record.account}</dd>
            </dl>
            <c:if test="${style==3}">
                <dl>
                    <dt>开户行：</dt>
                    <dd>${record.bank}</dd>
                </dl>
            </c:if>
            <dl>
                <dt>提现时间：</dt>
                <dd>${record.createTime}</dd>
            </dl>
            <dl>
                <dt>审核：</dt>
                <dd>
                    <input type="radio" name="pass" id="pass" value="1">
                    <label for="pass" style="margin-right:10%;">通过</label>

                    <input type="radio" name="pass" id="fail" value="0">
                    <label for="fail">不通过</label>
                </dd>
            </dl>
            <dl>
                <dt style="line-height:4.0rem">审核原因：</dt>
                <dd><textarea placeholder="请输入审核理由" name="message"></textarea></dd>
            </dl>
            <div class="check_btn"><input type="button" value="提交" onclick="submitForm()"></div>
        </form>
        <script>
            function submitForm(){
                $.post('<%=basePath%>sellerWithdraw/sellerWithdrawCheck',$(".detail_form").serialize(),function(data){
                    alert(data.message);
                    location.href='<%=basePath%>sellerWithdraw/sellerWithdrawCheckList';
                });
            }
        </script>

    </div>
    <div style="width:100%;height:20px;">
    </div>
</div>
</body>
</html>
