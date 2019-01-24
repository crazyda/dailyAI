<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Goods"%>
<%@page import="com.axp.dao.IGoodsDao"%>
<%@page import="com.axp.model.AdminUser"%>
<%@page import="com.axp.util.StringUtil"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object o = request.getAttribute("sc");

Goods sc = null;
String adver_imageurls = null;
String adver_imageurls_small = null;
String adver_imageurls2 = null;
String adver_imageurls_small2 = null;
String name = "";
String imgurls = null;
String website = "";
int adver_status = 0;
int status = 0;
int quantity =0;
int playnum=0;
int play =0;
String starttime ="";
String endtime ="";
String checkstr="广告匙不足！";
if(o!=null){
   
   sc = (Goods)o;
   adver_imageurls = sc.getAdverImgurls();
   adver_imageurls = adver_imageurls.replace("goods_adverbig", "goods_adver");
   adver_imageurls2 = sc.getAdverImgurls()==null?"":sc.getAdverImgurls();
   imgurls = sc.getImgurls();
   
   
   if(imgurls.indexOf("goodsbig")>0){
	   imgurls = imgurls.replace("goodsbig", "goods");
   }
   
   adver_imageurls_small = sc.getAdverImgurlsSmall();
   adver_imageurls_small2 = sc.getAdverImgurlsSmall()==null?"":sc.getAdverImgurlsSmall();
   
   if(adver_imageurls_small.indexOf("goods_adver_smallbig")>0){
	   adver_imageurls_small = adver_imageurls_small.replace("goods_adver_smallbig", "goods_adver_small");
   }
   name = sc.getName();
   adver_status = sc.getAdverStatus()==0?1:sc.getAdverStatus();
   status = sc.getStatus();
   AdminUser au = sc.getAdminUser();
   quantity = au.getQuantity();
   if(sc.getStarttime() !=null){
   		starttime = DateFormat.getDateInstance().format(sc.getStarttime());
   }
   if(sc.getEndtime() !=null){
  		 endtime = DateFormat.getDateInstance().format(sc.getEndtime());
   }
   play = sc.getPlaytotal()==null?0:sc.getPlaytotal();
   checkstr = StringUtil.getNullValue(sc.getCheckstr());
   String show = "";
   if(au.getQuantity()<1){
	   checkstr ="广告匙不足！";
   }
}



%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
<title>兑换商品列表</title>
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

<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>

</head>

<body>
<div class="main" style="height:auto;">
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
						<p>你当前的位置：[广告管理]-[广告素材审核]</p>
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
      <form action="<%=basePath %>adver/saveCheck" id="saveForm" method="post">
         <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				<div class="rows">
					<label>名称:</label>
					<span class="input">
					   <%=name %>
					</span>
					<div class="clear"></div>
				</div>
				
				
				
				<div class="rows"  style="height:460;">
					<label>  广告图片:</label>
					<span class="input">
						<img id="div_img2"  src="<%=basePath %>images_new/adverpng.png" style="width:250;height:450;position:absolute;z-index:2;" />
					  <img src="${RESOURCE_LOCAL_URL}<%=adver_imageurls %>" style="width:250;height:450;"/>
					</span>
					<div class="clear"></div>
				</div>
				
			<%-- 	<div class="rows">
					<label>广告图片(iphone4):</label>
					<span class="input">
					  <img src="<%=basePath+adver_imageurls_small %>" />
					</span>
					<div class="clear"></div>
				</div> --%>
				
				<div class="rows">
					<label>微网站地址:</label>
					<span class="input">
					  <a href="<%=website %>" target="_brank" >点击查看微官网</a>
					</span>
					<div class="clear"></div>
				</div>
                <div class="rows">
					<label> 审核：</label>
					<span class="input">
					 	 <label><input type=radio name=adver_status value=1 onchange="show(1)" <% if(adver_status==1){ %> checked <% } %> />通过</label>
					     <label><input type=radio name=adver_status value=-1 onchange="show(0)" <% if(adver_status==-1){ %> checked <% } %> />不通过&nbsp;&nbsp;</label>
					</span>
					<div class="clear"></div>
				</div>	
				
			<div class="rows" id=nopass; style="display: none">
				<label>审批不通过说明:</label>
				<span class="input">
				  <textarea cols=10 rows=30 name="checkstr" id="checkstr"><%=checkstr %></textarea>
				</span>
				<div class="clear"></div>
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
        <input type=hidden id="id" name="id" value="<%=sc==null?"":sc.getId() %>" />
        <input type=hidden id="adver_imageurls" name="adver_imageurls" value="<%=adver_imageurls2 %>" />
        <input type=hidden id="adver_imageurls_small" name="adver_imageurls_small" value="<%=adver_imageurls_small2 %>" />
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
	   var New=document.getElementsByName("adver_status");
	      for(var i=0;i<New.length;i++)
	      {
	        if(New.item(i).checked){
	      		if(New.item(i).getAttribute("value")==-1){
	      			var checkstr=document.getElementById("checkstr").value;
	      			if(checkstr ==""){
	      				alert("请说明审批不通过原因！");
	      				return;
	      			}
	      		}
	     	}
	      }
      
      document.getElementById("saveForm").submit();
   }
   
   function show(type){
	   if(type==1){
		   document.getElementById("nopass;").style.display="none";
	   }else{
		   document.getElementById("nopass;").style.display="";
	   }
   }
   
 
   <%
   		if(adver_status==-1){
   %>
   show(0);
   <%}%>
   	
</script>
</body>
</html>
