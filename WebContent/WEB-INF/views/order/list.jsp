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
    <title>订单列表</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>
</head>

<body>
<form id="searchOrChangePage"><!-- 用于提交查询条件，或者翻页的一些数据信息； -->
    <input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
    <input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
    <input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->


    <!-- 展示主体 -->
    <div class="main" style="height:auto;">
        <div class="header">
            <!-- 导航栏 -->
            <div class="header-left">
                <p>你当前的位置：[商城管理]-[订单列表]</p>
            </div>
            <!-- 右侧点击区域 -->
            <div class="header-right"> 
            </div>
            <hr align="center"/>
        </div>
        <!-- 搜索内容区域 -->
      <div class="div_search">
           <form action="list" id="searchOrChangePage" method="post">
           	交易状态：
           <select name="order_status">
           		<option value="-1" selected="selected">请选择</option>
           		<option value="10">下单中</option>
           		<option value="20">商家未确认</option>
           		<option value="30">发货中</option>
           		<option value="40">待评价</option>
           		<option value="50">已完成</option>
           </select>
		   <input type="submit"  id = "searchs"  class="button" value="搜索" />
            </form>
        </div>
	
        <!-- 表格主体内容 -->
        <div class="pageColumn">
            <table class="list" style="width:98%;margin:0 auto;">
                <thead>
                <th width="3%"><input id="s" name="" type="checkbox" value=""
                                      onclick="$(this).attr('checked')?checkAll():uncheckAll()"/></th>
                <th width="5%" style="height:50px;">序号</th>
                <th width="10%">编码</th>
                <th>创建时间</th>
                <th>商家</th>
                <th>商品名称</th>
                <th>商品数量</th>
                <th>支付金额</th>
                <th>用户地址</th>
                <th>用户姓名</th>
                <th>物流公司</th>
                <th>订单状态</th>
                <th>操作</th>
                </thead>
                <tbody>
                <c:forEach items="${pageResult.result }" var="each">
                    <tr>
                        <td><input type="checkbox" name="code_Value" value="${each.id }"/></td>
                        <td>${each.id }</td>
                        <td>${each.orderCode }</td>
                        <td><fmt:formatDate value="${each.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${each.seller.name }</td>
                        <td>${each.seller.name }</td>
                        <td>${each.seller.name }</td>
                        <td>${each.payPrice }</td>
                        <td>${each.address }</td>
                        <td>${each.user.name }</td>
                        <td>${each.logisticsCompay }</td>
                        <td>
                        	<span id="span${each.id }">
                        	<c:if test="${each.status==-1 }">      
                                  <font color="green">待支付</font>    
                             </c:if>
                        	<c:if test="${each.status==10 }">      
                                  <font color="green">待确认</font>    
                             </c:if>
                             <c:if test="${each.status==20 }">      
                                   <font color="green">商家已确认</font>  
                             </c:if>
                             <c:if test="${each.status==30 }">      
                                 <font color="green">待收货</font>     
                             </c:if>
                             <c:if test="${each.status==40 }">      
                                  <font color="green">待评价</font>    
                             </c:if>
                             <c:if test="${each.status==50 }">      
                                   <font color="green">已完成</font>   
                             </c:if>
                             </span>
                        </td>
                        <td>
                            <div align="center">
                                <span id="span2${each.id }" class="STYLE4">
                                <c:if test="${each.status==10 }">
                                	<span onclick="ensure('${each.id }');" style="cursor: pointer;" onclick="if(!confirm('您确定要确认吗？'))return false; else confirmOrder('${record.id }');">
                                        <img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16"/>确认
                                    </span>
                                    <span style="cursor: pointer;">
                                        <img src="<%=basePath%>constant/tab/images/del.gif" width="16"
                                             height="16"/><font color="silver">物流详情</font>
                                    </span>	
                                </c:if>
                                <c:if test="${each.status>=20 }">      
                                        <img src="<%=basePath%>constant/tab/images/edt.gif" width="16"
                                             height="16"/><font color="silver">确认</font>
                                    <span style="cursor: pointer;"
                                          onclick="detail('${each.id }');">
                                        <img src="<%=basePath%>constant/tab/images/del.gif" width="16"
                                             height="16"/>物流详情
                                    </span>	
                                </c:if> 
                                <c:if test="${each.status<10}">      
                                        <img src="<%=basePath%>constant/tab/images/edt.gif" width="16"
                                             height="16"/><font color="silver">确认</font>
                                    	<img src="<%=basePath%>constant/tab/images/del.gif" width="16"
                                             height="16"/><font color="silver">物流详情</font>
                                </c:if> 
                                    <script>
                                        function detail(id) {
                                        	 window.location.href="detail?id="+id;
                                        }
                                        function ensure(editId) {
                                        
                                        	$.ajax({
                                        		type:"post",
                                        		url:"<%=basePath %>reOrder/ensureByAjax",
                                        		data:{"id":editId},
                                        		async:true,
                                        		success:function(msg){
                                        			if(msg.status){
                                        				$("#span"+editId).empty();
                                            			$("#span"+editId).append("<font color='green'>商家已确认</font>");
                                            			$("#span2"+editId).empty();
                                            			$("#span2"+editId).append("<img src='<%=basePath%>constant/tab/images/edt.gif' width='16'"+
    	                                                          "height='16'/><font color='silver'>确认</font>"+
    	                                                          "<span style='cursor: pointer;' onclick=\"detail('"+'${each.id }'+"');\">"+      
    	                                                          "<img src=\"<%=basePath%>constant/tab/images/del.gif\" width=\"16\" height=\"16\"/>物流详情</span>");
                                        			}
                                        			
                                        		}
                                        	});
                                           
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
</form>

</body>
</html>
