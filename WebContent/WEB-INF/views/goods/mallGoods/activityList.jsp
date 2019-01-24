<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>游戏活动列表</title>
	<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

	<!-- 分页插件 -->
	<link href="<%=basePath%>js/plugins/pagination/mricode.pagination.css" rel="stylesheet" />
	<script src="<%=basePath%>js/plugins/pagination/mricode.pagination.js"></script>

	<!-- artDialog -->
	<link href="<%=basePath%>js/plugins/artDialog-master/artDialog-master/css/ui-dialog.css" rel="stylesheet"/>
	<script src="<%=basePath%>js/plugins/artDialog-master/artDialog-master/dist/dialog-min.js"></script>
	<script  src="<%=basePath%>js/goods/goodsStandard.js"></script>
</head>
<style>
.row{Margin-left:80px}
.rows{Margin:20px;text-align:left;}



</style>


<body>

		<!-- 展示主体 -->
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[游戏管理]-[活动列表]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="javascript:location.href=''" style="cursor: pointer;">
						<img src="<%=basePath%>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>

<!-- 填写游戏活动信息 -->
			<div class="row" >
			   <form id="saveForm" method="post" action="<%=basePath %>/reBaseGoods/activity">
					<input id="currentPage" type="hidden" name="currentPage" value="${pageResult.currentPage }"/><!-- 当前页 -->
					<input id="pageSize" type="hidden" name="pageSize" value="${pageResult.pageSize }"/><!-- 每页显示的条数 -->
					<input id="totalCount" type="hidden" name="totalCount" value="${pageResult.totalCount }"/><!-- 总条数 -->
				<div class="rows">
					活动是否启动：
					<input type="radio" name="isStart" class="" id="" value="1">启动
                    <input type="radio" name="isStart" class="" id="" value="2">不启动
				</div>
				<div class="rows">
						抽奖次数：
						<input type="text" name="drawNum" class="txt" placeholder="一天可参与抽奖次数" value="${userDarw.drawNum }" >
					</div>
					<div class="rows">
						每次抽奖积分数：
						<input type="text" name="drawScore" class="txt" placeholder="每次需要消耗多少积分" value="${userDarw.score }" >
					</div>
					<div class="rows">
						<label> 封面图片：</label>
						<span class="input"> 
						 <img src="${RESOURCE_LOCAL_URL}${userDarw.coverPic}" style="width: 150px;height: 150px;" name="coverPic[]"/> 
						<wh:UploadUtil fileDirName="gameImg" thumbH="100" thumbW="100" amount="5" 
                                           submitName="coverPic[]" targetExt="jpg" relatedChar="${userDarw.coverPic}"></wh:UploadUtil>
                    	</span>
                    </div>
                    <div class="rows">
					活动介绍：<textarea name="details" clos=",50" rows="5" warp="virtual" >${userDarw.details}</textarea>
			 		  </div>
			 		  
			 		  	
					<div class="rows">
						奖品设置：
						<select  name="drawType" id="drawType"  value = "this.value" onchange="show(this.value);" style="width:80px;">
						<c:forEach items="${scoreTypes}" var="type">
								<option value="${type.id }">${type.name }</option>
						</c:forEach>
					</select>
					
					</div>
					
					
					<div class="rows" id = "scoreDiv" style="">
						输入积分：<input type="text" name="score" class="txt" placeholder="" value="" id = "score">
						
						
						</div>
					<div class="rows" id="goodsDiv" style="display:none;">
						选择商家：
						<select  name="seller" id="seller_id" onchange="show_seller(this.value)">
						<c:forEach items="${sellers }" var="seller">
								<option value="${seller.id }" >${seller.name }</option>
						</c:forEach>
					</select> 
					选择商品：
					<select  name="goodsOrder" id="goodsOrder" onchange="">
						<c:forEach items="${goods }" var="good">
								<option value="${good.id }">${good.snapshotGoods.name }</option>
						</c:forEach>
					</select>
						
					</div>
					
						<div class="rows" id="goodsDiv" style="">
						中奖概率：<input type="text" id="chance" name="chance" class="txt" placeholder="0.001-100" value="" >注:价值高的设置0.001
						<input type="button" value="确定" class="click" onclick="submitAjax();">
						</div>
					<%-- <div class="" id="yhqDiv" style="display:none;">
						选择优惠券：
						<select  name="" id="" onchange="">
						<c:forEach items="" var="type">
								<option value=""></option>
						</c:forEach>
					</select>
						中奖概率：<input type="text" name="chanceScore" class="txt" placeholder="" value="" >
					</div> --%>
					
					
					
			    </form>
			</div>
	<script>
	
	function show(type){
		if(type==18){
			$("#scoreDiv").show();
			$("#goodsDiv").hide();
			$("#yhqDiv").hide();
		}else if(type==19){
			
			$("#scoreDiv").hide();
			$("#goodsDiv").show();
			$("#yhqDiv").hide();
		}else if(type==21){
			/* $("#scoreDiv").hide();
			$("#goodsDiv").hide();
			$("#yhqDiv").show(); */
			
		}else if(type==20){
			$("#scoreDiv").hide();
			$("#goodsDiv").hide();
			$("#yhqDiv").hide();
		}
	}
	
	
	function show_seller(sellerId){
		
		var ad = event.srcElement;
		  var seller_id = document.getElementById("seller_id");
		  var goodsOrder = document.getElementById("goodsOrder");
		
				if(parseInt(ad.value)>0){
					if(ad==seller_id){
						goodsOrder.options.length=0;				
					}
					goodsOrder.options.length=0;
				
					$.ajax({
						type:"post",
						url:"<%=basePath %>reBaseGoods/changeSeller",
						data: "seller_id="+ad.value,        
				        dataType: "text",
				        success: function (data){
				        	var json=eval( '('+data+ ')' );
			    	    	 if(ad==seller_id){
			    	    		 goodsOrder.options.add(new Option("请选择", ""));
			    	    	 }
			    	    	 
			    	    	 for(var i in json){	
			    	    		 if(ad==seller_id){
			    	    			 goodsOrder.options.add(new Option(json[i],i));
			    	    		 }else{
			    	    			 goodsOrder.options.add(new Option(json[i],i));
			    	    		 	
			    	    		 }
			    	    	 }
				
				        }
					});
				}
	}
	
	
	
	function submitAjax(){
		var goods = $("#goodsOrder").val();
		var score = $("#score").val();
		var drawType =$("#drawType").val();
		var chance = $("#chance").val();
		if(drawType==18){
			if( score ==null || score==""){
				alert("请输入积分");
				return;
			}
		}else if(drawType==19){
			if( goods ==null || goods==""){
				alert("请选择商品");
				return;
			}
		}
		
		if(chance==null || chance==""){
			alert("请输入中奖概率");
			return;
		}
		
		document.getElementById("saveForm").submit();
	}
	
	
	
	
	
	
	
	
	</script>		
                
              





			<!-- 游戏活动列表 -->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						
						<th width="5%" style="height:50px;">序号</th>
						<th width="30%">奖品</th>
						<th width="5%">概率</th>
						<th width="10%">创建时间</th>
						<th width="10%">操作</th>
					</thead>
					<tbody>
						<c:forEach items="${pageResult.result }" var="each">
							<tr>
								<td>${each.id }</td>
								<c:if test="${each.drawYlassify.id eq 18 }">
								<td>${each.score}${each.drawYlassify.name }</td>
								</c:if>
								<c:if test="${each.drawYlassify.id eq 19 }"> 
								<td>${each.reGoodsOfSellerMall.snapshotGoods.name }</td>
								</c:if>
								<c:if test="${each.drawYlassify.id eq 20 }"> 
								<td>${each.drawYlassify.name }</td>
								</c:if>
								<td> ${each.chanceScore}%	</td>
								<td>
									<fmt:formatDate value="${each.createTime }" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<div align="center">
										<span class="STYLE4"> 
											<span style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else del('${each.id }');">
												<img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />删除
											</span>
											<script>
												function del(deleteId){
													$.post('<%=basePath%>reBaseGoods/_delActivity',{id:deleteId},function(data){
														window.location.reload();
														
													});
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
			<!-- 表格主体内容结束 -->

			<!-- 分页条开始 -->
			<div style="margin-top: 50px;margin-bottom: 50px;text-align: center;margin-left: 20px;">

				<div id="page" class="m-pagination" ></div>
				<div id="eventLog"></div>
				<script>
					(function() {
						$("#page").pagination({
							pageIndex: $("#currentPage").val()-1,
							pageSize: $("#pageSize").val(),
							total:$("#totalCount").val(),
							debug: true,
							showJump: true,
							showPageSizes: true,
							pageElementSort: ['$page', '$size', '$jump', '$info']
						});
					})();
					$("#page").on("pageClicked", function(event, data) {
						$("#currentPage").val(data.pageIndex+1);
						$("#searchOrChangePage").submit();
					}).on('jumpClicked', function(event, data) {
						$("#currentPage").val(data.pageIndex+1);
						$("#searchOrChangePage").submit();
					}).on('pageSizeChanged', function(event, data) {
						$("#pageSize").val(data.pageSize);
						$("#searchOrChangePage").submit();
					});
				</script>
			</div>
			<!-- 分页条开结束-->
		</div>
		<!-- 分页条结束 -->

		
		<!-- 展示主体结束 -->

</body>
</html>
