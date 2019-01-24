
<div>
	<input type="button" onclick="openSeller()" value="上传图片"/>
	<ul></ul>
</div>
<script type="text/javascript" src="../../js/jquery-2.1.0.js"></script>
<script type="text/javascript" src="../../js/layer-v2.0/layer/layer.js"></script>
<script type="text/javascript">
	
		 function openSeller()
		{
			var ub = event.srcElement;
			layer.open({
			title : "选择文件",
			type : 2,
			maxmin: true,
			//offset:  ['10px', '10px'],
			area : [ "750px", "70%" ],
			content : "http://localhost:8080/daily_ref/imagehandle/test?amountLimit=5&uploadPath=rrrrrrrr"+$(ub).parent().children('#submitName'),
			btn: ['确定', '取消'],
   		 	yes: function(index, layero){
   		 	 var iframeWin = window[layero.find('iframe')[0]['name']];
   		 	 var values = iframeWin.getFileList();
   		 	 if(values != '' && values != undefined)
   		 	 {
   		 		$(ub).parent().children('ul').append(values);
   		 	 }
   		 	layer.close(index);
   		 	//var dburl = iframeWin.getUrl();
       		 //按钮【按钮一】的回调
    		},cancel: function(index){
        	//按钮【按钮二】的回调
        	layer.close(index);
        	}
			});
		} 
		 
		function delImg(img){
			$(img).parent().remove();
		}

</script>

