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
    <link href="<%=basePath %>css/baguetteBox.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css">
    <title>新增商品规格</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/goods/goodsStandard.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/baguetteBox.js"></script>
    
    <style type="text/css">
	    tr#tableProperty {
	    		text-align: left;
		}
		td{
	    		padding-right: 50px;
		}
    </style>
</head>

<body>
<div class="div_new_content">
    <div style="margin-left:10px;">

        <div class="header-left">
            <p>你当前的位置：[商城审核]-[帖子详情-换货帖子]</p>
        </div>

        <form action="<%=basePath%>reBaseGoods/save" id="reGoodsForm" method="post">
            <div class="r_con_wrap">
                <div class="r_con_form">
                    <input name="id" type="hidden" id="id" value="">

                    <div class="rows">
                        <label>发帖人：</label>
                        <span class="input">
                            <label>${changeNote.sendUsers.name}</label>
                        </span>
                        <div class="clear"></div>
                    </div>
                     <div class="rows">
                        <label>发帖内容：</label>
                        <span class="input">
                            <label>${changeNote.content}</label>
                        </span>
                        <div class="clear"></div>
                    </div>
                    <c:if test="${changeNote.coverPic!=null}">
                    	    <div id="slider" class="rows">
                        <label>帖子图片：</label>
                        <span  class="input">
                            <c:forEach  items="${changeNote.listOfCoverPic}" var="cover">
                                  <a href="${RESOURCE_LOCAL_URL}${cover}"><img  src="${RESOURCE_LOCAL_URL}${cover}" style="width: 100px;height: 100px;display: inline-block"></a>
                            </c:forEach>
                        </span>
                        <script type="text/javascript">
                        	baguetteBox.run('#slider');
						</script>
                        <div class="clear"></div>
                    </div>
                    </c:if>
                    
                    <c:if test="${changeNote.goodsIds!=null}">
	                   <div class="rows">
	                       <label>商品详情：</label>
						<span class="input">
	                           <a href="${BASEPATH_IN_SESSION}goodsCheck/noteGoodsdetail?checkId=${changeNote.id}">查看换货商品详情</a>
						</span>
	                       <div class="clear"></div>
	                   </div>
                    
                    </c:if>
                    
                     <div class="rows">
                        <label>审核说明：</label>
                        <span class="input" name="checkRemark">
							 <textarea  name="checkRemark" class="checkRemark"></textarea>						
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label></label>
						<span class="input">
							<input type="button" value="审核通过" onclick="pass(${changeNote.id},true);" style="color:#fff; font-size:1.0rem; line-height:2.5rem; width:6.0rem; background:#0e93d8; border-radius:3px; cursor:pointer;"/>
							<input type="button" value="审核不通过" onclick="pass(${changeNote.id},false);" style="color:#fff; font-size:1.0rem; line-height:2.5rem; width:6.0rem; background:#0e93d8; border-radius:3px; cursor:pointer;"/>
						</span>
                        <div class="clear"></div>
                        <script>
                            function pass(checkId, isPass) {
                            	var checkRemark = $(".checkRemark").val();
                                $.post("checkOfChangeNoteMall", {"checkId": checkId, "isPass": isPass,"checkRemark":checkRemark}, function (data) {
                                    alert(data.message);
                                    location.href = "listOfChangeNoteMall";
                                });
                            }
                        </script>
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
