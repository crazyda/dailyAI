<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>店铺审核列表</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

    <!-- 分页插件 -->
    <link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet"/>
    <script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>
</head>

<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[商品审核]-[已审核店铺]</p>
        </div>
        <hr align="center"/>
    </div>
    <div class="div_search">
			   <form action="${BASEPATH_IN_SESSION}/cashShop/store/list.action" method="post">
				    <input type ="hidden" name="type" value="2"/>
				             店铺名称: <input type=text class="input" value="${sreach_name}" name="search_name" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索"/>
			    </form>
			</div>
    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            <th width="3%">置顶</th>
            <th width="5%" style="height:50px;">序号</th>
           
            <th width="10%">店铺名称</th>
            <th width="10%">联系方式</th>
            <th width="10%">商家身份证</th>
            <th width="10%">店铺地址</th>
            <th width="10%">营业执照</th>
            <th width="10%">提交时间</th>
            <th width="10%">审核状态</th>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.list}" var="seller">
                <tr>
                	<td><input type="radio" name="code_Value" value="${seller.id }" onclick="zhiding('${seller.id }')"/></td>
                    <td>${seller.id}</td>
                    <td>${seller.name}</td>
                    <td>${seller.phone}</td>
                    <td>${seller.sellerIdCard}</td>
                    <td>${seller.address}</td>
                    <td><img src="${RESOURCE_LOCAL_URL}${seller.businessLicencePic}" style="width:80px;height:80px;"/></td>
                    <td>
                        <fmt:formatDate value="${seller.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                    <c:choose>
                    <c:when test="${seller.verifyStatus==-1}">审核不通过</c:when>
                    <c:otherwise>
                    	审核通过
                    </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- 表格主体内容结束 -->
    <div class="footer">
				<div class="page">
					<table>
						<tr>
							<div class="page-box common-page-box" style="text-align:center">
								${requestScope.pageFoot }
							</div>
						</tr>
					</table>

				</div>
			</div>
</div>

</div>
<script>
    function zhiding(id) {
        $.ajax({
            url: "<%=basePath%>cashShop/store/zhiding",
            type: "post",
            data: {"id": id},
            success: function (data) {
                if (data.status == true) {
                    alert("置顶成功");
                    location.reload();
                    
                } else {
                    alert("置顶失败");
                }
            }
        });
    }
</script>
</body>
</html>
