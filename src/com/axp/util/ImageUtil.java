package com.axp.util;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * 图片处理公共类。包括生成缩略图
 * 
 * @author Administrator
 * 
 */
public class ImageUtil {
	private static Component component = new Canvas();
	private static int width;

	private static int height;

	private static int scaleWidth;

	static double support = (double) 3.0;

	static double PI = (double) 3.14159265358978;

	static double[] contrib;

	static double[] normContrib;

	static double[] tmpContrib;

	static int startContrib, stopContrib;

	static int nDots;

	static int nHalfDots;

	
	public  void resizeImage(String srcImgPath, String distImgPath,  
            int width, int height) throws IOException {  
  
        File srcFile = new File(srcImgPath);  
        Image srcImg = ImageIO.read(srcFile);  
        BufferedImage buffImg = null;  
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        buffImg.getGraphics().drawImage(  
                srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,  
                0, null);  
  
        ImageIO.write(buffImg, "JPEG", new File(distImgPath));  
  
    } 
	
	public static void getImges(String destUrl,String bgUrl,String outUrl){
		 try {
			  
			 File fileTwo = new File(bgUrl);
			 BufferedImage ImageTwo = ImageIO.read(fileTwo);
			
			 int width2 = ImageTwo.getWidth();// 图片宽度
			   int height2 = ImageTwo.getHeight();// 图片高度
			 
			   HttpURLConnection httpUrl = null;  
		    	 URL url = null;
		    	 url = new URL(destUrl);  
		    	 httpUrl = (HttpURLConnection) url.openConnection();  
		    	 httpUrl.connect();  
			  // BufferedImage ImageOne = ImageIO.read(fileOne);
		    	 BufferedImage ImageOne = ImageIO.read(httpUrl.getInputStream());
		    	 
		    	 BufferedImage buffImg = null;  
			     buffImg = new BufferedImage(width2/2, height2, BufferedImage.TYPE_INT_RGB);  
			     buffImg.getGraphics().drawImage(  
			        		ImageOne.getScaledInstance(width2/2, height2, Image.SCALE_SMOOTH), 0,  
			                0, null); 
		    	 
			   int width = buffImg.getWidth();// 图片宽度
			   int height = buffImg.getHeight();// 图片高度
			   
			    int[] ImageArrayOne = new int[width * height];
			    ImageArrayOne = buffImg.getRGB(0, 0, width, height, ImageArrayOne,0, width);
			    
			  
			   
			   int[] ImageArrayTwo = new int[width2 * height2];
			   ImageArrayTwo = ImageTwo.getRGB(0, 0, width2, height2, ImageArrayTwo,0, width2);
			   
			   BufferedImage ImageNew = new BufferedImage(width2, height2,BufferedImage.TYPE_INT_RGB);
			   
			   ImageNew.setRGB(0, 0, width2, height2, ImageArrayTwo, 0, width2);
			   ImageNew.setRGB(0, 0, width, height, ImageArrayOne, 0, width);
			   File outFile = new File(outUrl);
			   ImageIO.write(ImageNew, "jpg", outFile);// 写图片
			   }catch (Exception e) {
			      e.printStackTrace();
			   }
	}
	
	public static void saveToFile(String destUrl,String output) {  
	
   	 FileOutputStream fos = null;  
   	 BufferedInputStream bis = null;  
   	 HttpURLConnection httpUrl = null;  
   	 URL url = null;  
   	 int BUFFER_SIZE = 1024;  
   	 byte[] buf = new byte[BUFFER_SIZE];  
   	 int size = 0;  
   	 try {  
   	 url = new URL(destUrl);  
   	 httpUrl = (HttpURLConnection) url.openConnection();  
   	 httpUrl.connect();  
   	 bis = new BufferedInputStream(httpUrl.getInputStream());  
   	 fos = new FileOutputStream(output);  
   	 while ((size = bis.read(buf)) != -1) {  
   	 fos.write(buf, 0, size);  
   	 }  
   	 fos.flush();  
   	 } catch (IOException e) {  
   	 } catch (ClassCastException e) {  
   	 } finally {  
   	 try {  
   	 fos.close();  
   	 bis.close();  
   	 httpUrl.disconnect();  
   	 } catch (IOException e) {  
   	 } catch (NullPointerException e) {  
   	 }  
   	 }  
   	 }  
	
	/**
	 * 
	 * @param fromFileStr
	 *            原图片地址
	 * @param saveToFileStr
	 *            生成缩略图地址
	 * @param formatWideth
	 *            生成图片宽度
	 * @param formatHeight
	 *            formatHeight高度
	 */
	public static void saveImageAsJpg(File fromFile, File saveFile,
			int formatWideth, int formatHeight) throws Exception {
		BufferedImage srcImage;

		srcImage = javax.imageio.ImageIO.read(fromFile); // construct image
		int imageWideth = srcImage.getWidth(null);
		int imageHeight = srcImage.getHeight(null);
		int changeToWideth = 0;
		int changeToHeight = 0;
		if (imageWideth > 0 && imageHeight > 0) {
			// flag=true;
			if (imageWideth / imageHeight >= formatWideth / formatHeight) {
				if (imageWideth > formatWideth) {
					changeToWideth = formatWideth;
					changeToHeight = (imageHeight * formatWideth) / imageWideth;
				} else {
					changeToWideth = imageWideth;
					changeToHeight = imageHeight;
				}
			} else {
				if (imageHeight > formatHeight) {
					changeToHeight = formatHeight;
					changeToWideth = (imageWideth * formatHeight) / imageHeight;
				} else {
					changeToWideth = imageWideth;
					changeToHeight = imageHeight;
				}
			}
		}

		srcImage = imageZoomOut(srcImage, changeToWideth, changeToHeight);
		ImageIO.write(srcImage, "JPEG", saveFile);
	}

	public static BufferedImage imageZoomOut(BufferedImage srcBufferImage,
			int w, int h) {
		width = srcBufferImage.getWidth();
		height = srcBufferImage.getHeight();
		scaleWidth = w;

		if (DetermineResultSize(w, h) == 1) {
			return srcBufferImage;
		}
		CalContrib();
		BufferedImage pbOut = HorizontalFiltering(srcBufferImage, w);
		BufferedImage pbFinalOut = VerticalFiltering(pbOut, h);
		return pbFinalOut;
	}

	/**
	 * 决定图像尺寸
	 */
	private static int DetermineResultSize(int w, int h) {
		double scaleH, scaleV;
		scaleH = (double) w / (double) width;
		scaleV = (double) h / (double) height;
		// 需要判断一下scaleH，scaleV，不做放大操作
		if (scaleH >= 1.0 && scaleV >= 1.0) {
			return 1;
		}
		return 0;

	} // end of DetermineResultSize()

	private static double Lanczos(int i, int inWidth, int outWidth,
			double Support) {
		double x;

		x = (double) i * (double) outWidth / (double) inWidth;

		return Math.sin(x * PI) / (x * PI) * Math.sin(x * PI / Support)
				/ (x * PI / Support);

	}

	private static void CalContrib() {
		nHalfDots = (int) ((double) width * support / (double) scaleWidth);
		nDots = nHalfDots * 2 + 1;
		try {
			contrib = new double[nDots];
			normContrib = new double[nDots];
			tmpContrib = new double[nDots];
		} catch (Exception e) {
			System.out.println("init   contrib,normContrib,tmpContrib" + e);
		}

		int center = nHalfDots;
		contrib[center] = 1.0;

		double weight = 0.0;
		int i = 0;
		for (i = 1; i <= center; i++) {
			contrib[center + i] = Lanczos(i, width, scaleWidth, support);
			weight += contrib[center + i];
		}

		for (i = center - 1; i >= 0; i--) {
			contrib[i] = contrib[center * 2 - i];
		}

		weight = weight * 2 + 1.0;

		for (i = 0; i <= center; i++) {
			normContrib[i] = contrib[i] / weight;
		}

		for (i = center + 1; i < nDots; i++) {
			normContrib[i] = normContrib[center * 2 - i];
		}
	} // end of CalContrib()

	// 处理边缘
	private static void CalTempContrib(int start, int stop) {
		double weight = 0;

		int i = 0;
		for (i = start; i <= stop; i++) {
			weight += contrib[i];
		}

		for (i = start; i <= stop; i++) {
			tmpContrib[i] = contrib[i] / weight;
		}

	} // end of CalTempContrib()

	private static int GetRedValue(int rgbValue) {
		int temp = rgbValue & 0x00ff0000;
		return temp >> 16;
	}

	private static int GetGreenValue(int rgbValue) {
		int temp = rgbValue & 0x0000ff00;
		return temp >> 8;
	}

	private static int GetBlueValue(int rgbValue) {
		return rgbValue & 0x000000ff;
	}

	private static int ComRGB(int redValue, int greenValue, int blueValue) {

		return (redValue << 16) + (greenValue << 8) + blueValue;
	}

	// 行水平滤波
	private static int HorizontalFilter(BufferedImage bufImg, int startX,
			int stopX, int start, int stop, int y, double[] pContrib) {
		double valueRed = 0.0;
		double valueGreen = 0.0;
		double valueBlue = 0.0;
		int valueRGB = 0;
		int i, j;

		for (i = startX, j = start; i <= stopX; i++, j++) {
			valueRGB = bufImg.getRGB(i, y);

			valueRed += GetRedValue(valueRGB) * pContrib[j];
			valueGreen += GetGreenValue(valueRGB) * pContrib[j];
			valueBlue += GetBlueValue(valueRGB) * pContrib[j];
		}

		valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
				Clip((int) valueBlue));
		return valueRGB;

	} // end of HorizontalFilter()

	// 图片水平滤波
	private static BufferedImage HorizontalFiltering(BufferedImage bufImage,
			int iOutW) {
		int dwInW = bufImage.getWidth();
		int dwInH = bufImage.getHeight();
		int value = 0;
		BufferedImage pbOut = new BufferedImage(iOutW, dwInH,
				BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < iOutW; x++) {

			int startX;
			int start;
			int X = (int) (((double) x) * ((double) dwInW) / ((double) iOutW) + 0.5);
			int y = 0;

			startX = X - nHalfDots;
			if (startX < 0) {
				startX = 0;
				start = nHalfDots - X;
			} else {
				start = 0;
			}

			int stop;
			int stopX = X + nHalfDots;
			if (stopX > (dwInW - 1)) {
				stopX = dwInW - 1;
				stop = nHalfDots + (dwInW - 1 - X);
			} else {
				stop = nHalfDots * 2;
			}

			if (start > 0 || stop < nDots - 1) {
				CalTempContrib(start, stop);
				for (y = 0; y < dwInH; y++) {
					value = HorizontalFilter(bufImage, startX, stopX, start,
							stop, y, tmpContrib);
					pbOut.setRGB(x, y, value);
				}
			} else {
				for (y = 0; y < dwInH; y++) {
					value = HorizontalFilter(bufImage, startX, stopX, start,
							stop, y, normContrib);
					pbOut.setRGB(x, y, value);
				}
			}
		}

		return pbOut;

	} // end of HorizontalFiltering()

	private static int VerticalFilter(BufferedImage pbInImage, int startY,
			int stopY, int start, int stop, int x, double[] pContrib) {
		double valueRed = 0.0;
		double valueGreen = 0.0;
		double valueBlue = 0.0;
		int valueRGB = 0;
		int i, j;

		for (i = startY, j = start; i <= stopY; i++, j++) {
			valueRGB = pbInImage.getRGB(x, i);

			valueRed += GetRedValue(valueRGB) * pContrib[j];
			valueGreen += GetGreenValue(valueRGB) * pContrib[j];
			valueBlue += GetBlueValue(valueRGB) * pContrib[j];
			// System.out.println(valueRed+"->"+Clip((int)valueRed)+"<-");
			//   
			// System.out.println(valueGreen+"->"+Clip((int)valueGreen)+"<-");
			// System.out.println(valueBlue+"->"+Clip((int)valueBlue)+"<-"+"-->");
		}

		valueRGB = ComRGB(Clip((int) valueRed), Clip((int) valueGreen),
				Clip((int) valueBlue));
		// System.out.println(valueRGB);
		return valueRGB;

	} // end of VerticalFilter()

	private static BufferedImage VerticalFiltering(BufferedImage pbImage,
			int iOutH) {
		int iW = pbImage.getWidth();
		int iH = pbImage.getHeight();
		int value = 0;
		BufferedImage pbOut = new BufferedImage(iW, iOutH,
				BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < iOutH; y++) {

			int startY;
			int start;
			int Y = (int) (((double) y) * ((double) iH) / ((double) iOutH) + 0.5);

			startY = Y - nHalfDots;
			if (startY < 0) {
				startY = 0;
				start = nHalfDots - Y;
			} else {
				start = 0;
			}

			int stop;
			int stopY = Y + nHalfDots;
			if (stopY > (int) (iH - 1)) {
				stopY = iH - 1;
				stop = nHalfDots + (iH - 1 - Y);
			} else {
				stop = nHalfDots * 2;
			}

			if (start > 0 || stop < nDots - 1) {
				CalTempContrib(start, stop);
				for (int x = 0; x < iW; x++) {
					value = VerticalFilter(pbImage, startY, stopY, start, stop,
							x, tmpContrib);
					pbOut.setRGB(x, y, value);
				}
			} else {
				for (int x = 0; x < iW; x++) {
					value = VerticalFilter(pbImage, startY, stopY, start, stop,
							x, normContrib);
					pbOut.setRGB(x, y, value);
				}
			}

		}

		return pbOut;

	} // end of VerticalFiltering()

	static int Clip(int x) {
		if (x < 0)
			return 0;
		if (x > 255)
			return 255;
		return x;
	}

	public static void saveImageAsJpgs(File fromFile, File saveFile,
			int formatWideth, int formatHeight) {
		try {
			BufferedImage bufferedImage = ImageIO.read(fromFile);
			BufferedImage bi = imageZoomOut(bufferedImage, formatWideth,
					formatHeight);
			ImageIO.write(bi, "JPEG", saveFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 以宽度为基准生成图片
	 * @param fileName
	 * @param fileNameTarget
	 * @param w
	 * @param h
	 * @throws IOException
	 */
	public static int resizeByWidth(File fileName, File fileNameTarget, int w,
			int h) throws IOException {
		Image image = ImageIO.read(fileName);
		float rw = image.getWidth(null);
		float rh = image.getHeight(null);
		int h1 = (int) (rh * w / rw);

		
		try {
			createZoomSizeImages1(fileName, fileNameTarget, w, h1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return h1;
	}

	/**
	 * 以高度为基准，等比例缩放图片
	 * 
	 * @param h
	 *            int 新高度
	 * @throws IOException
	 */
	public static int resizeByHeight(File fileName, File fileNameTarget,
			int w, int h) throws IOException {

		Image image = ImageIO.read(fileName);
		float rw = image.getWidth(null);
		float rh = image.getHeight(null);
		int w1 = (int) (rw * h / rh);

		try {
			createZoomSizeImages1(fileName, fileNameTarget, w1, h);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return w1;

	}

	/**
	 * 按照最大高度限制，生成最大的等比例缩略图
	 * 
	 * @param w
	 *            int 最大宽度
	 * @param h
	 *            int 最大高度
	 * @throws IOException
	 */
	public static List<Integer> resizeFix(File fileName, File fileNameTarget, int w,
			int h) throws IOException {
		Image image = ImageIO.read(fileName);
		int rw = image.getWidth(null);
		int rh = image.getHeight(null);

		ArrayList<Integer> arr = new ArrayList<Integer>();
		if (rw / rh > w / h) {

			int height = resizeByWidth(fileName, fileNameTarget, w, h);
			arr.add(w);
			arr.add(height);
		} else {
			int width = resizeByHeight(fileName, fileNameTarget, w, h);
			arr.add(width);
			arr.add(h);
		}
		return arr;
	}
	
	public static List<Integer> resizeFix2(File fileName, File fileNameTarget, int w,
			int h) throws IOException {
		Image image = ImageIO.read(fileName);
		
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int height = resizeByWidth(fileName, fileNameTarget, w, h);
		arr.add(w);
		arr.add(height);
		return arr;
	}

	public static void createZoomSizeImages(File fileName, File fileNameTarget,
			int w, int h) {
		try {
			resizeFix(fileName, fileNameTarget, w, h);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 按指定大小生成图片
	 * 
	 * @param fileName
	 *            原文件
	 * @param fileNameTarget
	 *            生成的目标文件
	 * @param w
	 *            宽
	 * @param h
	 *            高
	 * @throws Exception
	 */
	public static void createZoomSizeImages1(File fileName,
			File fileNameTarget, int w, int h) throws Exception {
		BufferedImage image = ImageIO.read(fileName);
		
		/*BufferedImage bufferedImage = imageZoomOut(image, w, h);*/

		AreaAveragingScaleFilter areaAveragingScaleFilter = new AreaAveragingScaleFilter(
				w, h);
		FilteredImageSource filteredImageSource = new FilteredImageSource(image
				.getSource(), areaAveragingScaleFilter);
		BufferedImage bufferedImage = new BufferedImage(w, h,
				BufferedImage.TYPE_3BYTE_BGR);
		String fileName1 = fileNameTarget.getName();
		String filetype = StringUtil.getStringsub(fileName1, ".")[1];
		// System.out.println(filetype);
		filetype = filetype.toUpperCase();
        Graphics2D  graphics = bufferedImage.createGraphics();
		
        if (filetype.endsWith("PNG")) {
			bufferedImage = graphics.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT); 
	
			graphics.dispose(); 
	
			graphics = bufferedImage.createGraphics();
		}
		
		graphics.drawImage(component.createImage(filteredImageSource), 0, 0,
				null);
		
		// System.out.println(filetype);
		if (filetype.endsWith("PNG")) {
			
			ImageIO.write(bufferedImage, "png", fileNameTarget);
		} else if (filetype.endsWith("GIF")) {
			ImageIO.write(bufferedImage, "GIF", fileNameTarget);
		} else if (filetype.equals("JPEG")) {
			ImageIO.write(bufferedImage, "JPEG", fileNameTarget);
		} else {
			ImageIO.write(bufferedImage, "JPG", fileNameTarget);
		}

	}

	/**
	 * 图片水印
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param targetImg
	 *            目标图片
	 * @param x
	 *            修正值 默认在中间
	 * @param y
	 *            修正值 默认在中间
	 * @param alpha
	 *            透明度
	 */
	public final static void pressImage(String pressImg, String targetImg,
			int x, int y, float alpha) {
		try {
			File img = new File(targetImg);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			Image src_biao = ImageIO.read(new File(pressImg));
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			g.drawImage(src_biao, (wideth - wideth_biao) / 2 + x,
					(height - height_biao) / 2 + y, wideth_biao, height_biao,
					null);
			// 水印文件结束
			g.dispose();
			ImageIO.write((BufferedImage) image, "jpg", img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 图片水印右下角
	 * 
	 * @param pressImg
	 *            水印图片
	 * @param targetImg
	 *            目标图片
	 * @param x
	 *            修正值 默认在中间
	 * @param y
	 *            修正值 默认在中间
	 * @param alpha
	 *            透明度
	 */
	public final static void pressImageInRightBotton(File pressImg,
			File targetImg, float alpha) {
		try {
			File img = targetImg;
			// new File(targetImg);
			Image src = ImageIO.read(img);
			int wideth = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(wideth, height,
					BufferedImage.TYPE_INT_RGB);
			
			
			Graphics2D  graphics = bufferedImage.createGraphics();
			
			bufferedImage = graphics.getDeviceConfiguration().createCompatibleImage(wideth, height, Transparency.TRANSLUCENT); 

			graphics.dispose(); 
			
			graphics = bufferedImage.createGraphics();
			
			graphics.drawImage(src, 0, 0, wideth, height, null);
			// 水印文件
			Image src_biao = ImageIO.read(pressImg);
			int wideth_biao = src_biao.getWidth(null);
			int height_biao = src_biao.getHeight(null);
			graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			graphics.drawImage(src_biao, wideth - wideth_biao - 10, height
					- height_biao - 10, wideth_biao, height_biao, null);
			// 水印文件结束
			graphics.dispose();
			String fileName1 = targetImg.getName();
			String filetype = StringUtil.getStringsub(fileName1, ".")[1];
			// System.out.println(filetype);
			filetype = filetype.toUpperCase();
			// System.out.println(filetype);
			if (filetype.endsWith("PNG")) {
				ImageIO.write((BufferedImage) bufferedImage, "png", img);

			} else if (filetype.endsWith("GIF")) {
				ImageIO.write((BufferedImage) bufferedImage, "GIF", img);

			} else if (filetype.equals("JPEG")) {
				ImageIO.write((BufferedImage) bufferedImage, "JPEG", img);

			} else {
				ImageIO.write((BufferedImage) bufferedImage, "JPG", img);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按指定大小生成图片
	 * 
	 * @param fileName
	 *            原文件
	 * @param fileNameTarget
	 *            生成的目标文件
	 * @param w
	 *            宽
	 * @param h
	 *            高
	 * @throws Exception
	 */
	public static void createZoomSizeImagesWithoutwh(File fileName,
			File fileNameTarget) throws Exception {
		String fileName1 = fileNameTarget.getName();
		String filetype = StringUtil.getStringsub(fileName1, ".")[1];
		filetype = filetype.toUpperCase();
		if (filetype.endsWith("GIF")) {
			CopyFile(fileName,fileNameTarget);
			return;
		}
		Image image = ImageIO.read(fileName);
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		AreaAveragingScaleFilter areaAveragingScaleFilter = new AreaAveragingScaleFilter(
				w, h);
		FilteredImageSource filteredImageSource = new FilteredImageSource(image
				.getSource(), areaAveragingScaleFilter);
		//BufferedImage bufferedImage = new BufferedImage(w, h,
			//	BufferedImage.TYPE_3BYTE_BGR);
		BufferedImage bufferedImage = new BufferedImage(w, h,
			BufferedImage.TYPE_INT_RGB);
		
		Graphics2D  graphics = bufferedImage.createGraphics();

		if (filetype.endsWith("PNG")) {
			bufferedImage = graphics.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT); 
	
			graphics.dispose(); 
	
			graphics = bufferedImage.createGraphics();
		}
		
		graphics.drawImage(component.createImage(filteredImageSource), 0, 0,
				null);
		if (filetype.endsWith("PNG")) {
			//ImageIO.write(bufferedImage, "PNG", fileNameTarget);
			ImageIO.write(bufferedImage, "png", fileNameTarget); 
		} else if (filetype.equals("JPEG")) {
			ImageIO.write(bufferedImage, "JPEG", fileNameTarget);
		} else {
			ImageIO.write(bufferedImage, "JPG", fileNameTarget);
		}

	}
	
	
	public static void createZoomSizeImagesWithoutwh_1(File fileName,
			File fileNameTarget) throws Exception {
		String fileName1 = fileNameTarget.getName();
		String filetype = StringUtil.getStringsub(fileName1, ".")[1];
		filetype = filetype.toUpperCase();
		
		Image image = ImageIO.read(fileName);
		int w = image.getWidth(null);
		int h = image.getHeight(null);
		AreaAveragingScaleFilter areaAveragingScaleFilter = new AreaAveragingScaleFilter(
				w, h);
		FilteredImageSource filteredImageSource = new FilteredImageSource(image
				.getSource(), areaAveragingScaleFilter);
		BufferedImage bufferedImage = new BufferedImage(w, h,
				BufferedImage.TYPE_3BYTE_BGR);
		
		Graphics2D  graphics = bufferedImage.createGraphics();
		
		if (filetype.endsWith("PNG")) {
			bufferedImage = graphics.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT); 
	
			graphics.dispose(); 
	
			graphics = bufferedImage.createGraphics();
		}
		
		

		graphics.drawImage(component.createImage(filteredImageSource), 0, 0,
				null);
		if (filetype.endsWith("PNG")) {
			
			ImageIO.write(bufferedImage, "png", fileNameTarget);
		} else if (filetype.equals("JPEG")) {
			ImageIO.write(bufferedImage, "JPEG", fileNameTarget);
		} else {
			ImageIO.write(bufferedImage, "JPG", fileNameTarget);
		}

	}
	
	public  static  void   CopyFile(File   in,   File   out)   throws   Exception   { 
		BufferedInputStream   fis   =   new   BufferedInputStream(new   FileInputStream(in)); 
		BufferedOutputStream   fos   =   new   BufferedOutputStream(new   FileOutputStream(out)); 

        byte[]   buf   =   new   byte[1024]; 
        int   i   =   0; 
        while((i=fis.read(buf))!=-1)   { 
            fos.write(buf,   0,   i); 
        } 
        fis.close(); 
        fos.close(); 
	} 



	/**
	 * 生成图片，不改变原图的尺寸
	 * 
	 * @param fromFile
	 * @param saveFile
	 * @param formatWideth
	 * @param formatHeight
	 * @throws IOException 
	 * @throws Exception
	 */
	public static void savePicture(File fromFile, File saveFile) throws IOException{
		BufferedImage srcImage;
		srcImage = javax.imageio.ImageIO.read(fromFile);
		int imageWideth = srcImage.getWidth(null);
		int imageHeight = srcImage.getHeight(null);
		srcImage = imageZoomOut(srcImage, imageWideth, imageHeight);
		//获取文件后缀
		String fileName = saveFile.getName();
		String fileType = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		String formatName = "JPEG";
		//当文件为png时，以png格式保存，为其他格式则以jpeg格式保存
		if("png".equals(fileType)){
			formatName="PNG";
		}
		ImageIO.write(srcImage,formatName, saveFile);
	}
	/**  
     * 截取一个图像的中央区域  
     * @param image 图像File  
     * @param w 需要截图的宽度  
     * @param h 需要截图的高度  
     * @return 返回一个  
     * @throws IOException  
     */  
    public static void cutImage(File image, int w, int h) throws IOException {   
           
        // 判断参数是否合法   
        if (null == image || 0 == w || 0 == h) {   
            new Exception ("哎呀，截图出错！！！");   
        }   
        InputStream inputStream = new FileInputStream(image);   
        // 用ImageIO读取字节流   
        BufferedImage bufferedImage = ImageIO.read(inputStream);   
        BufferedImage distin = null;   
        // 返回源图片的宽度。   
        int srcW = bufferedImage.getWidth();   
        // 返回源图片的高度。   
        int srcH = bufferedImage.getHeight();   
        int x = 0, y = 0;   
        // 使截图区域居中   
        x = srcW / 2 - w / 2;   
        y = srcH / 2 - h / 2;   
        srcW = srcW / 2 + w / 2;   
        srcH = srcH / 2 + h / 2;   
        // 生成图片   
        distin = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);   
        Graphics g = distin.getGraphics();   
        g.drawImage(bufferedImage, 0, 0, w, h, x, y, srcW, srcH, null);   
        ImageIO.write(distin, "jpg", new File("D:\\pic\\33.jpg"));   
    }   
    public static void main(String[] args) throws Exception {   
        File file = new File("D:\\pic\\22.jpg");   
        cutImage(file, 200, 200);   
    }   
}
