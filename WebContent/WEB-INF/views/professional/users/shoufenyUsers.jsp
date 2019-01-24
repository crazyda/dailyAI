<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>收分用户列表</title>
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
					<p>你当前的位置：[业务管理]-[收分用户管理]</p>
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
				      用户名: <input type=text class="input" value="${search_name} " name="search_name" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索">	
			    </form>
			</div>
			<!--end of header-->
			<div class="pageColumn" id="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%" style="height:50px;">序号</th>
						<th width="10%">登录名</th>
						<th width="8%">手机号</th>
						<th width="10%">分佣下限</th>
						<th width="10%">创建时间</th>
						<th width="15%">基本操作</th>
					</thead>
					<tbody>
					<c:forEach items="${asList}" var="user">
					<tr>
            		<td>${user.id }</td>
           	 		<td>${user.loginname }</td> 
           	 		<td>${user.phone }</td> 
					<td>${user.totalMoney}</td>
					 <td>
                        <fmt:formatDate value="${user.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
					<td height="20" >
						<div align="center">
								<span class="STYLE4"> 
								<span onclick="editUser('${user.id }');" style="cursor: pointer;"> <img
										src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />编辑
								</span>&nbsp;
								<span id="delDiv" style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deleteAdminuser('${user.id }');"><img
											src="<%=basePath%>constant/tab/images/del.gif" width="16"
											height="16" />删除
							   	</span>
						</div>
					</td>
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
	</form>

	
<script>
  function createUser(){
	  window.location.href="addUser?id=";
  }
  
  
  function editUser(id){
	  window.location.href="<%=basePath%>professional/user/addUser?id="+id;
  }

  function deleteAdminuser(id){
	  $.ajax({
	        url: "<%=basePath%>professional/user/delUser",
	        type: "post",
	        dataType:"json",
	        data: {"id": id},
	        success: function (data) {
	            alert(data.msg);
	            location.reload();
	        }
	    });
	 
  }
  
  


</script>
</body>
</html>

