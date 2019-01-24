<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/css2.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
</head>
<script>
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
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	   return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
	//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
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
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
	else
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}
</script>
<body>
	<form>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[业务管理]-[消息中心]</p>
				</div>
				<hr align="center" />
			</div>
			
			<div class="div_search">
			   <form action="${BASEPATH_IN_SESSION}messageCenter/list" method="post">

				     关键字: <input type=text class="input" value="${search_name }" name="search_name" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索" >
			    </form>
			</div>
				<div class="header-right" style="margin-top: -60px;margin-left: 100px">
				<div class="header-right-cell" style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else mulDelete();">
						<img src="${BASEPATH_IN_SESSION}img/new-3.png" />
						<p>删除</p>
					</div>
					<div class="header-right-cell" onclick="addmessage()" style="cursor: pointer;">
						<img src="${BASEPATH_IN_SESSION}img/new-2.png" />
						<p>新增</p>
					</div>&nbsp;&nbsp;
				
				</div>
			
		
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="5%"  style="height:50px;">序号</th>
						<th width="10%">标题</th>
						<th width="9%">封面</th>
						<th width="9%">作者</th>
						<th width="9%">显示位置</th>
						<th width="9%">审核状态</th>
						<th width="9%">发送状态</th>
						<th width="9%">发送方式</th>
						<th width="9%">创建时间</th>
						<th width="9%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${messagelist }" var="message">
							<tr>
								<td><input type="checkbox" name="code_Value" value="${message.id }" /></td>
								<td>${message.id }</td>
								<td>${message.title }</td>
								<td><img src="${RESOURCE_LOCAL_URL}${message.cover }" style="width:80px;height:80px;"/></td>
								<td>${message.author }</td>
								<td>
									<c:if test="${message.type==null|| message.type==0}">消息中心</c:if>
									<c:if test="${message.type==10}">安装协议</c:if>
									<c:if test="${message.type==20}">必看攻略</c:if>
									<c:if test="${message.type==30}">关于爱小屏</c:if>
								</td>
								<td>
									<c:if test="${message.checkStatus==0}">未审核</c:if>
									<c:if test="${message.checkStatus==1}">审核通过</c:if>
									<c:if test="${message.checkStatus==2}">审核不通过</c:if>
								</td>
								<td>
									<c:if test="${message.centerStatus==0}">未发送</c:if>
									<c:if test="${message.centerStatus==1}">已发送</c:if>
								</td>
								<td>
									<c:if test="${message.isTimer==0}">即时</c:if>
									<c:if test="${message.isTimer==1}">定时</c:if>
								</td>
								<td>
									<fmt:formatDate value="${message.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								
								</td>
								<td>
								<div align="center">
								<c:if test="${ message.adminUser.id==currentUserId }">
									<span class="STYLE4"> <span
										onclick="editmessage('${message.id }');" style="cursor: pointer;"> <img
											src="${BASEPATH_IN_SESSION}constant/tab/images/edt.gif" width="16"
											height="16" />编辑
									</span> <span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else del('${message.id }');"><img
											src="${BASEPATH_IN_SESSION}constant/tab/images/del.gif" width="16"
											height="16" />删除</span>
									</span>
								</c:if>
								<c:if test="${ message.adminUser.id!=currentUserId }">
									<span class="STYLE4">
										<img src="${BASEPATH_IN_SESSION}constant/tab/images/edt.gif" width="16"
											height="16" /><font color="gray">编辑</font>
										<img src="${BASEPATH_IN_SESSION}constant/tab/images/del.gif" width="16"
											height="16" />删除
									</span>
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

	function addmessage(){
		window.location.href="add";
		}
	function del(id){
		window.location.href="del?ids="+id;
	}
	
	function editmessage(id){
		
		window.location.href="add?id="+id;
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

  function mulDelete() 
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
	    alert('请至少选择一项来删除！'); 
	    return;
	  } 
	  if(num >0){ 
		  if(ids.length>0){
			  ids = ids.substring(0,ids.length-1);
		  }
		  window.location.href="del.action?ids="+ids;
	  } 
  } 
</script>
</body>
</html>

