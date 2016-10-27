package qing.yun.hui.common.enums;
/***
 ** @Description: 加密摘要
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: 2016年4月9日 下午9:51:27
 ** @version: V1.0
 ***/
public enum CipherDigest {
	
	UN_ENCRYPT(0,"未加密"),
	ENCRYPTED(1,"已加密"),
	PWD_MISMATCH(3,"密码不匹配"),
	ENCRYPTED_MATCHING(4,"已加密且已匹配");
	private final int value;
	private final String name;

	private CipherDigest(int value,String name){
		this.value=value;
		this.name=name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
