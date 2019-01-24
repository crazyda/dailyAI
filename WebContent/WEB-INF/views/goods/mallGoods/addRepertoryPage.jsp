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
    <title>补充库存界面</title>
    <link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">

    <link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
</head>

<!-- 展示主体 -->
<div class="main" style="height:auto;">
    <div class="header">
        <div class="header-left">
            <p>你当前的位置：[商城管理]-[商城商品列表]-[补充库存]</p>
        </div>
        <hr align="center"/>
    </div>

    <form action="addRepertory">
        <%--商品id值--%>
        <input type="hidden" name="goodsId" value="${goodsId}"/>
        <%--商城类型--%>
        <input name="mallType" type="hidden" id="id" value="${mallType}">

    <%--商家商城--%>
        <c:if test="${mallType==1}">
            <c:if test="${reGoodsOfSellerMall.isNoStandard}">
                <input type="hidden" name="isNoStandard" value="1"/>
                <div class="r_con_wrap">
                    <div class="r_con_form">
                        <div class="rows">
                            <label>商品规格：</label>
                            <span class="input">
                                <label>商品无规格</label>
                            </span>
                        </div>
                        <div class="rows">
                            <label>增加库存量：</label>
                            <span class="input">
                                <input type="text" name="repertory[]" value="${noStandardRepertory}"/>
                            </span>
                        </div>
                        <div class="rows">
                            <label>操作：</label>
                            <span class="input">
                                <input type="submit" value="提交"/>
                            </span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${!reGoodsOfSellerMall.isNoStandard}">
                <div class="pageColumn">
                    <table class="list" style="width:98%;margin:0 auto;">
                        <thead>
                            ${reGoodsOfSellerMallTableHead}
                        <th>规格</th>
                        <th>库存</th>
                        <th>售价</th>
                        <th style="height: 50px;">操作</th>
                        </thead>
                        <tbody>
                            ${reGoodsOfSellerMallTableLine}
                        </tbody>
                    </table>
                    <button type="submit">提交</button>
                </div>
            </c:if>
        </c:if>

        <%--积分商城--%>
        <c:if test="${mallType==2}">
            <c:if test="${reGoodsOfScoreMall.isNoStandard}">
                <input type="hidden" name="isNoStandard" value="1"/>
                <div class="r_con_wrap">
                    <div class="r_con_form">
                        <div class="rows">
                            <label>商品规格：</label>
                            <span class="input">
                                <label>商品无规格</label>
                            </span>
                        </div>
                        <div class="rows">
                            <label>增加库存量：</label>
                            <span class="input">
                                <input type="text" name="repertory[]" value="${noStandardRepertory}"/>
                            </span>
                        </div>
                        <div class="rows">
                            <label>操作：</label>
                            <span class="input">
                                <input type="submit" value="提交"/>
                            </span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${!reGoodsOfScoreMall.isNoStandard}">
                <div class="pageColumn">
                    <table class="list" style="width:98%;margin:0 auto;">
                        <thead>
                            ${reGoodsOfScoreMallTableHead}
                        <th>库存</th>
                        <th>积分</th>
                        <th style="height: 50px;">操作</th>
                        </thead>
                        <tbody>
                            ${reGoodsOfScoreMallTableLine}
                        </tbody>
                    </table>
                    <button type="submit">提交</button>
                </div>
            </c:if>
        </c:if>

        <%--秒杀商城--%>
        <c:if test="${mallType==3}">
            <c:if test="${reGoodsOfSeckillMall.isNoStandard}">
                <input type="hidden" name="isNoStandard" value="1"/>
                <div class="r_con_wrap">
                    <div class="r_con_form">
                        <div class="rows">
                            <label>商品规格：</label>
                            <span class="input">
                                <label>商品无规格</label>
                            </span>
                        </div>
                        <div class="rows">
                            <label>增加库存量：</label>
                            <span class="input">
                                <input type="text" name="repertory[]" value="${noStandardRepertory}"/>
                            </span>
                        </div>
                        <div class="rows">
                            <label>操作：</label>
                            <span class="input">
                                <input type="submit" value="提交"/>
                            </span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${!reGoodsOfSeckillMall.isNoStandard}">
                <div class="pageColumn">
                    <table class="list" style="width:98%;margin:0 auto;">
                        <thead>
                            ${reGoodsOfSeckillMallTableHead}
                        <th>库存</th>
                        <th>售价</th>
                        <th style="height: 50px;">操作</th>
                        </thead>
                        <tbody>
                            ${reGoodsOfSeckillMallTableLine}
                        </tbody>
                    </table>
                    <button type="submit">提交</button>
                </div>
            </c:if>
        </c:if>

        <%--总部商城--%>
        <c:if test="${mallType==4}">
            <c:if test="${reGoodsOfLocalSpecialtyMall.isNoStandard}">
                <input type="hidden" name="isNoStandard" value="1"/>
                <div class="r_con_wrap">
                    <div class="r_con_form">
                        <div class="rows">
                            <label>商品规格：</label>
                            <span class="input">
                                <label>商品无规格</label>
                            </span>
                        </div>
                        <div class="rows">
                            <label>增加库存量：</label>
                            <span class="input">
                                <input type="text" name="repertory[]" value="${noStandardRepertory}"/>
                            </span>
                        </div>
                        <div class="rows">
                            <label>操作：</label>
                            <span class="input">
                                <input type="submit" value="提交"/>
                            </span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${!reGoodsOfLocalSpecialtyMall.isNoStandard}">
                <div class="pageColumn">
                    <table class="list" style="width:98%;margin:0 auto;">
                        <thead>
                            ${reGoodsOfLocalSpecialtyMallTableHead}
                        <th>库存</th>
                        <th>售价</th>
                        <th>返现比例（%）</th>
                        <th style="height: 50px;">操作</th>
                        </thead>
                        <tbody>
                            ${reGoodsOfLocalSpecialtyMallTableLine}
                        </tbody>
                    </table>
                    <button type="submit">提交</button>
                </div>
            </c:if>
        </c:if>

        <%--久久商城--%>
        <c:if test="${mallType==5}">
            <c:if test="${reGoodsOfNineNineMall.isNoStandard}">
                <input type="hidden" name="isNoStandard" value="1"/>
                <div class="r_con_wrap">
                    <div class="r_con_form">
                        <div class="rows">
                            <label>商品规格：</label>
                            <span class="input">
                                <label>商品无规格</label>
                            </span>
                        </div>
                        <div class="rows">
                            <label>增加库存量：</label>
                            <span class="input">
                                <input type="text" name="repertory[]" value="${noStandardRepertory}"/>
                            </span>
                        </div>
                        <div class="rows">
                            <label>操作：</label>
                            <span class="input">
                                <input type="submit" value="提交"/>
                            </span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${!reGoodsOfNineNineMall.isNoStandard}">
                <div class="pageColumn">
                    <table class="list" style="width:98%;margin:0 auto;">
                        <thead>
                            ${reGoodsOfNineNineMallTableHead}
                        <th>库存</th>
                        <th>售价</th>
                        <th>返现比例（%）</th>
                        <th style="height: 50px;">操作</th>
                        </thead>
                        <tbody>
                            ${reGoodsOfNineNineMallTableLine}
                        </tbody>
                    </table>
                    <button type="submit">提交</button>
                </div>
            </c:if>
        </c:if>

        <%--会员商城--%>
        <c:if test="${mallType==6}">
            <c:if test="${reGoodsOfMemberMall.isNoStandard}">
                <input type="hidden" name="isNoStandard" value="1"/>
                <div class="r_con_wrap">
                    <div class="r_con_form">
                        <div class="rows">
                            <label>商品规格：</label>
                            <span class="input">
                                <label>商品无规格</label>
                            </span>
                        </div>
                        <div class="rows">
                            <label>增加库存量：</label>
                            <span class="input">
                                <input type="text" name="repertory[]" value="${noStandardRepertory}"/>
                            </span>
                        </div>
                        <div class="rows">
                            <label>操作：</label>
                            <span class="input">
                                <input type="submit" value="提交"/>
                            </span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${!reGoodsOfMemberMall.isNoStandard}">
                <div class="pageColumn">
                    <table class="list" style="width:98%;margin:0 auto;">
                        <thead>
                            ${reGoodsOfMemberMallTableHead}
                        <th>库存</th>
                        <th style="height: 50px;">操作</th>
                        </thead>
                        <tbody>
                            ${reGoodsOfMemberMallTableLine}
                        </tbody>
                    </table>
                    <button type="submit">提交</button>
                </div>
            </c:if>
        </c:if>

    </form>
</div>

</body>
</html>
