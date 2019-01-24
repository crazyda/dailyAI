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

<title>PID分配</title>
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
	                	<p>你当前的位置：[淘客管理]-[PID管理]-[PID分配]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table>

     <td>
     <form action="<%=basePath %>taoke/pid/saveDistribute?id=${adminuserTaokePid.id}" id="saveForm" method="post">
     <input type="hidden" id="adminuserId" name="adminuserId" value=""/>
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>PID</label>
			<span class="input">
			<font color="red"> ${adminuserTaokePid.pid}</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>PID说明</label>
			<span class="input">
			<font color="red"> ${adminuserTaokePid.pidname}</font>
			</span>
			<div class="clear"></div>
		</div>

		 <div class="rows" >
			<label>分配人员</label>
			
			<span class="input">
				运营中心
				<select  name="center" id="centerId" onchange="changeDistribute();gotoselect()">
					<option value=0>请选择</option>
					<c:forEach items="${centerlist }" var="center">
						<option value="${center.id }" <c:if test="${center.id == adminUser.id }">selected="selected"</c:if>>${center.loginname}</option>
					</c:forEach>
				</select>
				<div id="c" >代理商
				<select  name="agency" id="agencyId" onchange="changeDistribute();gotoselect()">
					<option value=0>请选择</option>
			</select> 
			</div>
			</span>
			<div class="clear"></div>
		</div> 

		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button" onclick="savePtype();" value="分 配" />
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

 
function changeDistribute(){
	  var ad = event.srcElement;
	  var center = document.getElementById("centerId");
	  var agency = document.getElementById("agencyId");
			if(parseInt(ad.value)>0){
				if(ad==center){
					agency.options.length=0;				
				}
				
				$.ajax({
					type:"post",
					url:"${BASEPATH_IN_SESSION}taoke/pid/changeDistribute.action",
					data: "adminuserId="+ad.value,        
			        dataType: "text",
			        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 if(ad==center){
		    	    		 agency.options.add(new Option("请选择", ""));
		    	    	 }
		    	    	 for(var i in json){	
		    	    		 if(ad==center){
		    	    			 agency.options.add(new Option(json[i],i));
		    	    		 }
		    	    	 }
			
			        }
				})
			}
			
	}

	
function savePtype(){
	   var adminuserId = $("input#adminuserId");
	   var center =$("select#centerId").find("option:selected").text();
	   var agency =$("select#agencyId").find("option:selected").text();
	   if(agency && agency!='请选择'){
		   $("input#uri").val(agency);
		   adminuserId.val($("select#agencyId").val());
	   }
	   else if(center && center!='请选择'){
		   $("input#uri").val(center);
		   adminuserId.val($("select#centerId").val());
	   }
   document.getElementById("saveForm").submit();
}
</script>
</body>
</html>
