var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if(source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
		source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor){
		for(var i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=highlightcolor;
		}
	}
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
	   return;
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor){
		//source.style.backgroundColor=originalcolor
		for(var i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}
}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
	   return;
	while(source.tagName!="TD")
	   source=source.parentElement;
	source=source.parentElement;
	cs  =  source.children;
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc"){
		for(var i=0;i<cs.length;i++){
			cs[i].style.backgroundColor=clickcolor;
		}
	}else{
		for(var i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}
}

//全选
function checkAll(){ 
	var code_Values = document.all['code_Value']; 
	if(code_Values.length){ 
	  for(var i=0;i<code_Values.length;i++){ 
		  code_Values[i].checked = true; 
	  } 
	}else{ 
	  code_Values.checked = true; 
	} 
}

//取消全选
function uncheckAll() { 
	var code_Values = document.all['code_Value']; 
	if(code_Values.length){ 
	  for(var i=0;i<code_Values.length;i++){ 
		  code_Values[i].checked = false; 
	  } 
	}else{ 
	  code_Values.checked = false; 
	}
}

//获取已勾选行id 以逗号分隔(1,2,3,4)
function getSelect(){
	var ids = "";
	var code_Values = document.getElementsByName('code_Value');
	for(var i=0;i<code_Values.length;i++){
		if(code_Values[i].checked == true){
			id = code_Values[i].value;
			ids+=id+",";
		}
	}
	ids = ids.substring(0,(ids.length-1));
	return ids;
}

function GetXmlHttpObject(){
	var xmlHttp=null;
	try{
		// Firefox, Opera 8.0+, Safari
		xmlHttp=new XMLHttpRequest();
	}catch (e){
		// Internet Explorer
		try{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}catch (e){
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xmlHttp;
}

//乘法
function accMul(arg1,arg2){ 
    var m=0,s1=arg1.toString(),s2=arg2.toString(); 
    try{m+=s1.split(".")[1].length;}catch(e){} 
    try{m+=s2.split(".")[1].length;}catch(e){} 
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}

//除法
function accDiv(arg1,arg2){ 
    var t1=0,t2=0,r1,r2; 
    try{t1=arg1.toString().split(".")[1].length;}catch(e){} 
    try{t2=arg2.toString().split(".")[1].length;}catch(e){} 
    with(Math){ 
      r1=Number(arg1.toString().replace(".",""));
      r2=Number(arg2.toString().replace(".",""));
      return (r1/r2)*pow(10,t2-t1); 
    } 
} 