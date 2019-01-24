
//浣跨敤姝ゆ柟娉曢獙璇佹墍鏈塼ext textarea 鏄惁鍚湁闈炴硶瀛楃
 $(document).ready(function(){
    $("input[type='text']").each(function(i){
         $(this).keyup(function() { checkIllegalChar($(this)); }); 
     });
     $("textarea").each(function(i){
         $(this).keyup(function() { checkIllegalChar($(this)); }); 
     });
 });

  

 function checkIllegalChar(obj){
     var value = obj.val();
     if(!checkChar(value)){
          obj.val(value.substring(0,value.length-1));
          obj.focus();
         
     }
 }

 //妫�煡杈撳叆涓殑闈炴硶瀛楃
 function checkChar(InString) {
	 //var RefStringAll = ["<","%","\"",">","~","&","?","'","\\"];
	 var RefStringAll = ["<","\"",">","&","'","\\"];
	 var o = 1;
     for (Count = 0; Count < InString.length; Count++) {
         TempChar = InString.substring(Count, Count + 1);
         $.each(RefStringAll,function(key,val){
        	 if ((val.indexOf(TempChar, 0) == 0)) {
        		 o = -1;
        		 alert("鎮ㄧ殑杈撳叆涓惈鏈夐潪娉曞瓧绗�璇烽噸鏂拌緭鍏�");
                 return false;
             }
         })
         if(o==-1){
        	 return false;
         }
         
     }
     return true;
 }