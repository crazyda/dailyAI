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
     <form  action="<%=basePath%>newRedPaper/empowerCredit/saveDetail"   id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      	<div class="rows">
			<label>授权对象：</label>
			<span class="input">
				<c:if test="${asset.id==null }">
				<span id="center">运营中心： <wh:selectTag id="center_id" name="center_id" optionValue = "${center_id }"  defaultOptionText="--请选择--" showProperty="username" where=" ${condition} " tableName="admin_user" key="id"></wh:selectTag></span>
				<c:if test="${sessionScope.level>=95||sessionScope.level<=85 }">
				<span id="proxy"> 市级代理： <wh:selectTag parentId="center_id" parentIdName="parent_id" parentValue="${center_id }" id="proxy_id" name="proxy_id" optionValue = "${proxy_id }"  defaultOptionText="--请选择--" showProperty="username" where="${condition_proxy}" tableName="admin_user" key="id"></wh:selectTag></span>
				</c:if><c:if test="${sessionScope.level>=95||sessionScope.level<=75 }">
				<span id="alliance">联盟商圈： <wh:selectTag parentId="proxy_id" parentIdName="parent_id" parentValue="${proxy_id}" id="alliance_id" name="alliance_id" optionValue = "${alliance_id }"  defaultOptionText="--请选择--" showProperty="username" where="${condition_alliance}" tableName="admin_user" key="id"></wh:selectTag></span>
				</c:if><c:if test="${sessionScope.level>=95||sessionScope.level<=65 }">
				<div><span id="selelr">品牌商家： <wh:selectTag parentId="alliance_id" parentIdName="adminuser_id" parentValue="${alliance_id }" id="seller_id" name="seller_id" optionValue = "${seller_id }"  defaultOptionText="--请选择--" showProperty="name" where="${condition_seller}" tableName="seller" key="id"></wh:selectTag></span></div>
				</c:if>
				</c:if>
				<div style="padding-top:10px;">
				<span id="getterName"></span>
				<c:if test="${asset.id!=null }">
				<c:if test="${asset.adminUser!=null }">${asset.adminUser.loginname }</c:if>
				<c:if test="${asset.seller!=null }">${asset.seller.name }</c:if>
				</c:if>
				&nbsp;&nbsp;&nbsp;该对象现有额度：
					<a id="positionSurplus">
						<fmt:formatNumber type="number" maxFractionDigits="2" value="${asset.positionSurplus}" />
					</a>
				</div>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>额度：</label>
			<span class="input">
			  <input type=text name="positionTotal" id="positionTotal" class = "required number" value=""  />
			</span>
			<div class="clear"></div>
		</div>
				
		<div class="rows">
			<label>有效期：</label>
			<span class="input">
			  <input class="login-input-username" type=text id=endTime name=endTime value="" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});"  />
			  <div>
			  	目前有效期：<a id="laseEndTime"><fmt:formatDate  value="${asset.endTime }" pattern="yyyy年MM月dd日" /></a>
			  </div>
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
				  <input type="submit" class="button"  value="确 认" />
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
 function savePtype(){
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
