package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.validation.constraints.Pattern;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午4:09:17
 **/
public class PatternParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		Pattern pattern=(Pattern)anno;
		validate.add(field.getName(), "regexp", pattern.regexp(), pattern.message());
	}
}
