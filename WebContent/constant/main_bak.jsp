<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>聚宝屏-后台</title>
<link href="css/css2.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="css/jquery-latest.js"></script>
<script type="text/javascript">
    $(function(){
        var i=0;
        $("ol").hide();
        $("span").text("+");
        $("#left div div").click(function(){
            i++;
            if(i%2==0){   
                $(this).find('span').text("+");
                $(this).nextAll().addClass(".cc").slideUp();
            }else{
                $(this).find('span').text("-");
                $(this).nextAll().slideDown();
            }
        });
    })
</script>  

<script language="JavaScript"> 
function chooseCheckbox(obj){ 
    if(obj.checked == true){//选中复选框 
		obj.parentNode.parentNode.style.backgroundColor='#f6f6f6'; 
    }else{//未选中或取消选中 
        obj.parentNode.parentNode.style.backgroundColor='#FFFFFF'; 
    } 
    return false; 
} 

</script>
</head>
<body >
<div class="main">
<div class="beijing-bai"></div>
<div class="jb_head">
	
	<div class="but">
		<div class="but-1">
		<div class="but-1-cell" style="text-align:left;margin-left:10%"><img src="img/new-5.png"/>退出</div><div class="but-1-cell" style="text-align:right"><p><img src="img/new-4.png"/>亲!你好</p></div>
		</div>
	</div>
</div>
<div class="jb_content">
	<div id="left">
<div class="list-1"></div>
		 <div id="div1">		
			<div class="list-1-1-bg"><p>系统管理<span></span></p></div>
					<ol><a href="">test1</a></ol>
					<ol><a href="">test1</a></ol>
					<ol><a href="">test1</a></ol>
			</div>
			<div id="div2">
				 <div class="list-1-1-bg"><p>渠道管理<span></span></p></div>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
					<ol><a href="">test2</a></ol>
			</div>
			<div id="div3">
				<div class="list-1-1-bg"><p>用户管理<span></span></p></div>
					<ol><a href="">test3</a></ol>
					<ol><a href="">test3</a></ol>
					<ol><a href="">test3</a></ol>
			</div>
			<div id="div4">
				 <div class="list-1-1-bg"><p>六维人脉<span></span></p></div>
					<ol><a href="">test4</a></ol>
					<ol><a href="">test4</a></ol>
					<ol><a href="">test4</a></ol>
			</div>	
	</div>
	
	<iframe id="iframe_content1" src="show_data.jsp" name="iframe" frameborder="0" scrolling="auto" ></iframe>
<!--	<iframe id="iframe_content" src="http://www.baidu.com" name="iframe" frameborder="0" scrolling="no" ></iframe>
-->
	
	
</div><!-- end of jb_content-->
<div class="jb_footer">
	<div class="jb_footer_logo">	
		
		<div class="foot_cell">
		<img src="img/6.png"/>
		<div class="foot_cell_text">退出系统</div>
		</div>
		
		<div class="foot_cell">
		<img src="img/9.png"/>
		<div class="foot_cell_text">广告审核</div>
		</div>
		<div class="foot_cell">
		<img src="img/8.png"/>
		<div class="foot_cell_text">会员信息</div>
		</div>
		<div class="foot_cell">
		<img src="img/7.png"/>
		<div class="foot_cell_text">代理商信息</div>
		</div>
<div class="foot_cell">
		<img src="img/1.png" style="margin:0" />
		<div class="foot_cell_text">我的首页</div>
		</div>
	</div>
</div>
</div><!-- end of main-->

 <div id="light" class="white_content">
 <div class="content-1">管理员
 <img src="img/10.png" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'"/>
 </div> 
 <div class="content-2">
 <p>姓名:13688888888</p>
 <p>电话:13688888888</p>
  <p>等级:普通会员</p>
 <p>地址:湖南省长沙市·····</p>
  <p>直接下级:0.0</p>
	<table>
		<tr>
		<td>  <p>数量:22</p></td>
		<td>  <p>积分:220</p></td>
		<td>  <p>邀请:53111</p></td>
		</tr>
	</table>
  <p>剩余金额:220</p>
	<div class="content-table">
		<table border="1"cellpadding="10px" cellspacing="10px">
			<tr><td><img src="img/t-5.png">推广成绩</td> <td><img src="img/t-3.png">增加积分</td> <td><img src="img/t-1.png">修改等级</td></tr>
			<tr><td><img src="img/t-6.png">积分商城</td> <td><img src="img/t-4.png">重置密码</td> <td><img src="img/t-2.png">编辑</td></tr>
		</table>
	</div>
 </div>
 </div>
 <div id="fade" class="black_overlay"></div> 










</body>
</html>