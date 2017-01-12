package qing.yun.hui.common.struct.juhe.phone.telephone;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月13日上午9:48:12
 **/
@Getter
@Setter
public class TelephoneData {
	
	private String city;
	
	private Integer lng;
	
	private Integer lat;
	
	private String name;
	
	private String addr;
	
	private String tel;
	
/*"hy": {    号码详细，可能部分号码为NULL
    "city": "上海",
    "lng": "0",
    "lat": "0",
    "name": "上海xxxxxx有限公司",
    "addr": "",
    "tel": "021-51860253"
},*/

}
