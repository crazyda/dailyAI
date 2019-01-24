<%@page import="com.axp.dao.impl.AdminUserDAOImpl" %>
<%@page import="com.axp.util.StringUtil" %>
<%@page import="com.axp.model.AdminUser" %>
<%@page import="com.axp.dao.AdminUserDAO" %>
<%@page import="com.axp.model.CashshopType" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    Integer current_user_id = (Integer) request.getSession().getAttribute("currentUserId");
/* AdminUserDAO adao = new AdminUserDAOImpl();
AdminUser user = null;
user = adao.findById(current_user_id); */
    Object o = request.getAttribute("cashshopType");
    CashshopType sc = null;
    String imageurls = null;
    String imageurls1 = null;
    if (o != null) {
        sc = (CashshopType) o;
        imageurls = sc.getImg();
        imageurls1 = sc.getImg2();
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/baguetteBox.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${BASEPATH_IN_SESSION}ueditor/themes/default/ueditor.css">
    <title>店铺审核</title>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.config.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}ueditor/ueditor.all.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/goods/goodsStandard.js"></script>
     <script type="text/javascript" src="<%=basePath%>js/baguetteBox.js"></script>
    <style type="text/css">
	    tr#tableProperty {
	    		text-align: left;
		}
		td{
	    		padding-right: 50px;
		}
		
    </style>
</head>

<body>
<div class="div_new_content">
    <div style="margin-left:10px;">

        <div class="header-left">
            <p>你当前的位置：[商城管理]-[信息详情]-[店铺审核]</p>
        </div>

        <form class="data_form"  method="post" action="<%=basePath%>cashShop/store/">
        	<input type="hidden" id="adminuserId" name="adminuserId" value=""/>  
            <div class="r_con_wrap">
                <div class="r_con_form">
                	<input type="hidden" name="id" id="id" value="${seller.id}"/>
                    <div class="rows">
                        <label>店铺名称：</label>
                        <span class="input">
                            <label>${seller.name}</label>
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>营业执照：</label>
                        <span class="input">
                           <img src="${RESOURCE_LOCAL_URL}${seller.businessLicencePic}" style="width: 150px;height: 150px;;">
                        </span>
                        <div class="clear"></div>
                    </div>
                   
                    <div class="rows">
                        <label>联系方式：</label>
                        <span class="input">
                            <label>${seller.phone}</label>
                        </span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>商家身份证：</label>
						<span class="input">
                            <label>${seller.sellerIdCard}</label>
			 			</span>
                        <div class="clear"></div>
                    </div>
                    <div class="rows">
                        <label>店铺地址：</label>
						<span class="input">
                            <label>${seller.address}</label>
			 			</span>
                        <div class="clear"></div>
                    </div>

                    <div class="rows">
                        <label>提交时间：</label>
						<span class="input">
                            <label>${seller.createtime}</label>
			 			</span>
                        <div class="clear"></div>
                    </div>
                      <div class="rows">
						<label><font color="red">*</font>管理角色:</label>
						<c:choose>
							<c:when test="${adminuser.level>=85 }">
								<span class="input">
									<select  name="agency" id="agencyId" onchange="changeAdminByDistribute()">
										<option value=0>请选择代理商</option>
										<c:forEach items="${agencylist }" var="agency">
										
										<option value="${agency.id }" <c:if test="${agency.id == adminUser.id }">selected="selected"</c:if>>${agency.loginname}</option>
										</c:forEach>
									</select>
									<div id="c" >
									<select  name="role" id="roleId" onchange="changeAdminByDistribute();gotoselect()">
										<option value=0>请选择</option>
									</select> 
									</div>
									<div id="p" >
									<select  name="zone" id="zoneId" onchange="changeAdminByDistribute();gotoselect()">
										<option value=0>请选择</option>
									</select> 
									</div>
								</span>
							</c:when>
							<c:when test="${adminuser.level==75 }">
								<span class="input">
								<select id="roleId" name="role">
									<option value="-1">--请选择--</option>
									<c:forEach items="${level }" var="lev">
										<option value="${lev.id }"  >${lev.name }</option>
									</c:forEach>
								</select>
								 <div id="p" >
									<select  name="zone" id="zoneId">
										<option value=0>--请选择管理地区--</option>
										<c:forEach items="${list }" var="p">
											<option value="${p.id }"  >${p.name }</option>
										</c:forEach>
									</select>
								</div> 
								</span>
							</c:when>
							<c:when test="${adminuser.level==65}">
								<span class="input">
								<select id="roleId" name="role">
									<option value="-1">--请选择--</option>
									<c:forEach items="${level }" var="lev">
										<option value="${lev.id }"  >${lev.name }</option>
									</c:forEach>
								</select>
								</span>
							</c:when>
						</c:choose>
					
						<div class="clear"></div>
					</div>
                    <div class="rows">
                        <label>审核状态：</label>
						<span class="input">
					        <dd>
					            <input type="radio" name="verifyStatus" id="tg"   value="2"/>审核通过
					            <input type="radio" name="verifyStatus" id="ntg"   value="-2"/>审核不通过
					        </dd>
						</span>
                        <div class="clear"></div>
                      </div>
                      <div class="rows">
                      	<label></label>
                      	<span class="input">
                        	<p><input type="button" value="提交" onclick="submitForm()" style="color:#fff; font-size:1.0rem; line-height:2.5rem; width:6.0rem; background:#0e93d8; border-radius:3px; cursor:pointer; text-align:center;"></p>
                        </span>
                        <div class="clear"></div>
                         <script>
				        //提交表单；
					        function submitForm(data) {
					           var adminuserId = $("input#adminuserId");
					           var zoneId = $("input#zoneId");
					     	   var agency =$("select#agencyId").find("option:selected").text();
					     	   var role =$("select#roleId").find("option:selected").text();
					     	   var zone = $("select#zoneId").find("option:selected").text();
					     	   if(zone && zone !='请选择'){
					     		   $("input#uri").val(zone);
					     		  adminuserId.val($("select#zoneId").val());
					     	   }
					     	   else if(role && role!='请选择'){
					     		   $("input#uri").val(role);
					     		  adminuserId.val($("select#roleId").val());
					     	   }
					     	   else if(agency && agency!='请选择'){
					     		   $("input#uri").val(agency);
					     		  adminuserId.val($("select#agencyId").val());
					     	   }
					     	   var data={adminuserId:adminuserId,id:$("#id").val()};
								if(($("#roleId").val()!="" && $("#zoneId").val()!="") || ($("#roleId").val()!="")){
									data.adminuserId=$("#roleId").val();
									if($("#zoneId").val()!=undefined){
										data.zoneId=$("#zoneId").val();
									}
									
									data.verifyStatus = $("input[name='verifyStatus']:checked").val();
								}
								
								if(!document.getElementById("tg").checked && !document.getElementById("ntg").checked){
									alert("请选择审核的结果");
									return ;
								}
								
								
								
					            $.post('submitSellerReview2',data , function (data) {
					                alert(data.message);
					                location.href = "<%=basePath%>cashShop/store/listOfStoreVerify";
					            });
					        }
				    	</script>
				    	</div>
                    </div>
                </div>
            </div>

        </form>

    </div>
    <div style="width:100%;height:20px;">
    </div>
</div>
<script type="text/javascript">
 function changeAdminByDistribute(){
	  var ad = event.srcElement;
	  var agency = document.getElementById("agencyId");
	  var role = document.getElementById("roleId");
	  var zone = document.getElementById("zoneId");
			if(parseInt(ad.value)>0){
				if(ad==agency){
					role.options.length=0;	
					zone.options.length=0;
				}
				var data={adminuserId:agency.value};
				if(role.value!=""){
					//data.adminuserId=role.value;
				}else if(zone.value!=""){
					data.zoneId=zone.value;
				}
				$.ajax({
					type:"post",
					url:"${BASEPATH_IN_SESSION}cashShop/store/changeAdminByDistribute.action",
					data: data,        
			        dataType: "text",
			        success: function (data){
			        var tempdate =eval("("+data+")"); 
		    	    	if(ad==agency){
		    	    		role.options.add(new Option("请选择管理角色", ""));
		    	    		zone.options.add(new Option("请选择管理地区",""));
		    	    	 }
		    	    	if(ad==agency){
		    	    		$.each(tempdate.role, function(key, value) {  
			    	    		 role.options.add(new Option(value,key));
	                        });  
		    	    		$.each(tempdate.zone, function(key, value) {  
				    	    	 zone.options.add(new Option(value,key));
		                    });
			    	    	
		    	    	}
			        }
				})
			}
 }

</script>
</body>

</html>
