/**得到项目跟路径*/
function getRootPath() {
	try {
		//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
	    var curWwwPath=window.document.location.href;
	   	var comIndex =  curWwwPath.indexOf(".com");
	    if(curWwwPath.indexOf(".com") > 0){
	    	//域名映射的情况不要项目名
	    	//return curWwwPath.substr(0,comIndex+4);
	    }
	    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    //获取主机地址，如： http://localhost:8083
	    var localhostPaht=curWwwPath.substring(0,pos);
	    //获取带"/"的项目名，如：/uimcardprj
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    return(localhostPaht+projectName);
	} catch (e) {
	}
    return "";
}	

function openSeller(ids)
		{
	var path = getRootPath();
			layer.open({
			title : "选择商品",
			type : 2,
			maxmin: true,
			//offset:  ['10px', '10px'],
			area : [ "820px", "100%" ],
			content : path+'/seller/sellerdate.action?type=select',
			btn: ['确定', '取消'],
   		 	yes: function(index, layero){
   		 	 var iframeWin = window[layero.find('iframe')[0]['name']];
   		 	 var idS = iframeWin.selectSeller();
   		 	 if(idS != '' && idS != undefined)
   		 	 {
   		 	 	var array = new Array();
   		 	 	array = idS.split(",");
   		 	 	var id = array[0];
   		 	 	var text = array[1];
   		 	 	$("#"+ids).append('<option value = "'+id+'" >'+text+'</option>');
   		    	$("#"+ids).val(id);
   		 	 	layer.close(index);
   		 	 }
   		 	 
   		 	//var dburl = iframeWin.getUrl();
       		 //按钮【按钮一】的回调
    		},cancel: function(index){
        	//按钮【按钮二】的回调
        	layer.close(index);
        	}
			});
		} 
