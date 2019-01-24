<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>特产置顶</title>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/mian.js"></script>

</head>
<body>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[商城管理]-[特产置顶]</p>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="<%=basePath%>cashShop/Istoplocal/list.action" id="searchOrChangePage" method="post">
				             特产名称: <input type=text class="input" value="${search_name}" name="search_name" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索"/>
			    </form>
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						
						
						<th width="5%"  style="height:50px;">序号</th>
						<th width="10%">商品图</th>
						<th width="8%">商家名称</th>
						<th width="10%">特产名称</th>
						<th width="5%">是否置顶</th>
						<th width="8%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.rlslist }" var="local" >
						<tr>
						
							 <input type="hidden" class="istop" value="${local.istop}"/> 
							<td>${local.id }</td>
							<td><img src="${RESOURCE_LOCAL_URL }${local.snapshotGoods.coverPicOne }" style="width:80px;height:80px;"/></td>
							<td>${local.snapshotGoods.seller.name}</td>
							<td>${local.snapshotGoods.name}</td>
							 <td>
								 ${local.istop==true?'置顶':'未置顶' } 
							</td> 
							<td>
								<div>
								
										<span onclick="zhiding('${local.id}',this,1)" style="cursor: pointer;">
											<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
											height="16" />置顶
										</span><br/>
										<span onclick="zhiding('${local.id}',this,0)" style="cursor: pointer;">
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
			<!--end of pageColunm-->
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
		</div>
<script>
function zhiding(id,dom,v){
	
    $.ajax({
        url: "<%=basePath%>/cashShop/Istoplocal/toTop",
        type: "post",
        data: {"sid": id,"istop":v},
        success: function (istop) {
        	var json = eval("("+istop+")");
            if (json.msg) {
            	alert("操作成功");
            	window.location.href = "list";
            } else {
                if(json.message=='fail!'){
                	alert("置顶失败！");
                }
                if(json.message=='access denied!'){
                	alert("权限限制！");
                }
            }
        }
    });
	
}
</script>
</body>
</html>

