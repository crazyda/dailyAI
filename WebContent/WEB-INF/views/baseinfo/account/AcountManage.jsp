<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/css2.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
<link href="${BASEPATH_IN_SESSION}css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${BASEPATH_IN_SESSION}layui/css/layui.css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/layer3/layer.js"></script>
<script src="${BASEPATH_IN_SESSION}layui/layui.js" charset="utf-8"></script>

<style type="text/css">
		.layui-input-submit{
			margin:0 auto;
			text-align:center;
			margin-top: 20px;
		}
		.layui-input-submit input{
			width:15%;	
			height:3%;
			background-color: #0099ff;
			outline:none;
			border-radius:8px;
			font-weight:bold;
			font-size: 12px;
			color:#ffffff;
		}
		#content{
			display:none;
		}
		.f{
			margin-left: 50px;
		}
		.layui-inline input{
			border-radius:6px;
		}
	</style>
</head>
<body>
	<form>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[系统管理]-[账户管理]</p>
				</div>
				<hr align="center" />
			</div>

				<div class="header-right" style="margin-top: -60px;margin-left: 100px">
					<div class="header-right-cell" onclick="addmessage()" style="cursor: pointer;">
						<img src="${BASEPATH_IN_SESSION}img/new-2.png" />
						<p>新增</p>
					</div>&nbsp;&nbsp;
				
				</div>
			
		
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">ID</th>
						<th width="10%">账户名</th>
						<th width="9%">时间</th>
						<th width="9%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="account">
							<tr>
								<td>${account.id}</td>
								<td>${account.accountName }</td>
								<td>
								   <fmt:formatDate value="${account.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 
								</td>
								<td>
								<div align="center">
									<span class="STYLE4"> <span
										onclick="editmessage('${account.id }');" style="cursor: pointer;"> <img
											src="${BASEPATH_IN_SESSION}constant/tab/images/edt.gif" width="16"
											height="16" />添加账户明细
									</span> 
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
		<div id="content" >
			<div class="f" >
				<label>账户名：</label>
				</strong>
				<div class="layui-inline">
					<input type=text name="name" style="width: 200px" id="name" value="" />
				</div>
				<strong>
			</div>
			
			<div class="layui-input-submit">
				<input type="button" name="btnSub" id="btnSub" onclick="submitForm()" value="提交"  >
			</div>
		</div>
		<!--end of main-->
	</form>
<script>
var index;
	function addmessage(){
		index=layer.open(
			{
			  type: 1,
			  area: ['420px', '240px'], //宽高
			  content: $('#content')
			});
		
		}
	
	function editmessage(id){
		window.location.href="add?id="+id;
	}
	
	
	
	function submitForm(){
		var name = $("#name").val();
		if(name==""){
			layer.msg("账户名不能为空");
			return false;
		}
		$.ajax({
			url:"${BASEPATH_IN_SESSION}account/save",
			data:{
				name:name,
			},
			type:"POST",
			content: "json",
			success:function(res){
			    layer.alert("success",{
			         btn:['确认']
			    	,yes:function(){
			    		window.location.reload();
			    	}});
				layer.close(index);			
			},
			error:function(res){
			    layer.alert("fail");
			}
		});
	}
	
</script>
</body>
</html>

