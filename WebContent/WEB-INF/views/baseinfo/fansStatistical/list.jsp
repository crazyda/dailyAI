<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" deferredSyntaxAllowedAsLiteral="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>粉丝查询</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<link type="text/css" href="<%=basePath %>css/orders/child-page.css" rel="stylesheet">
<link type="text/css" href="<%=basePath %>css/orders/merchant-order.css" rel="stylesheet">
<script src="<%=basePath %>js/order/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
</head>
<body>
<div class="merchant">
<div class="header">
	<div class="header-left">
		<p>你当前的位置：[粉丝查询]-[查询]</p>
	</div>
	<hr/>
</div>
<br>
	<div class="tab">
		<form  id="saveForm" action="list"  class="orderform" method="post">
			<input type="hidden" id="zoneId" name="zoneId" value=""/> 
            <p class="name">粉丝账号：<input type="text"  name="userId"  value="${requestScope.userId }"   placeholder="手机号或粉丝Id号" onkeyup="value=value.replace(/[^\d]/g,'')"></p>
            <p class="startTime">活跃开始时间：<input class="startTM" type=text id=starttime name="sTM" value='${requestScope.sTM }' placeholder="只能查询两个月内的记录" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen',minDate:'%y-#{%M-2}-%d-%H-%m-%s', maxDate: '%y-%M-%d-%H-%m-%s' })"/></p>
            <p class="endTime">活跃结束时间： <input class="endTM" type=text id=endtime name="eTM" value='${requestScope.eTM }' placeholder="只能查询两个月内的记录" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',skin:'whyGreen',minDate:'%y-#{%M-2}-%d-%H-%m-%s', maxDate: '%y-%M-%d-%H-%m-%s' })" /></p>
         	
         	<div>
         	<p class="county" style="position: relative; ">  活跃地区:
         		
				<span>市</span>
				<select  name="city" id="cityId" onchange="changeZone();" >
					<option value=0>请选择</option>
					<c:forEach items="${zonelist }" var="zone">
						<option value="${zone.id }" <c:if test="${zone.id == enum1.provinceEnum.id }">selected="selected"</c:if>>${zone.name}</option>
					</c:forEach>
				</select>
				
				
				<span >区县</span>
				<select  name="county" id="countyId" onchange="changeZone();" style="position:absolute;left:550px;top:28%;width:50%;"> 
					<option value=0>请选择</option>
						<option value="${enum1.id }" <c:if test="${enum1.id == enum1.id }">selected="selected"</c:if>>${enum1.name}</option>
				</select> 
				
            </p>
            </div>
            <p class="btn">
            	<input type="submit" value="搜索" class="btn" onclick="savePtype();"><font style="color: red;">*该功能从4月21号正式启用，只能查询到该日之后的活跃数据!</font>
            </p>
				<div class="count" style="float: right;">	
					<span> 总活跃量&nbsp;:&nbsp;</span>	
					<span style="float:right; margin-right:30px;" id="count">${count} 位</span>
				</div>
        </form>
            	 <div class="table"> 
                	<table>
                    	<thead>
                        	<tr>
                            	<th>用户名称</th>
                            	<th>昵称</th>
                            	<th>头像</th>
                            	<th>积分</th>
                            	<th>注册时间</th>
                            	<th>金额</th>
                            	<th>最后活跃时间</th>
                            	<th>地区</th>
                            	<th>最后活跃地区</th>
                            </tr>
                        </thead>
					  <c:forEach items="${usersOperationRecord}" var="record"> 
						<tr>
								<td>${record.users==null?"":fn:substring(record.users.name,0,7)}****</td>
								<td>${record.users==null?"":record.users.realname }</td>
								<td>
								
								<c:choose>
								
									<c:when test="${record.users!=null&&record.users.headimage!=null&&record.users.headimage!='http://jifen.aixiaoping.cn:8080/dailyRes/null'&&record.users.headimage!='http://jifen.aixiaoping.cn:8080/dailyRes/'}">
										<img src="${record.users.headimage}" style="width:60px;height:60px;" />
									</c:when>
									<c:when test="${record.users!=null&&record.users.imgUrl!=null&&record.users.imgUrl!='http://jifen.aixiaoping.cn:8080/dailyRes/null'&&record.users.imgUrl!='http://jifen.aixiaoping.cn:8080/dailyRes/'}">
											<img src="${RESOURCE_LOCAL_URL}${record.users.imgUrl}" style="width:60px;height:60px;" />
									</c:when>
									<c:otherwise>
										<img src="http://jifen.aixiaoping.cn:8080/dailyRes/upload-res/message_icon/1/nomal/6200881211100906819.png" style="width:60px;height:60px;" />
									</c:otherwise>
								</c:choose>
								
								
								</td>
								<td>${record.users==null?"":record.users.score }</td>
								<td>${record.users==null?"":record.users.createtime }</td>  
								<td>${record.users==null?"":record.users.money }</td>
								<td>${record.lasttime }</td>
								<td>${record.provinceEnum==null?"":record.provinceEnum.name }</td>
								<td>${record.provinceEnum==null?"":record.provinceEnum.name }</td>
						</tr>
					</c:forEach>
					</table>
					 </div> 
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
</div>
<script type="text/javascript">
function changeZone(){
	  var ad = event.srcElement;
	  var city = document.getElementById("cityId");
	  var county = document.getElementById("countyId");
			if(parseInt(ad.value)>0){
				if(ad==city){
					county.options.length=0;				
				}
				$.ajax({
					type:"post",
					url:"<%=basePath %>system/FansStatistical/changeZone",
					data: "zoneId="+ad.value,        
			        dataType: "text",
			        success: function (data){
			        	var json=eval( '('+data+ ')' );
		    	    	 if(ad==city){
		    	    		 county.options.add(new Option("请选择", ""));
		    	    	 }
		    	    	 for(var i in json){	 
		    	    		 if(ad==city){
		    	    			 county.options.add(new Option(json[i],i));
		    	    		 }
		    	    	 }
			
			        }
				})
			}
			
	}
	
function savePtype(){
	   var zoneId = $("input#zoneId");
	   var city =$("select#cityId").find("option:selected").text();
	   var county =$("select#countyId").find("option:selected").text();
	   if(county && county!='请选择'){
		   $("input#uri").val(county);
		   zoneId.val($("select#countyId").val());
	   }
	   else if(city && city!='请选择'){
		   $("input#uri").val(city);
		   zoneId.val($("select#cityId").val());
	   }
		document.getElementById("saveForm").submit();
}
</script>
</body>
</html>


















