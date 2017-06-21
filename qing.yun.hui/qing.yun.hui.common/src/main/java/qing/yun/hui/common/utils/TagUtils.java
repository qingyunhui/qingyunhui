
package qing.yun.hui.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import qing.yun.hui.common.enums.ICommonEnum;

/**
 * Description: <p>针对velocity模板中调用枚举类(所调用的枚举类必须实现ICommonEnum接口)的处理</p>
 *
 * @author <a href="mailto:qingyunhui@zuozh.com">qingyunhui</a>
 * @Date Create on 2016年8月3日
 * @since version1.0 Copyright 2015 ZZJR All Rights Reserved.
 */
public class TagUtils {
	
	/**
	 * <p枚举类转换成JSON串</p>
	 * @param className
	 * @return String
	 * */
	public static String getEnumToJSON(String className){
		ICommonEnum[] objects=(ICommonEnum[]) getEnumValues(className);
		Map<String,Object> map=new HashMap<String,Object>();
		for(ICommonEnum ice:objects){
			map.put(ice.getCode(), ice.getValue());
		}
		return JSONObject.toJSONString(map);
	}

    /**
     * <p>以声明顺序返回一个数组，该数组包含构成此class对象所表示的枚举类的值</p>
     * <p>前台调用:$!enumTool.getEnumValues("com.zzjr.commons.enums.BillLianlian$CheckStatus"))</p>
     *
     * @param className 枚举类(包名+类名，注意调用内部类的区别)
     * @return Object[] 枚举类的值
     **/
    public static Object[] getEnumValues(String className) {
        return EnumUtil.getEnumValues(className);
    }

    /**
     * <p>根据给定code获取value</p>
     * <p>前台调用:$!enumTool.getValueByCode("com.zzjr.commons.enums.BillLianlian$CheckStatus",code))</p>
     *
     * @param clzs 枚举类(该枚举类必须实现ICommonEnum接口)
     * @param code 枚举code
     * @return String 枚举value
     */
    public static String getValueByName(String className, String name) {
        return EnumUtil.getNameByValue(className, name);
    }

    /**
     * 根据给定value获取Name
     * <p>前台调用:$!enumTool.getNameByValue("包名.类名",value))</p>
     *
     * @param className  枚举类 (该枚举类必须实现ICommonEnum接口)
     * @param value 枚举value
     * @return String 枚举Name
     */
    public static String getNameByValue(String className, int value) {
        Class<?> clzs = getClass(className);
        if (null == clzs) return null;
        return EnumUtil.getNameByValue(clzs, value);
    }

    private static Class<?> getClass(String className) {
        Class<?> clzs = null;
        try {
            clzs = Class.forName(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clzs;
    }
}
