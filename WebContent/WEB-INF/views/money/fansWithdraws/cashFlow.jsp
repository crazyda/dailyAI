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
    <title>粉丝金额变动情况</title>
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
            <p>你当前的位置：[资金管理]-[粉丝金额变动情况]</p>
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
           		<label>资金类别：</label> 
           		<select id="type" name="type">
                <option value="-1">请选择${type}</option>
            	<option value="1" <c:if test="${type==1}">selected</c:if>>红包</option>
				<option value="2" <c:if test="${type==2}">selected</c:if>>退款</option>
				<option value="3" <c:if test="${type==3}">selected</c:if>>提现</option>
				<option value="4" <c:if test="${type==4}">selected</c:if>>充值</option>
	            <option value="5" <c:if test="${type==5}">selected</c:if>>购买商品</option>
	            <option value="6" <c:if test="${type==6}">selected</c:if>>(事业)合伙人推广收益</option>
	            <option value="7" <c:if test="${type==7}">selected</c:if>>(事业)合伙人分佣收益</option>
            </select>
            &nbsp;&nbsp;
             <input type=submit class="button" value="搜索">
            </div>
            
            <div style="float: right; height: 100%; margin-right: 10%; margin-top:-28px;color: red;font-size: 18px;" >
					<div class="money" style="float: left;">	
					<span> 已确认总金额&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="money">${totalMoney}&nbsp;元</span>
					</div>
					<div class="revenue" style="float: left;">	
					<span> 未确认总金额&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="revenue">${untotalMoney}&nbsp;元</span>
					</div>
				
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
            <th width="12%">变动金额</th>
            <th width="10%">时间</th>
            <th width="12%">类型</th>
            <th width="20%">说明</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="cash">
            
                <tr>
                    <td><input type="checkbox" name="code_Value" value="${cash.id }"/></td>
                    <td>${cash.usersByUserId.name}</td>
                    <td>${cash.money}</td>
                    <td>
                        <fmt:formatDate value="${cash.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                    	<c:choose>
                    		<c:when test="${cash.type==1}">已确认</c:when>
                    		<c:when test="${cash.type==-1}"><font color="red">未确认</font></c:when>
                    		<c:otherwise>其他</c:otherwise>
                    	</c:choose>
                    </td>

                    <td>

                    <c:choose>

                    	<c:when test="${cash.account!=null }">
                    	 	    <u><span  >
	                                    ${cash.remark }（红包id${cash.account}）
	                            </span></u>
                    	</c:when>
                   		 <c:otherwise>
                    		${cash.remark }
                    	</c:otherwise>  
                    </c:choose>
                    
                   
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
