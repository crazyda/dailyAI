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
    <title>粉丝详情</title>
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
    	
    	
    	<style type="text/css">
		
			tr td{
				height: 35px !important;
			}
	
</style>
    	
</head>

<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[淘客管理]-[粉丝详情]</p>
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

    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            
            <th width="8%">用户名</th>
             <th width="5%" style="height:50px;">粉丝积分</th>
            <th width="5%" >钱包金额</th>
            <th width="10%">粉丝加入时间</th>
            <th width="10%">粉丝最后登录时间</th>
            <th width="10%">最后登录地区</th>
            <th width="10%">登录版本</th>
             <th width="8%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="user" >
                <tr >
                    <td>${user.name}</td>
                    <td>${user.score}</td>
                    <td>${user.money}</td>
                    <td>${user.createtime}</td>
                    <td>${record[user.id].lasttime}</td>
                  <%--   <td>
                        <fmt:formatDate value="${Remoney.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td> --%>
                    
                    <td>
                    	<%-- <c:if test="${record[user.id].provinceEnum!=null}&&${record[user.id].provinceEnum.level==3}">
                    		<span>${record[user.id].provinceEnum.name}---</span>	
                    	</c:if>
                    	<c:if test="${record[user.id].provinceEnum.provinceEnum!=null}&&${record[user.id].provinceEnum.level==2}">
                    		<span>${record[user.id].provinceEnum.provinceEnum.name}</span>	
                    	</c:if> --%>
                    	<%-- ${record[user.id].provinceEnum.provinceEnum.name==null?"":record[user.id].provinceEnum.provinceEnum.name==null}
                    		--
                    	${record[user.id].provinceEnum.name==null?"":record[user.id].provinceEnum.name} --%>
                    </td>
                    <td>${record[user.id].appVersion==null?"":record[user.id].appVersion}</td>
                    <td>
                        <div align="center">
                            <span class="STYLE4">
                              <u><span onclick="changeRecord('${user.id}');" style="cursor: pointer;">
	                                    资金明细
	                                </span></u>
                                  <script>
	                                    function changeRecord(userId){
	                                        location.href="<%=basePath%>fansWithdraws/cashFlow?userId="+userId;
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
