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
    <title>提现</title>
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
            <p>你当前的位置：[资金管理]-[已支付店铺提现列表]</p>
        </div>
        <div class="header-right">
            <div class="header-right-cell-line"></div>
            <script>
                function doRequest(){
                    $.post("<%=basePath%>sellerWithdraw/requestWithdraw",function(data){
                        alert(data.message);
                        if(data.success) {
                            location.href="<%=basePath%>"+data.url;
                        }
                    });
                }
            </script>
        </div>
        <hr align="center"/>
    </div>

    <div class="div_search">
        <form id="searchOrChangePage" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
            <input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
            <input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->
        </form>
    </div>

    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
       
            <thead>
            <th width="5%"><input id="s" name="" type="checkbox" value=""
                                  onclick="$(this).attr('checked')?checkAll():uncheckAll()"/></th>
            <th width="5%" style="height:50px;">商家账号</th>
            <th width="5%">提现金额</th>
            <th width="5%">手续费</th>
            <th width="10%">提现时间</th>
            <th width="5%">银行户名</th>
            <th width="10%">手机号</th>
            <th width="10%">银行账户</th>
            <th width="10%">银行地址</th>
            <th width="10%">支付时间</th>
            <th width="10%">审核说明</th>
            <th width="5%">审核状态</th>
            <th width="10%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="withdraw">
             <input type="hidden" name="code_Value" value="${withdraw.adminUser.id }"/>
                <tr>
                    <td><input type="checkbox" name="code_Value" value="${withdraw.id }"/></td>
                    
                    <td>${withdraw.adminUser.loginname }</td>
                    <td>${withdraw.money }</td>
                    <td>${withdraw.counterFee }</td>
                    <td>
                        <fmt:formatDate value="${withdraw.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${withdraw.payName }</td>
                    <td>${withdraw.adminuserWithdrawalsData.phone }</td>
                    <td>${withdraw.payCode }</td>
                    <td>${withdraw.payAddress }</td>
                    <td>
                        <fmt:formatDate value="${withdraw.checktime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${withdraw.remark }</td>
                    <td>
                    		已支付
                    </td>
                    <td>
                            <div align="center">
                            <span class="STYLE4">
	                           
	                    			<span onclick="changeRecord('${withdraw.adminUser.id}');" style="cursor: pointer;">
	                                   		资金明细
	                                </span>
	                    		
                                <script>
                                   
                                    function changeRecord(adminuserId){
                                        location.href="<%=basePath%>AdminwithdrawReview/getMoneychange?adminuserId="+adminuserId;
                                    }
                                </script>
                            </span>
                            </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- 表格主体内容结束 -->

    <!-- 分页条开始 -->
    <div style="margin-top: 50px;margin-bottom: 50px;text-align: center;margin-left: 20px;">

        <div id="page" class="m-pagination"></div>
        <div id="eventLog"></div>
        <script>
            (function () {
                $("#page").pagination({
                    pageIndex: $("#currentPage").val() - 1,
                    pageSize: $("#pageSize").val(),
                    total: $("#totalCount").val(),
                    debug: true,
                    showJump: true,
                    showPageSizes: true,
                    pageElementSort: ['$page', '$size', '$jump', '$info']
                });
            })();
            $("#page").on("pageClicked", function (event, data) {
                $("#currentPage").val(data.pageIndex + 1);
                $("#searchOrChangePage").submit();
            }).on('jumpClicked', function (event, data) {
                $("#currentPage").val(data.pageIndex + 1);
                $("#searchOrChangePage").submit();
            }).on('pageSizeChanged', function (event, data) {
                $("#pageSize").val(data.pageSize);
                $("#searchOrChangePage").submit();
            });
        </script>
    </div>
    <!-- 分页条开结束-->
</div>
<!-- 分页条结束 -->

</div>
<!-- 展示主体结束 -->

</body>
</html>