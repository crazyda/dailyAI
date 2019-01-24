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
						<p>你当前的位置：[红包管理]-[申请红包额度列表]-[申请红包额度]</p>
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
     <form  action="<%=basePath%>newRedPaper/apply/save"   id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
		<div class="rows">
			<label>额度：</label>
			<span class="input">
			  <input type=text name="positions" id="positions" class = "required number" value=""  />
			</span>
			<div class="clear"></div>
		</div>	
		<div class="rows">
			<label>备注：</label>
			<span class="input">
			  <textarea rows="5" cols="3" id="remark" name = "remark">${asset.remark }</textarea>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
				<label></label>
				<span class="input">
				  <input type="button" class="button"  value="确 认"  onclick="save()"/>
				  <input type="button" class="button"  value="取 消" onclick="javascript:history.go(-1);"/>
				</span>
				<div class="clear"></div>
			</div>
		</div>
		</div>
      
	<input type=hidden id="id" name="id" value="${asset.id }" />
	<input type=hidden id="adminUserId" name="adminUserId" value="${asset.adminUser.id }" />
	<input type=hidden id="sellerId" name="sellerId" value="${asset.seller.id }" />
     </form> 
     </td>
  </tr>
 
</table>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script>
 function save(){
	 var positions = $("#positions").val();
	 if(positions==null||positions==undefined||positions==""){
		 alert("额度不能为空");
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
