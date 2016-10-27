package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午3:21:15
 **/
public interface IValidataParse {

	 /**
	  * 校验
	  * @param validate 
	  * @param field 校验的字段
	  * @param anno 校验规则
	  * **/
	 void parse(Validata validate, Field field, Annotation anno);
	
}
