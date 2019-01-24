<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
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
                    <dt><font color="red">*</font>游戏类型</dt>
                    <dd>
                        <select  name="region" id="region" onchange="show(this.value);">
						<c:forEach items="${gameTypes }" var="type">
								<option value="${type.commodityType.id}" <c:if test="${ type.id == typeId}" >selected="selected"</c:if> >${type.name }</option>
						</c:forEach>
					</select>
                    </dd>
                </dl>
              
                
                
                
                <dl class="stock" id="nump" style="display:none;">
                    <dt>参与人数</dt>
                    <dd>
                    	 <input type="text" name="nump" class="txt" placeholder="请输入参与人数" value="" style="width: 20%">
                    </dd>
                </dl>
                
                
                
                <dl class="stock" id ="numscore" style="display:none;">
                    <dt>兑换资格积分数</dt>
                    <dd>
                   		<input type="text" name="numscore" class="txt" placeholder="请输入兑换资格积分数" value="" style="width: 20%">
                    </dd>
                </dl>
                 
            	
            	<dl class="stock" id="way" style="display:none;">
                    <dt><font color="red">*</font>中奖方式</dt>
                    <dd>
                        <input type="radio" name="way" class="" id="" value="1">随机中奖码
                        <input type="radio" name="way" class="" id="" value="2">人工中奖码
                        
                    </dd>
                </dl>
                
                <dl class="time add_time" id="startTime_265" style="display:none;">
                    <dt><font color="red">*</font>开始时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=startTime name="startedTime" value=""
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                </dl>
                <dl class="time shelf_time" id="endTime_265" style="display:none;">
                    <dt><font color="red">*</font>结束时间</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=endTime name="endedTime" value=""
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"/>
                    </dd>
                </dl>
                
                <!--倒计时时间  -->
                <!-- <dl class="time add_time" id="Time_267" style="display:none;">
                    <dt><font color="red">*</font>购买时间范围:</dt>
                    <dd>
                        <input placeholder="点击选择时段" class="txt" type=text id=startTime name="startTime" value=""onClick="WdatePicker({dateFmt:'HH:mm'});"/>
                   		-&nbsp;-&nbsp;-&nbsp;-&nbsp;-
                   		<input placeholder="点击选择时段" class="txt" type=text id=endTime name="endTime" value="" onClick="WdatePicker({dateFmt:'HH:mm'});"/>
                    </dd>
                </dl> -->
                
                <dl class="time add_time" id="Time_267" style="display:none;">
                    <dt><font color="red">*</font>购买时间范围:</dt>
                    <dd class="seckillTimes">
                        <c:forEach items="${timesList }" var="times">
                            <input type="checkbox" name="buytimeId" class="times"
                                   value=${times.id }>&nbsp;${times.startTime }~${times.endTime }&nbsp;&nbsp;
                        </c:forEach>
                    </dd>
                    <script>
                        //用于回显秒杀时间段；
                        $(function () {
                            var a =${reGoodsOfSeckillMall.arrayForReturnBack};
                            var inputs = $(".seckillTimes input");
                            $.each(inputs, function (index, obj) {
                                var v = $(obj).val();
                                for (var i = 0; i < a.length; i++) {
                                    if (v == a[i]) {
                                        $(obj).prop("checked", true);
                                    }

                                }
                            });
                        });
                    </script>
                </dl>
                
                
                
                
                
                
                <dl class="stock" id ="desc" style="display:none;">
                    <dt><font color="red">*</font>抽奖说明</dt>
                    <dd>
                        <input type="text" name="changeDesc" class=""  placeholder="标明抽奖活动说明规则。" value="" style="width: 50%; height: 10%">
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
<script>
function show(gameType){
	if(gameType==265){
		$("#nump").show();
		$("#way").show();
		$("#numscore").show();
		$("#startTime_265").show();
		$("#endTime_265").show();
		$("#Time_267").hide();
		$("#desc").show();
		
	}else if(gameType==267){
		$("#nump").hide();
		$("#way").hide();
		$("#numscore").show();
		$("#startTime_265").show();
		$("#endTime_265").show();
		$("#Time_267").show();
		$("#desc").show();
		
	}else{
		
	}
}



</script>
