<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.axp.model.Slides" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object o = request.getAttribute("sc");

Slides sc = null;
String imgurls=null;
String linkurl="";
String imgurls2 = null;
if(o!=null){
   
   sc = (Slides)o;
   imgurls = request.getServletContext().getAttribute("RESOURCE_LOCAL_URL")+sc.getImgurls();
   imgurls2 = sc.getImgurls();
   linkurl = sc.getLinkurl();
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
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>


</head>

<body>
<div class="div_new_content">
<div style="margin-left:10px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
                  
                    <div class="header-left">
                    	<c:if test="${id==null}">
                    		<p>你当前的位置：[商城管理]-[商城广告]-[新增商城广告]</p>
                    	</c:if>
						<c:if test="${id!=null}">
                    		<p>你当前的位置：[商城管理]-[商城广告]-[编辑商城广告]</p>
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
      <form action="<%=basePath %>cashShop/slide/save" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
		 <div class="r_con_form" >
		 <div class="rows">
			<label>级别</label>
			<span class="input">
			<select name="type">
			   <c:forEach items="${typelist}" var="types">
			  		 <option  value="${types.key }" <c:if test="${types.key==slides.type }">selected='selected'</c:if>>${types.value }</option>
			 	</c:forEach>
			  </select> <font color="red">*</font> 
			</span>
			<div class="clear"></div>
		</div>
			<div class="rows">
				<label>图片:</label>
				<span class="input">
				    <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=4" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
	                <div id="div_img" style="display:none">
	                      <img src ="" id="img_id" />
	                </div>
	                <font color="red">*上传现金商城、商家详情页幻灯片的广告图请确保尺寸为：750*230 </font>
				</span>
				<div class="clear"></div>
		     </div>
		     <div class="rows">
				<label>链接地址:</label>
				<span class="input">
				   <input type=text value="${sc.linkurl}" name="linkurl" id="linkurl" />
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
        <input type=hidden id="id" name="id" value="<%=sc==null?"":sc.getId() %>" />
        <input type=hidden id="imgurls" name="imgurls" value="" />
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
   	  var img = $("#imgurls").val();
   	 var linkurl = $("#linkurl").val();
   	  if(img==null||img==undefined||img==""){
   		  alert("请选择上传图片");
   		  return ;
   	  }
   	  if(linkurl==null||linkurl==undefined||linkurl==""){
 		  alert("请填写链接地址");
 		  return ;
 	  }
      document.getElementById("saveForm").submit();
   }
   
   function setImageUrlTure(src,url){

		 $("#div_img").html(src);
		 $("#div_img").show();

		 $("#imgurls").attr("value",url);
	}
	function setImgurl(url){
		   url2 =  "slide/"+url;
	       urls = "${RESOURCE_LOCAL_URL}slide/"+url;
	       var src = "&nbsp;<img src=\""+urls+"\"  ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	       
		   setImageUrlTure(src,url2);
	}

	function delImg(url){
	   var imageurls = $("#imgurls").attr("value");
	   var iarr = imageurls.split(",");
	   var strs = "";
	   for(i=0;i<iarr.length;i++){
        var s = iarr[i];
        if(s==url || s==""){
           continue;
         }
        strs = strs + s+",";
	   }
	  // $("#imageurls").attr("value",strs);
	  
	   $("#div_img").html("");
	   $("#imgurls").attr("value","");
      <% if(sc!=null){%>
	   messageservice.delImg(<%=sc.getId()%>,url,1,callback_delimg);
	   <%}%>
	   setImgurls(strs);
 }
 function callback_delimg(res){
      // alert('res======='+res);
      
 }

 function setImgurls(imageurls){
	  
    var src = "&nbsp;<img src=\""+imageurls+"\"  ondblclick=\"delImg('"+imageurls+"');\" alt='双击删除图片' title='双击删除图片' />";
    //alert('src======='+src);
    setImageUrlTure(src,imageurls);
	}

 <%
   if(imgurls!=null && imgurls.length()>0){
 %>
 	url = "<%=imgurls2%>";
	 urls = "<%=imgurls%>";
	 var src = "&nbsp;<img src=\""+urls+"\"  ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	 
	 setImageUrlTure(src,url);
 <%}%>

</script>
</body>
</html>
