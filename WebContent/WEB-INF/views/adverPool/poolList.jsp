<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.axp.model.Levels"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	List<Levels> slst = (List<Levels>) request.getAttribute("slst");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<style>   
  BODY   {   
  FONT-FAMILY:   "宋体";   FONT-SIZE:   9pt;   SCROLLBAR-FACE-COLOR:   #E8E8E8;   SCROLLBAR-HIGHLIGHT-COLOR:   #D96405;   SCROLLBAR-SHADOW-COLOR:   #D96405;   SCROLLBAR-3DLIGHT-COLOR:   #eeeeee;   SCROLLBAR-ARROW-COLOR:   #D96405;   SCROLLBAR-TRACK-COLOR:   #eeeeee;   SCROLLBAR-DARKSHADOW-COLOR:   #eeeeee   
  }   
  </style> 
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
					<c:if test="${outtime=='no'}">
						<p>你当前的位置：[广告管理]-[广告池管理]</p>
					</c:if>
					<c:if test="${outtime!='no'}">
						<p>你当前的位置：[广告管理]-[已过期广告池]</p>
					</c:if>
				</div>
				
				<hr align="center" />
			</div>
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="3%"><input id="s" name="" type="checkbox" value=""
							onclick="$(this).attr('checked')?checkAll():uncheckAll()" /></th>
						<th width="10%" style="height:50px;">序号</th>
						<th width="10%">广告名</th>
						<th width="10%">广告图</th>
						<!-- <th width="10%">上级播放</th> -->
					
						<th width="10%">使用天数</th>
						<th width="10%">开始时间</th>
						<th width="10%">结束时间</th>
						<th width="10%">状态</th>
						<th width="10%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${poollist }" var="pool" >
						<tr>
							<td><input type="checkbox" name="code_Value" value="${pool.id }" /></td>
							<td>${pool.id }</td>
							<td>${pool.goods.name }</td>
							<td><img src="${RESOURCE_LOCAL_URL }${pool.goods.adverImgurls }" style="width:80px;height:80px;" /></td>
						<%-- 	<td>${pool.higelevel==true?'是':'否' }</td> --%>
						
							<td>${pool.playtotal }</td>
							<td><fmt:formatDate value="${pool.starttime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td><fmt:formatDate value="${pool.endtime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							
							<td>${outtime=="yes"?'已过期':pool.isplay==3?'播放完毕':pool.isplay==2?'播放中':'待播' }</td>
							<td>
								<div>
									<span onclick="playGoods('${pool.id }');" style="cursor: pointer;">
										<img src="<%=basePath%>constant/tab/images/edt.gif" width="16"
										height="16" />${outtime=="yes"?'重新播放':'播放设置' }
									</span><br>
									<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deletePool('${pool.id }');"><img
										src="<%=basePath%>constant/tab/images/del.gif" width="16"
										height="16" />删除</span>
								</div>
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

	
  function setHigeLievel(id){
				
				$.ajax({
					type:"post",
					url:"<%=basePath %>pool/changeHigeLievel.action",
					data: "poolId="+id,        
			        dataType: "text",
			        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 for(var i in json){	
		    	    		 if(json[i]==1){
		    	    			 window.location.href = "<%=basePath%>" + "pool/list.action";
		    	    		 }else if(json[i]==-2){
		    	    			 alert('你的最大跨级广告个数为'+<%=(Integer)request.getAttribute("highLevelLimit")%>+'，不能继续添加！'); 
		    	    			 return;
		    	    		 }else{
		    	    			 alert('设置失败，请重试！'); 
		    	    			 return;
		    	    		 }
		    	    	 }
			
			        }
				})
  }


  function playGoods(id){
	  window.location.href = "${BASEPATH_IN_SESSION}" + "adverPool/playAdver?id="+id;
	}

  function deletePool(id){
	  window.location.href = "${BASEPATH_IN_SESSION}" + "adverPool/delPool?id="+id;
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
  
<%--   function init_load(){
	  var frsize = <%=frsize%>;
	  var mheight = (parseInt(frsize,10)+8) * 50;
	  document.getElementById("div_main").style.height=mheight+"px"; 
	 
  }--%>
</script>
</body>
</html>

