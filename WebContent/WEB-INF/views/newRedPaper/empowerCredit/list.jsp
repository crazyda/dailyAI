<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" /> 
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/paging.css" rel="stylesheet"/>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
</head>
<script>
function add(){
	window.location.href='<%=basePath%>newRedPaper/empowerCredit/detailUI.action'; 
}
function edit(id){
	window.location.href="<%=basePath%>newRedPaper/empowerCredit/detailUI?id="+id; 
}
function history(id){
	window.location.href="<%=basePath%>newRedPaper/empowerCredit/getAssetRecord?id="+id; 
}
function del(id,t){
var url = "<%=basePath%>newRedPaper/empowerCredit/invalidAsset?id="+id;
$.post(url,{},function(){
	//$(t).parent().parent().parent().remove();
	$(t).parent().html("已冻结");
});
}
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
		source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(var i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	   return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
		for(var i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
	   source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(var i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
	else
		for(var i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}
</script>
<body>
	<form action="<%=basePath%>newRedPaper/empowerCredit/assetList" method="post">
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[红包管理]-[授权红包额度]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="add();" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
						<%-- 查询类型：  	<select id="search_type" cssStyle="width:80px;" name="search_type">
									<option value="">所有</option>
									<option value="title">标题</option>
								</select>
						&nbsp;&nbsp;
						关键字：<input type="text" class="input" value="${search_name }" name="search_name" style="width:160px;"/>
						&nbsp;&nbsp;
						活动状态：<select id="status" cssStyle="width:80px;" name="status">
									<option value="">所有</option>
									<option value="1">开启</option>
									<option value="0">关闭</option>
								</select>
						&nbsp;&nbsp;
						<input type="submit" class="button" value="搜索" /> --%>
					<span style="float:right; margin-right:30px;">总记录：${count}条</span>
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
					<!-- 
						<th width="3%"><input id="s" name="" type="checkbox" value="" /></th> -->
						<th width="3%"  style="height:50px;">序号</th> 
						<th width="8%">用户</th>
						<th width="8%">角色</th>
						<th width="5%">剩余额度</th>
						<th width="5%">已用额度</th>
						<th width="5%">总额度</th>
						<th width="8%">有效期</th>
						<th width="10%">操作</th>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.assetList }" var="data">
							<tr>
								<!-- <td><input type="checkbox" name="code_Value" value="${data.id }" /></td>  -->
								<td>${data.id}</td>
								<td>${data.adminUser.username}</td>
								<td>${data.adminUser.role.name}</td>
            					<td><fmt:formatNumber  value="${data.positionSurplus}" type="number" />元</td>
            					<td><fmt:formatNumber value="${data.positionUsed}" type="number" />元</td>
            					<td><fmt:formatNumber value="${data.positionTotal}" type="number" />元</td>
            					<td><fmt:formatDate value="${data.endTime}" type="date" pattern="yyyy-MM-dd"/></td>
            					<td>
            					<div>
            						<c:if test="${data.status==0 }">已冻结</c:if>
            						<c:if test="${data.status==5 }">已过期</c:if>
            						<c:if test="${data.status==1 }">
									<span onclick="edit('${data.id }');" style="cursor: pointer;">
										<img src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />增加额度
									</span>
									<span onclick="history('${data.id }');" style="cursor: pointer;">
										<img src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />历史
									</span>
									<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要进行冻结吗?冻结后数据不能恢复!'))return false; else del('${data.id }',this);"><img
										src="<%=basePath%>constant/tab/images/del.gif" width="16"
										height="16" />冻结余额</span>
									</c:if>
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
							<div class="page-box common-page-box" style="text-align:center">${requestScope.pageFoot }</div>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<!--end of main-->
	</form>
</body>
</html>
