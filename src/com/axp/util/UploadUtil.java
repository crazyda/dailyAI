package com.axp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;

public class UploadUtil extends TagSupport {
	private String id;
	private String targetExt="";//保存格式限制
	private String amount="";//对多上传文件个数
	private String thumbW="110";//缩略图宽
	private String thumbH="110";//缩略图高
	private String submitName = "uploadImgUrl";//图片表单名
	private String fileDirName="";//路径名称
	private String relatedClass="";//关联表（备用）
	private String relatedId="";//关联id（备用）
	private String relatedChar="";//资源json
	private String isLocal="false";//是否上传到本地
	public int doStartTag() {
		JspWriter out = pageContext.getOut();// 用pageContext获取out，他还能获取session等，基本上jsp的内置对象都能获取
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		String url =  request.getServletContext().getAttribute("BASEPATH_IN_SESSION").toString();
		String uploadBaseUrl;
		if(isLocal.equals("false")){
			uploadBaseUrl = request.getServletContext().getAttribute("RESOURCE_LOCAL_URL").toString();
		}else{
			uploadBaseUrl = request.getServletContext().getAttribute("BASEPATH_IN_SESSION").toString();
		}
		try {
			String asda = "<div id=\"uploadFileBox\">"+
				"<input type=\"button\" class=\"button\" onclick=\"openSeller(this)\" value=\"上传图片\"/>"+
				"<ul>"+getImgList(uploadBaseUrl,request,submitName)+"</ul>"+
				"<input type=\"hidden\" id=\"submitName\" value=\""+submitName+"\"/>"+
				"<input type=\"hidden\" id=\"thumbW\" value=\""+thumbW+"\"/>"+
				"<input type=\"hidden\" id=\"thumbH\" value=\""+thumbH+"\"/>"+
				"<input type=\"hidden\" id=\"fileDirName\" value=\""+fileDirName+"\"/>"+
				"<input type=\"hidden\" id=\"amount\" value=\""+amount+"\"/>"+
				"<input type=\"hidden\" id=\"targetExt\" value=\""+targetExt+"\"/>"+
			"</div>";
			if(!fileDirName.equals("teamPic")){
				asda+= "<script type=\"text/javascript\" src=\"../js/jquery-2.1.0.js\"></script>"+
			"<script type=\"text/javascript\" src=\"../js/layer-v2.0/layer/layer.js\"></script>";
			}
			asda+= "<script type=\"text/javascript\">"+
	
		 "function openSeller(ub){"+
			"layer.open({"+
			"title : \"选择文件\","+
			"type : 2,"+
			"maxmin: true,"+
			"area : [ \"750px\", \"70%\" ],"+
			"content : \""+url+"fileHandle/test?" +
				"&uploadBaseUrl="+uploadBaseUrl +
				"&submitName=\"+$(ub).parent().children('#submitName').val()+\"" +
				"&thumbW=\"+$(ub).parent().children('#thumbW').val()+\"" +
				"&thumbH=\"+$(ub).parent().children('#thumbH').val()+\"" +
				"&amountLimit=\"+$(ub).parent().children('#amount').val()+\"" +
				"&uploadPath="+fileDirName+"\","+
			"btn: ['确定', '取消'],"+
   		 	"yes: function(index, layero){"+
   		 	 "var iframeWin = window[layero.find('iframe')[0]['name']];"+
   		 	 "var values = iframeWin.getFileList();"+
   		 	 "if(values != '' && values != undefined){"+
   		 	"	$(ub).parent().children('ul').append(values);"+
   		 	" }" +
   		 	"layer.close(index);"+
       		 //按钮【按钮一】的回调
    		"},cancel: function(index){"+
        	//按钮【按钮二】的回调
        	"layer.close(index);"+
        	"}"+
			"});"+
		"} "+
			"function delImg(img){$(img).parent().remove();}"+
		"</script>";
			//sb.append(getFileString("WEB-INF/views/webUploader/uploadIndex.jsp"));
			out.println(asda);
			//关闭资源
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;// 标签执行完后，继续执行后面的
	}
	
	 /**得到图片集合
	 * @param dataName2
	 * @return
	 * @throws IOException 
	 *@time 2015-6-13
	 */
	private String getImgList(String url,HttpServletRequest request,String submitName) throws IOException {
		StringBuffer sb = new StringBuffer();
		if(!StringUtils.isEmpty(relatedChar)&&relatedChar.startsWith("[")){
			sb.setLength(0);
			JSONArray array = JSONArray.parseArray(relatedChar.toString());
			for(int i=0;i<array.size();i++){
				String fileUrl = array.getJSONObject(i).getString("imgUrl");
				sb.append("<li><input type=\"hidden\" name=\""+submitName+"\" value=\""+fileUrl+"\"/>");
				sb.append("<img src=\""+url+fileUrl+"\" style=\"height:100;width:100;\" ondblclick=\"delImg(this);\" /></li>");
			}
		}
		return sb.toString();
	}
	
	/**得到指定目录的文件字符串形式
	 * @param url
	 * @return
	 */
	private String getFileString (String url)
	{
		String urls = pageContext.getRequest().getRealPath(url);
		File file = new File(urls);
		StringBuffer sb = new StringBuffer(1000);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				sb.append("\n" + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = "";
		try {
			result = new String(sb.toString().getBytes(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			result = sb.toString();
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getFileDirName() {
		return fileDirName;
	}
	public void setFileDirName(String fileDirName) {
		this.fileDirName = fileDirName;
	}
	public String getRelatedId() {
		return relatedId;
	}
	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRelatedClass() {
		return relatedClass;
	}
	public void setRelatedClass(String relatedClass) {
		this.relatedClass = relatedClass;
	}

	public String getRelatedChar() {
		return relatedChar;
	}

	public void setRelatedChar(String relatedChar) {
		this.relatedChar = relatedChar;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getThumbW() {
		return thumbW;
	}

	public void setThumbW(String thumbW) {
		this.thumbW = thumbW;
	}

	public String getThumbH() {
		return thumbH;
	}

	public void setThumbH(String thumbH) {
		this.thumbH = thumbH;
	}

	public String getSubmitName() {
		return submitName;
	}

	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}

	public String getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(String isLocal) {
		this.isLocal = isLocal;
	}

	public String getTargetExt() {
		return targetExt;
	}

	public void setTargetExt(String targetExt) {
		this.targetExt = targetExt;
	}
	
	
}