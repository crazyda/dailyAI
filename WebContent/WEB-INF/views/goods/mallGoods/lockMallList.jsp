<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />   
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>积分夺宝和积分抽奖获奖用户</title>
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
            <p>你当前的位置：[商城管理]-[积分夺宝和积分抽奖获奖用户]</p>
        </div>
        <div class="header-right">
            <div class="header-right-cell-line"></div>
            <div class="header-right-cell" onclick="javascript:location.href='<%=basePath%>reBaseGoods/edit'" style="cursor: pointer;">
                <%-- <img src="<%=basePath%>img/new-2.png" />
                <p>新增</p> --%>
            </div>
        </div>
        <hr align="center"/>
    </div>

    <div class="div_search">
        <form id="searchOrChangePage" method="post" action="lockMallList">
            <input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
            <input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
            <input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->

					<span class="input">
						<select id="goodsType" name="goodsType" onchange="changeGoodsType()" style="display: none;">
							<c:forEach items="${selectMall }" var="each">
                                <option value="${each.type }">${each.name }</option>
                            </c:forEach>
						</select>
						<script>
							//回显选项框；
                            $(function () {
                                var options = $("#goodsType option");
                                $.each(options, function (index, value) {
                                    if ('${goodsType}' == $(value).val()) {
                                        $(value).prop("selected", "selected");
                                    }
                                });
                            });

                            //更改选项时提交表单，重新搜索内容；
                            function changeGoodsType() {
                                $("#currentPage").val("1");//更换商城时，需要设置当前页为1，否则可能找不到任何数据；
                                $("#searchOrChangePage").submit();
                            }
						</script>
					</span>
					<span class="input">
						商品名称：
						<input id="search" name="searchWord" type="text" value=""/>
						<button onclick="searchWord()">搜索</button>
						<script>
							//回显搜索框；
                            $(function () {
                                $("#search").val("${searchWord}");
                            });

                            //按搜索按钮时，提交表单；
                            function searchWord() {
                                $("#searchOrChangePage").submit();
                            }
						</script>
					</span>
        </form>
    </div>

    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
           
            <th width="5%" style="height:50px;">序号</th>
            <th width="10%">中奖用户</th>
            <th width="12%">提供奖品商家</th>
            <th width="10%">夺宝商品</th>
            <th width="10%">状态</th>
             <th width="10%">夺宝时间</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="each">
                <tr>
                    <td>${each.id }</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                     	 <fmt:formatDate value="${each.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    
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
