<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
</head>
<script>
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
		source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	   return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
	//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
	   source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
	else
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}
</script>
<body>
	<form>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[商城管理]-[合伙人产品库]</p>
				</div>
				<hr align="center" />
			</div>
			
			<div class="div_search">
			   <form action="BranksCoupon/getProductToAgent" method="post">

			     产品关键字: <input type=text class="input" value="${search }"  name="search_name" />&nbsp;&nbsp;
			        <input type=hidden name=type value="${type }"/> 
				  所属银行:	<select name="branksId">
				  				<option value="-1">-请选择-</option>
					  		<c:forEach items="${branks }" var="b">
					  			<option value="${b.id }" <c:if test="${ b.id == branksId}" >selected="selected"</c:if> >${b.branksName }</option>
					  		</c:forEach>
				  		</select>   
				    <input type=submit class="button" value="搜索" >
			    </form>
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				
			
			
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="2%" style="height:50px;">归属银行</th>
						<th width="3%">产品名称</th>
						<th width="1%">可获积分</th>
						<th width="1%">产品剩余量</th>
						<th width="2%">收分用户</th>
						<c:choose>
							<c:when test="${adminUser.level == 75 }">
								<th width="2%">归属联盟</th>
							</c:when>
							<c:when test="${adminUser.level == 65 }">
							
								<th width="5%">基本操作</th>
							</c:when>
						
						</c:choose>
					</thead>
					<tbody>
						<c:forEach items="${agentProducts }" var="type">
							<tr>
								  <td>${type.product.brank.branksName}</td>  
								<td>${type.product.productName }</td>
								<td>${type.product.score}</td>
								<td>${(type.product.num-type.product.useNum)}</td>
								<td>${type.product.adminUser.username}</td>
								<td>
									<div align="" style="margin:left;">
										<c:choose>
											<c:when test="${adminUser.level == 75 }">
												${type.adminUser.username}
											</c:when>
											<c:when test="${adminUser.level == 65 }">
											
												<form action="addCode" id = "form_1" method="post">
													<input type = "hidden" name = "id" value="${type.id }"/>
														填写兑换码:<input type="text" name="redeemCode" id = "redeemCode" value=""/><br />
														兑换用户(填写手机号码):<input type="text" name="phone" id = "phone" value=""/>
													<input type="hidden" name="typeId" value="${type.id }"/>
													 <span onclick="addCode('${type.id }');" >提交</span>
			                               		
												</form>
											</c:when>
										
										</c:choose>
										
									</div>
										
									
									
								</td>
    						</tr>
						</c:forEach>
         			
						
					</tbody>
				</table>
			</div>
			<!--end of pageColunm-->
			<div class="footer">
				<div class="page">
					<table>
						<tr>
							<div class="page-box common-page-box" style="text-align:center">
			${requestScope.pageFoot }
		</div>
						</tr>
					</table>

				</div>
			</div>
		</div>
		<!--end of main-->
	</form>
<script>

	function addCode(id){
		var redeemCode = $("#redeemCode").val();
		var phone =$("#phone").val();
		if(redeemCode == ""){
			alert("兑换码不能为空!");
		}
		if(phone == ""){
			alert("手机号不能为空!");
		}
		$.ajax({
            url: "<%=basePath%>BranksCoupon/addCode",
            type: "post",
            data: $("#form_1").serialize(), 
            success: function (data) {
          	  alert(data.msg);
              
            }
        }); 
	}
	function del(id){
		$.ajax({
            url: "<%=basePath%>BranksCoupon/del_dl",
            type: "post",
            data: {"id": id},
            success: function (data) {
          	  alert(data.msg);
              location.reload();
            }
        });
		
	}
	
	function add(id){
		$.ajax({
            url: "<%=basePath%>BranksCoupon/add",
            type: "post",
            data: {"id": id},
            success: function (data) {
          	  alert(data.msg);
              location.reload();
            }
        });
	}
	function allProduct(id){
		alert(id);
		window.location.href="BranksCoupon/list";
	}

  function checkAll() 
  { 
	  var code_Values = document.all['code_Value']; 
	  if(code_Values.length){ 
	  for(var i=0;i<code_Values.length;i++) 
	  { 
	  code_Values[i].checked = true; 
	  } 
	  }else{ 
	  code_Values.checked = true; 
	  } 
  } 
  function uncheckAll() 
  { 
	  var code_Values = document.all['code_Value']; 
	  if(code_Values.length){ 
	  for(var i=0;i<code_Values.length;i++) 
	  { 
	  code_Values[i].checked = false; 
	  } 
	  }else{ 
	  code_Values.checked = false; 
	  } 
  }

</script>
</body>
</html>

