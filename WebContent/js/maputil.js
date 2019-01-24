var longitude = document.getElementById("longitude").value;
var latitude = document.getElementById("latitude").value;
var coordinates0 = switchCoordinates0(longitude,latitude);//转换坐标系
	longitude = coordinates0[0]; 
	latitude = coordinates0[1];
var map = new BMap.Map("allmap");
var bMap_point = null;
if(longitude==null||longitude==""||longitude==undefined){
	bMap_point = new BMap.Point(117.10, 40.13);
	map.centerAndZoom(bMap_point, 11);
}else{
	bMap_point = new BMap.Point(longitude, latitude);
	map.centerAndZoom(bMap_point, 11);
}

map.addControl(new BMap.NavigationControl()); 
map.addControl(new BMap.ScaleControl()); 
map.addControl(new BMap.OverviewMapControl()); 
map.addControl(new BMap.MapTypeControl()); 
map.enableScrollWheelZoom(); 

map.clearOverlays();
var geolocation = new BMap.Geolocation();
geolocation.getCurrentPosition(function(r){
if(this.getStatus() == BMAP_STATUS_SUCCESS){
	/*var mk = new BMap.Marker(r.point);
	map.addOverlay(mk);
	map.panTo(r.point); */
	var mk = new BMap.Marker(bMap_point);
	map.addOverlay(mk);
	map.panTo(bMap_point);
}else {
	var point2 = new BMap.Point(113.76343399,23.04302382);
	map.centerAndZoom(point2,12);
}
var opts = {
		  position : r.point,    // 指定文本标注所在的地理位置
		  offset   : new BMap.Size(0, -50)    //设置文本偏移量
		};
		var label = new BMap.Label("您所在位置！", opts);  // 创建文本标注对象
			label.setStyle({
				 color : "red",
				 fontSize : "12px",
				 height : "20px",
				 lineHeight : "20px",
				 fontFamily:"微软雅黑"
			 });
		map.addOverlay(label);
},{enableHighAccuracy: true});

map.addEventListener("click", function (e) {  
	map.clearOverlays(); 
	var pointMarker = new BMap.Point(e.point.lng, e.point.lat); // 创建标注的坐标 
	var coordinates = switchCoordinates(e.point.lng,e.point.lat);//转换坐标系
	document.getElementById("longitude").value = coordinates[0]; 
	document.getElementById("latitude").value = coordinates[1];
	
	addMarker(pointMarker); 
	
	var mk = new BMap.Marker(pointMarker);
	map.addOverlay(mk);
	map.panTo(pointMarker); 
}); 
	
function addMarker(point) { 
	var myIcon = new BMap.Icon("mk_icon.png", new BMap.Size(21, 25), 
			{offset: new BMap.Size(21, 21), 
			 imageOffset: new BMap.Size(0, -21) 
			}
	); 
	var marker = new BMap.Marker(point, { icon: myIcon }); 
	map.addOverlay(marker); 
}

function flashaddress(){
	var province = document.getElementById("province");
	var city = document.getElementById("city");
	var county = document.getElementById("county");
	if(province.value!=null&&city.value!=null&&county.value!=null){
		document.getElementById("address").value = province.options[province.selectedIndex].text 
				+ city.options[city.selectedIndex].text 
				+ county.options[county.selectedIndex].text; 
	}
};

function gotoselect(path){
	if(path==undefined){
		return;
	}
	var ad = event.srcElement;
		$.ajax({
			type:"post",
			url:path+"seller/getAddressPoint.action",
			data: "address="+ad.options[ad.selectedIndex].text,
	        dataType: "text",
	        success: function (data){
	        	var point2 = new BMap.Point((eval('(' + data + ')').lng),(eval('(' + data + ')').lat));
	        	map.centerAndZoom(point2,12);
	        }
		});
};
	
function changeZone(path){
	if(path==undefined){
		return;
	}
	var ad = event.srcElement;
	var province = document.getElementById("province");
	var city = document.getElementById("city");
	var county = document.getElementById("county");
	gotoselect();
	if(parseInt(ad.value)>0){
		if(ad==province){
			city.options.length=0;				
		}
		county.options.length=0;
						
		$.ajax({
			type:"post",
			url:path+"seller/changeZone.action",
			data: "zonesid="+ad.value,        
			dataType: "text",
			success: function (data){
				var json=eval( '('+data+ ')' );
				if(ad==province){
					city.options.add(new Option("请选择", ""));
				}
				county.options.add(new Option("请选择", ""));
				for(var i in json){	
					if(ad==province){
						city.options.add(new Option(json[i],i));
				    }else{
				    	county.options.add(new Option(json[i],i));
				    }
				}
					
			}
		});
	}
}

function switchCoordinates(lng,lat){
	//国测局坐标(火星坐标,比如高德地图在用),百度坐标,wgs84坐标(谷歌国外以及绝大部分国外在线地图使用的坐标)
    //百度经纬度坐标转国测局坐标
    var bd09togcj02 = coordtransform.bd09togcj02(lng, lat);
    //国测局坐标转百度经纬度坐标
//    var gcj02tobd09 = coordtransform.gcj02tobd09(lng, lat);
    //wgs84转国测局坐标
//    var wgs84togcj02 = coordtransform.wgs84togcj02(lng, lat);
    //国测局坐标转wgs84坐标
//    var gcj02towgs84 = coordtransform.gcj02towgs84(lng, lat);
//    console.log(bd09togcj02);
//    console.log(gcj02tobd09);
//    console.log(wgs84togcj02);
//    console.log(gcj02towgs84);
    return bd09togcj02;
    //result
    //bd09togcj02:   [ 116.39762729119315, 39.90865673957631 ]
    //gcj02tobd09:   [ 116.41036949371029, 39.92133699351021 ]
    //wgs84togcj02:  [ 116.41024449916938, 39.91640428150164 ]
    //gcj02towgs84:  [ 116.39775550083061, 39.91359571849836 ]
}

function switchCoordinates0(lng,lat){
    //国测局坐标转百度经纬度坐标
    var gcj02tobd09 = coordtransform.gcj02tobd09(lng, lat);
    return gcj02tobd09;
}
