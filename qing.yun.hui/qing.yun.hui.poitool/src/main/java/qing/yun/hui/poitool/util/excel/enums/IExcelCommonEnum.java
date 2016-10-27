package qing.yun.hui.poitool.util.excel.enums;
/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:28:26 PM
 ** @version: V1.0
 ***/
public interface IExcelCommonEnum {
	
	public String getName();
	public int getValue();
	
	/**
	 * 
	 * 通过值查找到名称， 通常用在 列表或者详情页面获取值
	 * 由于枚举不能继承， 接口不能声明静态方法
	 * 
	 */
	
}
