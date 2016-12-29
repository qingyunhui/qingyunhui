package qing.yun.hui.common.test;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月30日下午4:29:17
 **/
@Getter
@Setter
public class Student {
	
	private Integer age;
	
	private String sex;
	
	public Student(){}
	
	public Student(Integer age,String sex){
		this.age=age;
		this.sex=sex;
	}

}
