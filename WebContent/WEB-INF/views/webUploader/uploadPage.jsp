<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>web uploader</title>
<script type="text/javascript" src="../js/jquery.js"></script>

<link rel="stylesheet" type="text/css" href="../web-uploader/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="../web-uploader/css/diyUpload.css">
<script type="text/javascript" src="../web-uploader/js/wujquery.js"></script>
<script type="text/javascript" src="../web-uploader/js/webuploader.html5only.min.js"></script>
<script type="text/javascript" src="../web-uploader/js/diyUpload.js"></script>
</head>
<style>
*{ margin:0; padding:0;}
#box{ margin:15px auto; width:98%; min-height:500px; background:#ffffff}
.parentFileBox>.fileBoxUl>li>.viewThumb {
	position:absolute;
	top:0;
	left:0;
	width:100%;
	height:100%;
	overflow:hidden;
}
</style>
<body>
<div id="box">
	<div id="test" ></div>
</div>
<!-- 需要提交的相对url -->
<div id="uploadUrlDiv">

</div>
</body>
<script type="text/javascript">

/*
* 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
* 其他参数同WebUploader
*/
$('#test').diyUpload({
	fileSingleSizeLimit:2*1024*1024,
	fileNumLimit:'${amount}',
	threads:1,
	fileVal:"uploadFile",
	url:'${uploadBaseUrl}fileHandle/upload?dirName=${url}&currentUserId=${currentUserId}&targetExt=${targetExt}',
	thumb:{
		width: '${thumbW}',
	    height: '${thumbH}',
	},
	fileQueued:function(file){
		$('<input type="hidden" name="wu_input" id="'+file.id+'_input'+'" value=""/>').appendTo($('#uploadUrlDiv'));
		if('${thumbW}'){
			$($('.fileBoxUl')).children('li').css('width','${thumbW}');	
		}
		if('${thumbH}'){
			$($('.fileBoxUl')).children('li').css('height','${thumbH}');	
		}
	},
	fileDequeued:function(file){
		$('#'+file.id+'_input').remove();
	},
	success:function( file, data ) {
		$('#'+file.id+'_input').val(data.picPath);
	},
	error:function( err ) {
		$('#uploadUrlDiv').empty();
	},
	chunked:true,
	// 分片大小
	chunkSize:512 * 1024,
	//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
	fileNumLimit:50,
	fileSizeLimit:500000 * 1024,
	fileSingleSizeLimit:50000 * 1024,
	compress:false,
	accept: {}
});


function getFileList(){
	var value = "";
	var inputs =document.getElementsByName("wu_input");
	for(var i=0;i<inputs.length;i++){
		if(inputs[i].value!=null&&inputs[i].value!=''){
			value = value + 
			'<li><input type="hidden" name="${submitName}" value="'+inputs[i].value+'"/>' + 
			'<img src="${uploadBaseUrl}'+inputs[i].value+'" style="height:${thumbH};width: ${thumbW};" ondblclick="delImg(this);"/></li>';
		}
		
	}
	return value;
}


/*
$('#as').diyUpload({
	url:'server/fileupload.php',
	success:function( data ) {
		console.info( data );
	},
	error:function( err ) {
		console.info( err );	
	},
	buttonText : '选择文件',
	chunked:true,
	// 分片大小
	chunkSize:512 * 1024,
	//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
	fileNumLimit:50,
	fileSizeLimit:500000 * 1024,
	fileSingleSizeLimit:50000 * 1024,
	accept: {}
});*/
</script>

</html>
