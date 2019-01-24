<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>

<title>返现活动编辑</title>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}
.l_err{
    background: none repeat scroll 0 0 #FFFCC7;
    border: 1px solid #FFC340;
    font-size: 12px;
    padding: 4px 8px;
    width: 200px;
    display: none;
}
.error{
  border: 3px solid #FFCCCC;
}
</style>



</head>

<body>
<div class="div_new_content">
  <div style="margin-left:10px;">
  <div class="l_err" style="width: 100%; margin-top: 2px; "></div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1">
	                <div class="header-left">
						<p>你当前的位置：[红包管理]-[授权红包额度]-[额度编辑]</p>
					</div>
					

                </td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table></td>
  </tr>
  <tr>
     <td>
     <form  action="<%=basePath%>newRedPaper/checkApply/save"   id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      	<div class="rows">
			<label>审核对象：</label>
			<span class="input">
				${asset.adminUser.loginname }
				<div style="padding-top:10px;">
				</div>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>申请额度：</label>
			<span class="input">
			  ${assetlog.positons }
			</span>
			<div class="clear"></div>
		</div>
				
		<div class="rows">
			<label>有效期：</label>
			<span class="input">
			  	<fmt:formatDate  value="${asset.endTime }" pattern="yyyy年MM月dd日" />
			</span>
			<div class="clear"></div>
		</div>
		
		<div class="rows">
			<label>申请说明：</label>
			<span class="input">
			  ${assetlog.remark }
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>审核：</label>
			<span class="input">
			  <select name="status" id="status">
			  	<option value="-1">请选择</option>
			  	<option value="1">通过</option>
			  	<option value="2">不通过</option>
			  </select>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
				<label></label>
				<span class="input">
				  <input type="button" class="button"  value="确 认" onclick="saveType();"/>
				  <input type="button" class="button"  value="取 消" onclick="javascript:history.go(-1);"/>
				</span>
				<div class="clear"></div>
			</div>
		</div>
		</div>
      
	<input type=hidden id="assetlogId" name="assetlogId" value="${assetlog.id }" />
	<input type=hidden id="parentAsset" name="parentAsset" value="${parentAsset.id }" />
	<input type=hidden id="asset" name="asset" value="${asset.id }" />
     </form> 
     </td>
  </tr>
 
</table>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script>
 function saveType(){
	 var level = '${parentAsset.adminUser.level}';
	 var positionSurplus = '${parentAsset.positionSurplus}';//当前用户额度
	 var positions = '${assetlog.positons }';//申请额度
	 
	 var status = $("#status").val();
	 if(parseInt(level)<95){ 
	 		if(positionSurplus-positions <0.0){
	 			alert(positions);
	 		}
	 
		  if(positions>positionSurplus){
			  alert("审核失败！您的余额只剩下"+positionSurplus+",已不够发放");
			  return;
		  }else{
			  alert("审核成功！您的余额还剩下"+positionSurplus+"，足够本次申请");
		  }
	 }
	 
	 if(status == "-1"){
		 alert("请审核");
		 return;
	 }
     document.getElementById("saveForm").submit();
 }
 $(function(){
	 $("select").change(function(event){
		 $("#getterName").html(""); 
		 $("#id").val("");
		 $("#adminUserId").val("");
		 $("#selelrId").val("");
		 if($(this).val()&&('${sessionScope.level}'>=95||('${sessionScope.level}'==85&&$(this).attr("name")=="proxy_id")||
				 ('${sessionScope.level}'==75&&$(this).attr("name")=="alliance_id")||
				 ('${sessionScope.level}'==65&&$(this).attr("name")=="seller_id"))
		 ){
			 $("#getterName").html($(this).find("option:selected").text()); 
			 if($(this).attr("name")!="seller_id"){
				 $("#adminUserId").val($(this).val());
			 }else{
				 $("#sellerId").val($(this).val());
			 }
			 $.ajax({
				cache : true,
				type : "POST",
				url : "<%=basePath %>newRedPaper/empowerCredit/getAssetPosition",
				data : {
					adminId : $("#adminUserId").val(),
					sellerId : $("#sellerId").val()
				},
				async : false,
				error : function(data) {
					alert('添加属性失败');
				},
				success : function(data) {
					//$('#positionSurplus').html(eval('(' + data + ')').surplus);
					//$('#laseEndTime').html(eval('(' + data + ')').endTime);
					//$('#id').val(eval('(' + data + ')').id);
					$('#positionSurplus').html(data.surplus);
					$('#laseEndTime').html(data.endTime);
					$('#id').val(data.id);
				}
			});
		 }
	 });
	 
	   $("#saveForm").validate({
		 		submitHandler : function(form) {
		 		 document.getElementById("saveForm").submit();
		 		},
		 		errorPlacement : function(error, element) {//自定义提示错误位置
		 			$(".l_err").css('display','block');
		 			//element.css('border','2px solid #FFCCCC');
		 			$(".l_err").html(error.html());
		 		},
		 		rules:{
	                budget:{
	                    required:true,
	                    range:[0,'${totalPoint }'],
		 				min:0.0001
	                },
	                orderPoint:{
	                    required:true,
	                    range:[0,'${totalPoint }'],
	                	min:0.0001
	                }
	            },
		 		success: function(label) {//验证通过后
		 			$(".l_err").css('display','none');
		 		}
		 		});
	   });
 
 
  
</script>
</body>
</html>
