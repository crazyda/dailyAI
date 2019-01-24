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
<title>pid管理</title>
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
					<p>你当前的位置：[淘客管理]-[PID管理]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell" style="cursor: pointer;" onclick="createPid();">
						<img src="<%=basePath %>img/new-2.png" />
						<p>新增</p>
					</div>
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" style="cursor: pointer;" onclick="if(!confirm('您确认要分配这些数据吗?'))return false; else mulDistribute();">
						<img src="${BASEPATH_IN_SESSION}constant/tab/images/edt.gif" />
						<p>分配</p>
					</div>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="<%=basePath%>taoke/pid/list.action" id="searchOrChangePage" method="post">
				    <input type ="hidden" name="type" value="2"/>
				            PID名称: <input type=text class="input" value="${search_name}" name="search_name" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索"/>
			    </form>
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
					<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="5%"  style="height:50px;">序号</th>
						<th width="7%">PID</th>
						<th width="10%">PID说明</th>
						<th width="10%">时间</th>
						<th width="10%">是否分配代理商</th>
						<th width="10%">是否分配用户</th>
						<th width="8%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.list }" var="pid" >
						<input type="hidden" class="status" value="${pid.status}"/>
						<input type="hidden" id="ids" name="ids" value=""/>
						<tr>
						<c:choose>
							<c:when test="${pid.status==0 }">
								<td><input type="checkbox" name="code_Value" value="${pid.id }" style="height:80px;" /></td>
							</c:when>
							<c:otherwise>
								<td><input type="checkbox" name="" value="" style="height:80px;" /></td>
							</c:otherwise>
						</c:choose>
							
							<td>${pid.id }</td>
							<td>${pid.pid }</td>
							<td>${pid.pidname }</td>
							<td>${pid.createtime }</td>
							<td>
								<c:choose>
									<c:when test="${pid.status==1 }">
										已分配：${pid.fengpei}
									</c:when>
									<c:otherwise>未分配代理商</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${pid.status==1&&pid.fengpeis!=null }">
										已分配：${pid.fengpeis}
									</c:when>
									<c:otherwise>代理未分配用户</c:otherwise>
								</c:choose>
								
							</td>
							<td>
								<div>
								<c:if test="${pid.status==0}">
									<span onclick="if(!confirm('您确认要分配此数据吗?'))return false;else distribute('${pid.id}')" style="cursor: pointer;">
										<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
										height="16" />分配
									</span><br/>
									<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deletePid('${pid.id}');"><img
										src="<%=basePath %>constant/tab/images/del.gif" width="16"
										height="16" />删除</span><br/>
										<span onclick="editPid('${pid.id }');" style="cursor: pointer;">
										<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
										height="16" />编辑
									</span><br/>
								</c:if>
								</div>
								<div>
							 	<c:choose>
									<c:when test="${pid.status==1&&pid.fengpeis==null}">
										<span onclick="if(!confirm('您确认要取消分配此数据吗?取消分配后，代理商的收益信息同时取消!'))return false; else isDistribute('${pid.id}')" style="cursor: pointer;">
										<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
										height="16" />取消分配
									</span><br/>
									</c:when>
									<c:when test="${pid.status==1&&pid.fengpeis!=null}">
										<span onclick="if(!confirm('您确认要强制回收此数据吗?回收分配后，代理商和用户的收益信息同时取消!'))return false; else recycleDistribute('${pid.id}')" style="cursor: pointer;">
										<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
										height="16" />强制回收
									</span><br/>
									</c:when>
								</c:choose> 
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
function editPid(id){
	  window.location.href = "${BASEPATH_IN_SESSION}taoke/pid/add?id="+id;
}

function deletePid(ids){
	  window.location.href = "${BASEPATH_IN_SESSION}taoke/pid/del?ids="+ids;
}

function createPid(){
	window.location.href = "${BASEPATH_IN_SESSION}taoke/pid/add";
}

function distribute(ids){
	window.location.href = "${BASEPATH_IN_SESSION}taoke/pid/isDistribute?ids="+ids;
}
function isDistribute(id){
	window.location.href = "${BASEPATH_IN_SESSION}taoke/pid/ofDistribute?ids="+ids;
}
function allDistribute(ids){
	window.location.href = "${BASEPATH_IN_SESSION}taoke/pid/allDistribute?ids="+ids;
}
function recycleDistribute(ids){
	window.location.href = "${BASEPATH_IN_SESSION}taoke/pid/recycleDistribute?ids="+ids;	
}

function checkAll() 
{ 
	  var code_Values = document.all['code_Value']; 
	  if(code_Values.length){ 
	  for(var i=0;i<code_Values.length;i++) 
	  { 
	  code_Values[i].checked = true; 
	  } 
	  }else{ 
	  code_Values.checked = true; 
	  } 
} 
function uncheckAll() 
{ 
	  var code_Values = document.all['code_Value']; 
	  if(code_Values.length){ 
	  for(var i=0;i<code_Values.length;i++) 
	  { 
	  code_Values[i].checked = false; 
	  } 
	  }else{ 
	  code_Values.checked = false; 
	  } 
}
function mulDistribute() 
{ 
	  var num = 0; 
	  var ids="";
	  var code_Values = document.all['code_Value']; 
	  if(code_Values.length){ 
		  for(var i=0;i<code_Values.length;i++) 
		  { 
		     if(code_Values[i].checked == true) 
			  { 
			    num ++;
			     id = code_Values[i].value;
			     ids= ids+ id+",";
			  } 
		  } 
	  }else{ 
		  if(code_Values.checked == true){ 
		     num ++ ; 
		     id = code_Values[i].value;
		     ids= ids+ id+",";
		  } 
	  } 
	  if(num == 0){ 
	    alert('请至少选择一项来分配！'); 
	  } 
	  if(num >0){ 
		  if(ids.length>0){
			  ids = ids.substring(0,ids.length-1);
		  }
		  allDistribute(ids);
	  } 
} 

</script>
</body>
</html>

