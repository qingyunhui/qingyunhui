package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.constraints.Length;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午3:58:11
 **/
public class LengthParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		Length len=(Length)anno;
		validate.add(field.getName(), "rangelength", new int[]{len.min(), len.max()}, len.message());
	}
}
