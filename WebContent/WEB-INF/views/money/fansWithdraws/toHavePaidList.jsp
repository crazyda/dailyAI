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
    <title>粉丝已支付列表</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	 <link href="<%=basePath%>css/load.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/load2.css" rel="stylesheet" type="text/css">
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
            <p>你当前的位置：[资金管理]-[粉丝已支付列表]</p>
        </div>
        <div class="header-right">
	   				<div class="header-right-cell" id="refurbish" style="cursor: pointer; width: 160px;">
						 <img src="<%=basePath%>img/refurbish.png" style="display: inline-block; width:40px; height:40px; line-height: 40px; margin: 17px 0;  "/> 
						<p style="font-size: 24px" >刷新</p>
					</div>
        </div>
        <hr align="center"/>
    </div>

    <div class="div_search" style="display: none;">
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
            <th width="3%" >序号</th>
            <th width="8%" style="height:50px;">提现粉丝号</th>
            <th width="8%" >粉丝手机号</th>
            <th width="8%" >银行账号名</th>
            <th width="10%">提现账号</th>
            <th width="10%">提现时间</th>
            <th width="6%">已支付金额</th>
            <th width="10%">支付时间</th>
            <th width="6%">手续费</th>
            <th width="10%">开户行地址</th>
            <th width="5%">支付状态</th>
            <th width="8%">交易描述</th>
            <th width="8%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="Remoney">
                <tr>
                    <!--<td><input type="checkbox" name="code_Value" value="${Remoney.id }"/></td>  -->
                     <td>${Remoney.id }</td>
                    <td>${Remoney.users.name }</td>
                    <td>${Remoney.users.name }</td>
                    
                    <td>${Remoney.name }</td>
                    
                    <td>${Remoney.account}</td>
                    <td>
                        <fmt:formatDate value="${Remoney.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${Remoney.money}</td>
                    <td>
                        <fmt:formatDate value="${Remoney.checktime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${Remoney.counterFee}</td>
                    <td>${Remoney.address }</td>
                    <td>
                    <c:if test="${Remoney.status==10}">已支付</c:if>
                    <c:if test="${Remoney.status==6}">支付成功</c:if>
                    <c:if test="${Remoney.status==9}">支付失败</c:if>
                    </td>
                    <td>
                    	${Remoney.message}
                    </td>      
                    <td>
                        <div align="center">
                          <c:if test="${Remoney.status==9}">
                            <span class="STYLE4">
                                 <u><span onclick="reject('${Remoney.id }',1,this);"  style="cursor: pointer;">
                                    支付
                                </span></u>
                                 <u  ><span onclick="reject('${Remoney.id}',2,this);"  style="cursor: pointer;">
                                  驳回
                                </span></u>
                            </span>
                           </c:if>
	                             <span class="STYLE4">
	                              <u><span onclick="changeRecord('${Remoney.users.id}');" style="cursor: pointer;">
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
            
            function reject(data,falg,obj){
            	$(obj).hide();
            	var url="";
            	var data;
            	if(falg==1){
            		 url="<%=basePath%>fansWithdraws/fansPay";
            		 data={ids:data}
            	}else if(falg==2){
            		 url="<%=basePath%>fansWithdraws/rejectPay";
            		 data={id:data}
            	}else{
            		alert("数据异常,请联系客服");
            		return;
            	}
            	
            	$.ajax({
					async:false,
					url:url,
					data:data,
					 dataType: "json",
					success:function(data){
						if(data.status!="1"){
							alert(data.message);
						}else{
							alert(data.message);
							location.href="<%=basePath%>fansWithdraws/toHavePaidList";
						}
					}
				}); 
            }
            
            $(function(){
            	$("#refurbish").click(function(){
            		location.href="<%=basePath%>fansWithdraws/toHavePaidList?timed=1"
            	});
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
