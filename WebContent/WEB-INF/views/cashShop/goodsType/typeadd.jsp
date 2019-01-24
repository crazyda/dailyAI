<%@page import="com.axp.model.Cashshop"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="s" uri="/struts-tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">


<title>图片</title>
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
#local{
	transform: scale(2);
}

</style>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>


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
                    	<c:if test="${type.id==null}">
                    		<p>你当前的位置：[商城管理]-[商品分类管理]-[新增商品分类信息]</p>
                    	</c:if>
                    	<c:if test="${type.id!=null}">
                    		<p>你当前的位置：[商城管理]-[商品分类管理]-[编辑商品分类信息]</p>
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
     <form action="<%=basePath%>cashShop/goodsType/typesave" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
			 	<%-- <div class="rows">
			 		<label>	所属模块:</label>
			 		<span class="input">
			 			<select name="modelId" id="modelId" onchange="seleceModel(this)">
			 				<option value="">请选择</option>
			 				<c:forEach items="${modules }" var="m">
			 					<option value="${m.key }" <c:if test="${ m.key == commodity_type.modelId}" >selected="selected"</c:if> >${m.value }</option>
			 				</c:forEach>
			 			</select>
			 			<font color=red>*</font>
			 		</span>
			 	</div> --%>
			 
				<div class="rows">
					<label> 分类名：</label>
					<span class="input">
					  <input type=text name="name" id="name"  value="${commodity_type.name }"/><font color=red>*</font>
					</span>
					<div class="clear"></div>
				</div>
				<div class="rows">
					<label>上级分类：</label>
					<span class="input" >
					<select  name="typeParentId" id="typeParentId" >
						<option value="">无</option>
							<c:forEach items="${typeList }" var="type">
								<option value=${type.id } <c:if test="${ type.id == commodity_type.commodityType.id}" >selected="selected"</c:if> >${type.name }</option>
							</c:forEach>
						</select>
					</span>
					<div class="clear"></div>
				</div>
				<div class="rows">
					<label>图片：</label>
					<span class="input">
					    <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=16" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>

		                    <div id="div_img" style="display:none">
			                      <img src ="" id="img_id" width="100px" height="100px" />
			                </div>
		                
		                <input type=hidden id="imgurls" name="imgurls" value="${commodity_type.imgUrl}" />
		                <input type=hidden id="typeId" name="typeId" value="${commodity_type.id}" />
					</span>
					<div class="clear"></div>
				</div>
				
				<div class="rows">
					<label> 是否特产：</label>
					<span class="input">
					  <input type="checkbox"  name="local" id="local"  value="1"
					  	<c:if test="${commodity_type.isLocal==true}" >checked="checked"</c:if>
					   />
					 
					</span>
					<div class="clear"></div>
				</div>
				<div class="rows">
					<label></label>
					<span class="input">
					  <input type="button" class="button" onclick="savePtype();" value="添 加" />
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
<script>
   function savePtype(){
	   var name = document.getElementById("name").value;
	      if(name==""){
	    	  alert("请输入分类名称");
	    	  return;
	      }
	      var imgurls = document.getElementById("imgurls").value;
	      if(imgurls==""){
	    	  alert("请上传图片");
	    	  return;
	      }
	      
      document.getElementById("saveForm").submit();
   }
   
   function setImageUrlTure(src,url){
		 $("#div_img").html(src);
		 $("#div_img").show();

		 $("#imgurls").attr("value",url);
	}
	function setImgurl(url){
		   url2 = 'commodity_type/'+url;
	       url = '${RESOURCE_LOCAL_URL}commodity_type/'+url;
	       var src = "&nbsp;<img src=\""+url+"\"  ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
	       
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
	   $("#div_img").style.display="none";
	   $("#imgurls").attr("value","");
      if(goods !=null){
	   messageservice.delImg(goods.id,url,1,callback_delimg);
	   }
	   setImgurls(strs);
 }
 function callback_delimg(res){
      // alert('res======='+res);
      
 }

 function setImgurls(imageurls,url){
    var src = "&nbsp;<img src=\""+url+"\"  ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
    //alert('src======='+src);
    setImageUrlTure(src,imageurls);
	}
	 var img = document.getElementById("imgurls").value;


 	if(img != ""){

	setImgurls(img+"",'${RESOURCE_LOCAL_URL}'+img);
	}
function seleceModel(selectNode){
	var modelId = selectNode.value;
	var modelId = 1;
	typeParentId.length = 1;
	$.get("<%=basePath %>cashShop/goodsType/getTypeByModelId?modelId="+modelId,function(json){
		$.each(json,function(i,d){
			$("#typeParentId").append("<option value='"+d.id+"'>"+d.name+"</option>");
		});	
	});	
}
	
	$(function(){
		var modelId = 1;
		typeParentId.length = 1;
		$.get("<%=basePath %>cashShop/goodsType/getTypeByModelId?modelId="+modelId,function(json){
			$.each(json,function(i,d){
				$("#typeParentId").append("<option value='"+d.id+"'>"+d.name+"</option>");
			});	
		});	
	});

</script>
</body>
</html>
