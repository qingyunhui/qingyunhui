package qing.yun.hui.common.enums;
/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:22:55 AM
 ** @version: V1.0
 ***/
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

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	/**
	 * 根据给定的value查询对应的name。
	 * @param value 待查询的value
	 * @return String
	 * **/
	public static String getValueByName(int value){
		for(Sex acsta:values()){
			if(acsta.getValue()==value){
				return acsta.getName();
			}
		}
		return null;
	}
	
}
