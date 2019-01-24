<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!-- saved from url=(0022) -->
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath %>css/top.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css">
<title>无标题文档</title>

</head>

<body>
	<div class="jb_head">
		<div class="but">
			<div class="but-1">
				<div class="but-1-cell" style="text-align:left;margin-left:10%;cursor: pointer;" onclick="logout();">
					<img src="../img/new-5.png" />退出
				</div>
				<div class="but-1-cell" style="text-align:right">
					<p>
						<img src="../img/new-4.png" />亲!你好
					</p>
				</div>
			</div>
		</div>
	</div>
	<script>

   function logout(){
	   var url= "<%=basePath%>login/Logout.action";
	   parent.document.location = url;
   }
   function customers(){
	
	   parent.mainFrame.middelIfm.customers();
   }

   function owner(){
	   var role_id = 1;
	 //  alert('roelid='+role_id);
	   switch(role_id){
	     case 1:
           //管理员
		   parent.mainFrame.middelIfm.proxys(1);
           break;
	     
	   }
   }

   function top_Menu(f){
	
       switch(f){
	       case 4:
		    	parent.mainFrame.middelIfm.students();
			    break;
    }
	    
   }
   

   
 </script>



		
	</body>
</html>

