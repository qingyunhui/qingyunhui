package qing.yun.hui.common.utils.persistent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年8月1日下午11:01:44
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Anno {

	 /**<P>序号:用于排序</p>**/
	 int order();
	 
	 /**<P>名称:用于描述字段</p>**/
	 String name() default "";
	 
	 /**<P>备注:字段的一些描述</p>**/
	 String remark() default "";
	
}
