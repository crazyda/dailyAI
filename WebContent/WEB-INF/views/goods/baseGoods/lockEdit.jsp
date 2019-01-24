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
    <title>新增商品规格</title>
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
            <p>你当前的位置：[商城管理]-[游戏商品编辑开奖号码]</p>
        </div>
		 <form action="<%=basePath%>reBaseGoods/save" id="reGoodsForm" method="post">
            <div class="r_con_wrap">
                <div class="r_con_form">
                    <input name="id" type="hidden" id="id" value="${lockMall.id}" >
                    <div class="rows">
                        <label> 商品名：</label>
						<span class="input"> 
							<input type="text" name="name" id="name" value="${reGoodsBase.name}" maxlength="38" readonly> </font>
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label> <font color="red">*</font>中奖号码：</label>
						<span class="input"> 
							<input type="text" name="openYards" id="openYards" value="" maxlength="38" > <font color="red"></font>
						</span>
                        <div class="clear"></div>
                    </div>
                  
	                <div class="rows">
	                        <label> 参与人数：</label>
							<span class="input"> 
								<input type="text" name="salesVolume" id="salesVolume" value="${count}" maxlength="38" readonly> <font color="red"></font>
							</span>
	                        <div class="clear"></div>
	                   </div>
	                   <div class="rows">
	                        <label>兑换资格积分数：</label>
							<span class="input"> 
								<input type="text" name="score" id="score" value="${lockMall.score}" maxlength="38" readonly> <font color="red"></font>
							</span>
	                        <div class="clear"></div>
	                   </div>
	                    
                    <div class="rows">
                        <label>抽奖说明：</label>
						<span class="input">
			     			<textarea name="standardDetails" clos=",50" rows="5" warp="virtual"  readonly>${lockMall.lockDesc}</textarea>
			 			</span>
			 			
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label></label>
						<span class="input"> 
							<input type="button" class="button" value="保存" onclick="submitForm();"/>
						</span>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>

        </form>
		<script type="text/javascript">
		  function submitForm() {
              
          	var name =document.getElementById("openYards").value;
          	if(name==""){
          		alert("请填写中奖号码");
          		return;
          	}
              $.ajax({
                  url: "<%=basePath%>reBaseGoods/saveOpenYards",
                  async:false,
                  type: "post",
                  data: $("#reGoodsForm").serialize(),
                  success: function (data) {
                      alert(data.msg);
                      window.location.href = "list";
                  }
              });
          }
		
		
		
		
		
		
		
		
		
		</script>
       

    </div>
    <div style="width:100%;height:20px;">
    </div>
</div>

</body>

</html>
