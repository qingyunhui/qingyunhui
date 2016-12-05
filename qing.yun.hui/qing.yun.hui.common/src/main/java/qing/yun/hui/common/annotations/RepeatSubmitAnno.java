package qing.yun.hui.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 ** @category 防止表单重复提交注解类...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月5日上午10:38:24
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmitAnno {
	
	 /**
	  * @category <p>用于防止表单重复提交，比如:双击按钮或者提交表单后单击浏览器的返回按钮的处理</p>
	  * <p>用于须要验证重复提交表单的action的注解上</p>
	  * <p>用在action控制器上（表单提交之前的action上）</p>
	  * */
	 boolean save() default false;

	 /**
	  * @category <p>用于防止表单重复提交</p>
	  * <p>用于真正提交到服务器上的action的注解上</p>
	  * **/
	 boolean remove() default false;

}
