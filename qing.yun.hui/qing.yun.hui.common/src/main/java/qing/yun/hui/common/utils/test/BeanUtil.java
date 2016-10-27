
package qing.yun.hui.common.utils.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import qing.yun.hui.common.utils.StringUtil;

/**
 * Description: 
 * 
 * @Date Create on 2016年8月3日
 * @author <a href="mailto:qingyunhui@zuozh.com">qingyunhui</a>
 * @since version1.0 Copyright 2015 ZZJR All Rights Reserved.
 */
public class BeanUtil {
	

	/**
	 * 遍历对象的非空(包括空null及"")属性，copy到新的对象中...
	 * @param object  待处理的对象
	 * @return Object 处理后的对象
	 * **/
	public static Object notEmptyToObject(Object object){
		if(null==object) return object;
		Field[] fields=object.getClass().getDeclaredFields();
		Object tmpobj=null;
		try {
			 tmpobj=object.getClass().newInstance();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		for(Field field:fields){
			String name=field.getName();
			name=StringUtil.firstLetterConvert(name, true);
			String type=field.getType().getSimpleName();
			Method method=null;
			try {
				method=object.getClass().getMethod("get"+name);
				Object obj=method.invoke(object, new Object[]{});
				if(null!=obj && !"".equals(obj)){
					method=tmpobj.getClass().getMethod("set"+name,field.getType());
					if("String".equals(type)){
						method.invoke(tmpobj, String.valueOf(obj));
					}else if("Integer".equals(type)){
						method.invoke(tmpobj, Integer.valueOf(obj+""));
					}else{
						method.invoke(tmpobj, obj);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tmpobj;
	}
	
}
