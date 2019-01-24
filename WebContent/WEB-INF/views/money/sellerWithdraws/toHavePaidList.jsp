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
    <title>已支付商家提现列表</title>
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
/* .pageColumn2{padding:0;margin:0;width:100%;}
.pageColumn2 table{ width:100%;height:auto;}
.pageColumn2 thead{ border:2px solid #2d84b9; background:#2d84b9;}
.pageColumn2 thead th{border:2px solid #fff; text-align:center; height:10px;line-height:10px;color:#fff;font-size:18px;}
.pageColumn2 tbody tr:hover td {
	background: #E6EFF9;
}
.pageColumn2 tbody td{padding:5px;border:1px solid #fff;  text-align:center;color:#545454;font-size:15px;} */
    </style>
</head>

<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[资金管理]-[已支付商家提现列表]</p>
        </div>
        <div class="header-right">
            
            <div class="header-right-cell" id="refurbish" style="cursor: pointer; width: 160px;">
						 <img src="<%=basePath%>img/refurbish.png" style="display: inline-block; width:40px; height:40px; line-height: 40px; margin: 17px 0;  "/> 
						<p style="font-size: 24px" >刷新</p>
					</div>
           <%--  <script>
                function doRequest(){
                    $.post("<%=basePath%>sellerWithdraw/requestWithdraw",function(data){
                        alert(data.message);
                        if(data.success) {
                            location.href="<%=basePath%>"+data.url;
                        }
                    });
                }
            </script> --%>
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
    <div class="pageColumn ">
        <table class="list" style="width:98%;margin:0 auto;">
       
            <thead>
            <th width="3%">序号<!-- <input id="s" name="" type="checkbox" value=""
                                  onclick="$(this).attr('checked')?checkAll():uncheckAll()"/> --></th>
            <th width="5%" style="height:50px;">商家账号</th>
            <th width="5%">店铺名称</th>
            <th width="3%">提现金额</th>
            <th width="3%">手续费</th>
            <th width="5%">提现时间</th>
            <th width="5%">银行户名</th>
            <th width="5%">手机号</th>
            <th width="8%">银行账户</th>
            <th width="8%">银行地址</th>
            <th width="7%">支付时间</th>
            <th width="7%">审核说明</th>
            <th width="5%">审核状态</th>
            <th width="8%">交易描述</th>
            <th width="6%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${pageResult.result }" var="withdraw"  varStatus="i">
                <tr >
                    <td>${withdraw.id }</td>
                    
                    <td>${withdraw.adminUser.loginname }</td>
                    <td>${withdraw.adminUser.providerSeller.name }</td>
                    <td>${withdraw.money }</td>
                    <td>${withdraw.counterFee }</td>
                    <td>
                        <fmt:formatDate value="${withdraw.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${withdraw.payName }</td>
                    <td>${withdraw.adminuserWithdrawalsData.phone }</td>
                    <td>${withdraw.payCode }</td>
                    <td>${withdraw.payAddress }</td>
                    <td>
                        <fmt:formatDate value="${withdraw.checktime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>${withdraw.remark }</td>
                    <td>
                    	<c:if test="${withdraw.state==10}">已支付</c:if>
                    	<c:if test="${withdraw.state==20}">支付成功</c:if>
                    	<c:if test="${withdraw.state==30}">支付失败</c:if>
                    </td>
                    <td>${withdraw.message}</td>
                    <td>
                            <div align="center">
                            <span class="STYLE4">
	                           		<c:if test="${withdraw.state==30}">
	                           		<span onclick="reject('${withdraw.id }',1,this);" class="switch"  style="cursor: pointer;">
                                    	支付
                                	</span>
	                           		<span onclick="reject('${withdraw.id}',2,this);" class="switch" style="cursor: pointer;">
	                                   		驳回
	                                </span>
	                           		</c:if>
	                    			<span onclick="changeRecord('${withdraw.adminUser.id}');" style="cursor: pointer;">
	                                   		资金明细
	                                </span>
	                    			
                                <script>
                                   
                                    function changeRecord(adminuserId){
                                        location.href="<%=basePath%>AdminwithdrawReview/getMoneychange?adminuserId="+adminuserId;
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
            		 url="<%=basePath%>sellerWithdraws/sellerPay";
            		 data={ids:data}
            	}else if(falg==2){
            		 url="<%=basePath%>sellerWithdraws/rejectSellerPay";
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
							location.href="<%=basePath%>sellerWithdraws/toHavePaidList";
						}
					}
				});  
            }
            
            $(function(){
            	$("#refurbish").click(function(){
            		location.href="<%=basePath%>sellerWithdraws/toHavePaidList?timed=1";
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
