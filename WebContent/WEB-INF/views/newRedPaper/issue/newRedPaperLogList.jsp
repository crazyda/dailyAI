<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
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

function dosummit(num){
		$("#num").val(num);
		document.getElementById("id").value=${id};
		document.forms[0].action ="${basePath}newRedPaper/issue/newRedPaperLogList";
		document.forms[0].submit();
}
</script>
<body>
	<form>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>[红包管理]-[红包概况统计]</p>
				</div>
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			
			<div class="div_search">
			<p style="margin:0;padding:0;margin-left:50px;color:#2d84b9;font-size:18px;font-family:Microsoft Yahei;position:absolute">
			&nbsp;&nbsp;(红包名称:${nrps.name }) 已领取:${nrps.allMoneyUsed }元/${nrps.allNumColl }个
			</p>
			   <form action="" method="post" id="searchForm">
			   		<input type="hidden" id="name" name="name"  />
			   		<input type="hidden" id="id" name="id"/>
				    <input type="button" id="quanbu" value="全部" class="button" onclick="dosummit(0)" style="float:right;margin-right:60px;"/>
				    <input type="button" id="fenshi" value="粉丝"class="button" onclick="dosummit(1)" style="float:right;margin-right:30px;"/>
			    </form>
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="10%"  style="height:50px;">ID</th>
						<th width="20%">领取时间</th>
						<th width="10%">商圈</th>
						<th width="10%">用户</th>
						<th width="10%">领取面值</th>
						<th width="10%">状态</th>
						<th width="10%">店铺粉丝</th>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.nrplList }" var="data">
						<tr>
							<td>${data.id }</td>
							<td>${data.createTime }</td>
							<td>${data.users.adminUser.username }</td>	
							<td>${data.users.name }</td>					
							<td>${data.money }元</td>
							<td>
								<c:if test="${money == avail}">
									未使用
								</c:if>
								<c:if test="${money != avail}">
									已使用
								</c:if>
							</td> 
							<td>
								<c:if test="${requestScope.nrps.seller.id == users.parentSeller.id}">
									是
								</c:if>
								<c:if test="${requestScope.nrps.seller.id != users.parentSeller.id}">
									否		
								</c:if>
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
</body>
</html>

