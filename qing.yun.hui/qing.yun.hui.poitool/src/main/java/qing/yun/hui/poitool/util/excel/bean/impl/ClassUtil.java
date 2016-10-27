package qing.yun.hui.poitool.util.excel.bean.impl;
/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:21:57 PM
 ** @version: V1.0
 ***/
public class ClassUtil {
	/**
	 * 判断一个类是否是一个接口的实现
	 * @param implementClass  实现接口的类
	 * @param interfaceClass		接口类
	 * @return
	 */
	public static boolean isImplement(Class<?> implementClass, Class<?> interfaceClass){
		Class<?>[] clazzs = implementClass.getInterfaces();
		for (Class<?> clazz : clazzs) {
			if(clazz ==interfaceClass ){
				return true;
			}
		}
		return false;
	}
}
