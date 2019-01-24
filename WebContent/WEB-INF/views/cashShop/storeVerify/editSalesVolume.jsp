<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>修改销量</title>
    <link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link type="text/css" rel="stylesheet" href="<%=basePath %>layui/css/layui.css">
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/layer3/layer.js"></script>
	<script src="<%=basePath %>layui/layui.js" charset="utf-8"></script>
	<script type="text/javascript">var basePath="<%=basePath %>"</script>
	
	<style type="text/css">
		.layui-table th{
			background-color: #0099ff;
			color:#ffffff; 
			font-weight:bold;
			text-align:center
		}
		
		.layui-table td{
			text-align:center;		
		}
		.layui-input-submit{
			margin:0 auto;
			text-align:center;
		}
		.layui-input-submit input{
			width:10%;	
			height:3%;
			background-color:#0099ff;
			text-align: center;
			font-size: 12px; 
			color:#ffffff;
		    font-weight:bold;
		    outline:none;
		    border-radius:8px;
		}
	</style>
  </head>
  <body>
   	 <div class="header">
			<div class="header-left">
				<p>你当前的位置：[商城管理]-[修改销量]</p>
			</div>
			<hr align="center" />
		</div>
  	  <div>
   
   <form class="" action="<%=basePath%>cashShop/store/editSalesVolume">
  		  <div class="layui-row">
               <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                    <div class="grid-demo grid-demo-bg1">
                         <label class="layui-form-label">商城类别:</label>
						    <div class="layui-input-block" >
						      <select name="type" id="type" lay-verify="required" style="margin-top:0.4%;width:30%;" >
						        <option value="0" <c:if test="${type==0}">selected</c:if>>周边店铺</option>
						        <option value="1" <c:if test="${type==1}">selected</c:if>>特产商城</option>
						        <option value="2" <c:if test="${type==2}">selected</c:if>>限时秒杀</option>
						        <option value="3" <c:if test="${type==3}">selected</c:if>>积分兑换</option>
						        
						      </select>
						    </div>
                    </div>
               </div>
               <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                    <div class="grid-demo grid-demo-bg1">
                         <label class="layui-form-label">商家名:</label>
						    <div class="layui-input-block" >
						      <input type="text" id="sellerName" name="sellerName" required  lay-verify="required" placeholder="请输入商家名" autocomplete="off" class="layui-input" style="width:50%;" value="${sellerName}">
						    </div>
                    </div>
               </div>
                <div class="layui-col-xs6 layui-col-sm6 layui-col-md4">
                         <div class="grid-demo grid-demo-bg1">
						    <div class="layui-input-block" >
						      <input type="submit" required  lay-verify="required"  class="layui-input" style="width:18%;background-color:#0099ff;border-radius:8px; ">
						    </div>
                    </div>
               </div>
                <hr align="center" style="margin-top: 3%"/>
               
               
               		<table class="layui-table" name="listData" id="listData">
					  <thead>
					    <tr>
					      <th>ID</th>
					      <th>店铺名</th>
					      <th>商品名</th>
					      <th>现有销量</th>
					      <th>修改销量</th>
					    </tr>
					  </thead>
					  <tbody>
					  <c:forEach items="${list}" var="each">
					  	<tr>
					  		<td>${each.id}</td>
					  		<td>${each.snapshotGoods.seller.name }</td>
					  		<td>${each.snapshotGoods.name }</td>
					  		<td>${each.salesVolume }</td>
					  		<td>
					  			<input type="number" name="salesVolume"  min="5" max="999" value="" style="border-radius:7px;width: 20%" />   
					  		</td>
					  	</tr>
					  </c:forEach>
					  </tbody>
					</table>
					<div class="layui-input-submit">
						<input type="button" name="btnSub" id="btnSub" onclick="submitForm()" value="提交更改"  >
					</div>
  		</div>
  	  </div>
   </form>
   <script type="text/javascript">
   		function submitForm(){
   			var type = $("select#type").val();
   			var table=$("#listData").find("tr");
   			var ids=new Array();
   			for(var i=1;i<table.length;i++){
   				var id= $($(table[i]).find("td")[0]).text();
   				var numb =$($($(table[i]).find("td")[4]).find("input")).val();
   				ids.push(id+","+numb);//id,销量
   			}
   			var index = layer.load(1);
			$.ajax({
	        	 url:basePath+'cashShop/store/saveSalesVolume',
		         data:{
		         	ids:ids,
		         	type:type
		         },
		         type:"POST",
		         success: function (res) {
					layer.close(index);
		            layer.alert(res.result, {
				    skin: 'layui-layer-molv' //样式类名  自定义样式
				    ,closeBtn: 1    // 是否显示关闭按钮
				    ,anim: 1 //动画类型
				    ,btn: ['确认'] //按钮
				    ,icon: 6    // icon
				    ,yes:function(){
				    	window.location.reload();
				    }});
					}
				});
   		}
   		
   </script>
  </body>
</html>
