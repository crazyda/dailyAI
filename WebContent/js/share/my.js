// JavaScript Document


//代码分离---------------------
$(function(){ 
//每个效果分别用闭包写-----
//17演示经典新闻tab栏，真正可用的tab栏--------------
	(function(){ 
		$('.tab h2 span').click(function(e) {
            $(this).addClass('current').siblings().removeClass('current');
			$('.tab ul li:eq('+$(this).index()+')').show().siblings().hide();
        });
	})();
//18演示淘宝商品展示，上下显示隐藏内容--------------
	(function(){ 
		//用jq制作一步css的工作让中间的很多产品隐藏掉；
		var lis=$('.today p:gt(1)')
		lis.hide();
		//toggle(function(){},function(){})
		$('.today .tittle').click(function(e) {
            lis.slideToggle('fast');
        });
	})();
	(function(){ 
	/*	//用jq制作一步css的工作让中间的很多产品隐藏掉；
		var lis=$('.before p:gt(1)')
		lis.hide();
		//toggle(function(){},function(){})
		$('.before .tittle').click(function(e) {
            lis.slideToggle('fast');
        });*/
		
		if($(".today p").length <= 0){
			$(".today").empty();
		}
		if($(".before p").length <= 0){
			$(".before").empty();
		}
	})();
})

































