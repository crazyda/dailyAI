<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>游戏列表</title>
<link href="<%=basePath%>css/css.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/css2.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath %>css/paging.css" rel="stylesheet" type="text/css">
<link type="text/css" href="<%=basePath %>css/orders/child-page.css" rel="stylesheet">
<link type="text/css" href="<%=basePath %>css/orders/merchant-order.css" rel="stylesheet">
<script src="<%=basePath %>js/order/jquery-1.7.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
<style type="text/css">
.orderform p{  width:33%; height:2.0rem; font-size:1.0rem; line-height:2.0rem; padding:0.5rem 0;}
.orderform p input{ width:20%;  border-radius:2px; font-size:1.0rem; line-height:1.5rem; padding-left:0.5rem;}
</style >
</head>
<body>

<div class="merchant">
<div class="header">
	<div class="header-left">
		<p>你当前的位置：[商城管理]-[游戏列表]</p>
	</div>
	<hr/>
</div>
<br>
	<div class="tab">
    	<h4>
            <span>登录签到</span>
            <c:if test="${adminUser.level >= 95  }">
            
            <span>幸运抽奖</span>
            </c:if>
            
        </h4>
         <form  action=""  class="orderform" method="post">
			<input type="hidden" id="status" name="status" value="">
       
            <p class="btn">
            	<c:if test="${status eq 0 }">
            		<input type="button" value="新增签到奖品" onclick="sign();">
            	</c:if>
            	<c:if test="${status eq 1 }">
            		<input type="button" value="新增抽奖奖品" onclick="draw();">
            	</c:if>
            </p>
        </form> 
        <script type="text/javascript">
        
        	function sign(){
        		location.href="<%=basePath%>game/addSign?id=0";
        	}
        	function draw(){
        		location.href="<%=basePath%>game/addDraw";
        	}
        
        
        </script>
        
        
        
        
        
        
        <ul>
            <li class="current">
            	<div class="table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th>编号</th>
                            	<th>代理</th>
                            	<th>类型</th>
                            	<th>奖品</th>
                            	<th>预览</th>
                            	<th>提供积分商家</th>
                            	<th>创建时间</th>
                            	<th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
            				<c:forEach items="${gameLists }" var="gameList">
            				
	                            <tr class="">
	                            	<td width="5%" >
	                                	${gameList.id }
	                                </td>
	                                <td width="5%" >
	                                	${gameList.adminUser.username }
	                                </td>							
	                                <td width="8%">${gameList.commodityType.name }</td>
	                                <td width="15%">${gameList.oneScore}</td>
	                                <td width="10%">广告预览 </td>
	                                <td width="10%">${gameList.seller.username } </td>
	                                <td width="10%"><fmt:formatDate value="${gameList.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                
	                              
	                                <td width="12%">
	                                <span style="cursor: pointer;" onclick="add('${gameList.id }');">
												<img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />修改
											</span>
	                                	<span style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else del('${gameList.id }');">
												<img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />删除
											</span>
	                                </td> 
	                            </tr>
            				 
            				
            				</c:forEach>
                                         
                        </tbody>
                    </table>
                   <script>
                   
                   function del(deleteId){
                	 
						$.get('<%=basePath%>game/delSign',{id:deleteId},function(data){
							alert(data.message);
                           location.reload();
						});
					}
                  
                   
			      	function add(id){
			      		location.href="<%=basePath%>game/addSign?id="+id;
			      	}
			      	
	      
	      
	    
                   
                   </script>
                    <div class="page_num">
                       
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
					<!--end of main-->
                    </div>
                </div>
            </li>
            
            
            <li>
            	<div class="table">
                	<table>
                    	<thead>
                        	<tr>
                            	<th>序号</th>
                            	<th>奖品</th>
                            	<th>概率</th>
                            	<th>图片预览</th>
                            	<th>创建时间</th>
                            	<th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${gameLists }" var="gameList">
            				
	                            <tr class="">
	                            	<td width="5%" >
	                                	${gameList.id }
	                                </td>
	                                
	                                <c:if test="${gameList.drawYlassify.id eq 18 }">
								<td>${gameList.score}${gameList.drawYlassify.name }</td>
								</c:if>
								<c:if test="${gameList.drawYlassify.id eq 19 }"> 
								<td>${gameList.reGoodsOfSellerMall.snapshotGoods.name }</td>
								</c:if>
								<c:if test="${gameList.drawYlassify.id eq 20 }"> 
								<td>${gameList.drawYlassify.name }</td>
								</c:if>
	                                <td width="15%">${gameList.chanceScore}%</td>
	                                <td width="15%">
	                                	<img src="${RESOURCE_LOCAL_URL }${gameList.content}"style="width:80px;height:80px;"/>
	                                
	                                </td>
	                                
	                                <td width="10%"><fmt:formatDate value="${gameList.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                                <td width="12%">
	                                	<span style="cursor: pointer;" onclick="if(!confirm('您确认要删除此数据吗?删除后数据不能恢复!'))return false; else del('${gameList.id }');">
												<img src="<%=basePath%>constant/tab/images/del.gif" width="16" height="16" />删除
											</span>
	                                </td> 
	                            </tr>
            				
            				
            				</c:forEach>                
                        </tbody>
                        <script>
							function del(deleteId){
								
								$.post('<%=basePath%>game/_delActivity',{id:deleteId},function(data){
									window.location.reload();
									
								});
							}
							
							
						</script>
                    </table>
                    <div class="page_num">
                 
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
					<!--end of main-->
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

</body>
</html>
<script type="text/javascript">
/*<!-------------tab栏转换效果---------------->*/
  (function xieRan(){
	$('.tab h4 span:eq(${status})').addClass('current').siblings().removeClass('current');
	$('.tab ul li:eq(${status})').addClass('current').siblings().removeClass('current');
})(); 
 $('.tab h4 span').click(function(e) {
	var status = $(this).index();
	parent.document.getElementById('iframe_content').src='${BASEPATH_IN_SESSION}game/gameList?status='+status; 
	/* $(this).addClass('current').siblings().removeClass('current');
	$('.tab ul li:eq('+$(this).index()+')').addClass('current').siblings().removeClass('current'); */
	$("#status").val($(this).index());
}); 	 


/* $('.btn input').click(function(e) {
    $(this).addClass('click').siblings().removeClass('click');
});

$("input[name='quanxuan']").click(function(e){
	$(".tab ul li:eq("+$(this).index()+") :checkbox[name!='quanxuan']").attr("checked",this.checked);
}); */
 
</script>


















