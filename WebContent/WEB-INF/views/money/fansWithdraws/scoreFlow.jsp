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
    <title>粉丝积分信息</title>
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
            <p>你当前的位置：[资金管理]-[粉丝积分情况]</p>
        </div>
        <div class="header-right">
            <div class="header-right-cell-line"></div>
            <div class="header-right-cell" onclick="doRequest()" style="cursor: pointer;">
                <p>返回</p>
            </div>
            <script>
                function doRequest(){
                   history.go(-1);
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
            <div style="margin-left: 1%">
           		<label>积分类别：</label> 
           		<select id="type" name="type">
                <option value="-1">请选择${type}</option>
            	<option value="1" <c:if test="${type==1}">selected</c:if>>邀请</option>
				<option value="2" <c:if test="${type==2}">selected</c:if>>刷屏解锁</option>
				<option value="3" <c:if test="${type==3}">selected</c:if>>访问微网站</option>
				<option value="4" <c:if test="${type==4}">selected</c:if>>注册</option>
	            <option value="5" <c:if test="${type==5}">selected</c:if>>分享</option>
	            <option value="6" <c:if test="${type==6}">selected</c:if>>补充个人信息</option>
	            <option value="7" <c:if test="${type==7}">selected</c:if>>网络通话</option>
	            <option value="8" <c:if test="${type==8}">selected</c:if>>滑屏浏览广告</option>
	            <option value="9" <c:if test="${type==8}">selected</c:if>>换货商品</option>
            </select>
            &nbsp;&nbsp;
             <input type=submit class="button" value="搜索">
            </div>
        </form>
    </div>

    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            <th width="3%"><input id="s" name="" type="checkbox" value=""
                                  onclick="$(this).attr('checked')?checkAll():uncheckAll()"/></th>
            <th width="5%" style="height:50px;">提现人</th>
            <th width="12%">积分</th>
            <th width="10%">类型</th>
            <th width="12%">创建时间</th>
            </thead>
             <tbody>
            <c:forEach items="${pageResult.result }" var="score">
            
                <tr>
                    <td><input type="checkbox" name="code_Value" value="${score.id }"/></td>
                    <td>${score.users.name}</td>
                    <td>${score.score}</td>
                    <td>${score.remark }</td>
                    <td>
                        <fmt:formatDate value="${score.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
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
