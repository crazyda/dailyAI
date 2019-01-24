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
    <title>当地特产商城待审核商品列表</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>
</head>

<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[商品审核]-[总部商城]</p>
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
            <th width="3%"><input id="s" name="" type="checkbox" value=""
                                  onclick="$(this).attr('checked')?checkAll():uncheckAll()"/></th>
            <th width="5%" style="height:50px;">序号</th>
            <th width="10%">名称</th>
            <th width="10%">所在省市</th>
            <th width="10%">投放商家</th>
            <th width="10%">预览</th>
            <th width="10%">投放时间</th>
            <!-- <th width="10%">审核信息</th> -->
            <th width="10%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="each">
                <tr>
                    <td><input type="checkbox" name="code_Value" value="${each.id }"/></td>
                    <td>${each.id }</td>
                    <td>${each.snapshotGoods.name }</td>
                    <c:if test="${each.snapshotGoods.seller.provinceEnum.provinceEnum.provinceEnum!=null}">
                    	<td>
                    	${each.snapshotGoods.seller.provinceEnum.provinceEnum.provinceEnum.name}-
                    	${each.snapshotGoods.seller.provinceEnum.provinceEnum.name}-
                    	${each.snapshotGoods.seller.provinceEnum.name}
                    	</td>   
                    </c:if>  
                    <c:if test="${each.snapshotGoods.seller.provinceEnum.provinceEnum.provinceEnum==null}">
                    	<c:if test="${each.snapshotGoods.seller.provinceEnum.provinceEnum!=null}">
                    		<td>
                    		${each.snapshotGoods.seller.provinceEnum.provinceEnum.name}-
                    		${each.snapshotGoods.seller.provinceEnum.name}
                    		</td>
                    	</c:if>
                    	<c:if test="${each.snapshotGoods.seller.provinceEnum.provinceEnum==null}">
                    		<td>
                    		${each.snapshotGoods.seller.provinceEnum.name}
                    		</td>
                    	</c:if>
                    </c:if>
                    <td>${each.snapshotGoods.seller.name }</td>
                    <td><img src="${RESOURCE_LOCAL_URL}${each.snapshotGoods.coverPicOne }" style="width:80px;height:80px;"/></td>
                    <td>
                        <fmt:formatDate value="${each.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <%-- <c:choose>
                        <c:when test="${empty each.checkDesc}">
                            <td>未审核<br/>暂无审核信息</td>
                        </c:when>
                        <c:otherwise>
                            <td>${each.checkDesc }</td>
                        </c:otherwise>
                    </c:choose> --%>
                    <td>
                        <div align="center">
                            <span class="STYLE4">
                                <span onclick="check('${each.id }');" style="cursor: pointer;">
                                    <img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16"/>审核
                                </span>
                                <script>
                                    function check(checkGoodsId) {
                                        location.href = "<%=basePath%>goodsCheck/detailOfLocalSpecialMall?checkGoodsId=" + checkGoodsId;
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
