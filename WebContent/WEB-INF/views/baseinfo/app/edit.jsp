<%@page import="java.sql.Timestamp"%>
<%@page import="com.axp.model.ImageType"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.axp.model.Zones"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";



%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>版本更新管理</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
 <style>
        *{border:0;margin:0;padding:0;list-style:none;}
        body{font-family: "Helvetica Neue", "Helvetica", "Arial", "sans-serif";}
        .Android{width:80%;font-size:1.5rem;font-weight:bold;margin:1% auto 0;}
        .ios{width:80%;font-size:1.5rem;font-weight:bold;margin:1% auto 0;}
        .same{width:80%;font-size:1.5rem;font-weight:bold;margin:1% auto 0;}
        .name{width: 80%;min-width:400px;height:auto;overflow: hidden;margin:0 auto;}
        .name .r1{float:left;width:20%;height:2rem;line-height:2rem;text-indent:0.2rem;}
        .name .r2{float:left;width:20%;height:2rem;line-height:2rem;text-indent:0.2rem;}
        .name .r1 input{width:1rem;height:1rem;}
        .name .r2 input{width:1rem;height:1rem;}
        

        .name-in{width:30%;float:left;box-sizing:border-box;}
        input{width: 70%;min-height:2rem;float: right;box-sizing:border-box;border:1px solid #ccc;margin-bottom:0.2rem;}
        .inputs{width: 70%;min-height:6rem;float: right;box-sizing:border-box;margin-top:0.1rem;background:#f2f2f2;}
        .add{width:80%;height:auto;overflow:hidden;margin:0 auto;}
        .add-in-left{width:30%;float:left;box-sizing:border-box;}
        .add-in-right{width:70%;min-height:2rem;float:right;}
        .tianjia{width:8%;min-height:1.5rem;background:#0e95d9;color:#fff;margin:1% auto 0;text-align: center;}

        #button{width:20%;margin:2% auto 0;}
        #button button{width:100%;height:3rem;background:#0e95d9;color:#fff;font-size:22px;border-radius:10px;}
    </style>
</head>
<body style="background:none">
<form id="AppInformation_id" action="" method="post">
<input type="hidden" name="id" id="id" value="${app.id}">
    <div class="Android">Android</div>
    <section class="names">
        <section class="name">
            <div class="name-in">
                评分<font style="color:red;">*</font>
            </div>
            <input type="text" name="AScore" id="AScore" value="${app.AScore }">
        </section>

        <section class="name">
            <div class="name-in">
                下载次数<font style="color:red;">*</font>
            </div>
            <input type="text" name="ADownloads" id="ADownloads" value="${app.ADownloads}">
        </section>

        <section class="name">
            <div class="name-in">
                大小<font style="color:red;">*</font>
            </div>
            <input type="text" name="ASize" id="ASize" value="${app.ASize}">
        </section>

        <section class="name">
            <div class="name-in">
                APK下载命名<font style="color:red;">*</font>
            </div>
            <input type="text" name="ADirectDownload" id="ADirectDownload" value="${app.ADirectDownload}">
        </section>

        <section class="name">
            <div class="name-in">
                APK下载地址<font style="color:red;">*</font>
            </div>
            <input type="text" name="ADirectUrl" id="ADirectUrl" value="${app.ADirectUrl}">
        </section>

        <section class="name">
            <div class="name-in">
                应用市场<font style="color:red;">*</font>
            </div>
            <input type="text" name="AMarketDownload" id="AMarketDownload" value="${ app.AMarketDownload}">
        </section>

        <section class="name">
            <div class="name-in">
                应用市场地址<font style="color:red;">*</font>
            </div>
            <input type="text" name="AMarketUrl" id="AMarketUrl" value="${app.AMarketUrl}">
        </section>

        <section class="name">
            <div class="name-in">
                Android 版本<font style="color:red;">*</font>
            </div>
            <input type="text" name="AVersion" id="AVersion" value="${app.AVersion}">
        </section>
    </section>

    <div class="ios">IOS 详情</div>
    <section class="names">
        <section class="name">
            <div class="name-in">
                评分<font style="color:red;">*</font>
            </div>
            <input type="text" name="IScore" id="IScore" value="${app.IScore}">
        </section>

        <section class="name">
            <div class="name-in">
                下载次数<font style="color:red;">*</font>
            </div>
            <input type="text" name="IDownloads" id="IDownloads" value="${ app.IDownloads}">
        </section>

        <section class="name">
            <div class="name-in">
                大小<font style="color:red;">*</font>
            </div>
            <input type="text" name="ISize" id="ISize" value="${app.ISize}">
        </section>

        <section class="name">
            <div class="name-in">
                应用市场<font style="color:red;">*</font>
            </div>
            <input type="text" name="IMarketDownload" id="IMarketDownload" value="${app.IMarketDownload}">
        </section>

        <section class="name">
            <div class="name-in">
                应用市场地址<font style="color:red;">*</font>
            </div>
            <input type="text" name="IMarketUrl" id="IMarketUrl" value="${app.IMarketUrl}">
        </section>
    </section>



    <div class="same">公共详情</div>
    <section class="names">
        <section class="name">
            <div class="name-in">
                版本号<font style="color:red;">*</font>
            </div>
            <input type="text" name="appVersion" id="appVersion" value="${app.appVersion}">
        </section>

        <section class="name">
            <div class="name-in">
                App版本<font style="color:red;">*</font>
            </div>
            <input type="hidden" id = "type_app" value="${app.appType }" />
            <div class="r1"><input type="radio" name="appType" value="1" style="float: left;" checked="checked" >用户版</div>
            <div class="r2"><input type="radio" name="appType" value="2" style="float: left;">商家版</div>

        </section>

        <section class="name">
            <div class="name-in">
                App描述<font style="color:red;">*</font>
            </div>
            <textarea type="text" class="inputs" name="describe" id="describe"/>${app.describe}</textarea>
        </section>
		<section class="add">
            <div class="add-in-left">
                版本更新信息
            </div>
            <textarea type="text" class="add-in-right" name="INewVersionContents" id="INewVersionContents"/>${app.INewVersionContents}</textarea>
        </section>
      <!--   <div class="tianjia">添加</div> -->

    </section>
    
    <div id="button">
        <input type="button" class="button" value="提交" onclick="submitForm();"/>
    </div>
</form>
<script>
// 添加更新信息
$('.tianjia').click(function(){
    $('#cont').append('<input type="text" class="add-in-right"  name="content[]" value=""/>');
});
$(function(){
    var apptype = $("#type_app").val();
    
    if(apptype == 1){
        $("input[type=radio][name=appType][value=1]").attr("checked",true);
    }
    if(apptype == 2){
        $("input[type=radio][name=appType][value=2]").attr("checked",true);
    }
});

	function submitForm(){
		var AScore = $("#AScore");
		if(AScore == null){
			alert("评分不能为空");
		}
		
		var ADownloads = $("#ADownloads");
		if(ADownloads == null){
			alert("下载次数不能为空");
		}
		var ASize = $("#ASize");
		if(ASize == null){
			alert("APP大小不能为空");
		}
		var ADirectDownload = $("#ADirectDownload");
		if(ADirectDownload == null){
			alert("应用市场不能为空");
		}
		var ADirectUrl = $("#ADirectUrl");
		if(ADirectUrl == null){
			alert("应用市场地址不能为空");
		}
		var AVersion = $("#AVersion");
		if(AVersion == null){
			alert("安卓版本不能为空");
		}
		
		var IMarketDownload = $("#IMarketDownload");
		if(IMarketDownload == null){
			alert("应用市场不能为空");
		}
		var IMarketUrl = $("#IMarketUrl");
		if(IMarketUrl == null){
			alert("应用市场地址不能为空");
		}
		var appVersion = $("#appVersion");
		if(appVersion == null){
			alert("版本号不能为空");
		}
		var describe = $("#describe");
		if(describe == null){
			alert("APP描述不能为空");
		}
		var IScore = $("#IScore");
		if(IScore == null){
			alert("IOS 评分不能为空");
		}
		var IDownloads = $("#IDownloads");
		if(IDownloads == null){
			alert("下载次数不能为空");
		}
		$.ajax({
            url: "<%=basePath %>system/appVersion/save",
            async:false,
            type: "post",
            data: $("#AppInformation_id").serialize(),
            success: function (data) {
              
                location.href="<%=basePath%>system/appVersion/list";
            }
        });
		
		
		
		
		
	}


</script>
</body>
</html>

