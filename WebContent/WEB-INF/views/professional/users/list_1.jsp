<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<div class="main" id="div_main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[业务管理]-[用户管理]</p>
				</div>
				<div class="header-right">
					
					<div class="header-right-cell" onclick="createUser();" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="<%=basePath%>user/list" method="post">
				      登录名: <input type=text class="input" value="${search_name} " name="search_name" />&nbsp;&nbsp;
			
				用户级别:
			
			   <select name="levelname" id="levelname">
			   <option value="">请选择</option>
			   <c:forEach  items="${leveltable }" var="levelset" >
			  		 <option  value="${levelset.key }" <c:if test="${ levelset.key ==levelid}">selected='selected'</c:if> >${levelset.value }</option>
			   </c:forEach>
			</select>  
			
			
				    <input type=submit class="button" value="搜索">	
				    
				    <c:if test="${aus.level==75 or aus.level==95}">
				    	积分池总积分:${aus.score } ,奖金池:${maid }
				    </c:if>
				    <c:if test="${aus.level==65 }">
				    	<input type="hidden" name="user_num" value="1"/>
				    	
				            <input type="button" onclick="javascript:window.location.href='list?backState=10';" value="用户数" class="button"/>
				            <input  value="商家数" class="button" type="button"/>
				        
				    	积分池总积分:${aus.parentAdminUser.score } ,奖金池:${maid }
				    </c:if>
			    </form>
			</div>
			<!--end of header-->
			<div class="pageColumn" id="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="5%" style="height:50px;">序号</th>
						<c:choose>
						     <c:when test="${aus.level==75}">
						     
							 <th width="10%">真实名</th>
							 <th width="8%">等级</th>
						     <th width="8%">联盟占比</th>
						     <th width="10%">邀请码</th>
							<th width="15%">基本操作</th>
						     </c:when>
						     <c:otherwise>
							     <th width="10%">登录名</th>
								<th width="10%">真实名</th>
								<th width="8%">等级</th>
						         <th width="8%">总积分</th>
								<th width="8%">剩余积分</th>
								<th width="8%">可用积分</th>
								<th width="10%">邀请码</th>
							<th width="15%">基本操作</th>
						     </c:otherwise>
						</c:choose>
					</thead>
					<tbody>
					<c:forEach items="${asList}" var="user">
					<tr>
					<td><input type="checkbox" name="code_Value" value="" /></td>
            		<td>${user.id }</td>
            		<c:choose>
						     <c:when test="${aus.level==75}">
						     	<td>${user.username }</td>
            					<td>${user.levelname }</td>
						          <td>${user.lmUnion }</td>
						     </c:when>
						     <c:otherwise>
						     	<td>${user.loginname }</td>
            					<td>${user.username }</td>
            					<td>${user.levelname }</td>
						        <td>${user.scoreMax }</td>
            					<td>${(user.scoreMax-user.scoreSurplus<0)?0:(user.scoreMax-user.scoreSurplus) }</td>
            					<td>${user.score }</td>
						     </c:otherwise>
						</c:choose>
					<td>${user.invitecode}</td>
					<td height="20" >
					<div align="center">
						<span class="STYLE4"> 
								<span onclick="editUser('${user.id }');" style="cursor: pointer;"> <img
										src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />编辑
								</span>&nbsp;
								<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要重置密码吗?重置后密码将恢复为默认值888888!'))return false; else resetPassword('${user.id }');"><img
										src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />重置密码</span>
								</span>
								&nbsp;
								<span id="delDiv" style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deleteAdminuser('${user.id }');"><img
											src="<%=basePath%>constant/tab/images/del.gif" width="16"
											height="16" />删除
							   	</span>
						</div></td>
						</tr>
					</c:forEach>
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
/*
	$(function(){
		$('#delSubDiv').click(function(){
			alert('您暂无删除联盟组的权限，请联系每天积分总部！');
			return;
		});
		$('#delDiv').click(function(){
			alert('您暂无删除联盟组的权限，请联系每天积分总部！');
			return;
		});
	});*/
	function resetPassword(id){
		window.location.href="resettingpwd?id="+id;
	}
  function createUser(){
	  window.location.href="add";
  }
  
  function createChildUser(id){
	  parent.addAdminChilduser(id);
  }
  
  function addScore(id){
	  
	  parent.addusercore(id);
  }
/*
  function editUser(id,isChild){
	if(isChild==null){
		parent.editadminuser(id);
	}else{
		parent.addAdminChilduser(id);
	}
    
  }*/
  function editUser(id){
	  window.location.href="add?id="+id;
  }
  
  function changelevel(id){
    parent.changelevel(id);
  }
  
  function ExtendResult(id){
	  
	  parent.extendresult(id);
  }
  
  function resetpwd(id){
	  
	  parent.resetpwd(id);
  }

  function deleteAdminuser(id){
	  
	  window.location.href="delete?id="+id;
  }
  
  

  function edit_usersetting(id){
     parent.edit_usersetting(id);
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

