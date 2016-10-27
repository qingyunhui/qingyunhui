package qing.yun.hui.poitool.util.excel.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import qing.yun.hui.poitool.util.excel.bean.IExcelCustomConverter;
import qing.yun.hui.poitool.util.excel.enums.ExcelColumnType;
import qing.yun.hui.poitool.util.excel.enums.ExcelNullEnum;

/***
 ** @Description: 导入导出字段的注解
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:14:06 PM
 ** @version: V1.0
 ***/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
	
	/**
	 * Excel 标题名称
	 */
	String title(); 
	/**
	 * 输出的的格式
	 */
	String format() default ""; 
	/**
	 * 在什么时间处理，导入， 导出， 全部 
	 */
	ExcelColumnType type() default ExcelColumnType.ALL; 
	/**
	 * 导出时标题的顺序 由小到大
	 */
	int order() default 999;
	/**
	 * 导入时候的简单验证 
	 * FIXME 以后再处理
	 */
	String validate() default "";
	/**
	 * 枚举类型的相互转换,必须是 ICommonEnum 的实现
	 */
	Class<?> enumType()  default ExcelNullEnum.class;
	
	/**
	 * 自定义处理器，可以用于自已定义数据的转换，比如从数据库查出来的值，或者经过计算的值
	 * FIXME  么有实现，基础架构已经搭建好了，如有需要再去实现
	 */
	Class<?> customType()  default IExcelCustomConverter.class;
	
	/**
	 * 列宽(px),大差不差，不是很精准，<br/>0 默认值，为不处理，
	 *           <br/>-1 自动宽度，
	 *          <br/> 其他值 就会设置为宽度
	 *  
	 * @default 0
	 */
	int width() default 0;
	
}
