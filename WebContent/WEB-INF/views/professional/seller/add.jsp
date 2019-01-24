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

<body onload="load()" >
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
						<p>你当前的位置：[业务管理]-[店铺信息管理]</p>
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
     <form action="<%=basePath %>professional/seller/save" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>管理账号</label>
			<span class="input">
			   ${seller.adminUser.loginname } 
			   <span style="cursor: pointer; margin-left: 9.5%;"
					onclick="if(!confirm('您确认要重置密码吗?重置后密码为888888可用于商家版登录!'))return false; else resetPassword('${seller.adminUser.id }');"><a><u><font color="red" >重置密码</font></u></a></span>
				</span>
			</span>
			<div class="clear"></div>
		</div>
      <div class="rows">
			<label>店铺名称</label>
			<span class="input">
			<input type="hidden" id="id" name ="id" value="${seller.id }">
			<input type=text name="name" id="name" value="${seller.name }"   /> <font color="red">店铺名称用于显示在app端，与后台登录 用户可以不相同*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>店铺联系人</label>
			<span class="input">
			  <input type=text name="contacts" id="contacts" value="${seller.contacts }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>商家邀请码</label>
			<span class="input">
			  ${seller.adminUser.invitecode }
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>店铺联系电话</label>
			<span class="input">
			  <input type=text name="phone" id="phone" value="${seller.phone }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="add">
			<label>店铺地址</label>
			<span class="input">
			 ${seller.adminUser.provinceEnum.provinceEnum.provinceEnum.name }${seller.adminUser.provinceEnum.provinceEnum.name }${seller.adminUser.provinceEnum.name }<input type=text name="address" id="address" value="${seller.address }" /><input type="button" value="定位" onclick="gotoselect()"><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="add">
			<label>绑定粉丝号</label>
			<span class="input">
			  <input type=text name="mainnum" id="mainnum" value="${sanMain.name }" onchange="checkUserName(true)" />&nbsp;<input type="button" value="&nbsp;+&nbsp;" onclick="addtext()"><font color="red">此账号可用于商家版或用户版登录*</font> 
			  <c:forEach items="${sanList }" var="san">
		  		<span><br/><input type="text" name="nums" value="${san.name }" onchange="checkNums(this,true)"/>&nbsp;<input type="button" value="&nbsp;-&nbsp;&nbsp;" onclick="removetext(this)"></span>
			  </c:forEach>
			  <span id="main"></span>
			</span>
			<div class="clear"></div>
		</div>
		
		<div class="rows" id="add">
			<label>商圈负责人</label>
			<span class="input">
			  ${seller.adminUser.parentAdminUser.contacts }-${seller.adminUser.parentAdminUser.phone }
			</span>
			<div class="clear"></div>
		</div> 
		
		<div class="rows" id="add">
			<label>商圈位置</label>
			<span class="input">
			  ${seller.adminUser.provinceEnum.name }
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" id="dw">
			<label> 地图定位：</label>
			<span class="input">
		
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
			
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button" onclick="savePtype();" value="添 加" />
			  <input type=hidden id="imageurls_logo" name="imageurls_logo" value="${seller.logo }" />
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
var numresult = true;
var count = ${count};

	function load(){
	
		if(${seller.longitude==null?0:seller.longitude }>0 && ${seller.latitude==null?0:seller.latitude }>0){
			var point2 = new BMap.Point(seller.longitude,seller.latitude);
		    map.centerAndZoom(point2,10);
		}else{
			gotoselect();
		}
	
		
	}		 	
	


   function savePtype(){
	   var name = document.getElementById("name").value;
	   var contacts = document.getElementById("contacts").value;
	   var phone = document.getElementById("phone").value;
	   var address = document.getElementById("address").value;
	
	   
	      if(name==""){
	         alert('请输入店铺名称!');
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
	   
		
		
      document.getElementById("saveForm").submit();
   }
   
   function addtext(){
	   if(count<4)
	   $("#main").after("<span><br/><input type=\"text\" name=\"nums\" onchange=\"checkNums(this)\" />&nbsp;<input type=\"button\" value=\"&nbsp;-&nbsp;&nbsp;\" onclick=\"removetext(this)\"></span>");
	   count++;
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
   
   //判断是否存在该用户名
   function checkUserName(as){
	   var $mainnum = $("#mainnum");
	   var $id = $("#id");
	   $.ajax({
		   url:"${basePath}checkUserNameByAjax",
		   type:"post",
		   data:{"name":$mainnum.val(),"id":$id.val()},
	   	   async:as,
	   	   success:function(msg){
	   		   var json = msg;
	   		   if(json=="1"){
	   				alert("该账号不存在");
	   				$("#mainnum").val("");
	   				$("#mainnum").focus();
	   		   }else if(json=="2"){
	   				$("#mainnum").val("");
   			   		alert("该账号已绑定");
   		   	   }else if(json=="0"){
   		   			result=true;
   		   	   }
	   	   }
	   });
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
   
   
   
   
   
   function gotoselect(){
   			var address = document.getElementById("address").value;
   			var localzone='${seller.adminUser.provinceEnum.provinceEnum.provinceEnum.name }${seller.adminUser.provinceEnum.provinceEnum.name }${seller.adminUser.provinceEnum.name }'+address;
   			
   				
			$.ajax({
				type:"post",
				url:"<%=basePath %>professional/user/getAddressPoint",
				data: "address="+localzone,        
		        dataType: "text",
		        success: function (data){
		        	
		        	var point2 = new BMap.Point((eval('(' + data + ')').lng),(eval('(' + data + ')').lat));
		        	map.centerAndZoom(point2,15);
		
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
   
   
   
   //////////////////////////////////////////
   
   function setImageUrlTure(src,url,type){

   		if(type==8){
   		$("#div_img_logo").html(src);
   		 $("#div_img_logo").show();

   		 $("#imageurls_logo").attr("value",url);
   		}
	}
	function setImgurl(url,type){
			if(type ==8){
				url2 = "logo/"+url;
				url = "${RESOURCE_LOCAL_URL}logo/"+url;
			}
	       var src = "&nbsp;<img src=\""+url+"\"  ondblclick=\"delImg('"+url+"','"+type+"');\" alt='双击删除图片' title='双击删除图片' />";
		   setImageUrlTure(src,url2,type);
	}
	
	function delImg(url,type){
	  	if(type==8){
	  		$("#div_img_logo").html("");
	 	   $("#imageurls_logo").attr("value","");
	  	}
 }
   
   function setImgurls(imageurls,type,url){
		  
	    var src = "&nbsp;<img src=\""+url+"\"  ondblclick=\"delImg('"+url+"','"+type+"');\" alt='双击删除图片' title='双击删除图片' />";
	    //alert('src======='+src);
	    setImageUrlTure(src,imageurls,type);
		}
   
   $(function(){
	   if('${seller}'!=''&&'${seller.logo}'!=''){
		   setImgurls('${seller.logo}',8,'${RESOURCE_LOCAL_URL}${seller.logo}');
	 	}
   });
   
   function resetPassword(id){
		window.location.href = "${BASEPATH_IN_SESSION}professional/seller/resettingpwd?id="+id;
	}
   
  
     
</script>
</body>
</html>
