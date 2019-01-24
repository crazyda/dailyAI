<%@page import="com.axp.model.MessageType"%>
<%@page import="com.axp.model.SeLive"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Levels"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>拼团置顶</title>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/layer3/layer.js"></script>
<!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
     <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>
    
	<style type="text/css">
			#uploadFileBox li{
				display: none;
			}
	</style>

</head>
<body>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[商城管理]-[拼团置顶]</p>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="<%=basePath%>reGoodsTop/list" id="searchOrChangePage" method="post">
				             拼团名称: <input type=text class="input" value="${search_name}" name="search_name" />&nbsp;&nbsp;
				             <input id="currentPage" type="hidden" name="currentPage" value="${currentPage }"/><!-- 当前页 -->
           	 		<input id="pageSize" type="hidden" name="pageSize" value="${pageSize }"/><!-- 每页显示的条数 -->
           			 <input id="totalCount" type="hidden" name="totalCount" value="${totalCount }"/><!-- 总条数 -->
           			 <input id="typeVal" type="hidden" value="${type}" />
           			 <select id="type" name="type" ">
           			 	<option value="0" >请选择</option>
           			 	<option value="1">大图置顶</option>
           			 	<option value="2">小图置顶</option>
           			 </select> 
				    <input type=submit class="button" value="搜索"/>
			    </form>
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">序号</th>
						<th width="8%">商品图</th>
						<th width="10%">轮播图</th>
						<th width="8%">商家名称</th>
						<th width="10%">商品名称</th>
						<th width="10%">上架时间</th>
						<th width="10%">下架时间</th>
						<th width="5%">是否置顶</th>
						<th width="10%">上传轮播图</th>
						<th width="8%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${result}" var="list" >
						<tr>
							<td>${list.id }</td>
							<td><img src="${RESOURCE_LOCAL_URL }${list.coverPicOne }" style="width:80px;height:80px;"/></td>
							<td class="">
								<img class="${list.id}" style="width: 100px; height: 100px;"  src="${RESOURCE_LOCAL_URL }${list.carouselPic}" />
							</td>
							<td>${list.seller.name}</td>
							<td>${list.name}</td>
							<td><fmt:formatDate value="${list.addedTime }" pattern="yyyy-MM-dd"/></td>
							<td><fmt:formatDate value="${list.shelvesTime }" pattern="yyyy-MM-dd"/></td>
							 <td>
							 <c:if test="${list.topType!=null&&list.topType==1}">大图置顶</c:if>
							  <c:if test="${list.topType!=null&&list.topType==2}">小图置顶</c:if>
							  <c:if test="${list.topType==null}">无</c:if>
							</td> 
							<td>
									<span class="input${list.id}"> 
											 <wh:UploadUtil fileDirName="teamPic" thumbH="100" thumbW="100" amount="1"
						                     targetExt="png" ></wh:UploadUtil> 
										</span>
										<br/>
										<span class="input"> 
												<input onclick="btn(${list.id})"  id="btn" style="width: 80px; height: 20px; margin-top: 5px" type="button" value="确定"/>
										</span>
										<br/>
							</td>
							<td>
								<div>
										
										<br/>
										
										<span onclick="zhiding('${list.id}',this,1)" style="cursor: pointer;">
											<img src="<%=basePath %>constant/tab/images/zhiding.png" width="16"
											height="16" />大图置顶
										</span><br/>
										<span onclick="zhiding('${list.id}',this,2)" style="cursor: pointer;">
											<img src="<%=basePath %>constant/tab/images/zhiding.png" width="16"
											height="16" />小图置顶
										</span><br/>
										
										
										<span onclick="zhiding('${list.id}',this,null)" style="cursor: pointer;">
											<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
											height="16" />取消置顶
										</span><br/>
								</div>
							</td>
						</tr>
						</c:forEach>
									
					</tbody>
				</table>
			</div>
		
	
    <!-- 分页条开结束-->
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
		</div>
				

<script>
function zhiding(id,dom,v){
    $.ajax({
        url: "<%=basePath%>/reGoodsTop/stick",
        type: "post",
        dataType:"json",
        data: {"id": id,"type":v},
        success: function (data) {
            if (data.status==1) {
            	layer.alert(data.message,{icon: 1},function(){
            		console.log($("[name='search_name']").val());
            		window.location.href = "list?search_name="+$("[name='search_name']").val()+"&type="+$("#type").val()+"&currentPage="+$("#currentPage").val();
            	});
            } else {
            	layer.alert(data.message,{icon: 2});
            }
        }
    });
}
	
	
function btn(id){
	var box=$(".input"+id).parent();
		$(".input"+id+" #uploadFileBox ul li input:eq(0)");	
	var path= $(".input"+id+" #uploadFileBox ul li input:eq(0)").val();
	var src=$(".input"+id+" #uploadFileBox ul li img").attr("src");
	
	 $.ajax({
	        url: "<%=basePath%>/reGoodsTop/uploadPic",
	        type: "post",
	        dataType:"json",
	        data: {"id": id,"path":path},
	        success: function (data) {
	            if (data.status==1) {
	            	layer.alert(data.message,{icon: 1},function(){
	            		window.location.href = "list?search_name="+$("[name='search_name']").val()+"&type="+$("#type").val()+"&currentPage="+$("#currentPage").val();
	            	});
	            	
	            } else {
	            	layer.alert(data.message,{icon: 2});
	            }
	        }
	    });
	}
	
	
	$(function(){
		
		
		var val=$("#typeVal").val();
		$("#type option").map(function(){
		 	if($(this).val()==val){ 
				$(this).attr("selected",true);
			} 	
			
		});
		
	});

</script>
</body>
</html>

