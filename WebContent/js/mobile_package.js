var before_pageI = 1;

var before_pageI_weixin = 1;
var before_pageI_yixin = 1;

function showPage(i){
   switch(before_pageI){
      
       case 1:
    	   $("#weixin_page").hide();
    	  $("#package_page_1").removeClass("tab_line_item_selected");
	      break;
       case 2:
    	   $("#android_page").hide();
    	   $("#package_page_2").removeClass("tab_line_item_selected");
 	      break;
       case 3:
    	   $("#ios_page").hide();
    	   $("#package_page_3").removeClass("tab_line_item_selected");
 	      break;
       case 4:
    	   $("#baidu_page").hide();
    	   $("#package_page_4").removeClass("tab_line_item_selected");
 	      break;
       case 5:
    	   $("#yixin_page").hide();
    	   $("#package_page_5").removeClass("tab_line_item_selected");
 	      break;
   }
   
   switch(i){
   
	   case 1:
		   $("#weixin_page").show();
	 	   $("#package_page_1").addClass("tab_line_item_selected");
		   break;
	    case 2:
	       $("#android_page").show();
	 	   $("#package_page_2").addClass("tab_line_item_selected");
		   break;
	    case 3:
	       $("#ios_page").show();
	 	   $("#package_page_3").addClass("tab_line_item_selected");
		   break;
	    case 4:
	       $("#baidu_page").show();
	 	   $("#package_page_4").addClass("tab_line_item_selected");
		   break;
	    case 5:
	       $("#yixin_page").show();
	 	   $("#package_page_5").addClass("tab_line_item_selected");
		   break;
   }
   
   before_pageI = i;
	
}

function showWeixinSub(i){
	 $("#weixin_sub_"+before_pageI_weixin).removeClass("tab_line_item_selected");
	 $("#weixin_sub_page_"+before_pageI_weixin).hide();
	 
	 $("#weixin_sub_"+i).addClass("tab_line_item_selected");
	 $("#weixin_sub_page_"+i).show();
      before_pageI_weixin = i;
}

function showYixinSub(i){
	 $("#yixin_sub_"+before_pageI_yixin).removeClass("tab_line_item_selected");
	 $("#yixin_sub_page_"+before_pageI_yixin).hide();
	 
	 $("#yixin_sub_"+i).addClass("tab_line_item_selected");
	 $("#yixin_sub_page_"+i).show();
     before_pageI_yixin = i;
}