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
						<p>你当前的位置：[系统管理]-[添加账户]</p>
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
      <form class="layui-form" action="<%=basePath %>account/saveAccount.action" id="saveForm" method="post">
      <input type="hidden" id="id" name="id" value="${id}">
         <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				<div class="rows">
					<label> 商户类别：</label>
					<span class="input">
						<select id="firstselect" name="firstselect">
							<option value="1">支付宝</option>
							<option value="2">微信</option>
						</select>
						<select id="secondselect" name="secondselect">
							<option value="1">用户版</option>
							<option value="2">商家版</option>
							<option value="3">公众号</option>
						</select>
					</span>
					<div class="clear"></div>
				</div>
				
				
				<div id="tab1">
					<table class="table1">
					<div class="rows">
					<label>App_Id：</label>
					<span class="input">
					  <input type=text name="appid" style="width:350px;" id="appid" value="" />
					</span>
					<div class="clear"></div>
					</div>
					<div class="rows" >
					<label>App_secret：</label>
					<span class="input">
					  <input type=text name="secret" style="width:350px;" id="secret" value="" />
					</span>
					<div class="clear"></div>
					</div>
					<div class="rows">
						<label>Mch_id：</label>
						<span class="input">
						  <input type=text name="mchid" style="width:350px;" id="mchid" value="" />
						</span>
						<div class="clear"></div>
					</div>
					<div class="rows" >
						<label>API_key：</label>
						<span class="input" >
						  <input type=text name="apikey" style="width:350px;" id="apikey" value="" />
						</span>
						<div class="clear"></div>
					</div>
				</table>
				</div>
				
				 
				 <div id="tab2">
				 	<table class="table2">
				 	<div class="rows">
					<label>收款支付宝账号：</label>
					<span class="input">
					  <input type=text name="alisellerid" style="width:350px;" id="aliappid" value="" />
					</span>
					<div class="clear"></div>
					</div>
					<div class="rows" >
					<label>商户的私钥(RSA)：</label>
					<span class="input">
					  <input type=text name="priKey" style="width:350px;" id="priKey" value="" />
					</span>
					<div class="clear"></div>
					</div>
					<div class="rows">
						<label>商户的私钥(RSA,pkcs8格式)：</label>
						<span class="input">
						  <input type=text name="priKey2" style="width:350px;" id="priKey2" value="" />
						</span>
						<div class="clear"></div>
					</div>
					<div class="rows" >
						<label>公钥：</label>
						<span class="input" >
						  <input type=text name="pubKey" style="width:350px;" id="pubKey" value="" />
						</span>
						<div class="clear"></div>
					</div>
				 	<div class="rows">
						<label>商户私钥(MD5)：</label>
						<span class="input">
						  <input type=text name="aliapikey" style="width:350px;" id="apikey" value="" />
						</span>
						<div class="clear"></div>
					</div>
					<div class="rows" >
						<label>支付宝app_id：</label>
						<span class="input" >
						  <input type=text name="aliappid" style="width:350px;" id="appid" value="" />
						</span>
						<div class="clear"></div>
					</div>
				 
				 </table>
				 
				 </div>
				 
	            <div class="rows">
					<label></label>
					<span class="input">
					  <input type=button class="button" onclick="savePtype();" value="添 加" />
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
	$(function(){
		$("#tab1").hide();
		$("#secondselect option:gt(0)").hide();
		$("#firstselect").change(function(){
			if($("#firstselect").val()==1){
				$("#tab1").hide();
				$("#tab2").show();
				$("#secondselect option:gt(0)").hide();
			}
			if($("#firstselect").val()==2){
				$("#tab1").show();
				$("#tab2").hide();
				$("#secondselect option:gt(0)").show();
			}
			
		})
		
	})
	
   function savePtype(){
      document.getElementById("saveForm").submit();
   }

</script>
</body>
</html>
