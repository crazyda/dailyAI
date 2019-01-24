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
    <title></title>
    <style type="text/css">

    </style>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <%-- <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script> --%>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
    <style type="text/css">
    	img{width:100%;height:100%}
    </style>
</head>


<body>
 	 <div class="r_con_wrap">
 	 
 	 		<c:if test="${record!=null&&record.name!=null}">
                <div class="r_con_form">
                    <%--基础商品信息开始--%>
                     <div class="rows">
                        <h2 style="margin-left: 30%; height: 30px; font-size: 20px; color: red;">商品修改前信息</h2>
                        <div class="clear"></div>
                    </div>
                    
                    <div class="rows">
                        <label>商品名称：</label>
                        <span class="input">
                            <lable>${record.name}</lable>
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>封面图片：</label>
                        <span class="input">
                            <img src="${RESOURCE_LOCAL_URL}${record.coverPicOne}" style="width: 150px;height: 150px;;">
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>简述图：</label>
                        <span class="input">
                            <c:forEach items="${record.pics}" var="each">
                                <img src="${RESOURCE_LOCAL_URL}${each}" style="width: 100px;height: 100px;display: inline-block">
                            </c:forEach>
                        </span>
                        <div class="clear"></div>
                    </div>
                    
                    <c:if test="${record.sellerMallSalesVolume!=null}">
                    <div class="rows">
                        <label>周边店铺销量:</label>
                        <span class="input">
                            <lable>${record.sellerMallSalesVolume}</lable>
                        </span>
                        <div class="clear"></div>
                    </div>
                    </c:if>
                     <c:if test="${record.scoreMallSalesVolume!=null}">
                    <div class="rows">
                        <label>积分商城销量:</label>
                        <span class="input">
                            <lable>${record.scoreMallSalesVolume}</lable>
                        </span>
                        <div class="clear"></div>
                    </div>
                    </c:if>
                    <c:if test="${record.localSpecialtyMallSalesVolume!=null}">
                    <div class="rows">
                        <label>总部商城销量:</label>
                        <span class="input">
                            <lable>${record.localSpecialtyMallSalesVolume}</lable>
                        </span>
                        <div class="clear"></div>
                    </div>
                    </c:if>
                       <c:if test="${record.seckillMallSalesVolume!=null}">
                    <div class="rows">
                        <label>秒杀商城销量:</label>
                        <span class="input">
                            <lable>${record.seckillMallSalesVolume}</lable>
                        </span>
                        <div class="clear"></div>
                    </div>
                    </c:if>
                     <c:if test="${record.lablesString!=null}">
                    <div class="rows">
                        <label>商品标签：</label>
                        <span class="input">
                            <lable>${record.lablesString}</lable>
                        </span>
                        <div class="clear"></div>
                    </div>
                    </c:if>
                     <c:if test="${record.intro!=null}">
                    <div class="rows">
                        <label>商品描述：</label>
                        <span class="input">
                            <lable>${record.intro}</lable>
                        </span>
                        <div class="clear"></div>
                    </div>
                    </c:if>
                    <c:if test="${record.details!=null}">
                    	 <div class="rows">
                        <label>商品详细介绍内容：</label>
                        <span class="input">
                            <lable>${record.details}</lable>
                        </span>
                        <div class="clear"></div>
                    </div>
                    </c:if>
                   
                    <%--基础商品信息结束--%>
                </div>
                </c:if>
            </div>
</body>

</html>
