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
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>

<body style="background: white">
<div class="div_new_content">



     <div style="margin-left:10px;">

        <div class="header-left">
            <p>你当前的位置：[资金管理管理]-[提现]-[提现审核]</p>
        </div>

        <form class="detail_form" action="AdminwithdrawReview">
            <input type="hidden" name="Id" value="${withdraw.id}">
            <dl>
                <dt>系统用户：</dt>
                <dd>${withdraw.adminUser.loginname}</dd>
            </dl>
            <dl>
                <dt>店铺名称：</dt>
                <dd>${seller.name}</dd>
            </dl>
            <dl>
                <dt>提现金额：</dt>
                <dd><b>${withdraw.money}</b>元 </dd>
            </dl>
            <dl>
                <dt>提现手续费：</dt>
                <dd>${withdraw.counterFee}</dd>
            </dl>
            <dl>
                <dt>提现时间：</dt>
                <dd>${withdraw.createtime}</dd>
            </dl>
            <dl>
                <dt>提现账号名：</dt>
                <dd>${withdraw.adminuserWithdrawalsData.name}</dd>
            </dl>
            
            <dl>
                <dt>提现资料-电话：</dt>
                <dd>${withdraw.adminuserWithdrawalsData.phone}</dd>
            </dl>
            
            <dl>
                <dt>提现资料-省份证号：</dt>
                <dd>${withdraw.adminuserWithdrawalsData.code}</dd>
            </dl>
            
            <dl>
                <dt>提现银行：</dt>
                <dd>${withdraw.adminuserWithdrawalsBank.bankName}</dd>
            </dl>
            
            <dl>
                <dt>提现银行卡号：</dt>
                <dd>${withdraw.adminuserWithdrawalsBank.cardNo}</dd>
            </dl>
            
            <dl>
                <dt>提现银行地址：</dt>
                <dd>${withdraw.adminuserWithdrawalsBank.bankAddress}</dd>
            </dl>
           
         <dl>
                <dt>审核：</dt>
                <dd>
                    <input type="radio" name="pass" checked="checked"  id="pass" value="1">
                    <label for="pass" style="margin-right:10%;">通过</label>

                    <input type="radio" name="pass"  id="fail" value="0">
                    <label for="fail">驳回</label>
                </dd>
            </dl>
            <dl>
                <dt style="line-height:4.0rem">审核说明：</dt>
                <dd><textarea placeholder="请输入审核说明" name="remark"></textarea></dd>
            </dl>
            <div class="check_btn"><input type="button" value="提交" onclick="submitForm()"></div>
            
            <script>
            
            function submitForm(){
                $.post("<%=basePath%>sellerWithdraws/verifying",$(".detail_form").serialize(),function(data){
                	
                	if(data.status==1){
                		alert(data.message);
                    	location.href='<%=basePath%>sellerWithdraws/sellerExamineList';
                	}else{
                    	alert(data.message);
                	}
                });
            }
        </script>
        </form>
        

    </div>
    <div style="width:100%;height:20px;">
    </div>
</div>
</body>
</html>
