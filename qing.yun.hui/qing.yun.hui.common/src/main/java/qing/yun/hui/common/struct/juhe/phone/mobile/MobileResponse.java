package qing.yun.hui.common.struct.juhe.phone.mobile;

import qing.yun.hui.common.struct.juhe.JuheResponse;
import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午2:13:55
 **/
@Getter
@Setter
public class MobileResponse extends JuheResponse{

	//{"result":{"zip":"18000","company":"联通","province":"广东","card":"","areacode":"0755","city":"深圳"},"reason":"Return Successd!","error_code":0,"resultcode":"200"}
	
 	/** 省份*/
 	private String province	;
 	
 	/** 城市*/
 	private String city		;
 	
 	/** 区号*/
 	private String areacode	;
 	
 	/** 邮编*/
 	private String zip		;
 	
 	/** 运营商*/
 	private String company	;
 	
 	/** 卡类型*/
 	private String card		;
 	
 	//=============----=================
 	
	/** 返回结果集*/
 	private String result	;
 	
}
