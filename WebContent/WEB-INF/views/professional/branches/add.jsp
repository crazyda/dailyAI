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
    <td height="30" background="images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
	                <div class="header-left">
	                <c:if test="${seller.id==null}">
	                <p>你当前的位置：[业务管理]-[分店管理]-[添加店铺]</p>
	                </c:if>
					<c:if test="${seller.id!=null}">
	                <p>你当前的位置：[业务管理]-[分店管理]-[编辑店铺]</p>
	                </c:if>	
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
     <form action="<%=basePath %>professional/branches/save" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>分店名称</label>
			<span class="input">
			<input type="hidden" id="id" name ="id" value="${seller.id }">
			<input type=text name="name" id="name" value="${seller.name }"  onchange="doCheckName(true)" /> <font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>联系人</label>
			<span class="input">
			  <input type=text name="contacts" id="contacts" value="${seller.contacts }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>联系电话</label>
			<span class="input">
			  <input type=text name="phone" id="phone" value="${seller.phone }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="add">
			<label>联系地址</label>
			<span class="input">
			  <input type=text name="address" id="address" value="${seller.address }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="add">
			<label>绑定子账号</label>
			<span class="input">
			  <input type="button" value="&nbsp;+&nbsp;" onclick="addtext()"><br/>
			  <c:forEach items="${sanList }" var="san">
			  		<span><br/><input type="text" name="nums" value="${san.name }" onchange="checkNums(this,true)"/>&nbsp;<input type="button" value="&nbsp;-&nbsp;&nbsp;" onclick="removetext(this)"></span>
			  </c:forEach>
			  <span id="main"></span>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="dw">
			<label> 坐标定位：</label>
			<span class="input">
			<font color="red">*</font>省份：
			<select  name="province" id="provinceId" onchange="changeZone();gotoselect()">
				<option value=0>请选择</option>
				<c:forEach items="${zoneList }" var="zone">
					<option value="${zone.id }" <c:if test="${zone.id == seller.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
				</c:forEach>
			</select>
			<font color="red">*</font>城市：
			<select  name="city" id="cityId" onchange="changeZone();gotoselect()">
					<option value=0>请选择</option>
					<c:if test="${seller.provinceEnum!=null}">
							<option value=${seller.provinceEnum.provinceEnum.id } selected="selected">${seller.provinceEnum.provinceEnum.name }</option>
					</c:if>
			</select> 
			<font color="red">*</font>区县：
			<select  name="county" id="countyId" onchange="gotoselect();flashaddress()">
					<option value=0>请选择</option>
					<c:if test="${seller.provinceEnum!=null}">
							<option value=${seller.provinceEnum.id } selected="selected">${seller.provinceEnum.name }</option>
					</c:if>
			</select> 
			<p><font color="red">*点击地图获得商家定位坐标：</font></p>
				经度：
				<input readOnly="true" type=text style="min-width:250px;" name="longitude" id="longitude" value="${seller.longitude }" />
				纬度：
				<input readOnly="true" type=text style="min-width:250px;" name="latitude" id="latitude" value="${seller.latitude }" />			
				<p></p>
				<input type=hidden style="min-width:250px;" name="detailedAddress" id="detailedAddress" value="${seller.detailedAddress }" />
				<p></p>
				<div id="allmap" style="width: 99%;height: 500px;"></div>
			</span>
			<div class="clear"></div>
			</div>
			<div class="rows" id="sq">
				<label>所属商圈</label>
			<span class="input">
			<font color="red">*</font>省份：
			<select  name="province" id="proId" onchange="changeP()">
				<option value=0>请选择</option>
				<c:forEach items="${zoneList }" var="zone">
					<option value="${zone.id }" <c:if test="${zone.id == seller.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
				</c:forEach>
			</select>
			<font color="red">*</font>城市：
			<select  name="city" id="ciId" onchange="getadvertwo()">
					<option value=0>请选择</option>
			</select> 
			<font color="red">*</font>商圈：
			<select  name="advertwo" id="advertwoId" >
					<option value=0>请选择</option>
					<c:if test="${seller.provinceEnum!=null}">
							<option value=${seller.advertwo.id } selected="selected">${seller.advertwo.loginname }</option>
					</c:if>
			</select>  
				</div>
				<div class="rows" id="pw">
					<label>品牌类型</label>
					<span class="input">
						<select  name="typeid" >
							<c:forEach items="${shoptypeList}" var="type">
								<option value="${type.id }" <c:if test="${type.id == seller.shoptypes.id} ">selected="selected"</c:if> >${type.name }</option>
							</c:forEach>
						</select>
					</span>
					<div class="clear"></div>
				</div>
		<div class="rows">
			<label>分店介绍</label>
			<span class="input">
			  <textarea rows="30" cols="10" name="remark" id="remark" >${seller.remark }</textarea>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button" onclick="savePtype();" value="添 加" />
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
<script type="text/javascript" src="<%=basePath %>js/coordtransform.js"></script>
<script type="text/javascript" src="<%=basePath %>js/maputil.js"></script>
<script>
var verifyResult =false;
var result = false;
var numresult = "0";
var count = ${count};
   function savePtype(){
	   var name = document.getElementById("name").value;
	   var contacts = document.getElementById("contacts").value;
	   var phone = document.getElementById("phone").value;
	   var address = document.getElementById("address").value;
	   
	   var countyId = document.getElementById("countyId").value;
	   var longitude = document.getElementById("longitude").value;
	   var advertwoId = document.getElementById("advertwoId").value;
	   
	   var nums = document.getElementsByName("nums");
	   var remark = document.getElementById("remark");
	  
	   var $id = $("#id");
	   
	      if(name==""){
	         alert('请输入登录名称!');
	         return;
	      }
	     if(verifyResult==false){
	    	 doCheckName(false);
	    	 if(verifyResult==false){
	    		 return;
	    	 }
	     }
	     var $id = $("#id");
		    for(var i=0;i<nums.length;i++){
		    	 var $t = $(nums[i]);
		  	  
		  	   $.ajax({
		  		   url:"${basePath}checkUserNameByAjax",
		  		   type:"post",
		  		   data:{"name":$t.val(),"id":$id.val()},
		  	   	   async:false,
		  	   	   success:function(msg){
		  	   		   var json = msg;
		  	   		   if(json=="1"){
		  	   				numresult=false;
		  	   				alert("该账号不存在");
		  	   				
		  	   				$t.val("");
		  	   				$t.focus();
		  	   		   }else if(json=="2"){
		  	   				numresult=false;
		  	   				$t.val("");
		     			   alert("该账号已绑定");
		     		   }
		  	   	   }
		  	   });
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
	    if(countyId==""||parseInt(countyId)==0){
		        alert('请选定位!');
		        return;
		 } 
		 if(longitude==""){
		       	alert('请选择经纬度!');
		       	return;
		 } 
		 if(remark.value.length<20){
	         alert('店铺介绍不能少于20字!');
	         return;
	      }
		   if(remark.value.length>255){
		         alert('店铺介绍不能超过255字!');
		         return;
		      }
		 if(advertwoId==""||parseInt(advertwoId)==0){
		         alert('请选择所在商圈!');
		         return;
		  } 
		 
		
      document.getElementById("saveForm").submit();
   }
   
   function addtext(){
	   if(count<4){
	  	 	$("#main").after("<span><br/><input type=\"text\" name=\"nums\" onchange=\"checkNums(this)\" />&nbsp;<input type=\"button\" value=\"&nbsp;-&nbsp;&nbsp;\" onclick=\"removetext(this)\"></span>");
	   		count++;
	   }
   }
   function removetext(button){
	   count--;
	   $(button).parent().remove();
	  
   }
   //判断子账号是否重名
   function checkNums(th,as){
	   var $t = $(th);
	   var $id = $("#id");
	   $.ajax({
		   url:"${basePath}checkUserNameByAjax",
		   type:"post",
		   data:{"name":$t.val(),"id":$id.val()},
	   	   async:as,
	   	   success:function(msg){
	   		   var json = msg;
	   		   if(json=="1"){
	   				alert("该账号不存在");
	   				$t.val("");
	   				$t.focus();
	   		   }else if(json=="2"){
	   				$t.val("");
   			   		alert("该账号已绑定");
   		   	   }
	   	   }
	   });
   }
   
   //校验商家名称唯一性
   function doCheckName(as){
   	//1、获取帐号
   	var $name = $("#name");
   	var $id = $("#id");
   	if($id.val() == "" || $id.val() == undefined || $id.val() == null){
   		$id.val("0");
   	}else{
   		var $id = $("#id");
   	}
  
   	//2、根据商家名称查询商家表是否存在记录，如果存在记录说明该帐号已经存在不可使用，否则不存在记录说明可以使用该帐号
   	$.ajax({
   		url:"${basePath}checkAdminUserNameByAjax",
   		type:"post",
   		data:{"name":$name.val(),"adId":$id.val()},	
   		async:as,//异步属性，默认值为true
   		success:function(msg){
   			var json=msg;
   			if("false" == json){
   				
   				//该帐号不可使用
   				alert("该登录账号已存在，请输入其它帐号名称！");
   				$("#name").focus();
   				verifyResult = false;
   			} else {
   				verifyResult = true;
   			}
   		}
   	});
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
						url:"<%=basePath %>professional/branches/changeZone",
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
						url:"<%=basePath %>professional/branches/changeZone",
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
						url:"<%=basePath %>professional/branches/getadvertwo",
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
		 var province = document.getElementById("province");
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
		var s1 =  document.getElementById("province");
		var s2 =  document.getElementById("city");
		var s3 =  document.getElementById("county");
		var localzone = ad.options[ad.selectedIndex].text;
		if(ad == s3){
			localzone = s1.options[s1.selectedIndex].text+s2.options[s2.selectedIndex].text+s3.options[s3.selectedIndex].text
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
			})
		
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
