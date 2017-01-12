package qing.yun.hui.common.struct.juhe.idcard;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午2:58:11
 **/
@Getter
@Setter
public class IdCardResponse {

 	private String area	;//	地区
 	private String sex	;//	性别
 	private String birthday	;//	出生日期
	
 	//=============----=================
 	
 	private int    error_code	;//	返回码
 	private String reason	;//	返回说明
 	private String result	;//	返回结果集
}
