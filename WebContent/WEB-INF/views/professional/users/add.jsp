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

<title>新增后台用户</title>
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
	                	<p>你当前的位置：[业务管理]-[城市代理设置]-[新增城市代理]</p>
	                	</c:if>
	                	
						<c:if test="${adminUser.id !=null}">
	                		<p>你当前的位置：[业务管理]-[城市代理设置]-[修改城市代理]</p>
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
			<label>登录名称</label>
			<span class="input">
			<input type="hidden" id="id" name ="id" value="${adminUser.id }">
			<input type=text name="loginname" id="loginname" value="${adminUser.loginname }"  onchange="doCheckName(true)" /> <font color="red">*请输入字母或数字组合!密码默认为888888，请及时修改</font>
			</span>
			<div class="clear"></div>
		</div>
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
			   <option value="">请选择</option>
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
			<label>联系人</label>
			<span class="input">
			  <input type=text name="contacts" id="contacts" value="${adminUser.contacts }" /><font color="red">*</font>
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
								<option value=0>--请选择--</option>
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
		
	<c:if test="${issq=='yes' }">
		<div class="rows" >
			<label>商圈所在地</label>
			<span class="input">
			<input type=hidden name="county" id="countyId" value="${current_user.provinceEnum.id }" >
				${current_user.provinceEnum.name }
			</span>
			<div class="clear"></div>
		</div> 
	</c:if>

		<div class="rows">
			<label>地址</label>
			<span class="input">
			  <input type=text style="min-width:250px;" name="address" id="address" value="${adminUser.address }" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="jfsx" style="display: none" >
         	<label>代理积分上限</label>
			<span class="input">
				<c:if test="${adminUser.id ==null}">
	                <input type=text style="min-width:250px;" name="integralMax" id="integralMax" value="${adminUser.scoreMax }"  onkeyup="value=value.replace(/[^\d]/g,'')"/>
              	 <font color="red">*创建新代理的时候填写,后期添加积分不能在做修改</font>
	             </c:if>
				<c:if test="${adminUser.id !=null}">
	                <input type=text style="min-width:250px;" name="integralMax" id="integralMax" value="${adminUser.scoreMax }" readonly="readonly" onkeyup="value=value.replace(/[^\d]/g,'')"/>
	             	<font color="red">*改代理最大申请积分数(不可编辑)</font>
	             </c:if>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="jf" style="display: none" >
         	<label>本次申请积分</label>
			<span class="input">
				<input type=text style="min-width:250px;" name="integral" id="integral" value=""  onkeyup="value=value.replace(/[^\d]/g,'')"/>
			
              	 		<font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		 <div class="rows" id="lmsj" style="display: none" >
         	<label>城市合伙人占比(代理最大积分为:${current_user.scoreMax})</label>
			<span class="input">
				<input type=text style="min-width:250px;" name="union" id="union" value="${adminUser.lmUnion }" onchange="doCheckNum()" onkeyup="value=value.replace(/[^\d]/g,'')"/>
			</select>
			
              	<font color="red" id="proportion"></font>
			</span>
			<div class="clear"></div>
		</div>
		
		
		
		<div class="rows" id="sn" style="display: none" >
			<label>店铺名称</label>
			<span class="input">
				<input type=text style="min-width:250px;" name="sellername" id="sellername" value="${seller.name}" /><font color="red">*</font>
			
			
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="dw"  style="display: none">
			<label> 代理位置：</label>
			<span class="input">
			
			<p><font color="red">获取代理位置定位商圈 点击地图获得定位坐标：</font></p>
				经度：
				<input readOnly="true" type=text style="min-width:250px;" name="longitude" id="longitude" value="${adminUser.longitude }" />
				纬度：
				<input readOnly="true" type=text style="min-width:250px;" name="latitude" id="latitude" value="${adminUser.latitude }" />			
				
				<div id="allmap" style="width: 99%;height: 500px;"></div>
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
	
	$("#proportion").html("该联盟合伙人占比是:"+union/scoreMax);
	
	
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
	$("#dw").show();
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
	document.getElementById("dw").style.display="none";
	$("#c").hide();
	$("#d").hide();
	document.getElementById("sn").style.display="none";
}
}


seller();
var verifyResult =false;
var phoneResult = false;
   function savePtype(){
	  
	   var name = document.getElementById("loginname").value;
	   var username = document.getElementById("username").value;
	   var sellername= document.getElementById("sellername").value;
	   var level = document.getElementById("level").value;
	   var contacts = document.getElementById("contacts").value;
	   var phone = document.getElementById("phone").value;
	   var address = document.getElementById("address").value;
	   var levelname = document.getElementById("levelname").value;
	   var integral = document.getElementById("integral").value;
	   var adminUser_integral = document.getElementById("adminUser_integral").value;
	   var integralMax = document.getElementById("integralMax").value;
	   var union = document.getElementById("union").value;
	   var levels  = document.getElementById("levels").value;
	   
	   if(name==""){
	         alert('请输入登录名称!');
	         return;
	      }else if(!isNumberOrLetter(name)){
	    	  alert("您输入的不是数字或者字母");
    		  return false;
	      }
	      
	     if(verifyResult==false){
	    	 doCheckName(false);
	    	 if(verifyResult==false){
	    		 return;
	    	 }
	     }
	     if(username==""){
	         alert('请输入真实名称!');
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
	     if(contacts==""){
	         alert('请输入联系人!');
	         return;
	      }
	     
	     if(phone==""){
	         alert('请输入联系电话!');
	         return;
	      }
		     
	    
	     if(address==""){
	         alert('请输入地址!');
	         return;
	      }
	     
	 if(document.getElementById("issq").value=="yes"){
		 
			 var countyId = document.getElementById("countyId").value;
			  if(countyId==""){
		         alert('商圈区域为空，不能添加商家');
		         return;
	      }
	 	}
	 
	  if(document.getElementById("iscity").value!="" && document.getElementById("iscity").value=="no" && document.getElementById("issq").value!="" && document.getElementById("issq").value=="no"){
		 var cityId = document.getElementById("cityId").value; 
		 if(cityId=="" && cityId==undefined){
			 alert('城市不能为空，不能添加代理');
	         return;
		 } 
	 } 
	
	  if(integralMax < integral){
		  alert('本次申请积分,大于最大代理最大上限');
	         return;
	  }
	  
	  
	var county= $("#countyId").find("option:selected").text(); //区
      document.getElementById("saveForm").submit();
   }
   
   function seller(){
	   var level = document.getElementById("level").value;
	
   }
   
    function doCheckphone(as){
  	var levels  = document.getElementById("levels").value;
   	//1、获取帐号
   	var $phone = $("#phone");
   	
   	//2、根据商家名称查询商家表是否存在记录，如果存在记录说明该帐号已经存在不可使用，否则不存在记录说明可以使用该帐号
   	if(levels == 65){
   		
	   	$.ajax({
	   		url:"${basePath}checkphoneByAjax",
	   		type:"post",
	   		data:{"phone":$phone.val()},	
	   	//	async:as,//异步属性，默认值为true
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
   }
 //校验商家名称唯一性
   function doCheckName(as){
   	//1、获取帐号
   	var $loginname = $("#loginname");
   	var $id = $("#id");
   	if($id.val() == "" || $id.val() == undefined || $id.val() == null){
   		var $id = $("#id");
   		$id.val("0");
   	}else{
   		var $id = $("#id");
   	}
   	//2、根据商家名称查询商家表是否存在记录，如果存在记录说明该帐号已经存在不可使用，否则不存在记录说明可以使用该帐号
   	$.ajax({
   		url:"${basePath}checkAdminUserNameByAjax",
   		type:"post",
   		data:{"loginname":$loginname.val(),"adId":$id.val()},	
   		async:as,//异步属性，默认值为true
   		success:function(msg){
   			var json=eval( '('+msg+ ')' );
   			if(true != json){
   				
   				//该帐号不可使用
   				alert("该登录账号已存在，请输入其它帐号名称！");
   				$("#loginname").focus();
   				verifyResult = false;
   			} else {
   				verifyResult = true;
   			}
   		}
   	});
   }
 
 //判断是否是数字或字母 
  function isNumberOrLetter(name){
	  var regu = "^[0-9a-zA-Z]+$"; 
	  var re = new RegExp(regu); 
	  if (re.test(name)) { 
	  		return true; 
	  }else{ 
	  		return false; 
	  } 
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
   function flashaddress(){
		 var province = document.getElementById("provinceId");
		  var city = document.getElementById("city");
		  var county = document.getElementById("county");
		  if(province.value!=null&&city.value!=null&&county.value!=null){
			  document.getElementById("address").value = province.options[province.selectedIndex].text + 
			  											city.options[city.selectedIndex].text + 
			  											county.options[county.selectedIndex].text; 
		  }
		
		};
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
   function changeSum(){
	   var as = Number($('#hqScale').val())
	   			+Number($('#centerScale').val())
	   			+Number($('#levelOneScaleText').val())
	   			+Number($('#levelTwoScaleText').val())
	   			+Number($('#levelThreeScaleText').val())
	   			+Number($('#otherSellerScale').val());
	   			
	   $('#providerScaleFinal').val(100-as);
   }
   
</script>
</body>
</html>
