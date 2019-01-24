<%@page import="com.axp.model.Cashshop"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>

<title>新增商品标签信息</title>
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
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>


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
                    <c:if test="${lable.id==null}">	
      					<p>你当前的位置：[商城管理]-[商品标签管理]-[新增标签信息]</p>
                    </c:if>
                    <c:if test="${lable.id!=null}">	
      					<p>你当前的位置：[商城管理]-[商品标签管理]-[编辑标签信息]</p>
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
     <form action="<%=basePath%>cashShop/goodsLable/lablesave" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				<div class="rows">			
					<label> 商品标签名称：</label>
					<span class="input">
					  <input type=text name="name" id="name"  value="${lable.name}" onchange="doCheckName(true)"/><font color=red>*</font>
					</span>
					<div class="clear"></div>
				</div>
				<div class="rows">			
					<input type=hidden id="lableId" name="lableId" value="${lable.id}" />
					<div class="clear"></div>
				</div>			
				<div class="rows">
					<label></label>
					<span class="input"  balign="center" style:"margin-left:50px">
					  <input type="button" class="button" onclick="savePtype()" value="添 加" />
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
<script>
 <%--   function savePtype(){
	   var name = document.getElementById("name").value;
	      if(name==""){
	    	  alert("请输入标签名称");
	    	  return;
	      }
	      window.location.href='<%=basePath%>cashShop/goodsLable/lablesave'; 
      document.getElementById("saveForm").submit();
   } --%>
</script>
<script>
 var verifyResult =false;
//校验商家名称唯一性
   function doCheckName(as){
   	//1、获取商标名称
   	var $name = $("#name");
   	var $id = $("#lableId");
   	if($id.val() == "" || $id.val() == undefined || $id.val() == null){
   		$id.val("0");
   	}else{
   		var $id = $("#lableId");
   	}
   /*	alert("+"+$id.val());*/
   	//2、根据商品标签表是否存在记录，如果存在记录说明该标签已经存在不可用，否则不存在记录说明添加新的标签
   	$.ajax({
   		url:"${basePath}checkNameByAjax",
   		type:"post",
   		data:{"name":$name.val(),"lableId":$id.val()},	
   		async:as,//异步属性，默认值为true
   		success:function(msg){
   			var json=msg;
   			alert(json);
   			if("true" != json){
   				//标签重复，不能添加
   				alert("该商品标签已存在，请输入其它商品标签名称！");
   				$("#name").focus();
   				verifyResult = false;
   			} else {
   				verifyResult = true;
   			}
   		}
   	});
   }
   </script>
</body>
</html>
