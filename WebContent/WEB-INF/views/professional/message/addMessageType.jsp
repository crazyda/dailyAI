<%@page import="com.axp.model.MessageType"%>
<%@page import="com.axp.model.SeLive"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Levels"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object o = request.getAttribute("sc");

MessageType mt = null;
String logo=null;
String icon = null;
String title = "";
if(o!=null){
   
	mt = (MessageType)o;
	icon = mt.getIcon();
    logo=mt.getIcon();
   	title = mt.getTitle();
}

%>
<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="<%=basePath %>js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/coordtransform.js"></script>
<script type="text/javascript" src="<%=basePath %>js/maputil.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/layer3/layer.js"></script>

<link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>新增消息类别</title>
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

<body >
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
						<p>你当前的位置：[业务管理]-[消息类别管理]-[添加消息类别]</p>
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
     <form action="<%=basePath %>system/message/save?id=${mt.id}" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
				<div class="rows">
					<label><font color="red">*</font>类别名称:</label>
					<span class="input">
					  <input type="text" name="title" id="title" value="${mt.title}" /> <span style="color: red">*</span>
					</span>
					<div class="clear"></div>
		        </div>
		      
				 <div class="rows">
                        <label> <font color="red">*</font>类别图标：</label>
						<span class="input"> 
							<wh:UploadUtil fileDirName="message_icon" thumbH="100" thumbW="100" amount="5"
                                           submitName="iconPic" targetExt="jpg" relatedChar="${mt.icon}">尺寸为：200px*200px</wh:UploadUtil>
                            
						</span>
                        <div class="clear"></div>
                    </div>
		       <div class="rows">
				<label>所属类别：</label>
				<span class="input">
				  <select name="level" id="level">
				 	<option value="-1" >父类</option>
					  		<c:forEach items="${mlist }" var="message">
		                  		<c:if test="${empty message.messageType }">
		                  			<option value="${message.id }">${message.title}</option>
		                  		</c:if>
					  		</c:forEach>
	               </select>
				</span>
				<div class="clear"></div>
		</div>
			<div class="rows">
				<label></label>
				<span class="input">
				 <input type="button" class="button" onclick="savePtype();" value="保存" />
				</span>
				<div class="clear"></div>
		</div>
      </div>
     </div>
     <input type="hidden" name="isorder" value="${message.isorder}" />
     </form> 
     </td>
  </tr>

</table>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script type="text/javascript">

function savePtype(){
	var title = document.getElementById("title").value;
	var icon = $("input[name='iconPic']");
    if(title==""){
       alert("请输入类型名称!");
       return;
    }
    if (icon == null || icon.size() == 0) {
        alert("请上传图标!");
        return;
    }
    document.getElementById("saveForm").submit();
 }
 

 
 
</script>
</body>
</html>
