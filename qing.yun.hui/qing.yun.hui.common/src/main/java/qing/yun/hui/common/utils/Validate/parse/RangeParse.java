package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.hibernate.validator.constraints.Range;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午4:11:19
 **/
public class RangeParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		Range range=(Range)anno;
		validate.add(field.getName(), "range", new Long[]{range.min(),range.max()}, range.message());
		validate.add(field.getName(), "digits", true, range.message());
	}
}
