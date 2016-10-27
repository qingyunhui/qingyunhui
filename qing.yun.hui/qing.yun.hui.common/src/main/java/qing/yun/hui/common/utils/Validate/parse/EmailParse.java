package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.constraints.Email;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 邮箱...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午3:25:14
 **/
public class EmailParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		Email em=(Email)anno;
		validate.add(field.getName(), "email", true, em.message());
	}
}
