<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商家管理</title>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-2.1.0.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/mian.js"></script>
<script type="text/javascript" src="<%=basePath %>js/layer-v2.0/layer/layer.js"></script>
</head>

<body>
	<form action="<%=basePath%>professional/sellershop/list" method="post">
		<div class="main" id="div_main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[业务管理]-[商家管理]</p>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
				      商家名称: <input type=text class="input" value="${search_name} " name="search_name" />&nbsp;&nbsp;
				    <input type="submit" class="button" value="搜索" >
			</div>
			<!--end of header-->
			<div class="pageColumn" id="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="20%" style="height:50px;">序号</th>
						<th width="20%">商家名称</th>
						<th width="20%">地址</th>
						<th width="20%">创建时间</th>
						<th width="20%">基本操作</th>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.sellerlist}" var="seller">
					<tr>
					<td><input type="checkbox" name="code_Value" value="" /></td>
            		<td>${seller.id }</td>
           	 		<td>${seller.name }</td>
            		<td>${seller.address }</td>
            		<td>${seller.createtime }</td>
						<td>
								<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deleteSeller('${seller.id}');"><img
										src="<%=basePath %>constant/tab/images/del.gif" width="16"
										height="16" />删除</span>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!--end of pageColunm-->
			<div class="footer" id="footer">
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
		</div>
		<!--end of main-->
	</form>
<script>
  
  function deleteSeller(id){
	  $.ajax({
          type:"POST",
          url:"${BASEPATH_IN_SESSION}/professional/sellershop/del",
          data:{
             id:id,
          },
          success:function(result){
              if(result.result!=true){
            	  layer.alert("删除失败！");
              }else{
            	 layer.msg("删除成功！",{icon: 1, time: 1000}, function(){
	            		 location.replace(location.href);
	             });
              }
          }
      });
  }
</script>
</body>
</html>
