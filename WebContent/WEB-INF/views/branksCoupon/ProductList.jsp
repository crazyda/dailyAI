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
    <title>银行优惠券商品</title>
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
            <p>你当前的位置：[商城管理]-[银行优惠券商品]</p>
        </div>
       		 <div class="header-right">
       		 
					<div class="header-right-cell-line"></div>
	       		 <c:if test="${adminUser.level ==  95}">
						<div class="header-right-cell" onclick="javascript:location.href='<%=basePath%>BranksCoupon/addProduct?pid='" style="cursor: pointer;">
							<img src="<%=basePath%>img/new-2.png" />
							<p>新增</p>
						</div>
	       		 </c:if>
				</div>
				<hr align="center" />
    </div>
    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            <th width="3%" style="height:50px;">序号</th>
            <th width="10%">归属银行</th>
            <th width="10%">产品名称</th>
            <th width="10%">推广图片</th>
            <th width="10%">产品说明</th>
            <th width="8%">兑换可获积分</th>
            <th width="5%">总数量</th>
            <th width="8%">已兑换数量</th>
            <th width="5%">剩余量</th>
            <th width="10%">收分用户</th>
            <th width="10%">创建时间</th>
            <th width="10%">操作</th>
            </thead>
            <tbody>
             <c:forEach items="${list}" var="list">
                <tr>
                    <td>${list.id}</td>
                  	<td>${list.brank.branksName}</td> 
                    <td>${list.productName}</td>
                    <td>
                    	<c:forEach items="${list.productImg }" var = "map">
                    		<img class="pic" src="${RESOURCE_LOCAL_URL}${map}" style="width:80px;height:80px;"/>
                    	</c:forEach>
                    </td>
                    <td>${list.remark}</td>
                    <td>${list.score}</td>
                    <td>${list.num}</td>
                    <td>${list.useNum}</td>
                    <td>${(list.num-list.useNum)}</td>
                    <td>${list.adminUser.username}</td>
                    
                    <td>
                        <fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <div align="center">
								<c:if test="${adminUser.level == 75}">
							       <span class="STYLE4">
		                                <span onclick="agentProduct('${list.id }');" style="cursor: pointer;">
		                                    <img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16"/>添加到产品列表
		                                </span>
		                                <script>
		                                    function agentProduct(pid) {
		                                        	$.ajax({
				                                          url: "<%=basePath%>BranksCoupon/saveAgentProduct",
				                                          type: "post",
				                                          data: {"pid": pid},
				                                          success: function (data) {
				                                        	  alert(data.msg);
				                                          }
				                                      });
		                                   		 }
		                                </script>
	                         		</span>
								</c:if>
								<c:if test="${adminUser.level != 75 }">
		                            <span class="STYLE4">
		                                <span onclick="check('${list.adminUser.id }','${list.id }','${list.brank.id }');" style="cursor: pointer;">
		                                   <script>
		                                    function check(aid,pid,bid) {
		                                        	location.href = "<%=basePath%>BranksCoupon/addProduct?pid="+ pid +"&bid="+bid+"&aid="+aid;
		                                   		 }
		                                </script>
		                            </span>
		                            <span class="STYLE4">
		                                <span style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else del('${list.id }');">
		                                    <img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16"/>删除
		                                </span>
		                                <script>
		                                function del(deleteId){
		                                	  $.ajax({
		                                          url: "<%=basePath%>BranksCoupon/del",
		                                          type: "post",
		                                          data: {"id": deleteId},
		                                          success: function (data) {
		                                        	  alert(data.msg);
		                                              location.reload();
		                                          }
		                                      });
										}
		                                </script>
		                            </span>
								</c:if>
                        	
                        </div>
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
	  $(function(){
          $(".pic").click(function(){
        	  var pic=this;
              $("body").append("<div class=pics><img src="+this.src+"></img></div>");
              $(".pics").click(function(){
            		$(this).hide();  
              });
          });
      });

	</script>
</body>
</html>
