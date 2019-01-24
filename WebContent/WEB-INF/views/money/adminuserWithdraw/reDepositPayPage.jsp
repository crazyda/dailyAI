<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>退押金确认</title>
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
            <p>你当前的位置：[资金管理管理]-[粉丝提现]-[粉丝提现支付]</p>
        </div>

        <form class="detail_form" action="getUserPayPage">
            <input type="hidden" name="Id" id="Id" value="${getmoneyRecord.id}">
            <dl>
                <dt>提现用户：</dt>
                <dd>${getmoneyRecord.users.name}</dd>
            </dl>
            <dl>
                <dt>提现账号名：</dt>
                <dd>${getmoneyRecord.name}</dd>
            </dl>
            <dl>
                <dt>提现金额：</dt>
                <dd><b>${getmoneyRecord.money}</b>元 </dd>
            </dl>
            <dl>
                <dt>手续费：</dt>
                <dd><b>${getmoneyRecord.counterFee}</b>元 </dd>
            </dl>
            <dl>
                <dt>提现账户：</dt>
                <dd>${getmoneyRecord.account}</dd>
            </dl>
              <dl>
                    <dt>开户行：</dt>
                    <dd>${getmoneyRecord.address}</dd>
                </dl>
            <dl>
                <dt>提现时间：</dt>
                <dd>${getmoneyRecord.createTime}</dd>
            </dl>
            <dl>
                <dt>支付：</dt>
                <dd>
                    <input type="radio" name="pass" checked="checked"  id="pass" value="1">
                    <label for="pass" style="margin-right:10%;">支付</label>

                    <input type="radio" name="pass"  id="fail" value="0">
                    <label for="fail">驳回</label>
                </dd>
            </dl>
            <dl>
                <dt style="line-height:4.0rem">支付说明：</dt>
                <dd><textarea placeholder="请输入支付说明" name="remark"></textarea></dd>
            </dl>
            <div class="check_btn"><input type="button" value="提交" onclick="submitForm()"></div>
        </form>
        <script>
            function submitForm(){
            if(confirm("确认提交审核？确认操作后数据无法恢复！请谨慎操作")){
                $.post('<%=basePath%>confirmUsersWithdraw/confirmUsersWithdraw',$(".detail_form").serialize(),function(data){
                    alert(data.message);
                    location.href="<%=basePath%>/confirmUsersWithdraw/confirmUsersWithdrawList";
                    
                });
                
               }
            }
        </script>

    </div>
    <div style="width:100%;height:20px;">
    </div>
</div>
</body>
</html>
