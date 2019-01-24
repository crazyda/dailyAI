<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";



%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath %>css/top.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css">
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<style> 
.navPoint { 
COLOR: white; CURSOR: hand; FONT-FAMILY: Webdings; FONT-SIZE: 9pt 
} 
</style> 
<script>
  /*用户标准管理*/
  function levels(){
     document.getElementById("iframe_content").src="<%=basePath%>level/list.action";
  }

  function addlevel(){
	  document.getElementById("iframe_content").src="<%=basePath%>level/add.action";
     
  }
  
  function editlevel(id){
	  document.getElementById("iframe_content").src="<%=basePath%>level/add.action?id="+id;
  }

  function dellevel(id){
	  document.getElementById("iframe_content").src="<%=basePath%>level/del.action?id="+id;
  }
  
  
  /*商家类型管理*/
  function shoptypes(){
     document.getElementById("iframe_content").src="<%=basePath%>shoptype/list.action";
  }

  function addshoptype(){
	  document.getElementById("iframe_content").src="<%=basePath%>shoptype/add.action";
     
  }

  function editshoptype(id){
	  document.getElementById("iframe_content").src="<%=basePath%>shoptype/add.action?id="+id;
  }

  function delshoptype(id){
	  document.getElementById("iframe_content").src="<%=basePath%>shoptype/del.action?id="+id;
  }
  
  /*地区管理*/
  function zones(){
     document.getElementById("iframe_content").src="<%=basePath%>zone/list.action";
  }

  function addzone(){
	  document.getElementById("iframe_content").src="<%=basePath%>zone/add.action";
     
  }

  function editzone(id){
	  document.getElementById("iframe_content").src="<%=basePath%>zone/add.action?id="+id;
  }

  function delzone(id){
	  document.getElementById("iframe_content").src="<%=basePath%>zone/del.action?id="+id;
  }
  
  
  /*兑换商品管理*/
  function goods(){
     document.getElementById("iframe_content").src="<%=basePath%>goods/list.action";
  }

  function addgoods(){
	  document.getElementById("iframe_content").src="<%=basePath%>goods/add.action";
     
  }

  function editgoods(id){
	  document.getElementById("iframe_content").src="<%=basePath%>goods/add.action?id="+id;
  }

  function delgoods(id){
	  document.getElementById("iframe_content").src="<%=basePath%>goods/del.action?id="+id;
  }
  
  function adver_imgs(id){
	  document.getElementById("iframe_content").src="<%=basePath%>goods/adverimgs.action?id="+id;
  }
  
  function checkimgs(){
	  document.getElementById("iframe_content").src="<%=basePath%>goods/checkimgs.action";
  }
  
  function add_checkimgs(id){
	  document.getElementById("iframe_content").src="<%=basePath%>goods/add_checkimgs.action?id="+id;
  }

  /*商家管理*/
  function users(){
     document.getElementById("iframe_content").src="<%=basePath%>user/list.action";
  }

  function adduser(){
	  document.getElementById("iframe_content").src="<%=basePath%>user/add.action";
     
  }
  
 

  function edituser(id){
	  document.getElementById("iframe_content").src="<%=basePath%>user/add.action?id="+id;
  }
  
  function edit_usersetting(id){
	  document.getElementById("iframe_content").src="<%=basePath%>user/addusersetting.action?id="+id;
  }

  function deluser(id){
	  document.getElementById("iframe_content").src="<%=basePath%>user/del.action?id="+id;
  }
  
  function addusercore(id){
	  document.getElementById("iframe_content").src="<%=basePath%>user/addscore.action?id="+id;
  }
  
  function resetpwd(id){
	  document.getElementById("iframe_content").src="<%=basePath%>user/resetpwd.action?id="+id;
  }
  
  function extendresult(id){
	  document.getElementById("iframe_content").src="<%=basePath%>user/extendresult.action?id="+id;
  }
  
  function changelevel(id){
	  document.getElementById("iframe_content").src="<%=basePath%>user/changelevel.action?id="+id;
  }
  
  function addusermoney(id){
	  document.getElementById("iframe_content").src="<%=basePath%>user/addcost.action?id="+id;
     
  }
  
  /*积分类型管理*/
  function scoretypes(){
     document.getElementById("iframe_content").src="<%=basePath%>scoretype/list.action";
  }

  function addscoretype(){
	  document.getElementById("iframe_content").src="<%=basePath%>scoretype/add.action";
     
  }

  function editscoretype(id){
	  document.getElementById("iframe_content").src="<%=basePath%>scoretype/add.action?id="+id;
  }

  function delscoretype(id){
	  document.getElementById("iframe_content").src="<%=basePath%>scoretype/del.action?id="+id;
  }
  
  
  /*积分类型管理*/
  function userscoretypes(){
     document.getElementById("iframe_content").src="<%=basePath%>userscoretype/list.action";
  }

  function adduserscoretype(){
	  document.getElementById("iframe_content").src="<%=basePath%>userscoretype/add.action";
     
  }

  function edituserscoretype(id){
	  document.getElementById("iframe_content").src="<%=basePath%>userscoretype/add.action?id="+id;
  }

  function deluserscoretype(id){
	  document.getElementById("iframe_content").src="<%=basePath%>userscoretype/del.action?id="+id;
  }
  
  /*微网站管理*/
  function websites(){
     document.getElementById("iframe_content").src="<%=basePath%>website/list.action";
  }

  function addwebsite(){
	  document.getElementById("iframe_content").src="<%=basePath%>website/add.action";
     
  }

  function editwebsite(id){
	  document.getElementById("iframe_content").src="<%=basePath%>website/add.action?id="+id;
  }

  function delwebsite(id){
	  document.getElementById("iframe_content").src="<%=basePath%>website/del.action?id="+id;
  }
  
  
  /*积分兑换记录管理*/
  function exchangescores(){
     document.getElementById("iframe_content").src="<%=basePath%>exchangescore/list.action";
  }

  function delexchangescore(id){
	  document.getElementById("iframe_content").src="<%=basePath%>exchangescore/del.action?id="+id;
  }
  
  
  /*消息管理*/
  function messages(){
     document.getElementById("iframe_content").src="<%=basePath%>message/list.action";
  }

  function addmessage(){
	  document.getElementById("iframe_content").src="<%=basePath%>message/add.action";
     
  }

  function editmessage(id){
	  document.getElementById("iframe_content").src="<%=basePath%>message/add.action?id="+id;
  }

  function delmessage(id){
	  document.getElementById("iframe_content").src="<%=basePath%>message/del.action?id="+id;
  }
  
  /*邀请记录*/
  function inviterecords(){
     document.getElementById("iframe_content").src="<%=basePath%>inviterecord/list.action";
  }
  
  function addbaseinfo(id){
	  
	  document.getElementById("iframe_content").src="<%=basePath%>baseinfo/add.action?id="+id;
  }

  /*幻灯片*/
  function slides(){
     document.getElementById("iframe_content").src="<%=basePath%>slide/list.action";
  }

  function addslide(){
	  document.getElementById("iframe_content").src="<%=basePath%>slide/add.action";
     
  }

  function editslide(id){
	  document.getElementById("iframe_content").src="<%=basePath%>slide/add.action?id="+id;
  }

  function delslide(id){
	  document.getElementById("iframe_content").src="<%=basePath%>slide/del.action?id="+id;
  }
  
  /*滑屏广告位设置*/
  function adversettings(){
     document.getElementById("iframe_content").src="<%=basePath%>adversetting/list.action";
  }
  
  function adverrecord(id){
	  
	  document.getElementById("iframe_content").src="<%=basePath%>adverrecord/list.action?good_adver_id="+id;
  }

  function addadversetting(){
	  document.getElementById("iframe_content").src="<%=basePath%>adversetting/add.action";
     
  }

  function editadversetting(id){
	  document.getElementById("iframe_content").src="<%=basePath%>adversetting/add.action?id="+id;
  }

  function deladversetting(id){
	  document.getElementById("iframe_content").src="<%=basePath%>adversetting/del.action?id="+id;
  }
  
  function adveronline(id,status){
	  document.getElementById("iframe_content").src="<%=basePath%>adversetting/online.action?id="+id+"&status="+status;
  }
  
  
  
  
  /*六维积分奖励*/
  function rewardscores(){
     document.getElementById("con_frame").src="<%=basePath%>rewardscore/list.action";
  }

  function addrewardscore(){
	  document.getElementById("con_frame").src="<%=basePath%>rewardscore/add.action";
     
  }

  function editrewardscore(id){
	  document.getElementById("con_frame").src="<%=basePath%>rewardscore/add.action?id="+id;
  }

  function delrewardscore(id){
	  document.getElementById("con_frame").src="<%=basePath%>rewardscore/del.action?id="+id;
  }
  
  /*推广人数限制*/
  function extendlimits(){
     document.getElementById("con_frame").src="<%=basePath%>extendlimit/list.action";
  }

  function addextendlimit(){
	  document.getElementById("con_frame").src="<%=basePath%>extendlimit/add.action";
     
  }

  function editextendlimit(id){
	  document.getElementById("con_frame").src="<%=basePath%>extendlimit/add.action?id="+id;
  }

  function delextendlimit(id){
	  document.getElementById("con_frame").src="<%=basePath%>extendlimit/del.action?id="+id;
  }
  
  
  /*充值记录*/
  function feerecords(){
     document.getElementById("con_frame").src="<%=basePath%>feerecord/list.action";
  }

  function addfeerecord(){
	  document.getElementById("con_frame").src="<%=basePath%>feerecord/add.action";
     
  }

  function editfeerecord(id){
	  document.getElementById("con_frame").src="<%=basePath%>feerecord/add.action?id="+id;
  }

  function delfeerecord(id){
	  document.getElementById("con_frame").src="<%=basePath%>feerecord/del.action?id="+id;
  }
  
  
  /*代理商区域*/
  function proxyzones(){
     document.getElementById("con_frame").src="<%=basePath%>proxyzone/list.action";
  }

  function addproxyzone(parent_id){
	  document.getElementById("con_frame").src="<%=basePath%>proxyzone/add.action?parent_id="+parent_id;
     
  }

  function editproxyzone(id,parent_id){
	  document.getElementById("con_frame").src="<%=basePath%>proxyzone/add.action?id="+id+"&parent_id="+parent_id;
  }

  function delproxyzone(id){
	  document.getElementById("con_frame").src="<%=basePath%>proxyzone/del.action?id="+id;
  }
  
  /*收益列表*/
  function userprofits(){
     document.getElementById("con_frame").src="<%=basePath%>userprofit/list.action";
  }
  
  
  /*代理商管理*/
  function proxyinfos(){
     document.getElementById("con_frame").src="<%=basePath%>proxyinfo/list.action";
  }

  
  function delproxyinfo(id){
	  document.getElementById("con_frame").src="<%=basePath%>proxyinfo/del.action?id="+id;
  }

 </script>

</head>

<body style="overflow:hidden">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
  <tr>
  
    <td id="left" noWrap name="fmTitle" align="center" valign="top"><table height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
      <tr>
        <td><iframe name="I1" height="100%" src="<%=basePath %>constant/left.jsp" border="0" frameborder="0" scrolling="no"> 浏览器不支持嵌入式框架，或被配置为不显示嵌入式框架。</iframe></td>
      </tr>
    </table>		</td>
    <td id="iframe_content1" width="100%" align="left" valign="top">
         <iframe name="I2" id="con_frame" width="100%" height="100%" border="0" frameborder="0" src="<%=basePath %>user/list.action">
    
    </td>
  </tr>
</table>

</body>
</html>