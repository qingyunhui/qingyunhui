package qing.yun.hui.common.struct.mobile;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 手机号返回的信息...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月11日下午3:05:38
 **/
@Getter
@Setter
public class MobileResponse {

	private int errorCode	;//返回码
	private String reason	;//返回说明
	private String result	;//返回结果集
	private String province	;//省份
	private String city		;//城市
	private String areacode	;//区号
	private String zip		;//邮编
	private String company	;//运营商
	private String card		;//卡类型
	
}
