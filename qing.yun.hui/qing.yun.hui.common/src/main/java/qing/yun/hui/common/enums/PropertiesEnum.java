package qing.yun.hui.common.enums;

import lombok.Getter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月3日下午4:18:50
 **/
@Getter
public enum PropertiesEnum implements ICommonEnum{

	JDBC_DRUID_USER(0,"jdbc_username"),
	JDBC_DRUID_PASSWORD(1,"jdbc_password"),
	REDIS_IP(2,"redis.ip"),
	REDIS_PORT(3,"redis.port"),
	REDIS_PASSWORD(4,"redis.password");
	
	private final int value;
	private final String name;

	private PropertiesEnum(int value,String name){
		this.value=value;
		this.name=name;
	}

	public String getCode() {
		return String.valueOf(value);
	}
}
