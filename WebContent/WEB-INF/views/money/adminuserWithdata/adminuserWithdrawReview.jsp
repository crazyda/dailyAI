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
    <title>提现信息审核页</title>
    <link type="text/css" href="<%=basePath%>css/money/phone/changePhone.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
    
    
</head>
<body>

<form class="data_form">
    <input type="hidden" name="id" id="id" value="${log.id}"/>
    <input type="hidden" name="dataid" id ="dataid" value="${data.id}"/>
    <dl>
        <dt>资料修改用户：</dt>
        <dd><label>${data.adminUser.loginname}</label></dd>
    </dl>
   <%--  <dl>
        <dt>证件照片：</dt>
        <dd>
            <span>
                <img src="${data.image}" alt="" style="width: 80px;height: 80px;"/>
            </span>
            <span>
                <img src="${data.image2}" alt="" style="width: 80px;height: 80px;"/>
            </span>
        </dd>
    </dl> --%>
    <dl class="data">
        <dt>提现资料：</dt>
        <dd>
            姓名:<label>${log.name}(原：${data.name })</label><br/>
            身份证号:<label>${log.code}(原：${data.code })</label><br/>
           手机:<label>${log.phone}(原：${data.phone })</label>
        </dd>
    </dl>
    <dl>
        <dt>审核状态：</dt>
        <dd>
            <input type="radio" name="status" value="10"/>审核通过
            <input type="radio" name="status" value="-1"/>审核不通过
        </dd>
    </dl>
    <p><input type="button" value="提交" onclick="submitForm()"></p>
    <script>
        //提交表单；
        function submitForm() {
        	/* alert(1); */
            $.post("submitWithdrawDataReview", $(".data_form").serialize(), function (data) {
                alert(data.message);
              location.href = "<%= basePath%>/AdminwithdrawData/reviewList";
            });
        }
    </script>
</form>
</body>
</html>
