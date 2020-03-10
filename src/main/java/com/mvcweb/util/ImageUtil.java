package com.mvcweb.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.LogFactory;

public class ImageUtil {
	
	
	/**
	 * 返回图片的类型，小写形式。如： image/jpeg
	 * @param file
	 * @return 获取是否则返回application/octet-stream
	 */
	public static String getImageContentType(File file){
	    ImageInputStream iis = null;
	    String contentType = "application/octet-stream";
		try {
			iis = ImageIO.createImageInputStream(file);
		    Iterator <ImageReader>  iter  =  ImageIO.getImageReaders(iis);
		    while(iter.hasNext()){
		    	contentType = iter.next().getFormatName();
		    	contentType = "image/"+contentType.toLowerCase();
		    	break;
		    }
		} catch (IOException e) {
		}finally{
			if(iis != null){
				try {
					iis.close();
				} catch (IOException e) {
				}
			}
		}
	    return contentType;
	}
	

	/**
	 * 获取图片的宽度和高度，获取失败返回null
	 * @param in
	 * @return
	 */
	public static int[] getImageWidthAndHeight(InputStream in){
		try {
			BufferedImage bufferedImage = ImageIO.read(in);
			if(bufferedImage == null) return null;
			return new int[]{bufferedImage.getWidth(),bufferedImage.getHeight()};
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 获取图片宽和高<br>
	 * 如果图片解析成功则返回图片的实际宽高，文件为空或不存在或其他返回[0,0]
	 * @param file 图片文件
	 * @return 图片的宽和高或者[0,0]
	 */
	public static int[] getImageWdithHeight(File file){
		if(file == null || !file.exists()) return new int[]{0,0};
		try {
			BufferedImage bi = ImageIO.read(file);
			return new int[]{bi.getWidth(),bi.getHeight()};
		} catch (IOException e) {
		}
		return new int[]{0,0};
	}
	
	/**
	 * 获取文件的后缀名
	 * @param filename
	 * @return
	 */
	public static String getFileSuffix(String filename){
		if(filename == null) return "";
		filename = filename.trim();
		int index = filename.lastIndexOf(".");
		if(index == -1) return "";
		return filename.substring(index+1);
	}
	
	/**
	 * 根据访问的绝对路径获取文件在硬盘上的实际路径
	 * @param req
	 * @param path
	 * @return
	 */
	public static String getRealPath(HttpServletRequest req,String path){
		return req.getSession().getServletContext().getRealPath(path);
	}
}
