package qing.yun.hui.common.utils;

/***
 ** @Description: EnumUtil 枚举工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:20:23 AM
 ** @version: V1.0
 ***/
public class EnumUtil {
	
	/***
	 * 根据传的枚举类(必须是包名+类包)，得到该枚举所有属性值。 
	 * @param clzName　绝对路径的类名
	 * ***/
	public static Object[] getEnumValues(String clzName){
		Object[] obj=null;
		try {
			Class<?> clz=Class.forName(clzName);
			if(clz.isEnum()){
				obj=clz.getEnumConstants();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static void main(String[] args){
		
		String clz="cn.com.longfei.cms.common.enums.Sex";
		Object[] objs=getEnumValues(clz);
		for(Object obj:objs){
			System.out.println(obj.toString());
		}
		
	}

}
