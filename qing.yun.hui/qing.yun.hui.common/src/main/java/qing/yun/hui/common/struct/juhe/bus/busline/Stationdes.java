package qing.yun.hui.common.struct.juhe.bus.busline;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日下午1:39:16
 **/
@Getter
@Setter
public class Stationdes {
	
	private String code;
	
	private Integer stationNum;
	
	private String name;
	
	/**经纬度*/
	private String xy;
	
	/*{
        "code": "330100", 
        "stationNum": "1", 
        "name": "翠苑四区", 
        "xy": "120.119137,30.287073"
     }*/

}
