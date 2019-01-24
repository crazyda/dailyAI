<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>聚宝屏-后台1</title>
<link href="<%=basePath%>css/css3.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="right">
		<div class="right-s">
			<div class="right-s-1">
				<div class="right-s-1-1">
					<p style="margin-left:10px;">系统消息</p>
				
				</div>
				<div class="right-s-1-2">
					<ul>
						<li><nobr>国家主席习近平3日上午抵达首尔</nobr></li>
						<li><nobr>国家主席习近平3日上午抵达首尔</nobr></li>
						<li><nobr>国家主席习近平3日上午抵达首尔</nobr></li>
						<li><nobr>国家主席习近平3日上午抵达首尔</nobr></li>
					</ul>
				</div>
			</div>
			<div class="right-s-2">
				<div class="right-s-2-1">
					<div class="right-s-1-1">
					<p>我的六维人脉 	<span>>></span></p>
					</div>
					
					<div class="liuwei1">
					<p>今日新增</p>
						<div class="liuwei1-1">
							<p>20</p>
						</div>
					</div>
					<div class="liuwei2">
					<p>累积人数</p>
						<div class="liuwei1-1">
							<p>6000</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="right-z">
			<div class="right-z-1">
					<p>我投放的广告<span>>></span></p>
			</div>
			<div class="right-z-2">
					<img src="<%=basePath%>img/g1.png"/>
					<div class="right-z-2-zhengzai"><p>正在播放的广告</p></div>
					
			</div>
			<div class="right-z-2-3">
				<div class="right-z-2-3-shang">
					<p>最新广告</p>
					<img src="<%=basePath%>img/g2.png"/>
					<img src="<%=basePath%>img/g2.png"/>
					<img src="<%=basePath%>img/g2.png"/>
					
					<img src="<%=basePath%>img/g2.png"/>
				</div>
				<div class="right-z-2-3-xia">
					<p>下架广告</p>
					<img src="<%=basePath%>img/g2.png"/>
					<img src="<%=basePath%>img/g2.png"/>
					
					<img src="<%=basePath%>img/g2.png"/>
					<img src="<%=basePath%>img/g2.png"/>
				</div>
			</div>
		</div>
	</div>
	</body>
	</html>