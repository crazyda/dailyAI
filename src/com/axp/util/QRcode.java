package com.axp.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRcode {

	 private static final int BLACK = 0xFF000000;
	 private static final int WHITE = 0xFFFFFFFF;


	 public QRcode() {
		 
	 }
	 
	 
	  private static BufferedImage toBufferedImage(BitMatrix matrix) {
	        int width =matrix.getWidth();
	        int height =matrix.getHeight();
	        BufferedImage image = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x,y, matrix.get(x,y) ? BLACK :WHITE);
	            }
	        }
	        return image;
	    }

	    private static void writeToFile(BitMatrix matrix, String format, File file)
	            throws IOException {
	        BufferedImage image = toBufferedImage(matrix);
	        if (!ImageIO.write(image,format, file)) {
	            throw new IOException("Could not write an image of format "
	                    + format + " to " + file);
	        }
	    }
	    /**
	     * @Title: 构造图片
	     * @Description: 生成水印并返回java.awt.image.BufferedImage
	     * @param file源文件(图片)
	     * @param waterFile水印文件(图片)
	     * @param x距离右下角的X偏移量
	     * @param y 距离右下角的Y偏移量
	     * @param alpha 透明度, 选择值从0.0~1.0: 完全透明~完全不透明
	     * @return BufferedImage
	     * @throws IOException
	     */
	    private static BufferedImage watermark(File file, File waterFile,int x, int y, float alpha) throws IOException {
	        // 获取底图
	        BufferedImage buffImg = ImageIO.read(file);
	        // 获取层图
	        BufferedImage waterImg = ImageIO.read(waterFile);
	        // 创建Graphics2D对象，用在底图对象上绘图
	        Graphics2D g2d = buffImg.createGraphics();
	        int waterImgWidth =waterImg.getWidth();// 获取层图的宽度
	        int waterImgHeight =waterImg.getHeight();// 获取层图的高度
	        // 在图形和图像中实现混合和透明效果
	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
	        // 绘制
	        g2d.drawImage(waterImg,x, y, waterImgWidth, waterImgHeight,null);
	        g2d.dispose();// 释放图形上下文使用的系统资源
	        return buffImg;
	    }

	    /**
	     * 输出水印图片
	     * 
	     * @param buffImg 图像加水印之后的BufferedImage对象
	     * @param savePath 图像加水印之后的保存路径
	     */
	    private void generateWaterFile(BufferedImage buffImg, String savePath) {
	        int temp =savePath.lastIndexOf(".") + 1;
	        try {
	            ImageIO.write(buffImg, savePath.substring(temp), new File(savePath));
	        } catch (java.io.IOException e1) {
	            e1.printStackTrace();
	        }
	    }

	    /**
	     * 
	     * @param text 二维码内容
	     * @param width 二维码图片宽度
	     * @param height 二维码图片高度
	     * @param format 二维码的图片格式
	     * @param sourceFilePath 底层图片路径
	     * @param waterFilePath 二维码路径
	     * @param saveFilePath 合成图片路径
	     * @param maginx  二维码距离底图x轴距离
	     * @param maginy  二维码距离底图y轴距离
	     * @throws Exception
	     */
	    public void addImageQRcode(String text,int width,int height,String format, 
	    String sourceFilePath,String waterFilePath, String saveFilePath,int maginx,int maginy)throws Exception{
	     
		    Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		    hints.put(EncodeHintType.CHARACTER_SET,"utf-8"); // 内容所使用字符集编码
		     
		    BitMatrix bitMatrix =new MultiFormatWriter().encode(text,
		    BarcodeFormat.QR_CODE,width, height,hints);
		    // 生成二维码
		    File outputFile = new File("/Users/chenfenqian/Desktop" + File.separator +"new.jpg");
		    QRcode.writeToFile(bitMatrix,format, outputFile);
		     
		    QRcode newImageUtils = new QRcode();
		    // 构建叠加层
		    BufferedImage buffImg = QRcode.watermark(new File(sourceFilePath),new File(waterFilePath),maginx, maginy, 1.0f);
		    // 输出水印图片
		    newImageUtils.generateWaterFile(buffImg,saveFilePath);
	    }
	    
	    
	    
	    /**
	     * @param name 二维码图片名
	     * @param text 二维码内容
	     * @param width 二维码图片宽度
	     * @param height 二维码图片高度
	     * @param format 二维码的图片格式
	     * @param waterFilePath 二维码路径
	     * @throws Exception
	     */
	    public void addImageQRcode(String name,String text,int width,int height,String format, String waterFilePath)throws Exception{
		    Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		    hints.put(EncodeHintType.CHARACTER_SET,"utf-8"); // 内容所使用字符集编码
		     
		    BitMatrix bitMatrix =new MultiFormatWriter().encode(text,
		    BarcodeFormat.QR_CODE,width, height,hints);
		    // 生成二维码
		    File file = new File(waterFilePath);
		    if (!file.exists()) {
		    	file.mkdirs();
			}

		    file = new File(waterFilePath + File.separator +name);
		    
		    QRcode.writeToFile(bitMatrix,format, file);
		     
	    }
	    
	    
	    
	    public static void main(String[] args) throws Exception{
			QRcode rcode = new QRcode();
			/*ImageUploadUtil imageUploadUtil = new ImageUploadUtil();
			imageUploadUtil.uploadimg();
			
			String path = "F:/QRcode";*/
			//rcode.addImageQRcode("code.png","123456789", 400, 400, "png", path);
			rcode.addImageQRcode("1.png","123456789", 400, 400, "png", "F:/QRcode/new.jpg");
			
			
			
		}
	    
	    
}
	    
	    
