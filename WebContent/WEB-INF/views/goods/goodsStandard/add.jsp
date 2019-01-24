<%@page import="com.axp.dao.impl.AdminUserDAOImpl"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="com.axp.model.AdminUser"%>
<%@page import="com.axp.dao.AdminUserDAO"%>
<%@page import="com.axp.model.CashshopType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
/* AdminUserDAO adao = new AdminUserDAOImpl();
AdminUser user = null;
user = adao.findById(current_user_id); */
Object o = request.getAttribute("cashshopType");
CashshopType sc = null;
String imageurls = null;
String imageurls1 = null;
if(o!=null){
   sc = (CashshopType)o;
   imageurls = sc.getImg();
   imageurls1 = sc.getImg2();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">

<title>新增商品规格</title>
<style type="text/css">

</style>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>

</head>

<body>
<div class="div_new_content">
<div style="margin-left:10px;">

	<div class="header-left">
	<c:if test="${goodsStandard.id==null}">	
      	<p>你当前的位置：[商城管理]-[商品规格列表]-[新增商品规格信息]</p>
           </c:if>
                    <c:if test="${goodsStandard.id!=null}">	
      					<p>你当前的位置：[商城管理]-[商品规格列表]-[编辑商品规格信息]</p>
                    </c:if>
	</div>

     <form action="<%=basePath%>reGoodsStandard/save" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
					<input name="id" type="hidden" id="id" value="${reGoodsStandard.id }">
					<input  name="seId" type="hidden" id="seId" value="">
					<div class="rows">
						<label> 规格名：</label> <span class="input"> <input type=text
							name="name" id="name" value="${reGoodsStandard.name }" />
						</span>
						<div class="clear"></div>
					</div>
					<div class="rows">
						<label> 规格明细：</label>
						<span id="item" class="input">
								<div class="item">
									<input id="details"  type="text" name="details[]" style="margin:0px 10px 10px 0px;"/>
									<input id="hiddendetails"  type="hidden" name="detailsId[]" style="margin:0px 10px 10px 0px;"/>
									<input type="button" onclick="delLine(this);" value="删除"/>
								</div>
								<input class="addStandardItem" type="button" value="增加规格明细" onclick="addStandardItem();">
							<script>
								//删除一行；
								function delLine(obj) {
									$(obj).parent().remove();
									
								}
								//增加规格明细按钮的点击事件；
								function addStandardItem(){
									$(".addStandardItem").before('<div class="item"><input type="text" name="details[]" style="margin:0px 10px 10px 0px;"/><input type="hidden" name="detailsId[]" style="margin:0px 10px 10px 0px;"/><input type="button" onclick="delLine(this);" value="删除"/></div>');
								}
							</script>
						</span>
						<div class="clear"></div>
					</div>
					<div class="rows">
						<label></label> <span class="input"> <input type="button" value="保存" onclick="submitBtn();"/>
						<script>
							function submitBtn(){
								 $("input[name='details[]']").each(function(){
									var val=$(this).val();
									var hidd = $(this).next().attr("value");
									if(hidd!=undefined){
										var hid = hidd.indexOf(",");
										var hidval = hidd.substring(0, hid+1);
										var hiddenval = hidval+val;
										$(this).next().attr("value",hiddenval);
									}else{
										var hidval =+'0'+','+ val;
										$(this).next().attr("value",hidval);
									}
								});
								$.post("<%=basePath%>reGoodsStandard/save",$("#saveForm").serialize(),function(data){
                                    alert(data.message);
                                    location.href="<%=basePath%>reGoodsStandard/list";
                                });
							}
						</script>
						</span>
						<div class="clear"></div>
					</div>
				</div>
			</div>

        </form>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
</body>
</html>
<script type="text/javascript">
$(function(){
	
	var id = $("#id").val();
	if(id!=null&&id!=undefined&&id!=""){
		$.ajax({
	        url: "<%=basePath%>reGoodsStandard/getGoodsStandardByParent",
	        type: "post",
	        dataType: "json",//返回json格式的数据
 	 		async:false,
	        data: {"id":id},
	        success: function (data) {
	        	var datas = data.data;
	        	var mainBox = document.getElementById('item');
	        	$(datas).each(function(index,item){ 
	        		var divDom = document.createElement("div");
	        		var inpDom = document.createElement("input");
	        		var hidBtn = document.createElement("input");
	        		var delBtn = document.createElement("input");
	        		divDom.className ='item';
	        		
	        		inpDom.name='details[]';
	        		inpDom.value=item.name;
	        		inpDom.type='text';
	        		inpDom.style='margin:0px 10px 10px 0px;';
	        		
	        		delBtn.type='button';
	        		delBtn.value='删除';
	        		delBtn.addEventListener('click', function(e){
	        			$(this).parent().remove();
				    }, false);
	        		
	        		
	        		hidBtn.name='detailsId[]';
	        		hidBtn.value=+item.id+','+item.name;
	        		hidBtn.type='hidden';
	        		hidBtn.style='margin:0px 10px 10px 0px;';
	        		divDom.appendChild(inpDom);
	        		divDom.appendChild(hidBtn);
	        		divDom.appendChild(delBtn);
	        		mainBox.appendChild(divDom);
	        		//$("#item").prepend('<div class="item"><input type="text" class="ds" name="details[]" value="'+item.name+'" style="margin:0px 10px 10px 0px;"/><input type="hidden" name="detailsId[]" value="'+item.id+','+item.name+'"  style="margin:0px 10px 10px 0px;"/><input type="button" onclick="delLine(this);" value="删除"/></div>');
	        	});     
	        }
	    });
	}
});
</script>
