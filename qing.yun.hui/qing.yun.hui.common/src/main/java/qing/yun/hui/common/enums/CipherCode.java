package qing.yun.hui.common.enums;

import lombok.Getter;

/***
 ** @Description: 自定义加密的key
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: 2016年4月9日 下午9:51:27
 ** @version: V1.0
 ***/
@Getter
public enum CipherCode implements ICommonEnum{
	
	QING_YUN_HUI("qing.yunhui",0,"卿云辉"),
	QING_YUN_FEI("qing.yunfei",1,"卿云飞");
	private final String key;
	private final int value;
	private final String name;

	private CipherCode(String key,int value,String name){
		this.key=key;
		this.value=value;
		this.name=name;
	}

	public String getCode() {
		return String.valueOf(value);
	}
}
