<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>红包统计列表</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
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
					<p>[红包管理]-[红包概况统计]</p>
				</div>
				<div class="header-right">
				</div>
				<hr align="center" />
			</div>
			
			<div class="div_search">	
			  <%--  <form action="${basePath }newRedPaperCountList" id="searchForm" method="post">
					<label>商家：</label>
					<span class="input" >
					运营：
					<select id="centerId" name="centerId" onchange="changeCity()">
					<option value="">--请选择--</option>
						<c:forEach items="${requestScope.centerList }" var="center">
							<option value="${center.id }">${center.loginname}</option>
						</c:forEach>
					</select>

					城市：
					<select  name="cityId" id="cityId"  onchange="changeAlliance()">
 						<c:if test="${cityKey==null }"><option value="">--请选择--</option></c:if>
						<c:if test="${cityKey!=null}"><option value="${cityKey}">${cityValue}</option></c:if>	
					</select>&nbsp;&nbsp;
					商圈：
					<select  name="allianceId" id="allianceId" onchange="changeSeller()">
						<c:if test="${allianceKey==null }"><option value="">--请选择--</option></c:if>
						<c:if test="${allianceKey!=null}"><option value="${allianceKey}">${allianceValue}</option></c:if>
					</select>
					店铺：
					<select  name="sellerId" id="sellerId" >
						<c:if test="${sellerKey==null }"><option value="">--请选择--</c:if>
						<c:if test="${sellerKey!=null}"><option value="${sellerKey}">${sellerValue}</option></c:if>
					</select>
					</span>
					<span style="float:right;margin-right:30px;">
					商家Id:<input type="text" class="input" name="search_id" style="width: 50px" value="${search_id }"/>
				 	商家名称:<input type="text" class="input" name="search_name" value="${search_name}"/>
				    <input type="submit" class="button" value="搜索" />
					</span>
			    </form> --%>
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="10%"  style="height:50px;">时段</th>
						<th width="10%">总红包量</th>
						<th width="10%">已领取红包</th>
						<th width="10%">未领取红包</th>
						<th width="10%">总金额</th>
						<th width="10%">已领取金额</th>
						<th width="10%">未领取金额</th>
						<th width="10%">红包消费金额</th>
						<th width="10%">新增红包数</th>
						<th width="10%">新增红包金额</th>
					</thead>
					<tbody>
					<c:forEach items="${requestScope.list }" var="list">
						<tr>
							<td>${list.name}</td>
							<td>${list.totalNum} </td>
							<td>${list.alreadyReceiveNum}</td>
							<td>${list.notReceiveNum}</td>
							<td><fmt:formatNumber value="${list.totalMoney }" type="currency"></fmt:formatNumber></td>
							<td><fmt:formatNumber value="${list.alreadyReceiveMoney }" type="currency"></fmt:formatNumber></td>
							<td><fmt:formatNumber value="${list.notReceiveMoney }" type="currency"></fmt:formatNumber></td>
							<td><fmt:formatNumber value="${list.userMoney }" type="currency"></fmt:formatNumber></td>
							<td>${list.addNum}</td>
							<td><fmt:formatNumber value="${list.addMoney }" type="currency"></fmt:formatNumber></td>
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
	
	
	function changeCity(){		 	
	 var xmlHttp=GetXmlHttpObject(); 
	  if (xmlHttp==null)
	    {
	    alert ("您的浏览器不支持AJAX！");
	    return;
	    }
	  
	  var centerId = document.getElementById("centerId").value;
	  var cityId = document.getElementById("cityId");
	  var allianceId = document.getElementById("allianceId");
	  var sellerId = document.getElementById("sellerId");
	  cityId.options.length=0;
	  allianceId.options.length=0;
	  sellerId.options.length=0;
	 
	  if(centerId!=""){
		  
	  var url='<%=basePath %>newRedPaper/statistics/findCity?centerId='+centerId;
	  xmlHttp.onreadystatechange=function () 
	  { 		    	  
		if (xmlHttp.readyState==4)
		{ 
			  var tt= xmlHttp.responseText;
			  var json=eval( '('+tt+ ')' );
			    	    
			 cityId.options.add(new Option("--请选择--", "-1"));
			  sellerId.options.add(new Option("--请选择--", "-1"));
			  allianceId.options.add(new Option("--请选择--", "-1")); 
			  for(var i in json){	
				  cityId.options.add(new Option(json[i],i));
			   }	
		}		    	  		    	     
		};		    	   
		xmlHttp.open("GET",url,true);
		xmlHttp.send(null);	  
	  }else{
		  alert("aaaa");
		  cityId.options.add(new Option("--请选择--", "-1"));
		  sellerId.options.add(new Option("--请选择--", "-1"));
		  allianceId.options.add(new Option("--请选择--", "-1"));
	  }
	}
	
 	function changeAlliance(){
		var xmlHttp=GetXmlHttpObject(); 
		  if (xmlHttp==null)
		    {
		    alert ("您的浏览器不支持AJAX！");
		    return;
		    }
		  var cityId = document.getElementById("cityId").value;
		  var allianceId = document.getElementById("allianceId");
		  var sellerId = document.getElementById("sellerId");
		  allianceId.options.length=0;
		  sellerId.options.length=0;
		  if(cityId!=-1){
		  var url='<%=basePath %>newRedPaper/statistics/findAlliance?cityId='+cityId;
		  xmlHttp.onreadystatechange=function () 
		  { 		    	  
			if (xmlHttp.readyState==4)
			{ 
				  var tt= xmlHttp.responseText;
				  var json=eval( '('+tt+ ')' );
				    	    
				  allianceId.options.add(new Option("--请选择--", "-1"));
				  sellerId.options.add(new Option("--请选择--", "-1"));
				  for(var i in json){	
					  allianceId.options.add(new Option(json[i],i));
				   }	
			}		    	  		    	     
			};		    	   
			xmlHttp.open("GET",url,true);
			xmlHttp.send(null);
		  }else{
			  sellerId.options.add(new Option("--请选择--", "-1"));
			  allianceId.options.add(new Option("--请选择--", "-1"));
		  }
		
	} 
	
	function changeSeller(){
		 var xmlHttp=GetXmlHttpObject(); 
		  if (xmlHttp==null)
		    {
		    alert ("您的浏览器不支持AJAX！");
		    return;
		    }
		  var allianceId = document.getElementById("allianceId").value;
		  var sellerId = document.getElementById("sellerId");
		  sellerId.options.length=0;
		  if(allianceId !=-1){
		  var url='<%=basePath %>newRedPaper/statistics/findSeller?allianceId='+allianceId;
		  xmlHttp.onreadystatechange=function () 
		  { 		    	  
			if (xmlHttp.readyState==4)
			{ 
				  var tt= xmlHttp.responseText;
				  var json=eval( '('+tt+ ')' );
				    	    
			 	  sellerId.options.add(new Option("--请选择--", "-1")); 
				  for(var i in json){	
					  sellerId.options.add(new Option(json[i],i));
				   }	
			}		    	  		    	     
			};		    	   
			xmlHttp.open("GET",url,true);
			xmlHttp.send(null);
		  }else{
			  sellerId.options.add(new Option("--请选择--", "-1")); 
		  }
	}
	
	
	
	
function GetXmlHttpObject()
{
	var xmlHttp=null;
	try
	  {
	  // Firefox, Opera 8.0+, Safari
	  xmlHttp=new XMLHttpRequest();
	  }
	catch (e)
	  {
	  // Internet Explorer
	 try
	    {
	    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	    }
	  catch (e)
	    {
	    xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	    }
	  }
	return xmlHttp;
}
	
	</script>
</body>
</html>

