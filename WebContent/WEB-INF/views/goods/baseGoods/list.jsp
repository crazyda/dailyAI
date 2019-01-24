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
	<title>基础商品列表</title>
	<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

	<!-- 分页插件 -->
	<link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet" />
	<script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>

	<!-- artDialog -->
	<link href="<%=basePath%>js/plugins/artDialog-master/artDialog-master/css/ui-dialog.css" rel="stylesheet"/>
	<script src="<%=basePath%>js/plugins/artDialog-master/artDialog-master/dist/dialog-min.js"></script>
	<script  src="<%=basePath%>js/goods/goodsStandard.js"></script>
</head>

<body>

		<!-- 展示主体 -->
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[商城管理]-[基础商品列表]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="javascript:location.href='<%=basePath%>reBaseGoods/edit'" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>

			<div class="div_search">
			   <form id="searchOrChangePage" method="post" action="list">
					<input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
					<input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
					<input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->

				   <%--商品名称关键字搜索--%>
				   <span class="input">
						商品名称：
						<input id="search" name="searchWord" type="text" value=""/>
						<button onclick="searchWord()">搜索</button>
						<script>
							//回显搜索框；
							$(function () {
								$("#search").val("${searchWord}");
							});

							//按搜索按钮时，提交表单；
							function searchWord() {
								$("#searchOrChangePage").submit();
							}
						</script>
					</span>

			    </form>
			</div>

			<!-- 表格主体内容 -->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="5%" style="height:50px;">序号</th>
						<th width="10%">商铺</th>
						<th width="10%">名称</th>
						<th width="12%">预览图</th>
						<th width="10%">创建时间</th>
						<th width="18%">已投放商城</th>
						<th width="10%">投放</th>
						<th width="10%">操作</th>
					</thead>
					<tbody>
						<c:forEach items="${pageResult.result }" var="each">
							<tr>
								<td><input type="checkbox" name="code_Value" value="${each.id }" /></td>
								<td>${each.id }</td>
								<td>${each.seller.name }</td>
								<td>${each.name }</td>
								<td><img src="${RESOURCE_LOCAL_URL }${each.coverPicOne }" style="width:80px;height:80px;"/></td>
								<td>
									<fmt:formatDate value="${each.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>${each.launchMallsName }</td>
								
								<td>
									<div align="center">
										<span class="STYLE4"> 
											<span onclick="put('${each.id }');" style="cursor: pointer;"> 
												<img src="<%=basePath%>constant/tab/images/22.gif" width="16" height="16" />投放
											</span>
											
											<script>
												function put(data){
													location.href="<%=basePath%>reBaseGoods/putPage?baseGoodsId="+data;
													//$.post("<%=basePath%>reBaseGoods/putPage","{'baseGoodsId':data}");
												}
												
												
											</script>
										</span>
									</div>
								</td>
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
													$.post('<%=basePath%>reBaseGoods/_delete',{id:deleteId},function(data){
														alert(data.message);
                                                        location.reload();
													});
												}
												function edit(editId){
													$.post('<%=basePath%>reBaseGoods/edit',{"id":editId},function(data){
														if(data.success) {
															location.href="<%=basePath%>reBaseGoods/edit?id="+editId+"&flag=1";
														}else{
															alert(data.message);
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

</body>
</html>
