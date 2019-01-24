<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>商品规格列表</title>
	<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	
	<!-- 分页插件 -->
	<link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet" />
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
					<p>你当前的位置：[商城管理]-[商品规格列表]</p>
				</div>
				<!-- 右侧点击区域 -->
				<div class="header-right">
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="javascript:location.href='<%=basePath%>reGoodsStandard/edit'" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>
			<!-- 搜索内容区域 -->
			<%-- <div class="div_search">	
			   <form id="searchOrChangePage" method="post">
					<input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
					<input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
					<input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->
			    </form>
			</div> --%>
			
			<!-- 表格主体内容 -->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="5%" style="height:50px;">序号</th>
						<th width="10%">名称</th>
						<th width="9%">创建时间</th>
						<th width="64%">具体规格</th>
						<th width="9%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${pageResult.result }" var="each">
							<tr>
								<td><input type="checkbox" name="code_Value" value="${each.id }" /></td>
								<td>${each.id }</td>
								<td>${each.name }</td>
								<td><fmt:formatDate value="${each.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>${each.goodsStandardDetails }</td>
								<td>
									<div align="center">
										<span class="STYLE4"> 
											<span onclick="edit('${each.id }');" style="cursor: pointer;"> 
												<img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16" />编辑
											</span>
											<span style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else del('${each.id }');">
												<img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />删除
											</span>
											<script>
												function del(deleteId){
													$.post("<%=basePath%>reGoodsStandard/delete",{id:deleteId},function(data){
														alert(data.message);
														location.reload();
													});
												}
												function edit(editId){
													location.href="<%=basePath%>reGoodsStandard/edit?id="+editId;
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
			    
				<div id="page" class="m-pagination" ></div>
				<div id="eventLog"></div>
				<script>
					(function() {
						$("#page").pagination({
							pageIndex: $("#currentPage").val()-1,
							pageSize: $("#pageSize").val(),
							total:$("#totalCount").val(),
							debug: true,
							showJump: true,
							showPageSizes: true,
							pageElementSort: ['$page', '$size', '$jump', '$info']
						});
					})();
					$("#page").on("pageClicked", function(event, data) {
						$("#currentPage").val(data.pageIndex+1);
						$("#searchOrChangePage").submit();
					}).on('jumpClicked', function(event, data) {
						$("#currentPage").val(data.pageIndex+1);
						$("#searchOrChangePage").submit();
					}).on('pageSizeChanged', function(event, data) {
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
