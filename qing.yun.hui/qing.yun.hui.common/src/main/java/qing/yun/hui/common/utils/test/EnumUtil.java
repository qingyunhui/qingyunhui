package qing.yun.hui.common.utils.test;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Description: 枚举工具类
 * 
 * @Date Create on 2016年8月3日
 * @author <a href="mailto:qingyunhui@zuozh.com">qingyunhui</a>
 * @since version1.0 Copyright 2015 ZZJR All Rights Reserved.
 */
public class EnumUtil {
	
	public static void main(String[] args){
		/*String code=getCodeByValue(TestStatus.Status.class, "失败");
		String value=getValueByCode(TestStatus.Status.class, "2");
		System.err.println("code:"+code+",value:"+value);
		System.out.println("-------------------------------------");
		String code2=getCodeByValue(TestStatus.Sex.class, "男");
		String value2=getValueByCode(TestStatus.Sex.class, "1");
		System.err.println("code2:"+code2+",value2:"+value2);*/
		
		
		Object[] objects=getEnumValues("qing.yun.hui.common.utils.test.BillLianlian$CheckStatus");
		for(Object obj:objects){
			/*BillLianlian.CheckStatus status=(CheckStatus) obj;
			System.out.println(status.getCode()+","+status.getName());*/
		}
		String json=getEnumJSON("qing.yun.hui.common.utils.test.BillLianlian$PayStatus");
		System.err.println("json:"+json);
		
	}
	/**
	 * 根据给定code获取value
	 * @param clzs  枚举类(该枚举类必须实现ICommonEnum接口)
	 * @param code 枚举code
	 * @return String 枚举value
	 * */
	public static String getValueByCode(Class<?> clzs,String code){
		ICommonEnum[] commonEnums=getICommonEnums(clzs);
		if(null==commonEnums) return null;
		for(ICommonEnum ienum:commonEnums){
			if(String.valueOf(ienum.getCode()).equals(code)){
				return ienum.getValue();
			}
		}
		return null;
	}
	
	/**
	 * 根据给定value获取code
	 * @param clzs  枚举类 (该枚举类必须实现ICommonEnum接口)
	 * @param value 枚举value
	 * @return String 枚举code
	 * */
	public static String getCodeByValue(Class<?> clzs,String value){
		ICommonEnum[] commonEnums=getICommonEnums(clzs);
		if(null==commonEnums) return null;
		for(ICommonEnum ienum:commonEnums){
			if(String.valueOf(ienum.getValue()).equals(value)){
				return String.valueOf(ienum.getCode());
			}
		}
		return null;
	}
	
	/**
	 * 以声明顺序返回一个数组，该数组包含构成此class对象所表示的枚举类的值
	 * @param className 枚举类(包名+类名，)
	 * @return Object[] 枚举类的值
	 * **/
	public static Object[] getEnumValues(String className) {
		Object[] enumobjs = null;
		try{
			Class<?> enums = Class.forName(className);
			enumobjs=getEnumConstants(enums);
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return enumobjs;
	}
	
	/**
	 * 枚举转JSON串
	 * @param className 枚举类(名名+类名)
	 * @return String
	 * **/
	public static String getEnumJSON(String className) {
		ICommonEnum[] ts = (ICommonEnum[]) getEnumValues(className);
		Map<Object, Object> map = new HashMap<Object, Object>(); 
		for(ICommonEnum t : ts) {
			map.put(t.getCode(),t.getValue());
		}
		return JSON.toJSONString(map);
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
}

class TestStatus{
	
	public enum Status implements ICommonEnum{
		SUCCESS("1","成功"),
		FAULT("2","失败");
		private String code;
		private String value;
		
		private Status(String code,String value){
			this.code=code;
			this.value=value;
		}
		
		public String getValue() {
			return value;
		}

		public String getCode() {
			return code;
		}
	}
	
	public enum Sex{
		MAN("1","男"),
		WOMEN("2","女");
		
		private String code;
		private String value;
		
		private Sex(String code,String value){
			this.code=code;
			this.value=value;
		}
		
		public String getCode() {
			return code;
		}
		public String getValue() {
			return value;
		}
		
	}
}
