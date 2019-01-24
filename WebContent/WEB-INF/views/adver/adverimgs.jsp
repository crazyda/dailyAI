<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Goods"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="com.axp.dao.IGoodsDao"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object o = request.getAttribute("sc");

Goods sc = null;
String adver_imageurls = null;
String adver_imageurls_small = null;
String name = "";
int adver_status = 0;
String coin ="1";
int adver_imgurl_size = 0;
String checkstr = "";
if(o!=null){
   
   sc = (Goods)o;
   adver_imageurls = sc.getAdverImgurls();
   
   adver_imageurls_small = sc.getAdverImgurlsSmall();
   name = sc.getName();
   coin = sc.getPlaytotal()==null?"1":sc.getPlaytotal().toString();
   adver_status = sc.getAdverStatus();
   adver_imgurl_size = sc.getAdverImgurlSize();
   checkstr = StringUtil.getNullValue(sc.getCheckstr());
}

String adver_status_str="审核通过";
if(adver_status==0){
	
	adver_status_str = "等待管理员审核";
}else if(adver_status==-1){
	adver_status_str = "审核不通过";
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">

<title>兑换商品列表</title>
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
						<p>你当前的位置：[系统管理]-[兑换商品管理]-[添加广告图片]</p>
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
      <form action="<%=basePath %>adver/saveAdverimgs" id="saveForm" method="post">
      
          <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				<div class="rows">
					<label> 名称:</label>
					<span class="input">
					  <%=name %>
					</span>
					<div class="clear"></div>
				</div>
				<div class="rows"  style="height:540;">
					<label>广告图片(普通):</label>
					<span class="input">
					  <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=2" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
		                <div style="position:absolute;z-index:2;"><img id="div_img2"  src="<%=basePath %>images_new/adverpng.png" style="padding-left:4px;display:none;width:250;height:450;" onclick="delImg('<%=basePath+adver_imageurls_small%>');" alt='双击删除图片' title='双击删除图片'></div>       
		                <div id="div_img" style="display:none;">
		                      <img src ="" id="img_id" />
		                </div>
		                (640×1136)
					</span>
					<div class="clear"></div>
				</div>
                <div class="rows">
					<label> 状态：</label>
					<span class="input">
					   <% if(adver_status==1){ %>
			                <span style="font-weight:bold;color:green">
			                <%=adver_status_str %>
			                </span>
		                <% }else{ %>
		                    <span style="font-weight:bold;color:red">
			                  <%=adver_status_str %>
			                </span>
		                <% } %>
					</span>
					<div class="clear"></div>
				</div>
		  <% if(!"".equals(checkstr)){ %>
			<div class="rows">
				<label>审批说明:</label>
				<span class="input">
				  <%=checkstr %>
				</span>
				<div class="clear"></div>
	        </div>	
           <% } %>
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
        <input type=hidden id="adver_imageurls" name="adver_imageurls" value="" />
        <input type=hidden id="adver_imageurls_small" name="adver_imageurls_small" value="" />
        <input type=hidden name="adver_imgurl_size" style="width:80px;" value="0" />
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
   
   function setImageUrlTure(src,url,type){

	    switch(parseInt(type,10)){
	        case 2:
	           $("#div_img").html(src);
	           $("#div_img").show();
	           $("#div_img2").show();
	   		   $("#adver_imageurls").attr("value",url);
	   			url=url.replace('nomal','160');
	   			$("#adver_imageurls_small").attr("value",url);
	    	   break;
	        case 6:
	           $("#div_img_small").html(src);
	   		   $("#div_img_small").show();
	   		   $("#adver_imageurls_small").attr("value",url);
		       break;
	    }
		 
	}
	function setImgurl(url,type){

	  switch(parseInt(type,10)){
	      case 2:
	    	  url2 = "goods_adver/"+url;
	    	  url = "${RESOURCE_LOCAL_URL}goods_adver/"+url;
	          var src = "&nbsp;<img src=\""+url+"\" style=\"width:250;height:450;\" ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	   	     setImageUrlTure(src,url2,type);
		     break;
	      case 6:
	    	  url2 = "goods_adver_small/"+url;
	    	  url = "${RESOURCE_LOCAL_URL}goods_adver_small/"+url;
	          src = "&nbsp;<img src=\""+url+"\" style=\"width:250;height:450;\" ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	   	      setImageUrlTure(src,url2,type);
	    	  break;
	  
	  }
       
	}

	function delImg(url){
	   var imageurls = $("#adver_imageurls").attr("value");
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
	   $("#div_img2").hide();
	   $("#adver_imageurls").attr("value","");
	   setImgurls(strs);
   }

   function setImgurls(imageurls,url,type){
	  
      var src = "&nbsp;<img src=\""+url+"\" style=\"width:250;height:450;\" ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
      //alert('src======='+src);
      setImageUrlTure(src,imageurls,type);
	}

   <%
     if(adver_imageurls!=null && adver_imageurls.length()>0){
   %>
       setImgurls("<%=adver_imageurls%>","${RESOURCE_LOCAL_URL}<%=adver_imageurls%>",2);
   <%}%>
   
   <%
   if(adver_imageurls_small!=null && adver_imageurls_small.length()>0){
 %>
     setImgurls("<%=adver_imageurls_small%>","${RESOURCE_LOCAL_URL}<%=adver_imageurls_small%>",6);
 <%}%>

</script>
</body>
</html>
