package qing.yun.hui.common.test;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月30日下午4:28:39
 **/
@Getter
@Setter
public class Teach {

	private Integer id;
	
	private String name;
	
	public Teach(){}
	
	public Teach(Integer id,String name){
		this.id=id;
		this.name=name;
	}
}
