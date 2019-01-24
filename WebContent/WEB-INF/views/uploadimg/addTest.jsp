<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<title>图片</title>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>

</head>

<body>
	<div class="div_new_content">
		<div style="margin-left:10px;">
			<form action="<%=basePath%>cashShop/cashShopType/save" id="saveForm"
				method="post">
		<input type=hidden id="imageurls" name="img" value="" />
				<div id="products" class="r_con_wrap">
					<div class="r_con_form">

						<div class="rows">
							<label>图片:</label> <span class="input"><br /> <iframe
									name="if_upload" id="if_upload"
									src="<%=basePath %>imagehandle/showupload.action?uploadtype=21"
									height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
								<div id="div_img" style="display:none">
									<img src="" id="img_id" />
								</div> </span>
							<div class="clear"></div>
						</div>

						<div class="rows">
							<label></label> <span class="input"> <input type="hidden"
								name="typeid" id="typeid"> <input type="button"
								class="button" onclick="savePtype();" value="添 加" />
							</span>
							<div class="clear"></div>
						</div>
					</div>
				</div>

			</form>
		</div>
		<div style="width:100%;height:20px;"></div>
	</div>
	
	<!--多图片上传  -->
	 <div class="infor2-container">                
              <form  class="fl-l" id="uploadForm"  enctype="multipart/form-data" method="post"  action="${RESOURCE_LOCAL_URL}upload6" > 
                  上传文件1：<input type="file" name="file1"/><br/><br/><br/>
                  上传文件2：<input type="file" name="file2"/><br/><br/><br/>
                  上传文件3：<input type="file" name="file3"/>
                  <input type="submit" value="提   交"/>
              </form>

              <h2>上传结果（多个文件）：</h2>
              <c:forEach items="${fileUrlList}" var="fileUrl" varStatus="status">
                  <img alt="" src="${basePath }${fileUrl }" />
              </c:forEach>
         </div>
	
	
	
	
	
	
<script>


   function savePtype(){
	   
      	document.getElementById("saveForm").submit();
   } 

   
   function setImageUrlTure(src,url,type){
			$("#div_img").html(src);
			 $("#div_img").show();
			 $("#imageurls").attr("value",url);
		
		 
	}
	function setImgurl(url,type){
				url = "cashshop_type/"+url;
				var url1 = "${RESOURCE_LOCAL_URL }"+url;
			       var src = "&nbsp;<img src=\""+url1+"\"  ondblclick=\"delImg('"+url+"',21);\" alt='双击删除图片' title='双击删除图片' />";
				   setImageUrlTure(src,url,type);
	}

	function delImg(url,type){
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
			   $("#div_img").html("");
			   $("#imageurls").attr("value","");
		      messageservice.delImg(message.id,url,1,callback_delimg);
   }
  

   function setImgurls(imageurls,type){
		  var src = "&nbsp;<img src=\"${RESOURCE_LOCAL_URL }"+imageurls+"\"  ondblclick=\"delImg('"+imageurls+"',21);\" alt='双击删除图片' title='双击删除图片' />";
	      alert('src======='+src);
	      setImageUrlTure(src,imageurls,type);
	}

	
	</script> 
</body>
</html>
