<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class="free default">
    <form action="put" id="saveForm" method="post">
        <input type=hidden name=mallType value="6"/> <!-- 商城类型 -->
        <input type=hidden name=pageflag value="4"/> <!-- 验证类型 -->
        <input type=hidden name=baseGoodsId value="${baseGoods.id }"/><!-- 基础商品id -->
        <input type="hidden" name="goodsOrder" value="${reGoodsOfMemberMall.goodsOrder}"><!--此字段用于区分上传的商品是新增还是重新编辑-->
        <div class="free_con default_con">
            <div class="title">会员免单区</div>
            <div class="list">
                <dl class="stock">
                    <dt><font color="red">*</font>库存</dt>
                    <dd>
                        <div style="display: inline;">
                            库存：<input type="text" placeholder="" name="noStandardRepertory" class="txt" value="${reGoodsOfMemberMall.noStandardRepertory}">
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
                        <input type="text" name="displayPrice" class="txt" value="${reGoodsOfMemberMall.displayPrice}">
                    </dd>
                </dl>
                <dl class="status">
                    <dt>状态</dt>
                    ${reGoodsOfMemberMall.isChecked?"<dd>已审核</dd>":"<dd>未审核</dd>"}
                </dl>
                <dl class="dely_way">
                    <dt><font color="red">*</font>配送方式</dt>
                    <dd>
                        ${reGoodsOfMemberMall.transportationType==1?"<input type=radio name='transportationType' value='1' checked/>":"<input type=radio name='transportationType' value='1'/>"}
                        <span>包邮</span>
                        ${reGoodsOfMemberMall.transportationType==2?"<input type=radio name='transportationType' value='2' checked/>":"<input type=radio name='transportationType' value='2'/>"}
                        <span style="margin-right:0;">邮费</span>
                        <input type="text" name="transportationPrice" class="txt" value="${reGoodsOfMemberMall.transportationPrice}">
                        <span>元</span>
                        ${reGoodsOfMemberMall.transportationType==3?"<input type=radio name='transportationType' value='3' checked/>":"<input type=radio name='transportationType' value='3'/>"}
                        <span>到店消费</span>
                        ${reGoodsOfMemberMall.transportationType==4?"<input type=radio name='transportationType' value='4' checked/>":"<input type=radio name='transportationType' value='4'/>"}
                        <span>上门自取</span>
                    </dd>
                </dl>
                <dl class="time add_time">
                    <dt><font color="red">*</font>上架时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=startTime name="addedTime" value="${reGoodsOfMemberMall.addedTime}"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                </dl>
                <dl class="time shelf_time">
                    <dt><font color="red">*</font>下架时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=endTime name="shelvesTime" value="${reGoodsOfMemberMall.shelvesTime}"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                </dl>
            </div>
        </div>
        <div class="operate">
            <div class="title">操作</div>
            <div class="op_btn">
                <%--<input type="button" value="紧急下架" class="click">--%>
                <input type="button" value="提交修改" class="click" onclick="submitAjax();">
            </div>
        </div>
        <!-- 商家编辑商品规格区域，并且上传对应的库存和售价； -->
        <div class="stock_form standardArea">
            <!-- 选择具体属性的区域 -->
            <div class="checkArea">
                <c:forEach items="${firstStandardList}" var="each">
                    <input data-key="${each.id}" value="${each.name}" type="checkbox"
                           onclick="showSellectAndTableHead(this);"/>${each.name}
                </c:forEach>
                ${reGoodsOfMemberMallFirstStandards}
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
                <button type="button" onclick="addItem(this,6,2);">添加</button>
            </div>

            <!-- 生成表格的区域 -->
            <div class="tableArea" style="display: none;">
                <table id="table">
                    <thead>
                    <tr id="tableProperty" class="headLine">
                        ${reGoodsOfMemberMallTableHead}
                        <th>库存</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="newbody">
                    ${reGoodsOfMemberMallTableLine}
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