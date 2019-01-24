<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>推广优惠券</title>
	<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
	<%-- <script type="text/javascript" src="<%=basePath %>js/mian.js"></script> --%>
 		<style type="text/css">
			* {
				margin: 0px;
				padding: 0px;
			}
			
			.container {
				margin-left: 10%;
				margin-top: 20px;
				width: 1024px;
				height: 700px;
				/* border: 1px solid black; */
			}
			
			.choice {
				width: 800px;
				height: 65px;
				
			}
			
			.line {
				height: 75px;
				line-height: 75px;
				float: left;
				width: 300px;
				text-align: center;
				margin-top: 7px;
			}
			
			.line2 {
				float: left;
				width: 500px;
				height: 75px;
				line-height: 75px;
				text-align: left;
				
			}
			
			.line2>select {
				height: 45px;
				width: 150px;
				line-height: 45px;
				margin-top: 20px;
			}
			
			.desc {
				float: left;
				width: 300px;
				height: 50px;
				line-height: 50px;
				text-align: center;
				
			}
			
			.layout_input {
				width: 500px;
				height: 50px;
				line-height: 50px;
				text-align: left;
				float: left;
			}
			
			.layout_input>input {
				
				width: 200px;
				height: 30px;
				line-height: 30px;
				text-align: left;
				margin-top: 10px;
				
			}
			.layout_div{
				margin: 20px 0 0 0;
			}
			.tips{
				margin-left: 30px;
				display: none;
				color: red;
			}
			
			
			
			
			#choicePid{
				font-size:18px;
				width: 350px;
				font-weight: bold;
				overflow-y:auto;
			}
			.tip{
				font-size: 12px;
				color: red;
			}
			.confirm{
				width: 100%;
				height: 100px;
				float: left;
				margin-top: 50px;
				
			}
			.confirm>input{
				width: 150px;
				height: 50px;
				font-size: 24px;
				margin-left: -250px;
			}
			
			#level{
				font-size:18px;
				font-weight: bold;
			}
		</style>  
	</head>

	<body>

		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[系统管理]-[群功能管理]-[创建群]</p>
				</div>
				<hr align="center" />
			</div>

			<div class="container">
				<form>

					<div class="layout_div">
						<div class="desc">
							<div>群名称:</div>
						</div>

						<div class="layout_input">
							<input type="text" name="groupName" id="groupName" value="" />
							<span class="tips">提示:</span>
						</div>
					</div>
					<div class="layout_div">
						<div class="desc">
							<div>群公告:</div>
						</div>

						<div class="layout_input">
							<input type="text" name="groupNotice" id="groupNotice" value="" />
							<span class="tips">提示:</span>
						</div>
					</div>
					<div class="layout_div">
						<div class="desc">
							<div>群信息:</div>
						</div>

						<div class="layout_input">
							<input type="text" name="groupInfo" id="groupInfo" value="" />
							<span class="tips">提示:</span>
						</div>
					</div>
					<div class="layout_div">
						<div class="desc">
							<div>群头像:</div>
						</div>

						
			<div class="rows">
						<label>封面:</label>
						<span class="input">
						   (图片尺寸：600*300)<br/>
			                <iframe name="if_upload" id="if_upload" src="${BASEPATH_IN_SESSION}imagehandle/showupload?uploadtype=17" height=80 FRAMEBORDER=0 SCROLLING=no> </iframe>
			                <div id="div_img" style="display:none">
			                      <img src ="" id="img_id" />
			                </div>
						</span>
						<div class="clear"></div>
			</div>
						
					</div>
					 <div class="choice">
						 <div class="line">群类别 :</div>
						<div class="line2">
							<select name="level" id="level">
								
								<option value="2" >事业合伙人</option>
								<option value="3" >合伙人</option>
						 	</select>
							<span class="tips">提示:</span>
						</div> 
					</div>
					
					
					
					
					<div class="confirm">
							<input type="button" name="" id="sub" value="提交" />
					</div>
				</form>
			</div>

		</div>

				<script type="text/javascript">
							$(function(){
									var datas;
									//控制pid
									var flag=true;
									
									
									
										//提交表单
										$("#sub").click(function(){
											
										 	if(!checkSub(flag)){
												alert("请填下完整信息");
											}else{
												var level= $("select[name=level]").val();
												var groupName= $("input[name=groupName]").val();
												var groupInfo= $("input[name=groupInfo]").val();
												var groupNotice = $("input[name=groupNotice]").val();
												
												$.ajax({
													
													"type":"POST",
													"url":"<%=basePath%>system/group/createGroup",
													"data": {"level":level,"groupName":groupName,"groupInfo":groupInfo,"groupNotice":groupNotice},
													"error":function (data){
														alert(data);
													},
													"success":function(data){
														
														 location.href = "<%=basePath%>system/group/groupList";
													}
												});
												
												
											}
										});
									});
									$("#groupName").blur(function(){
										var groupName= $("#groupName").val();
										var span= $("#groupName").next("span");
										if(groupName.length>=30 || groupName==null || groupName==""){
													$(span).text("群名称不能为空且限制为30字符");
													$(span).show();
													$(span).attr("color","red");
													flag=false;
												}else{
													$(span).hide();
													flag=true;
												}
									});
								
				 	function checkSub(flag){
						
							var level= $("select[name=level]").val();
							var groupName= $("input[name=groupName]").val();
							var groupInfo= $("input[name=groupInfo]").val();
							var span= $("#groupName").next("span");
							if(!groupName || groupName.length>=30){
									$(span).text("群名称不能为空且限制为30字符");
									$(span).show();
									$(span).attr("color","red");
								return false ;
							}else{
								$(span).hide();
								return true;
							}
						/* 	alert(222);
							var data={id:id,level:level,groupName:groupName,ldLoginName:ldLoginName,groupInfo:groupInfo,
									ldLoginReamrk:ldLoginReamrk,remark:remark,parentPidId:parentPidId}
							document.getElementById("sub").disabled = 'disabled';
								 $.ajax({
									async:false,
									url:"taoke/pid/saveTkldPid",
									data:data,
									success:function(data){
										 if(flag==1){
											alert("新增成功");
											location.href='taoke/pid/toPidList';
											}
											else{ 
												alert("新增失败");
												document.getElementById("sub").disabled ='';
											}
									}
								}) 
							 */
							return true;
					}

				 	
				 	
				 	
				    function setImageUrlTure(src,url){

						 $("#div_img").html(src);
						 $("#div_img").show();

						 $("#imageurls").attr("value",url);
					}
					function setImgurl(url){
							url = "messagecenter_cover/"+url;
					       var url1 = "${RESOURCE_LOCAL_URL}"+url;
					       var src = "&nbsp;<img src=\""+url1+"\"  ondblclick=\"delImg('"+url+"');\" alt='双击删除图片' title='双击删除图片' />";
						   setImageUrlTure(src,url);
					}

					function delImg(url){
					   var imageurls = $("#imageurls").attr("value");
					   var iarr = imageurls.split(",");
					   var strs = "";
					   for(i=0;i<iarr.length;i++){
				          var s = iarr[i];
				          if(s==url || s==""){
				             continue;
				           }
				          strs = strs + s+",";
					   }
					  // $("#imageurls").attr("value",strs);
					  
					   $("#div_img").html("");
					   $("#imageurls").attr("value","");
				       if('${message}'!=null){
					   		messageservice.delImg('${message.id}',url,1,callback_delimg);
				       }
					   setImgurls(strs);
				   }
				</script>
	
	</body>

</html>