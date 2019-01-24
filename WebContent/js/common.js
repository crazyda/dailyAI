var contextPath="SutienSMS/";
function isMobilephone(mobilephone){
	var myreg = /^(((13[0-9]{1})|159|(15[0-9]{1}))+\d{8})$/;
    if(!myreg.test(mobilephone)){
       return false;
    }
    return true;
}


function isChinese(_str) {
	return /^[\u4E00-\u9FA5]{0,25}$/.test(_str);
}
function isEnglish(_str) {
	return /^[A-Za-z]{0,25}$/.test(_str);
}
function isNumber(_str) {
	return /^[-+]?[\d|\.|,]+$/.test(_str);
}
function isDate(_str) {
	return /^\d{2}\/\d{2}\/\d{4}$/.test(_str);
}
function isTime(_str) {
	return /^\d{2}\/\d{2}\/\d{4}\s\d{2}:\d{2}:\d{2}$/.test(_str);
}

function isIllegalUserName(str){
	
	var reg = /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{2,16}$/; 
	return reg.test(str); 

}
function isEmail(_str) {
	return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/
			.test(_str);
}

function CheckInputWordCount(){
       
       var v = document.getElementById("content").value;
       var len =  v.length;
       //alert('len ======'+len);
       document.getElementById("fontLeaved").innerHTML = len;
       var count =0;
       if(len>0){
    	   count=1;
       }
       if(len>70){
    	   var m = len % 70;
    	   if(m==0){
    		   count = len / 70;
    	   }else{
    		   count = len / 70;
    		   count ++;
    	   }
       }
       count = parseInt(count,10);
       document.getElementById("phone_count").innerHTML = count;
 }


var pop;
function ShowIframe(title,url,width,height)
{
     pop=new Popup({ contentType:1,scrollType:'no',isReloadOnClose:false,width:width,height:height});
     pop.setContent("contentUrl",url);
     pop.setContent("title",title);
     
     pop.build();
     pop.show();
}

function ShowIframe_scroll(title,url,width,height)
{
     pop=new Popup({ contentType:1,scrollType:'auto',isReloadOnClose:false,width:width,height:height});
     pop.setContent("contentUrl",url);
     pop.setContent("title",title);
     
     pop.build();
     pop.show();
}

function ShowIframe_scroll(title,url,width,height)
{
     pop=new Popup({ contentType:1,scrollType:'yes',isReloadOnClose:false,width:width,height:height});
     pop.setContent("contentUrl",url);
     pop.setContent("title",title);
     
     pop.build();x
     pop.show();
}



function ShowConfirm_relative(title,content,width,height,type,left,top)
{
   var pop=new Pop_nocover({ contentType:3,isReloadOnClose:false,width:width,height:height,controlLeft:left,controlTop:top});
   pop.setContent("title",title);
   pop.setContent("confirmCon",content);
   pop.setContent("callBack",ShowCallBack_confirm);
   pop.setContent("parameter",{type:type,obj:pop});
   pop.build();
   pop.show();
}

function ShowConfirm(title,content,width,height,type)
{
   var pop=new Popup({ contentType:3,isReloadOnClose:false,width:width,height:height});
   pop.setContent("title",title);
   pop.setContent("confirmCon",content);
   pop.setContent("callBack",ShowCallBack_confirm);
   pop.setContent("parameter",{type:type,obj:pop});
   pop.build();
   pop.show();
}

function ShowIframe_reload(title,url,width,height)
{
   pop=new Popup({ contentType:1,scrollType:'no',isReloadOnClose:true,width:width,height:height});
   pop.setContent("contentUrl",url);
   pop.setContent("title",title);
   pop.build();
   pop.show();
}
function ShowIframe_relative(title,url,width,height,left,top)
{
     pop=new Pop_nocover({ contentType:1,scrollType:'no',isReloadOnClose:false,width:width,height:height,controlLeft:left,controlTop:top});
     pop.setContent("contentUrl",url);
     pop.setContent("title",title);
     pop.build();
     pop.show();
}
function   getAbsolutePos(el)   { 
	var   r   =   {   x:   el.offsetLeft,   y:   el.offsetTop   }; 
	if   (el.offsetParent)   { 
		var   tmp   =   getAbsolutePos(el.offsetParent); 
		r.x   +=   tmp.x; 
		r.y   +=   tmp.y; 
	} 
	return   r; 
}
function ShowCallBack_confirm(para){
    var o_pop = para["obj"]
    o_pop.close();
}
function ShowAlert_relative(title,content,width,height,left,top)
{
    var pop=new Pop_nocover({ contentType:4,isReloadOnClose:false,width:width,height:height,controlLeft:left,controlTop:top});
    pop.setContent("title",title);
    pop.setContent("alertCon",content);
    pop.build();
    pop.show();
}
function getCookie(name) {
	var start = document.cookie.indexOf( name + "=" );
	var len = start + name.length + 1;     
	if ( ( !start ) && (name != document.cookie.substring( 0, name.length ) ) ) {
		return null;     
	}     
	if ( start == -1 ) 
		return null;     
	var end = document.cookie.indexOf( ';', len );     
	if ( end == -1 ) 
		end = document.cookie.length;     
	return unescape( document.cookie.substring( len, end ) ); 
}
function setCookie( name, value, expires, path, domain, secure ) {
	var today = new Date();     
	today.setTime( today.getTime() );     
	if ( expires ) {         
		expires = expires * 1000 * 60 * 60 * 24;     
	}     
	var expires_date = new Date( today.getTime() + (expires) );     
	document.cookie = name+'='+escape( value ) + ( ( expires ) ? ';expires='+expires_date.toGMTString() : '' )+ 
	( ( path ) ? ';path=' + path : '' ) +         
	( ( domain ) ? ';domain=' + domain : '' ) +         
	( ( secure ) ? ';secure' : '' ); 
}
function deleteCookie( name, path, domain ) {
	
	if ( getCookie( name ) ) 
		document.cookie = name + '=' +             
		( ( path ) ? ';path=' + path : '') +             
		( ( domain ) ? ';domain=' + domain : '' ) +             
		';expires=Thu, 01-Jan-1970 00:00:01 GMT';
}
function showRelatiaveDialog(obj,content){
	var v = getAbsolutePos(obj);
    var len = content.length;
    if(len<=40){
       ShowAlert_relative('查看详细内容',content,250,100,v.x, v.y+20);
    }else if(len >40 && len <=70){
       ShowAlert_relative('查看详细内容',content,250,120,v.x, v.y+20);
    }else if(len >70 && len <210){
       ShowAlert_relative('查看详细内容',content,350,150,v.x, v.y+20);
    }else if(len>210){
    	ShowAlert_relative('查看详细内容',content,550,250,v.x, v.y+20);
    }
}


function studentReward(id){
	  parent.studentDetail_reward(id);
}

function studentChar(id){
	  parent.studentDetail_char(id);
}

function studentScore(id){
	  parent.studentDetail_score(id);
}

function studentExp(id){
	parent.studentDetail_expr(id);
}