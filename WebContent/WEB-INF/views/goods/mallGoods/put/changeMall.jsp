<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<li class=" indpdt_mall default">
    <form action="put">
        <input type=hidden name=mallType value="7"/> <!-- 商城类型 -->
        <input type=hidden name=pageflag value="7"/> <!-- 验证类型 -->
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
                    	双方协商
                    </dd>
                </dl>
                
            	<dl class="stock">
                    <dt><font color="red">*</font>换货商品描述</dt>
                    <dd>
                        <input type="text" name="changeDesc" class=""  placeholder="尽量详细描述您的宝贝有助于提高成交哦！如：换货原因，新旧程度，详尽规格，宝贝质量。" value="" style="width: 50%; height: 10%">
                    </dd>
                </dl>
                
                  <dl class="stock">
                    <dt>想换(选填,例如输入'数码')</dt>
                    <dd>
                        <input type="text" name="lable1" class="txt" placeholder="标签1" value="" style="width: 10%">
                    </dd>
                    <dd>
                        <input type="text" name="lable2" class="txt" placeholder="标签2" value="" style="width: 10%">
                    </dd>
                    
                </dl>
                <c:choose>
                	<c:when test="${reGoodsOfSellerMall.isNoStandard}">
                		<dl class="stock">
		                    <dt><font color="red">*</font>商品数量</dt>
		                    <dd>
		                        <input type="text" name="stock" class="txt"  placeholder="请输入换货商品数量" value="" style="width: 20%">
		                    </dd>
                		</dl>
                	</c:when>
                	<c:otherwise>
                	
                		<dl class="stock">
		                    <dt><font color="red">*</font>商品规格</dt>
		                    <dd>
		                    <c:forEach items="${standard }" var="standard">
		                    	<input type="radio" id="radio" name="standardId" value="${standard.id1}" style="margin-left: 3%">
		                    	${standard.name1}
                            </c:forEach>
		                    </dd>
		                    <dd>
                            <input type="text" value="" id="stock" name="stock" placeholder="规格库存" style="margin-left: 3%;text-align: center;">
                            </dd>
                		</dl>
                		 
                	</c:otherwise>
                
                </c:choose>
               
                
            </div>
        </div>
        
        <input type="hidden" id="standardName" name="standardName" value=""> 
		<input type="hidden" id="price" name="price" value="">  
        <input type="hidden"  id="want"  name="want" class="txt"  value="" >
        <input type="hidden" id="standardArray" name="standardArray" value="">
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
        
        $("input[name='standardId']").click(function(){
        	var list = '<%=request.getAttribute("standard")%>';
       		var json =eval('(' + list + ')');
       		$.each($('input:radio'),function(){
       			if(this.checked){
       				for(var i=0;i<json.length;i++){
		      			if($(this).val()==json[i].id1){
		      				$("#stock").attr("value",json[i].repertory);
		      				$("#standardName").attr("value",json[i].name1);
		      				$("#price").attr("value",json[i].price);
		      			}
       				}
       			}else{
       				$("#stock").remove('value');
       			}
       			
            });
            
        });
    	
        </script>
    </form>
</li>