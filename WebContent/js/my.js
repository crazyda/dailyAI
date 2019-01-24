// JavaScript Document
$(function(){ 
	(function(){ 
		//banner自动轮播效果-----------------------------
		var num=0;
		var timer=null;
		var shijian=3000;
		timer=setInterval(sport,shijian)
		function sport(){
			num++;
			if( num>slideLen ){ num=0 } 
			$('.banner ul li:eq('+num+')').addClass('current').siblings().removeClass('current');
			$('.banner ol li:eq('+num+')').addClass('current01').siblings().removeClass('current01');
		}
	})();
	
//屏幕滚动导航变化效果-----------------------------

	(function(){
		var win=$(window).height() 
		$(window).scroll(function(e) {
            var top=$(window).scrollTop()
			if( top>100 ){ 
				$('.search').css('background','rgba(0,191,223,1.0)')
			}else{ 
				$('.search').css('background','rgba(0,191,223,0)')
			}
        });
	})();
//搜索框焦点触发效果------------------------------
	(function(){ 
		$('.search input').focus(function(e) {
            if( $(this).val()=='搜索'){ 
				$(this).val('')
			}
        }).blur(function(e) {
            if( $(this).val()==''){ 
				$(this).val('搜索')
			}
        });
	})();

})



























