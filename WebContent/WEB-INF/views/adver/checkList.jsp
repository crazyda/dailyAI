<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.axp.model.Goods"%>
<%@page import="com.axp.model.AdminUser"%>
<%@page import="com.axp.model.Users"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<Goods> slst = (List<Goods>)request.getAttribute("slst");
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
		<div class="main" id="div_main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[广告管理]-[广告审核管理]</p>
				</div>
				<hr align="center" />
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="9%" style="height:50px;">序号</th>
						<th width="10%">名称</th>
						<th width="9%">投放用户</th>
						<th width="9%">图片</th>
						<th width="9%">审核状态</th>
						<th width="9%">基本操作</th>
					</thead>
					<tbody>
				<% 
				   int frsize = slst.size();
		           for(Goods sc: slst){
		        	   int id = sc.getId();
		        	   if(sc==null){
		        		   continue;
		        	   }
		        	   String name = sc.getName();
		        	   String adver_imgurl = sc.getAdverImgurls();
		        	   String adver_imgurl_small = sc.getAdverImgurlsSmall();
		        	   int adver_status = sc.getAdverStatus();
		        	   String adver_status_str="未审核";
		        	   if(adver_status==1){
		        		   adver_status_str="通过";
		        	   }else if(adver_status ==2){
		        		   adver_status_str="轮播中";
		        	   }
		        	   
		        	   AdminUser user= sc.getAdminUser();
		        	   String uname = user.getLoginname();
		        	  
		        	   
		          %>
						<tr>
							<td><input type="checkbox" name="code_Value" value="<%=id%>" /></td>
							<td><%=id%></td>
							<td><%=name%></td>
							<td><%=uname%></td>
							
							<td><img src="${RESOURCE_LOCAL_URL}<%=adver_imgurl%>" style="width:80px;height:80px;" /></td>
							<td><%=adver_status_str%></td>
							<td><span onclick="add_checkimgs_adver(<%=id%>);" style="cursor: pointer;">
										<img src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />审核
									</span>&nbsp; &nbsp;</td>
						</tr>
						<%
							}
						%>
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


  function add_checkimgs_adver(id){
	  window.location.href = "${BASEPATH_IN_SESSION}adver/checkAdver?id="+id;
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

