<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="${BASEPATH_IN_SESSION}css/add/css/shop.css" rel="stylesheet" type="text/css">
 <style>
        .container
        {
            top: 5%; left: 36%; right: 0; bottom: 0;
        }
        .action
        {
            width: 400px;
            height: 30px;
            margin: 10px 0;
        }
        .cropped>img
        {
            margin-right: 10px;
        }
    </style>
<title>图片</title>
<style type="text/css">
<!--
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
-->
</style>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
<!-- 验证字符合法性 -->
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/checktext.js"></script>
</head>

<body>
<div class="div_new_content">
<div style="margin-left:10px;">
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
						<p>你当前的位置：[商城管理]-[秒杀时段管理]-[新增秒杀时段]</p>
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
     <form action="${BASEPATH_IN_SESSION}cashShop/secKillTimes/save" id="saveForm" method="post">
        <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				
				<div class="rows">
					<label>时间：</label>
					<span class="input" id="param">
					<b id="add" style="cursor:pointer;" onclick="add2()">+添加</b> <font color=red>最多四个时间段 (填写时只需填写小时即可，如：18:00)</font>
					
						<div id="d1"  style="float:left;Width:100%;<c:if test="${size<=2 }">display:none</c:if>" >
						开始时间： <input type="text" name="startTime" id="startTime" onClick="WdatePicker({dateFmt:'HH:mm'});" readonly="readonly"/>&nbsp;&nbsp;
						 结束时间：<input type="text" name="endTime" id="endTime" onClick="WdatePicker({dateFmt:'HH:mm'});" readonly="readonly"/>	
						&nbsp;&nbsp;<b style="cursor:pointer;" id="del" onclick="del2()">删除</b>				
						</div>
					
						
					</span>
						
					<div class="clear"></div>
		
				
				<div class="rows">
					<label></label>
					<span class="input">
					  <input type="button" class="button"  onclick="sub()" value="添 加" />
					</span>
					<div class="clear"></div>
				</div>
				</div>
			</div>
        
        </form>
     </td>
  </tr>
 
</table>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script>
function sub(){
	var a=document.getElementById("startTime").value;
	
	if(a==null || a==""){
		alert("请输入正确的时间");
		return;
	}
	var b=document.getElementById("endTime").value;
	if(b==null || b==""){
		alert("请输入正确的时间");
		return;
	}
	var at= new Array();
	at=a.split(":");
	if(parseInt(at[0])>24 || parseInt(at[0])<0||parseInt(at[1])>60 || parseInt(at[1])<0){
		alert("请输入正确的开始时间");
		return;
	}
	var bt= new Array();
	bt=b.split(":");
	if(parseInt(bt[0])>24 || parseInt(bt[0])<0 || parseInt(bt[1])>60 || parseInt(bt[1])<0){
		alert("请输入正确的结束时间");
		return;
	}
	document.getElementById("saveForm").submit();
}

function add2() {
	var math = document.getElementById('param').children.length;
	var a='${size}';
	if(parseInt(math) < parseInt(a)){
		
	
			$(
					"<div  id=\"d1\" style=\"float:left;Width:100%\"> 开始时间： <input type=text name=\"startTime\" id=\"startTime\" onClick=\"WdatePicker({dateFmt:'HH:mm'});\" />&nbsp;&nbsp;"
					+ "  结束时间：<input type=text name=\"endTime\" id=\"endTime\"  onClick=\"WdatePicker({dateFmt:'HH:mm'});\"/>"
					+ " &nbsp;&nbsp;<b style=\"cursor:pointer;\" onclick=\"del2()\" id=\"del\">删除</b></div>"
					)
					.appendTo($('#param'));
	};
}
	
	
   
   function del2() {
		var ad = event.srcElement;
		ad.parentNode.parentNode.removeChild(ad.parentNode);
   };
   
   

</script>
</body>
</html>
