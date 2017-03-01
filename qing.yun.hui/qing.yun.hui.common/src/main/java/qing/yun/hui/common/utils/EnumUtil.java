package qing.yun.hui.common.utils;

import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @Description: EnumUtil 枚举工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:20:23 AM
 ** @version: V1.0
 ***/
public class EnumUtil {
	
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
	
	private static ICommonEnum[] getICommonEnums(Class<?> clzs){
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
		String clz="qing.yun.hui.common.enums.Test$Time";
		Object[] objs=getEnumValues(clz);
		for(Object obj:objs){
			System.out.println(obj.toString());
		}
	}

}
