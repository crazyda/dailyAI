<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Levels"%>
<%@page import="com.axp.util.Utility"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript" src="<%=basePath %>js/WdatePicker.js"></script>
<title>用户标准列表</title>
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
						<p>你当前的位置：[广告管理]-[广告池管理]-[广告池播放设置]</p>
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
     <form action="<%=basePath %>adverPool/savePlayAdver" id="saveForm" method="post">
     <div id="products" class="r_con_wrap">
      <div class="r_con_form" >
      	<div class="rows">
			<label>广告名称</label>
			<span class="input">
			  <input type=text name="name" id="name" value="${adverpool.goods.name }" readonly ="true" /><font color="red">*</font>
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>广告图片(普通):</label>
			<span class="input">
					
                <div id="div_img" >
                      <img src ="${RESOURCE_LOCAL_URL}${adverpool.goods.adverImgurls }" id="img_id" width="160" height="284" />
                </div>
		               
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>重要说明：</label>
			<span class="input">
			  	<font color =red>滑屏广告投放扣除对应级别的${coin.name},使用数量则是连续播放的天数。开始时间则表示广告开始播放的日期</font>
			</span>
			<div class="clear"></div>
		</div>	
		<div class="rows">
			<label>播放范围</label>
			<span class="input">
			<c:if test="${userLevel==85 }">
			<!-- 	<input type="radio" name="areaType" value="10" onclick="parentDetail();"/>全国&nbsp; -->
			  	<input type="radio" name="areaType" value="" onclick="childDetail();"/>全省&nbsp;
			  	（实际消耗天数=所选范围商圈数x播放天数）
			</c:if>
			<c:if test="${userLevel==75 }">
				<input type="radio" name="areaType"  <c:if test="${hasHigel=='1' }"> disabled="disabled"</c:if> value="20"  onclick="parentDetail();"/>全省&nbsp;
				<input type="radio" name="areaType" value="" onclick="childDetail();"/>全市&nbsp;
				（实际消耗天数=所选范围商圈数x播放天数）
			</c:if>
			<c:if test="${userLevel==65 }">
			 	<input type="radio" name="areaType" <c:if test="${hasHigel=='1' }"> disabled="disabled"</c:if> value="30" onclick="parentDetail();"/>全市&nbsp;
			  	<input type="radio" name="areaType" value="" onclick="childDetail();"/>商圈区域&nbsp;
			  	（实际消耗天数=所选范围商圈数x播放天数）
			</c:if>
			<c:if test="${userLevel==60 }">
			 	<input type="radio" name="areaType" value="" onclick="childDetail();"/>商圈&nbsp;
			</c:if>
			<br>
			<span id="areaDetail1" >
				<c:forEach items="${childAreaMap }" var="s">${s.key }${s.value }&nbsp;</c:forEach>
			</span><br>
			<span id="areaDetail2">
				<c:forEach items="${parentAreaMap }" var="s">${s.key }${s.value }&nbsp;</c:forEach>
			</span>
			</span>
			<div class="clear"></div>
		</div>	
		<div class="rows">
			<label>播放天数</label>
			<span class="input">
			  <input type=text name=playday id=playday value=""  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>${coin.unit }
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>实际消耗天数</label>
			<span class="input">
			  <input type=text readOnly="true" name=playtotal id=playtotal value="${adverpool.playtotal }"/>${coin.unit }
				（当前广告天数余额：${quantity }）
			</span>
			<div class="clear"></div>
		</div>
		<div class="rows">
			<label>开始时间：</label>
			<span class="input">
			 <input class="login-input-username" type=text id=starttime name=starttime value="<fmt:formatDate value='${adverpool.starttime }' pattern='yyyy-MM-dd'/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd '});"  />
			</span>
			<div class="clear"></div>
		</div>
		
	
     
		<div class="rows">
			<label></label>
			<span class="input">
			  <input type=button class="button" onclick="savePtype(2);" value="播放 " /><%-- ${childAreaSize} --%>
			</span>
			<div class="clear"></div>
		</div>
      </div>
	<input type=hidden id="id" name="id" value="${adverpool.id }" />
	<input type=hidden id="quti" name="quti" value="${quantity }" />
	<input type=hidden id="adver_id" name="adver_id" value="${adverpool.goods.id }" />
	<input type=hidden id="isplay" name="isplay" value="" />
	
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
$('#areaDetail2').hide();
$('#areaDetail1').hide();
	function parentDetail(){
		   $('#areaDetail2').show();
		   $('#areaDetail1').hide();
	}
   function childDetail(){
	   $('#areaDetail1').show();
	   $('#areaDetail2').hide();
   }
   $('#playday').change(function(){
	   var total = 0;
	   
	   if(parseInt('${userLevel}')<85){
		   var val=$('input:radio[name="areaType"]:checked').val();
		   
		   if(val!=null&&val!=''){//越级
			   total = $(this).val()*parseInt('${parentAreaSize}');
		   }else{
			   total = $(this).val()*parseInt('${childAreaSize}');
			   
		   }
	   }else{
		   if(parseInt('${childAreaSize}')>0){
			   total = $(this).val()*parseInt('${childAreaSize}');
		   }else{
			   total = $(this).val(); 
		   }
	   }
	   $('#playtotal').val(total);
   });
   function savePtype(type){
	   var playday = document.getElementById("playday").value;
	   var playtotal = document.getElementById("playtotal").value;
	   var quti = document.getElementById("quti").value;
	      if(playday==""||parseInt(playday)<=0){
	         alert("请输入${coin.name}，并保证大于0!");
	         return;
	      }
	      if(parseInt(playtotal)<=0){
	    	  alert("当前所选范围无商圈，不可投放!");
		      return;
	      }
	      var starttime = document.getElementById("starttime").value;
	      if(starttime==""){
	         alert('请输入开始时间!');
	         return;
	      }
	      if(starttime=="${adverpool.starttime}"){
	    	  alert("开始投放时间错误");
	    	  return;
	      }
	      
	      if(playtotal-quti>0){
	      	alert("广告天数不足！");
	      	return;
	      }
	      
      	if(type ==null){
      		type =0;
      	}
     
	    document.getElementById("isplay").value =type;
        document.getElementById("saveForm").submit();
   }

</script>
</body>
</html>
