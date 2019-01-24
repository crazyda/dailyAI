<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.axp.model.Slides"%>
<%@page import="com.axp.util.StringUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Slides> slst = (List<Slides>)request.getAttribute("slst");
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
					<p>你当前的位置：[商城管理]-[商城广告]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell" style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else mulDelete();">
						<img src="<%=basePath%>img/new-3.png" />
						<p>删除</p>
					</div>
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="createSlide();" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="9%"  style="height:50px;">序号</th>
						<th width="10%">商家</th>
						<th width="10%">类型</th>
						<th width="10%">图片</th>
						<th width="20%">基本操作</th>
					</thead>
					<tbody>
						<% 
           for(Slides sc: slst){
        	   if(sc==null){
        		   continue;
        	   }
        	   String imgurls = request.getServletContext().getAttribute("RESOURCE_LOCAL_URL")+sc.getImgurls();
        	 
        	   int  id = sc.getId();
        	   String linkurl=sc.getLinkurl()==null?"":sc.getLinkurl();
        	   String name = sc.getAdminUser().getUsername();
        	   int types = sc.getType()==null?5:sc.getType();
        	   String type = StringUtil.getType().get(types);
        	   
          %>
						<tr>
							<td><input type="checkbox" name="code_Value" value="<%=id%>" /></td>
							<td><%=id%></td>
							<td><%=name%></td>
							<td><%=StringUtil.getNullValue(type)%></td>
							<td><a href="<%=linkurl%>" target="_brank" ><img src="<%=imgurls %>" style="width:80px;height:80px;" /></a></td>
							
							<td><span onclick="editSlide(<%=id %>);" style="cursor: pointer;">
            <img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16"  />编辑</span>&nbsp; &nbsp;
            <span style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deleteSlide(<%=id %>);"><img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />删除</span></td>
						</tr>
						<%
							}
						%>
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
	<input type="hidden" id = "count" value="${count }"/>
	<input type="hidden" id = "auLevel" value="${au.level }"/>
	</form>
	
	
	
<script>

  function createSlide(){
	  var level = $("#auLevel").val();
	  var count = $("#count").val();
	  if(level<95&&count>4){
		  alert("代理只允许上传5张广告图");
	  }else{
	  	window.location.href='<%=basePath%>cashShop/slide/add'; 
	  }
  }

  function editSlide(id){
	  window.location.href='<%=basePath%>cashShop/slide/add?id='+id; 
  }

  function deleteSlide(id){
	  
	  window.location.href='<%=basePath%>cashShop/slide/del?id='+id; 
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
		  window.location.href='<%=basePath%>cashShop/slide/dels?ids='+ids; 
	  } 
  } 
</script>
</body>
</html>

