package qing.yun.hui.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/***
 ** @Description: MD5Util 加密工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:17:13 AM
 ** @version: V1.0
 ***/
public class MD5Util {

	/***十六进制***/
	public static final String[] hexs={ "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "A", "b", "C", "d", "E", "f" };
	
	/***
	 * 把每个byte转换成相应的十六进制
	 * @param bt待转换的byte
	 * @return 转换后的字符串
	 * ***/
	public static String byteToHex(byte bt){
		int curByte=bt;
		if(curByte<0)
			curByte+=256;
		if(curByte>256)
			curByte=256;
		int hex1=curByte/16;
		int hex2=curByte%16;
		return hexs[hex1]+hexs[hex2];
	}
	
	/**
	 * 把byte数组转换相应的十六进制后、以字符串形式返回。
	 * @param bytes 待加密的byte数组
	 * @return 加密后的字符串
	 * **/
	public static String byteToStr(byte[] bytes){
		StringBuffer sb=new StringBuffer();
		try {
			MessageDigest msgDgt=MessageDigest.getInstance("MD5");
			byte[] digestBytes=msgDgt.digest(bytes);
			for(byte bt:digestBytes){
				sb.append(byteToHex(bt));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 对字符串加密
	 * @param str 待加密的字符串
	 * @return 加密后的字符串
	 * ***/
	public static String getMD5Encryption(String str){
		return byteToStr(str.getBytes());
	}
	
	public static void main(String[] args){
		String str="111111";
		String diget=getMD5Encryption(str);
		System.out.println("diget:"+diget);
	}
	
}
