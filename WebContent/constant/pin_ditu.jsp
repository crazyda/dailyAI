<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String address = request.getParameter("address");

String latitude = request.getParameter("latitude");
String longitude = request.getParameter("longitude");

%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>事件监听函数参数</title>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
</head>
<body>

 <div>
    地址：<input id="address" type="textbox" value="<%=address%>">
    <input type="button" value="重新标记" onclick="codeAddress()">
    <input type="button" value="确认" onclick="confirmMap()">
  </div>
  <form action="ditu_save.asp" method="post" name="dituFrm" id="dituFrm">
     <input type=hidden name="latitude" id="latitude" value="<%=latitude%>" />
 	 <input type=hidden name="longitude" id="longitude" value="<%=longitude%>" />
  </form>
<div style="width:1000px;height:500px;border:1px solid gray" id="container"></div>

</body>
</html>
<script type="text/javascript">
var map = new BMap.Map("container");
var latitude="<%=latitude%>";
var longitude = "<%=longitude%>";
var address="<%=address%>";
var ispoint=true;
if(!latitude){
   //latitude="23.03955";
//   longitude="113.77892";
    ispoint=false;
   latitude="113.77892";
   longitude="23.03955";
}


map.centerAndZoom(new BMap.Point(latitude,longitude), 15);
map.addControl(new BMap.NavigationControl()); 
map.addControl(new BMap.ScaleControl()); 
map.addControl(new BMap.OverviewMapControl()); 
map.addControl(new BMap.MapTypeControl());

if(!ispoint){
     //根据地址查找
	if(!address || address==""){
		var point = new BMap.Point(latitude, longitude);
		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);              // 将标注添加到地图中
	//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	
	}else{
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(address, function(point){
		  if (point) {
			map.centerAndZoom(point, 16);
			map.addOverlay(new BMap.Marker(point));
           document.getElementById("longitude").value=point.lat;
		   document.getElementById("latitude").value=point.lng;
			
		  }
		}, "");
	}
}else{
     var point = new BMap.Point(latitude, longitude);
	 var marker = new BMap.Marker(point);  // 创建标注
	 map.addOverlay(marker);
}

function codeAddress(){
    var addr = document.getElementById("address").value;
	//alert('==========1');
    var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint(addr, function(point){
	  if (point) {
		map.centerAndZoom(point, 16);
		map.addOverlay(new BMap.Marker(point));
		document.getElementById("longitude").value=point.lat;
		document.getElementById("latitude").value=point.lng;
	  }
	}, "东莞");
}

function confirmMap(){
  var lng = document.getElementById("longitude").value;
  if(lng=="" || !lng){
     alert("请标识公司地址坐标！");
     return;
  }

  document.getElementById("dituFrm").submit();
}
</script>