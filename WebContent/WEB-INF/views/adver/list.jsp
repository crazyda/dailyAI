<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/css2.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/main.js"></script>

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
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[广告管理]-[广告素材管理]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell" style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else mulDelete();">
						<img src="${BASEPATH_IN_SESSION}img/new-3.png" />
						<p>删除</p>
					</div>
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="createAdver();" style="cursor: pointer;">
						<img src="${BASEPATH_IN_SESSION}img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="${BASEPATH_IN_SESSION}adver/list.action" method="post">
				    <input type ="hidden" name="type" value="2"/>
				             广告 名称: <input type=text class="input" value="${sreach_name}" name="search_name" />&nbsp;&nbsp;
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
						<th width="15%">广告名称</th>
						<th width="15%">广告用户</th>
						
						<th width="15%">广告图片</th>
						
						<th width="10%">状态</th>
						<th width="15%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${goodslist }" var="gift" >
						<tr>
							<td><input type="checkbox" name="code_Value" value="${gift.id }" style="height:80px;" /></td>
							<td>${gift.id }</td>
							<td>${gift.name }</td>
							<td>${gift.adminUser.username }</td>
							
							<td>
							<c:if test="${gift.adverImgurls!='' }">
								<img src="${RESOURCE_LOCAL_URL}${gift.adverImgurlsSmall }" style="width:80px;height:80px;" />
							</c:if>
							</td>
							<td><c:if test="${gift.adverStatus ==-1 }" >审核不通过</c:if>
								<c:if test="${gift.adverStatus ==1 }" >审核通过</c:if>
								<c:if test="${gift.adverStatus ==0 }" >未审核</c:if>
								<c:if test="${gift.adverStatus ==2 }" >已投放</c:if>
							</td>
							<td>
								<div>
								<c:if test="${gift.adverStatus !=2 }" >
									<span onclick="editGoods('${gift.id }');" style="cursor: pointer;">
											<img src="${BASEPATH_IN_SESSION}constant/tab/images/edt.gif" width="16"
											height="16" />编辑
										</span><br/>
										<span onclick="adver_imgs('${gift.id }');"
											 style="cursor: pointer;"> <img
											src="${BASEPATH_IN_SESSION}constant/tab/images/edt.gif" width="16"
											height="16" />广告图片
										</span><br/>
									<c:if test="${gift.adverStatus ==1 }" >
										<span onclick="playGoods('${gift.id }');" style="cursor: pointer;">
											<img src="${BASEPATH_IN_SESSION}constant/tab/images/edt.gif" width="16"
											height="16" />播放
										</span><br/>
									</c:if>
								</c:if>
								</div>
								<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deleteGoods('${gift.id}');"><img
										src="${BASEPATH_IN_SESSION}constant/tab/images/del.gif" width="16"
										height="16" />删除</span>
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
<script>

function playGoods(id){
	window.location.href = "${BASEPATH_IN_SESSION}adverPool/playAdver?adver_id="+id;
}

function createAdver(){
	window.location.href = "${BASEPATH_IN_SESSION}adver/add";
}

  function editGoods(id){
	  window.location.href = "${BASEPATH_IN_SESSION}adver/add?id="+id;
  }

  function deleteGoods(ids){
	  window.location.href = "${BASEPATH_IN_SESSION}adver/del?ids="+ids;
  }

  function adver_imgs(id){
	  window.location.href = "${BASEPATH_IN_SESSION}adver/addAdverImg?id="+id;
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
		  deleteGoods(ids);
	  } 
  } 
</script>
</body>
</html>

