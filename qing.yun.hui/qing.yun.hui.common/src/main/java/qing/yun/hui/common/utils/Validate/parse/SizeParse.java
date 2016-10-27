package qing.yun.hui.common.utils.Validate.parse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.validation.constraints.Size;

import qing.yun.hui.common.utils.Validate.bean.Validata;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午4:13:49
 **/
public class SizeParse implements IValidataParse{

	public void parse(Validata validate, Field field, Annotation anno) {
		Size size=(Size)anno;
		validate.add(field.getName(), "rangelength", new int[]{size.min(), size.max()},size.message());
	}
}
