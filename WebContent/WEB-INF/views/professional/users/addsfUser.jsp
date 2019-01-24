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
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/messages_cn.js"></script>

<title>新增收分用户</title>
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
	                	<c:if test="${result.id ==null}">
	                	<p>你当前的位置：[业务管理]-[收分用户]-[新增收分用户]</p>
	                	</c:if>
	                	
						<c:if test="${result.id !=null}">
	                		<p>你当前的位置：[业务管理]-[收分用户]-[修改收分用户]</p>
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
     <form action="<%=basePath %>/professional/user/saveUser" id="saveForm" method="post">
     <input type="hidden" name = "adId" value="0"/>
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>用户名</label>
			<span class="input">
			<input type="hidden" id="id" name ="id" value="${result.id }">
			<input type=text name="loginname" id="loginname" value="${result.loginname }"  onchange="doCheckName(true)" /> <font color="red">*请输入字母或数字组合!密码默认为888888，请及时修改</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>联系人</label>
			<span class="input">
			  <input type=text name="contacts" id="contacts" value="${result.contacts }" /><font color="red">*</font>
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
			<label>预留手机号</label>
			<span class="input">
			  <input type=text name="phone" id="phone" value="${result.phone }"  /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>收分佣金下限</label>
			<span class="input">
			  <input type=text name="totalMoney" id="totalMoney" value="${result.totalMoney }"/><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
			
		<div class="rows">
			<label></label>
			<span class="input">
			 	<c:if test="${result.id ==null}">
               
               	 <input type=button class="button" onclick="savePtype();" value="添 加" />
               	</c:if>
               	
				<c:if test="${result.id !=null}">
               		 <input type=button class="button" onclick="savePtype();" value="修 改" />
               	</c:if>
			 
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


   function savePtype(){
	  
	   var name = document.getElementById("loginname").value;
	   var contacts = document.getElementById("contacts").value;
	   var phone = document.getElementById("phone").value;
	   
	      
	     if(contacts==""){
	         alert('请输入联系人!');
	         return;
	      }
	     
	     if(phone==""){
	         alert('请输入联系电话!');
	         return;
   
	    }
	     
	     document.getElementById("saveForm").submit(); 
   }
    function doCheckphone(as){
   	//1、获取帐号
   	var phone = $("#phone");
	   	$.ajax({
	   		url:"<%=basePath%>professional/user/checkphoneByAjax",
	   		type:"post",
	   		data:{"phone":phone.val(),"type":1},	
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
 //校验商家名称唯一性
   function doCheckName(as){
   	//1、获取帐号
   	var loginname = $("#loginname").val();
   	//2、根据商家名称查询商家表是否存在记录，如果存在记录说明该帐号已经存在不可使用，否则不存在记录说明可以使用该帐号
   	$.ajax({
   		url:"<%=basePath%>professional/user/checkAdminUserNameByAjax",
   		type:"post",
   		data:{"loginname":loginname},	
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
	     
</script>
</body>
</html>
