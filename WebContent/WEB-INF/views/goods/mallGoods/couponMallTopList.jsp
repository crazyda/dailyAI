<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>优惠券商品置顶</title>
   <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>

</head>
<body>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[商城管理]-[优惠券商品置顶]</p>
				</div>
				<hr align="center" />
			</div>
			
			   <div class="div_search">
        <form id="searchOrChangePage" method="post" action="<%=basePath%>goodsCheck/listOfCouponMallTop" >
        	   	商品名称: <input type=text class="input" id="searchName" value="${searchName}" name="searchName" />&nbsp;&nbsp;
            <input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
            <input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
            <input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->
        </form>
    </div>
			
			  <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            <th width="5%" style="height:50px;">序号</th>
            <th width="10%">名称</th>
            <th width="10%">所在地区</th>
            <th width="10%">投放商家</th>
            <th width="10%">预览</th>
            <th width="10%">投放时间</th>
            <th width="10%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="each">
                <tr>
                    <td>${each.id }</td>
                    <td>${each.name }</td>
                    <td>${each.provinceEnum.name }</td>
                    <td>${each.seller.name }</td>
                    <td><img src="${RESOURCE_LOCAL_URL}${each.coverPicOne}" style="width:80px;height:80px;"/></td>
                    <td>
                         <fmt:formatDate value="${each.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <div align="center">
                            <span class="STYLE4">
                                <c:if test="${!each.isTop}">
                                <span onclick="istop('${each.id }');" style="cursor: pointer;">
                                    <img src="<%=basePath%>constant/tab/images/zhiding.png" width="16" height="16"/>置顶
                                </span>
                                </c:if>
                                <c:if test="${each.isTop}">
                                <span onclick="istop('${each.id }');" style="cursor: pointer; color:red ">
                                    <img src="<%=basePath%>constant/tab/images/cancelTop.png" width="16" height="16"/>取消置顶
                                </span>
                                </c:if>
                                
                                
                                 <span onclick="reject('${each.id }');" style="cursor: pointer; color:red ">
                                    <img src="${BASEPATH_IN_SESSION}img/new-3.png" width="16" height="16"/>驳回
                                </span>
                                
                                
                                
                                <script>
                                    function istop(id) {
                                        $.ajax({
                                        	url:"<%=basePath%>goodsCheck/extendMallTop",
                                        	data:{id:id},
                                        	success:function(ret){
                                        		alert(ret.msg);
                                        		if(ret.success){
                                        			location.href = "<%=basePath%>goodsCheck/listOfCouponMallTop?searchName="+$("#searchName").val();
                                        		}
                                        	}
                                        })
                                    }
                                    
                                    function reject(id) {
                                        $.ajax({
                                        	url:"<%=basePath%>goodsCheck/rejectExtendMallTop",
                                        	data:{id:id},
                                        	success:function(ret){
                                        		alert(ret.msg);
                                        		if(ret.success){
                                        			location.href = "<%=basePath%>goodsCheck/listOfCouponMallTop?searchName="+$("#searchName").val();
                                        		}
                                        	}
                                        })
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

