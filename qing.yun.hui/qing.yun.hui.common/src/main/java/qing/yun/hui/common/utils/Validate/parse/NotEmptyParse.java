package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.constraints.NotEmpty;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午4:07:05
 **/
public class NotEmptyParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		NotEmpty notEmpty=(NotEmpty)anno;
		validate.add(field.getName(), "required", true, notEmpty.message());
	}
}
