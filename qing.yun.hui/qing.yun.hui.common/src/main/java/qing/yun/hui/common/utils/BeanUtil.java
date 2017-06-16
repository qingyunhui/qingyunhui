package qing.yun.hui.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 11:46:02 PM
 ** @version: V1.0
 ***/
public class BeanUtil {
	
	public static void main(String[] args){
		/*SysUsers user=new SysUsers();
		user.setAccount("张三");
		user.setCreater("");
		user.setAge(22);
		user.setCreaterId(null);
		user.setComments("你大爷的");
		user.setEditor(null);
		Map<String,Object> map=notEmptyToMap(user);
		Set<Entry<String, Object>> set=map.entrySet();
		Iterator<Entry<String, Object>> iterator=set.iterator();
		while(iterator.hasNext()){
			Entry<String, Object> entry=iterator.next();
			String key=entry.getKey();
			Object value=entry.getValue();
			System.out.println("key:"+key+",value:"+value);
		}*/
	}
	
	/**
	 * 遍历对象的属性，将非空属性的名称和值加入map中
	 * @param object
	 * @param map
	 */
	public static Map<String, Object> pojoToMap(Object object) {
		Map<String, Object> tmp = new HashMap<String, Object>();
		if (object == null) {
			return tmp;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] flds = object.getClass().getDeclaredFields();
		for (Field f : flds) {
			map.put(f.getName(), null);
		}
		Class<?> cls = object.getClass().getSuperclass();
		while (cls != Object.class) {
			Field[] fldz = cls.getDeclaredFields();
			for (Field f : fldz) {
				if (!map.containsKey(f.getName())) {
					map.put(f.getName(), null);
				}
			}
			cls = cls.getSuperclass();
		}
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			String methodName = "get" + StringUtil.firstLetterConvert(key, true);
			try {
				Method m = object.getClass().getMethod(methodName);
				// 调用getter方法获取属性值
				Object value = (Object) m.invoke(object);
				if (!StringUtil.isEmpty(value)) {
					tmp.put(key, value);
				}
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			} catch (InvocationTargetException e) {
			}
		}
		return tmp;
	}
	
	/**
	 * 枚举转换map
	 * @param enumClass
	 * @return Map<String,String>
	 * **/
	public static Map<String, String> enumToJSONString(Class<?> enumClass){
		Map<String, String> res = new HashMap<String, String>();
		for (Object o : enumClass.getEnumConstants()) {
			ICommonEnum item=(ICommonEnum) o;
			res.put(item.getValue()+"", item.getName());
		}
		return res;
	}
	
	/**
	 * 遍历对象非空(包括空null及"")属性，copy到map中.
	 * @param object
	 * @return Map<String,Object> 
	 * **/
	public static Map<String,Object> notEmptyToMap(Object object){
		Map<String,Object> map=new HashMap<String,Object>();
		if(null==object) return map;
		Field[] fields=object.getClass().getDeclaredFields();
		for(Field field:fields){
			String name=field.getName();
			Method method=null;
			try {
				name=StringUtil.firstLetterConvert(name, true);
				method=object.getClass().getMethod("get"+name);
				Object value=method.invoke(object, new Object[]{});
				if(null!=value && !"".equals(value)){
					if(!map.containsKey(field.getName())){
						map.put(field.getName(),value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	
	/**
	 * 遍历对象非空(包括空null及"")属性，copy到新的对象中...
	 * @param object
	 * @return Object 
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
