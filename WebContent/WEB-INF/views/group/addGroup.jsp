<%@page import="com.axp.dao.impl.AdminUserDAOImpl"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="com.axp.model.AdminUser"%>
<%@page import="com.axp.dao.AdminUserDAO"%>
<%@page import="com.axp.model.ItalkGroup"%>
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
Object o = request.getAttribute("ItalkGroup");
ItalkGroup sc = null;
String imageurls = null;
String imageurls1 = null;
if(o!=null){
   sc = (ItalkGroup)o;
   imageurls = sc.getImgUrl();
   //imageurls1 = sc.getImg2();
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
	                    
	                    	<p>你当前的位置：[系统管理]-[群管理]-[创建群]</p>
	                    
					</div>
     <form action="<%=basePath%>system/group/createGroup" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
			 <input name="id" type="hidden" id="id" value="${cashshopType.id }">
			 <input type=hidden id="imageurls" name="img" value="" />
					 <div class="rows">
					<label> 群名称：</label>
					<span class="input">
					  <input type=text name="groupName" id="groupName"  value=""/><font color=red>最多5个中文字符*</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 群公告：</label>
					<span class="input">
						<textarea cols=30 rows=10 name="groupNotice" id="groupNotice"></textarea><font color=red>显示位置的描述（必填）*</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 群信息：</label>
					<span class="input">
						<textarea cols=30 rows=10 name="groupInfo" id="groupInfo"></textarea><font color=red>显示位置的描述（必填）*</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 所属类型：</label>
					<div class="line2">
							<select name="level" id="level">
								
								<option value="2" >事业合伙人</option>
								<option value="3" >合伙人</option>
						 	</select>
						</div> 
					<div class="clear"></div>
			</div>

			<div class="rows">
						<label>群头像:</label>
						<span class="input"><br/>
			                <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload.action?uploadtype=21" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
			                <div id="div_img" style="display:none">
			                      <img src ="" id="img_id" />
			                </div>
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
	   var groupNotice = document.getElementById("groupNotice").value;
	   var cover = document.getElementById("imageurls").value;
	  // var url = document.getElementById("url").value;
		if(groupNotice==""){
			alert("请输入群公告");
	    	  return;
		}
		if(groupName==""){
			alert("请输入群名称");
	    	  return;
		}
		if(groupInfo==""){
			alert("请输入群信息");
	    	  return;
		}
		
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
   function callback_delimg(res){
        // alert('res======='+res);
        
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
