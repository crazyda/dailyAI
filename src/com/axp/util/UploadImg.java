package com.axp.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.axp.dao.UploadFileDAO;
import com.axp.dao.impl.UploadFileDAOImpl;
import com.axp.model.UploadFile;



public class UploadImg {
	private  int width = 100;//缩略图宽度
	private  int height = 100;//缩略图高度
	private UploadFileDAO uploadFileDAO;
	private UploadFileUtil uploadFileUtil;
	public UploadImg()
	{
		uploadFileDAO = (UploadFileDAO) ToolSpring.getBean("uploadFileDAO");
	}
	public UploadImg(int width,int height)
	{
		this.width = width;
		this.height = height;
		uploadFileDAO = (UploadFileDAO) ToolSpring.getBean("uploadFileDAO");
	}
	 /**删除文件,及配置信息（isvaid false）
     * @param tableName 表名 
     * @param relatedId 关联id
     * @author zhangpeng
     * @time 2015-6-17
     */
    public  boolean updateUploadFalse(String tableName , int relatedId)
    {
    	try {
    		UploadFileDAO dao = new UploadFileDAOImpl();
    		List<UploadFile> uploadFiles = uploadFileUtil.getUploadFiles(relatedId,tableName);
    		for (UploadFile uploadFile : uploadFiles) 
    		{
    			//更新数据为false
    			uploadFile.setIsValid(false);
    			dao.update(uploadFile);
    		}
    		return true;
		} 
    	catch (Exception e) 
		{
			e.printStackTrace();
			return false ;
		}
    }
    /**删除文件,及配置信息
     * @param tableName 表名 
     * @param relatedId 关联id
     * @author zhangpeng
     * @time 2015-6-17
     */
    public  boolean delUpload(String tableName , int relatedId,HttpServletRequest request)
    {
    	try {
    		uploadFileUtil = new UploadFileUtil();
    		List<UploadFile> uploadFiles = uploadFileUtil.getUploadFiles(relatedId,tableName);
    		for (UploadFile uploadFile : uploadFiles) 
    		{
    			//删除文件
    			delFile(getSavePath(uploadFile.getUrl(),request)); 
    			delFile(getSavePath(uploadFile.getSmallUrl(),request));//缩略图
    			//删除数据
    			uploadFileDAO.delete(uploadFile);
    		}
    		return true;
		} 
    	catch (Exception e) 
		{
			e.printStackTrace();
			return false ;
		}
    }
	/**将64位编码的文件保存到服务器
     * @param type 文件类型
     * @param fileUrl 服务器上传的文件夹
     * @param base64 
     * @return
     *@author zhangpeng
     *@time 2015-6-12
     */
    public  String doUpload(String type,String fileUrl,String base64,HttpServletRequest request)
    {
    	String fileName = getDateNowFormFile(type);
    	String fileUrlToDb = "/"+fileUrl+"/"+fileName;
    	String resizeUrl = "/"+fileUrl+"/small/"+fileName.replace(type, ".jpg");
		//将上传的文件保存到服务器
		String savePath = getSavePath(fileUrlToDb,request);
		String resizePath = getSavePath(resizeUrl,request);
		//原图文件
		File file = createFile(savePath);
		File sFile = createFile(resizePath);
		//缩略图文件
    	try {
    		
    		BASE64Decoder decoder = new BASE64Decoder();
            //保存到服务器
    		FileOutputStream write = new FileOutputStream(file);
            byte[] decoderBytes = decoder.decodeBuffer(base64);
            write.write(decoderBytes);
            //保存缩略图
            ImageUtil.resizeByWidth(file, sFile,width, height);
            //关闭资源
            write.close();
            file = null;
            sFile = null;
		} catch (Exception e) {
			e.printStackTrace();
		} 
    	
    	return fileUrlToDb;
    }
    
    /**得到服务器原始路径
	 * @param fileUrlToDb
	 * @return
	 *@author zhangpeng
	 *@time 2015-6-15
	 */
	private static  String getSavePath(String fileUrlToDb,HttpServletRequest request) {
		//return ServletActionContext.getServletContext().getRealPath(fileUrlToDb);
		return request.getSession().getServletContext().getRealPath(fileUrlToDb);
	}
	/**文件上传
     * @param base64s
     * @param fileUrl 上传文件的相对路径
     * @param uploadFile 上传文件类
     *@author zhangpeng
     *@time 2015-6-12
     */
    public  void upload(String base64s,String fileUrl,UploadFile uploadFile,HttpServletRequest request)
    {
    	// 先重置该记录
		delUpload(uploadFile.getTableName(), uploadFile.getRelatedId(),request);
		/*
		 * 上传文件
		 */
    	String[] baseArray = base64s.split("@");//拆分
    	for (int i = 0; i < baseArray.length; i++) {
    		//data:image/png;base64,iVBORw0KGgoA
    		String str = baseArray[i];
    		//非64位转码
    		if(str.indexOf("data:") != -1)
    		{
    			//图片类型
        		String type = "."+str.substring(str.indexOf("/")+1, str.indexOf(";"));
        		//图片base64
        		String base64 = str.substring(str.indexOf(",")+1);
        		//数据库中文件相对路径
        		String fileUrlToDb = doUpload(type, fileUrl, base64,request);
        		//数据库操作
        		if(fileUrlToDb != null)
        		{
        			uploadFile.setIsValid(true);
        			uploadFile.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        			uploadFile.setUrl(fileUrlToDb);
        			
        			uploadFileDAO.save(uploadFile);
        		}
    		}
		}
    }
    
	/**文件上传
     * @param base64s
     * @param fileUrl 上传文件的相对路径
     * @param uploadFile 上传文件类
     *@author zhangpeng
     *@time 2015-6-12
     */
    public List<String> uploadForPath(String base64s,String fileUrl,UploadFile uploadFile,HttpServletRequest request)
    { 
    	List<String> list = new ArrayList<String>();
    	// 先重置该记录
		delUpload(uploadFile.getTableName(), uploadFile.getRelatedId(),request);
		/*
		 * 上传文件
		 */
    	String[] baseArray = base64s.split("@");//拆分
    	for (int i = 0; i < baseArray.length; i++) {
    		//data:image/png;base64,iVBORw0KGgoA
    		String str = baseArray[i];
    		//非64位转码
    		if(str.indexOf("data:") != -1)
    		{
    			//图片类型
        		String type = "."+str.substring(str.indexOf("/")+1, str.indexOf(";"));
        		//图片base64
        		String base64 = str.substring(str.indexOf(",")+1);
        		//数据库中文件相对路径
        		String fileUrlToDb = doUpload(type, fileUrl, base64,request);
        		list.add(fileUrlToDb);
        		//数据库操作
        		if(fileUrlToDb != null)
        		{
        			uploadFile.setIsValid(true);
        			uploadFile.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
        			uploadFile.setUrl(fileUrlToDb);
        			uploadFile.setType(type);
        			uploadFileDAO.save(uploadFile);
        		}
    		}
		}
    	return list;
    }
    
    /**
	 * 
	 *@author zhangpeng
	 *@time 2015-6-13
	 */
	private static   void delFile(String url) {
		File file = new File(url);
			file.delete();
	}
	/**创建文件
	 * @param url
	 *@author zhangpeng
	 *@time 2015-6-16
	 */
	private File createFile(String url)
	{
		File file = new File(url);
		File parent = file.getParentFile(); 
		if(parent != null && !parent.exists()){ 
		parent.mkdirs(); 
		} 
		return file;
	}
	public static void main(String[] args) {
		
		
		
	}
    /**上传文件
     * @param file
     * @return
     * @throws IOException
     *@author zhangpeng
     *@time 2015-6-12
     */
    public String doUpload(File file) throws IOException{
		if(file == null )
		{
			return "";
		}
		String fileUrlToDb = "/goods/"+getDateNowFormFile(".jpg");
		//将上传的文件保存到服务器
		String savePath=ServletActionContext.getServletContext().getRealPath(fileUrlToDb);
			FileInputStream fis=new FileInputStream(file);
			FileOutputStream fos=new FileOutputStream(savePath);
			IOUtils.copy(fis, fos);
			fos.flush();
			fos.close();
			fis.close();
		return fileUrlToDb.substring(1);
	}
    public void testBase64Encoder(){
        BASE64Encoder encoder = new BASE64Encoder();
        
        try {
            StringBuilder pictureBuffer = new StringBuilder();
            InputStream input = new FileInputStream(new File("D:\\test\\ee.jpg"));
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            for(int len = input.read(temp); len != -1;len = input.read(temp)){
                out.write(temp, 0, len);
                pictureBuffer.append(encoder.encode(out.toByteArray()));
                //out(pictureBuffer.toString());
                out.reset();
            }
            
       /* //pictureBuffer = 
        BASE64Decoder decoder = new BASE64Decoder();
        FileOutputStream write = new FileOutputStream(new File("c:/test3.jpg"));
        byte[] decoderBytes = decoder.decodeBuffer("");
        write.write(decoderBytes);*/
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /**得到文件名
	 * @param format
	 * @return
	 */
	public static String getDateNowFormFile(String type)
	{
		Calendar calendar = Calendar.getInstance();// 日历对象
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		Random random = new Random();
		String date = df.format(calendar.getTime());
		return date+random.nextInt(1000)+type;
	}

}