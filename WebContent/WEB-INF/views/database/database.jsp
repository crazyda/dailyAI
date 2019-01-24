<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>同步数据库</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>js/layer3/mobile/need/layer.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/layer3/layer.js"></script>
	 <style type="text/css">
	 		
	 			*{
	 				margin: 0px;
	 				padding: 0px;
	 			}
	 			.container{
	 				width: 100%;
	 				height: 100%;
	 				background-color: white;
	 				border-radius:10px;
	 			}
	 			.header{
	 				width: 100%;
	 				height: 220px;
	 			}
	 			
	 			.header h1{
	 				width:200px;
	 				height:30px;
	 				margin: 0 auto;
	 				margin-top: 25px;
	 			}
	 			
	 			.head{
	 				height:120px;
	 				width:1500px;
	 				/* margin: 25px 0 0 25px; */
	 				margin: 25px auto;
	 			}
	 			.form-group{
	 				margin: 15px;
	 			}
	 			
	 			form:nth-child(1){
	 				margin-left: 100px;
	 			}
	 			
	 			table tr{
	 				width:800px;
	 				height: 55px;
	 				max-width:120px;
	 				text-align: left;
	 			}
				
				table tr td{
					width: 220px;
					
				}
				
				tbody input,td,lable,span{
					display: inline-block;
					margin-left: 5px;
				}	 			
				
				#selectCount{
					color:red;
				}
				
	 </style>
  </head>
  
  <body>
  
    	<div id="msg">
    	
    		<p></p>
    		  
    	</div>
    	
    	<div class="container">
    		<div class="header">
    			<h1>同步数据库</h1>
    			<div class="head">
    				<form  method="get" role="form" id="f1" class="form-inline" >
    					
    					<div class="form-group">
    						<label class="text-info" for="dbName">URL</label>
    						<input type="text" class="form-control" value="" name="url" id="url" placeholder="请输入URL" />
    					</div>
    					
    					<div class="form-group">
    						<label class="text-info" for="dbName">数据库名</label>
    						<input type="text" class="form-control" name="dbName" value="" id="dbName" placeholder="请输入数据库名" />
    					</div>
    					
    					<div class="form-group">
    						<label class="text-info" for="userName">用户名</label>
    						<input type="text" class="form-control" name="userName" value="" id="userName" placeholder="请输入用户名" />
    					</div>
    					
    					<div class="form-group">
    						<label class="text-info" for="pwd">密码</label>
    						<input type="text" class="form-control" name="pwd" value="" id="pwd" placeholder="请输入密码" />
    					</div>
    					
    					<div class="form-group">
    						 <button type="submit" class="btn btn-primary ">查询所有表</button>
    					</div>
    					
    					<br/>
    					<div class="form-group">
    						 <h3 class="text-danger">选择需要同步的表</h3>
    					</div>
    					
    					
    					
    					
    					<div class="form-group">
    						 <a id="sub"  class="btn btn-danger ">开始同步</a>
    					</div>
    				</form>
    			</div>
    		</div>
    	
    	<div id="main">
    		<form id="f2">
    			<div class="form-group">
    						 <label class="radio-inline">
     							   <input style="transform: scale(1.5,1.5);" type="radio" name="type"  value="increment" checked> 增量同步  
   							 </label>
  						 	 <label class="radio-inline">
        							<input style="transform: scale(1.5,1.5);" type="radio" name="type"   value="clear"> 清表同步
   							 </label>
    			</div>
    			
		  <table class="table">
				  <caption>当前数据库所有表&nbsp;&nbsp;&nbsp;<label>
      					<input type="checkbox" onclick="CheckAll(this)" id="checkAll" style="transform: scale(1.5,1.5);" />&nbsp;&nbsp;全选</label><span id="tableCount"></span><span id="selectNum"></span>
   		 </caption>
   		 		
				  <tbody id="tbody">
				   		
				   			
				   	
				  </tbody>
				
			</table>
			 </form>
    	</div>
    	
 	</div>
    	
    <script type="text/javascript">
    
    	$("#msg").hide();
    	var lay;
    	var content;
    	var intervalId;
    
    	var checks;
    $("#sub").click(function(){
    	   checks= $("tbody input[type='checkbox']:checked");
    	  if(checks.length==undefined||checks.length==0){
			  layer.alert("请选择需要同步的表");
			  return false;
		  }
		  
      lay=layer.open({
    		  type:1,
    		  /* title:'同步信息', */
    		  skin: 'layui-layer-lan',
    		  resize:false,
    		  icon:5,
    		  closeBtn:1,
    		  area: ['780px', '550px'], //宽高
    		  /* offset: ['200px', '50px'], *///定位
    		  anim :6,
    		  content:$("#msg").html(),
    		  success:function(la){
    			  $(la).find("p").css({'display':'inline-block','width':'350px',
    				  'height':'80px','padding-top':'20px','padding-left':'20px','line-height: ':'80px'/* 'text-align':'center' */});
    			
    			  layer.title("同步进度:"+checks.length, lay);
    			  
    			   setTimeout(intervals(),1000);
    			  
    			   $.post("<%=basePath%>db/batchSynchro",$("#f2").serialize(),function(result){
						
    			    });    
    		  },
    		  cancel: function(index, layero){
    				clearInterval(intervalId)  //清除定时器
    				cleanTableStatus();
    			    layer.close(index)
    			}   
    		});
     		
     		
    });
    		
    
    			function intervals(){
    				intervalId=	setInterval(updateContent, 2000);
    			}
    		
    
    		function updateContent(){
    			var runStr;
    			var str;
    			var count=0;	
    			 $.get("<%=basePath%>db/getTableStatus",function(data){
				 	 var p=$(".layui-layer-content p");
				 	 	for(var i=0;i<p.length;i++){
						
				 	 	
				 	 		
				 	 	var index=p[i].innerText.indexOf("|");
				 	 	
				 	 	if(index==-1){
				 	 		index=p[i].innerText.length+1;
				 	 	}
				 	 	text=p[i].innerText.substring(0,index);
				 	 	
				 	 	
				 	 	if(undefined==data[text].status){
			 	 			return;
			 	 		}
				 	 	switch (data[text].status) {
				 	 		case 0:
									runStr="等待运行";
							break;
							case 1:
									runStr="正在运行中";
									var myDate = new Date();
									var mytime=myDate.getTime()-data[text].time;
									data[text].time=mytime/1000;
								break;
							case 2:
									runStr="已完成";
									count++;
								break;
							case -1:
									runStr="运行失败";
									count++;
								break;
								
							default:
								   runStr="未知状态";
									count++;
								break;
							}
				 	 	
				 	   		layer.title("同步进度: 选择"+checks.length+"张表=======已完成"+count+"张表");
				 	   		if(checks.length==count){
				 	   			clearInterval(intervalId);  //清除定时器
				 	   			//layer.alert("同步完成");
				 	   			alert("同步完成");
				 	 		  	layer.close(layer.index);
				 	 		  
				 	   		}
				 	 		 str=data[text].tableName+"|  "+"执行状态:"+runStr+"  执行时长"+data[text].time+"秒";
				 	 		if(data[text].status==-1){
				 	 			str+=" 错误信息: "+data[text].errorMsg;
				 	 		}
				 	 		 $(p[i]).text(str); 
				 	 	}
			     });  
	    	}
    		
    		function cleanTableStatus(){
    			 $.get("<%=basePath%>db/cleanTableStatus",function(data){
    				 // 清除tableStatus
    			 });
    			
    			
    		}
    	
    	function showContent(){
    		  //变量拼接content
     	   $("#msg p").remove();
     	   var checks= $("#tbody input[type='checkbox']:checked");
     	   if(checks.length>0){ 
     			for(var i=0;i<	checks.length;i++){
         	   		try {
         	   		var p=document.createElement("p");
         	   	
         	   		p.innerHTML=checks[i].getAttribute('_name');
         	   	
         	   		$("#msg").append(p);
         	   		} catch (e) {
     					console.log(i);
     				}
         	   	}
     	   } 
     	   content=$("#msg").html();
    	}
    	
    	
    	$("#f1").submit(function(){
    		 $.getJSON("<%=basePath%>db/tabls",$("#f1").serialize(),function(result){
    			 var tr=document.createElement("tr");
    			     $.each(result, function(i, field){
	   			    	if((i+6)%6==0){
   			    		 tr=document.createElement("tr");
    	 			    }
	   			    	
    			     	var td=document.createElement("td");
    			     	var lable=document.createElement("lable");
    			     	var input =document.createElement("input");
    			     	input.type="checkbox";
    			     	input.value=field.table_name;
    			     	var span=document.createElement("span");
    			     	span.innerText="("+field.table_rows+")";
    			     	input.setAttribute("_name",field.table_name);
    			     	input.setAttribute("name","tableName");
    			     	if(field.table_rows>10000){
    			     		span.style.color="red";
    			     		input.setAttribute("flag",true);
    			     	}
    			     	td.appendChild(input);
    			     	lable.innerText=field.table_name;
    			     	td.appendChild(lable);
    			     	td.appendChild(span);
						    	
						tr.appendChild(td);    			     	
    			     	if((i+6)%6==0){
    			     		$("#tbody").append(tr);
    			     	}
    			    });  
    			    
    			    $("#tableCount").text(result.length+"张表").css("color","red");
    			  });
    		return false;
    	});
    
    
    function CheckAll(obj) {
    	
    	   //获取checked属性
    	   if ($(obj).prop("checked")) {
    	    	  //设置checked属性
        	      $("input[type='checkbox']").prop("checked", true);  
    	      
    	   } else {
    	      //设置checked属性
    	      $("input[type='checkbox']").prop("checked",false);                                
    	   }
    	   
    	  $("input[flag='true']").prop("checked",false);  
    	  
    	   $("#msg p").remove();
     	   var checks= $("#tbody input[type='checkbox']:checked");
     	   if(checks.length>0){ 
     	   	for(var i=0;i<	checks.length;i++){
     	   		try {
     	   		var p=document.createElement("p");
     	   	
     	   		p.innerHTML=checks[i].getAttribute('_name');
     	   	
     	   		$("#msg").append(p);
     	   		} catch (e) {
 					console.log(i);
 				}
     	   	}
     	   } 
     	   content=$("#msg").html();
     	  
    	}
    
    
    	$("#checkAll").click(function(){
    		var checks= $("#tbody input[type='checkbox']:checked");
    		$("#selectNum").text("已选"+checks.length+"张表");
    		showContent();
    	});
    	
    	$("#tbody").on("click","input", function() {
    		var checks= $("#tbody input[type='checkbox']:checked");
    		$("#selectNum").text("已选"+checks.length+"张表");
    		showContent();
    	 });
    	
    </script>
    
  </body>
</html>
