<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/shop.css" rel="stylesheet" type="text/css">

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

<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-1.9.1.min.js"></script>
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
						<p>你当前的位置：[广告管理]-[广告素材管理]-[添加广告素材]</p>
					</div>
                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table></td>
  </tr>
  <c:if test="${sc!=null}">
  <tr><td>
  <div style="width:90%;height:30px;color:red;margin-left:10px;font-size:12px;">
  注：每次修改需要重新审核！
  </div>
  </td></tr>
  </c:if>
  <tr>
     <td>
      <form action="${BASEPATH_IN_SESSION}adver/save?id=${sc.id}" id="saveForm" method="post">
         <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				<div class="rows">
					<label> 名称:</label>
					<span class="input">
					  <input type=text name="name" id="name" value="${sc.name }" />
					</span>
					<div class="clear"></div>
		        </div>
	            <input type="hidden" value =2 name="type"/>
	            <div class="rows">
					<label>第三方链接:</label>
					<span class="input">
						<input type="text" name="outWebsite" id="outWebsite" value="${sc.outWebsite }" />
					</span>
					<div class="clear"></div>
		         </div>
                 <div class="rows">
					<label>描述:</label>
					<span class="input">
					  <textarea cols=30 rows=10 name="description" id="description">${sc.description }</textarea>
					</span>
					<div class="clear"></div>
		         </div>
		         <input type="hidden" name="website_id" id="website_id" value="1">
		       
          
	            <div class="rows">
					<label></label>
					<span class="input">
					  <input type=button class="button" onclick="savePtype();" value="添 加" />
					</span>
					<div class="clear"></div>
				</div>
				</div>
			</div>
        <input type=hidden id="imageurls" name="imageurls" value="" />
        </form>
     </td>
  </tr>
 
</table>

</div>
<div style="width:100%;height:20px;">
</div>
</div>

<script>
   var curr_type='${sc.type}';
   var u_score = 100;
   var per_adverscore = '${per_adverscore}';
   function savePtype(){
	   var name = document.getElementById("name").value;
	      if(name==""){
	         alert('请输入礼品名称!');
	         return;
	      }else if(name.length>10){
	    	  alert('名称最长不能超过10');
	          return;
	      }
      document.getElementById("saveForm").submit();
   }
   
   function setImageUrlTure(src,url){

		 $("#div_img").html(src);
		 $("#div_img").show();

		 $("#imageurls").attr("value",url);
	}
	function setImgurl(url){
		
	       url = "${BASEPATH_IN_SESSION}goods/"+url;
	       var src = "&nbsp;<img src=\""+url+"\"  ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
		   setImageUrlTure(src,url);
	}

	function delImg(url){
	   var imageurls = $("#imageurls").attr("value");
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
	   $("#imageurls").attr("value","");
       if('${sc}'!=''){
	   	messageservice.delImg('${sc.id}',url,1,callback_delimg);
	   }
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

   if('${sc.imgurls}'!=''){
	   setImgurls('${sc.imgurls}');
   }

</script>
</body>
</html>
