<%@page import="com.axp.model.MessageType"%>
<%@page import="com.axp.model.SeLive"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Levels"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object o = request.getAttribute("sc");

MessageType mt = null;
String logo=null;
String icon = null;
String title = "";
if(o!=null){
   
	mt = (MessageType)o;
	icon = mt.getIcon();
    logo=mt.getIcon();
   	title = mt.getTitle();
}

%>
<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=basePath %>js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/coordtransform.js"></script>
<script type="text/javascript" src="<%=basePath %>js/maputil.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/layer3/layer.js"></script>

<link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>发送消息</title>
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

<body >
<div class="div_new_content">
  <div style="margin-left:10px;">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
	                <div class="header-left">
						<p>你当前的位置：[系统管理]-[消息中心]-[发送消息]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table></td>
  </tr>
  <tr>
     <td>
     <form action="<%=basePath %>system/message/messagesave?id=${ml.id}" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      
      			<input type="hidden" id="zid"/>
      			
      		    <div class="rows">
				<label>消息类别：</label>
				<span class="input">
			 	  <select  id="typeId" onchange="changeType();gotoselect()">
				 	<option value="-1" >请选择类别</option>
					  		<c:forEach items="${mlist }" var="message">
		                  		<c:if test="${empty message.messageType }">
		                  			<option value="${message.id }">${message.title}</option>
		                  		</c:if>
					  		</c:forEach>
	               </select>&nbsp;&nbsp;----&nbsp;&nbsp;
	               <select name="typeId" id="typeId2" onchange="gotoselect()">
	               <option value="-1" >请选择子类别</option>
	               </select> 
	              <!--  <select  id="typeId" name="typeId">
	                <option value="-1" >请选择类别</option>
				 	<option value="1" >系统消息</option>
				 	<option value="14" >活动消息</option>
	               </select>&nbsp;&nbsp;----&nbsp;&nbsp;
	               
	               	<select name="typeId" id="typeId2">
	               	   <option value="-1" >请选择类别</option>
	               	   <option value="4" >平台咨询</option>
	              	   <option value="13">商家系统消息</option>
	              	   <option value="15">活动消息</option>
	               </select>
	                -->
	                
				</span>
				<div class="clear"></div>
	        	</div>
	        	 <div class="rows" id="zone" style="display: none">
				<label>消息发送地区：</label>
				<span class="input">
				<c:if test="${level>=95}">
					 <select  name="province" id="provinceId" onchange="changeZone();gotoselect()">
				 	<option value="-1" >省</option>
					  	<c:forEach items="${zoneList }" var="zone">
							<option value="${zone.id }" <c:if test="${zone.id == adminUser.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
						</c:forEach>
	               </select>&nbsp;&nbsp;----&nbsp;&nbsp;
	               <select name="city" id="cityId" onchange="changeZone();gotoselect()">
	               <option value="-1" >市</option>
	               </select>&nbsp;&nbsp;----&nbsp;&nbsp;
	               <select name="county" id="countyId" onchange="gotoselect()">
	               <option value="-1" >区</option>
	               </select>
				</c:if>
				 <c:if test="${level==85 }">
				 	<select name="city" id="cityId" onchange="changeZone();gotoselect()">
	               <option value="-1" >市</option>
	               		<c:forEach items="${zoneList }" var="zone">
							<option value="${zone.id }" <c:if test="${zone.id == adminUser.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
						</c:forEach>
	               </select>&nbsp;&nbsp;----&nbsp;&nbsp;
	               <select name="county" id="countyId" onchange="gotoselect()">
	               <option value="-1" >区</option>
	               </select>
				 </c:if>
				 
				 <c:if test="${level==75 }">
				 	<select name="county" id="countyId" >
	               		<option value="-1" >区</option>
	               		
	               		<c:forEach items="${zoneList }" var="zone">
	               		
		               		<c:choose>
		               			<c:when test="${au.provinceEnum.level==2}">
		               				<c:if test="${zone.name.substring(zone.name.length()-1 ) == '区' }"> 
										<option value="${zone.id }" <c:if test="${zone.id == adminUser.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
									</c:if>
		               			</c:when>
		               			<c:otherwise>
		               				<option value="${zone.id }" <c:if test="${zone.id == adminUser.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
		               			</c:otherwise>
		               		</c:choose>
	               				
						</c:forEach>
	               </select>
				 </c:if>
				</span>
				<div class="clear"></div>
	        	</div>
				<div class="rows">
					<label><font color="red">*</font>消息标题:</label>
					<span class="input">
					  <input type="text" name="title" id="title" value="${ml.title}" style="width:350px " /> <span style="color: red">*</span>
					</span>
					<div class="clear"></div>
					<input id="adminLevels" value="${level}" type="hidden">
		        </div>
		        
		         <div class="rows" id="image1" style="display: none" >
                        <label> <font color="red">*</font>消息图标：</label>
						<span class="input"> 
							<wh:UploadUtil fileDirName="message_icon" thumbH="100" thumbW="100" amount="5"
                                           submitName="icon" targetExt="jpg" relatedChar="${mt.icon}"></wh:UploadUtil>
                            
						</span>
                        <div class="clear"></div>
                 </div>
                 
		        <div class="rows" id="image" style="display: none" >
                        <label> <font color="red">*</font>消息详情图片：</label>
						<span class="input"> 
							<wh:UploadUtil fileDirName="message_icon" thumbH="100" thumbW="100" amount="5"
                                           submitName="image" targetExt="jpg" relatedChar="${mt.image}">建议尺寸：700PX(宽) * 300PX(高)</wh:UploadUtil>
                            
						</span>
                        <div class="clear"></div>
                 </div>
                    
		        <div class="rows">
					<label><font color="red">*</font>消息内容:</label>
					<span class="input">
					  <textarea rows="6" cols="60" name="content" id="content" 
					  style="margin: 0px;height: 400;width: 600;" />${ml.content }</textarea> <span style="color: red">*</span>
					</span>
					<div class="clear"></div>
		        </div>
		      <!--   <div class="rows" id="uid" >
					<label><font color="red">*</font>接收的用户:</label>
					<span class="input">
					  <input type="text" name="userId" id="userId" value="" /> <span style="color: red">*</span>
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows" id="orid" style="visibility: none;">
					<label><font color="red">*</font>订单:</label>
					<span class="input">
					  <input type="text" name="orderId" id="orderId" value="" /> <span style="color: red">*</span>
					</span>
					<div class="clear"></div>
		        </div> -->

		      
	

			<div class="rows">
				<label></label>
				<span class="input">
				 <input type="button" class="button" onclick="savePtype();" value="保存" />
				</span>
				<div class="clear"></div>
		</div>
      </div>
     </div>
     </form> 
     </td>
  </tr>

</table>
</div>
<div style="width:100%;height:20px;">

</div>
</div>
<script type="text/javascript">

function savePtype(){
	var title = document.getElementById("title").value;	
	var contenttip = document.getElementById("content").value;	
	var typevar=document.getElementById("typeId2").value;   
	var zone = document.getElementById("zone").value;
	var adminLevels=document.getElementById("adminLevels").value;
	var type = document.getElementById("typeId").value;
	//var image=document.getElementById("image1").value;
	//var image1 = document.getElementById("image").value;
	var icon =	$(":input[name='icon']").val(); 
	var image= $(":input[name='image']").val();
	

	  if(typevar<0){
    	 alert("请选择类型!");
         return;
    }
	  var county= $("#countyId").val();
		 var city=$("#zid").val();
	 if(adminLevels!="" && adminLevels<95){
		 if(county==-1&&city==-1){
			 alert("请选择地区!");
		    	return;
		 }
		
		 if(county==-1){
			 zone=city;
		 }else if(county!=-1){
			 zone=county;
		 }
			 
			
	   
	    if(zone==-1||zone==""){
	    	 alert("请选择地区!");
		    	return;
	    }
	    
    }else if(zone==undefined){
    	//zone=-1;
    }
	 
	  if(type!="" && type == 12){
			if(!icon){
				alert("消息图标不能为空");
				return;
			}
			if(!image){
				alert("消息详情图片不能为空");
				return;
			}
	}

    if(title==""){
       alert("请输入标题!");
       return;
    }
    if(contenttip==""){
        alert("请输入内容!");
        return;
     }
  	

   
    document.getElementById("saveForm").submit();
 }
 function chang(){
	return;
 }
 
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
						url:"<%=basePath %>system/message/changeZone",
						data: "zonesid="+ad.value,        
				        dataType: "text",
				        success: function (data){
				        	var json=eval( '('+data+ ')' );
			    	    	 if(ad==province){
			    	    		 city.options.add(new Option("请选择", "-1"));
			    	    	 }
			    	    	 county.options.add(new Option("请选择", "-1"));
			    	    	 for(var i in json){	
			    	    		 if(ad==province){
			    	    			
			    	    			 city.options.add(new Option(json[i],i));
			    	    		 }else{
			    	    			 county.options.add(new Option(json[i],i));
			    	    		 	
			    	    		 }
			    	    		 
			    	    	 }
			    	    	 $("#zid").val(ad.value);
				        }
					})
				}
				 $("#zid").val(-1);
		}
  
  
  function changeType(){
  	 	var ad = event.srcElement;
  	 	var type = document.getElementById("typeId").value;
		var type1 = document.getElementById("typeId");
		var type2 = document.getElementById("typeId2");
		if(parseInt(ad.value)>0){
					if(ad==type1){
						type2.options.length=0;				
					}
					$.ajax({
						type:"post",
						url:"<%=basePath %>system/message/changeType",
						data: "typeId="+ad.value,        
				        dataType: "text",
				        success: function (data){
				        	var json=eval( '('+data+ ')' );
			    	    	 for(var i in json){	
			    	    		 if(ad==type1){
			    	    			 type2.options.add(new Option(json[i],i));
			    	    		 }
			    	    	 }
				
				        }
					})
				}
				
		if(type==12){
			document.getElementById("image").style.display="";
			document.getElementById("zone").style.display="";
			document.getElementById("image1").style.display="";
			
		}else if(type==1){
			document.getElementById("image").style.display="none";
			document.getElementById("zone").style.display="none";
			document.getElementById("image1").style.display="none";
		}
  
  }
  
	
	function showhideType(typeId){
		if(typeId==12){
			document.getElementById("image").style.display="";
		}
	}
 
</script>
</body>
</html>
