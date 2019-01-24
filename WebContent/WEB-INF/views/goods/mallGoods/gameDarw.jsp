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
    <title>游戏中奖信息</title>
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
            <p>你当前的位置：[商城管理]-[游戏中奖用户]</p>
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
						<select id="typeId" name="typeId" onchange="" value="${typeId }">
		                     
		                    <option value="3" <c:if test="${typeId eq 3}">selected</c:if>>积分夺宝</option>
                     		<option value="4" <c:if test="${typeId eq 4}">selected</c:if>>幸运抽奖</option>
						</select>
						<button onclick="searchWord()">搜索</button>
						<input type="submit" value="导出Excel" class="btn" name="btn" >
						<script>
							//回显搜索框；
                            $(function () {
                                $("#search").val("${searchWord}");
                            });

                            //按搜索按钮时，提交表单；
                            function searchWord() {
                                $("#searchOrChangePage").submit();
                            }
                            function excel() {
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
            <th width="10%">中奖类型</th>
            <th width="10%">中奖用户</th>
            <th width="5%">订单状态</th>
            <th width="10%">用户手机号</th>
            <th width="30%">商品名称</th>
            <th width="10%">归属店铺</th>
            <th width="15%">预览图</th>
            <th width="15%">创建时间</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="each">
                <tr>
                    
                    <td>${each.id }</td>
                    <td>${each.gameType.name }</td>
                   <td>${each.user.realname}</td>
                   <td>${each.status}</td>
                    <td>${each.user.phone}</td>
                    <td>${each.goodName}</td>
                   <td>${each.order.seller.name}</td>
                    <td><img src="${RESOURCE_LOCAL_URL }" style="width:80px;height:80px;"/></td>
                    <td>
                        <fmt:formatDate value="${each.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script>
    function zhiding(id) {
       

        $.ajax({
            url: "<%=basePath%>reMallGoods/zhiding",
            type: "post",
            data: {"id": id,"scoreMall":"scoreMall"},
            success: function (data) {
                if (data.status == true) {
                    alert("置顶成功");
                    location.reload();
                } else {
                    alert("置顶失败");
                }
            }
        });
    }
</script>
</body>
</html>
