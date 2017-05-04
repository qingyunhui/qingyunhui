package qing.yun.hui.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/***
 ** @Description: ReflexUtil 反射工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:15:50 AM
 ** @version: V1.0
 ***/
public class ReflexUtil {
	
	/**
	 * <p>从jsonObj的值copy到obj中，注意jsonObj中的key(属性)要与obj中字段保持一致</p>
	 * @param obj 待处理的对象
	 * @param jsonObject 待操作的对象
	 * @return void
	 * */
	public static void setObjectValue(Object obj,JSONObject jsonObj){
		if(null==obj||null==jsonObj) return;
		Field[] fields=obj.getClass().getDeclaredFields();	//得到所有已声明的的属性字段。
		for(Field field:fields){
			try {
				String name=field.getName();
				String value=jsonObj.getString(name);
				if(!StringUtil.isEmpty(value)){
					field.setAccessible(true);
					Class<?> clz=field.getType();
					if(clz.equals(Date.class)){
						field.set(obj, DateUtil.getDateByStr(value));
					}else if(clz.equals(Integer.class) || clz.equals(int.class)){
						field.set(obj, Integer.parseInt(value));
					}else if(clz.equals(Long.class) || clz.equals(long.class)){
						field.set(obj, Long.parseLong(value));
					}else if(clz.equals(Double.class) || clz.equals(double.class)){
						field.set(obj, Double.parseDouble(value));
					}else{
						field.set(obj, value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	/**
	 * 对象到map转换、只有当obj对象中的值不为null或空的时候才会被放到map中。
	 * @param obj 待转换的对象
	 * @return 转换后的map对象。
	 * **/
	public static Map<String,Object> clzToMap(Object obj){
		Map<String,Object> map=new HashMap<String,Object>();
		if(null==obj){
			return map;
		}
		Field[] fields=obj.getClass().getDeclaredFields();	//得到所有已声明的的属性字段。
		for(Field field:fields){
			try {
				Method method=obj.getClass().getMethod("get"+StringUtil.firstLetterConvert(field.getName(), true));
				Object object=method.invoke(obj);//method.invoke(obj,new Object[]{})也可;
				if(null!=object && !StringUtil.isEmpty(object)){
					map.put(field.getName(), object);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return map;
	}
	
	/***
	 * 根据给定的methodsName数组从list数据列表中找出与之对应的值并封装到List<Object[]>对像中。
	 * @param list 数据列表
	 * @param methodsName list数据列表中的方法名。
	 * @param addSeq 是否要加序号
	 * @return List<Object[]> 返回对应的数据。
	 * ****/
	public static <T> List<Object[]> getObjs(List<T> list,String[] methodsName,boolean addSeq){
		List<Object[]> objs=new ArrayList<Object[]> ();
		for(T t:list){
			Object[] object=new Object[addSeq?methodsName.length+1:methodsName.length];
			for(int i=0;i<methodsName.length;i++){
				Object obj=null;
				try {
					if(addSeq && i==0){
						object[i]="index:"+ ++i;
					}else{
						/**这里作了异常处理、为防用户给定的方法不存在时、还能让程序继续运行。**/
						Method md=t.getClass().getMethod("get"+StringUtil.firstLetterConvert(methodsName[i], true));
						obj=md.invoke(t);
						if(null==obj){
							object[i]=methodsName[i]+":";
						}else{
							if(obj instanceof Date){
								object[i]=methodsName[i]+":"+DateUtil.dateToString((Date)obj,DateUtil.YYYY_MM_DD);
							}else{
								object[i]=methodsName[i]+":"+obj.toString();	
							}
						}
					}
				} catch (Exception e) {
					object[i]="get"+StringUtil.firstLetterConvert(methodsName[i],true)+"() 方法不存在。";
					continue;
				}
			}
			objs.add(object);
		}
		return objs;
	}
}
