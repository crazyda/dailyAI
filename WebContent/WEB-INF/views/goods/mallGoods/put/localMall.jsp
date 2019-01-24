<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class=" indpdt_mall default">
    <form action="put">
        <input type=hidden name=mallType value="4"/> <!-- 商城类型 -->
        <input type=hidden name=pageflag value="4"/> <!-- 验证类型 -->
        <input type=hidden name=baseGoodsId value="${baseGoods.id }"/><!-- 基础商品id -->
        <input type="hidden" name="goodsOrder" value="${reGoodsOfSellerMall.goodsOrder}"><!--此字段用于区分上传的商品是新增还是重新编辑-->
        <div class="indpdt_mall_con default_con">
            <div class="title">商家商城</div>
            <div class="list">
            	<dl class="stock">
                    <dt>商品名称</dt>
                    <dd>
                        ${reGoodsOfSellerMall.snapshotGoods.name}
                    </dd>
                </dl>
              <dl class="stock">
                    <dt>商品价格</dt>
                    <dd>
                    <c:choose>
                    	<c:when test="${!reGoodsOfSellerMall.isNoStandard}">
                    		${reGoodsOfSellerMall.price}&nbsp;元
                    	</c:when>
                    	<c:otherwise> ${reGoodsOfSellerMall.noStandardPrice}&nbsp;元</c:otherwise>
                    </c:choose>
                    </dd>
                </dl>
                <dl class="stock">
                    <dt>配送方式</dt>
                    <dd>
                    <c:choose>
                    	<c:when test="${reGoodsOfSellerMall.transportationType==1}">
                    		包邮
                    	</c:when>
                    	<c:when test="${reGoodsOfSellerMall.transportationType==2}">
                    		邮费：${reGoodsOfSellerMall.transportationPrice}
                    	</c:when>
                    	<c:when test="${reGoodsOfSellerMall.transportationType==3}">
                    		到店消费
                    	</c:when>
                    	<c:otherwise>上门自提</c:otherwise>
                    </c:choose>
                    </dd>
                </dl>
                <dl class="stock">
                    <dt>下架时间</dt>
                    <dd style="font-style: red;">
                       	下架时间为审核通过后的180天
                    </dd>
                </dl>
            </div>
        </div>
        <div class="operate">
            <div class="title">操作</div>
            <div class="op_btn">
                <input type="button" value="发布推广" class="click" onclick="submitAjax();">
            </div>
        </div>
        
    </form>
</li>