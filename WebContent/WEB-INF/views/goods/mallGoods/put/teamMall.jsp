<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class=" indpdt_mall default">
    <form action="put">
        <input type=hidden name=mallType value="8"/> <!-- 商城类型 -->
        <input type=hidden name=pageflag value="8"/> <!-- 验证类型 -->
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
                    <dt>拼团人数</dt>
                    <dd>
                        <input type="text" name="teamNumber" class="txt"   value="2"  disabled="disabled">
                    </dd>
                </dl>
            	<dl class="stock">
                    <dt><font color="red">*</font>拼团优惠(最终拼团价)</dt>
                    <dd>
                        <input type="text" name="discountPrice" class="txt"  placeholder="请输入优惠金额" value="" style="width: 20%">
                    </dd>
                </dl>
                <dl class="stock">
                    <dt><font color="red">*</font>发布数量</dt>
                    <dd>
                        <input type="text" name="repertory" class="txt" placeholder="请输入拼团商品的数量" value="" style="width: 20%">
                    </dd>
                </dl>
                 <dl class="stock">
                    <dt>限购数量<font>(选填)</font></dt>
                    <dd>
                        <input type="text" name="purchaseNum" class="txt" placeholder="请输入限购数量" value="">
                    </dd>
                </dl>
                <dl class="time add_time">
                    <dt><font color="red">*</font>开始时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=startTime name="startTime" value=""
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                </dl>
                <dl class="time shelf_time">
                    <dt><font color="red">*</font>结束时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=endTime name="endTime" value=""
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
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