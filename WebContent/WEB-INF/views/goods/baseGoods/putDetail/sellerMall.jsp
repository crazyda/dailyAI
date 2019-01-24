<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class=" indpdt_mall default">
    <form action="put">
        <input type=hidden name=mallType value="1"/> <!-- 商城类型 -->
        <input type=hidden name=pageflag value="7"/> <!-- 验证类型 -->
        <input type=hidden name=baseGoodsId value="${baseGoods.id }"/><!-- 基础商品id -->
        <input type="hidden" name="goodsOrder" value="${reGoodsOfSellerMall.goodsOrder}"><!--此字段用于区分上传的商品是新增还是重新编辑-->
        <div class="indpdt_mall_con default_con">
            <div class="title">商家商城</div>
            <div class="list">
                <dl class="stock">
                    <dt><font color="red">*</font>库存及单价</dt>
                    <dd>
                    <!-- //da -->
                        <div style="display: inline;">
                            库存：<input type="text" placeholder="" name="noStandardRepertory" class="txt" value="${reGoodsOfSellerMall.noStandardRepertory}">

                           单价：<input type="text" placeholder="" name="noStandardPrice" class="txt" value="${reGoodsOfSellerMall.noStandardPrice}"> 

                       		赠送积分:<input type="text" placeholder="" name="score" class="txt" value="${reGoodsOfSellerMall.sendScoreNum}"> 
                            <input class="isNoStandard" type="hidden" name="isNoStandard" value="1">
                        </div>

                        <input type="button" value="编辑库存" class="stock_btn editStandard_btn" style="display: none;">
                        <input type="checkbox" checked onclick="clickNoStandard(this);" class="sellerMallNoStandardCheckBox">
                        <span>商品无规格</span>
                    </dd>
                </dl>
                <dl class="stock">
                    <dt><font color="red">*</font>市场价</dt>
                    <dd>

                        <input type="text" name="displayPrice" class="txt" value="${reGoodsOfSellerMall.displayPrice}">

                    </dd>
                </dl>
                
               
                
                
                <%--<dl class="discount">
                    <dt>优惠</dt>
                    <dd>
                        <p>
                            <input type="checkbox" name="isDiscount">
                            <span>红包可抵：</span>
                            <select name="redPaperDiscount">
                                <option value="0.2">20%</option>
                                <option value="0.5">50%</option>
                                <option value="0.8">50%</option>
                                <option value="1.0">100%</option>
                            </select>
                        </p>
                    </dd>
                </dl>--%>
                <dl class="status">
                    <dt>状态</dt>
                    ${reGoodsOfSellerMall.isChecked?"<dd>已审核</dd>":"<dd>未审核</dd>"}
                </dl>
                <dl class="dely_way">
                    <dt>配送方式</dt>
                    <dd>
                        ${reGoodsOfSellerMall.transportationType==1?"<input type=radio name='transportationType' value='1' checked/>":"<input type=radio name='transportationType' value='1'/>"}
                        <span>包邮</span>
                        ${reGoodsOfSellerMall.transportationType==2?"<input type=radio name='transportationType' value='2' checked/>":"<input type=radio name='transportationType' value='2'/>"}
                        <span style="margin-right:0;">邮费</span>
                        <input type="text" name="transportationPrice" class="txt" value="${reGoodsOfSellerMall.transportationPrice}">
                        <span>元</span>
                        ${reGoodsOfSellerMall.transportationType==3?"<input type=radio name='transportationType' value='3' checked/>":"<input type=radio name='transportationType' value='3'/>"}
                        <span>到店消费</span>
                        ${reGoodsOfSellerMall.transportationType==4?"<input type=radio name='transportationType' value='4' checked/>":"<input type=radio name='transportationType' value='4'/>"}
                        <span>上门自取</span>
                    </dd>
                </dl>
                <dl class="time add_time">
                    <dt><font color="red">*</font>上架时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=startTime name="addedTime" value="${reGoodsOfSellerMall.addedTime}"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                </dl>
                <dl class="time shelf_time">
                    <dt><font color="red">*</font>下架时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=endTime name="shelvesTime" value="${reGoodsOfSellerMall.shelvesTime}"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                    </dl>
            </div>
        </div>
         <div class="operate"><!-- da -->
            <div class="title">权益保障</div>
            	
            <span class="input goodsLables"><!-- 回显问题 -->
				<input type=checkbox name=rightsId[] value="1"/>&nbsp;正品保证&nbsp;&nbsp;&nbsp;
				<input type=checkbox name=rightsId[] value="2"/>&nbsp;售后无忧&nbsp;&nbsp;&nbsp;
				<input type=checkbox name=rightsId[] value="3"/>&nbsp;快速发货&nbsp;&nbsp;&nbsp;
			</span>
        </div>
        <div class="operate">
            <div class="title">操作</div>
            <div class="op_btn">
                <%--<input type="button" value="紧急下架" class="click">--%>
                <input type="button" value="提交修改" class="click" onclick="submitAjax();">
            </div>
        </div>
        <div class="stock_form standardArea">
            <!-- 选择具体属性的区域 -->
            <div class="checkArea">
                <c:forEach items="${firstStandardList}" var="each">
                    <input data-key="${each.id}" value="${each.name}" type="checkbox"
                           onclick="showSellectAndTableHead(this);"/>${each.name}
                </c:forEach>
                ${reGoodsOfSellerMallFirstStandards}
            </div>

            <!-- 选择属性明细的区域 -->
            <div class="selectArea" style="display: none;">
                <c:forEach items="${firstStandardList}" var="each">
                    <select data-key="${each.id}" data-value="${each.name}" style="display: none;">
                        <c:forEach items="${each.secondStandards}" var="item">
                            <option value="${item.id}">${item.name}</option>
                        </c:forEach>
                    </select>
                </c:forEach>
                <button type="button" onclick="addItem(this,1,4);">添加</button>
            </div>

            <!-- 生成表格的区域 -->
            <div class="tableArea" style="display: none;">
                <table id="table">
                    <thead>
                    <tr id="tableProperty" class="headLine">
                        ${reGoodsOfSellerMallTableHead}
                        <th>库存</th>
                       <th>售价</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="newbody">
                    ${reGoodsOfSellerMallTableLine}
                    </tbody>
                </table>
            </div>

            <div class="btm">
                <div class="btm_in">
                    <input type="button" value="取消">
                    <input type="button" value="保存" class="click">
                </div>
            </div>
        </div>
    </form>
</li>