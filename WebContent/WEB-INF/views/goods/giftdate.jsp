<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/css2.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/paging.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
</head>
<script>
//跳转到礼品数据详细页面，放在html代码加载之前
function giftDetails(id){
	  parent.giftdetail(id);
}
</script>
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
		<div class="main" id="div_main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[数据中心]-[礼品数据]</p>
				</div>
				<hr align="center" />
			</div>
			<!--end of header-->
			<div class="div_search" style="margin-top: -5px;">
			   <form action="${BASEPATH_IN_SESSION}goods/giftdate.action" method="post">
				    城市代理：
				    <wh:selectTag  name="username" optionValue = "${username }"  defaultOptionText="--请选择--" showProperty="username" where="level BETWEEN 70 AND 75 and (case when ${userLevel } >= 95 then 1=1 ELSE  parent_id = ${current_user_id } end)" tableName="admin_user" key="id"></wh:selectTag>
				    &nbsp;&nbsp;
				    礼品类型：
				    <wh:selectTag  name="shoptypes" optionValue = "${shoptypes }"  defaultOptionText="--请选择--" showProperty="name" tableName="shoptypes" key="id"></wh:selectTag>
				    &nbsp;&nbsp;
				     礼品名称: <input type=text class="input" value="${giftName }" name="giftName" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索" />
				    <span style="float:right; margin-right:30px;">总记录：${count} 条</span>
			    </form>
			</div>
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="9%" style="height:50px;">序号</th>
						<th width="10%">礼品名称</th>
						<th width="10%">城市代理</th>
						<th width="10%">礼品类型</th>
						<th width="10%">礼品价格</th>
						<th width="10%">图片</th>
						<th width="10%">剩余数量</th>
						<th width="9%">基本操作</th>
					</thead>
					<tbody>
					
					<s:iterator value="goodslist" var="gift" >
						<tr>
							<td><input type="checkbox" name="code_Value" value="${gift.id }" /></td>
							<td>${gift.id }</td>
							<td>${gift.name }</td>
							<td>${gift.adminUser.username }</td>
							<td>${gift.shoptypes.name }</td>
							<td>${gift.money }</td>
							<td><img src="${BASEPATH_IN_SESSION}.${gift.imgurls }" style="width:80px;height:80px;" /></td>
							<td>${gift.tcount }</td>
							<td><span onclick="giftDetails('${gift.id }');"  style="cursor: pointer;">
										<img src="${BASEPATH_IN_SESSION}constant/tab/images/edt.gif" width="16"
										height="16" />详细
									</span>&nbsp; &nbsp;</td>
						</tr>
						</s:iterator>
					
				
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
	  } }
</script>
</body>
</html>

