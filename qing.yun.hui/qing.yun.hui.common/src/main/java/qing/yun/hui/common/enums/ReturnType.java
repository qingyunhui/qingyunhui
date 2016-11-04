package qing.yun.hui.common.enums;

import lombok.Getter;

/***
 ** @Description: 返回类型
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: 2016年4月12日 下午4:54:37
 ** @version: V1.0
 ***/
@Getter
public enum ReturnType implements ICommonEnum{
	

	IS_EMPTY(0,"目标为空"),
	UN_EXIST(1,"目标不存在"),
	IS_OK(8,"合法的");
	private final int value;
	private final String name;

	private ReturnType(int value,String name){
		this.value=value;
		this.name=name;
	}

	public String getCode() {
		return String.valueOf(value);
	}
}
