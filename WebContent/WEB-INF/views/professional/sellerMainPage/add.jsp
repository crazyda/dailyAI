<%@page import="com.axp.dao.impl.AdminUserDAOImpl"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="com.axp.model.AdminUser"%>
<%@page import="com.axp.dao.AdminUserDAO"%>
<%@page import="com.axp.model.SellerMainPage"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@	taglib prefix="s" uri="/struts-tags"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");

Object o = request.getAttribute("sellerMainPage");
SellerMainPage sc = null;
String imageurls = null;
String imageurls1 = null;
String imageurls2 = null;
String imageurls3 = null;
if(o!=null){
   sc = (SellerMainPage)o;
   imageurls = sc.getSellerLogo();
   imageurls1 = sc.getTopCarouselAd();
   imageurls2 = sc.getSellerView();
   imageurls3 = sc.getBannerAd();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
	<!-- 
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=FhqpjmNavgBUzcjqOlHfeqrR"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script> -->
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
<title>新增后台用户</title>
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
<script type="text/javascript">

</script>
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
						<p>你当前的位置：[业务管理]-[店铺主页管理]-[编辑主页]</p>
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
     <form action="<%=basePath %>professional/sellerMainPage/save" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      <div class="rows">
			<label>店铺名称</label>
			<span class="input">
			<input type="hidden" id="id" name ="id" value="${seller.id }">
			<input type="hidden" id="pageId" name ="pageId" value="${sellerMainPage.id }">
			<font>${seller.name }</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>店铺名LOGO：</label>
			<span class="input">
				<span>
				<font color="red">大于140*140px</font><br>
			    <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=24" height=80  FRAMEBORDER=0 SCROLLING=no> </iframe>
		            <div id="div_img1"">
					</div>	           
		         </span>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows" >
			<label>店铺介绍</label>
			<span class="input" >
			  <textarea rows="30" cols="10" name="remark" id="remark" >${sellerMainPage.remark}</textarea>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>营业时间：</label>
			<span class="input">
			  <input class="login-input-username" type="text" id="beginTime" name="beginTime" value="${sellerMainPage.beginTime}" onClick="WdatePicker({dateFmt:'HH:mm'});"  />
			 	~
			  <input class="login-input-username" type="text" id="endTime" name="endTime" value="${sellerMainPage.endTime}" onClick="WdatePicker({dateFmt:'HH:mm'});"  />
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>顶部轮播广告:</label>
			<span class="input">
			<font color="red">限750*450px</font><br>
				<iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=25" height=80  FRAMEBORDER=0 SCROLLING=no> </iframe>
				<div id="div_img"></div>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>店铺视频:</label>
			<span class="input">
				<span>
				<font color="red">*点击“选择文件”，上传视频封面和输入视频URL</font><br>
			    <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=26" height=80  FRAMEBORDER=0 SCROLLING=no> </iframe>
		            <div id="div_img2" >
					</div>	           
		         </span>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>横幅广告:</label>
			<span class="input">
				<span>
				<font color="red">限750*200px</font><br>
			    <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=27" height=80  FRAMEBORDER=0 SCROLLING=no> </iframe>
		            <div id="div_img3">
					</div>	           
		         </span>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type="button" class="button" onclick="savePtype();" value="添 加" /><img alt="" src="">
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
<!-- 
<script type="text/javascript" src="<%=basePath %>js/maputil.js"></script> -->
<script>
	var count = 0;
   function savePtype(){   
      var beginTime = document.getElementById("beginTime").value;
	  var endTime = document.getElementById("endTime").value;
	  if(beginTime==null||beginTime==""||beginTime==undefined){
		  alert("请选择营业时间");
		  return ;
	  }
	  if(endTime==null||endTime==""||endTime==undefined){
		  alert("请选择营业时间");
		  return ;
	  }
	   $('input[name="url"]').each(function(index,url){
	    	  if($(url).val()==null||$(url).val()==""){
	    		  $(url).val("NOCHAR");
	    	  }
	  });
	
      document.getElementById("saveForm").submit();
   }
   
   
	
      
      
   
  function imgurl(url){
	  url2 =  "top_carousel_ad/"+url;
      url = "${RESOURCE_LOCAL_URL}top_carousel_ad/"+url;
      var src = "&nbsp;<span><img src=\""+url+"\"  width=\"140px\" height=\"140px\" ondblclick=\"delPic('"+url+"');\" alt='双击删除图片' title='双击删除图片' />"+
      "&nbsp;链接到:<input type=\"text\" name=\"url\"  ><br></span>";
     
      if(count<5){
	 	 imageUrlTure(src,url2);
      }
  }
  
  function imageUrlTure(src,url){
		 $("#div_img").append(src);
		 $("#div_img").show();
		 $("#imgurls").val(url);
	}
  
	function delPic(url){
		 var imageurls = $("#imgurls").attr("value");
		 var iarr = imageurls.split(",");
		 var strs = "";
		 for(i=0;i<iarr.length;i++){
	       	var s = iarr[i];
	       	if(s!=url || s!=""){
	    	   	strs = strs + s+",";
	       	} 	
		 }
		 count--;
		 $("img[src=\"${RESOURCE_LOCAL_URL}"+url+"\"]").parent("span").remove();
		 $("#imgurls").attr("value",strs);
	 }
////////////////////////////////////////////////////////////////////////////////////	
	  function setImageUrlTure(src,url,type){
	  
		    if(type==24){
		    
		    	var img_url = $($(src).find("img")).attr("src");
				// 创建对象  
				var img = new Image();  
				// 改变图片的src  
				img.src = img_url;  
				img.onload = function(){
			    // 打印
			    if(img.width<140||img.height<140){
			    	alert("上传图片必须大于140px*140px");
			    	return false;
			    }
				$("#div_img1").html(src);
			 	$("#imageurls1").attr("value",url);
			}
			}else if(type==26){
				$("#div_img2").html(src);
				 $("#imageurls2").attr("value",url);
			}else if(type==27){
				$("#div_img3").html(src);
				 $("#imageurls3").attr("value",url);
			}
		
		// 打印  
	  }
			
		
	  
		function setImgurl(url,type){
				if(type==24){
					url = "seller_logo/"+url;
					var url1 = "${RESOURCE_LOCAL_URL}"+url;
				    var src = "<span><img src=\""+url1+"\" width=\"140px\" height=\"140px\" ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' /><input type=\"hidden\" id=\"imageurls1\" name=\"imageurls1\" value=\"\" /></span>";
					setImageUrlTure(src,url,type);
				}if(type==25){
					url = "top_carousel_ad/"+url;
					var url1 = "${RESOURCE_LOCAL_URL}"+url;
				    var src = "<span><img src=\""+url1+"\" width=\"100px\" height=\"100px\" ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' />"+
				    "&nbsp;链接到:<input type=\"text\" name=\"url\" value=\"\" ><input type=\"hidden\" name=\"imgurls\" value=\""+url+"\" ><br></span>";
				    imageUrlTure(src,url);
				}else if(type==26){
					url = "seller_view/"+url;
					var url1 = "${RESOURCE_LOCAL_URL}"+url;
				    var src = "<span><img src=\""+url1+"\" width=\"100px\" height=\"100px\" ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' />"+
				    "&nbsp;视频链接URL:<input type=\"text\" name=\"viewurl\" value=\"\" ><input type=\"hidden\" id=\"imageurls2\" name=\"imageurls2\" value=\""+url+"\" /></span>";
					setImageUrlTure(src,url,type);
				}else if(type==27){
					url = "banner_ad/"+url;
					var url1 = "${RESOURCE_LOCAL_URL}"+url;
				    var src = "<span><img src=\""+url1+"\" width=\"100px\" height=\"100px\" ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' />"+
				    "&nbsp;链接到:<input type=\"text\" name=\"bannerurl\" value=\"\" ><input type=\"hidden\" id=\"imageurls3\" name=\"imageurls3\" value=\""+url+"\" /></span>";
					setImageUrlTure(src,url,type);
				}
		       
		}

		function delImg(objest){
			$(objest).parent().remove();
	   }
		function setImgurls(imageurls,type){
			  if(type==24){
				  var src = "<span><img src=\"${RESOURCE_LOCAL_URL}"+imageurls+"\" width=\"140px\" height=\"140px\"  ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' /><input type=\"hidden\" id=\"imageurls1\" name=\"imageurls1\" value=\"\" /></span>";
				  
			      setImageUrlTure(src,imageurls,type);
			  }else if(type==25){ 
				  var json1=imageurls; 
				  for(var i=0;i<json1.length;i++){
					  url2 =  json1[i].img;
				      url = "${RESOURCE_LOCAL_URL}"+json1[i].img;
				      var src = "<span><img src=\""+url+"\"  width=\"100px\" height=\"100px\" ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' />"+
				      "&nbsp;链接到:<input type=\"text\" name=\"url\" value=\""+json1[i].url+"\" ><input type=\"hidden\" name=\"imgurls\" value=\""+url2+"\" ><br></span>";
				      imageUrlTure(src,url2);
				  }       
			  }else if(type==26){
				  var json2=imageurls; 
				  url2 =  json2[0].img;
				  if(url2==''){
					  return;
				  }
			      url = "${RESOURCE_LOCAL_URL}"+json2[0].img;
			      var src = "<span><img src=\""+url+"\"  width=\"100px\" height=\"100px\" ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' />"+
			      "&nbsp;视频链接URL:<input type=\"text\" name=\"viewurl\" value=\""+json2[0].url+"\" ><input type=\"hidden\" id=\"imageurls2\" name=\"imageurls2\" value=\"\" /><br></span>";
			      setImageUrlTure(src,json2[0].img,type);
			  }else if(type==27){
				  var json3=imageurls; 
				  url2 =  json3[0].img;
				  if(url2==''){
					  return;
				  }
			      url = "${RESOURCE_LOCAL_URL}"+json3[0].img;
 			      var src = "<span><img src=\""+url+"\"  width=\"100px\" height=\"100px\" ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' />"+
			      "&nbsp;链接到:<input type=\"text\"  name=\"bannerurl\" value=\""+json3[0].url+"\" ><input type=\"hidden\" id=\"imageurls3\" name=\"imageurls3\" value=\"\" /><br></span>";
			      setImageUrlTure(src,json3[0].img,type);
			  }
		      
			}

		<%if(imageurls!=null && imageurls.length()>0){%>
			
			setImgurls("<%=imageurls%>",24);
	   	<%}%>
	   	<%if(imageurls1!=null && imageurls1.length()>0){%>
	   		setImgurls(<%=imageurls1%>,25);
	   	
	   	<%}%>
	   	<%if(imageurls2!=null && imageurls2.length()>0){%>
   			setImgurls(<%=imageurls2%>,26);
   	
   		<%}%>
   		<%if(imageurls3!=null && imageurls3.length()>0){%>
			setImgurls(<%=imageurls3%>,27);
	
		<%}%>
	
</script>
</body>
</html>
