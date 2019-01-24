<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
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
	<title>幸运抽奖活动设置</title>
	<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/paging.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>

	<!-- artDialog -->
	<link href="<%=basePath%>js/plugins/artDialog-master/artDialog-master/css/ui-dialog.css" rel="stylesheet"/>
	<script src="<%=basePath%>js/plugins/artDialog-master/artDialog-master/dist/dialog-min.js"></script>
	<script  src="<%=basePath%>js/goods/goodsStandard.js"></script>
	 
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
</head>
<style>
.row{Margin-left:80px}
.rows{Margin:20px;text-align:left;}



</style>


<body>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[游戏设置]-[幸运抽奖设置]</p>
				</div>
				
				<hr align="center" />
			</div>

<!-- 填写游戏活动信息 -->
			<div class="row" >
			   <form action="" id="reGoodsForm" method="post">
        	<input type="hidden" id="status" name="status" value="266">
            <div class="r_con_wrap">
                <div class="r_con_form">
                    <div class="rows">
                        <label> 每天抽奖次数：</label>
						<span class="input"> 
							<input type="text" name="drawNum" id="drawNum" class="txt" placeholder="一天可参与抽奖次数" value="${userDarw.drawNum }" >
						</span>
                        <div class="clear"></div>
                    </div>
                     <div class="rows">
                        <label> 每次所需积分：</label>
						<span class="input"> 
							<input type="text" name="drawScore" id = "drawScore" class="txt" placeholder="每次需要消耗多少积分" value="${userDarw.score }" >
						</span>
                        <div class="clear"></div>
                    </div>
                     <div class="rows">
                        <label>抽奖轮播广告：</label>
						 <span class="input"> 
							<wh:UploadUtil fileDirName="basegoods_cover" thumbH="100" thumbW="100" amount="5"
                                           submitName="coverPic[]" targetExt="jpg" relatedChar="${userDarw.coverPic}"></wh:UploadUtil>
						</span> 
                        <div class="clear"></div>
                    </div>
                      
                    <div class="rows">
                        <label>幸运抽奖活动说明：</label>
						<span class="input">
			     			<textarea name="details"  id= "details" clos="1000" rows="2" warp="virtual" >${userDarw.details }</textarea>
			 			</span>
			 			
                        <div class="clear"></div>
                    </div>
                      <div class="rows">
                        <label></label>
						<span class="input"> 
							<input type="button" class="button" value="保存幸运抽奖详情" onclick="submitDarw();"/>
                    	</span>
                    	</div>
                    	
                    	</div>
                    	</from>
                    	
                    
    <!-- 以上是幸运抽奖整个说明 -->
    
    <!-- 以下是每个奖品的设计保存 -->                   
                    <from action="" id="oneDraw" method="post">
                   
                    <div class="r_con_wrap">
                     <div class="rows">
                        <label> 每项奖品设置：</label>
						<span class="input"> 
							<select  name="drawType" id="drawType"  value = "this.value" onchange="show(this.value);" style="width:80px;">
								<c:forEach items="${scoreTypes}" var="type">
									<option value="${type.id }">${type.name }</option>
								</c:forEach>
							</select>
						</span>
                        <div class="clear"></div>
                    </div>
                     <div class="rows" id = "scoreDiv" style="">
                        <label> 每次积分奖励：</label>
						<span class="input"> 
							<input type="text" name="score" class="txt" placeholder="" value="" id = "score">
						</span>
                        <div class="clear"></div>
                    </div>
                     <div class="rows" id="goodsDiv" style="display:none;">
                        <label> 选择参与商家：</label>
						<span class="input"> 
							<select  name="seller" id="seller_id" onchange="show_seller(this.value)">
								<c:forEach items="${sellers }" var="seller">
									<option value="${seller.id }" >${seller.name }</option>
								</c:forEach>
							</select> 
						</span>
						<select  name="goodsOrder" id="goodsOrder" onchange="">
								<c:forEach items="${goods }" var="good">
									<option value="${good.id }">${good.snapshotGoods.name }</option>
								</c:forEach>
						</select> 
						
						
                        <div class="clear"></div>
                    </div>
                     
                     <div class="rows" id="goodsDiv" style="">
                        <label> 奖品中奖概率：</label>
						<span class="input"> 
							<input type="text" id="chance" name="chance" class="txt" placeholder="0.001-100" value="" >
						</span>
                        <div class="clear"></div>
                    </div>
                     <div class="rows">
                        <label>对应介绍广告:</label>
						 <span class="input">
    						<wh:UploadUtil fileDirName="basegoods_cover" thumbH="100" thumbW="100" amount="5"
                                           submitName="contents[]" targetExt="jpg" relatedChar=""></wh:UploadUtil>
			 			</span> 
                        <div class="clear"></div>
                    </div>
                   
                    <div class="rows">
                        <label></label>
						<span class="input"> 
							<input type="button" class="button" value="保存奖项信息" onclick="submitForm();"/>
						</span>
                            <!-- 提交表单，获取返回数据 -->
					
						
                        <div class="clear"></div>
                    </div>
                </div>
                
               </from>
            </div>

        
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
		  var goodsOrder = document.getElementById("goodsOrder");
				
					goodsOrder.options.length=0;
					$.ajax({
						type:"post",
						url:"<%=basePath %>reBaseGoods/changeSeller",
						data: "seller_id="+sellerId,        
				        dataType: "text",
				        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 for(var i in json){	
		    	    		 goodsOrder.options.add(new Option(json[i],i));
		    	    	 }
				
				        }
					});
				
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
		
		$("#oneDraw").submit();
	}
	
	
	</script>		

		<script>

                            function submitForm() {
                              var contents = $("input[name='contents[]']").val();
                              var drawType =  $("#drawType").val();
                              var score = $("#score").val();
                              var seller_id = $("#seller_id").val();
                              var goodsOrder = $("#goodsOrder").val();
                              var chance = $("#chance").val();
                                if (contents == null) {
                                   alert("上传介绍广告");
                                  return; 
                                } 
                                if(drawType==18){
                        			if( score ==null || score==""){
                        				alert("请输入积分");
                        				return;
                        			}
                        		}else if(drawType==19){
                        			if( goodsOrder ==null || goodsOrder==""){
                        				alert("请选择商品");
                        				return;
                        			}
                        		}
                        		
                        		if(chance==null || chance==""){
                        			alert("请输入中奖概率");
                        			return;
                        		}
                                
                                
                                $.ajax({
                                	url: "<%=basePath%>game/activity",
                                    type: "get",
                                    data: "contents="+contents+"&drawType="+drawType+"&score="+score+"&seller_id="+seller_id+"&goodsOrder="+goodsOrder+"&chance="
                                    	+chance,
                                    success: function (data) {
                                    	 alert("添加成功,");
                                    	 location.reload();
                                    	 
                                    },
                                    error :function(XMLHttpRequest, textStatus, errorThrown){
                                    	alert("添加失败");//errorThrown
                                    }
                                });
                            }
							
                            
                            
                            
                            
                       	 function submitDarw() {
                                   var pic = $("input[name='coverPic[]']");
                                   var drawNum = $("#drawNum").val();
                                   var drawScore = $("#drawScore").val();
                                   var details = $("#details").val();
                                   if (pic == null || pic.size() == 0) {
                                       alert("请上传轮播广告");
                                       return;
                                   }
                                   if (drawNum == "") {
                                      alert("请填写每天抽奖次数");
                                     return; 
                                   }
                                   if (drawScore == "") {
                                       alert("请填写每次抽奖消耗积分");
                                      return; 
                                    }
                                   $.ajax({
                                   	   url: "<%=basePath%>game/saveUserDarw",
                                       type: "get",
                                       data: $("#reGoodsForm").serialize(),
                                       success: function (data) {
                                           alert("添加成功");
                                           location.reload();
                                       }
                                   });
                               }
							</script>


		</div>

</body>
</html>
