<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	%>
<li class="deals default">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/goods/goodsStandard.js"></script>
 <script>var basePath =<%=basePath%></script> 
    <form action="put" id="saveForm" method="post">
        <input type=hidden name=mallType value="4"/> <!-- 商城类型 -->
        <input type=hidden name=pageflag value="3"/> <!-- 验证类型 -->
        <input type=hidden name="zoneid" id="zoneid"/>
        <input type=hidden name=baseGoodsId value="${baseGoods.id }"/><!-- 基础商品id -->
        <input type="hidden" name="goodsOrder" value="${reGoodsOfLocalSpecialtyMall.goodsOrder}"><!--此字段用于区分上传的商品是新增还是重新编辑-->
        <div class="deals_con default_con">
            <div class="title">总部商城</div>
            <div class="list">
                <dl class="stock">
                    <dt><font color="red">*</font>库存及单价</dt>
                    <dd>
                        <div style="display: inline;">
                            库存：<input type="text" placeholder="" name="noStandardRepertory" class="txt"
                                      value="${reGoodsOfLocalSpecialtyMall.noStandardRepertory}">
                            单价：<input type="text" placeholder="" name="noStandardPrice" class="txt" value="${reGoodsOfLocalSpecialtyMall.noStandardPrice}">
                            <input type="hidden" name="cashBack" value="${reGoodsOfLocalSpecialtyMall.cashBack}" class="txt">
                           <input class="isNoStandard" type="hidden" name="isNoStandard" value="1"> 
                        </div>

                        <input type="button" value="编辑库存" class="stock_btn editStandard_btn" style="display: none;">
                        <input type="checkbox" checked onclick="clickNoStandard(this);" class="sellerMallNoStandardCheckBox">
                        <span>商品无规格</span>
                    </dd>
                </dl>
                <%--<dl class="discount">
                    <dt>优惠</dt>
                    <dd>
                        <p>
                            <input type="checkbox" name="isDiscount">
                            <span>促销返现：</span>
                            <select>
                                <option value="0.2">20%</option>
                                <option value="0.5">50%</option>
                                <option value="0.8">50%</option>
                                <option value="1.0">100%</option>
                            </select>
                        </p>
                    </dd>
                </dl>--%>
                <dl class="stock">
                    <dt><font color="red">*</font>市场价</dt>
                    <dd>
                        <input type="text" name="displayPrice" class="txt" value="${reGoodsOfLocalSpecialtyMall.displayPrice}">
                    </dd>
                </dl>
                <dl class="status">
                    <dt>状态</dt>
                    ${reGoodsOfLocalSpecialtyMall.isChecked?"<dd>已审核</dd>":"<dd>未审核</dd>"}
                </dl>
                <dl class="dely_way">
                    <dt><font color="red">*</font>配送方式</dt>
                    <dd>
                        ${reGoodsOfLocalSpecialtyMall.transportationType==1?"<input type=radio name='transportationType' value='1' checked/>":"<input type=radio name='transportationType' value='1'/>"}
                        <span>包邮</span>
                        <%-- ${reGoodsOfLocalSpecialtyMall.transportationType==2?"<input type=radio name='transportationType' value='2' checked/>":"<input type=radio name='transportationType' value='2'/>"}
                        <span style="margin-right:0;">邮费</span>
                        <input type="text" name="transportationPrice" class="txt" value="${reGoodsOfLocalSpecialtyMall.transportationPrice}">
                        <span>元</span>
                        ${reGoodsOfLocalSpecialtyMall.transportationType==3?"<input type=radio name='transportationType' value='3' checked/>":"<input type=radio name='transportationType' value='3'/>"}
                        <span>到店消费</span>
                        ${reGoodsOfLocalSpecialtyMall.transportationType==4?"<input type=radio name='transportationType' value='4' checked/>":"<input type=radio name='transportationType' value='4'/>"}
                        <span>上门自取</span> --%>
                    </dd>
                </dl>
                <dl class="time add_time">
                    <dt><font color="red">*</font>上架时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=startTime name="addedTime" value="${reGoodsOfLocalSpecialtyMall.addedTime}"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                </dl>
                <dl class="time shelf_time">
                    <dt><font color="red">*</font>下架时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=endTime name="shelvesTime" value="${reGoodsOfLocalSpecialtyMall.shelvesTime}"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                </dl>

				
				<dl class="time add_time place">
                    <dt>已保存产地</dt>
                   <font color="red" style="margin-left: 5%;margin-bottom: 3%;" id="cityAndcounty">${reGoodsOfLocalSpecialtyMall.provinceEnum.provinceEnum.name}- ${reGoodsOfLocalSpecialtyMall.provinceEnum.name}</font>
                </dl>
                
                <dl class="time add_time place">
                    <dt><font color="red">*</font>所在产地</dt>
                    <dd>
                    <input type="hidden"  class="txt"  name="placeOfProduction" value=""> 
					                   省：
						<select  name="province" id="provinceId" onchange="changeZone();gotoselect()">
							<option value=0>请选择</option>
							<c:forEach items="${zoneList }" var="zone">
								<option value="${zone.id }" <c:if test="${zone.id == adminUser.provinceEnum.provinceEnum.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
							</c:forEach>
						</select>
						市：
						<select  name="city" id="cityId" onchange="changeZone();gotoselect()">
							<option value=0>请选择</option>
					</select> 
					
					区县：
					<select  name="county" id="countyId" onchange="gotoselect()" >
							<option value=0>请选择</option>
					</select>
                       
                    </dd>
                </dl>
                
                <dl class="time type place">
                	<dt>已选择类别</dt>
                	<font color="red" style="margin-left: 5%;margin-bottom: 3%;"> ${reGoodsOfLocalSpecialtyMall.commodityType.commodityType.name } - ${reGoodsOfLocalSpecialtyMall.commodityType.name }</font>
                </dl>
                
                <dl class="time type place">
                    <dt><font color="red">*</font>所在类别</dt>
                    <dd>
                    <input type="hidden"  class="txt"  name="placeOfProduction" value=""> 
					                   一级分类：
						<select id="first_model" onchange="seleceModel(this)">
							<option value="">请选择</option>
							<c:forEach items="${commodityTypeList }" var="m">
								<option value="${m.id }">${m.name }</option>
							</c:forEach>
						</select>
						二级分类：
						<select  name="modelId" id="second_model">
							<option value="">请选择</option>
						</select> 
                    </dd>
                </dl>
                       <script type="text/javascript">
	                       function seleceModel(selectNode){
	                    		var levelId = selectNode.value;
	                    		second_model.length = 1;
	                    		$.get("<%=basePath %>cashShop/goodsType/getTypeByLevel?modelId=2&parentId="+levelId,function(json){
	                    			$.each(json,function(i,d){
	                    				$("#second_model").append("<option value='"+d.id+"'>"+d.name+"</option>");
	                    			});	
	                    		});	
	                    	}
                       
                       </script>
                <dl class="time sales place">
                    <dt><font color="red">*</font>默认销量</dt>
                    <dd>
                       <input type="text" name="sales" class="txt" value="${reGoodsOfLocalSpecialtyMall.sales}"/>
                    </dd>
                </dl>
                
                <dl class="time add_time package">
                    <dt><font color="red">*</font>商品包装</dt>
                    <dd>
                        <input type="text" placeholder="请输入包装类型" class="txt" name="pack" value="${reGoodsOfLocalSpecialtyMall.pack}">
                    </dd>
                </dl>
            </div>
        </div>
        <div class="operate">
            <div class="title">操作</div>
            <div class="op_btn">
               <%-- <input type="button" value="紧急下架" class="click">--%>
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
                ${reGoodsOfLocalSpecialtyMallFirstStandards}
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
                <button type="button" onclick="addItem(this,4,4);">添加</button>
            </div>

            <!-- 生成表格的区域 -->
            <div class="tableArea" style="display: none;">
                <table id="table">
                    <thead>
                    <tr id="tableProperty" class="headLine">
                         ${reGoodsOfLocalSpecialtyMallTableHead} 
                        <th>库存</th>
                        <th>售价</th>
                        <!-- <th>返现比例（%）</th> -->
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="newbody">
                    	 ${reGoodsOfLocalSpecialtyMallTableLine}
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