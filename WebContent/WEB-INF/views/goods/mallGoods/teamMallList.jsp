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
    <title>拼团商品投放列表</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/layer3/layer.js"></script>
    <!-- artDialog -->
    <link href="<%=basePath%>js/plugins/artDialog-master/artDialog-master/css/ui-dialog.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/artDialog-master/artDialog-master/dist/dialog-min.js"></script>
</head>

<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[商城管理]-[拼团商品投放列表]</p>
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
        <form id="searchOrChangePage" method="post" action="sellerMallList">
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
            <th width="3%">置顶</th>
            <th width="5%" style="height:50px;">序号</th>
            <th width="10%">名称</th>
            <th width="12%">预览图</th>
            <th width="10%">创建时间</th>
            <th width="10%">状态</th>
            <th width="10%">投放</th>
            <th width="10%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="each">
                <tr>
                    <td><input type="radio" name="code_Value" value="${each.id }" onclick="zhiding('${each.id }')"/></td>
                    <td>${each.id }</td>
                    <td>${each.snapshotGoods.name }</td>
                    <td><img src="${RESOURCE_LOCAL_URL }${each.snapshotGoods.coverPicOne }" style="width:80px;height:80px;"/></td>
                    <td>
                        <fmt:formatDate value="${each.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                     <td>
                        <c:if test="${each.isChecked==true }"><font color="green">审核通过</font></c:if>
                        <c:if test="${each.isChecked==false }"><font color="red">未审核</font></c:if>
                        <c:if test="${each.isChecked==null }"><font color="red">审核拒绝</font></c:if>
                        <c:choose>
                        <c:when test="${empty each.checkDesc}">
                            <br/>暂无审核信息
                        </c:when>
                        <c:otherwise>
                             <br/>${each.checkDesc }
                        </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${each.isChecked!=null }">
                            <div align="center">
										<span class="STYLE4">
											<span onclick="onPut('${each.id }');" style="cursor: pointer;">
												<img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16"/>删除
											</span>
											<script>
												function onPut(data) {
													
													layer.confirm('下架该商品会导致该商品所有优惠价一同下架', {icon: 3, title:'提示'}, function(index){
													layer.close(index);
                                                    $.post("<%=basePath%>reMallGoods/unPut", {"mallGoodsId": data, "mallId": "${goodsType}"}, function (data) {
                                                        alert(data.msg);
                                                        var goodsType = $("#goodsType").val();
                                                        location.href = "<%=basePath%>reMallGoods/teamMallList?goodsType=" + goodsType;
                                                    });
														});
													
													
                                                    //location.href="<%=basePath%>reMallGoods/unPut?mallGoodsId="+data+"&mallId=${goodsType}";
                                                }
											</script>
										</span>
                            </div>
                        </c:if>
                        <c:if test="${each.isChecked==null }">
                            审核拒绝<br/>已下架
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${each.isChecked!=null }">
                            <div align="center">
										<span class="STYLE4">
											<%-- <span onclick="addRepertory('${each.id }');" style="cursor: pointer;">
												<img src="<%=basePath%>constant/tab/images/22.gif" width="16" height="16"/>补充库存
											</span> --%>
											<script>
												function del(deleteId) {
                                                    $.post("<%=basePath%>reGoodsStandard/delete", {id: deleteId}, function (data) {
                                                        alert(data);
                                                    });
                                                }
                                                function addRepertory(id) {
                                                    var mallType = $("#goodsType").val();
                                                    location.href = "<%=basePath%>reMallGoods/addRepertoryPage?goodsId=" + id + "&mallType=" + mallType;
                                                }
											</script>
										</span>
                            </div>
                        </c:if>
                        <c:if test="${each.isChecked==null }">
                            <div align="center">
										<span class="STYLE4">
											<span onclick="delUnpassGoods('${each.id }');" style="cursor: pointer;">
												<img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16"/>删除此项
											</span>
											<script>
												function delUnpassGoods(id) {
                                                    var mallType = $("#goodsType").val();
                                                    $.post("<%=basePath%>reMallGoods/delUnpassGoods", {"goodsId": id, "mallType": mallType}, function (data) {
                                                        alert(data.message);
                                                        location.reload();
                                                    });
                                                }
											</script>
										</span>
                            </div>
                        </c:if>
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
        var goodsType = $("#goodsType").val();
        if (parseInt(goodsType) != 1) {
            alert("只有商家商城的商品才可以置顶");
            return;
        }

        $.ajax({
            url: "<%=basePath%>reMallGoods/zhiding",
            type: "post",
            data: {"id": id},
            success: function (data) {
                if (data.status == true) {
                    alert("置顶成功");
                    window.location.href = "list";
                } else {
                    alert("置顶失败");
                }
            }
        });
    }
</script>
</body>
</html>
