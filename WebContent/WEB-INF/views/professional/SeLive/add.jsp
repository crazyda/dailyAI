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

SeLive se = null;
String logo=null;
String sellerLogo = null;
String imgRecommend = null;
String imgRe=null;
String image=null;
String img=null;
String name = "";
if(o!=null){
   
	se = (SeLive)o;
	sellerLogo = se.getSellerLogo();
    image=se.getImage();
    img=se.getImage();
    imgRecommend = se.getImgRecommend();
    imgRe=se.getImgRecommend();
    logo=se.getSeller().getLogo();
   	name = se.getSellerName();
}


%>
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
<script type="text/javascript" src="<%=basePath %>js/coordtransform.js"></script>
<script type="text/javascript" src="<%=basePath %>js/maputil.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>新增直播信息</title>
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
						<p>你当前的位置：[业务管理]-[直播管理]-[添加直播信息]</p>
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
     <form action="<%=basePath %>/professional/seLive/save?id=${seLive.id}" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      		
			<div class="rows">
					<label> 店铺（主播）名称 :</label>
					<span class="input">
					  <input type="text" name="sellerName" id="sellerName" value="${seLive.sellerName }" />   <span style="color: red">*</span>
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows"  style="height:200;">
					<label>店铺（主播）头像 : </label>
					<span class="input">
					  <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=24" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
		                <div style="position:absolute;z-index:2;">
		                	<img id="div_img2"  src="${RESOURCE_LOCAL_URL}${seLive.sellerLogo==null?seller.logo:seLive.sellerLogo}" style="padding-left:4px;display:'';width:100;height:100;" onclick="delImg('<%=basePath+sellerLogo%>');" alt='双击删除图片' title='双击删除图片'>
		                </div>       
		                <div id="div_img">
		                      <img src ="" id="img_id" />
		                </div>
					</span>
					<div class="clear"></div>
				</div>
		        
			<div class="rows">
					<label> 店铺（主播）地址 :</label>
					<span class="input">
					  <input style="width: 350px;" type=text name="sellerAddress" id="address" value="${seLive.sellerAddress }" />   <span style="color: red"></span>
					</span>
					<div class="clear"></div>
		        </div>
			<div class="rows" >
					<label>店铺（主播）简介 ：</label>
					<span class="input" >
			  			<textarea rows="30" cols="10" name="remark" id="remark" >${seLive.remark}</textarea>
					</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
					<label> 直播名称 :</label>
					<span class="input">
					  <input type=text name="livename" id="livename" value="${seLive.livename }" />
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label> 直播地址 :</label>
					<span class="input">
					  <textarea rows="30" cols="10" name="liveUri" id="outWebsite"  >${seLive.liveUri }</textarea>微信系统中生成的网页地址，以http 开头
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>直播时间段：</label>
					<span class="input">
				  		<input class="login-input-username" type="text" id="begintime" name="begintime" value="${seLive.begintime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"  />
				  		~
				  		<input class="login-input-username" type="text" id="endtime" name="endtime" value="${seLive.endtime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"  />
					</span>
					<div class="clear"></div>
			</div>
		<div class="rows" style="height:220;">
			<label>店铺（主播）广告图片 : </label>
			<span class="input">
				<span>
				<font color="red">*点击“选择文件”，上传主播广告图片</font><br>
			    <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=26" height=80  FRAMEBORDER=0 SCROLLING=no> </iframe>
		            <div style="position:absolute;z-index:2;">
		                <img id="div_img5"  src="${RESOURCE_LOCAL_URL}${seLive.imgRecommend}" style="padding-left:4px;display:'';width:100;height:100;" onclick="delImg1('<%=basePath+imgRecommend%>');" alt='双击删除图片' title='双击删除图片'>
		                </div>
		            <div id="div_img3" class="img3" >
	                      <img src ="" id="img_id3" />
	                </div>           
		         </span>
			</span>
			<div class="clear"></div>
		</div>

		<div class="rows" style="height:220;">
			<label>直播底层图片 : </label>
			<span class="input">
				<span>
				<font color="red">*点击“选择文件”，上传直播底层图片URL</font><br>
			    <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=27" height=80  FRAMEBORDER=0 SCROLLING=no> </iframe>
		            <div style="position:absolute;z-index:2;">
		                <img id="div_img6"  src="${RESOURCE_LOCAL_URL}${seLive.image}" style="padding-left:4px;display:'';width:100;height:100;" onclick="delImg2('<%=basePath+image%>');" alt='双击删除图片' title='双击删除图片'>
		            </div>
		            <div id="div_img4">
	                      <img src ="" id="img_id4" />
	                </div>           
		         </span>
			</span>
			<div class="clear"></div>
		</div>
		
		<div class="rows">
					<label> 是否首页显示 :</label>
					<span class="input">
					  <input type="radio" name="istop" id="istop" value="1" <c:if test='${seLive.istop }'>selected="selected"</c:if> />是
					   <input type="radio" name="istop"  value="0" <c:if test='${seLive.istop==false }'>selected="selected"</c:if> />否
					</span>
					<div class="clear"></div>
		        </div>
		
		<div class="rows">
			<label></label>
			<span class="input">
			
	
			 <input type="button" class="button" onclick="savePtype();" value="添加" />
		
			 
			</span>
			<div class="clear"></div>
		</div>
      </div>
     </div>
    	<input type=hidden id=sId name="sId" value="${seller.id}" />
     	<input type=hidden id="logo" name="logo" value="${seller.logo} }" />
        <input type=hidden id="sellerLogo" name="sellerLogo" value="${seLive.sellerLogo}" />
        <input type=hidden id="imgRecommend" name="imgRecommend" value="${seLive.imgRecommend}" />
        <input type=hidden id="image" name="image" value="${seLive.image}" />
        <input type=hidden id="imgRe" name="imgRe" value="${seLive.imgRecommend}" />
        <input type=hidden id="img" name="img" value="${seLive.image}" />
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
    document.getElementById("saveForm").submit();
 }


function setImageUrlTure(src,url,type){

	    switch(parseInt(type,10)){
	        case 24:
	           $("#div_img").html(src);
	           $("#div_img").show();
	           $("#div_img2").show();
	   		   $("#logo").attr("value",url);
	   			url=url.replace('nomal','160');
	   			$("#sellerLogo").attr("value",url);
	    	   break;
	        case 25:
	           $("#div_img").html(src);
	           $("#div_img").show();
	   		   $("#sellerLogo").attr("value",url);
		       break;
	        case 26:
		   		$("#div_img3").html(src);
		        $("#div_img3").show();
		        $("#div_img5").show();
		   		$("#imgRe").attr("value",url);
		   		url=url.replace('nomal','160');
		   		$("#imgRecommend").attr("value",url);
		    	break;
	        case 28:
		           $("#div_img3").html(src);
		           $("#div_img3").show();
		   		   $("#imgRecommend").attr("value",url);
			       break;
	        case 27:
	        	$("#div_img4").html(src);
	        	$("#div_img4").show();
	        	$("#div_img6").show();
	        	$("#img").attr("value",url);
	   			url=url.replace('nomal','160');
		   		$("#image").attr("value",url);
		   		break;
	        case 29:
		           $("#div_img4").html(src);
		           $("#div_img4").show();
		   		   $("#image").attr("value",url);
			       break;	
	    }
		 
	}
	function setImgurl(url,type){

	  switch(parseInt(type,10)){
	      case 24:
	    	  $('img#div_img2').parent().hide();
	    	  url2 = "seller_logo/"+url;
	    	  url = "${RESOURCE_LOCAL_URL}seller_logo/"+url;
	          var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	   	     setImageUrlTure(src,url2,type);
		     break;
	      case 25:
	    	  url2 = "seller_logo/"+url;
	    	  url = "${RESOURCE_LOCAL_URL}seller_logo/"+url;
	          var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	   	      setImageUrlTure(src,url2,type);
	    	  break;
	      case 26:
	    	  $('img#div_img5').parent().hide();
	    	  url2 = "seller_view/"+url;
	    	  url = "${RESOURCE_LOCAL_URL}seller_view/"+url;
	          var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg1('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	   	      setImageUrlTure(src,url2,type);
	    	  break;
	      case 28:
	    	  url2 = "seller_view/"+url;
	    	  url = "${RESOURCE_LOCAL_URL}seller_view/"+url;
	          var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg1('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	   	      setImageUrlTure(src,url2,type);
	    	  break;
	      case 27:
	    	  $('img#div_img6').parent().hide();
	    	  url2 = "banner_ad/"+url;
	    	  url = "${RESOURCE_LOCAL_URL}banner_ad/"+url;
	          var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg2('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	   	      setImageUrlTure(src,url2,type);
	    	  break;
	      case 29:
	    	  url2 = "banner_ad/"+url;
	    	  url = "${RESOURCE_LOCAL_URL}banner_ad/"+url;
	          var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg2('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	   	      setImageUrlTure(src,url2,type);
	    	  break;
	  
	  }
       
	}

	function delImg(url){
	   var imageurls = $("#logo").attr("value");
	   var iarr = imageurls.split(",");
	   var strs = "";
	   for(i=0;i<iarr.length;i++){
          var s = iarr[i];
          if(s==url || s==""){
             continue;
           }
          strs = strs + s+",";
	   }
	   $("#div_img").html("");
	   $("#div_img2").hide();
	   $("#logo").attr("value","");
	   
	   setImgurls(strs);
   }
	function delImg1(url){
		   var imageurls = $("#imgRe").attr("value");
		   var iarr = imageurls.split(",");
		   var strs = "";
		   for(i=0;i<iarr.length;i++){
	          var s = iarr[i];
	          if(s==url || s==""){
	             continue;
	           }
	          strs = strs + s+",";
		   }
		   $("#div_img3").html("");
		   $("#div_img5").hide();
		   $("#imgRe").attr("value","");
		   
		   setImgurls(strs);
	   }
	function delImg2(url){
		   var imageurls = $("#img").attr("value");
		   var iarr = imageurls.split(",");
		   var strs = "";
		   for(i=0;i<iarr.length;i++){
	          var s = iarr[i];
	          if(s==url || s==""){
	             continue;
	           }
	          strs = strs + s+",";
		   }
		   $("#div_img4").html("");
		   $("#div_img6").hide();
		   $("#img").attr("value","");
		   
		   setImgurls(strs);
	   }
 
   function setImgurls(imageurls,url,type){
	  
      var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
      setImageUrlTure(src,imageurls,type);
	}
   function setImgurls1(imageurls,url,type){
		  
	      var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg1('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	      setImageUrlTure(src,imageurls,type);
		}
   function setImgurls2(imageurls,url,type){
		  
	      var src = "&nbsp;<img src=\""+url+"\" style=\"width:100;height:100;\" ondblclick=\"delImg2('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	      setImageUrlTure(src,imageurls,type);
		}

   <%
   if(logo!=null && logo.length()>0){
 %>
     setImgurls("<%=logo%>","${RESOURCE_LOCAL_URL}<%=logo%>",24);
 <%}%>
 	
 	<%
     if(sellerLogo!=null && sellerLogo.length()>0){
   %>
       setImgurls("<%=sellerLogo%>","${RESOURCE_LOCAL_URL}<%=sellerLogo%>",25);
   <%}%>
   
   <%
   if(img!=null && img.length()>0){
 %>
     setImgurls("<%=img%>","${RESOURCE_LOCAL_URL}<%=img%>",27);
 <%}%>
 <%
 if(image!=null && image.length()>0){
%>
   setImgurls("<%=image%>","${RESOURCE_LOCAL_URL}<%=image%>",29);
<%}%>

   <%
   if(imgRe!=null && imgRe.length()>0){
 %>
     setImgurls("<%=imgRe%>","${RESOURCE_LOCAL_URL}<%=imgRe%>",26);
 <%}%>
 
 <%
 if(imgRecommend!=null && imgRecommend.length()>0){
%>
   setImgurls("<%=imgRecommend%>","${RESOURCE_LOCAL_URL}<%=imgRecommend%>",28);
<%}%>

	
</script>
</body>
</html>
