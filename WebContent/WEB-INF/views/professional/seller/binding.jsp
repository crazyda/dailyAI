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
						<p>你当前的位置：[业务管理]-[商家绑定]</p>
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
     <form action="<%=basePath %>professional/seller/bindingSave" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>当前用户</label>
			<span class="input">
				${adminUser.username }
			</span>
			<div class="clear"></div>
		</div>
		<c:if test="${adminUser.sellerId!=null }">
		<div class="rows">
			<label>已绑定商家名称</label>
			<span class="input">
			  ${seller.name }
			</span>
			<div class="clear"></div>
		</div>
		</c:if>
		<c:if test="${adminUser.sellerId==null }">
		 <div class="rows">
			<label>说明</label>
			<span class="input">
				<font color="red">商家只允许绑定一次，请谨慎处理!</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>绑定商家方式</label>
			<span class="input">
			  <input type="radio" name="isNewSeller" id="isNewSeller" value="0" />新增商家
			  <input type="radio" name="isNewSeller" id="isNewSeller" value="1" />搜索绑定
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>商家名称</label>
			<span class="input">
			  <input type="text" name="sellerName" id="sellerName" value="" /><font color="red">*请输入需要创建或绑定的商家名</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button" onclick="savePtype();" value="确认绑定" />
			</span>
			<div class="clear"></div>
		</div>
		</c:if>
		<c:if test="${adminUser.sellerId!=null }">
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button" onclick="if(!confirm('您确认要解除绑定吗？'))return false; else savePtype();" value="解除绑定" />
			  <input type="hidden" name="sellerId" id="sellerId" value="${adminUser.sellerId }" />
			</span>
			<div class="clear"></div>
		</div>
		</c:if>
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
   function savePtype(){
	      if('${adminUser.sellerId}'==''&&sellerName==""){
	         alert('请输入商家名称!');
	         return;
	      }
	     if('${adminUser.sellerId}'==''){
	    	 var isNewSeller=$('input:radio[id="isNewSeller"]:checked').val();
	         if(isNewSeller==null||isNewSeller==''){
	        	 alert('请选择绑定方式!');
		         return;
	         }
	     }
	     
	     $.ajax({
             type: 'POST',
             url: 'bindingSave',
             data: $('#saveForm').serialize(),
             async: false,
             dataType:'json',
             error: function (data) {
                 alert("提交失败");
             },
             success: function (data) {
            	 if(data.status==1){
            		 alert(data.message);
            		 window.location.href = "${BASEPATH_IN_SESSION}/professional/seller/binding";
            	 }else{
            		 alert(data.message);
            		 return;
            	 }
             }
         });
   }

</script>
</body>
</html>
