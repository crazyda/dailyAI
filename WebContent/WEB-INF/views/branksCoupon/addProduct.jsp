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
    <%-- <link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css"> --%>
    <title>新增兑换产品</title>
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
            <p>你当前的位置：[商城管理]-[新增兑换产品]</p>
        </div>

        <form action="" id="productForm" method="get">
            <div class="r_con_wrap">
                <div class="r_con_form">
                    <input name="id" type="hidden" id="id" value="${product.id }" >
                    <div class="rows">
                        <label> <font color="red">*</font>收分用户：</label>
                        <span class="input"> 
							<div>
                                	<select class="" name = "adminUserId">
										<c:forEach items="${adminUsers }" var="a">
											<option  value="${a.id }"  <c:if test="${a.id==adminUser.id}">selected="selected"</c:if> >${a.loginname }</option>
										</c:forEach>
									</select>
                                	
                                </div>
							
							
							
						</span>
                        <div class="clear"></div>
                    </div>
                    
                     <div class="rows">
                        <label> <font color="red">*</font>归属银行：</label>
						<span class="input selectType">
                            <c:forEach begin="1" end="1" step="1">
                                <div>
                                	<select class="typesId" name = "brankId">
										<c:forEach items="${branks }" var="b">
											<option  value="${b.id }"  <c:if test="${b.id==brank.id}">selected="selected"</c:if> >${b.branksName }</option>
										</c:forEach>
									</select>
                                	
                                </div>
                            </c:forEach>
						</span>
                        <script>
                            //回显选择框；
                           $(function () {
                                var dataFromWeb;//基础商品的类型；
                                   dataFromWeb =eval("");
                                if (dataFromWeb) {
                                    var dataSize = dataFromWeb.length;//数据长度；
                                    for (var i = 0; i < dataSize; i++) {//遍历select，对第一个select进行赋值；
                                        var select1 = $(".typesId:eq(" + i + ")");
                                        var options = select1.find("option");
                                        $.each(options, function (index, obj) {//对select进行赋值；
                                            if ($(obj).val() == dataFromWeb[i].parentTypeId) {
                                                $(obj).prop("selected", "selected");
                                            }
                                        });
                                        //给div后面的input赋值；
                                        if (select1.val() != -1) {
                                            select1.parent().find("input").val(select1.val());
                                        }

                                    }
                                }
                            }); 
                        </script>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label> <font color="red">*</font>产品名称：</label>
						<span class="input"> 
							<input type="text" name="productName" id="productName" value="${product.productName}" maxlength="38"> 
						</span>
                        <div class="clear"></div>
                    </div>
                     <div class="rows">
                        <label> <font color="red">*</font>产品图片：</label>
						<span class="input"> 
							<wh:UploadUtil fileDirName="basegoods_cover" thumbH="100" thumbW="100" amount="5"
                                           submitName="productImg[]" targetExt="jpg" relatedChar="${product.productImg}">尺寸为：200px*200px</wh:UploadUtil>
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label> <font color="red">*</font>兑换积分：</label>
						<span class="input"> 
							<input type="text" name="score" id="score" value="${product.score}" maxlength="38"> 
						</span>
                        <div class="clear"></div>
                    </div>
                   <div class="rows">
                        <label> <font color="red">*</font>产品数量：</label>
						<span class="input"> 
							<input type="text" name="num" id="num" value="${product.num}" maxlength="38"> 
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>产品详情(文字介绍)：<font color="red">*最多可输入38个字数</font></label>
						<span class="input">
			     			<textarea name="remark" clos=",50" rows="5" warp="virtual" >${product.remark }</textarea>
			 			</span>
			 			
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label></label>
						<span class="input"> 
							<input type="button" class="button" value="保存" onclick="submitForm();"/>

							<script>


                            function submitForm() {
                            	var adminUserId = $("#adminUserId").val;
                            	var pic = $("input[name='productImg[]']");
                            	if(name==""){
                            		alert("请填写产品名称！");
                            		return;
                            	}
                                if (pic == null || pic.size() == 0) {
                                    alert("请上传封面");
                                    return;
                                }
                                $.ajax({
                                    url: "<%=basePath%>BranksCoupon/saveProduct",
                                    type: "post",
                                    data: $("#productForm").serialize(), 
                                    dataType: "json",
                                    success: function (data) {
                                       alert(data.msg);
                                       window.location.href = "list";
                                    },
                       	         error : function(XMLHttpRequest, textStatus, errorThrown) {
                     	        	//这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
                     	        	alert("error");
                     	        	alert(XMLHttpRequest.responseText); 
                     	        	alert(XMLHttpRequest.status);
                     	        	alert(XMLHttpRequest.readyState);
                     	        	alert(textStatus); // parser error;
                     	        	}
                                });
                            }
							</script>
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

</body>

</html>
