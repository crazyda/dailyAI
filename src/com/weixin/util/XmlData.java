package com.weixin.util;

public class XmlData{
	
	private StringBuffer buffer;
	
	public XmlData(){
		buffer = new StringBuffer();
		buffer.append("<xml>");
	}
	
	public void setData(String dataName,Object object){
		buffer.append("<"+dataName+">"+object.toString()+"</"+dataName+">");
	}
	
	public void setProtectData(String dataName,Object object){
		buffer.append("<"+dataName+"><![CDATA["+object.toString()+"]]></"+dataName+">");
	}
	
	public String putout(){
		buffer.append("</xml>");
		return buffer.toString();
	}
}