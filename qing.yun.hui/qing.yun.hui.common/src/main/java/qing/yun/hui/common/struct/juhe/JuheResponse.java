package qing.yun.hui.common.struct.juhe;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月12日下午3:02:42
 **/
@Getter
@Setter
public class JuheResponse {
	
	/**	错误码*/
	private int error_code	;
	
	/** 成功码**/
	private String resultcode;
	
	/** 返回说明*/
 	private String reason	;
 	
 	private String result;
 	
}
