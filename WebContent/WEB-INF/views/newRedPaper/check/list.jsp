<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>审核红包</title>
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
					<p>你当前的位置：[红包管理]-[审核红包]</p>
				</div>
				
				<%-- <div class="header-right">
					<div class="header-right-cell" style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else mulDelete();">
						<img src="<%=basePath%>img/new-3.png" />
						<p>删除</p>
					</div>
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="createMessage();" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div> --%>
				
				<hr align="center" />
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="4%"  style="height:50px;">序号</th>
						<th width="6%">红包名称</th>
						<th width="6%">总金额</th>
						<th width="6%">发放总量</th>
						<th width="6%">领取数</th>
						<th width="9%">每天最多发放量</th>
						<th width="6%">最小面值</th>
						<th width="6%">最大面值</th>
						<th width="6%">每人限领取</th>
						<th width="6%">开始时间</th>
						<th width="6%">领取周期</th>
						<th width="7%">红包描述</th>
						<th width="7%">红包头像</th>
						<th width="7%">红包链接</th>
						
						
						<th width="11%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${redPaperSettingList }" var="each">
							<tr>
								<td><input type="checkbox" name="code_Value" value="${each.id }" /></td>
								<td>${each.id }</td>
								<td>${each.name }</td>
								<td>${each.allMoney }</td>
								<td>${each.allNum }</td>
								<td>${each.allNumColl }</td>
								<td>${each.maxPutout }</td>
								<td>${each.minParvalue }</td>
								<td>${each.maxParvalue }</td>
								<td>${each.limits }</td>
								<td>${each.beginTime }</td>
								<td>${each.cyc }</td>
								<td>${each.description }</td>
								<td><img src="${RESOURCE_LOCAL_URL}${each.headimg }" style="width: 50px;height: 50px;"/></td>
								<td><a href="${each.link }">查看链接</a></td>
								
								
								<td>
						            <span onclick="requestCheck('${each.id}',1);" style="cursor: pointer;">
						           		 <img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16"  />审核通过
						           	</span>
									<br />
						            <span style="cursor: pointer;" onclick="requestCheck('${each.id}',2);">
						            	<img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />审核不通过
						            </span>
					           
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

	//异步请求；(提交审核数据，并提示处理结果)
	function requestCheck(id,status){
		$.post('<%=basePath%>/newRedPaper/check/check', { 'id': id, 'status':status },
		          function(data){
		          	if(data==1){
		          		alert("已审核成功");
		          	}else if (data==2) {
		          		alert("已审核拒绝");
					}else{
						alert("审核失败");
		          	}
		          	location.reload();
		          });
	}

  function createMessage(){
	  
	  parent.addmessage();
  }

  function editMessage(id){
    parent.editmessage(id);
  }

  function deleteMessage(id){
	  
	  parent.delmessage(id);
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

