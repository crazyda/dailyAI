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
    <title>兑换码列表</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>
</head>
 <style type="text/css">
    	.pics{
    		position: absolute;
    		top:150px;
    		left:150px;
    		width:70%;
    		height: 70%;
    	}
    	.pics>img{
    		display:inline-block;
    		width:90%;
    		height: 90%;
    	}
    </style>

<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[商城管理]-[兑换码列表]</p>
        </div>
       		 <div class="header-right">
				<hr align="center" />
    		</div>
    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            <th width="5%" style="height:50px;">归属银行</th>
            <th width="5%">产品名称</th>
            <th width="10%">产品说明</th>
            <th width="5%">兑换积分</th>
            <th width="5%">总数量</th>
            <th width="5%">已兑换数量</th>
            <th width="5%">剩余量</th>
            <th width="10%">兑换码</th>
            <th width="9%">提交时间</th>
           
            </thead>
            <tbody>
             <c:forEach items="${RedeemCodes }" var="list">
                <tr>
                  	<td>${list.agentProduct.product.brank.branksName}</td> 
                    <td>${list.agentProduct.product.productName}</td>
                    <td>${list.agentProduct.product.remark}</td>
                    <td>${list.agentProduct.product.score}</td>
                    <td>${list.agentProduct.product.num}</td>
                    <td>${list.agentProduct.product.useNum}</td>
                    <td>${(list.agentProduct.product.num-list.agentProduct.product.useNum)}</td>
                    <c:choose>
                    	<c:when test="${level eq 55 && !list.isRead}">
                    		<td><span id="redeemcode${list.id }" onclick="seeCode('${list.id}')">查看并审核</span></td>
                    	</c:when>
                    	<c:otherwise>
		                    <td>兑换码:${list.redeemCode}
		                    <c:if test="${list.isRead}">
			                    ${list.isVerify?",审核通过":",审核不通过"}</td>
		                    </c:if>
                    	
                    	</c:otherwise>
                    </c:choose>
                    <td>
                        <fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                   
                </tr>
            </c:forEach> 
            </tbody> 
        </table>
    </div>
    <div class="footer">
				<div class="page">
					<table>
						<tr>
							<div class="page-box common-page-box" style="text-align:center">
								${requestScope.pageFoot }
							</div>
						</tr>
					</table>

				</div>
			</div>
    <!-- 表格主体内容结束 -->
    
 	<!-- 分页条开始 -->
</div>

</div>
	<script type="text/javascript">
	function seeCode(id){
		$.ajax({
            url: "<%=basePath%>BranksCoupon/seeCode",
            type: "post",
            data: {"id":id,"isVerify":0}, 
            success: function (data) {
            	if(data.code === 1){
            		if(confirm(data.msg+",选择确认审核通过,取消审核不通过")){
			          	  $.ajax({
			          		url: "<%=basePath%>BranksCoupon/seeCode",
			          		type: "post",
			          		data: {"id":id,"isVerify":1}, 
			          		success:function (data){
			          	  		$("#redeemcode"+id).html(data.msg+",审核通过");
			          	  		$("#redeemcode"+id).removeAttr("onclick");
			          		}
			          	  });
			          	  
            		}else{
            			$.ajax({
			          		url: "<%=basePath%>BranksCoupon/seeCode",
			          		type: "post",
			          		data: {"id":id,"isVerify":2}, 
			          		success:function (data){
			          	  		$("#redeemcode"+id).html(data.msg+",审核不通过");
			          	  		$("#redeemcode"+id).removeAttr("onclick");
			          		}
			          	  });
            		}
            	}else{
            		alert(data.msg);
            	}
              
            },
        }); 
	}
	</script>
</body>
</html>
