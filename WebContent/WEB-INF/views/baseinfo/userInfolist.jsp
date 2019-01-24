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
<title>粉丝管理</title>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath %>css/Capthchalist.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=basePath %>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath %>js/mian.js"></script>

</head>
<body>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[系统管理]-[粉丝管理]</p>
				</div>
				<hr align="center" />
			</div>
			<div class="div_search" style="margin-bottom: 2%;">
			   <form action="<%=basePath%>system/users/list.action" method="post">
				    <input type ="hidden" name="type" value="2"/>
				    <div style="float: left;">
				    <div class="name">
				    <span>粉丝名称 &nbsp;:&nbsp;</span>
				    <input type=text class="input" id="name" value="${search_name}" name="search_name" />
				     </div>
				     <div class="code" style="margin-left: 12px">
				    <span>邀请码 &nbsp;:&nbsp;</span>
				    <input type=text class="input" id="code" value="" name="code" />
				     </div>
				    <div class="mix">
					<span> 最小金额&nbsp;&nbsp;:&nbsp;&nbsp;</span>
		            <input class="mixMoney" id="mix" type=text name="mixM" value='${requestScope.mixM }'/>
					</div>
					<div class="max">
					<span> 最大金额&nbsp;&nbsp;:&nbsp;&nbsp;</span>
		            <input class="maxMoney" id="max" type=text name="maxM" value='${requestScope.maxM }'/>
					</div>
					<div class ="serech">  
					<input class="button"  type="submit"  value="搜索"/>
					</div>
					</div>
					
					<div style="float: right; height: 100%" >
					<div class="count" style="float: left;">	
					<span> 总记录&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="count">${count} 条</span>
					</div>
					<div class="money" style="float: left;">	
					<span> 总金额&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="money">${totalMoney} </span>
					</div>
					<div class="code" style="float: left;">	
					<span> 总积分&nbsp;:&nbsp;</span>		
					<span style="float:right; margin-right:30px;" id="code">${totalScore}</span>
					</div>
					</div>
			    </form>
			</div>
			
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="5%"  style="height:50px;">序号</th>
						<th width="5%">粉丝名称</th>
						<th width="5%">真实名字</th>
						<th width="5%">性别</th>
						<th width="10%">电话</th>
						<th width="10%">时间</th>
						<th width="8%">邀请码</th>
						<th width="6%">金额</th>
						<th width="8%">积分</th>
						<th width="10%">头像</th>
						<th width="8%">基本操作</th>
					</thead>
					<tbody>
						
						<c:forEach items="${requestScope.userslist }" var="users" >
						<tr>
							<c:set var="string1" value="${users.id }"/>
							<td>${users.id }</td>
							<td>
							  <c:choose>
							  	<c:when test="${users.name==users.id.toString() }">
							  		${users.openId }
							  	</c:when>
							  	<c:otherwise>
							  		${users.name }
							  	</c:otherwise>
							  </c:choose>
							</td>
							<td>${users.realname }</td>
							
							<td>
							<c:choose>
								<c:when test="${users.sex==1 }">男</c:when>
								<c:otherwise>女</c:otherwise>
							</c:choose>
							</td>
							
							<td>${users.phone }</td>
							<td>${users.createtime }</td>
							<td>${users.mycode }</td>
					<!-- da -->
							<td><span onclick="seemoney('${users.id}')">${users.money}</span></td>
							<td><span onclick="seescore('${users.id}')">${users.score }</span></td>
							<td>
							<c:choose>
							<c:when test="${empty users.headimage }">
								<img src="${users.imgUrl }" style="width:80px;height:80px;" />
							</c:when>
							<c:otherwise>
							  	<img src="${users.headimage }" style="width:80px;height:80px;" />
							 </c:otherwise>
							</c:choose>
							</td>
							<td>
								<span style="cursor: pointer;"
										onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else deleteusers('${users.id}');"><img
										src="<%=basePath %>constant/tab/images/del.gif" width="16"
										height="16" />删除</span>
										<span class="STYLE4">
	                              <u><span onclick="changeRecord('${users.id}');" style="cursor: pointer;">
	                                    资金明细
	                                </span></u>
	                                <script>
	                                    function changeRecord(userId){
	                                        location.href="<%=basePath%>fansWithdraws/cashFlow?userId="+userId;
	                                    }
	                                </script>
	                            </span>
							</td>
						</tr>
						</c:forEach>
									
					</tbody>
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
<script>

function deleteusers(ids){
	  window.location.href = "${BASEPATH_IN_SESSION}/system/users/del?ids="+ids;
}
function seemoney(ids){
	 window.location.href = "${BASEPATH_IN_SESSION}/fansWithdraws/cashFlow?userId="+ids;
}

function seescore(ids){
	 window.location.href = "${BASEPATH_IN_SESSION}/fansWithdraws/scoreFlow?userId="+ids;
	
	
	
	
}


</script>
</body>
</html>

