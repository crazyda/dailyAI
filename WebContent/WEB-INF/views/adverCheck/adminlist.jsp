<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
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
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
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
					<p>你当前的位置：[广告管理]-[广告天数列表]</p>
				</div>
				<div class="header-right">
					
				</div>
				<hr align="center" />
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="5%"  style="height:50px;">序号</th>
						<th width="10%">购买人</th>
						<th width="10%">所属公司</th>
						<th width="10%">天数类型</th>
						<th width="5%">数量</th>
						<th width="10%">提交时间</th>
						<th width="10%">状态</th>
						<c:if test="${type==2}">
						<th width="10%">确认人</th>
						<th width="10%">确认时间</th>
						</c:if>
						<c:if test="${type!=2}">
						<th width="10%">基本操作</th>
						</c:if>
					</thead>
					<tbody>
					<c:forEach items="${buylist}" var="buy">
						<tr>
						<td height="20" bgcolor="#FFFFFF">
						<div align="center">
              			<input type="checkbox" name="code_Value" value="${buy.id }" />
            			</div>
            			</td>
            			<td>${buy.id }</td>
           	 			<td>${buy.adminUser.username }</td>
            			<td>${buy.sellname }</td>
            			<td>${quantityHash[buy.level] }</td>
            			<td>${buy.quantity }</td>
            			<td>${buy.createtime }</td>
            			<td>${buy.status ==1?'未确认':'已确认' }</td>
            			<c:if test="${type==2 }">
							<td>${buy.checkstr }</td>
         					<td><fmt:formatDate value="${buy.checktime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</c:if>	
						<c:if test="${type!=2 }">		
            			<td height="20" bgcolor="#FFFFFF">
            			<div align="center">	
            			<c:if test="${buy.status ==1}">
						<span class="STYLE4"> 
						<span onclick="if(!confirm('确认之后不能回复！请谨慎！'))return false; else comfirmBuy('${buy.id }');" style="cursor: pointer;"> <img
							src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16" />确认</span>&nbsp; &nbsp; 
						<span style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deletebuy('${buy.id }');"><img
							src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />删除</span>
						</span>
						</c:if>
						</div>
						</td>
						</c:if>
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

  function buy(){
	  parent.addbuy();
  }

  function comfirmBuy(id){
	  parent.document.getElementById('iframe_content').src='${BASEPATH_IN_SESSION}adverCheck/confirm?id='+id;
  }
  function deletebuy(id){
	  
	  parent.document.getElementById('iframe_content').src='${BASEPATH_IN_SESSION}adverCheck/del?id='+id;
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
	  } 
	  if(num >0){ 
		  if(ids.length>0){
			  ids = ids.substring(0,ids.length-1);
		  }
		  parent.delMulschool(ids);
	  } 
  } 
</script>
</body>
</html>

