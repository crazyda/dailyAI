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
            <p>你当前的位置：[商城管理]-[新增基础商品]</p>
        </div>

        <form action="" id="reGoodsForm" method="post">
            <div class="r_con_wrap">
                <div class="r_con_form">
                    <input name="id" type="hidden" id="id" value="${reGoodsBase.id}" >
                    <div class="rows">
                        <label> <font color="red">*</font>商品名：</label>
						<span class="input"> 
							<input type="text" name="name" id="name" value="${reGoodsBase.name}" maxlength="38"> <font color="red">*最多可输入38个字数</font>
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label> <font color="red">*</font>封面图片：</label>
						<span class="input"> 
							<wh:UploadUtil fileDirName="basegoods_cover" thumbH="100" thumbW="100" amount="5"
                                           submitName="coverPic[]" targetExt="jpg" relatedChar="${reGoodsBase.coverPic}">尺寸为：200px*200px</wh:UploadUtil>
						</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label> <font color="red">*</font>商品类别：</label>
						<span class="input selectType">
                            <c:forEach begin="1" end="1" step="1">
                                <div>
                                    <label>类别：</label>
                                    <select class="typesId">
                                        <option value="-1">--请选择--</option>
                                        <c:forEach items="${commodityType }" var="ctype">
                                            <option value=${ctype.id }>${ctype.name }</option>
                                        </c:forEach>
                                    </select>
                                    <select class="typesIdItem">
                                        <option value="-1">--请选择--</option>
                                    </select>
                                    <input type="hidden" name="typesId[]" value="-1">
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

                                        //给第二个select填充值；
                                        var v = select1;
                                        var nextSelect = v.next("select");
                                        $.ajax({
                                            type: 'post',
                                            url: '<%=basePath%>reBaseGoods/goodsTypeAjax',
                                            async: false,
                                            data: {typeId: v.val()},
                                            cache: false,
                                            dataType: 'json',
                                            success: function (data) {
                                                v.next("select").children(":gt(0)").remove();
                                                for (var i = 0; i < data.length; i++) {
                                                    nextSelect.append("<option value='" + data[i].id + "' >" + data[i].name + "</option>");
                                                }
                                                //更改后面的input的值；
                                                v.parent().find("input").val(v.val());
                                            }
                                        });

                                        //对第二个select进行回显；
                                        var select2Option = nextSelect.find("option");
                                        $.each(select2Option, function (index, obj) {//对第二个select进行赋值；
                                            var childTypeId = dataFromWeb[i].childTypeId;
                                            if (childTypeId) {
                                                if ($(obj).val() == childTypeId) {
                                                    $(obj).prop("selected", "selected");
                                                }
                                            }
                                        });

                                        //给div后面的input赋值；
                                        if (nextSelect.val() != -1) {
                                            nextSelect.parent().find("input").val(nextSelect.val());
                                        }
                                    }
                                }
                            }); 
                        </script>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label> <font color="red">*</font>商品简述图：${reGoodsBase.descriptionPics}</label>
						<span class="input">
							<wh:UploadUtil fileDirName="basegoods_detail" thumbH="100" thumbW="100" amount="5"
                                           submitName="descriptionPic[]" targetExt="jpg"
                                           relatedChar="${reGoodsBase.descriptionPics}">尺寸为：750px(宽)*750px(高)</wh:UploadUtil>
						</span>
                        <div class="clear"></div>
                    </div>
                      <div class="rows">
                        <label>商品详情(图片)：<font color="red">*</font></label>
						<span class="input">
			     			<!-- 加载编辑器的容器 -->
    						<%--  <script id="container" name="content[]" type="text/plain" style="height:500px;width:99.3%">${detailPicture}${detailTxt}</script>  --%>
    						<!-- da -->
    						 <wh:UploadUtil fileDirName="goodsDetails" thumbH="100" thumbW="100" amount="5"
                                           submitName="content[]" targetExt="jpg"
                                           relatedChar="${img }">(图片尺寸为：750px(宽)*(高不限),最大不能超过3m)
                               <%--   <c:if test="${detailPicture!=null }"> 
                               	<img alt="" src="${RESOURCE_LOCAL_URL}${detailPicture }"style="height:500px;width:99.3%">
                               </c:if>  --%>
                             </wh:UploadUtil> 
			 			</span>
			 			
                        <div class="clear"></div>
                    </div>s
                    <div class="rows">
                        <label>商品详情(文字介绍)：<font color="red">*</font></label>
						<span class="input">
			     			<textarea name="details" clos=",50" rows="5" warp="virtual" >${detailTxt }</textarea>
			 			</span>
			 			
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label></label>
						<span class="input"> 
							<input type="button" class="button" value="保存" onclick="submitForm();"/>

                            <!-- 提交表单，获取返回数据 -->
							<script>
							
						/* 	//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
                            var ue = UE.getEditor('container');
                            //复写UEDITOR的getActionUrl 方法,定义自己的Action
                            UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
                            UE.Editor.prototype.getActionUrl = function (action) {
                                if (action == 'uploadimage' || action == 'uploadfile') {
                                    var dir = 'upload-res/goods/baseGoods/1/';
                                    return '${RESOURCE_LOCAL_URL}uHandle/upload?dir=' + dir;//图片上传初始化
                                } else {
                                    return this._bkGetActionUrl.call(this, action);//编辑器初始化
                                }
                            };
 */
                            //当第一个select出现变动的时候；要求：1，为后面的一个select增添内容；2，为后面的input填值；
                            $('.typesId').on('change', function () {
                                var v = $(this);
                                var nextSelect = v.next("select");
                                $.ajax({
                                    type: 'post',
                                    url: '<%=basePath%>reBaseGoods/goodsTypeAjax',
                                    async: false,
                                    data: {typeId: v.val()},
                                    cache: false,
                                    dataType: 'json',
                                    success: function (data) {
                                        v.next("select").children(":gt(0)").remove();
                                        for (var i = 0; i < data.length; i++) {
                                            nextSelect.append("<option value='" + data[i].id + "' >" + data[i].name + "</option>");
                                        }
                                        //更改后面的input的值；
                                        v.parent().find("input").val(v.val());
                                    }
                                });
                            });
                            //当第二个select变动的时候，要求：1，更改后面的input的值；
                            $('.typesIdItem').on('change', function () {
                                var v = $(this);
                                v.next("input").val(v.val());
                            });
							
                            //商品标签的选择事件；
                            $(".lablesId").on("change", function () {
                                $(this).next("input").val($(this).val());
                            });

                            function submitForm() {
                            
                            	var name =document.getElementById("name").value;
                            	if(name==""){
                            		alert("请填写产品名称！");
                            		return;
                            	}
                            
                              var pic = $("input[name='coverPic[]']");
                                var des = $("input[name='descriptionPic[]']");
                                if (pic == null || pic.size() == 0) {
                                    alert("请上传封面");
                                    return;
                                }
                                if (des == null || des.size() == 0) {
                                   alert("请上传简述图");
                                  return;
                                }
                                
                              
                                $.ajax({
                                    url: "<%=basePath%>reBaseGoods/save",
                                    type: "post",
                                    data: $("#reGoodsForm").serialize(), 
                                    dataType: "json",
                                    success: function (data) {
                                        alert(data.msg);
                                       window.location.href = "list";
                                    },
                       	         error : function(XMLHttpRequest, textStatus, errorThrown) {
                     	        	//这个error函数调试时非常有用，如果解析不正确，将会弹出错误框
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
