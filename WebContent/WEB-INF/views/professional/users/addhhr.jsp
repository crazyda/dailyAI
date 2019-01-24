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

<title>新增合伙人</title>
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

<body>
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
	                	<c:if test="${adminUser.id ==null}">
	                	<p>你当前的位置：[业务管理]-[联盟合伙人设置]-[新增联盟合伙人]</p>
	                	</c:if>
	                	
						<c:if test="${adminUser.id !=null}">
	                		<p>你当前的位置：[业务管理]-[联盟合伙人设置]-[修改联盟合伙人]</p>
	                	</c:if>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table>

     <td>
     <form action="<%=basePath %>professional/user/save" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
		<div class="rows">
			<label>真实名称</label>
			<span class="input">
			  <input type=text name="username" id="username" value="${adminUser.username }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>用户级别</label>
			<span class="input">
			   <select name="levelname" id="levelname" onchange="show(this.value);">
			   <c:forEach  items="${leveltable }" var="levelset" >
			  		 <option  value="${levelset.key }" <c:if test="${ levelset.key == adminUser.level}">selected='selected'</c:if> >${levelset.value }</option>
			   </c:forEach>
			</select> <font color="red">*</font> 
			</span>
			<div class="clear"></div>
		</div>
		
		
		<div class="rows">
			<label>管理角色</label>
			<span class="input">
				<select id="level" name="level" onchange="seller()" >
					<c:forEach items="${level }" var="l">
						<option  value="${l.id }"  <c:if test="${l.id==adminUser.rePermissionRoleId }">selected="selected"</c:if> >${l.name }</option>
					</c:forEach>
				</select>
				<font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		
		<div class="rows">
			<label>预留手机号</label>
			<span class="input">
			  <input type=text name="phone" id="phone" value="${adminUser.phone }" onchange="doCheckphone(true)" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		
		
		
		<c:if test="${show=='yes' }">
		<div class="rows">
			<label>已保存地区</label>
			<span class="input">
			<font color="red"> ${adminUser.provinceEnum.provinceEnum.name }-${adminUser.provinceEnum.name } </font>
			</span>
			<div class="clear"></div>
		</div>
		</c:if>
	 <c:if test="${iscity=='no' }">
		<c:if test="${issq=='no' }">
		 <div class="rows" >
			<label>省份</label>
			
			<span class="input">
				省
				<select  name="province" id="provinceId" onchange="changeZone();gotoselect()">
					<c:forEach items="${zoneList }" var="zone">
						<option value="${zone.id }" <c:if test="${zone.id == adminUser.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
					</c:forEach>
				</select>
				<div id="c" style="display: none">市
				<select  name="city" id="cityId" onchange="changeZone();gotoselect()">
				</select> 
			 
				</div>
				<div id="d" style="display: none">区
					<select  name="county" id="countyId" onchange="gotoselect()" >
							<option value=0>请选择</option>
					</select>
				</div>
				<span id="tip" style="color: red"></span>
			</span>
			
			<div class="clear"></div>
		</div> 
		</c:if>
		</c:if> 
	<c:if test="${iscity=='yes' }">
		<div class="rows" >
			<label>区</label>
			<span class="input">
			<select  name="county" id="countyId">
					<option value=0>--请选择--</option>
					<c:forEach items="${distrtList}" var="zone">
					
				 	<c:choose>
						<c:when test="${current_user.provinceEnum.level==3}">
							<option value="${zone.id }"
							 <c:if test="${zone.id  == adminUser.provinceEnum.id }">selected="selected"</c:if>>
					 	 	${zone.name }
					 		</option>
						</c:when>
						<c:otherwise>
								<option value="${zone.id }" <c:if test="${zone.id == adminUser.provinceEnum.id }">selected="selected"</c:if>>
						 	 			${zone.name }
								</option>
							
						</c:otherwise>
				</c:choose> 
					</c:forEach>
			</select>
			</span>
			<div class="clear"></div>
		</div> 
	</c:if>
		 <div class="rows" id="lmsj" style="display: none" >
         	<label>城市合伙人占比(代理最大积分为:${current_user.scoreMax})</label>
			<span class="input">
				<input type=text style="min-width:250px;" name="union" id="union" value="${adminUser.lmUnion }" onchange="doCheckNum()" onkeyup="value=value.replace(/[^\d]/g,'')"/>
			</select>
			
              	<font color="red" id="proportion"></font>
			</span>
			<div class="clear"></div>
		</div>
			
		<div class="rows">
			<label></label>
			<span class="input">
			<input type="hidden" id = "levels" value = "">
			<input type=hidden name="adminUser_integral" id="adminUser_integral" value="${current_user.score}">
			<input type=hidden name="scoreMax" id="scoreMax" value="${current_user.scoreMax}">
			<input type=hidden name="provinces" id="provinces" value="${provinecs}">
			<input type=hidden name="issq" id="issq" value="${issq}">
			<input type=hidden name="iscity" id="iscity" value="${iscity}">
			<input type="hidden" name ="adminUserId" value="${adminUser.id }">
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
function doCheckNum(){
	var scoreMax = $("#scoreMax").val();
	var union = $("#union").val();
	
	$("#proportion").html("该联盟合伙人占比是:"+(union/scoreMax).toFixed(6));
	
	
}
$(function(){
		
	$("#levelname").change(function(){
		var name= $("#levelname").find("option:selected").text(); 
	});
	
 	$("#countyId").change(function(){
		var county= $("#countyId").find("option:selected").text(); //区
		var city= $("#cityId").find("option:selected").text();  //市
		 if(county.lastIndexOf("区")>-1||county=='请选择'){
			 $("#tip").text("代理所属城市:"+city);
			
		}else{
			$("#tip").text("代理所属城市:"+county);
		} 
	}); 
});

show('${adminUser.level}');
function show(levelname){
	$("#levels").val(levelname);
if(levelname==60){
	if(document.getElementById("issq").value=="yes"){
		$("#c").hide();
		$("#d").hide();
		$("#dw").hide();
		$("#jfsx").hide();
		$("#jf").hide();
		$("#sn").hide();
		
	}else{
		$("#dw").hide();
		$("#c").show();
		$("#d").show();
		$("#jf").show();
		$("#jfsx").show();
		$("#lmsj").hide();
	}
	
	document.getElementById("sn").style.display="";
	
}else if(levelname==65  ){
	$("#jf").hide();
	$("#jfsx").hide();
	$("#lmsj").show();
	$("#dw").hide();
	$("#c").show();
	$("#d").show();
	$("#sn").hide();
	
}else if(levelname==75){
	$("#jf").show();
	$("#jfsx").show();
	$("#lmsj").hide();
	$("#dw").hide();
	$("#c").show();
	$("#d").show();
	$("#sn").hide();
}else if(levelname==85){
	$("#lmsj").hide();
	$("#dw").hide();
	document.getElementById("c").style.display="none";
	document.getElementById("d").style.display="none";
	document.getElementById("sn").style.display="none";
	document.getElementById("jf").style.display="none";
	document.getElementById("jfsx").style.display="none";
}else{
	$("#jfsx").show();
	$("#jf").show();
	$("#dw").hide();
	$("#c").hide();
	$("#d").hide();
	$("#sn").hide();
	$("#lmsj").show();
}
}



		var verifyResult =false;
		var phoneResult = false;
	  
   function savePtype(){
	   var level = document.getElementById("level").value;
	   var phone = document.getElementById("phone").value;
	   var username = document.getElementById("username").value;
	   var levelname = document.getElementById("levelname").value;
	 
	   var adminUser_integral = document.getElementById("adminUser_integral").value;
	  
	   var union = document.getElementById("union").value;
	   var levels  = document.getElementById("levels").value;
	  
	   if(phoneResult){
		   alert('请确认合伙人身份');
		  return; 
	   }
	      
	   if(username==""){
	         alert('真实名称不能为空');
	         return;
	      }
	       if(levelname==""){
	         alert('请选择用户级别！');
	         return;
	      }
	      
	     if(parseInt(level)==-1){
	         alert('请选择角色!');
	         return;
	      }
	     if(phone==""){
	         alert('请输入联系电话!');
	         return;
	      }
	     if(union==""){
	         alert('占比不能为空');
	         return;
	      }
	 
	  if(document.getElementById("iscity").value!="" && document.getElementById("iscity").value=="no" && document.getElementById("issq").value!="" && document.getElementById("issq").value=="no"){
		 var cityId = document.getElementById("cityId").value; 
		 if(cityId=="" && cityId==undefined){
			 alert('城市不能为空，不能添加代理');
	         return;
		 } 
	 } 
	
	 
	  
	  
	var county= $("#countyId").find("option:selected").text(); //区
      document.getElementById("saveForm").submit();
   }
   
   function seller(){
	   var level = document.getElementById("level").value;
	
   }
   
 //校验商家名称唯一性
  
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
					});
				}
				
		}
   function doCheckphone(as){
	  	var levels  = document.getElementById("levels").value;
	   	//1、获取帐号
	   	var $phone = $("#phone");
		   	$.ajax({
		   		url:"${basePath}checkphoneByAjax",
		   		type:"post",
		   		data:{"phone":$phone.val()},	
		   		
		   		success:function(msg){
		   			if(msg.code < 0){
		   				alert(msg.message);
		   				phoneResult = true;
		   			}else{
		   				phoneResult = false;
		   			}
		   			
		   		}
		   	});
	   		
	   
	   }
   
   function gotoselect(){
		var ad = event.srcElement;
		var s1 =  document.getElementById("provinceId");
		var s2 =  document.getElementById("cityId");
		var s3 =  document.getElementById("countyId");
		var localzone = ad.options[ad.selectedIndex].text;
		if(ad == s3){
			localzone = s1.options[s1.selectedIndex].text+s2.options[s2.selectedIndex].text+s3.options[s3.selectedIndex].text;
		}
			$.ajax({
				type:"post",
				url:"<%=basePath %>professional/user/getAddressPoint",
				data: "address="+localzone,        
		        dataType: "text",
		        success: function (data){
		        	var point2 = new BMap.Point((eval('(' + data + ')').lng),(eval('(' + data + ')').lat));
		        	map.centerAndZoom(point2,12);
		
		        }
			});
		
		};
   
</script>
</body>
</html>
