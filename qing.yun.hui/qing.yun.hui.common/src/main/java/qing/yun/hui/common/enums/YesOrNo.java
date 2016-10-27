package qing.yun.hui.common.enums;

/***
 ** @Description: YesOrNO 是or否
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:21:21 AM
 ** @version: V1.0
 ***/
public enum YesOrNo implements ICommonEnum{
	
	YES(1,"是","通用"),
	NO(0,"否","通用");
	
	private final int value;
    private final String name;
    private final String desc;
    
    private YesOrNo(int value, String name,String desc) {
        this.value = value;
        this.name = name;
        this.desc=desc;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

	public String getDesc() {
		return desc;
	}

}
