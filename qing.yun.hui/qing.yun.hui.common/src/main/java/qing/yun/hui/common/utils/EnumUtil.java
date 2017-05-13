package qing.yun.hui.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @Description: EnumUtil 枚举工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:20:23 AM
 ** @version: V1.0
 ***/
public class EnumUtil {
	
	/**
	 * <p>将enum转换map并其转换成json</p>
	 * <p>如果前端页面调用，只须EnumUtil.getEnumMapToJSON(clzName)[code]即可</p>
	 * @param clzName 待处理的枚举类
	 * @return String 转换后的json串
	 * */
	public static String getEnumMapToJSON(String clzName){
		if(StringUtil.isEmpty(clzName)) return null;
		ICommonEnum[] enums=(ICommonEnum[]) getEnumValues(clzName);
		Map<String,Object> map=new HashMap<String, Object>();
		for(ICommonEnum ice:enums){
			map.put(ice.getCode(), ice.getName());
		}
		return JSON.toJSONString(map);
	}
	
	/***
	 * <p>根据传的枚举类(必须是包名+类包)，得到该枚举所有属性值。 </p>
	 * <p>如果是内部类、包名$类名的形式调用即可</p>
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
	
	/***
	 * <p>根据传的枚举类(必须是包名+类包)，得到该枚举所有属性值。 </p>
	 * <p>如果是内部类、包名$类名的形式调用即可</p>
	 * @param clzName　绝对路径的类名
	 * ***/
	public static String getEnumToJSON(String clzName){
		Object[] obj=getEnumValues(clzName);
		if(null==obj) return "";
		return JSONObject.toJSONString(obj);
	}
	
	/**
	 * 根据给定code获取name
	 * @param clzs  枚举类(该枚举类必须实现ICommonEnum接口)
	 * @param code 枚举code
	 * @return String 枚举name
	 * */
	public static String getNameByCode(Class<?> clzs,Object code){
		ICommonEnum[] commonEnums=getICommonEnums(clzs);
		if(null==commonEnums) return null;
		for(ICommonEnum ienum:commonEnums){
			if(ienum.getCode().equals(String.valueOf(code))){
				return ienum.getName();
			}
		}
		return null;
	}
	
	public static String getNameByCode(String clzs,Object code){
		Class<?> clz;
		try {
			clz = Class.forName(clzs);
			ICommonEnum[] commonEnums=getICommonEnums(clz);
			if(null==commonEnums) return null;
			for(ICommonEnum ienum:commonEnums){
				if(ienum.getCode().equals(String.valueOf(code))){
					return ienum.getName();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据给定value获取name
	 * @param clzs  枚举类(该枚举类必须实现ICommonEnum接口)
	 * @param code 枚举code
	 * @return String 枚举name
	 * */
	public static String getNameByValue(Class<?> clzs,int value){
		ICommonEnum[] commonEnums=getICommonEnums(clzs);
		if(null==commonEnums) return null;
		for(ICommonEnum ienum:commonEnums){
			if(ienum.getValue()==value){
				return ienum.getName();
			}
		}
		return null;
	}
	
	/**
	 * 根据给定value获取name
	 * @param clzs  枚举类(该枚举类必须实现ICommonEnum接口)
	 * @param code 枚举code
	 * @return String 枚举name
	 * */
	public static String getNameByValue(String clzs,String value){
		try {
			Class<?> clz=Class.forName(clzs);
			return getNameByValue(clz, Integer.parseInt(value));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据给定name获取code
	 * @param clzs  枚举类 (该枚举类必须实现ICommonEnum接口)
	 * @param value 枚举value
	 * @return String 枚举code
	 * */
	public static String getCodeByName(Class<?> clzs,String name){
		ICommonEnum[] commonEnums=getICommonEnums(clzs);
		if(null==commonEnums) return null;
		for(ICommonEnum ienum:commonEnums){
			if(ienum.getName().equals(name)){
				return ienum.getCode();
			}
		}
		return null;
	}
	
	/**
	 * 根据给定name获取code
	 * @param clzs  枚举类 (该枚举类必须实现ICommonEnum接口)
	 * @param value 枚举value
	 * @return String 枚举code
	 * */
	public static String getCodeByValue(String clzs,String value){
		try {
			Class<?> clz=Class.forName(clzs);
			ICommonEnum[] commonEnums=getICommonEnums(clz);
			if(null==commonEnums) return null;
			for(ICommonEnum ienum:commonEnums){
				if(String.valueOf(ienum.getValue()).equals(value)){
					return ienum.getCode();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ICommonEnum[] getICommonEnums(Class<?> clzs){
		ICommonEnum[] commonEnums=null;
		try {
			commonEnums=(ICommonEnum[]) getEnumConstants(clzs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commonEnums;
	}
	
	private static Object[] getEnumConstants(Class<?> clzs){
		Object[] objs=null;
		if(clzs.isEnum()){
			objs=clzs.getEnumConstants();
		}
		return objs;
	}
	
	public static void main(String[] args){
		String clz="cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword";
		Object[] objs=getEnumValues(clz);
		System.out.println(getEnumToJSON(clz));
		for(Object obj:objs){
			System.out.println(obj.toString());
		}
	}

}
