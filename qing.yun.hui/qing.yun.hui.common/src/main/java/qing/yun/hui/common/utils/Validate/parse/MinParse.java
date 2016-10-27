package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.validation.constraints.Min;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午4:05:16
 **/
public class MinParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		Min min=(Min)anno;
		validate.add(field.getName(), "min", min.value(), min.message());
	}
}
