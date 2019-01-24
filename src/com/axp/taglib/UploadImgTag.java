/**
 * 
 */
package com.axp.taglib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Encoder;

import com.axp.model.UploadFile;
import com.axp.util.UploadFileUtil;


/*** 文件上传标签
 *<p>Title: </p>
 *<p>Description: </p> 
 *<p>Company: 广州爱小屏信息科技有限公司</p>
 * @author zhangpeng* @date 2015-6-13
 */
@SuppressWarnings("serial")
public class UploadImgTag extends TagSupport{
	private static final int WIDTH = 700;//平台宽度
	private static final int HEIGTH = 500;//平台高度
	private String name ;//input标签名
	private String corpWidth;//截取宽度
	private String corpHeight;//截取长度
	private String tableName;
	private String relatedIds;
	private String amount;
	private String asort;//显示位置
	private UploadFileUtil uploadFileUtil;
	public int doStartTag() {
		StringBuffer sb = new StringBuffer(2000);
		JspWriter out = pageContext.getOut();// 用pageContext获取out，他还能获取session等，基本上jsp的内置对象都能获取
		ServletRequest request = pageContext.getRequest();
		String url =  request.getScheme()+"://"+ request.getServerName()+":"+ request.getServerPort()+"/jupinhuiAI/";
		//String url =  request.getScheme()+"://"+ request.getServerName()+":"+ request.getServerPort()+"/axp/";
		try {
			//引入js,css
			sb.append("<link href='"+url+"css/upload_img/style.css' rel='stylesheet' type='text/css'>");
			sb.append("<script  type='text/javascript'   src='"+url+"js/upload_img/jquery-1.11.1.min.js' ></script>");
			sb.append("<script  type='text/javascript'   src='"+url+"js/upload_img/cropbox.js' ></script>");
			//最多提交个数
			if(StringUtils.isBlank(amount)){
				amount="";
			}
			sb.append("<input type='hidden' name='amount' id='amount' value='"+amount+"'>");
			//input
			sb.append("<input  type='hidden' name='"+name+"' id='imgData'>");
			//jsp流
			sb.append(getFileString("WEB-INF/views/taglib/upload_img_single.jsp"));
			//预览图片
			HttpServletRequest request2 = (HttpServletRequest) pageContext.getRequest();
			sb.append(getImgList(url,request2));
			//初始化截屏大小
			beforeGetWH();
			sb.append("<script type='text/javascript'> $(function(){initCrop("+getCorpWidth()+","+getCorpHeight()+");});  </script>");
			out.println(sb);
			//关闭资源
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;// 标签执行完后，继续执行后面的
	}
	 
	 /**得到图片集合
	 * @param dataName2
	 * @return
	 *@author zhangpeng
	 * @throws IOException 
	 *@time 2015-6-13
	 */
	private String getImgList(String url,HttpServletRequest request) throws IOException {
		if( StringUtils.isEmpty(relatedIds))
			return " </div> </div>";
		uploadFileUtil = new UploadFileUtil();
		List<UploadFile> list = uploadFileUtil.getUploadFiles(Integer.parseInt(relatedIds),tableName);
		StringBuffer sb = new StringBuffer(100);
		for (UploadFile uploadFile : list) {
			sb.append(" <img title='双击删除' ondblclick='del(this)' class='img_cropper' src='data:image/"+uploadFile.getType().substring(1)+";base64,"+getBase64Encoder(uploadFile.getUrl(),request)+"'>");
		}
		sb.append("</div> </div>"); 
		return sb.toString();
	}

	/**转64码
	 * @param url
	 * @return
	 *@author zhangpeng
	 * @throws IOException 
	 * @time 2015-6-13
	 */
	private String getBase64Encoder(String url,HttpServletRequest request) throws IOException {
		
		
		String savePath=request.getSession().getServletContext().getRealPath("");
		File  file = new File(savePath+url);
		if(!file.exists())
		{
			return null;
		}
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int)file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
	}

	/**得到指定目录的文件字符串形式
	 * @param url
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-13
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
	 public int doEndTag() {
	  return SKIP_BODY;// 标签执行完后，不继续执行后面的
	 }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCorpWidth() {
		return corpWidth;
	}
	public void setCorpWidth(String corpWidth) {
		this.corpWidth = corpWidth;
	}

	public String getCorpHeight() {
		return corpHeight;
	}
	/**
	 * 得到宽度和高度之前
	 *@author zhangpeng
	 *@time 2015-6-15
	 */
	private void beforeGetWH()
	{
		// 初始化
		if (corpWidth == null || corpWidth.equals(""))
			corpWidth = "200";
		if (corpHeight == null || corpHeight.equals(""))
			corpHeight = "200";
		double width = Integer.parseInt(corpWidth);
		double heigth = Integer.parseInt(corpHeight);
		// 如果宽度大于最大值，那么等比例缩小
		if (width > WIDTH || heigth > HEIGTH) {
			double b = Math.round(width/heigth*100)/100.0;// 得到比例
			//得到最大限度的 倍数
			int bs = 0;
			while (true) {
				if(bs * b > HEIGTH)
				{
					bs = bs -5;
					break;
				}
				bs += bs+5;
			}
			
			corpHeight = Double.valueOf(bs).toString();
			corpWidth = Double.valueOf(bs*b).toString();
		}
	}
	
	public void setCorpHeight(String corpHeight) {
	
		this.corpHeight = corpHeight;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRelatedIds() {
		return relatedIds;
	}

	public void setRelatedIds(String relatedIds) {
		this.relatedIds = relatedIds;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public static void main(String[] args) {
		UploadImgTag uploadImgTag = new UploadImgTag();
		uploadImgTag.setCorpHeight("380");
		uploadImgTag.setCorpWidth("660");
		uploadImgTag.beforeGetWH();
	}
}
