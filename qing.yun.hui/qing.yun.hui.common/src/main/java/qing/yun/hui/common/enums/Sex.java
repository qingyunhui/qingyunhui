package qing.yun.hui.common.enums;

import lombok.Getter;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:22:55 AM
 ** @version: V1.0
 ***/
@Getter
public enum Sex implements ICommonEnum{
	
	SECRET(0, "保密"),
	WOMAN(1,"女"),
	MAN(2,"男");
	
	private Sex(int value,String name){
		this.value=value;
		this.name=name;
	}
	
	private final int value;
	
	private final String name;
	
	public String getCode() {
		return String.valueOf(value);
	}
}
