package qing.yun.hui.poitool.util.excel.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 ** @Description: 单元格的sheet设置
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 4:17:21 PM
 ** @version: V1.0
 ***/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {

	/**
	 * 工作薄索引
	 * @defalut  "工作表{i}"
	 */
	String name() default "";//工作薄名称
	/**
	 * 工作薄索引
	 * @defalut  0
	 */
	int sheetIndex() default 0;
	/**
	 * 标题所在行号
	 * @defalut  0
	 */
	int titleNum() default 0;
	/**
	 * 忽略一些没有必要的异常，一般为对值进行类型转换时候会出现<br/>
	 * 一旦出现异常，程序不会中断，相应字段的值会以默认值得方式被赋予<br/>
	 * @defalut  false
	 */
	boolean ignoreException() default false;

}
