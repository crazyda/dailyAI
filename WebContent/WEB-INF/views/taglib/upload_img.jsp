 <style>
        .container
        {
            top: 5%; left: 36%; right: 0; bottom: 0;
        }
        .action
        {
            width: 400px;
            height: 30px;
            margin: 10px 0;
        }
        .cropped>img
        {
            margin-right: 10px;
        }
    </style>
<script type="text/javascript">
    $(window).load(function() {
        var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: '../../img/neiye-beijing.png'
        }
        var cropper = $('.imageBox').cropbox(options);
        $('#file').on('change', function(){
            var reader = new FileReader();
            reader.onload = function(e) {
                options.imgSrc = e.target.result;
                cropper = $('.imageBox').cropbox(options);
            }
            reader.readAsDataURL(this.files[0]);
            this.files = [];
        });
    
        $('#btnCrop').on('click', function(){
        	var isLimit = limit();
        	if(!isLimit){
        		return;
        	}
            var img = cropper.getDataURL();
            $('.cropped').append('<img title="双击删除" ondblclick="del(this)" class="img_cropper" src="'+img+'">');
            submit0();
        })
        $('#btnZoomIn').on('click', function(){
            cropper.zoomIn();
        })
        $('#btnZoomOut').on('click', function(){
            cropper.zoomOut();
        });
    });
    
	/**设置截屏大小*/
	function setHW()
	{
	$(".thumbBox").css("height",$("#height").val());
	$(".thumbBox").css("width",$("#width").val());
	$(".thumbBox").css("margin-left",-100-(parseFloat($("#width").val())-200)/2);
	$(".thumbBox").css("margin-top",-100-(parseFloat($("#height").val())-200)/2);
	}
	
	function initCrop(w,h)
	{
		$("#width").val(w);
		$("#height").val(h);
		setHW();
	}
	/**提交拼接图片数据*/
	
	function submit0()
	{
		var base64Data = "";
	       $('.img_cropper').each(function(){
		       var url = $(this).attr("src");
		       if(base64Data == "")
		       {
		       		base64Data = url;
		       }else
		       {
		       		base64Data += '@'+url;
		       }
		    	});
   			$("#imgData").val(base64Data);
	}
	  //删除图片
	function del(t){
		$(t).remove();
		submit0();
	}
	 //验证提交数量是否超出限制
	function limit(){
		var amount = $("#amount").val();
		if(''==amount){
			return true;
		}
		var imgCount = $("#cropped_div").children("img").length;
		if(imgCount>=amount){
			alert("提交数量超出限制!");
			return false;
		}
		return true;
	}
	$(function(){
		submit0();
	});
</script>
<div class="container">
					<div  style="margin-right: 0px;margin-top: 100px; float: right; width: 18%">
       			 	 <span>x: </span><input id = "width" type="text" width="40px;" size="5" value="480"/>px
       				 <br>
       				<span>y: </span><input id = "height" type="text" size="5" value="210"/>px
       				<br>
       				 <input type="button" id="btnZoomSet" onclick="setHW();" value="设定尺寸" >
        			</div>
    <div class="imageBox" style="float:left; width: 80%">
        <div class="thumbBox"></div>
        <div class="spinner" style="display: none">Loading...</div>
    </div>
    <div class="action">
        <input type="file" id="file" style="float:left; width: 50%"/>
        <input type="button" id="btnCrop" value="提交"  style="float: right; margin-left: 5px;">
        <input type="button" id="btnZoomIn" value="放大" style="float: right;margin-left: 5px;">
        <input type="button" id="btnZoomOut" value="缩小" style="float: right; margin-left: 5px;">
    </div>
    <div style="margin-top: 400px;">
     <span>预览图片</span>
    </div>
    <div id="cropped_div" class="cropped">
