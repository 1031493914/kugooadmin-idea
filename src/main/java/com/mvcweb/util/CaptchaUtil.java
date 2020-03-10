package com.mvcweb.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName: CaptchaUtil
 * @Description: 关于验证码的工具类
 * @author 无名
 * @date 2016-5-7 上午8:33:08
 * @version 1.0
 */
public class CaptchaUtil extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CaptchaUtil() {
		super();
	}

	/*
	 * 随机字符字典
	 */
	public static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
			'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	/*
	 * 随机数
	 */
	public static Random random = new Random();

	/*
	 * 获取6位随机数
	 */
	public static String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	/*
	 * 获取随机数颜色
	 */
	public static Color getRandomColor() {
		return new Color(222884);
	}

	/*
	 * 返回某颜色的反色
	 */
	public static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("images/jpeg");

		String randomString = getRandomString();
		System.out.println("randomString----"+randomString);
		int width = 100;
		int height = 30;
		Color color = getRandomColor();
		Color reverse = getReverseColor(color);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setFont(new Font("Times New Roman", Font.BOLD, 16));
		g.setColor(new Color(212, 212, 212));
		g.fillRect(0, 0, width, height);
		g.setColor(reverse);
		g.drawString(randomString, 18, 20);
		// 转成JPEG格式
//		ServletOutputStream out = response.getOutputStream();
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(bi);
//		out.flush();
//		ImageIO.write(bi, "JPEG", out);
//		ImageIO.write(dstImage, /*"GIF"*/ formatName /* format desired */ , new File(dstName) /* target */ );
		ImageIO.write(bi, "JPEG", response.getOutputStream()); // 输出图片
		HttpSession session = request.getSession(true);
		session.setAttribute("randCheckCode", randomString);
		g.dispose(); // 释放g所占用的系统资源

	}
}
