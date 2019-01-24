/**
 * 
 */
package com.axp.taglib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



/*** 文件上传标签
 *<p>Title: </p>
 *<p>Description: </p> 
 *<p>Company: 广州爱小屏信息科技有限公司</p>
 * @author hzc* @date 2015-6-13
 */
@SuppressWarnings("serial")
public class VIPMallCentTag extends TagSupport{
	private String type ;//input标签名
	public int doStartTag() {
		StringBuffer sb = new StringBuffer();
		JspWriter out = pageContext.getOut();// 用pageContext获取out，他还能获取session等，基本上jsp的内置对象都能获取
		ServletRequest request = pageContext.getRequest();
		String url =  request.getScheme()+"://"+ request.getServerName()+":"+ request.getServerPort()+"/jupinhui/";
		try {
			//jsp流
			if("normal".equals(type)){
				sb.append("<link href=\""+url+"css/css.css\" rel=\"stylesheet\" type=\"text/css\">");
				sb.append("<link href=\""+url+"css/add/css/global.css\" rel=\"stylesheet\" type=\"text/css\">");
				sb.append("<link href=\""+url+"css/add/css/main.css\" rel=\"stylesheet\" type=\"text/css\">");
				sb.append("<link href=\""+url+"css/add/css/shop.css\" rel=\"stylesheet\" type=\"text/css\">");
				sb.append(getFileString("WEB-INF/taglib/vipmall_cent.jsp"));
			}
			out.println(sb);
			//关闭资源
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;// 标签执行完后，继续执行后面的
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
