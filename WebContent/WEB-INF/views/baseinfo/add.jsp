<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Baseinfos"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object o = request.getAttribute("sc");

Baseinfos sc = null;
if(o!=null){
   
   sc = (Baseinfos)o;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

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

<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">

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
						<p>你当前的位置：[系统管理]-[关于我们]</p>
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
      <form action="<%=basePath %>baseinfo/save.action" id="saveForm" method="post">
         <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				<div class="rows">
					<label> 微网站链接：</label>
					<span class="input">
					  <input type=text name="linkurl" style="width:350px;" id="linkurl" value="<%=sc==null?"":sc.getLinkurl() %>" />
					</span>
					<div class="clear"></div>
				</div>
				<div class="rows">
					<label>关于我们：</label>
					<span class="input">
					  <textarea rows=20 cols=50 name="content"><%=sc==null?"":sc.getContent() %></textarea>
					</span>
					<div class="clear"></div>
				</div>
				<div class="rows" style="height:200px;">
					<label>许可协议：</label>
					<span class="input" style="height:200px;">
					  <textarea rows=50 cols=50 style="height:180px;" name="agreement"><%=sc==null?"":sc.getAgreement() %></textarea>
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
        <input type=hidden id="id" name="id" value="<%=sc==null?-1:sc.getId() %>" />
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
   
      document.getElementById("saveForm").submit();
   }

</script>
</body>
</html>
