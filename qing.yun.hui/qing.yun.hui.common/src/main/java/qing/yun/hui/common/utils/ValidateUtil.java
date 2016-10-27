package qing.yun.hui.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 ** @Description: 验证工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 21, 2015 8:10:05 PM
 ** @version: V1.0
 ***/
public class ValidateUtil {
	
	/**
	 * 
	 * @Description: 自定义正则表达式验证
	 * @param str
	 * @param regex
	 * @return boolean
	 * @throws
	 */
	public static boolean validate(String str, String regex) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile(regex);
		m = p.matcher(str);
		b = m.matches();
		return b;
	}

	/**
	 * 
	 * @Description: 手机验证
	 * @Title: isMobile
	 * @param str
	 * @return boolean
	 * @throws
	 */
	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;
		p = Pattern.compile("^((\\(\\d{3}\\))|(\\d{3}\\-))?1(3|5|8)\\d{9}$"); // 验证手机号
		m = p.matcher(str);
		b = m.matches();
		return b;
	}
	public static boolean isEmail(String str){
		String regex="^([a-z0-9A-Z]+[-\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		return validate(str,regex);
	}

	/**
	 * 
	 * @Description: 电话号码验证
	 * @Title: isPhone
	 * @param str
	 * @return boolean
	 * @throws
	 */
	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean b = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			b = m.matches();
		} else {
			m = p2.matcher(str);
			b = m.matches();
		}
		return b;
	}
	
	//TODO back......
	public static void replace(String targetReplace,String facePath){
		targetReplace=targetReplace.replaceAll("/</g", "&lt;");
		targetReplace = targetReplace.replace("/>/g","&gt;");
		targetReplace = targetReplace.replace("/\n/g","<br/>");
		targetReplace = targetReplace.replace("/\\[em_([0-9]*)\\]/g","<img src="+facePath+"/"+"$1.gif" +"border=0"+"/>");
//		str = str.replace(/\</g,'&lt;');
//		str = str.replace(/\>/g,'&gt;');
//		str = str.replace(/\n/g,'<br/>');
//		str = str.replace(/\[em_([0-9]*)\]/g,'<img src="'+facePath+'/face/$1.gif" border="0" />');
//		
	}
	
	public static void main(String[] args) {
		String str="[em_13]近日，网友晒出自己在健身房遇到的美女健身者，火辣身材超高颜值，引得围观网友纷纷高呼：好想认识！健身房一时成为美女走红之地。[em_55]";
		replace(str,"www.baidu.com/");
		System.out.println("str:"+str);
	}

}
