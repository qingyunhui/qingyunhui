package qing.yun.hui.common.utils.Validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import qing.yun.hui.common.test.Collections.sort.po.SysDictForm;
import qing.yun.hui.common.utils.Validate.bean.Validata;
import qing.yun.hui.common.utils.Validate.parse.DigitsParse;
import qing.yun.hui.common.utils.Validate.parse.EmailParse;
import qing.yun.hui.common.utils.Validate.parse.IValidataParse;
import qing.yun.hui.common.utils.Validate.parse.LengthParse;
import qing.yun.hui.common.utils.Validate.parse.MaxParse;
import qing.yun.hui.common.utils.Validate.parse.MinParse;
import qing.yun.hui.common.utils.Validate.parse.NotEmptyParse;
import qing.yun.hui.common.utils.Validate.parse.NotNullParse;
import qing.yun.hui.common.utils.Validate.parse.PatternParse;
import qing.yun.hui.common.utils.Validate.parse.RangeParse;
import qing.yun.hui.common.utils.Validate.parse.SizeParse;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月13日下午4:20:47
 **/
public class ValidataUtil {
	
	private static Map<String,IValidataParse> parse=null;
	
	static{
		init();
	}
	
	private static void init(){
		if (parse == null) {
            parse = new HashMap<String, IValidataParse>();
            parse.put(Email.class.getName(), new EmailParse());
            parse.put(Length.class.getName(), new LengthParse());
            parse.put(Max.class.getName(), new MaxParse());
            parse.put(Min.class.getName(), new MinParse());
            parse.put(NotEmpty.class.getName(), new NotEmptyParse());
            parse.put(Pattern.class.getName(), new PatternParse());
            parse.put(Range.class.getName(), new RangeParse());
            parse.put(Size.class.getName(), new SizeParse());
            parse.put(NotNull.class.getName(), new NotNullParse());
            parse.put(Digits.class.getName(), new DigitsParse());
        }
	}
	
	 public static Validata getValidate(Class<?> clz) {
	        Validata validate = new Validata();
	        for (Field field : clz.getDeclaredFields()) {
	            Annotation[] anno = field.getAnnotations();
	            if (anno.length > 0) {
	                for (Annotation ann : anno) {
	                	IValidataParse validataParse = parse.get(ann.annotationType().getName());
	                    if (validataParse != null) {
	                    	validataParse.parse(validate, field, ann);
	                    }
	                }
	            }
	        }
	        return validate;
	    }
	 
	 public static void main(String[] args) {
	     System.out.println(ValidataUtil.getValidate(SysDictForm.class));
	 }
}
