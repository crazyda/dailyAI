
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
             建议JPG格式，仅支持JPG图片文件，且文件小于500K
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
            /**
             * ActiveXObject 对象为IE和Opera所兼容的JS对象
             * 用法：
             *         var newObj = new ActiveXObject( servername.typename[, location])
             *         其中newObj是必选项。返回 ActiveXObject对象 的变量名。
             *        servername是必选项。提供该对象的应用程序的名称。
             *        typename是必选项。要创建的对象的类型或类。
             *        location是可选项。创建该对象的网络服务器的名称。
             *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
             *     Scripting.FileSystemObject 为 IIS 内置组件，用于操作磁盘、文件夹或文本文件，
             *    其中返回的 newObj 方法和属性非常的多
             *    如：var file = newObj.CreateTextFile("C:\test.txt", true) 第二个参表示目标文件存在时是否覆盖
             *    file.Write("写入内容");    file.Close();
             */
            var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
            // GetFile(path) 方法从磁盘获取一个文件并返回。
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
 
 function uploadFile1(){
    if(checkFileExt()){
       //parent.showLoading();
       $("#div_loading").show();
       document.getElementById("imageForm").action='${RESOURCE_LOCAL_URL}fileHandle/upload';
       document.getElementById("imageForm").submit();
    }
 }
 
 function uploadFile() {
	 if(checkFileExt()){
		 $("#div_loading").show();
	     var formData = new FormData($( "#imageForm" )[0]);
	   
	     $.ajax({
	           url: '${RESOURCE_LOCAL_URL}fileHandle/upload',  
	           type: 'POST', 
	           data: formData,
	           async: false, 
	           cache: false, 
	           contentType: false,
	           processData: false, 
	           //url: '${RESOURCE_LOCAL_URL}fileHandle/up', 
	           dataType: 'json',   
	         
	          success: function (json) {
	        	 // alert(json.filepath);
	        	  parent.setImgurl(json.filepath,json.uploadtype);
	        	  $("#div_loading").hide();
	          }
	         ,
	          error: function (returndata) {
	              alert("上传失败");
	          } ,
	         error : function(XMLHttpRequest, textStatus, errorThrown) {
	        	//这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
	        	alert(XMLHttpRequest.responseText); 
	        	alert(XMLHttpRequest.status);
	        	alert(XMLHttpRequest.readyState);
	        	alert(textStatus); // parser error;
	        	}
	     });
	 }
}

 <%
  if(filepath!=null){
 %>
     parent.setImgurl("<%=filepath%>","<%=uploadtype%>");
    

 <%}%>

 
</script>
</body>
</html>

