<%@page import="com.axp.model.ImageType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object o = request.getAttribute("sc");

ImageType sc = null;
if(o!=null){
   sc = (ImageType)o;
}


%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
<title>用户标准列表</title>
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
                  	<c:if test="${type.id==null }">						
                  		<p>你当前的位置：[商城管理]-[商城图文类别管理]-[新增图文信息]</p>
                  	</c:if>
                  	<c:if test="${type.id!=null }">						
                  		<p>你当前的位置：[商城管理]-[商城图文类别管理]-[编辑图文信息]</p>
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
      <form action="<%=basePath %>cashShop/imageType/save" id="saveForm" method="post">
          <div id="products" class="r_con_wrap">
	 <div class="r_con_form" >
		<div class="rows">
			<label>类型名称：</label>
			<span class="input">
			  <input type=text name="name" id="name" value="<%=sc==null?"":sc.getName() %>" />
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>所属商城：</label>
			<span class="input">
			  <select name="type">
                  <option value=-1>--请选择--</option>
				  <option value=1 <% if(sc!=null&&sc.getType()==1){%>selected="selected"<% } %> >城市精品</option>
				  <option value=2 <% if(sc!=null&&sc.getType()==2){%>selected="selected"<% } %> >促销特惠</option>
				  <option value=3 <% if(sc!=null&&sc.getType()==3){%>selected="selected"<% } %> >会员专区</option>
                </select>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
				<label></label>
				<span class="input">
				  <input type=button class="button" onclick="savePtype();" value="<%=sc==null?" 添 加":"修 改" %>" />
				</span>
				<div class="clear"></div>
			</div>
		</div>
		</div>
        
        <input type=hidden id="id" name="id" value="<%=sc==null?"":sc.getId() %>" />
        </form>
     </td>
  </tr>
 
</table>
</div>
<div style="width:100%;height:20px;">
</div>
</div>

<script>
   function savePtype(){
   
      var name = document.getElementById("name").value;
      if(name==""){
         alert('请输入类型名称!');
         return;
      }
      
      document.getElementById("saveForm").submit();
   }

</script>
</body>
</html>
