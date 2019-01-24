<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
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
	window.location.href='<%=basePath%>newRedPaper/issue/toEdit'; 
}
function edit(id){
window.location.href="<%=basePath%>newRedPaper/issue/toEdit?type=1&id="+id; 
}
function detail(id){
	window.location.href="<%=basePath%>newRedPaper/issue/toEdit.action?type=2&id="+id; 
}
function del(id,t){
var url = "<%=basePath%>newRedPaper/issue/invalid?id="+id;
$.post(url,{},function(){
	$(t).parent().html("已失效");
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
	<form action="<%=basePath%>newRedPaperIssue/list.action" method="post">
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[红包管理]-[派发红包]</p>
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
					<span style="float:right; margin-right:30px;">总记录：${count}条</span>
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<!-- <th width="3%"><input id="s" name="" type="checkbox" value="" /></th> -->
						<th width="3%"  style="height:50px;">序号</th> 
						<th width="8%">红包名称</th>
						<th width="5%">面值（元）</th>
						<th width="5%">领取限制</th>
						<th width="8%">发布时间</th>
						<th width="8%">结束时间</th>
						<th width="5%">总金额</th>
						<th width="5%">已领金额</th>
						<th width="8%">领取数/库存</th>
						<th width="5%">已使用</th>
						<th width="10%">操作</th>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.redpaperList }" var="data">
							<tr>
								<td>${data.id}</td>
								<td><a href="<%=basePath%>newRedPaper/issue/newRedPaperLogList?id=${data.id}">${data.name}</a></td>
								<td>
								<c:if test="${data.maxParvalue==data.minParvalue }">${data.maxParvalue }</c:if>
								<c:if test="${data.maxParvalue!=data.minParvalue }">${data.minParvalue } ~ ${data.maxParvalue }</c:if>
								</td>
								<td>一人${data.limits}张</td>
								<td><fmt:formatDate value="${data.createTime}" type="date" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
								<td><fmt:formatDate value="${data.endTime}" type="date" pattern="yyyy年MM月dd日 HH:mm:ss"/></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${data.allMoney}" /></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${data.allMoneyUsed}" /></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${data.allNumColl}" />/
									<fmt:formatNumber type="number" maxFractionDigits="0" value="${data.allNum}" /></td>
								<td><fmt:formatNumber type="number" maxFractionDigits="0" value="${data.allNumUsed}" /></td>
            					<td>
            					<div>
            						<c:if test="${data.status <= FAIL && data.dateStatus < ISSUEING }">
									<span onclick="edit('${data.id }');" style="cursor: pointer;">
										<img src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />编辑
									</span>-
									</c:if>
									<c:if test="${data.status == PASS && data.dateStatus == ISSUEING }">
									<span onclick="detail('${data.id }');" style="cursor: pointer;">
										<img src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />查阅
									</span>-
									</c:if>
									<c:if test="${data.status != INVALID}">
									<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else del('${data.id }',this);"><img
										src="<%=basePath%>constant/tab/images/del.gif" width="16"
										height="16" />使失效
									</span>-
									</c:if>
									<span>
									<c:if test="${data.status==CHECKING }">待审核</c:if>
									<c:if test="${data.status==FAIL }">审核不通过</c:if>
									<c:if test="${data.status==PASS&&data.dateStatus != ISSUEING }">审核通过</c:if>
									<c:if test="${data.status==PASS&&data.dateStatus == ISSUEING }">发放中</c:if>
									<c:if test="${data.status==PASS&&data.dateStatus == TIMEOUT }">已过期</c:if>
									<c:if test="${data.status==INVALID }">已失效</c:if>
									</span>
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
