<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.axp.util.StringUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/shop.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=FhqpjmNavgBUzcjqOlHfeqrR"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-validation/messages_cn.js"></script>

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
						<p>你当前的位置：[业务管理]-[我的用户]-[新增后台用户]</p>
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
     <form action="${BASEPATH_IN_SESSION}adminUser/save" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>登录名称</label>
			<span class="input">
			<input type="hidden" name ="id" value="${auser.id }">
			  <input type=text name="loginName" id="loginName" value="${auser.loginname }" <c:if test="${auser.loginname !=null}">readonly="readonly"</c:if>   /> <font color="red">*请输入字母!密码默认为888888，请及时修改</font>
			</span>
			<div class="clear"></div>
		</div>
      	<div class="rows">
			<label>真实名称</label>
			<span class="input">
			  <input type=text name="username" id="username" value="${auser.username }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>级别</label>
			<span class="input">
			   <select name="levelname" id="levelname">
			   <option value="">请选择</option>
			   <c:forEach  items="${leveltable }" var="levelset" >
			  		 <option  value="${levelset.key }" <c:if test="${ levelset.key == auser.level}">selected='selected'</c:if> >${levelset.value }</option>
			   </c:forEach>
			</select> <font color="red">*</font> 
			</span>
			<div class="clear"></div>
		</div>
		<c:if test="${ userLevel==85}">
		<div class="rows">
			<label>所属地区</label>
			<span class="input">
				省：
				<select name="provinceId" id="provinceId" onchange="changeZone()">
				<option value="">请选择</option>
				<c:forEach  items="${provinceList }" var="pro" >
					<option  value="${pro.id }" <s:if test="#pro.id == auser.provinceEnum.provinceEnum.provinceEnum.id">selected='selected'</s:if> >${pro.name }</option>
				</c:forEach>
			  	</select>
			  	市：
			  	<select  name="cityId" id="cityId" onchange="changeZone()">
			  	<option value=0>请选择</option>
			  	<c:if test="${auser.provinceEnum!=null}">
					<option value=${auser.provinceEnum.provinceEnum.id } selected="selected">${auser.provinceEnum.provinceEnum.name }</option>
				</c:if>
				</select>
				区：
				<select  name="zonesid" id="countyId">
				<option value=0>请选择</option>
				<c:if test="${auser.provinceEnum!=null }">
					<option value=${auser.provinceEnum.id } selected="selected">${auser.provinceEnum.name }</option>
				</c:if>
				</select>
			</span>
			<div class="clear"></div>
		</div>
		</c:if>
		<div class="rows">
			<label>${coin.name }</label>
			<span class="input">
			  <input type=text name="quantity" id="quantity" value="${auser.quantity }" <c:if test="${auser.id >0 }">readonly="readonly"</c:if> />${coin.unit }<font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
      <div class="rows">
			<label>联系人</label>
			<span class="input">
			  <input type=text name="contacts" id="contacts" value="${auser.contacts }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>联系电话</label>
			<span class="input">
			  <input type=text name="phone" id="phone" value="${auser.phone }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>联系地址</label>
			<span class="input">
			  <input type=text name="address" id="address" value="${auser.address }" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>

		<div class="rows" id="limitDiv" style="display:none;">
			<label>是否增加地域限制</label>
			<span class="input">
				<input type="radio" name="isZoneLimit" id="isZoneLimit" value="0" />否
				<input type="radio" name="isZoneLimit" id="isZoneLimit" value="1" />是
			</span>
		</div>
		<div class="rows" id="mapDiv" style="display:none;">
			<label> 坐标定位：</label>
			<span class="input">
				<font color="red">*点击地图获得定位坐标：</font><br/>
					经度：<input readonly type="text" style="min-width:250px;" name="longitude" id="longitude" value="${auser.longitude }" />
					纬度：<input readonly type="text" style="min-width:250px;" name="latitude" id="latitude" value="${auser.latitude }" />
					限制半径：<input type="text" style="min-width:100px;" name="radius" id="radius" value="${auser.radius}" />(单位：千米)
				<br/>
				<br/>
				<div id="allmap" style="width: 99%;height: 500px;"></div>
			</span>
			<div class="clear"></div>
		</div>
		
		<div class="rows" id="sellerDiv" style="display:none;">
			<label>商家：</label>
			<span class="input">
				城市代理： <wh:selectTag id="v_center" name="v_center" optionValue = ""  defaultOptionText="--请选择--" showProperty="username" where="level >= 75 and level <=80" tableName="admin_user" key="id"></wh:selectTag>
				&nbsp;&nbsp;
				商家联盟： <wh:selectTag parentId="v_center" parentIdName="parent_id" parentValue="${v_center }" id="v_alliance" name="v_alliance" optionValue = "" where =" level > 60 and level< 75" defaultOptionText="--请选择--" showProperty="username"  tableName="admin_user" key="id"></wh:selectTag>
				&nbsp;&nbsp;
				 品牌商家： <wh:selectTag parentId="v_alliance" parentIdName="adminuser_id" parentValue="${v_alliance }" id="v_seller" name="v_seller" optionValue = ""  defaultOptionText="--请选择--" showProperty="name"  tableName="seller" key="id"></wh:selectTag>
				<p></p>
				商家：<input disabled="disabled"  type=text name="sellername" id="sellername" value="${sellerName}"/>
				<input type="hidden" name="sellerId" id="sellerId" value="${auser.sellerId }"/>
			</span>
			<div class="clear"></div>  
		</div>
		<!--------------------- 会员分佣设置内容 ----------------------------->

		<!-- ------------------------------------------------- -->
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
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/maputil.js"></script>
<script>
	function setScale(type){
		var ad = event.srcElement;
		if(type==1){
			//$("#levelOneScale").val(ad.value);
			$('input[id="levelOneScale"]').each(function(){
			    $(this).val(ad.value);
			});
		}
		if(type==2){
			//$("#levelTwoScale").val(ad.value);
			$('input[id="levelTwoScale"]').each(function(){
			    $(this).val(ad.value);
			});
		}
		if(type==3){
			//$("#levelThreeScale").val(ad.value);
			$('input[id="levelThreeScale"]').each(function(){
			    $(this).val(ad.value);
			});
		}
		
	}
	$(function(){
		if($('#levelOneScale')!=null){
			$('#levelOneScaleText').val($('input[id="levelOneScale"]:eq(0)').val());
			$('#levelTwoScaleText').val($('input[id="levelTwoScale"]:eq(0)').val());
			$('#levelThreeScaleText').val($('input[id="levelThreeScale"]:eq(0)').val());
		}
		changeSum();
		var isZoneLimit="${auser.isZoneLimit}";
		var userLevel = "${auser.level}";
		
		if(isZoneLimit==''||isZoneLimit=='false'){
			$('input[name="isZoneLimit"]:eq(0)').attr("checked","checked");
		}
		else if(isZoneLimit=='true'){
			$('input[name="isZoneLimit"]:eq(1)').attr("checked","checked");
		}
		
		if(userLevel==60){
			$("#quantityDiv").css("display","none");
			$("#sellerDiv").css("display","block");
		}else{
			$("#CentCommission").css("display","none");
		}
		
		var level = "${userLevel}";
		if(level==75||level==80){
			$("#limitDiv").css("display","block");
			$("#mapDiv").css("display","block");
		}
		$("#levelname").change(function(){
			var val = $(this).val();
			if(val==60){
				$("#quantityDiv").css("display","none");
				$("#sellerDiv").css("display","block");
				$("#CentCommission").css("display","block");
			}else{
				$("#CentCommission").css("display","none");
				$("#quantityDiv").css("display","block");
				$("#sellerDiv").css("display","none");
			}
		});
		$("#v_seller").change(function(){
			var checkText=$("#v_seller").find("option:selected").text();
			var checkId=$("#v_seller").val();
			$("#sellername").val(checkText);
			$("#sellerId").val(checkId);
		});
		
		 $("#saveForm").validate({
		 		submitHandler : function(form) {
		 		 document.getElementById("saveForm").submit();
		 		},
		 		errorPlacement : function(error, element) {//自定义提示错误位置
		 			$(".l_err").css('display','block');
		 			//element.css('border','2px solid #FFCCCC');
		 			$(".l_err").html(error.html());
		 		},
		 		rules:{
		 			levelOneScale:{
	                    required:true,
	                    range:[0,100],
		 				min:0.01
	                },
	                levelTwoScale:{
	                    required:true,
	                    range:[0,100],
	                	min:0.01
	                },
	                levelThreeScale:{
	                    required:true,
	                    range:[0,100],
	                	min:0.01
	                }
	            },
		 		success: function(label) {//验证通过后
		 			$(".l_err").css('display','none');
		 		},
                hqScale:{
                    required:true,
                    range:[0,100],
                	min:0.01
                },
                centerScale:{
                    required:true,
                    range:[0,100],
                	min:0.01
                },
                providerScale:{
                    required:true,
                    range:[0,100],
                	min:0.01
                }
                
		 });
		 
	});
   function savePtype(){
	   var proScale = Number($("#hqScale").val())+Number($("#centerScale").val())+Number($("#providerScale").val());
	   var length  = $('input[id="vipScaleId"]').length;
	   for(var i=0;i<length;i++){
		   var total = 
			   Number($('input[id="levelOneScale"]:eq('+i+')').val())+
			   Number($('input[id="levelTwoScale"]:eq('+i+')').val())+
			   Number($('input[id="levelThreeScale"]:eq('+i+')').val())+proScale;
		   if(total>100){
			   alert('请确认分佣比例总和在100%之内!');
		       return;
		   }
	   }
	   if($('#providerScaleFinal').val()<0){
		   alert('请确认分佣比例总和在100%之内!');
	       return;
	   }
	   var name = document.getElementById("loginName").value;
	      if(name==""){
	         alert('请输入登录名称!');
	         return;
	      }
	      var levelname = document.getElementById("levelname").value;
	      if(levelname==""){
	         alert('请选择新用户级别!');
	         return;
	      }
	      
		var val = $('input[name="isZoneLimit"]:checked').val();
		var radius = $("#radius").val();
		if(''==radius&&val==1){
			alert('请输入限制范围半径!');
			return;
		}
		var radius = $("#countyId").val();
		if(radius<=0){
			alert('请选择所处地区!');
			return;
		}

     
      document.getElementById("saveForm").submit();
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
						url:"${BASEPATH_IN_SESSION}adminuser/changeZone.action",
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
