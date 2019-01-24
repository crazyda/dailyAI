<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class=" indpdt_mall default">
    <form action="put">
        <input type=hidden name=mallType value="9"/> <!-- 商城类型 -->
        <input type=hidden name=pageflag value="9"/> <!-- 验证类型 -->
        <input type=hidden name=baseGoodsId value="${baseGoods.id }"/><!-- 基础商品id -->
        <input type="hidden" name="goodsOrder" value="${reGoodsOfSellerMall.goodsOrder}"><!--此字段用于区分上传的商品是新增还是重新编辑-->
        <div class="indpdt_mall_con default_con">
            <div class="title">优惠券商城</div>
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
                    <dt><font color="red">*</font>优惠券价</dt>
                    <dd>
                        <input type="text" name="price" class="txt"  placeholder="请输入优惠券面值" value=""  style="width: 20%">
                    </dd>
                </dl>
                <dl class="stock">
                    <dt><font color="red">*</font>推广费用</dt>
                    <dd>
                        <input type="text" name="rewardFee" class="txt" placeholder="请输入每张券给推广员的奖励佣金" value="" style="width: 20%">
                    </dd>
                </dl>
                <dl class="stock">
                    <dt><font color="red">*</font>发券数量</dt>
                    <dd>
                        <input type="text" name="stock" class="txt" placeholder="请输入发券的数量" value="" style="width: 20%">
                    </dd>
                </dl>
                 <dl class="stock">
                    <dt><font color="red">*</font>发券区域</dt>
                    <dd>
                        <input type="radio" name="region" class="txt"  value="0" style="width: 12px">
                        周边
                        <input type="radio" name="region" class="txt"  value="1" style="width: 12px; margin-left: 15px">
                        全国
                    </dd>
                </dl>
                 <dl class="time">
                    <dt><font color="red">*</font>券有效期</dt>
                    <dd>
                        <select id="validityTime" name="validityTime">
                        	<option value="-1">请选择券有效期</option>
                        	<option value="1">3天</option>
                        	<option value="2">7天</option>
                        	<option value="3">15天</option>
                        	<option value="4">30天</option>
                        	<option value="5">90天</option>
                        </select>
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
        
        
        <script>
           $(document).ready(function(){  
		    	$('input[type=radio]').bind('click', function(){  
		       		 $('input[type=radio]').not(this).attr("checked", false);  
	    		});  
			}); 
    	
        </script>
    </form>
</li>