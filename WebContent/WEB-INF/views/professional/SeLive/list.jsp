<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>直播管理</title>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/mian.js"></script>

</head>
<body>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[业务管理]-[直播管理]</p>
				</div>
				<div class="header-right">
					<div class="header-right-cell-line"></div>
					<div class="header-right-cell" onclick="createAdver();" style="cursor: pointer;">
						<img src="<%=basePath %>img/new-2.png" />
						<p>新增</p>
					</div>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search">
			   <form action="<%=basePath %>professional/seLive/list.action" id="searchOrChangePage" method="post">
				    <input type ="hidden" name="type" value="2"/>
				             主播 名称: <input type=text class="input" value="${search_name}" name="search_name" />&nbsp;&nbsp;
				    <input type=submit class="button" value="搜索"/>
			    </form>
			</div>
			
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						
						
						<th width="5%"  style="height:50px;">序号</th>
						<th width="7%">主播名称</th>
						<th width="10%">直播名称</th>
						<th width="10%">直播地址</th>
						<th width="10%">开始时间</th>
						<th width="10%">结束时间</th>
						<th width="10%">是否置顶</th>
						<th width="8%">基本操作</th>
					</thead>
					<tbody>
						<c:forEach items="${requestScope.selivelist }" var="selive" >
						<tr>
						
							<input type="hidden" class="istop" value="${selive.istop}"/>
						<%-- 	<input type="radio" name="code_Value" value="${selive.id }" onclick="zhiding('${selive.id }',this)"/></td> --%>
							<td>${selive.id }</td>
							<td>${selive.sellerName }</td>
							<td>${selive.livename }</td>
							<td><a href="${selive.liveUri }">点击查看直播</a></td>
							<td>${selive.begintime }</td>
							<td>${selive.endtime }</td>
							<td>
								${selive.istop==true?'置顶':'未置顶' }
							</td>
							<td>
								<div>
								
									<span onclick="editlive('${selive.id }');" style="cursor: pointer;">
											<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
											height="16" />编辑
										</span><br/>
										<span onclick="zhiding('${selive.id}',this,1)" style="cursor: pointer;">
											<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
											height="16" />置顶
										</span><br/>
										<span onclick="zhiding('${selive.id}',this,0)" style="cursor: pointer;">
											<img src="<%=basePath %>constant/tab/images/edt.gif" width="16"
											height="16" />取消置顶
										</span><br/>
								</div>
								<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deletelive('${selive.id}');"><img
										src="<%=basePath %>constant/tab/images/del.gif" width="16"
										height="16" />删除</span>
							</td>
						</tr>
						</c:forEach>
									
					</tbody>
				</table>
			</div>
			<!--end of pageColunm-->
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
<script>
function editlive(id){
	  window.location.href = "${BASEPATH_IN_SESSION}/professional/seLive/add?id="+id;
}

function deletelive(ids){
	  window.location.href = "${BASEPATH_IN_SESSION}/professional/seLive/del?ids="+ids;
}

function createAdver(){
	window.location.href = "${BASEPATH_IN_SESSION}professional/seLive/add";
}


function zhiding(id,dom,v){
	
    $.ajax({
        url: "<%=basePath%>/professional/seLive/toTop",
        type: "post",
        data: {"sid": id,"istop":v},
        success: function (istop) {
        	var json = eval("("+istop+")");
            if (json.msg) {
            	alert("操作成功");
            	window.location.href = "list";
            } else {
                if(json.message=='fail!'){
                	alert("置顶失败！");
                }
                if(json.message=='access denied!'){
                	alert("权限限制！");
                }
            }
        }
    });
	
}
</script>
</body>
</html>

