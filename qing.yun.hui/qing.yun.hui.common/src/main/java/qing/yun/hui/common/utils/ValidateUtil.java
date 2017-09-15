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
	
	/**
	 * 身份证验证
	 * @param idcard
	 * @return boolean
	 * */
	public static boolean isIDCard(String idcard){
		boolean flag = false; 
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])"); //要么是15位，要么是18位，最后一位可以为字母
        //通过Pattern获得Matcher  
        Matcher idNumMatcher = idNumPattern.matcher(idcard);  
        //判断用户输入是否为身份证号  
        if(idNumMatcher.matches()){  
            System.out.println("您的出生年月日是：");  
            //如果是，定义正则表达式提取出身份证中的出生日期  
            Pattern birthDatePattern= Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//身份证上的前6位以及出生年月日  
            //通过Pattern获得Matcher  
            Matcher birthDateMather= birthDatePattern.matcher(idcard);  
            //通过Matcher获得用户的出生年月日  
            if(birthDateMather.find()){  
                String year = birthDateMather.group(1);  
                String month = birthDateMather.group(2);  
                String date = birthDateMather.group(3);  
                //输出用户的出生年月日  
                System.out.println(year+"年"+month+"月"+date+"日");                  
            }
            flag=true;
	     }
        return flag;
	}
	
	public static boolean isName(String name){
		String regex = "^(([\u4e00-\u9fa5]{2,8})|([a-zA-Z]{2,16}))$";
		return validate(name,regex);
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
		System.out.println(isName("你某"));
	}

}
