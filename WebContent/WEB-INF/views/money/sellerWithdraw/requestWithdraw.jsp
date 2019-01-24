<%@page import="com.axp.dao.impl.AdminUserDAOImpl" %>
<%@page import="com.axp.util.StringUtil" %>
<%@page import="com.axp.model.AdminUser" %>
<%@page import="com.axp.dao.AdminUserDAO" %>
<%@page import="com.axp.model.CashshopType" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
/* AdminUserDAO adao = new AdminUserDAOImpl();
AdminUser user = null;
user = adao.findById(current_user_id); */
    Object o = request.getAttribute("cashshopType");
    CashshopType sc = null;
    String imageurls = null;
    String imageurls1 = null;
    if (o != null) {
        sc = (CashshopType) o;
        imageurls = sc.getImg();
        imageurls1 = sc.getImg2();
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">

    <title>申请提现</title>
    <style type="text/css">

    </style>
    <script type="text/javascript" src="<%=basePath %>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>

</head>

<body>
<div class="div_new_content">
    <div style="margin-left:10px;">

        <div class="header-left">
            <p>你当前的位置：[资金管理管理]-[商家提现]-[申请提现]</p>
        </div>

        <form action="<%=basePath%>sellerWithdraw/withdraw" id="saveForm" method="post">
            <div id="products" class="r_con_wrap">
                <div class="r_con_form">
                    <input name="sellerId" type="hidden" id="id" value="${seller.id}">
                    <div class="rows">
                        <label>我的余额</label>
						<span class="input">
							<label style="color: red;font-size: large">${seller.money}</label>
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>提现金额</label>
						<span class="input">
							<input class="withdrawMoney" type="text" name="money"
                                   onKeyUp="this.value=this.value.replace(/[^\.\d]/g,'');this.value=this.value.replace('.','');"/>
						    <label style="color: red">(提现扣手续费千分之${tx}，提现后${gzr}个工作日内到账)</label>
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>提现方式</label>
						<span class="input">
							<input type="radio" name="type" class="type zhiFuBaoType" value="1">支付宝&nbsp;
							<input type="radio" name="type" class="type weiXinType" value="2">微信&nbsp;
							<input type="radio" name="type" class="type bankType" value="3">银行卡&nbsp;
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>真实姓名</label>
						<span class="input">
							<input type="hidden" name="realName" value="${seller.withdrawData.name}">
                            <label>${seller.withdrawData.name}</label>
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>提现账号</label>
						<span class="input">
                            <input type="hidden" class="zhifubaoInput" value="${seller.withdrawData.zhiFuBao}">
                            <input type="hidden" class="weixinInput" value="${seller.withdrawData.weiXin}">
                            <input type="hidden" class="yinhangInput" value="${seller.withdrawData.yinHang}">


							<input type="hidden" class="accountInput" name="account">
                            <label class="accountLable"></label>
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows bankArea" style="display:none">
                        <label>开户行</label>
						<span class="input">
							<label>${seller.withdrawData.kaiHuHang}</label>
                            <input type="hidden" name="bank" value="${seller.withdrawData.kaiHuHang}">
						</span>
                        <div class="clear"></div>
                    </div>
                    <script>

                        //如果是提现到银行卡，则显示开户银行；如果对应的提现方式值为空，则不给选；
                        $(".type").click(function () {
                            var v1 = $(".zhiFuBaoType").prop("checked");
                            var v2 = $(".weiXinType").prop("checked");
                            var v3 = $(".bankType").prop("checked");

                            var lable = $(".accountLable");
                            var input = $(".accountInput");

                            if (v3) {
                                if (!$(".yinhangInput").val()) {
                                    alert("该提现方式没有账号，不可选；");
                                    $(".type").prop("checked", false);
                                    return;
                                }

                                $(".bankArea").show();
                                lable.text($(".yinhangInput").val());
                                input.val($(".yinhangInput").val());
                            } else {
                                $(".bankArea").hide();
                            }

                            if (v1) {
                                if (!$(".zhifubaoInput").val()) {
                                    alert("该提现方式没有账号，不可选；");
                                    $(".type").prop("checked", false);
                                    return;
                                }

                                lable.text($(".zhifubaoInput").val());
                                input.val($(".zhifubaoInput").val());
                            }

                            if (v2) {
                                if (!$(".weixinInput").val()) {
                                    alert("该提现方式没有账号，不可选；");
                                    $(".type").prop("checked", false);
                                    return;
                                }

                                lable.text($(".weixinInput").val());
                                input.val($(".weixinInput").val());
                            }
                        });
                    </script>
                    <div class="rows">
                        <label></label> <span class="input"> <input type="button" value="提交申请" onclick="submitBtn();"
                                                                    style="color:#fff; font-size:1.2rem; line-height:3.0rem; width:10.0rem; background:#0e93d8; border-radius:3px; cursor:pointer; display:block;"/>
						<script>

                            //提交表单；
                            function submitBtn() {

                                //验证提现余额和最小金额；
                                var myBalances = '${seller.money}';
                                var moeny = $('input[name="money"]').val();
                                if (!moeny) {
                                    alert('请输入需要提现的金额；');
                                    return;
                                }
                                if (myBalances < 100 || moeny < 100) {
                                    alert('最小提现金额为100元；');
                                    return;
                                }
                                var balance = parseFloat(myBalances) - parseFloat(moeny);
                                if (parseFloat(balance) < 0) {//余额不足；
                                    alert('余额不足，请重新填写提取额度！');
                                    return;
                                } else if (parseFloat(balance) < moeny * (1 + 0.006)) {//余额不足以支付手续费；
                                    alert('余额不足以支付提现和手续费！');
                                    return;
                                }

                                var radioCheck = false;
                                $.each($(".type"), function (index, obj) {
                                    if ($(obj).prop("checked")) {
                                        radioCheck = true;
                                    }
                                });
                                if (!radioCheck) {
                                    alert('请选择提现方式；');
                                    return;
                                }

                                $.post('<%=basePath%>sellerWithdraw/withdraw', $("#saveForm").serialize(), function (data) {
                                    alert(data.message);
                                    location.href = "<%=basePath%>sellerWithdraw/list";
                                });
                            }
						</script>
						</span>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>

        </form>
    </div>
    <div style="width:100%;height:20px;">
    </div>
</div>
</body>
</html>
