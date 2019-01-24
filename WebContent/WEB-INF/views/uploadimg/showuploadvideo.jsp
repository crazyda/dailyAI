<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.opensymphony.xwork2.util.*;"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack"); 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String filepath = vs.findString("filepath");
String uploadtype = vs.findString("uploadtype");
String mvtime = vs.findString("mvtime");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>


<title></title>


</head>

<body>

<s:form theme="simple" id="imageForm" method="post" name="imageForm" onsubmit="return checkFileExt();" enctype="multipart/form-data">
  <table align=center style="height:30px;">
  <tr>
     <td align=center>
       <s:file theme="simple" name="imagefile" id="imagefile" width="150px;" onchange="uploadFile();"></s:file>
       
       <div id="div_loading" style="display:none;font-size:12px;">
		    <img src="<%=basePath %>images/loading.gif"  alt="加载数据中..." />&nbsp;&nbsp;&nbsp;请等待视频上传 ...
		</div>
     </td>
  </tr>
  
  <tr>
     <td align=center>
  <span style="font-size:12px;color:#999;text-align:center" id="span_type">
             仅支持MP4 文件，小于100M
  </span>   
     </td>
  </tr>
  </table>
  <input type=hidden name=file_ext id=file_ext value="mp4"/>
  <input type=hidden name=uploadtype value="<%=uploadtype %>"/>
  <input type=hidden name=mvtime value="<%=mvtime %>"/>
</s:form>

<script>

function checkFileExt(){
	   
    var filename= document.getElementById("imagefile").value;
    if(filename.length==0){
        var vobj = document.getElementById("span_type");
        vobj.style.color = "#FF9797";
        return false;
    }
    if(filename.lastIndexOf('.')==-1){
       var vobj = document.getElementById("span_type");
       vobj.style.color = "#FF9797";
       return false;
    }
    filename = filename.substring(filename.lastIndexOf('.')+1);
    
    filename = filename.toLowerCase();
    if(filename=="mp4" || filename=="flv" || filename=="swf") {
       document.getElementById("file_ext").value = filename;
       return true;
    }
    
    
    var vobj = document.getElementById("span_type");
    vobj.style.color = "#FF9797";
    
    return false;
 }
 
 function uploadFile(){
    if(checkFileExt()){
       //parent.showLoading();
       $("#div_loading").show();
       document.getElementById("imageForm").action="<%=basePath%>uploadimg/uploadvideo.action";
       document.getElementById("imageForm").submit();
    }
 }

 <%
  if(filepath!=null){
 %>
   
     parent.setUrl("<%=filepath%>","<%=uploadtype%>","<%=mvtime%>");
   		   
 <%}%>

 
</script>
</body>
</html>

