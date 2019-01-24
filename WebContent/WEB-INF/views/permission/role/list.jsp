<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
	<form action="<%=basePath%>/role/list" method="post">
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[权限管理]-[角色列表]</p>
				</div>
				<hr align="center" />
			</div>
			
			<div class="div_search">
				     权限名称: <input type="text" class="input" value="${permissionName}" name="permissionName" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索" >
			    </form>
			</div>
				<div class="header-right" style="margin-top: -60px;margin-left: 100px">
					<div class="header-right-cell" onclick="location.href='edit'" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
			
		
			<!--end of header-->
			<div class="pageColumn">
				
			<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value="" onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="5%" style="height:50px;">名称</th>
						<th width="10%">权限明细</th>
						<th width="10%">菜单明细</th>
						<th width="5%">操作</th>
					</thead>
					<tbody>
						<c:forEach items="${pageResult.result }" var="each">
							<tr>
								<td><input type="checkbox" name="code_Value" value="${each.id }" /></td>
								<td>${each.name }</td>
								<td>${each.permissionDetails }</td>
								<td>${each.itemDetails }</td>								
								 <td>
                    <a href="#" onclick="editRole('${each.id }');">编辑</a>
                    <a href="#" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else delRole('${each.id }');">删除</a>
                    <script type="">
                        function delRole(deleteId) {
                            $.post("<%=basePath%>role/delete", {id: deleteId}, function (data) {
                                alert(data.message);
                                if (data.success) {
                                    location.reload();
                                }
                            });
                        }
                        function editRole(editId) {
                            location.href = "<%=basePath%>role/edit?id=" + editId;
                        }
                    </script>
    						</tr>
						</c:forEach>
         			
						
					</tbody>
				</table></div>
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

	function addLable(){
		window.location.href="addLable";
	}
	function del(id){
		window.location.href="labledel?lableId="+id;
	}
	function editLable(id){
		
		window.location.href="addLable?lableId="+id;
	}
	

  
  function deleteExchangescore(id){
	  
	  parent.delexchangescore(id);
  }

  function editChanger(id){
	  parent.editchanger(id);
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


