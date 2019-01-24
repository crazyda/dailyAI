<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.axp.model.Messages"%>
<%@page import="com.axp.model.Users"%>
<%@page import="com.axp.dao.UsersDAO"%>
<%@page import="com.axp.dao.impl.UsersDAOImpl"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Object o = request.getAttribute("sc");

Messages sc = null;
int user_id =-1;
if(o!=null){
   
   sc = (Messages)o;
   user_id = sc.getUsersByUserId().getId();
   
}
int current_user_id = (Integer)request.getSession().getAttribute("current_user_id");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="<%=basePath %>css/css.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/global.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/main.css" rel="stylesheet" type="text/css">
<link href="<%=basePath %>css/add/css/shop.css" rel="stylesheet" type="text/css">
<title>添加消息</title>
<style type="text/css">
<!--
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
-->
</style>



</head>

<body>
<div class="div_new_content">
<div style="margin-left:10px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="images/tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="46%" valign="middle"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                
                <td width="95%" class="STYLE1"><span class="STYLE3">你当前的位置</span>：[渠道管理]-[消息管理]-[添加消息 ]</td>
              </tr>
            </table></td>
           
          </tr>
        </table></td>
       
      </tr>
    </table></td>
  </tr>
  <tr>
     <td>
      <form action="<%=basePath %>message/save.action" id="saveForm" method="post">
          <div id="products" class="r_con_wrap">
			 <div class="r_con_form" >
				<div class="rows">
					<label>标题：</label>
					<span class="input">
					  <input type=text name="title" id="title" value="<%=sc==null?"":sc.getTitle() %>" />
					</span>
					<div class="clear"></div>
		       </div>
		       
		       <div class="rows">
					<label>接收者：</label>
					<span class="input">
					  <select name="user_id">
		                  <option value=-1>全部</option>
		                <%
		                UsersDAO udao = new UsersDAOImpl(); 
		                List<Users> ulst = udao.findByCon(Users.class, "isvalid=true and parent_id="+current_user_id);               
		                for(Users user:ulst){
		                	int u_id = user.getId();
		                	String name= user.getName();
		                %>
		                    <option value=<%=u_id %> <% if(user_id==u_id){ %>selected <% } %>><%=name %></option>
		                <% } %>
                     </select>
					</span>
					<div class="clear"></div>
		      </div>
              <div class="rows">
		 			<label>内容：</label>
					<span class="input">
					  <textarea name="content" id="content"><%=sc==null?"":sc.getContent() %></textarea>
					</span>
					<div class="clear"></div>
		       </div>
           
          <div class="rows">
				<label></label>
				<span class="input">
				  <input type=button class="button" onclick="savePtype();" value="添 加" />
				</span>
				<div class="clear"></div>
			</div>
			</div>
		</div>
        <input type=hidden id="id" name="id" value="<%=sc==null?"":sc.getId() %>" />
        </form>
     </td>
  </tr>
 
</table>
</div>
<div style="width:100%;height:20px;">
</div>
</div>
<script>
   function savePtype(){
   
      var title = document.getElementById("title").value;
      if(title==""){
         alert('请输入标题!');
         return;
      }
      
      var content = document.getElementById("content").value;
      if(content==""){
         alert('请输入消息内容!');
         return;
      }
      
      
      document.getElementById("saveForm").submit();
   }

</script>
</body>
</html>
