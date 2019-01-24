<%@page import="com.axp.dao.impl.AdminUserDAOImpl" %>
<%@page import="com.axp.util.StringUtil" %>
<%@page import="com.axp.model.AdminUser" %>
<%@page import="com.axp.dao.AdminUserDAO" %>
<%@page import="com.axp.model.CashshopType" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
/* AdminUserDAO adao = new AdminUserDAOImpl();
AdminUser user = null;
user = adao.findById(current_user_id); */
    Object o = request.getAttribute("cashshopType");
    CashshopType sc = null;
    String imageurls = null;
    String imageurls1 = null;
    if (o != null) {
        sc = (CashshopType) o;
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
    <link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css">
    <title>添加登录签到奖励</title>
    <style type="text/css">

    </style>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
</head>

<body>
<div class="div_new_content">
    <div style="margin-left:10px;">

        <div class="header-left">
            <p>你当前的位置：[游戏设置]-[添加登录签到奖励]</p>
        </div>

            <div class="r_con_wrap">
       	 <form action="" id="reGoodsForm" method="post">
        	<input type="hidden" id="status" name="status" value="268">
        	<input type="hidden" name = "gameId" value = "${game.id }"> 
                <div class="r_con_form">
                  <div class="rows"  >
                        <label> 选择参与商家：</label>
                        <input type="hidden" name="sellerId" id="sellerId" value=""/>
						<span class="input"> 
						<input type=text name="sellerPhone" id="sellerPhone" value="${game.seller.phone}"  onchange="doCheckName(true)" /> <font color="red" id="sellerName">*填写商家手机号验证商店</font>
                        </span>
                        <div class="clear"></div>
                    </div> 
                    <c:forEach var="i" begin="0" end="7" step="1">
                    	<div class="rows">
                        <label> 签到第${i+1 }天获得奖励：</label>
						<span class="input"> 
							<input type="text" name="score${i+1 }" id="score${i+1 }" value="${array[i] }" maxlength="38"> 
						</span>
                        <div class="clear"></div>
                    </div>
                    
                    
                    </c:forEach>
				<div class="rows">
					<label>签到页面广告图：</label> <span class="input"> <wh:UploadUtil
							fileDirName="basegoods_cover" thumbH="100" thumbW="100"
							amount="5" submitName="coverPic[]" targetExt="jpg"
							relatedChar="${game.coverPics }"></wh:UploadUtil>
					</span>
					<div class="clear"></div>
				</div>
				<div class="rows">
					<label>每天对应签到图:</label> <span class="input"> <!-- 加载编辑器的容器 -->
						<wh:UploadUtil fileDirName="goodsDetails" thumbH="100"
							thumbW="100" amount="5" submitName="content[]" targetExt="jpg"
							relatedChar="${game.content }">
							
						</wh:UploadUtil>
					</span>

					<div class="clear"></div>
				</div>
				<div class="rows">
					<label>签到说明：<font color="red">*</font></label> <span class="input">
						<textarea name="details" clos="50" rows="5" warp="virtual">${game.detail }</textarea>
					</span>

					<div class="clear"></div>
				</div>

				<div class="rows">
					<label></label> <span class="input"> <input type="button"
						class="button" value="保存" onclick="submitForm();" />

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

<!-- 提交表单，获取返回数据 -->
<script>
	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	var ue = UE.getEditor('container');
	//复写UEDITOR的getActionUrl 方法,定义自己的Action
	UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
	UE.Editor.prototype.getActionUrl = function(action) {
		if (action == 'uploadimage' || action == 'uploadfile') {
			var dir = 'upload-res/goods/baseGoods/1/';
			return '${RESOURCE_LOCAL_URL}uHandle/upload?dir=' + dir;//图片上传初始化
		} else {
			return this._bkGetActionUrl.call(this, action);//编辑器初始化
		}
	};

	
	function submitForm() {
		 var pic = $("input[name='coverPic[]']");
		var des = $("input[name='content[]']");
		if (pic == null || pic.size() == 0) {
			alert("请上传页面广告图");
			return;
		}
		if (des == null || des.size() == 0) {
			alert("请上传每天对应签到图");
			return;
		} 
		 if (verifyResult == 0) {
			 doCheckName(false);
			 if(verifyResult == 0){
				alert("请选择商家");
			 }
			return;
		} 
		$.ajax({

			url : "<%=basePath%>game/saveSignInfo",
            type: "get",
            data: $("#reGoodsForm").serialize(),
            success: function (data) {
                window.location.href = "gameList";
            }
        });
    }
      
      //校验商家名称唯一性
      var verifyResult = 0;
      function doCheckName(as){
      	//1、获取帐号
      	var $sellerPhone = $("#sellerPhone");
      	
      	//2、查询改商家符合标准
      	$.ajax({
      		url:"${basePath}adminUserToSeller",
      		type:"post",
      		data:{"sellerPhone":$sellerPhone.val()},	
      		async:as,//异步属性，默认值为true
      		success:function(msg){
      			alert(msg.message);
      			if(msg.code==1){
      				$("#sellerName").html("选择商家为:"+msg.sellerName);
      				$("#sellerId").val(msg.sellerId);
      				verifyResult = 1;
      			}
      		}
      	});
      }                   
                            
</script>
</body>

</html>
