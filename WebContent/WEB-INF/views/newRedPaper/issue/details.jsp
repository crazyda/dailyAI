<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红包列表</title>
<script type="text/javascript" src="${basePath }js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${basePath }js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${basePath }js/WdatePicker.js"></script>
<script type="text/javascript" src="${basePath }js/checktext.js"></script>
<link href="${basePath }css/css.css" rel="stylesheet" type="text/css">
<link href="${basePath }css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="${basePath }css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="${basePath }css/add/css/shop.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
	}
	.STYLE1 {font-size: 12px}
	.STYLE3 {font-size: 12px; font-weight: bold; }
	.STYLE4 {
		color: #03515d;
		font-size: 12px;
	}
	.a-upload {
	    padding: 4px 10px;
	    margin-right:10px;
	    height: 20px;
	    line-height: 20px;
	    position: relative;
	    cursor: pointer;
	    color: #888;
	    background: #fafafa;
	    border: 1px solid #ddd;
	    border-radius: 4px;
	    overflow: hidden;
	    display: inline-block;
	    *display: inline;
	    *zoom: 1
	}
	
	.a-upload  input {
	    position: absolute;
	    font-size: 100px;
	    right: 0;
	    top: 0;
	    opacity: 0;
	    filter: alpha(opacity=0);
	    cursor: pointer
	}
	
	.a-upload:hover {
	    color: #444;
	    background: #eee;
	    border-color: #ccc;
	    text-decoration: none
	}
	
	.err{
		color:red;
	}
	
	.input li{
		display: inline;
		border: 1px solid lightgray;
		 border-left-width: 0px;
	}
	
	.input li:last-child{
		background-color: lightgray;
		padding-left: 5px;
		padding-right: 5px;
	}
</style>
<script type="text/javascript">
</script>
<body>
<div class="div_new_content">
<div style="margin-left:10px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="images/tab_05.gif">
   	<table width="100%" border="0" cellspacing="0" cellpadding="0">
   		<tr>
     		<td>
     		<table width="100%" border="0" cellspacing="0" cellpadding="0">
   				<tr>
     				<td width="46%" valign="middle">
   					<table width="100%" border="0" cellspacing="0" cellpadding="0">
     					<tr>
            				<td width="95%" class="STYLE1">
              				<div class="header-left">
              				<c:choose>
	                			<c:when test="${settingId==0 }">
									<p>你当前的位置：[红包管理]-[派发红包]-[新建红包]</p>
	                			</c:when>
			                  	<c:otherwise>
			                  		<p>你当前的位置：[红包管理]-[派发红包]-[编辑红包]</p>
			                  	</c:otherwise>
                			</c:choose>
							</div>
            				</td>
          				</tr>
        			</table>
        			</td>
      			</tr>
     		</table>
     		</td>
   		</tr>
   	</table>
    </td>
  </tr>
  <tr>
  	 <td>
		 <div id="settings" class="r_con_wrap">
		 	<div class="r_con_form" >
		 		<div class="rows">
					<label> &nbsp;派发范围：<input type="hidden" name="redPaperSetting.rangeid" id="rangid"></label>
					<span class="input">
					${range.loginname }
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label> &nbsp;红包名称：</label>
					<span class="input">
					  ${redPaperSetting.name }
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label> &nbsp;红包总金额：</label>
					<span class="input">
						<fmt:formatNumber type="number" maxFractionDigits="2" value=" ${redPaperSetting.allMoney }" />元
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>&nbsp;发放总量：</label>
					<span class="input">
					  ${redPaperSetting.allNum }张
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>&nbsp;每天发放最多：</label>
					<span class="input">
					  ${redPaperSetting.maxPutout }张
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>&nbsp;面值：</label>
					<span class="input">
						<c:if test="${redPaperSetting.minParvalue!=redPaperSetting.maxParvalue }">
							${redPaperSetting.minParvalue}~${redPaperSetting.maxParvalue}
						</c:if>
						<c:if test="${redPaperSetting.minParvalue==redPaperSetting.maxParvalue }">
							${redPaperSetting.minParvalue}（定额）
						</c:if>
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>&nbsp;每人限领：</label>
					<span class="input">
					 ${redPaperSetting.limits}（每人领取间隔：${redPaperSetting.cyc}小时）
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>&nbsp;开始时间：</label>
					<span class="input">
						<fmt:formatDate  value="${redPaperSetting.beginTime }" pattern="yyyy年MM月dd日" />
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<label>红包描述：</label>
					<span class="input">
						${redPaperSetting.description }
					</span>
					<div class="clear"></div>
		         </div>
		         <div class="rows">
					<label>红包有效期：</label>
					<span class="input">
					  3个自然月内有效
					</span>
					<div class="clear"></div>
		         </div>
		         <div class="rows">
					<label>红包头像：<input type="hidden" value ="0" name="redPaperSetting.headimg" id="headImg"/></label>
					<span class="input">
						<img id="loading" alt="" src="${RESOURCE_LOCAL_URL}${redPaperSetting.headimg }">
					</span>
					<div class="clear"></div>
		         </div>
		         <div class="rows">
					<label>&nbsp;红包链接：</label>
					<span class="input">
					  ${redPaperSetting.link }
					</span>
					<div class="clear"></div>
		        </div>
		        <div class="rows">
					<span class="input">
						<input type="button" value="返回" class="button" onclick="javascript:history.go(-1);">
					</span>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</td>
  </tr>
</table>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
</body>
</html>