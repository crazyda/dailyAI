<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>资金查询</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>

    <!-- artDialog -->
    <link href="<%=basePath%>js/plugins/artDialog-master/artDialog-master/css/ui-dialog.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/artDialog-master/artDialog-master/dist/dialog-min.js"></script>
</head>

<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[资金管理]-[资金查询]</p>
        </div>
    </div>

    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            <th width="10%" style="height:50px;">资金类别</th>
            <th width="10%">当前余额</th>
            <th width="10%">操作</th>
            </thead>
            <tbody>
                <tr>
                    <td>广告天数</td>
                    <td><a onclick="quantityRec'${adminUser.id}')">${quantity }</a></td>
                    <td>
                    	<span onclick="quantityCharge();" style="cursor: pointer;">充值</span>&nbsp;|
						<span onclick="quantityRec();" style="cursor: pointer;">明细</span>
					</td>
                </tr>
                <tr>
                    <td>红包额度</td>
                    <td>${positionSurplus }</td>
                    <td>
                    	<span onclick="redpaperCharge();" style="cursor: pointer;">充值</span>&nbsp;|
						<span onclick="redpaperRec();" style="cursor: pointer;">明细</span>
					</td>
                </tr>
                <c:if test="${seller!=null }">
                 <tr>
                    <td>商家已确认收益</td>
                    <td>${sellerMoney }</td>
                    <td>
                    	<span onclick="doRequest();" style="cursor: pointer;">提现</span>&nbsp;|
                    	<span onclick="sellerMoneyRec(true);" style="cursor: pointer;">明细</span>
					</td>
                </tr>
                 <tr>
                    <td>商家未确认收益</td>
                    <td>${sellerPreMoney }</td>
                    <td>
						<span onclick="sellerMoneyRec(false);" style="cursor: pointer;">明细</span>
					</td>
                </tr>
                </c:if>
               
            </tbody>
        </table>
         <!-- 分页条开始 -->
    <div style="margin-top: 50px;margin-bottom: 50px;text-align: center;margin-left: 20px;">
    </div>
    <!-- 分页条开结束-->
    </div>
    <!-- 表格主体内容结束 -->

</div>
<!-- 展示主体结束 -->
<script>
function quantityCharge(){
	window.location.href='<%=basePath%>adverBuy/add';
}

function quantityRec(){
	window.location.href='<%=basePath%>adverLog/list?limit=1';
}

function sellerWithdraw(){
	window.location.href='<%=basePath%>sellerWithdraw/requestWithdraw';
}

function sellerMoneyRec(isConfirmed){
	window.location.href='<%=basePath%>sellerWithdraw/sellerAssetLog?isConfirmed='+isConfirmed;
}

function redpaperCharge(){
	window.location.href='<%=basePath%>newRedPaper/apply/add';
}

function redpaperRec(){
	window.location.href='<%=basePath%>newRedPaper/empowerCredit/getAssetRecord?id=${redPaperAsset.id}';
}

//申请提现；
function doRequest(){
    $.post("<%=basePath%>sellerWithdraw/requestWithdraw",function(data){
        alert(data.message);
        if(data.success) {
            location.href="<%=basePath%>"+data.url;
        }
    });
}


</script>
</body>
</html>
