<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Baseinfos"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<title></title>

<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/layer3/layer.js"></script>
<script src="<%=basePath %>layui/layui.js" charset="utf-8"></script>
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
						<p>你当前的位置：[系统管理]-[积分卡]</p>
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
      <form class="layui-form" action="<%=basePath %>cashShop/vouchers/save.action" id="saveForm" method="post">
         <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				<div id="tab1">
					<table class="table1">
					<div class="rows">
					<label>数量：</label>
					<span class="input">
					  <input type=text name="Num" style="width:350px; height: 30px" id="Num" value="" placeholder="必填（发卡数量）"/>
					</span>
					<div class="clear"></div>
					</div>
					<div class="rows" >
					<label>面值：</label>
					<span class="input">
					  <input type=text name="faceValue" style="width:350px;height: 30px" id="faceValue" value="" placeholder="必填（卡面金额）"/>
					</span>
					<div class="clear"></div>
					</div>
					<div class="rows">
						<label>描述：</label>
						<span class="input">
							<textarea rows="" cols="" name="remark" id="remark" value=""></textarea>
						</span>
						<div class="clear"></div>
					</div>
					
				</table>
				</div>
				 
	            <div class="rows">
					<label></label>
					<span class="input">
					  <input type=button class="button" onclick="savePtype();" value="添加并导出" />
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
   function savePtype(){
   	  var Num = $("#Num").val();
   	  var faceValue = $("#faceValue").val();
   	  if(Num=="" || faceValue==""){
   	  	alert("请输入完整数据！");
   	  	return false;
   	  }else{
   	     document.getElementById("saveForm").submit();
   	  }
      
   }
   

</script>
</body>
</html>
