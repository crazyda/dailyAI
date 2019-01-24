<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@page import="com.axp.model.NewRedPaperSetting"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);

Object o = request.getAttribute("redPaperSetting");
NewRedPaperSetting sc = null;
String imageurls1 = null;

if(o!=null){
   sc = (NewRedPaperSetting)o;
   imageurls1 = sc.getHeadimg();
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红包列表</title>
<script type="text/javascript" src="${basePath }js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${basePath }js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basePath }js/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath }js/checktext.js"></script>
<link href="${basePath }css/css.css" rel="stylesheet" type="text/css">
<link href="${basePath }css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="${basePath }css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="${basePath }css/add/css/shop.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
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
	.a-upload {
	    padding: 4px 10px;
	    margin-right:10px;
	    height: 20px;
	    line-height: 20px;
	    position: relative;
	    cursor: pointer;
	    color: #888;
	    background: #fafafa;
	    border: 1px solid #ddd;
	    border-radius: 4px;
	    overflow: hidden;
	    display: inline-block;
	    *display: inline;
	    *zoom: 1
	}
	
	.a-upload  input {
	    position: absolute;
	    font-size: 100px;
	    right: 0;
	    top: 0;
	    opacity: 0;
	    filter: alpha(opacity=0);
	    cursor: pointer
	}
	
	.a-upload:hover {
	    color: #444;
	    background: #eee;
	    border-color: #ccc;
	    text-decoration: none
	}
	
	.err{
		color:red;
	}
	
	.input li{
		display: inline;
		border: 1px solid lightgray;
		 border-left-width: 0px;
	}
	
	.input li:last-child{
		background-color: lightgray;
		padding-left: 5px;
		padding-right: 5px;
	}
</style>
<script type="text/javascript">
	$(function(){
		var msg = '${msg}';
		if(msg != ""){
			alert("提示信息："+msg);
		}
		
		/* 周期 */
		var hour = '${hour}';
		var minute = '${minute}';
		var second = '${second}';
		for(var i=23; i>0; i--){
			if(i == hour){
				$("#hour").append("<option selected='selected'>"+i+"</option>");
			}else{
				$("#hour").append("<option>"+i+"</option>");
			}
		}
		for(var i=59; i>=0; i--){
			if(i == minute){
				$("#minute").append("<option selected='selected'>"+i+"</option>");
			}else{
				$("#minute").append("<option>"+i+"</option>");
			}
		}
	
		for(var i=59; i>=0; i--){
			if(i == second){
				$("#second").append("<option selected='selected'>"+i+"</option>");
			}else{
				$("#second").append("<option>"+i+"</option>");
			}
		}
	
		
		/* 红包链接 */
		var link = '${redPaperSetting.link}';
		$("#link").change(function(){
			if($(this).is(':checked')){
				$("#linkUrl").val("http://localhost:8080/jupinhui/sellerMall/sellerMall_index.action?sellerId=");
			}else{
				$("#linkUrl").val("");
			}
		});
		
		$("#quota").change(function(){
			/*
			if($(this).is(':checked')){
				$("#minParvalue").val("0.01");
				$("#maxParvalue").val("100");
			}else{
				$("#minParvalue").val("");
				$("#maxParvalue").val("");
			}*/
			if($(this).is(':checked')){
				$("#parvalueSpan").css("display","none");
				$("#parvalue").css("display","");
			}else{
				$("#parvalueSpan").css("display","");
				$("#parvalue").css("display","none");
			}
		});
		
		if('${redPaperSetting.minParvalue}'=='${redPaperSetting.maxParvalue}'){
			$("#quota").attr('checked',true);
			$("#parvalueSpan").css("display","none");
			$("#parvalue").css("display","");
		}
		
		$("#parvalue").change(function(){
			$("#minParvalue").val($("#parvalue").val());
			$("#maxParvalue").val($("#parvalue").val());
		});
		
		$("#sub").click(function(){
			edit();
		});
		/*
		$(":file").change(function(){
		    var file = this.files[0];
		    var size = file.size;
		    var type = file.type;
		    $(".a-upload font").empty();
            $(".a-upload font").html("请选择图片文件");
		    if(size < 2*1024*1024 && size > 0){
		    	$("#err").removeClass("err");
		    	upload();
		    }else{
		    	$("#err").addClass("err");
		    }
		});*/
	});
	function edit(){
		var bl = true;
		/*表单校验*/
		$(".not_empty").each(function(index,element){
			if($(this).val()==null || $(this).val()==""){
				if($(this).attr("id")=="parvalue"){
					if($('#quota').is(':checked')){
						bl = false;
						alert("请输入" + $(this).attr("title"));
						return false;
					}
				}else{
					bl = false;
					alert("请输入" + $(this).attr("title"));
					return false;
				}
			}
		});
		
		var center = $("#center_id").val();
		var proxy = $("#proxy_id").val();
		if(center == ""){
			alert("请选择派发范围");
			bl = false;
		}
		
		if(proxy == ""){
			$("#rangid").val(center);
		}else{
			$("#rangid").val(proxy);
		}
		
		if(bl == true){
			frm.submit();
		}
	}
	/*
	function upload(){
        $.ajaxFileUpload({  
           url: '${basePath}opera/upload.action',  
           secureuri: false,  
           fileElementId: 'upImg',  
           dataType: 'content',  
           beforeSend: function() {  
               $("#loading").show();  
           },  
           complete: function() {  
               $("#loading").hide();  
           },  
           success: function(data, status) {  
        	   $("#headImg").val(data);
        	   
               $(".a-upload font").empty();
               $(".a-upload font").html("图片已上传");
               $(".a-upload  input").attr({"disabled":"disabled"});
			   $("#img").html("");
               $("#img").append("<img src='${basePath }" + data + "' border='0' width='0' height='0' onload='AutoResizeImage(150,150,this)'/>");
           },  
           error: function(data, status, e) {  
        	   $(".a-upload font").empty();
               $(".a-upload font").html("内部错误"); 
           }  
       })  
       return false; 
	}
	
	function AutoResizeImage(maxWidth,maxHeight,objImg){
		var img = new Image();
		img.src = objImg.src;
		var hRatio;
		var wRatio;
		var Ratio = 1;
		var w = img.width;
		var h = img.height;
		wRatio = maxWidth / w;
		hRatio = maxHeight / h;
		if (maxWidth ==0 && maxHeight==0){
			Ratio = 1;
		}else if (maxWidth==0){//
			if (hRatio<1) Ratio = hRatio;
		}else if (maxHeight==0){
			if (wRatio<1) Ratio = wRatio;
		}else if (wRatio<1 || hRatio<1){
			Ratio = (wRatio<=hRatio?wRatio:hRatio);
		}
		if (Ratio<1){
			w = w * Ratio;
			h = h * Ratio;
		}
		objImg.height = h;
		objImg.width = w;
	}*/
	

</script>
<body>
<div class="div_new_content">
<div style="margin-left:10px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="images/tab_05.gif">
   	<table width="100%" border="0" cellspacing="0" cellpadding="0">
   		<tr>
     		<td>
     		<table width="100%" border="0" cellspacing="0" cellpadding="0">
   				<tr>
     				<td width="46%" valign="middle">
   					<table width="100%" border="0" cellspacing="0" cellpadding="0">
     					<tr>
            				<td width="95%" class="STYLE1">
              				<div class="header-left">
              				<c:choose>
	                			<c:when test="${settingId==0 }">
									<p>你当前的位置：[红包管理]-[派发红包]-[新建红包]</p>
	                			</c:when>
			                  	<c:otherwise>
			                  		<p>你当前的位置：[红包管理]-[派发红包]-[编辑红包]</p>
			                  	</c:otherwise>
                			</c:choose>
							</div>
            				</td>
          				</tr>
        			</table>
        			</td>
      			</tr>
     		</table>
     		</td>
   		</tr>
   	</table>
    </td>
  </tr>
  <c:if test="${!empty redPaperSetting }">
  <tr>
  	<td><div style="width:90%;height:30px;color:red;margin-left:10px;font-size:12px;">注：每次修改需要重新审核！</div></td>
  </tr>
  </c:if>
  <tr>
  	 <td>
	 <form name="frm" action="${basePath }newRedPaper/issue/edit" method="post">
		 <div id="settings" class="r_con_wrap">
		 	<div class="r_con_form" >
		 		<div class="rows">
					<label> <span class="err">*</span>&nbsp;派发范围：<input type="hidden" name="rangeid" id="rangid"></label>
					<span class="input">省份
					<select id="sheng" name="sheng" onclick="chaxun()">
						<option value="">请选择</option>
						<c:forEach var="s" items="${provinceEnumList}">
							<option value="${s.id }">${s.name}</option>
						</c:forEach>
					</select>&nbsp;&nbsp;&nbsp;&nbsp;城市
					<select id="cityId" name="cityId" >		
						<c:if test="${redPaperSetting.id==null }">
							<option value="">请选择</option>
						</c:if>
						<c:if test="${redPaperSetting.id!=null }">
							<option value="${provinceEnum.id }">${provinceEnum.name}</option>
						</c:if>
					</select>
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label> <span class="err">*</span>&nbsp;红包名称：</label>
					<span class="input">
					  <input title="红包名称" type="text" name="name" class="not_empty" value="${redPaperSetting.name }">
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label> <span class="err">*</span>&nbsp;红包总金额：</label>
					<span class="input">
					  <li><input title="红包总金额" type="number" class="not_empty"  name="allMoney" value="${redPaperSetting.allMoney }"></li><li>元</li>&nbsp;当前用户剩余额度：${positionSurplus }
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label><span class="err">*</span>&nbsp;发放总量：</label>
					<span class="input">
					  <li><input title="发放总量" class="not_empty"  type="number" name="allNum" value="${redPaperSetting.allNum }"></li><li>张</li>
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label><span class="err">*</span>&nbsp;每天发放最多：</label>
					<span class="input">
					  <li><input title="每天最多发放" type="number" name="maxPutout" class="not_empty" value="${redPaperSetting.maxPutout }"></li><li>张</li>（如果与发放总量相同，表示同意最快一天发完所有红包）
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label><span class="err">*</span>&nbsp;面值：</label>
					<span class="input">
					<input style="display:none;" title="定额" type="number" id="parvalue" class="not_empty" value="${redPaperSetting.minParvalue }">	
					<span id="parvalueSpan">
					  <input title="最小面值" type="number" name="minParvalue" id="minParvalue" class="not_empty" value="${redPaperSetting.minParvalue }">&nbsp;至&nbsp;<input title="最大面值" type="number" name="maxParvalue" id="maxParvalue" class="not_empty" value="${redPaperSetting.maxParvalue }">
					</span>
					<label>&nbsp;<input type="checkbox" id="quota">定额</label>
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label><span class="err">*</span>&nbsp;每人限领：</label>
					<span class="input">
					  <input title="每人限领" type="number" name="limits" class="not_empty" value="${redPaperSetting.limits}">（每人领取间隔：<select name="hour" id="hour" ></select>小时&nbsp;<select name="minute" id="minute" ></select>分&nbsp;<select name="second" id="second"></select>秒）
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label><span class="err">*</span>&nbsp;开始时间：</label>
					<span class="input">
					  <input title="开始时间" class="not_empty" type=text id=endTime name="bTime" value="${redPaperSetting.beginTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd '});"  />
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>红包描述：</label>
					<span class="input">
					  <c:choose>
						<c:when test="${!empty redPaperSetting.description }">
						<textarea cols=30 rows=10 name="description" class="des">${redPaperSetting.description }</textarea>
						</c:when>
						<c:otherwise>
						<textarea cols=30 rows=10 name="description" class="des">天天逛逛，天天赚赚;爱生活，每天积分</textarea>
						</c:otherwise>
					  </c:choose>
					</span>
					<div class="clear"></div>
		         </div>
		         <div class="rows">
					<label>红包有效期：</label>
					<span class="input">
					  3个自然月内有效
					</span>
					<div class="clear"></div>
		         </div>
		         <div class="rows">
					<label>红包头像：<input type="hidden" value ="0" name="headimg" id="headImg"/></label>
					<span class="input">
						<%-- <font id="err">图片尺寸300 x 300 ， 小于2M</font></br>
						<wh:UploadImgTag name="imgBaseData" corpHeight="300" relatedIds='${redPaperSetting.id }' tableName="new_red_paper_setting" corpWidth="300" amount="1"></wh:UploadImgTag>  --%>
						 <iframe name="if_upload" id="if_upload" src="<%=basePath %>imagehandle/showupload?uploadtype=23" height=80  FRAMEBORDER=0 SCROLLING=no> </iframe>
		            	<div id="div_img1"">
						</div>	  
					<!--<a class="a-upload"><input type="file" name="upImg"><font>请选择图片文件</font></a><img id="loading" alt="" src="">-->
					</span>
					<div class="clear"></div>
		         </div>
		         <div class="rows">
					<label><span class="err">*</span>&nbsp;红包链接：</label>
					<span class="input">
					 <!-- 
					  <c:choose>
						<c:when test="${empty redPaperSetting.link }">
						<input title="红包链接" type="text" name="redPaperSetting.link" id="linkUrl" class="not_empty" value="http://localhost:8080/aixiaoping/sellerMall/sellerMall_index.action?sellerId=" />
						</c:when>
						<c:otherwise>
						<input title="红包链接" type="text" name="redPaperSetting.link" id="linkUrl" class="not_empty" value="${redPaperSetting.link }" />
						</c:otherwise>
					  </c:choose>
					  <label><input type="checkbox" checked="checked" id="link">外部链接</label> -->
					  <input title="红包链接" type="text" name="link" id="linkUrl" class="not_empty" value="${redPaperSetting.link }" />
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>
					<input type="hidden" name="id" value="${redPaperSetting.id }">
					<input type="hidden" name="isValid" value="${redPaperSetting.isValid }">
					<input type="hidden" name="allMoneyUsed" value="${redPaperSetting.allMoneyUsed }">
					<input type="hidden" name="allNumColl" value="${redPaperSetting.allNumColl }">
					<input type="hidden" name="allNumUsed" value="${redPaperSetting.allNumUsed }">
					<input type="hidden" name="todaySurplus" value="${redPaperSetting.todaySurplus }">
					<input type="hidden" name="cTime" value="${redPaperSetting.createTime }">
					</label>
					<span class="input">
						<input type="button" value="确认" class="button" id="sub"><input type="reset" value="取消" class="button">
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
</body>

<script type="text/javascript">
function chaxun(){
	var id = $("#sheng").val();
	var city = document.getElementById("cityId");
	city.options.length=0;
	$.ajax({
		type:"post",
		url:"<%=basePath %>newRedPaper/issue/changeZone",
		data: "zonesid="+id,        
        dataType: "text",
        success: function (data){
        	var json=eval( '('+data+ ')' );
        	city.options.add(new Option("请选择", ""));
	    	 for(var i in json){	
	    		city.options.add(new Option(json[i],i));
	    	 }

        }
	})
	
}

function setImgurl(url,type){
	if(type==23){
		url = "new_red_paper_opera/"+url;
		var url1 = "${RESOURCE_LOCAL_URL}"+url;
	    var src = "<span><img src=\""+url1+"\" width=\"100px\" height=\"100px\" ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' /><input type=\"hidden\" id=\"imageurls1\" name=\"imageurls1\" value=\"\" /></span>";
		setImageUrlTure(src,url,type);
	}  
}

function setImageUrlTure(src,url,type){
	if(type==23){
		$("#div_img1").html(src);
		 $("#imageurls1").attr("value",url);
	}
}
function setImgurls(imageurls,type){	
	  if(type==23){
		  var src = "<span><img src=\"${RESOURCE_LOCAL_URL}"+imageurls+"\" width=\"100px\" height=\"100px\"  ondblclick=\"delImg(this);\" alt='双击删除图片' title='双击删除图片' /><input type=\"hidden\" id=\"imageurls1\" name=\"imageurls1\" value=\"\" /></span>";
	      setImageUrlTure(src,imageurls,type);
	  }
	}
<%if(imageurls1!=null && imageurls1.length()>0){%>
	setImgurls("<%=imageurls1%>",23);

<%}%>
</script>
</html>