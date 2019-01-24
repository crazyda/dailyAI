$(document).ready(function() {
    $("#hd .sousuo").click(function(){
        $("#hd .sousuo,.box-sousuo").toggleClass('on');
    });

   // 选项卡 鼠标点击
   $(".TAB_CLICK li").click(function(){
     var tab=$(this).parent(".TAB_CLICK");
     var con=tab.attr("id");
     var on=tab.find("li").index(this);
     $(this).addClass('hover').siblings(tab.find("li")).removeClass('hover');
     $(con).eq(on).show().siblings(con).hide();
   });

});
  
