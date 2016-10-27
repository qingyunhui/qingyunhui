package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.validation.constraints.Digits;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午4:18:15
 **/
public class DigitsParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		Digits digits = (Digits) anno;
        validate.add(field.getName(), "decimal", new int[]{digits.integer(), digits.fraction()},digits.message());
	}
}
