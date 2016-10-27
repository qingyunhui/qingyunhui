package qing.yun.hui.common.enums;
/***
 ** @Description:  一般键值型枚举的接口，规范 获取键和值的方式
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:24:19 AM
 ** @version: V1.0
 ***/
public interface ICommonEnum {
	
	public String getName();
	public int getValue();
	
	/**
	 * 
	 * 通过值查找到名称， 通常用在 列表或者详情页面获取值
	 * 由于枚举不能继承， 接口不能声明静态方法
	 * 
	 */

}
