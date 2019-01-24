<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="${BASEPATH_IN_SESSION}css/css3.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="main">
	
	
</div>
<script>
function fun(){
       var tr=document.getElementsByTagName("tr");
       for(var i=0;i<tr.length;i++)
       {
         tr[i].style.background=tr[i].rowIndex % 2==0?"#d5d5d5":"#fff";
		 tr[i].style.height=tr[i].rowIndex % 2==0?"30px":"28px";
		 tr[i].style.color="#666666";
       }
   }   
 window.onload=fun;
</script>
</body>
</html>