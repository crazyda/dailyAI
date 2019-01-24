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
 <style type="text/css">
    	.pics{
    		position: absolute;
    		top:150px;
    		left:150px;
    		width:70%;
    		height: 70%;
    	}
    	.pics>img{
    		display:inline-block;
    		width:90%;
    		height: 90%;
    	}
    </style>

<body>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[商品审核]-[店铺审核]</p>
        </div>
        <hr align="center"/>
    </div>
    <!-- 表格主体内容 -->
    <div class="pageColumn">
        <table class="list" style="width:98%;margin:0 auto;">
            <thead>
            <th width="5%" style="height:50px;">序号</th>
            <th width="10%">店铺名称</th>
            <th width="10%">联系方式</th>
            <th width="10%">商家身份证</th>
            <th width="10%">店铺地址</th>
            <th width="10%">营业执照</th>
            <th width="10%">提交时间</th>
            <th width="10%">操作</th>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.slist}" var="seller">
                <tr>
                    <td>${seller.id}</td>
                   <td>${seller.name}</td> 
                    <td>${seller.phone}</td>
                    <td>${seller.sellerIdCard}</td>
                    <td>${seller.address}</td>
                    <td><img class="pic" src="${RESOURCE_LOCAL_URL}${seller.businessLicencePic}" style="width:80px;height:80px;"/></td>
                    <td>
                        <fmt:formatDate value="${seller.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <div align="center">
                            <span class="STYLE4">
                                <span onclick="check('${seller.id }');" style="cursor: pointer;">
                                    <img src="<%=basePath%>constant/tab/images/edt.gif" width="16" height="16"/>审核
                                </span>
                                <script>
                                    function check(sellerId) {
                                        location.href = "<%=basePath%>cashShop/store/detailOfStoreVerify?sellerId="+sellerId;
                                    }
                                </script>
                            </span>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
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
    <!-- 表格主体内容结束 -->
    
 	<!-- 分页条开始 -->
</div>

</div>
	<script type="text/javascript">
	  $(function(){
          $(".pic").click(function(){
        	  var pic=this;
              $("body").append("<div class=pics><img src="+this.src+"></img></div>");
              $(".pics").click(function(){
            		$(this).hide();  
              });
          });
      });

	</script>
</body>
</html>
