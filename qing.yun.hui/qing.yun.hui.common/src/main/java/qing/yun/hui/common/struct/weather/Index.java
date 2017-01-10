package qing.yun.hui.common.struct.weather;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 天气指数...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月9日下午6:14:00
 **/
@Getter
@Setter
public class Index implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String title;
	
	private String zs ;
	
	private String tipt;
	
	private String des;

}
