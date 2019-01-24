var before_ptype_id;
var before_p_id =0;
function sendMessage(){
	   var mobilephones = $("#mobilephones").attr("value");
	   
	   if($.trim(mobilephones)==""){
	      alert(' 请输入手机号码！');
	      return;
	   }
	   
	   var content = $("#content").attr("value");
	   
	   if($.trim(content)==""){
	      alert(' 请输入短信内容！');
	      return;
	   }
	   
	   var istime = $("#istime").attr("checked");
	   if(istime){
	      var senddate = $("#senddate").attr("value");
	      if($.trim(senddate)==""){
	         alert('请输入定时时间！');
	         return;
	      }
	   }
	   
//	   var len = mobilephones.split('\n').length;
//	   var mul = $("#phone_count").html();
//	   
//	   if((len*mul)>cost){
//		   alert('您的短信余额不够！');
//		   return;
//		}
	   $("#btnSub").attr("disabled",true);
	   document.messageForm.submit();
	}

	function showtime(){
	   var istime = $("#istime").attr("checked");
	   if(istime){
	       $("#span_time").css("visibility","visible");
	       $("#istime_v").attr("value","1");
	   }else{
	       $("#span_time").css("visibility","hidden");
	       $("#istime_v").attr("value","0");
	   }
	}

	function checkLength(obj,maxlength){
	    if(obj.value.length > maxlength){
	        obj.value = obj.value.substring(0,maxlength);
	        alert("最多一次输入210个字符！");
	    }
	}

	function checkphonenumber(obj){
		
		var content = $("#mobilephones").attr("value");
		if(content.length==0){
			$("#span_pn").html("0");
			return;
		}
		var len = content.split('\n').length;
		$("#span_pn").html(len);
	}
	
    var isshow_p = false;
    var isinit= false;
    
	function insertPhrase(){
		
	    if(!isshow_p){
	    
		    $("#div_phrase").css("display","block");
		    if(!isinit){
			   messageservice.GetPhraseByType(before_ptype_id,callback_phrases);
			    isinit=true;
		    }
		    isshow_p = true;
	    }else{
	    	
	    	$("#div_phrase").css("display","none");
	    	isshow_p = false;
		}
    }

    function callback_phrases(result){
       if(result!=""){
           var rarr = result.split("_");
           var str= "";
           for(i=0;i<rarr.length;i++){
               var p = rarr[i];
               if(p!=""){
                   if(i==0){
                       str = str + "<div class=\"content_list_line_select\" id=\"p_"+i+"\" onclick=\"showphrase_e("+i+");\" ondblclick=\"selectPhrase('"+p+"');\" >"+p+"</div>";
                   }else{
                	   str = str + "<div class=\"content_list_line\" id=\"p_"+i+"\" onclick=\"showphrase_e("+i+");\" ondblclick=\"selectPhrase('"+p+"');\" >"+p+"</div>";
                   }
                   str = str + "<div class=\"split_line\"></div>";
               }
           }
           $("#phrase_content_lst").html(str);
       }
    }
    
    function showPhrase(id){
         $("#pt_"+before_ptype_id).css("background","#e7e7e7");
         $("#pt_"+id).css("background","#3399ff");
         before_ptype_id = id;

         messageservice.GetPhraseByType(id,callback_phrases);
    }

    function showphrase_e(id){
    	 $("#p_"+before_p_id).css("background","#e7e7e7");
         $("#p_"+id).css("background","#3399ff");
         before_p_id = id;
    }
    
    function GetUsersByShop(shop_id){
    	
    	$("#span_parentLst").html("");
    	$("#div_parentlst").css("display","block");
    	messageservice.GetUsersByShop(shop_id,callback_getuserbyshop);
    }
    
    function callback_getuserbyshop(result){
    	//alert('res len====='+result.length);
    	 if(result.length>0){
    	     // storeParents = result;
    	  	  var rarr = result.split(",");
    	  	  var str = "";
    	  	  
    	  	  for(i=0;i<rarr.length;i++){
    	  		  
    	  		  phone = rarr[i];
    	  		  if(phone!=""){
    	  		   	  
    	  			 str = str + "<div class=\"content_scope_r_sel\">";
      	  		     str = str + "<div class=\"parent_line_l\" style=\"font-size:12px;\">&nbsp;"+phone+"</div><div class=\"parent_line_r\" style=\"cursor:hand\"><input type=checkbox name=\"ch_parent\" id=\"ch_parent_"+phone+"\" value=\""+phone+"\" /></div>";
      	  		   
	      	  		 str = str + "</div>";
	      	         str = str + "<div class=\"split_line\"></div>";
    	  		  }
    	  	  }
    	  	
    	  	 $("#span_parentLst").html(str);
    	    }else{
    	  	  $("#span_parentLst").html("");
    	     }
    }

    function selectPhrase(p){
    	
    	var c = $("#content").html();
        
    	$("#content").html(c+p);
    	$("#div_phrase").css("display","none");
    }
    
    function addMark(obj){
    	var v =obj.value;
    	var str = "";
    	switch(parseInt(v,10)){
    	    case -1:
    		   break;
    	    case 1:
    	       str ="商家名称";
     		   break;
    	    case 2:
    	    	str ="用户手机号";
      		   break;
    	}
        var c = document.getElementById("content").value;
    	document.getElementById("content").value = c+"{"+str+"}";
    }

    function showType(sendtype){
	    
	    //var sex = $("input[name='sex'][checked]").val();
	    $("#tr_txt").css("display","none");
	    $("#tr_address").css("display","none");
	   // $("#tr_excel").css("display","none");
	    $("#tr_content").css("display","block");
	    switch(sendtype){
	       case 1:
	    	   $("#tr_parent").css("height","0px");
	          $("#tr_txt").css("display","block");
	          
	          break;
	       case 2:
	    	  // $("#tr_parent").css("height","230px");
	    	  var isdis= $("#div_parentlst").css("display");
	    	  if(isdis=="block"){
	    		  $("#tr_parent").css("height","230px");
	    	  }
	          $("#tr_address").css("display","block");
	          break;
	       case 3:
	           $("#tr_content").css("display","none");
	          break;
	    }
	}

	function submitMessage(){

	   var sendtype = $("input[name='sendtype']:checked").val();
	   if(sendtype=="1"){
	        var txtfile = $("#txtfile").attr("value");
	        if($.trim(txtfile)==""){
	           alert("请选择一个txt文件");
	           return;
	        }
	        if(txtfile.indexOf('.')==-1){
	           alert("请选择一个txt文件");
	           return;
	        }
	        var ext = txtfile.substring(txtfile.lastIndexOf('.')+1,txtfile.length);
	        if(ext.toLowerCase()!="txt"){
	            alert("请选择一个txt文件");
	            return;
	        }
	        var content = $("#content").attr("value");
	        if($.trim(content)==""){
	            alert("请输入短信内容！");
	            return;
	        }
	    }else if(sendtype=="2"){
	       var content = $("#content").attr("value");
	       if($.trim(content)==""){
	            alert("请输入短信内容！");
	            return;
	       }
	       
	       var code_Values = document.all['ch_parent']; 
	       
	      
	 	   var plen = code_Values.length;
	 	    var parent_ids = "";
	 	   if(!plen){
	 		   
	 		  pid = code_Values.value;
//	 		  alert('[id====='+pid);
	 		  parent_ids = parent_ids+ pid+"_";
	 	   }else{
	 	   
		 	   for(j=0;j<plen;j++){
		 		  if(code_Values[j].checked){
		 			  pid = code_Values[j].value;
		 			 parent_ids = parent_ids+ pid+"_";
		 		  }
		 		   
		 	   } 
	 	   }
	 	   if(parent_ids.length>0){
	 		   parent_ids = parent_ids.substring(0,parent_ids.length-1);
	 	   }
	       $("#parent_ids").attr("value",parent_ids);
	       //alert('parentids==='+parent_ids);
	       if(parent_ids==""){
	    	   alert('请选择家长！')
	    	   return;
	       }
	    }else{
//	         var xlsfile = $("#xlsfile").attr("value");
//	         if($.trim(xlsfile)==""){
//	           alert("请选择一个xls文件");
//	           return;
//	        }
//	        if(xlsfile.indexOf('.')==-1){
//	           alert("请选择一个xls文件");
//	           return;
//	        }
//	        var ext = xlsfile.substring(xlsfile.lastIndexOf('.')+1,xlsfile.length);
//	        if(ext.toLowerCase()!="xls"){
//	            alert("请选择一个xls文件");
//	            return;
//	        }
	    }
	    var istime = $("#istime").attr("checked");
	    if(istime){
	      var senddate = $("#senddate").attr("value");
	      if($.trim(senddate)==""){
	         alert('请输入定时时间！');
	         return;
	      }
	    }
	    var vertifymessage = $("#vertifymessage").attr("value");
	    if($.trim(vertifymessage)==""){
	        alert("请输入验证码！");
	        return;
	    }
	    
	    $("#btnSub").attr("disabled",true);
	    document.messageMaxForm.submit();
	}

	function chkPreMet(){
	      var c = $("#chkPre").attr("checked");
	      if(c){
	         $("#isprefix").attr("value","1");
	      }else{
	         $("#isprefix").attr("value","0");
	      }
	}

	function svcode(url,name) { 
		var winname = window.open('', '_blank', 'height=1,width=1,top=200,left=300'); 
		winname.document.open('text/html', 'replace'); 
		winname.document.writeln(url); 
		winname.document.execCommand('saveas','',name); 
		winname.close();
		return false; 
	}
	function selectMode(i){
	
	    $("#sendMod_v").attr("value",i);
	    if(i==1){
	    	$("#div_smartmode").css("display","none");
	    }else{
	    	$("#div_smartmode").css("display","block");
	    }
	}