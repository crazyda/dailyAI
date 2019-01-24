<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.axp.model.AdminUser" %>
<%@page import="com.axp.model.ExtendResults" %>

<%@page import="com.axp.util.StringUtil" %>

<!DOCTYPE html>
<!-- saved from url=(0022) -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <html style="overflow: hidden;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>后台操作系统</title>
        <link href="${BASEPATH_IN_SESSION}css/global.css" rel="stylesheet"
              type="text/css">
        <link href="${BASEPATH_IN_SESSION}css/main.css" rel="stylesheet" type="text/css">
        <script type="text/javascript" src="${BASEPATH_IN_SESSION}js/jquery.js"></script>

        <script type="text/javascript"
                src="${BASEPATH_IN_SESSION}js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript"
                src="${BASEPATH_IN_SESSION}js/jquery.jsonp-2.2.0.js"></script>

        <script type="text/javascript">
            window.onload = function () {
                var agent = navigator.userAgent.toLowerCase();
                //alert('agent========'+agent);
                if (!( agent.indexOf('chrome') > -1 || agent.indexOf('firefox') > -1 || agent.indexOf('safari') > -1)) {
                    if (confirm('您的浏览器不能完全支持本页面，是否跳转到chrome浏览器下载页面?')) {
                        window.open('http://www.google.cn/intl/zh-CN/chrome/browser/index.html');
                    }
                }
            };


        </script>


    </head>

<body style="overflow: hidden;">

<script type="text/javascript"
        src="${BASEPATH_IN_SESSION}js/jquery.mousewheel.js"></script>
<script type="text/javascript"
        src="${BASEPATH_IN_SESSION}js/jquery.jscrollpane.js"></script>

<link href="${BASEPATH_IN_SESSION}css/jquery.jscrollpane.css" rel="stylesheet"
      type="text/css">
<script type="text/javascript" src="${BASEPATH_IN_SESSION}js/main.js"></script>
<script language="javascript">$(document).ready(main_obj.page_init);
window.onresize = main_obj.page_init;</script>
<div id="header">
    <div class="logo">
        <img src="${BASEPATH_IN_SESSION}img/caozuo-logo2.png"/>
    </div>
    <div class="guanli" id="div_guanli">
        <img src="${BASEPATH_IN_SESSION}img/persion.png"/> 我的账号 <img src="${BASEPATH_IN_SESSION}img/trangle.png"/>
    </div>
    <div class="line1"></div>
    <ul>
        <div class="line1"></div>
        <li><a href="javascript:index_admin();" target="iframe">我的首页</a></li>
        <div class="line1"></div>
        <li><a href="#" onclick="logout();" target="iframe">退出登陆</a></li>

    </ul>
</div>

<div id="main" style="height: auto;">
    <div class="menu" style="overflow: auto; padding: 0px; width: 220px;">

        <div class="jspContainer" style="width: 220px;height: 2324px;">
            <div class="jspPane" style="padding: 0px; top: 0px; width: 220px;">
                <dl>
                    <c:forEach items="${ReItems_of_loginin_in_Session}" var="each">
                        <dt>${each.key}</dt>
                        <dd>
                            <c:forEach items="${each.value}" var="each2">
                                <div>
                                    <a target="iframe"
                                       onclick="javascript:document.getElementById('iframe_content').src='${BasepathWithoutFirstSprit_IN_SESSION}${each2.linkAddress}'"
                                       style="cursor:pointer">${each2.name}</a>
                                </div>
                            </c:forEach>
                        </dd>
                    </c:forEach>
                </dl>
            </div>
        </div>
    </div>

    <div class="iframe">
         <iframe id="iframe_content" src="${BASEPATH_IN_SESSION}/login/indexPage" name="iframe" frameborder="0" scrolling="auto"></iframe> 
    </div>

    <div class="clear"></div>
</div>
<script type="text/javascript" language="javascript">
    $(document).ready(function () {
        $("#div_guanli").click(function () {
            popCenterWindow2();
        });
    });

    var popWidth;
    var popHeight;
    function init() {

        popHeight = $(".guanliwindow").height();
        popWidth = $(".guanliwindow").width();
    }

    function popCenterWindow2() {
        init();
        var popY = 55;
        var popX = 220;
        $("#center2").css("top", popY).css("left", popX).slideToggle("fast");
        closeWindow();
    }
    function closeWindow() {
        $("#center2").hover(
                function () {
                }, function () {
                    $(this).hide("fast");
                }
        );

    }
</script>
<div class="guanliwindow" id="center2">
    <div class="content2">
        <p>
            登录账号:<font color="#b72c60">${loginName }</font>
        <p>
        <p>
            用户名:<font color="#b72c60">${userName }</font>
        <p>

        <p>会员级别:<font color="#b72c60">${userLevelName }</font></p>
    </div>
</div>


</body>
<script>

    function logout() {
        var url = "${BASEPATH_IN_SESSION}login/logout";
        parent.document.location = url;
    }

    function index_admin() {
        var url = "${BASEPATH_IN_SESSION}/login/indexPage";
        document.getElementById("iframe_content").src = url;
    }
    function customers() {

        parent.mainFrame.middelIfm.customers();
    }

    function owner() {
        var role_id = 1;
        //  alert('roelid='+role_id);
        switch (role_id) {
            case 1:
                //管理员
                parent.mainFrame.middelIfm.proxys(1);
                break;

        }
    }

    function top_Menu(f) {

        switch (f) {
            case 4:
                parent.mainFrame.middelIfm.students();
                break;
        }

    }

    function selectMenu(i) {

        switch (i) {
            case 1://用户级别管理
                levels();
                break;
            case 2://用户级别管理
                shoptypes();
                break;
            case 3://地区管理
                zones();
                break;
            case 4://兑换商品管理
                goods(1);
                break;
            case 5://前台用户

                users();
                break;
            case 6://前台用户
                scoretypes();
                break;
            case 7://自定义积分管理
                userscoretypes();
                break;
            case 8://微网站管理
                websites();
                break;
            case 9://兑换记录
                exchangescores(0);
                break;
            case 10://图片审核管理
                checkimgs();
                break;
            case 11://消息管理
                messages();
                break;
            case 12://邀请记录
                inviterecords();
                break;
            case 13://关于我们
                addbaseinfo(1);
                break;
            case 14://幻灯片
                slides();
                break;
            case 15://滑屏广告设置
                adversettings();
                break;
            case 16://六维积分奖励
                rewardscores();
                break;
            case 17://每个人推广人数限制
                extendlimits();
                break;
            case 18://充值记录
                feerecords();
                break;
            case 19://代理商区域
                proxyzones();
                break;
            case 20://收益记录
                userprofits();
                break;
            case 21://代理商管理
                proxyinfos();
                break;
            case 22://广告审核管理
                checkimgs_adver();
                break;
            case 50:
                adminusers();
                break;
            case 51:
                goods(2);
                break;
            case 52:
                pool();
                break;
            case 53:
                seller();
                break;
            case 54:
                adminuserdate();
                break;
            case 55:
                adminusers(3);
                break;
            case 56:
                adminusers(4);
                break;
            case 57:
                adminusers(6);
                break;
            case 58:
                adminusers(5);
                break;
            case 59:
                mydate();
                break;
            case 60:
                updatepwd();
                break;
            case 61://消息管理
                messages_admin();
                break;
            case 62://消息管理
                buylist();
                break;
            case 63://
                buy(1);
                break;
            case 64://消息管理
                advers_qr();
                break;
            case 65://消息管理
                advers_phone();
                break;
            case 66://消息管理
                advers_mv();
                break;
            case 67://兑换记录
                exchangescores(1);
                break;
            case 68://礼品数据
                giftdate();
                break;
            case 69://广告数据
                adverdate();
                break;
            case 70://购买数据
                buydate(1);
                break;
            case 71://
                buy(2);
                break;
            case 73:
                sellerdate();
                break;
            case 74://
                check_adver();
                break;
            case 75://
                check_adver_date();
                break;
            case 76://
                map();
                break;
            case 80://
                machine();
                break;
            case 81://
                machinepool();
                break;
            case 82://
                distribution();
                break;
            case 83://
                weixin();
                break;
            case 84://
                advertypelist();
                break;
            case 85://
                coinlist();
                break;
            case 86:
                outtimepool();
                break;
            case 87:
                allmachines();
                break;
            case 88:
                playslist();
                break;
            case 89:
                checklist();
                break;
            case 90:
                playspoollist();
                break;
            case 91:
                playspoolouttime();
                break;
            case 92://
                applylist();
                break;
            case 93://
                planlist();
                break;
            case 100://
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}voucher/list";
                break;
            case 101://
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}cashshop/typelist";
                break;
            case 102://
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}cashshop/specslist";
                break;
            case 103://消息中心
                messagelist();
                break;
            case 104://代金券状态列表-后台用户
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}hasvoucher/adminuserlist";
                break;
            case 105://代金券状态列表-商家用户
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}hasvoucher/sellerlist";
                break;
            case 106://系统管理-粉丝基数
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}statisticsbase/list";
                break;
            case 107://数据中心-推广员数据
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}promoter/list";
                break;
            case 108://系统管理-红包
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}redpaper/list";
                break;
            case 109://系统管理-红包
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}redpaper/logList";
                break;
            case 110://商家结算
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}seller/sellerBalanceList";
                break;
            case 111://预警管理
                receiver();
                break;
            case 201://现金商品审核
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}cashshop/checkList";
                break;
            case 202://商家结算申请
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}sellerDraw/list";
                break;
            case 203://商家身份审核
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}sellercheck/checklist";
                break;
            case 205://广告发布量统计图
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}echarts/adver.jsp";
                break;
            case 206://粉丝数据统计图
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}echarts/users.jsp";
                break;
            case 207://粉丝活跃度统计图
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}echarts/userliveness.jsp";
                break;
            case 209://秒杀时段管理
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}cashshopreset/listReset";
                break;
            case 210://数据中心-会员数据
                document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}members/list2";
                break;
        }
    }

    function playspoolouttime() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}playspool/outtimelist";
    }

    function playspoollist() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}playspool/list";
    }

    function plays(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}playspool/add?playsid=" + id;
    }

    function delplayspool(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}playspool/del?id=" + id;
    }

    function checklist() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}plays/checklist";
    }

    function checkplays(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}plays/check?id=" + id;
    }

    function editplays(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}plays/add?id=" + id;
    }

    function delplays(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}plays/del?id=" + id;
    }

    function addplays() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}plays/add";
    }

    function playslist() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}plays/list";
    }


    function allmachines() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machine/machinedate";
    }

    function outtimepool() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}pool/outtimelist";
    }
    function planlist() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}plans/list";

    }

    function applylist(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}apply/list";

    }

    function editapply(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}apply/add?id=" + id;

    }
    function advertypelist() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advertype/list";

    }

    function addadvertype() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advertype/add";

    }
    function editadvertype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advertype/add?id=" + id;

    }

    function deladvertype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advertype/del?id=" + id;

    }


    function coinlist() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}coin/list";

    }

    function addcoin() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}coin/add";

    }
    function editcoin(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}coin/add?id=" + id;

    }

    function delcoin(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}coin/del?id=" + id;

    }


    function weixin() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}weixinuser/list";

    }

    function weixinudate() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}weixinuser/update";

    }

    function distribution() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machinepool/one";

    }

    function go_two() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machinepool/two";
    }

    function putqr(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machinepool/put?d_id=" + id;
    }

    function machinepool() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machinepool/list";

    }

    function addadver(id, id1, id2) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machinepool/add?id=" + id + "&machineid=" + id1 + "&adverid=" + id2;

    }
    function delmachinepool(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machinepool/del?id=" + id;

    }

    function delmachine(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machine/del?id=" + id;

    }
    function getpool(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machinepool/getpool?machineid=" + id;

    }

    function machine() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machine/list";

    }
    function addmachine() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machine/add";

    }
    function editmachine(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}machine/add?id=" + id;

    }

    function map() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/map";

    }

    function deladvers(id, type) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/del?id=" + id + "&type=" + type;

    }
    function check_adver() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/check_adver";

    }
    function check_adver_date() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/check_adver_date";

    }

    function editcheck(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/editcheck?id=" + id;

    }

    function giftdetail(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/giftdetail?good_id=" + id;

    }

    function adverdetail(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/adverdetail?good_id=" + id;

    }
    function giftdate() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/giftdate";

    }
    function adverdate() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/adverdate";

    }
    function buydate() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}buy/buydate";

    }

    function advers_phone() {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/phonelist";

    }
    function advers_mv() {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/mvlist";

    }

    function advers_qr() {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/qrlist";

    }

    function editadvers(id, cate) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/add?id=" + id + "&cate=" + cate;
    }
    function addbuy() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}buy/add";

    }
    function buylist() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}buy/list";

    }

    function buy(type) {
        if (type == 1) {
            document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}buy/adminlist1";
        } else {
            document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}buy/adminlist2";
        }


    }
    function delbuy(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}buy/del?id=" + id;

    }

    function comfirmbuy(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}buy/confirm?id=" + id;

    }

    function seller() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}seller/list";

    }
    function sellerdate() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}seller/sellerdate";

    }
    function addseller() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}seller/add";

    }

    function editseller(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}seller/add?id=" + id;

    }
    function topseller(id, type) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}seller/top?id=" + id + "&type=" + type;

    }
    function delseller(id) {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}seller/del?id=" + id;
    }

    function playgoods(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}pool/add?id=" + id;

    }

    function playpool(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}pool/add?adver_id=" + id;

    }

    function pool(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}pool/list";

    }
    function delpool(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}pool/del?id=" + id;

    }


    function levels() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}level/list";
    }

    function addlevel() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}level/add";

    }

    function addadvers(cate) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}advers/add?cate=" + cate;

    }


    function editlevel(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}level/add?id=" + id;
    }

    function dellevel(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}level/del?id=" + id;
    }


    /*商家类型管理*/
    function shoptypes() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}shoptype/list";
    }

    function addshoptype() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}shoptype/add";

    }

    function editshoptype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}shoptype/add?id=" + id;
    }

    function delshoptype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}shoptype/del?id=" + id;
    }

    /*地区管理*/
    function zones() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}zone/list";
    }

    function addzone() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}zone/add";

    }

    function editzone(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}zone/add?id=" + id;
    }

    function delzone(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}zone/del?id=" + id;
    }

    /*预警管理*/
    function receiver() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}receiver/list";
    }
    function addreceiver() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}receiver/add";

    }

    function editreceiver(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}receiver/add?rid=" + id;
    }

    function delreceiver(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}receiver/del?rid=" + id;
    }
    /* 商家结算审核*/
    function toEdit(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}sellerDraw/toUpdateCheck?sid=" + id;
    }
    function toSeller(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}sellerDraw/toSellerDetails?sid=" + id;
    }

    /*兑换商品管理*/
    function goods(type) {
        if (type == 1) {
            document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/giftlist";
        } else {
            document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/adverlist";
        }

    }


    function addgoods(type) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/add?type=" + type;

    }

    function editgoods(id, type) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/add?id=" + id + "&type=" + type;
    }


    function delgoods(id, type) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/del?id=" + id + "&type=" + type;
    }

    function adver_imgs(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/adverimgs?id=" + id;
    }

    function checkimgs() {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/checkimgs";
    }

    function checkimgs_adver() {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/checkimgs_adver";
    }

    function add_checkimgs(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/add_checkimgs?id=" + id;
    }

    function add_checkimgs_adver(id) {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}goods/add_checkimgs_adver?id=" + id;
    }
    /*后台用户*/
    function adminusers() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adminUser/list";
    }
    /*后台用户*/
    function adminuserdate() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adminuser/userdate";
    }

    function mydate() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adminuser/mydate";
    }
    function updatepwd() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adminuser/updatepwd";
    }
    function addadminuser(type) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adminuser/add?type=" + type;
    }
    function deladminuser(id, type) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adminuser/del?id=" + id + "&type=" + type;
    }
    function editadminuser(id, type) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adminuser/add?id=" + id + "&type=" + type;
    }
    /*商家管理*/
    function users() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/list";
    }

    function adduser() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/add";

    }

    function edituser(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/add?id=" + id;
    }

    function edit_usersetting(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/addusersetting?id=" + id;
    }

    function deluser(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/del?id=" + id;
    }

    function addusercore(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/addscore?id=" + id;
    }

    function resetpwd(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/resetpwd?id=" + id;
    }

    function extendresult(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/extendresult?id=" + id;
    }

    function changelevel(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/changelevel?id=" + id;
    }

    function addusermoney(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}user/addcost?id=" + id;

    }

    /*积分类型管理*/
    function scoretypes() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}scoretype/list";
    }

    function addscoretype() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}scoretype/add";

    }

    function editscoretype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}scoretype/add?id=" + id;
    }

    function delscoretype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}scoretype/del?id=" + id;
    }


    /*积分类型管理*/
    function userscoretypes() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}userscoretype/list";
    }

    function adduserscoretype() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}userscoretype/add";

    }

    function edituserscoretype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}userscoretype/add?id=" + id;
    }

    function deluserscoretype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}userscoretype/del?id=" + id;
    }

    /*微网站管理*/
    function websites() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}website/list";
    }

    function addwebsite() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}website/add";

    }

    function editwebsite(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}website/add?id=" + id;
    }

    function delwebsite(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}website/del?id=" + id;
    }


    /*积分兑换记录管理*/
    function exchangescores(type) {
        if (type == 0) {
            document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}exchangescore/list_0";
        } else {
            document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}exchangescore/list_1";
        }

    }

    function delexchangescore(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}exchangescore/del?id=" + id;
    }

    function editchanger(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}exchangescore/status?id=" + id;
    }

    /*消息管理*/
    function messages() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}message/list?type=user";
    }

    /*消息管理*/
    function messages_admin() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}message/list?type=admin";
    }

    function addmessage() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}message/add";

    }

    function editmessage(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}message/add?id=" + id;
    }

    function delmessage(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}message/del?id=" + id;
    }

    /*邀请记录*/
    function inviterecords() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}inviterecord/list";
    }

    function addbaseinfo(id) {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}baseinfo/add?id=" + id;
    }

    /*幻灯片*/
    function slides() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}slide/list";
    }

    function addslide() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}slide/add";

    }

    function editslide(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}slide/add?id=" + id;
    }

    function delslide(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}slide/del?id=" + id;
    }

    /*滑屏广告位设置*/
    function adversettings() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adversetting/list";
    }

    function adverrecord(id) {

        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adverrecord/list?good_adver_id=" + id;
    }

    function addadversetting() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adversetting/add";

    }

    function editadversetting(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adversetting/add?id=" + id;
    }

    function deladversetting(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adversetting/del?id=" + id;
    }

    function adveronline(id, status) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}adversetting/online?id=" + id + "&status=" + status;
    }


    /*六维积分奖励*/
    function rewardscores() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}rewardscore/list";
    }

    function addrewardscore() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}rewardscore/add";

    }

    function editrewardscore(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}rewardscore/add?id=" + id;
    }

    function delrewardscore(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}rewardscore/del?id=" + id;
    }

    /*推广人数限制*/
    function extendlimits() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}extendlimit/list";
    }

    function addextendlimit() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}extendlimit/add";

    }

    function editextendlimit(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}extendlimit/add?id=" + id;
    }

    function delextendlimit(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}extendlimit/del?id=" + id;
    }


    /*充值记录*/
    function feerecords() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}feerecord/list";
    }

    function addfeerecord() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}feerecord/add";

    }

    function editfeerecord(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}feerecord/add?id=" + id;
    }

    function delfeerecord(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}feerecord/del?id=" + id;
    }


    /*代理商区域*/
    function proxyzones() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}proxyzone/list";
    }

    function addproxyzone(parent_id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}proxyzone/add?parent_id=" + parent_id;

    }

    function editproxyzone(id, parent_id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}proxyzone/add?id=" + id + "&parent_id=" + parent_id;
    }

    function delproxyzone(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}proxyzone/del?id=" + id;
    }

    /*收益列表*/
    function userprofits() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}userprofit/list";
    }


    /*代理商管理*/
    function proxyinfos() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}proxyinfo/list";
    }


    function delproxyinfo(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}proxyinfo/del?id=" + id;
    }

    function showScoreRecord(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}scorerecord/list?user_id=" + id;

    }

    function editcashshop(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}cashshop/add?ids=" + id;

    }
    /*消息中心*/
    function messagelist() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}messagecenter/ImageTextList";
    }
    //修改粉丝基数
    function editStatisticsBase(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}statisticsbase/edit?id=" + id;
    }

    //提现审核
    function getmoneyAudit(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}cashmoney/getmoneyAudit?id=" + id;
    }

    //结算记录
    function showBalanceRecord(id, type) {
        var path = "${BASEPATH_IN_SESSION}userDrawCashRecord/listBalance?module=1";
        if (type == 1) {
            path += "&type=1&sellerId=" + id;
        }
        if (type == 2) {
            path += "&type=2&proxyonename=" + id;
        }

        document.getElementById("iframe_content").src = path;
    }

    //结算详情
    function showOrderRecord(id, type) {
        var path = "${BASEPATH_IN_SESSION}usercashshoprecord/list?listModel=1";
        if (type == 1) {
            path += "&type=1&sellerId=" + id;
        }
        if (type == 2) {
            path += "&type=2&adminUserId=" + id;
        }

        document.getElementById("iframe_content").src = path;
    }

    //促销商城图文类别管理

    function addimagetype() {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}imagetype/add";
    }

    function editimagetype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}imagetype/add?id=" + id;
    }

    function delimagetype(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}imagetype/del?id=" + id;
    }

    //商家充值广告天数
    function forwardSellerDeposit(id) {
        document.getElementById("iframe_content").src = "${BASEPATH_IN_SESSION}seller/fowardDeposit?id=" + id;
    }
</script>
</html>
