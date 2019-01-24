<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>每天积分-登陆</title>
<link href="${BASEPATH_IN_SESSION}css/login.css" rel="stylesheet" type="text/css" />
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
  var errortype = '${errortype}';
  if(errortype==-1){
      alert('登陆失败！登录名不存在！');
  }
  if(errortype==-2){
      alert('登陆失败！密码错误！');
  }
  if(errortype==-3){
      alert('登陆失败！ 未知错误！');
  }
  if(errortype==-4){
      alert('登录超时，请重新登录！');
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
<div class="logo"><img src="${BASEPATH_IN_SESSION}images_new/axp-wenzi.png"></div>
<div class="text"><img src="${BASEPATH_IN_SESSION}images_new/text.png"></div>
<form name="loginForm" id="loginForm" action="${BASEPATH_IN_SESSION}login" method="post">
<input type="text" name="loginname" id="name" placeholder="账号：" class="account" autocompleter="on">
<input type="password" name="password" id="pwd" placeholder="密码：" class="password">
<div class="typelist" id="typelist">
<span><b></b><input type="checkbox" name="typelist" id="rember"/>&nbsp;<label for="rember">记住用户名</label></span>

</div>
<div class="login_box">
<button>登录</button>

</div>
</form>
</div>
</body>
</html>