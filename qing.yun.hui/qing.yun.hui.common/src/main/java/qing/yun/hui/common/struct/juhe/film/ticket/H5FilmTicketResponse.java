package qing.yun.hui.common.struct.juhe.film.ticket;

import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.struct.juhe.JuheResponse;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日上午9:26:36
 **/
@Getter
@Setter
public class H5FilmTicketResponse extends JuheResponse{
	
	/**	H5页面的URL*/
	private String h5url;
	
	/** 微信公众号专用h5*/
	private String h5weixin;
	
	private String result;
	
  /**
	{
	    "reason": "请求成功", 
	    "result": {
	        "h5url": "http://v.juhe.cn/wepiao/go?key=8e695f0e8a57e2c13e66639854cd4024", 
	        "h5weixin": "http://v.juhe.cn/wepiao/go?key=8e695f0e8a57e2c13e66639854cd4024&s=weixin"
	    }, 
	    "error_code": 0
	}
  */
}
