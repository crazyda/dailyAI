<%@page import="com.axp.util.StringUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Levels"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=FhqpjmNavgBUzcjqOlHfeqrR"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>

<title>新增淘客</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
-->
</style>
</head>

<body onload="setChecked();">
<div class="div_new_content">
  <div style="margin-left:10px;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
	                <div class="header-left">
	                	<p>你当前的位置：[业务管理]-[淘客管理]-[编辑淘客信息]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table>

     <td>
     <form action="<%=basePath %>professional/taoke/save?id=${zonetaoke.id}" id="saveForm" method="post">
    
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>PID</label>
			<span class="input">
			<input type=text name="bak_uri" id="bak_uri" value="${zonetaoke.bak_uri}"  style="width: 30%"/> <font color="red">*地区pid</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>PID地区</label>
			<span class="input">
			<font color="red"> ${zonetaoke.provinceEnum.provinceEnum.name }-${zonetaoke.provinceEnum.name } </font>
			</span>
			<div class="clear"></div>
		</div>
		 <div class="rows">
			<label>用户</label>
			<span class="input">
			<font color="red"> ${zonetaoke.adminUser.loginname}</font>
			</span>
			<div class="clear"></div>
		</div>
		 
		 <div class="rows" >
			<label>用户</label>
			
			<span class="input">
				运营中心
				<select  name="center" id="centerId" onchange="changeDistribute();gotoselect()">
					<option value=0>请选择</option>
					<c:forEach items="${centerlist }" var="center">
						<option value="${center.id }" <c:if test="${center.id == adminUser.id }">selected="selected"</c:if>>${center.loginname}</option>
					</c:forEach>
				</select>
				城市代理
				<select  name="agency" id="agencyId" onchange="changeDistribute();gotoselect()">
					<option value=0>请选择</option>
			</select> 
			
			商圈
			<select  name="circles" id="circlesId" onchange="gotoselect()" >
					<option value=0>请选择</option>
			</select>
			</span>
			<div class="clear"></div>
		</div> 


		 <div class="rows" >
			<label>淘客地址</label>
			
			<span class="input">
				省
				<select  name="province" id="provinceId" onchange="changeZone();gotoselect()">
					<option value=0>请选择</option>
					<c:forEach items="${zoneList }" var="zone">
						<option value="${zone.id }" <c:if test="${zone.id == adminUser.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
					</c:forEach>
				</select>
				市
				<select  name="city" id="cityId" onchange="changeZone();gotoselect()">
					<option value=0>请选择</option>
			</select> 
			
			区县
			<select  name="county" id="countyId" onchange="gotoselect()" >
					<option value=0>请选择</option>
			</select>
			<font color="red">*如果是城市（例如：广州市）则不选择区县！</font>
			</span>
			<div class="clear"></div>
		</div> 
		
 

		<div class="rows">
			<label></label>
			<span class="input">
			<input type=hidden name="zoneid" id="zoneid" >
			<input type=hidden name="centerid" id="centerid" >
			  <input type=button class="button" onclick="savePtype();" value="添 加" />
			</span>
			<div class="clear"></div>
		</div>
      </div>
     </div>
     </form> 
     </td>

</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script type="text/javascript" src="<%=basePath %>js/coordtransform.js"></script>
<script type="text/javascript" src="<%=basePath %>js/maputil.js"></script>
<script>

 
function changeZone(){
	  var ad = event.srcElement;
	  var province = document.getElementById("provinceId");
	  var city = document.getElementById("cityId");
	  var county = document.getElementById("countyId");
			if(parseInt(ad.value)>0){
				if(ad==province){
					city.options.length=0;				
				}
				county.options.length=0;
				
				$.ajax({
					type:"post",
					url:"<%=basePath %>professional/user/changeZone",
					data: "zonesid="+ad.value,        
			        dataType: "text",
			        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 if(ad==province){
		    	    		 city.options.add(new Option("请选择", ""));
		    	    	 }
		    	    	 county.options.add(new Option("请选择", ""));
		    	    	 for(var i in json){	
		    	    		 if(ad==province){
		    	    			 city.options.add(new Option(json[i],i));
		    	    		 }else{
		    	    		 	county.options.add(new Option(json[i],i));
		    	    		 }
		    	    	 }
			
			        }
				})
			}
			
	}
	
function changeDistribute(){
	  var ad = event.srcElement;
	  var center = document.getElementById("centerId");
	  var agency = document.getElementById("agencyId");
	  var circles = document.getElementById("circlesId");
			if(parseInt(ad.value)>0){
				if(ad==center){
					agency.options.length=0;				
				}
				circles.options.length=0;
				$.ajax({
					type:"post",
					url:"${BASEPATH_IN_SESSION}professional/taoke/changeDistribute.action",
					data: "adminuserId="+ad.value,        
			        dataType: "text",
			        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 if(ad==center){
		    	    		 agency.options.add(new Option("请选择", ""));
		    	    	 }
		    	    	 circles.options.add(new Option("请选择",""));
		    	    	 for(var i in json){	
		    	    		 if(ad==center){
		    	    			 agency.options.add(new Option(json[i],i));
		    	    		 }else{
		    	    			 circles.options.add(new Option(json[i],i));
		    	    		 }
		    	    	 }
			
			        }
				})
			}
			
	}
function changeP(){
	  var ad = event.srcElement;
	  var province = document.getElementById("proId");
	  var city = document.getElementById("ciId");
	  var county = document.getElementById("advertwoId");
			if(parseInt(ad.value)>0){
				if(ad==province){
					city.options.length=0;				
				}
				county.options.length=0;
				
				$.ajax({
					type:"post",
					url:"<%=basePath %>professional/user/changeZone",
					data: "zonesid="+ad.value,        
			        dataType: "text",
			        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 if(ad==province){
		    	    		 city.options.add(new Option("请选择", ""));
		    	    	 }
		    	    	 county.options.add(new Option("请选择", ""));
		    	    	 for(var i in json){	
		    	    		 if(ad==province){
		    	    			 city.options.add(new Option(json[i],i));
		    	    		 }else{
		    	    		 	county.options.add(new Option(json[i],i));
		    	    		 }
		    	    	 }
			
			        }
				})
			}
			
	}

function getadvertwo(){
	  var ad = event.srcElement;
	  var city = document.getElementById("ciId");
	  var county = document.getElementById("advertwoId");
			if(parseInt(ad.value)>0){
				county.options.length=0;
				
				$.ajax({
					type:"post",
					url:"<%=basePath %>professional/user/getadvertwo",
					data: "zonesid="+ad.value,        
			        dataType: "text",
			        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 county.options.add(new Option("请选择", ""));
		    	    	 for(var i in json){	

		    	    		 county.options.add(new Option(json[i],i));

		    	    	 }
			
			        }
				})
			}
			
	}
	
function savePtype(){
	   var zoneid = $("input#zoneid");
	   var centerid = $("input#centerid");

	   var province =$("select#provinceId").find("option:selected").text();
	   var city =$("select#cityId").find("option:selected").text();
	   var county = $("select#countyId").find("option:selected").text();
	   
	   var center = $("select#centerId").find("option:selected").text();
	   var agency = $("select#agencyId").find("option:selected").text();
	   var circles = $("select#circlesId").find("option:selected").text();
	   
	   if(county&&county!='请选择'){
		  
		   zoneid.val($("select#countyId").val());
	   }else if(city && city!='请选择'){
		  
		   zoneid.val($("select#cityId").val());
	   }
	   else if(province && province!='请选择'){
		   
		   zoneid.val($("select#provinceId").val());
	   }
	   
	    if(circles&&circles!='请选择'){
	    	centerid.val($("select#circlesId").val());
	   }else if(agency&&agency!='请选择'){
		   centerid.val($("select#agencyId").val());
	   }else if(center&& center!='请选择'){
		   centerid.val($("select#centerId").val());
	   }
   document.getElementById("saveForm").submit();
}
</script>
</body>
</html>
