package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.validation.constraints.NotNull;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午4:16:10
 **/
public class NotNullParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		NotNull notNull=(NotNull)anno;
		validate.add(field.getName(), "required", true, notNull.message());
	}
}
