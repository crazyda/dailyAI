<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>奖金池列表</title>
<link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/css2.css" rel="stylesheet" type="text/css" />
<link href="${BASEPATH_IN_SESSION}css/paging.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>
</head>
<script>
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
		source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	   return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
	//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
	   source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	//alert(cs.length);
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
	else
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
}
</script>
<body>
	<form>
		<div class="main" style="height:auto;">
			<div class="header">
				<div class="header-left">
					<p>你当前的位置：[业务管理]-[奖金池奖金]</p>
				</div>
				<hr align="center" />
			</div>
			
			<div class="div_search" style="margin-left: 35px;">
			 <c:if test="${aus.level==75}">
			    	<p style="font-size: 24px">尊敬的${aus.levelname }:${aus.username },&nbsp;&nbsp;
			    		持有总积分:${aus.scoreMax },&nbsp;已分配积分:${aus.scoreSurplus-aus.score },&nbsp;可用积分:${scoreCount}</p>
			    	
			    </c:if>
			    <c:if test="${aus.level==65 }">
			    	<input type="hidden" name="user_num" value="1"/>
			    	奖金池总奖金:${maid},&nbsp;&nbsp;尊敬的${aus.levelname },从奖金池中您将获得分佣:${maidhhr }&nbsp;分佣占比${aus.lmUnion }%
			    </c:if>
			
			 </div>
			
		
			<!--end of header-->
			<div class="pageColumn">
				<table class="list" style="width:98%;margin:0 auto;">
					<thead>
						<th width="10%" style="height:50px;">交易积分</th>
						<th width="9%">交易用户</th>
						<th width="9%">交易类型</th>
					 	<th width="9%">代理佣金</th>
						<th width="9%">合伙人佣金</th>
						<th width="9%">创建时间</th>
						<th width="9%">归属代理</th>
					</thead>
					<tbody>
						<c:forEach items="${asList }" var="as">
							<tr>
								<td>${as.score }</td>
								 
									<c:choose>
								     <c:when test="${as.user==null }">
								         <td> ${as.adminUserBuy.username}店主</td> 
								         <td>购买积分</td>
								     </c:when>
								     <c:otherwise>
								         <td>  ${as.user.realname eq null ?as.user.name:as.user.realname}用户</td> 
								         <td>消费积分</td>
								     </c:otherwise>
								</c:choose>
								 <td>${as.maid }</td>
								 <td>${as.maid * 0.75}</td>
								
								
								<td><fmt:formatDate value="${as.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${as.adminUser.username}</td>
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
		<!--end of main-->
	</form>
<script>

</script>
</body>
</html>

