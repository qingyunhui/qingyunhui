package qing.yun.hui.common.struct.weather;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月9日下午6:19:21
 **/
@Getter
@Setter
public class WeatherData {

	private String date;//"周二"
	
	private String dayPictureUrl;//图片url
	
	private String nightPictureUrl;//图片url
	
	private String weather;//"多云转阴"
	
	private String wind;//"东北风微风"
	
	private String temperature;// "10 ~ 5℃"
	
}
