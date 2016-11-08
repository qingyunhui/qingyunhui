package qing.yun.hui.common.enums;

import lombok.Getter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月8日下午10:45:03
 **/
public class Test {

	@Getter
	public enum Time{
		
		WOMAN(1,"女"),
		MAN(2,"男");
		
		private Time(int value,String name){
			this.value=value;
			this.name=name;
		}
		
		private final int value;
		
		private final String name;
		
		public String getCode() {
			return String.valueOf(value);
		}
	}
}
