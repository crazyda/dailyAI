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
    <title>退押金申请列表</title>
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
            <p>你当前的位置：[资金管理]-[退押金申请列表]</p>
        </div>
        <div class="header-right">
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
    <form action="<%=basePath%>AdminwithdrawReview/reDepositList" method="post">
		<div class="div_search" style="margin-left: 1.5%">			
				商家名:<input type="text" value="" name = "sellerName" />&nbsp;&nbsp;
				状态:&nbsp;&nbsp; 
				<select id="type" name="type">
					<option value="-1" >请选择</option>
					<option value="1" >已确认</option>
					<option value="-1">未确认</option>
				</select>
				&nbsp;<input type=submit class="button"  value="搜索" />
		</div>
	
    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            <th width="5%"><input id="s" name="" type="checkbox" value=""
                                  onclick="$(this).attr('checked')?checkAll():uncheckAll()"/></th>
             <th width="10%" style="height:50px;">申请主体</th>
            <th width="10%" >申请用户</th>
            <th width="5%">申请金额</th>
            <th width="10%">申请时间</th>
            <th width="5%">状态</th>
            <th width="10%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="Remoney">
                <tr>
                    <td><input type="checkbox" name="code_Value" value="${Remoney.id }"/></td>
                    <td>${Remoney.adminUser.username}</td>
                    <td>${Remoney.adminUser.loginname }</td>
                    <td>${Remoney.cashpoint}</td>
                    <td>
                        <fmt:formatDate value="${Remoney.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    
                    <td>
                    <c:choose>
                    	<c:when test="${Remoney.type==-1 }">未确认</c:when>
                    	<c:otherwise>已确认</c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                        <div align="center">
                            <span class="STYLE4">
                                <u><span onclick="pay('${Remoney.id }');" style="cursor: pointer;">
                                    确认
                                </span></u>&nbsp;&nbsp;
                                
                                <script>
                                function pay(id){
                                    if(confirm("确认提交？确认操作后数据无法恢复！请谨慎操作")){
                                        $.post('<%=basePath%>AdminwithdrawReview/deposit',{id: id },function(data){
                                            alert(data.message);
                                            location.reload();
                                        });
                                        
                                       }
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
    </form>
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


<!-- 展示主体结束 -->

</body>
</html>
