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
	                    <c:if test="${cashshopType.id==null }">
	                    	<p>你当前的位置：[商城管理]-[图文管理]-[新增图文信息]</p>
	                    </c:if>
						<c:if test="${cashshopType.id!=null }">
	                    	<p>你当前的位置：[商城管理]-[图文管理]-[编辑图文信息]</p>
	                    </c:if>	
					</div>
     <form action="<%=basePath%>cashShop/cashShopType/save" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
			 <input name="id" type="hidden" id="id" value="${cashshopType.id }">
			 <input type=hidden id="imageurls" name="img" value="" />
			 <input type=hidden id="imageurls1" name="img2" value="" />
			 <div class="rows">
					<label> 显示名称：</label>
					<span class="input">
					  <input type=text name="name" id="name"  value="${cashshopType.name }"/><font color=red>最多5个中文字符*</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 说明：</label>
					<span class="input">
						<textarea cols=30 rows=10 name="remark" id="remark">${cashshopType.remark }</textarea><font color=red>显示位置的描述（必填）*</font>
					</span>
					<div class="clear"></div>
			</div>
			
			<div class="rows">
					<label> 所属类型：</label>
					<span class="input">
					<select  name="typeId" id="typeId"  onchange="changeType()">
						<c:forEach items="${typeList }" var="type">
								<option value=${type.id } <c:if test="${ type.id == typeId}" >selected="selected"</c:if> >${type.name }</option>
						</c:forEach>
					</select>
					</span>
					<div class="clear"></div>
			</div>

			<div class="rows">
						<label>图片:</label>
						<span class="input"><br/>
			                <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload.action?uploadtype=21" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
			                <div id="div_img" style="display:none">
			                      <img src ="" id="img_id" />
			                </div>
						</span>
						<div class="clear"></div>
			</div>
			<div class="rows" id="clickrows" <c:if test="${cashshopType.img2==null }">style="display: none"</c:if>>
						<label>图片(商城按钮按下状态):</label>
						<span class="input"><br/>
			                <iframe name="if_upload1" id="if_upload1" src="<%=basePath %>imagehandle/showupload.action?uploadtype=22" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
			                <div id="div_img1" style="display:none">
			                      <img src ="" id="img_id1" />
			                </div>
						</span>
						<div class="clear"></div>
			</div>
			 <div class="rows">
					<label> 跳转链接：</label>	
					<span class="input">		
					  <input type="text" name="url" id="url" value="${cashshopType.url }"><font color="red" >1：留空则不保存,请保证链接带有“http”或“https”前序（如果留空则不跳转:2淘客专区链接需要增加?from=axp的参数拼接（例如:http://www.518wtk.com/?from=axp）：</font>
					</span>
					<div class="clear"></div>
			</div>	
			
			<div class="rows" >
				<label>商品类别：</label>
				
				<span class="input">
					一级
					<select  name="parentType" id="parentTypeId" onchange="changeTypes();gotoselect()">
						<option value=0>请选择</option>
						<c:forEach items="${clist }" var="ctype">
							<option value="${ctype.id }" <c:if test="${ctype.id == commodityType.commodityType.id }">selected="selected"</c:if>>${ctype.name}</option>
						</c:forEach>
					</select>
					二级
					<select  name="subType" id="subTypeId" onchange="changeTypes();gotoselect()">
						<option value=0>请选择</option>
						<option value="${commodityType.id }" <c:if test="${commodityType.id == commodityType.id }">selected="selected"</c:if>>${commodityType.name}</option>
				</select> 
				
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
		if(remark==""){
			alert("请输入说明");
	    	  return;
		}
		/* if(cover==""){
			alert("请上传图片");
	    	  return;
		} */
		
		if(url!=""){
			if(url.substring(0,4)!='http'&&url.substring(0,5)!='https'){
				alert("链接地址前缀必须带有“http”或者“https”");
				return;
			}
		}
		
		var typeid  = $("input#typeid");
		var parentType = $("select#parentTypeId").find("option:selected").text();
		var subType = $("select#subTypeId").find("option:selected").text();
		 if(subType && subType!='请选择'){
			   $("input#uri").val(subType);
			   typeid.val($("select#subTypeId").val());
		   }
		   else if(parentType && parentType!='请选择'){
			   $("input#uri").val(parentType);
			   typeid.val($("select#parentTypeId").val());
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
   
   function changeTypes(){
		  var ad = event.srcElement;
		  var parentType = document.getElementById("parentTypeId");
		  var subType = document.getElementById("subTypeId");
				if(parseInt(ad.value)>0){
					if(ad==parentType){
						subType.options.length=0;				
					}
					$.ajax({
						type:"post",
						url:"${BASEPATH_IN_SESSION}cashShop/cashShopType/changeType.action",
						data: "typeid="+ad.value,        
				        dataType: "text",
				        success: function (data){
				        	var json=eval( '('+data+ ')' );
			    	    	 if(ad==parentType){
			    	    		 subType.options.add(new Option("请选择", ""));
			    	    	 }
			    	    	 for(var i in json){	
			    	    		 if(ad==parentType){
			    	    			 subType.options.add(new Option(json[i],i));
			    	    		 }
			    	    	 }
				
				        }
					})
				}
				
		}
</script>
</body>
</html>
