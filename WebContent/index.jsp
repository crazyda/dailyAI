<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

 
int errortype = 0 ;
if(request.getParameter("errortype")!=null){
	
	errortype = Integer.parseInt(request.getParameter("errortype"));
}

Object obj = request.getAttribute("uri");

if(obj!=null){
	String reduri =obj.toString(); 
	String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String reduripath = serverPath + reduri+"?1=1";
	if(errortype!=0){
		reduripath = reduripath + "&errortype="+errortype;
	}
	%>
     <script>
	   parent.parent.parent.document.location = "<%=reduripath%>";
	</script>
	<%
}

String uri = request.getRequestURI();
if(uri.indexOf('.')==-1){
	uri = uri.concat("index.jsp");
}


if(request.getParameter("exitsystem")!=null){
	String reduri =request.getParameter("uri");
	String serverPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
	String reduripath = serverPath + reduri+"?1=1";
	if(errortype!=0){
		reduripath = reduripath + "&errortype="+errortype;
	}
	%>
	     <script>
		   parent.parent.parent.document.location = "<%=reduripath%>";
		</script>
	<%
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>每天积分-登陆</title>
<link href="<%=basePath %>css/login.css" rel="stylesheet" type="text/css" />
<script>
  function dosubmit(){
	  var name = document.getElementById("name").value;
	  if(name==""){
          alert('请输入用户名！');
	      return;
	  }
	  
	  var pwd = document.getElementById("pwd").value;
	  if(pwd==""){
          alert('请输入密码！');
	      return;
	  }
     document.getElementById("loginForm").submit();
  }
  var errortype = <%=errortype%>;
  if(errortype==-1){
      alert('登陆失败！登录名不存在！');
  }
  if(errortype==-2){
      alert('登陆失败！密码错误！');
  }
  if(errortype==-3){
      alert('登陆失败！ 未知错误！');
  }

  function butOnClick()
  {
          if(event.keyCode==13)
          {
        	  dosubmit();  
              return false;                               
          }
  }
  function addLoadEvent(func){
		var oldonload=window.onload;
		if(typeof window.onload!='function'){
			window.onload=func;
			}
			else{
				window.onload=function(){
					oldonload();
					func();
					};
				}
		}
		
		function checklist(){
			var li=document.getElementById("typelist").getElementsByTagName("span");
			for(var i=0;i<li.length;i++){
				li[i].onclick=function(){
					if(this.className=="checked"){
						this.className=null;
						}
						else{
							this.className="checked";
							}
					};
				}
			}
			
			addLoadEvent(checklist);
</script>
</head>

<body onkeydown="javascript:butOnClick();">
<div class="container">
<div class="logo"><img src="<%=basePath %>images_new/axp-wenzi.png"></div>
<div class="text"><img src="<%=basePath %>images_new/text.png"></div>
<form name="loginForm" id="loginForm" action="<%=basePath %>login" method="post">
<input type="text" name="loginname" id="name" placeholder="账号：" class="account" value="admin">
<input type="password" name="password" id="pwd" placeholder="密码：" class="password" value="888888">
<div class="typelist" id="typelist">
<span><b></b><input type="checkbox" name="typelist" id="rember"/>&nbsp;<label for="rember">记住用户名</label></span>

</div>
<div class="login_box">
<button  onclick="dosubmit()">登录</button>

</div>
</form>
</div>
</body>
</html>