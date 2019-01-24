<%@page import="org.apache.commons.lang3.StringUtils" %>
<%@page import="com.axp.util.StringUtil" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="wh" uri="wehua-taglib" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	%>
		
<head>
	
    <link href="${BASEPATH_IN_SESSION}css/css.css" rel="stylesheet" type="text/css">
    <link href="${BASEPATH_IN_SESSION}css/manage-stock.css" rel="stylesheet" type="text/css">
    <link href="${BASEPATH_IN_SESSION}css/add/css/global.css" rel="stylesheet" type="text/css">
    <link href="${BASEPATH_IN_SESSION}css/add/css/main.css" rel="stylesheet" type="text/css">	
    <link href="${BASEPATH_IN_SESSION}css/add/css/shop.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-validation/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery-validation/messages_cn.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/layer-v2.0/layer/layer.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/WdatePicker.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/goods/goodsStandard.js"></script>
    <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/goods/put.js"></script>

	
    <title>商品管理-投放</title>
    <style type="text/css">
    </style>
</head>

<body>
<input type="hidden" class="mypath" value="<%=basePath%>"/>
<div class="div_new_content">
    <div style="margin-left:10px;">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <tr>

                <td>
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="46%" valign="middle">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr>

                                        <td width="95%" class="STYLE1">
                                            <div class="header-left">
                                                <p>你当前的位置：[商城管理]-[商品管理]-[投放]</p>
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
                <div class="stock" id="manage">
                    <div class="setion_all">
                        <div class="left">投放到商城</div>
                        <div class="right">
                          
                            <div class="chek">
                                <ul>
                                	<c:if test="${zbdp=='show' }">
                                    <li><input type="checkbox" name="chk" class="chk" id="sem"> <span>周边店铺</span></li>
                                    </c:if>
                                   
                                    
                                <%--      <c:if test="${jfdh=='show' }">
                                    <li><input type="checkbox" name="chk" class="chk" id="scm"> <span>积分兑换</span></li>
                                    </c:if>
                                    <c:if test="${xsms=='show' }">
                                    <li><input type="checkbox" name="chk" class="chk" id="skm"> <span>限时秒杀</span></li>
                                    </c:if>
                                    <c:if test="${jjth=='show' }">
                                    <li><input type="checkbox" name="chk" class="chk" id="nnm"> <span>99特惠</span></li>
                                    </c:if>
                                    <c:if test="${gdtc=='show' }">
                                    <li><input type="checkbox" name="chk" class="chk" id="lsm"> <span>总部商城</span></li>
                                    </c:if>
                                    <c:if test="${mfdh=='show' }">
                                    <li><input type="checkbox" name="chk" class="chk" id="mem"> <span>免单专区</span></li>
                                    </c:if>
                                    <c:if test="${hhh=='show' }">
                                    <li><input type="checkbox" name="chk" class="chk" id="cha"> <span>换货</span></li>
                                    </c:if>  --%>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="tab">
                        <h4>
								<c:if test="${zbdp=='show' }">
									<span class="">周边店铺</span>
								</c:if>
								
								<c:if test="${jfdh=='show' }">
									<span>积分兑换</span>
								</c:if>
								<c:if test="${xsms=='show' }">
									<span>限时秒杀</span>
								</c:if>
								<c:if test="${jjth=='show' }">
									<span>99特惠</span>
								</c:if>
								<c:if test="${gdtc=='show' }">
									<span>总部商城</span>
								</c:if>
								<c:if test="${mfdh=='show' }">
									<span>免单专区</span>
								</c:if>
								<c:if test="${hhh=='show' }">
									<span>换货专区</span>
								</c:if>

							</h4>
                        <ul class="tab_con">
                        	<c:if test="${zbdp=='show' }">
                            <jsp:include page="putDetail/sellerMall.jsp"></jsp:include>
                             </c:if>
                            
                          <%--            <c:if test="${jfdh=='show' }">
                            <jsp:include page="putDetail/scoreMall.jsp"></jsp:include>
                             </c:if>
                                    <c:if test="${xsms=='show' }">
                            <jsp:include page="putDetail/seckillMall.jsp"></jsp:include>
                             </c:if>
                                    <c:if test="${jjth=='show' }">
                            <jsp:include page="putDetail/nineNineMall.jsp"></jsp:include>
                             </c:if>
                                    <c:if test="${gdtc=='show' }">
                            <jsp:include page="putDetail/localSpecialtyMall.jsp"></jsp:include>
                             </c:if>
                            <c:if test="${mfdh=='show' }">
                            <jsp:include page="putDetail/memberMall.jsp"></jsp:include>
                             </c:if>
                              <c:if test="${hhh=='show' }">
                             <jsp:include page="putDetail/changeMall.jsp"></jsp:include>   
                             </c:if>    --%> 

                        </ul>


                        <div style="width:100%;height:50px;"></div>


                    </div>
                </div>
            </td>
        </tr>

        </table>
    </div>
</div>
<script>



    //当页面加载完后，点击已经投放的商城；
    $(function () {
    	
        var launchMall =${launchMall};
        var size=launchMall.length;
        for (var i=0;i<size;i++) {
            var v=launchMall[i];
           if(v==1){
            $("#sem").click();
           }else if(v==2){
           $("#ldm").click();
           }
          /*  else if(v==2){
           $("#scm").click();
           }else if(v==3){
           $("#skm").click();
           }else if(v==4){
           $("#nnm").click();
           }else if(v==5){
           $("#lsm").click();
           }else if(v==6){
           $("#mem").click();
           }else if(v==7){
           $("#cha").click();
           } */
        }

    });
    
    $('input[name="price"]').change(function(){
    	
    });
    var _canpost = true;
    function submitAjax() {
    	
    	_canpost = true;
        console.log("提交按钮被点击了");
        var check = 1;
        var zoneid = $("input#zoneid");
   	   	var province =$("select#provinceId").find("option:selected").text();
   	   	var city =$("select#cityId").find("option:selected").text();
   	   	var county = $("select#countyId").find("option:selected").text();
         $('input[name="price"]').each(function (index, dis){ 
        	var price = $(this).parents('.list').find('input[name="noStandardPrice"]').val();
        	
        	if(parseFloat(price)>parseFloat($(dis).val())){
        		alert("单价不可超过原价，请修改");
        		check = 2;
        		return false;
        	}
        });
        
        var subLengh=1;
        if(check==1){
        $('form').each(function (index, form) {
            if ($(".chek input[name='chk']").eq(index).prop("checked")) {
            	var _flag= $("form:eq(" + index + ") input[name='pageflag']").val();
            	//checked(_flag,index);
            	if(_canpost){
            		var formNum=$("form:eq(" + index + ") input[name='mallType']").val();
            		if(formNum==4){
            			var cityAndcounty = $("#cityAndcounty").text();
            			cityAndcounty = $.trim(cityAndcounty.replace(/-/,""));
            			if(cityAndcounty=='' && $("select#cityId").val()==0){
            				alert("请选择城市!");
            				return;
            			}
            			if(county&&county!='请选择'){
                   		   zoneid.val($("select#countyId").val());
                   	   }else if(city && city!='请选择'){
                   		   zoneid.val($("select#cityId").val());
                   	   }
                   	   else if(province && province!='请选择'){
                   		   zoneid.val($("select#provinceId").val());
                   	   }
            		}
                        
            		 $.ajax({
            		 	
                         type: 'POST',
                         url: 'put',
                         data: $('form').eq(index).serialize(),
                         async: false,
                         error: function (data) {
                             alert("提交失败");
                             subLengh=1;
                         },
                         success: function (data) {
                        	 subLengh=subLengh+1;
                             //alert($("form:eq("+index+") .title:first").text()+"投放成功");

                             alert(data.msg);
                      		  window.location.href="<%= basePath%>"+"/reBaseGoods/list";
                             $("form:eq(" + index + ") input[name='id']").val(data.id);
                         }
                     });
            	}
            }
        });
        }
    }
    //下架
    function shelves(type, pageType) {
        var id = $("#" + pageType + " input[name='id']").val();
        $.ajax({
            type: 'POST',
            url: 'shelves',
            data: {"id": id, "type": type},
            async: false,
            error: function (data) {
                alert("提交失败");
            },
            success: function (data) {
                alert($("#" + pageType + " .title:first").text() + "下架成功");
            }
        });
        $("input[name='chk'][id='" + type + "']").prop({"checked": false});
        $("input[name='chk'][id='" + type + "']").click();
        $("input[name='chk'][id='" + type + "']").prop({"checked": false});
        /* if(type=="nnm"){
         nnmShelves();
         }
         if(type=="lsm"){
         lsmShelves();
         } */
    }

    $('.chk').click(function (event) {
        var index = $(this).parent().index();
        console.log(index);
		

        //勾选checkBox时，增加对应的商城名的span，并且，将第一个勾选的商城名增加current样式；
        if ($(this).prop('checked')) {

            //在选择商城的同时，需要回显该商城数据；(如果有数据的话；)
            returnBack($(this));

            $('.tab h4 span').eq(index).addClass('show').siblings();

            if ($('.chk:checked').length == 1) {
                $('.tab h4 span').eq(index).addClass('current').siblings().removeClass('current');
                $('.tab .tab_con li').eq(index).addClass('current').siblings().removeClass('current');
            }
        }
        //去掉勾选时，去掉span，并适宜的更改内容；
        else {
            $('.tab h4 span').eq(index).removeClass('show').removeClass('current');
            if ($('.tab h4 span.current').length == 0) {
                //$('.tab h4 span:visible').eq(0).addClass('current')
                $('.tab h4 span.show').eq(0).addClass('current');
                var showIndex = $('.tab h4 span.show').eq(0).index();
                $('.tab .tab_con li').eq(showIndex).addClass('current').siblings().removeClass('current');
                //alert(1)
            }
            if ($('.chk:checked').length == 0) {
                $('.tab .tab_con li').removeClass('current');
            }

        }
    });

    /*<!-------------tab栏转换效果---------------->*/
    $('.tab h4 span').click(function (e) {
        $('.tab_con').addClass('current');
        $(this).addClass('current').siblings().removeClass('current');
        $('.tab_con li:eq(' + $(this).index() + ')').addClass('current').siblings().removeClass('current');
    });

    $('.stock_btn').click(function (e) {
        $(this).toggleClass('stock_btn_click');
        $(this).parents('.default_con').siblings('.stock_form').toggle();
    });
    function Goto() {
    }
    function deleteCurrentRow(obj) {
        var tr = obj.parentNode.parentNode;
        var tbody = tr.parentNode;
        tbody.removeChild(tr);
    }
    $('.stock_form .btm_in input').click(function (e) {
        $(this).parents('.stock_form').hide();
    });

    /*
     function stockLimit(checkbox){
     if(checkbox.attr('checked')){
     checkbox.parent().children('.stock_btn').attr('disabled',true);
     }else{
     checkbox.parent().children('.stock_btn').attr('disabled','');
     }

     }*/

    //当更改checkBox的时候 决定显示什么-->
    function clickNoStandard(obj) {
        var isChecked = $(obj).prop("checked");
        if (isChecked) {
            $(obj).parent("dd").find("div").css({"display": "inline"}).find("input").val("0");
            $(obj).parent("dd").find("[type='button']").css({"display": "none"});
            $(obj).parent("dd").find(".isNoStandard").val("1");
        } else {
            $(obj).parent("dd").find("div").css({"display": "none"});
            $(obj).parent("dd").find("[type='button']").css({"display": "inline"});
            $(obj).parent("dd").find(".isNoStandard").val("0");
        }
    }
    


</script>
</body>
</html>
