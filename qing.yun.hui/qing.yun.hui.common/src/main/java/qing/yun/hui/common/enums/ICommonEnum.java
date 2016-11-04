package qing.yun.hui.common.enums;
/***
 ** @Description:  一般键值型枚举的接口，规范 获取键和值的方式
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:24:19 AM
 ** @version: V1.0
 ***/
public interface ICommonEnum {
	
	/**getName**/
	public String getName();
	
	/**getValue()与getCode()匹配，会做类型转换**/
	public int getValue();
	
	/**getCode()与getValue匹配，会做类型转换*/
	public String getCode();
	
}
