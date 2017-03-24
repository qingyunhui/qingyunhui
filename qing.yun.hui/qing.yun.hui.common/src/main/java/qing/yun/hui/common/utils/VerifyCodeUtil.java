package qing.yun.hui.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/***
 ** @Description: VerifyCodeUtil 验证码工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:18:17 AM
 ** @version: V1.0
 ***/
public class VerifyCodeUtil {
	
	private String verifyCode;								   //  验证码。
	
	private BufferedImage bufferedImage;	

	//初始化属性
	private VerifyCodeUtil() {
		init();										
	}
	
	/**
	 * 取得RandomNumUtil实例
	 */
	public static VerifyCodeUtil Instance() {
		return new VerifyCodeUtil();
	}
	
	/**
	 * 取得图片的验证码
	 */
	public String getVerifyCode() {
		return this.verifyCode;
	}
	
	/**
	 * 图片生成前的一些初始化,操作。
	 * ***/
	private void init() {
		// 在内存中创建图象
		int width = 85, height = 32;
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int j = random.nextInt(width);
			int k = random.nextInt(height);
			int h = random.nextInt(10);
			int m = random.nextInt(10);
			g.drawLine(j, k, h + j, m + k);
		}
		// 取随机产生的认证码(5位数字)
		StringBuffer sRand = new StringBuffer();
		int minLetters;
		int upperLetters;
		for (int i = 0; i < 5; i++) {
			minLetters = (random.nextInt(26) + 65);
			upperLetters = random.nextInt(26) + 97;
			switch (random.nextInt(5)) {
			case 0:
				sRand.append(random.nextInt(10));						//生成0-9中的任意数.
				break;
			case 1:
				sRand.append(String.valueOf((char) minLetters));	  //生成ascll码在:65-90之间的小写字母...
				break;
			case 2:
				sRand.append(String.valueOf((char) upperLetters));	//生成ascll码在:97-122之间的大写字母...
				break;
			case 3:
				sRand.append(random.nextInt(10));
				break;
			case 4:
				sRand.append(random.nextInt(10));
				break;
			default:
				sRand.append(String.valueOf((char) upperLetters));
				break;
			}
		}
		// 将认证码显示到图象中
		g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110), 20 + random.nextInt(110)));
		String randomLetter = sRand.toString();
		// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
		g.drawString(randomLetter, 13 * random.nextInt(3) + 5, 20);
		// 赋值验证码
		this.verifyCode = sRand.toString();
		// 图象生效
		g.dispose();
		this.setBufferedImage(image);
	}
	/*
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

}
