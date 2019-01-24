<%@page import="com.axp.dao.impl.AdminUserDAOImpl"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="com.axp.model.AdminUser"%>
<%@page import="com.axp.dao.AdminUserDAO"%>
<%@page import="com.axp.model.CashshopType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
/* AdminUserDAO adao = new AdminUserDAOImpl();
AdminUser user = null;
user = adao.findById(current_user_id); */
Object o = request.getAttribute("cashshopType");
CashshopType sc = null;
String imageurls = null;
String imageurls1 = null;
if(o!=null){
   sc = (CashshopType)o;
   imageurls = sc.getImg();
   imageurls1 = sc.getImg2();
  
}
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

</style>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>

</head>

<body>
<div class="div_new_content">
<div style="margin-left:10px;">

                    <div class="header-left">
	                    <c:if test="${scoreExtend.id==null }">
	                    	<p>你当前的位置：[商城管理]-[图文管理]-[新增推广积分券]</p>
	                    </c:if>
						<c:if test="${scoreExtend.id!=null }">
	                    	<p>你当前的位置：[商城管理]-[图文管理]-[编辑积分券]</p>
	                    </c:if>	
					</div>
     <form action="<%=basePath%>sellerWithdraw/edit?id=''" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
			 <input name="id" type="hidden" id="id" value="">
			 <input type=hidden id="imageurls" name="img" value="" />
			 <input type=hidden id="imageurls1" name="img2" value="" />
			 <div class="rows">
					<label> 推广积分券名称：</label>
					<span class="input">
					  <input type=text name="name" id="name"  value="${scoreExtend.name }"/><font color=red>*</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 单券面额：</label>
					<span class="input">
						<input type=text name="money" id="money"  value="${scoreExtend.money }"/><font color=red>*</font>
						
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 推广数量：</label>
					<span class="input">
						<input type=text name="extendNum" id="extendNum"  value="${scoreExtend.extendNum }"/><font color=red>*</font>
						
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 积分券说明：</label>
					<span class="input">
						<textarea cols=30 rows=10 name="remark" id="remark">${scoreExtend.remark }</textarea><font color=red></font>
						
					</span>
					<div class="clear"></div>
			</div>
			
			
			<div class="rows" id="clickrows" <c:if test="">style="display: none"</c:if>>
						<label>积分券的封面:</label>
						<span class="input"><br/>
			                <iframe name="if_upload1" id="if_upload1" src="<%=basePath %>imagehandle/showupload.action?uploadtype=22" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
			                <div id="div_img1" style="display:none">
			                      <img src ="" id="img_id1" />
			                </div>
						</span>
						<div class="clear"></div>
			</div>
			 
			<div class="rows">
				<label></label>
				<span class="input">
				<input type="hidden" name="typeid" id="typeid">
				  <input type="button" class="button" onclick="savePtype();" value="添 加" />
				</span>
				<div class="clear"></div>
			</div>
			
			
				</div>
			</div>
        
        </form>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script>

function changeType(){

	   var typeId = document.getElementById("typeId").value;
	   var clickrows = document.getElementById("clickrows");
		if(typeId<4){
			clickrows.style.display="";
		}else{
			clickrows.style.display="none";
		}
}

   function savePtype(){
	   var remark = document.getElementById("remark").value;
	   var cover = document.getElementById("imageurls").value;
	   var url = document.getElementById("url").value;
		/* if(remark==""){
			alert("请输入说明");
	    	  return;
		}
		 */
		 document.getElementById("saveForm").submit();
   }
   function setImageUrlTure(src,url,type){
		if(type==21){
			$("#div_img").html(src);
			 $("#div_img").show();

			 $("#imageurls").attr("value",url);
		}else if(type==22){
			$("#div_img1").html(src);
			 $("#div_img1").show();

			 $("#imageurls1").attr("value",url);
		}
		 
	}
	function setImgurl(url,type){
			if(type==21){
				url = "cashshop_type/"+url;
				var url1 = "${RESOURCE_LOCAL_URL }"+url;
			       var src = "&nbsp;<img src=\""+url1+"\"  ondblclick=\"delImg('"+url+"',21);\" alt='双击删除图片' title='双击删除图片' />";
				   setImageUrlTure(src,url,type);
			}else if(type==22){
				url = "cashshop_type_click/"+url;
				var url1 = "${RESOURCE_LOCAL_URL }"+url;
			       var src = "&nbsp;<img src=\""+url1+"\"  ondblclick=\"delImg('"+url+"',22);\" alt='双击删除图片' title='双击删除图片' />";
				   setImageUrlTure(src,url,type);
			}
	}

	function delImg(url,type){
		if(type==21){
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
		       <%if(sc!=null){%>
			   		messageservice.delImg(message.id,url,1,callback_delimg);
				<%}%>
			   setImgurls(strs,type);
		}else if(type==22){
			var imageurls = $("#imageurls1").attr("value");
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
			  
			   $("#div_img1").html("");
			   $("#imageurls1").attr("value","");
		       <%if(sc!=null){%>
			   		messageservice.delImg(message.id,url,1,callback_delimg);
				<%}%>
			   setImgurls(strs,type);
		}
	   
   }
  

   function setImgurls(imageurls,type){
	  if(type==21){
		  var src = "&nbsp;<img src=\"${RESOURCE_LOCAL_URL }"+imageurls+"\"  ondblclick=\"delImg('"+imageurls+"',21);\" alt='双击删除图片' title='双击删除图片' />";
	      //alert('src======='+src);
	      setImageUrlTure(src,imageurls,type);
	  }else if(type==22){
		  var src = "&nbsp;<img src=\"${RESOURCE_LOCAL_URL }"+imageurls+"\"  ondblclick=\"delImg('"+imageurls+"',22);\" alt='双击删除图片' title='双击删除图片' />";
	      //alert('src======='+src);
	      setImageUrlTure(src,imageurls,type);
	  }
      
	}


    <%if(imageurls!=null && imageurls.length()>0){%>

       setImgurls("<%=imageurls%>",21);
   	<%}%>
   	<%if(imageurls1!=null && imageurls1.length()>0){%>

    setImgurls("<%=imageurls1%>",22);
	<%}%>
   function showType(t){
	   
	   switch(t){
		   case 1:
			   document.getElementById("div_gift").style.display="block";
			   break;
		   case 2:
			   document.getElementById("div_gift").style.display="none";
			   break;
	   
	   }
	   curr_type = t;
   }
   

   
  
</script>
</body>
</html>
