<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@	taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/shop.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css">

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
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>

</head>

<body>
<div class="div_new_content">
<div style="margin-left:10px;">

                    <div class="header-left">
						<c:if test="${message.id==null}">
	                	<p>你当前的位置：[业务管理]-[消息中心]-[新增消息]</p>
	                	</c:if>
						<c:if test="${message.id !=null}">
	                	<p>你当前的位置：[业务管理]-[消息中心]-[修改消息]</p>
	                	</c:if>
					</div>
     <form action="${BASEPATH_IN_SESSION}messageCenter/save" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
			 <input name="id" type="hidden" id="id" value="${message.id }">
			 <input type=hidden id="imageurls" name="cover" value="" />
			 <div class="rows">
					<label> 标题：</label>
					<span class="input">
					  <input type=text name="title" id="title"  value="${message.title }"/><font color=red>*</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 作者：</label>
					<span class="input">
					  <input type=text name="author" id="author"  value="${message.author }"/><font color=red>*</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 说明：</label>
					<span class="input">
						<textarea cols=30 rows=10 name="remark" id="remark">${message.remark }</textarea><font color=red>*</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
					<label> 投放位置：</label>
					<span class="input">
						<select name="type" id="type" onchange="typeAjax(this)" >
							<option value="0" <c:if test="${message.type==null||message.type==0 }">selected="selected"</c:if>>消息中心</option>
					   <!-- <option value="1" <c:if test="${message.type==1 }">selected="selected"</c:if>>新人攻略</option>
							<option value="2" <c:if test="${message.type==2 }">selected="selected"</c:if>>会员攻略</option>
							<option value="3" <c:if test="${message.type==3 }">selected="selected"</c:if>>抢拍须知</option>
							<option value="4" <c:if test="${message.type==4 }">selected="selected"</c:if>>抢拍协议</option> -->
							<option value="10" <c:if test="${message.type==10 }">selected="selected"</c:if>>安装协议</option>
							<option value="20" <c:if test="${message.type==20 }">selected="selected"</c:if>>必看攻略</option>
							<option value="30" <c:if test="${message.type==30 }">selected="selected"</c:if>>关于每天积分</option>
							<option value="40" <c:if test="${message.type==40 }">selected="selected"</c:if>>收不到验证码</option>
						</select>&nbsp;&nbsp;
						<font color="red">*会员攻略、新人攻略默认只显示最新的消息</font>
					</span>
					<div class="clear"></div>
			</div>
			<div class="rows">
						<label>封面:</label>
						<span class="input">
						   (图片尺寸：600*300)<br/>
			                <iframe name="if_upload" id="if_upload" src="${BASEPATH_IN_SESSION}imagehandle/showupload?uploadtype=17" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
			                <div id="div_img" style="display:none">
			                      <img src ="" id="img_id" />
			                </div>
						</span>
						<div class="clear"></div>
			</div>
				
			 
			 <div class="rows">
					<label>内容编辑：</label>
					<span class="input">
			     		<!-- 加载编辑器的容器 -->
    					<script id="container" name="content" type="text/plain" style="height:500px;width:99.3%" >${message.content }</script>
    					<input name="info" type="hidden" id="info">
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
//var ue = UE.getEditor('container');

//编辑器资源文件根路径 最好在ueditor.config.js中配置
window.UEDITOR_HOME_URL = "../ueditor/";
//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
var ue = UE.getEditor('container');
//复写UEDITOR的getActionUrl 方法,定义自己的Action
UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
UE.Editor.prototype.getActionUrl = function(action) {
    if (action == 'uploadimage' || action == 'uploadfile') {
    	var dir = 'upload-res/ueditor/message/1/';
    	return '${RESOURCE_LOCAL_URL}uHandle/upload?dir='+dir;//图片上传初始化
    } else {
        return this._bkGetActionUrl.call(this, action);//编辑器初始化
    }
};

   function savePtype(){

	   var title = document.getElementById("title").value;
	   var author = document.getElementById("author").value;
	   var remark = document.getElementById("remark").value;
	   var cover = document.getElementById("imageurls").value;
		if(title==""){
			alert("请输入标题");
	    	  return;
		}
		if(author==""){
			alert("请输入发布人");
	    	  return;
		}
		if(remark==""){
			alert("请输入说明");
	    	  return;
		}
		if(cover==""){
			alert("请上传封面");
	    	  return;
		}
	      if(!UE.getEditor('container').hasContents()){
	    	  alert("请输入编辑内容");
	    	  return;
	      }
	
      document.getElementById("saveForm").submit();
   }
   
   function typeAjax(type){
   		if(parseInt($(type).val())>0){
   			$.ajax({
	   		url:"${BASEPATH_IN_SESSION}messageCenter/messageAjax",
	   		type:"post",
	   		data:{"id":$('#id').val(),"type":$(type).val()},	
	   	 	dataType: "json",//返回json格式的数据
	   		success:function(message){
	   			if(message!=null&&message!=''){
	   				$('#id').val(message.id);
	   				$('#title').val(message.title);
	   				$('#author').val(message.author);
	   				$('#remark').val(message.remark);
	   				setImgurls(message.cover);
	   				$('#container').val(message.content);
	   			}
	   		}
	   		});
   		}
		
   }

   
   function setImageUrlTure(src,url){

		 $("#div_img").html(src);
		 $("#div_img").show();

		 $("#imageurls").attr("value",url);
	}
	function setImgurl(url){
			url = "messagecenter_cover/"+url;
	       var url1 = "${RESOURCE_LOCAL_URL}"+url;
	       var src = "&nbsp;<img src=\""+url1+"\"  ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
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
       if('${message}'!=null){
	   		messageservice.delImg('${message.id}',url,1,callback_delimg);
       }
	   setImgurls(strs);
   }
   function callback_delimg(res){
   }

   function setImgurls(imageurls){
      var src = "&nbsp;<img src=\"${RESOURCE_LOCAL_URL}"+imageurls+"\"  ondblclick=\"delImg('"+imageurls+"');\" alt='双击删除图片' title='双击删除图片' />";
      setImageUrlTure(src,imageurls);
	}


   if('${message}'!=''&&'${message.cover}'!=''){
	   setImgurls('${message.cover}');
   }
   
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
