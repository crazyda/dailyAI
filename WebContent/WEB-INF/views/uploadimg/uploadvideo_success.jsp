<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.opensymphony.xwork2.util.*;"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack"); 

String filepath = vs.findString("filepath");
//out.println("filepath=========="+filepath);
String uploadtype = vs.findString("uploadtype");

System.out.println("uptype========"+uploadtype);
%>
<script>
   //parent.setImgurl("<%=filepath%>","<%=uploadtype%>");
  
</script>