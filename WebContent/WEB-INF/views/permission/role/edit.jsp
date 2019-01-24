<%@page import="com.axp.model.CashshopType" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
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
    <meta charset="utf-8">
    <title>新增角色</title>
    <link href="<%=basePath%>css/permission/permissionPageStyle.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>

    <!-- artDialog -->
    <link href="<%=basePath%>js/plugins/artDialog-master/artDialog-master/css/ui-dialog.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/artDialog-master/artDialog-master/dist/dialog-min.js"></script>
</head>

<body>
<form class="adduser-form" action="save">
    <input type="hidden" name="roleId" value="${EditRole_in_Session.id}">
    <ul class="user">
        <li>名称 <input type="text" name="roleName" value="${EditRole_in_Session.name}"></li>
    </ul>
    <div class="limit">
        <p class="title">权限</p>
        <div class="tab permission">
            <h4>
                <%--<span class="current"><i>分类1</i></span>--%>
                <c:forEach items="${ClassifyPermissions_in_Session}" var="each">
                    <span><i>${each.key}</i></span>
                </c:forEach>
            </h4>
            <ul>
                <c:forEach items="${ClassifyPermissions_in_Session}" var="each">
                    <li>
                        <c:forEach items="${each.value}" var="each2">
                            <p><input type="checkbox" value="${each2.id}" name="rePermissions"> ${each2.name}</p>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <p class="title">菜单</p>
        <div class="tab item">
            <h4>
                <%--<span class="current"><i>分类1</i></span>--%>
                <c:forEach items="${ClassifyItem_in_Session}" var="each">
                    <span><i>${each.key}</i></span>
                </c:forEach>
            </h4>
            <ul>
                <c:forEach items="${ClassifyItem_in_Session}" var="each">
                    <li>
                        <c:forEach items="${each.value}" var="each2">
                            <p><input type="checkbox" value="${each2.id}" name="reItems"> ${each2.name}</p>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <script type="text/javascript">

            //点击权限和菜单分类的标题栏时显示对应的子菜单
            $('.tab h4 span').click(function (e) {
                $(this).addClass('current').siblings().removeClass('current');
                $(this).parents(".tab").find("ul li").eq($(this).index()).show().siblings().hide();
            });

            //网页加载完后默认显示第一栏
            $(function () {
                $(".permission h4 span:first").click();
                $(".item h4 span:first").click();
            });

            //回显数据；
            Array
            ownedPermissions =${EditRolePermissions_Json_in_Session}
            $(function () {
                var inputs = $(".permission ul input");
                $.each(inputs, function (index, obj) {
                    for (var i = 0; i < ownedPermissions.length; i++) {
                        if ($(obj).val() == ownedPermissions[i]) {
                            $(obj).prop("checked", true);
                        }
                    }
                });
            });

            Array
            ownedItems =${EditRoleItems_Json_in_Session}
            $(function () {
                var inputs = $(".item ul input");
                $.each(inputs, function (index, obj) {
                    for (var i = 0; i < ownedItems.length; i++) {
                        if ($(obj).val() == ownedItems[i]) {
                            $(obj).prop("checked", true);
                        }
                    }
                });
            });


        </script>
    </div>
    <p class="sub"><input type="submit" value="提交"></p>
</form>
</body>
</html>
