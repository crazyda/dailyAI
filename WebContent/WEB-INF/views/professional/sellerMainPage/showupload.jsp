<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.opensymphony.xwork2.util.*"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object filepath = request.getAttribute("filepath");
String uploadtype = request.getParameter("uploadtype");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>


<title></title>


</head>

<body>

<form id="imageForm" method="post" name="imageForm" onsubmit="return checkFileExt();" enctype="multipart/form-data">
  <table align=center style="height:30px;">
  <tr>
     <td align=center>
       <input type="file" name="imagefile" id="imagefile"   width="150px;" onchange="uploadFile();">
       
       <div id="div_loading" style="display:none;font-size:12px;">
		    <img src="<%=basePath %>images/loading.gif"  alt="加载数据中..." />&nbsp;&nbsp;&nbsp;请等待图片上传 ...
		</div>
     </td>
  </tr>
  
  <tr>
     <td align=center>
  <span style="font-size:12px;color:#999;text-align:center" id="span_type">
      图片大小500*400 &lt;&nbsp;2M
  </span>   
     </td>
  </tr>
  </table>
  <input type="hidden" name="file_ext" id="file_ext" value="jpg"/>
  <input type="hidden" name="uploadtype" value="<%=uploadtype %>"/>
</form>

<script>
//判断是否为IE浏览器： /msie/i.test(navigator.userAgent) 为一个简单正则
var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
function checkFileExt(){
		var filename= document.getElementById("imagefile").value;
        var fileSize = 0;
        if (isIE && !filename) {    // IE浏览器
            var filePath = filename; // 获得上传文件的绝对路径
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            var file = fileSystem.GetFile(filePath);
            fileSize = file.Size;    // 文件大小，单位：b
        }
        else {    // 非IE浏览器
            fileSize = document.getElementById("imagefile").files[0].size;
        }
        var size = fileSize / 1024 / 1024;
        if (size > 0.5) {
            alert("附件不能大于500K");
            return false;
        }

	   
    
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
    if(filename=="jpg" || filename=="gif" || filename=="png" || filename=="jpeg"){
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
       document.getElementById("imageForm").action="<%=basePath%>professional/image/uploadimg";
       document.getElementById("imageForm").submit();
    }
 }
 <%
  if(filepath!=null){
 %>
 	parent.imgurl("<%=filepath%>");
 <%}%>
 
</script>
</body>
</html>

